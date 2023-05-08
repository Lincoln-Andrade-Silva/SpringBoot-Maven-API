package com.api.application.core.utils.exeption;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Data
@NoArgsConstructor
public class ApplicationErrorResponse {
    private static final String COM_API = "com.api";

    private int status;
    private String title;
    private String message;
    private String className;
    private String method;
    private String line;
    private String path;
    private String input;
    private LocalDateTime dateTime;

    public static ApplicationErrorResponse setErrorResponse(Exception exception) {
        ApplicationErrorResponse errorResponse = new ApplicationErrorResponse();
        String[] exceptionName = exception.getClass().getName().split("\\.");

        StackTraceElement stackTraceElement = null;
        if (Objects.nonNull(exception.getStackTrace()))
            stackTraceElement = getStackTraceElement(exception.getStackTrace());

        if (exception instanceof ApplicationBusinessException) {
            errorResponse.setTitle(((ApplicationBusinessException) exception).getTitle());
            errorResponse.setStatus(((ApplicationBusinessException) exception).getHttpStatusCode());
            errorResponse.setInput(((ApplicationBusinessException) exception).getInput());
        } else {
            errorResponse.setTitle(exceptionName[exceptionName.length - 1]);
            errorResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        if (Objects.nonNull(stackTraceElement)) {
            errorResponse.setClassName(stackTraceElement.getFileName());
            errorResponse.setLine(String.valueOf(stackTraceElement.getLineNumber()));
            errorResponse.setMethod(stackTraceElement.getMethodName());
            errorResponse.setPath(stackTraceElement.getClassName());
        }
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setDateTime(LocalDateTime.now());

        return errorResponse;
    }

    private static StackTraceElement getStackTraceElement(StackTraceElement[] exception) {
        return (StackTraceElement) Arrays.stream(exception).filter(x
                -> x.getClassName().contains(COM_API)).toArray()[0];
    }
}
