package com.xpto.toggle.Exceptions;

public class BedRequestExcpetion extends RuntimeException {
    Error error;

    public BedRequestExcpetion(Error error) {
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

