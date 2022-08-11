package com.api.application.utils.returns;

import org.springframework.context.MessageSource;

import java.io.Serializable;

public interface IDomainReturnCode extends Serializable {
    String getTranslatedDescription(MessageSource messageSource, String locale);
}
