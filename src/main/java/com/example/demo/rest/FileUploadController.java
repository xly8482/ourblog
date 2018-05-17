package com.example.demo.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.ResourceService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController
{
    @Autowired
    private ResourceService resourceService;

    private static final String JSON_PRODUCES = "application/json;charset=UTF-8";

    @Value("${uploadFilepath}")
    private String uploadPath;

    @Value("${baiduApiAccessToken}")
    private String accessToken;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    @RequestMapping(value = "/uploadPage")
    public ModelAndView test(ModelAndView mv)
    {
        mv.setViewName("/uploadPage");
        return mv;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @PostMapping(value = "/resource", produces = JSON_PRODUCES)
    public ModelAndView upload(HttpServletRequest request) throws IOException
    {
        ModelAndView mv = new ModelAndView();
        Map<String, Object> ret = new HashMap<>();
        if (!(request instanceof MultipartHttpServletRequest))
        {
            ret.put("code", "404");
            ret.put("desc", "no file found");
            mv.setViewName("/error");
            return mv;
        }
        MultipartFile mfile = ((MultipartHttpServletRequest)request).getFile("file");
        if (null == mfile)
        {
            ret.put("code", "404");
            ret.put("desc", "no file found");
            mv.setViewName("/error");
            return mv;
        }
        InputStream in = mfile.getInputStream();
        Boolean uploadResult = resourceService.uploadResource(mfile.getOriginalFilename(), in);
        if (uploadResult)
        {
            List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
            FormHttpMessageConverter httpMessageConverter = (FormHttpMessageConverter)messageConverters.get(0);
            List<MediaType> supportedMediaTypes = new ArrayList<>();
            supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
            supportedMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
            supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
            httpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
            String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate_basic?access_token={access_token}";
            String imageString = Base64.encodeBase64String(mfile.getBytes()); // 图片转成base64编码
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 在header里面设置编码方式
            MultiValueMap<String, Object> requestBody = new LinkedMultiValueMap<>();
            requestBody.add("image", imageString);
            HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(requestBody, httpHeaders);
            ResponseEntity<MultiValueMap> responseEntity = restTemplate.postForEntity(url, requestEntity, MultiValueMap.class, accessToken);
            if (responseEntity.getStatusCode().is2xxSuccessful())
            {
                MultiValueMap<Object, Object> body = responseEntity.getBody();
                System.out.println(body.toString());
                String result = body.toString().substring(1, body.toString().length() - 8);
                System.out.println(result);
                Map<String, Object> resultMap = gson.fromJson(result, Map.class);
                List<Map<String, String>> wordsResult = (List<Map<String, String>>)resultMap.get("words_result");
                mv.addObject("wordsResult", wordsResult);
            }
        }
        else
        {
            mv.setViewName("/error");
        }
        mv.setViewName("/imageIdentifyResult");
        return mv;
    }
}
