package com.service.hi.servicehi.controller;

import com.service.hi.servicehi.dto.UserService;
import com.service.hi.servicehi.entity.User;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class TestController {

    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private UserService userService;
    
    
    /** 
    * @Description: 输出认证信息
    * @param: 
    * @return: 
    * @author: fanjc
    * @Date: 2019/3/15 
    */ 
    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        logger.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        logger.info(oAuth2Authentication.toString());
        logger.info("principal.toString() " + principal.toString());
        logger.info("principal.getName() " + principal.getName());
        logger.info("authentication: " + authentication.getAuthorities().toString());

        return oAuth2Authentication;
    }

    /** 
    * @Description: 手动注册用户
    * @param: 
    * @return: 
    * @author: fanjc
    * @Date: 2019/3/15 
    */ 
    @RequestMapping(value = "/registry", method = RequestMethod.POST)
    public User createUser(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)) {
            return userService.create(username, password);
        }

        return null;
    }

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('ROLE_OAUTH2')")
    public String getTest() {
        return "11111111111111";
    }

}
