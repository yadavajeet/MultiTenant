package com.ongraph.response;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class Response {

    boolean success = true;
    HttpStatus status = HttpStatus.OK;
    String message ="";
    Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
