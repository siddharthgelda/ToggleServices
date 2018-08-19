package com.xpto.toggle.Exceptions;

public class BedRequestExcpetion extends RuntimeException {
    Error error;

    public BedRequestExcpetion(Error error) {
        super();
        this.error = error;
    }
}

