package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

public class ImageIdentifyResponseEntity implements Serializable
{

    /**
     * 
     */
    private static final long serialVersionUID = -7654259651036015690L;

    /**
     * 否
     * 图像方向，当detect_direction=true时存在。
     * - -1:未定义，
     * - 0:正向，
     * - 1: 逆时针90度，
     * - 2:逆时针180度，
     * - 3:逆时针270度
     */
    private int direction;

    /**
     * 是
     * 唯一的log id，用于问题定位
     */
    private long log_id;

    /**
     * 是
     * 识别结果数组
     */
    private List<String> words_result;

    /**
     * 是
     * 识别结果数，表示words_result的元素个数
     */
    private String words_result_num;

    /**
     * 否
     * 识别结果字符串
     */
    private String words;

    /**
     * 否
     * 识别结果中每一行的置信度值，包含average：行置信度平均值，variance：行置信度方差，min：行置信度最小值
     */
    private String probability;

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
    }

    public long getLog_id()
    {
        return log_id;
    }

    public void setLog_id(long log_id)
    {
        this.log_id = log_id;
    }

    public List<String> getWords_result()
    {
        return words_result;
    }

    public void setWords_result(List<String> words_result)
    {
        this.words_result = words_result;
    }

    public String getWords_result_num()
    {
        return words_result_num;
    }

    public void setWords_result_num(String words_result_num)
    {
        this.words_result_num = words_result_num;
    }

    public String getWords()
    {
        return words;
    }

    public void setWords(String words)
    {
        this.words = words;
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
