package com.example.ms.proveedor.exception;


public class DuplicateProveedorException extends RuntimeException {
    public DuplicateProveedorException(String message) {
        super(message);
    }

    public DuplicateProveedorException(String message, Throwable cause) {
        super(message, cause);
    }
}