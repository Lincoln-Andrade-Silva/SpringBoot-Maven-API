package com.api.application.utils.returns;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class InternationalizationUtil {
    public InternationalizationUtil() {
    }

    public static String getMessage(MessageSource messageSource, String desc, String locale) {
        Locale localeValue = new Locale(locale);
        return messageSource.getMessage(desc, null, desc, localeValue);
    }

}
