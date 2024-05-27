package com.app.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.http.MediaType;

import com.app.common.exception.ValidException;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {

    /**
     * 파일 다운로드
     * 
     * @param HttpServletResponse response, String filePathParam, File file
     * @return void
     * @author guney
     * @date 2024. 5. 23.
     */
    public static void fileDownload(HttpServletResponse response, String filePathParam, File file) throws ValidException, IOException {
        String fileName = file.getName();
        String saveFileName = filePathParam.concat("/".concat(fileName));
        
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
        
        //파일삭제
        if (!file.delete()) {
            log.error("delete File Info : {}", file);
            throw new ValidException("파일 삭제에 실패하였습니다.");
        }
    }
    
    /**
     * 파일을 저장
     * 
     * @param File file         : 파일객체
              String content    : 저장할 내용
     * @return void
     * @author guney
     * @date 2024. 5. 24.
     */
    public static void writeFile(File file, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(content.getBytes());
            fos.flush();
        }
    }

    /**
     * 파일 내용을 추가
     * 
     * @param File file         : 파일객체
              String content    : 저장할 내용
     * @return void
     * @author guney
     * @date 2024. 5. 24.
     */
    public static void appendToFile(File file, String content) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(file, true)) {
            fos.write(content.getBytes());
            fos.flush();
        }
    }
    
}
