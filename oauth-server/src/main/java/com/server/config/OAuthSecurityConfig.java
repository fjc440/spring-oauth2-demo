package com.server.config;


import com.server.filter.JiheBasicAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.sql.DataSource;


/** 
* @Description: SecurityConfig
* @param:
* @return: 
* @author: fanjc
* @Date: 2019/3/11 
*/ 
@Configuration
@EnableResourceServer
@EnableAuthorizationServer
public class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    @Qualifier("authenticationManagerBean")
    AuthenticationManager authenticationManager;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private WebResponseExceptionTranslator JiheOAuth2WebResponseExceptionTranslator;


    static final Logger logger = LoggerFactory.getLogger(OAuthSecurityConfig.class);


    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
    * @Description: JWT 密钥非对称加密
    * @param:
    * @return:
    * @author: fanjc
    * @Date: 2019/3/12
    */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("test-jwt.jks"), "test123".toCharArray());
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("test-jwt"));
        return converter;
    }

    /**
    * @Description: 声明JDBCClientDetails实现
    * @param:
    * @return:
    * @author: fanjc
    * @Date: 2019/3/13
    */
    @Bean
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }


    /**
    * @Description: 注入clientDetailsService
    * @param:
    * @return:
    * @author: fanjc
    * @Date: 2019/3/13
    */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String finalSecret = "++++++" + new BCryptPasswordEncoder().encode("123456");
        System.out.println(finalSecret);
        clients.withClientDetails(clientDetailsService);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .tokenEnhancer(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)
                //支持GET方法
                .allowedTokenEndpointRequestMethods(HttpMethod.POST,HttpMethod.GET)
                //自定义错误接口实现类
                .exceptionTranslator(JiheOAuth2WebResponseExceptionTranslator);

        // 配置tokenServices参数
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
//        tokenServices.setAccessTokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(30)); // 30天
        endpoints.tokenServices(tokenServices);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许表单认证
        security.allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                //client_secret用BCrypt方式加密
                .passwordEncoder(new BCryptPasswordEncoder())
                .addTokenEndpointAuthenticationFilter(new JiheBasicAuthenticationFilter());
;
    }

}
