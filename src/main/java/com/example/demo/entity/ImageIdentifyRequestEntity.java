package com.example.demo.entity;

import java.io.Serializable;

public class ImageIdentifyRequestEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = 3646069135634525698L;

    /**
     * 和url二选一
     * 图像数据，base64编码后进行urlencode，要求base64编码和urlencode后大小不超过4M，最短边至少15px，
     * 最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效
     */
    private String image;

    /**
     * 和url二选一
     * 图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，
     * 最长边最大4096px,支持jpg/png/bmp格式，当image字段存在时url字段失效，不支持https的图片链接
     */
    private String url;

    /**
     * 识别语言类型，默认为CHN_ENG。可选值包括：
     * - CHN_ENG：中英文混合；
     * - ENG：英文；
     * - POR：葡萄牙语；
     * - FRE：法语；
     * - GER：德语；
     * - ITA：意大利语；
     * - SPA：西班牙语；
     * - RUS：俄语；
     * - JAP：日语；
     * - KOR：韩语
     */
    private String language_type;

    /**
     * 是否检测图像朝向，默认不检测，
     * 即：false。朝向是指输入图像是正常方向、逆时针旋转90/180/270度。可选值包括:
     * - true：检测朝向；
     * - false：不检测朝向。
     */
    private boolean detect_direction;

    /**
     * 是否检测语言，默认不检测。当前支持（中文、英语、日语、韩语）
     * 取值范围：true、false
     */
    private String detect_language;

    /**
     * 是否返回识别结果中每一行的置信度
     * 取值范围：true、false
     */
    private String probability;

    public String getImage()
    {
        return image;
    }

    public void setImage(String image)
    {
        this.image = image;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getLanguage_type()
    {
        return language_type;
    }

    public void setLanguage_type(String language_type)
    {
        this.language_type = language_type;
    }

    public boolean isDetect_direction()
    {
        return detect_direction;
    }

    public void setDetect_direction(boolean detect_direction)
    {
        this.detect_direction = detect_direction;
    }

    public String getDetect_language()
    {
        return detect_language;
    }

    public void setDetect_language(String detect_language)
    {
        this.detect_language = detect_language;
    }

    public String getProbability()
    {
        return probability;
    }

    public void setProbability(String probability)
    {
        this.probability = probability;
    }

}
