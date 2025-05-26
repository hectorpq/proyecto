package com.example.ms.proveedor.exception;

public class ProveedorNotFoundException extends RuntimeException {
    public ProveedorNotFoundException(String message) {
        super(message);
    }

    public ProveedorNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}