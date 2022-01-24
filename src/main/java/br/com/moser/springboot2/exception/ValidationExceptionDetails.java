package br.com.moser.springboot2.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

/**
 * @author Juliano Moser
 */
@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExceptionDetails {

    private final String fields;
    private final String fieldsMessage;
}
