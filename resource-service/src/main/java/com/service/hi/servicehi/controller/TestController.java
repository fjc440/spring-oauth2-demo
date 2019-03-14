package com.service.hi.servicehi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:Testcontroller
 * @Description:
 * @author:fjc
 * @date 2017-08-11
 */
@RestController
@RequestMapping(value="/resource/")
public class TestController{


    @ResponseBody
    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getTest(){
        return  "11111111";
    }

}
