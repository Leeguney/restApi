
package com.app.common.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * api message enum클래스
 * @author guney
 * @date 2024. 4. 11.
 */
@AllArgsConstructor
public enum ApiCommEnum {
    
    SUCCESS(HttpStatus.OK.value()                    , "성공하였습니다."),
    FAILED (HttpStatus.INTERNAL_SERVER_ERROR.value() , HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

    @Getter
    private Integer code;
    
    @Getter
    private String message;
    
}
