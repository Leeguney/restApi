package com.app.common.util;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RexUtil {
    //소수점 체크 정규식(자릿수 제한 없음)
    public static final String DECIMAL_POINT_PATTERN = "^-?\\d*\\.?\\d+$";
    
    //yyyy-MM-dd 체크
    public static final String DATE_PATTERN = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    
    //숫자형 체크
    public static final String NUMBER_PATTERN = "\\d*";
    
    //슬래시, 백슬래시 체크
    public static final String SLASH_BACKSLASH_PATTERN = "[/\\\\]";
    
}
