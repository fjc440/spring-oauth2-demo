package com.server.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
* @Description: 自定义错误类
* @param:
* @return:
* @author: fanjc
* @Date: 2019/3/13
*/
@JsonSerialize(using = JiheOauthExceptionJacksonSerializer.class)
public class JiheOauth2Exception extends OAuth2Exception {



    public JiheOauth2Exception(String msg, Throwable t) {
        super(msg, t);

    }

    public JiheOauth2Exception(String msg) {
        super(msg);

    }


}
