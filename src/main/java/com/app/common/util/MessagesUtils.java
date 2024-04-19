package com.app.common.util;

import java.util.Locale;

import org.springframework.context.support.MessageSourceAccessor;

/**
 * 메세지 유틸
 * @author guney
 * @date 2024. 4. 17.
 */
public class MessagesUtils {
    private static MessageSourceAccessor messageSourceAccessor;

    public void setMessageSourceAccessor(MessageSourceAccessor messageSourceAccessor) {
        MessagesUtils.messageSourceAccessor = messageSourceAccessor;
    }

    public static String getMessage(String code) {
        return MessagesUtils.getMessage(code, new Object[] {});
    }

    public static String getMessage(String code, Object[] args) {
        return messageSourceAccessor.getMessage(code, args);
    }

    public static String getMessage(String code, Object[] args, Locale locale) {
        return messageSourceAccessor.getMessage(code, args, locale);
    }

    public static String getMessage(String code, Locale locale) {
        return messageSourceAccessor.getMessage(code, locale);
    }

    public static String getMessage(String code, String lang) {
        if ( lang == null ) {
            return MessagesUtils.getMessage(code, new Object[] {});
        }
        return messageSourceAccessor.getMessage(code, new Locale(lang));
    }

    public static String getMessageByCode(String msg) {
        return messageSourceAccessor.getMessage(msg);
    }
}
