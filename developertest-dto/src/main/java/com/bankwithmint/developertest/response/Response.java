package com.bankwithmint.developertest.response;

public class Response<D> {
    public Boolean success;
    public D payload;

    public Response(Boolean success, D payload) {
        this.success = success;
        this.payload = payload;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public D getPayload() {
        return payload;
    }

    public void setPayload(D payload) {
        this.payload = payload;
    }
}
