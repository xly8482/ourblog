/**
 * 自定义异常
 */
package com.example.demo.exception;

public class CustomException extends RuntimeException
{

    /**
     * 
     */
    private static final long serialVersionUID = 7452538702309228915L;

    private int code;

    private String msg;

    public CustomException(int code, String msg)
    {
        this.code = code;
        this.msg = msg;
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

}
