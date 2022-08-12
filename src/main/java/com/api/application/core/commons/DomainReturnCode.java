package com.api.application.core.commons;

import com.api.application.utils.returns.IDomainReturnCode;
import com.api.application.utils.returns.InternationalizationUtil;
import org.springframework.context.MessageSource;

public enum DomainReturnCode implements IDomainReturnCode {

    /*
        Operations
     */
    SUCCESSFUL_OPERATION("Operation concluded with success"),
    ERROR_OPERATION("An error occurred during the operation"),

    /*
      Student
   */
    STUDENT_NOT_FOUND("Student not found in database"),
    NAME_IS_NULL("Name field is a mandatory field"),
    LAST_NAME_IS_NULL("Last Name field is a mandatory field"),
    CLASSROOM_ID_IS_NULL("Classroom id field is a mandatory field"),
    BIRTH_DATE_IS_NULL("Birthdate field is a mandatory field"),
    CLASSROOM_CODE_IS_NULL("Classroom field is a mandatory field");

     /*
      Classroom
   */

    private final String desc;
    DomainReturnCode(String value) {
        desc = value;
    }

    public String getDesc(){
        return desc;
    }

    @Override
    public String getTranslatedDescription(MessageSource messageSource, String locale) {
        return InternationalizationUtil.getMessage(messageSource, getDesc(), locale);
    }

}
