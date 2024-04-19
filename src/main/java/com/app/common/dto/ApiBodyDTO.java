package com.app.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author guney
 * @date 2024. 3. 18.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiBodyDTO {

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @Schema(name = "ApiBodyDTO.Request")
    public static class Request<T> {

        @Valid
        private T data;

        private PageDTO page;

        @Builder
        public Request(T data, PageDTO page) {
            this.data = data;
            this.page = page;
        }
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @Schema(name = "ApiBodyDTO.Response")
    public static class Response<T> {

        @Schema(example="true")
        private boolean success = false;
        
        @Schema(example="ERR-A0")
        private String debugCode;
        
        @Schema(example="성공")
        private String message = "통신에 실패하였습니다.";
        
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private PageDTO page;
        
        @Schema(description = "data", nullable = true)
//        @JsonInclude(JsonInclude.Include.NON_NULL)
        private T data;

        @Builder
        public Response(T data, PageDTO page, boolean success, String message) {
            this.data = data;
            this.page = page;
            this.success = success;
            this.message = message;
        }
    }
}
