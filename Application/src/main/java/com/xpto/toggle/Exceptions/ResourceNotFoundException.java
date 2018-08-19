package com.xpto.toggle.Exceptions;

public class ResourceNotFoundException extends RuntimeException {
    Error error;

    public ResourceNotFoundException(Error error) {
        super();
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}
