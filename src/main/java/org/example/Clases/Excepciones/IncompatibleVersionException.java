package org.example.Clases.Excepciones;

public class IncompatibleVersionException extends RuntimeException {
    public IncompatibleVersionException(String message) {
        super(message);
    }
}
