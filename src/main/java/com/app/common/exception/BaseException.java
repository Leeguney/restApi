package com.app.common.exception;

import java.io.Serial;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 공통 예외처리
 * @author guney
 * @date 2024. 4. 11.
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6529993137441563838L;
    
    private String code;
    private String baseMessage;
    private boolean result;

    public BaseException(String message) {
        super(message);
        this.code = "code";
        this.baseMessage = message;
        this.result = false;
    }

    public BaseException(String code, String message) {
        super(message);
        this.code = code;
        this.baseMessage = message;
        this.result = false;
    }

    public BaseException(Throwable cause, String code, String message) {
        super(cause);
        this.code = code;
        this.baseMessage = message;
        this.result = false;
    }

    public BaseException(Throwable cause, String code) {
        super(cause);
        this.code = code;
        this.baseMessage = null;
        this.result = false;
    }
}
