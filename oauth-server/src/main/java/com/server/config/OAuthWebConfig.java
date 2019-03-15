package com.server.config;


import com.server.dto.UserServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/** 
* @Description: webConfig
* @param:
* @return: 
* @author: fanjc
* @Date: 2019/3/11 
*/ 
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuthWebConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {

        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
        http.requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll();
        //支持表单登录
        http.formLogin();
    }

    @Autowired
    UserServiceDetail userServiceDetail;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceDetail).passwordEncoder(passwordEncoder());
    }

}
