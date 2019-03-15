package com.server.entity;

import com.server.untils.CommonUtils;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
* @Description: ClientDetails自定义实现类
* @param:
* @return:
* @author: fanjc
* @Date: 2019/3/13
*/
@Data
@SuppressWarnings("unchecked")
public final class JiheOauthClientDetails implements ClientDetails {

    private OAuthClientDetail client;
    private Set<String> scope;

    public JiheOauthClientDetails(OAuthClientDetail client) {
        this.client = client;
    }

    public JiheOauthClientDetails() {
    }

    @Override
    public String getClientId() {
        return client.getClientId();
    }

    @Override
    public Set<String> getResourceIds() {
        return client.getResourceIds()!=null?
                CommonUtils.transformStringToSet(client.getResourceIds(),String.class):null;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public String getClientSecret() {
        return client.getClientSecret();
    }

    @Override
    public boolean isScoped() {
        return true;
    }

    @Override
    public Set<String> getScope() {

        this.scope = client.getScope()!=null?
                CommonUtils.transformStringToSet(client.getScope(),String.class):null;

        return client.getScope()!=null?
                CommonUtils.transformStringToSet(client.getScope(),String.class):null;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return client.getAuthorizedGrantTypes()!=null?
                CommonUtils.transformStringToSet(client.getAuthorizedGrantTypes(),String.class):null;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return client.getWebServerRedirectUri()!=null?
                CommonUtils.transformStringToSet(client.getWebServerRedirectUri(),String.class):null;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return (client.getAuthorities()!=null&&client.getAuthorities().trim().length()>0)?
                AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities()):null;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return client.getAccessTokenValiditySeconds();
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return client.getRefreshTokenValiditySeconds();
    }

    @Override
    public boolean isAutoApprove(String scope) {
        if(null == this.client.getIsAutoApprove()){
            return false;
        }else {
            if (this.client.getIsAutoApprove().equals("true")) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return null;
    }
}
