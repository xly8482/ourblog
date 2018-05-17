package com.example.demo.service;

import java.io.IOException;
import java.io.InputStream;

public interface ResourceService
{

    public Boolean uploadResource(String fileName, InputStream in) throws IOException;
}
