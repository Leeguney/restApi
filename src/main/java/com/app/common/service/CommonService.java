package com.app.common.service;

import java.io.File;
import java.io.UnsupportedEncodingException;

import jakarta.servlet.http.HttpServletResponse;

public interface CommonService {
    public void fileDownload(HttpServletResponse response, File file) throws UnsupportedEncodingException;
}
