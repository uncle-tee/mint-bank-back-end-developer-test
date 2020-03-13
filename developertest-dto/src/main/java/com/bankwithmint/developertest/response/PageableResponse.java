package com.bankwithmint.developertest.response;

public class PageableResponse<T> {
    public Boolean success = false;
    public T payload;
    public long start;
    public long limit;
    public long size;

    public PageableResponse(Boolean success, T payload, long start, long limit, long size) {
        this.success = success;
        this.payload = payload;
        this.start = start;
        this.limit = limit;
        this.size = size;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
