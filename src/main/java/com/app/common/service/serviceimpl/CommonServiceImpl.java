package com.app.common.service.serviceimpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.app.common.constant.CommonConstant;
import com.app.common.exception.DataCustomException;
import com.app.common.service.CommonService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonConstant commonConstant;
    
    /**
     * 파일 다운로드
     * 
     * @parma  : HttpServletResponse response, File file
     * @return : void
     * @throws UnsupportedEncodingException 
     * @user   : guney
     * @date   : 2024. 4. 14.
     * @since  : 1.0
     */
    public void fileDownload(HttpServletResponse response, File file) throws UnsupportedEncodingException {
        if (file.isFile()) {
            String fileName = file.getName();
            String saveFileName = commonConstant.FILE_PATH.concat("/".concat(fileName));
            
            long fileLength = file.length();
            
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < fileName.length(); i++) {
                char c = fileName.charAt(i);
                if (c > '~') {
                    sb.append(URLEncoder.encode("" + c, "UTF-8"));
                } else {
                    sb.append(c);
                }
            }
            fileName = sb.toString();
            
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ";");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("Content-Length", "" + fileLength);
            response.setHeader("Pragma", "no-cache;");
            response.setHeader("Expires", "-1;");
            
            try {
                FileInputStream fis = new FileInputStream(saveFileName);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                
                int readCount = 0;
                byte[] buffer = new byte[1024];
                
                while((readCount = fis.read(buffer)) != -1){
                    out.write(buffer, 0, readCount);
                }
                out.writeTo(response.getOutputStream());
                
                fis.close();
                out.close();
                
                if (!file.delete()) {
                    log.error("delete File Info : {}", file);
                }
                
            } catch(Exception ex){
                log.error("fileDownload ERROR : {}", ex.getMessage());
                throw new DataCustomException("file Save Error");
            }
        }
    }
}
