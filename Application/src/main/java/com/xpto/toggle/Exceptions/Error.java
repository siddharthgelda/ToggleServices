package com.xpto.toggle.Exceptions;

public class Error {
    int id;
    String code;
    String details;

    public Error() {
    }

    public Error(int id, String code, String details) {
        this.id = id;
        this.code = code;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
