package com.server.response;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SimpleResponse extends BaseResponse {

    private Object item;

    protected SimpleResponse() {
    }

    protected SimpleResponse(int status, String msg, Object item) {
        super(status, msg);
        this.item = item;
    }


}
