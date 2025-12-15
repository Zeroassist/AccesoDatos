package org.example.Clases.Excepciones;

import java.io.IOException;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException() {

    }

    public DataAccessException(IOException e) {
    }
}
