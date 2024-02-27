package edu.eci.arsw.bidify.Exceptions;

public class SalaSubastaException extends Exception{
    public static final String SALASUBASTA_ALREADY_EXITS = "La sala ya existe";
    public static final String SALASUBASTA_NOT_FOUND = "La sala ya existe";
    public static final String SALASUBASTA_IS_NOT_IN_REPOSITORY= "La sala no se encuentra en el repositorio";
    public SalaSubastaException(String message) {
        super(message);
    }
}
