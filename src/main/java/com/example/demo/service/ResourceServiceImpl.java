package com.example.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ResourceServiceImpl implements ResourceService
{
    @Value("${uploadFilepath}")
    private String uploadPath;

    @Override
    public Boolean uploadResource(String fileName, InputStream in) throws IOException
    {
        File f = new File(uploadPath + File.separator + fileName);
        FileUtils.copyInputStreamToFile(in, f);
        if (null != in)
        {
            in.close();
        }
        return true;
    }

}
