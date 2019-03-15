package com.server.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class ListResponse extends BaseResponse {

    private long count;
    private List items;

    protected ListResponse(){

    }

}
