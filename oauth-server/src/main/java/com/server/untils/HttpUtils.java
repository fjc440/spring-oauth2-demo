package com.server.untils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.response.BaseResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @Description: http工具类
* @param:
* @return:
* @author: fanjc
* @Date: 2019/3/13
*/
public class HttpUtils {

    public static void writerError(BaseResponse bs, HttpServletResponse response) throws IOException {
        response.setContentType("application/json,charset=utf-8");
        response.setStatus(bs.getStatus());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(),bs);
    }

}
