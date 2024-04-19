package com.app.common.exception;

import java.io.Serial;

import com.app.common.enums.ApiCommEnum;

/**
 * 예외 핸들링 커마
 * @author guney
 * @date 2024. 4. 11.
 */
public class DataCustomException extends BaseException {

    @Serial
    private static final long serialVersionUID = 2723351424020151736L;
    private static final String CODE = ApiCommEnum.FAILED.getCode().toString();
    private static final String MESSAGE = ApiCommEnum.FAILED.getMessage();


    public DataCustomException() {
        super(CODE, MESSAGE);
    }

    public DataCustomException(String message){
        super(message);
        super.setCode(CODE);
    }

    public DataCustomException(String code, String message){
        super(code, message);
    }

    public DataCustomException(Throwable cause, String code, String message){
        super(cause, code, message);
    }
}
