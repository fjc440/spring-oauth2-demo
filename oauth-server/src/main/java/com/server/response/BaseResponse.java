package com.server.response;

import lombok.Data;


@Data
public  class BaseResponse {

    private int status;
    private String msg;

    protected BaseResponse() {
    }

    protected BaseResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
