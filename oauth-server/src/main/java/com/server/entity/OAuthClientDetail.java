package com.server.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* @Description: oauthClientDetails实体对象
* @param:
* @return:
* @author: fanjc
* @Date: 2019/3/13
*/
@Data
@TableName("oauth_client_details")
public class OAuthClientDetail {

    @TableId
    private String id;
    @TableField("clientId")
    @NotNull
    private String clientId;
    @TableField("resourceIds")
    private String resourceIds;
    @TableField("clientSecret")
    @NotNull
    private String clientSecret;
    @TableField("scope")
    private String scope;
    @TableField("authorizedGrantTypes")
    @NotNull
    private String authorizedGrantTypes;
    @TableField("webServerRedirectUri")
    @NotNull
    private String webServerRedirectUri;
    @TableField("authorities")
    private String authorities;
    @TableField("autoapprove")
    private String isAutoApprove;
    @TableField("accessTokenValidity")
    @NotNull
    private Integer accessTokenValiditySeconds;
    @TableField("refreshTokenValidity")
    @NotNull
    private Integer refreshTokenValiditySeconds;

}
