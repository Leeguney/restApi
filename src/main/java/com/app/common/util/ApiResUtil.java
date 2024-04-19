package com.app.common.util;

import com.app.common.dto.ApiBodyDTO;
import com.app.common.dto.PageDTO;
import com.app.common.enums.ApiCommEnum;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * API response Util
 * @author guney
 * @date 2024. 4. 11.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ApiResUtil {

    private static final String SUCCESS = ApiCommEnum.SUCCESS.getMessage();

    public static <T> ApiBodyDTO.Response<T> success() {
        return generateSuccess(null, null, SUCCESS);
    }

    public static <T> ApiBodyDTO.Response<T> success(String message) {
        return generateSuccess(null, null, message);
    }

    public static <T> ApiBodyDTO.Response<T> success(T data) {
        return generateSuccess(data, null, SUCCESS);
    }

    public static <T> ApiBodyDTO.Response<T> success(T data, String message) {
        return generateSuccess(data, null, message);
    }

    public static <T> ApiBodyDTO.Response<T> success(T data, PageDTO page) {
        return generateSuccess(data, page, SUCCESS);
    }

    public static <T> ApiBodyDTO.Response<T> success(T data, PageDTO page, String message) {
        return generateSuccess(data, page, message);
    }

    public static <T> ApiBodyDTO.Response<T> generateSuccess(T data, PageDTO page, String message) {
        return ApiBodyDTO.Response.<T>builder()
                .success(true)
                .message(message)
                .data(data)
                .page(page)
                .build();
    }

    public static <T> ApiBodyDTO.Response<T> failed() {
        return failed(ApiCommEnum.FAILED.getMessage());
    }
    
    public static <T> ApiBodyDTO.Response<T> failed(String message) {
        return ApiBodyDTO.Response.<T>builder()
                .success(false)
                .message(message)
                .build();
    }

}
