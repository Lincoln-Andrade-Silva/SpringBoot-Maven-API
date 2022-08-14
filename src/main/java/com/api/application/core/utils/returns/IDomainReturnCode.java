package com.api.application.core.utils.returns;

import org.springframework.context.MessageSource;

import java.io.Serializable;

public interface IDomainReturnCode extends Serializable {
    String getTranslatedDescription(MessageSource messageSource, String locale);
}
