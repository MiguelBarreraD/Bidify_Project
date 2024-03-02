package edu.ieti.bidify.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/*
 * Excepción que se lanza cuando se intenta realizar una operación con un atributo inválido.
 */

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AttributeException extends Exception{
    public AttributeException(String message) {
        super(message);
    }
    
}
