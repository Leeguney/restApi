package com.app.common.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.app.common.enums.MessageEnum;
import com.app.common.exception.ValidException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class RestUtil {
    
    private static final ObjectMapper om = new ObjectMapper();
    
    private static final RestTemplate restTemplate = new RestTemplate();
    
	/**
     * restAPI exchange ResponseEntity 유틸 
     * 
     * @param url : 호출 url
     *          m : HttpMethod
     *          o : 파라미터(Object)
     *          c : 모델 클래스
     * @return 모델 클래스 : dto, vo
     * @author guney
     * @throws Exception 
     * @date 2024. 3. 13.
     */
	public static <O, C> C exchange(URI uri, HttpMethod m, O o, Class<C> c) throws ValidException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<C> result = null;
        
        try {
            HttpEntity<O> httpEntity = new HttpEntity<>(o, headers);
            result = restTemplate.exchange(uri, m, httpEntity, c);
            
            if (ObjectUtils.isEmpty(result)) {
                return null;
            }
            
        } catch (Exception e) {
            log.error("RestUtil exchange ERROR : {}", e.getMessage());
            throw new ValidException(MessageEnum.INTERNAL_SERVER_ERROR);
        }
	    
        return om.convertValue(result.getBody(), c);
	}
	
	public static <O, C> C exchange(String url, HttpMethod m, O o, Class<C> c) throws ValidException, URISyntaxException {
        return exchange(new URI(url), m, o, c);
    }
}
