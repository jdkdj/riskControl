package com.zxx.riskcontrol.demo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDO {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "用户id", example = "pg_345464")
    private String userId;

    @ApiModelProperty(value = "昵称", example = "JACK MA")
    private String nickname;

    @ApiModelProperty(value = "用户密码", example = "123456")
    private String password;

    @ApiModelProperty(value = "密码加密盐", example = "abc14a")
    private String salt;

    @ApiModelProperty(value = "手机号", example = "15072345678")
    private String mobile;

    @ApiModelProperty(value = "手机归属国家", example = "CN")
    private String country;


    @ApiModelProperty(value = "微信账号", example = "wechat账号")
    private String wechat;

    @ApiModelProperty(value = "邮箱", example = "123456@qq.com")
    private String mail;

    @ApiModelProperty(value = "账户id", example = "ac_1234bac")
    private String accountId;

    @ApiModelProperty(value = "是否拥有saas父账户(租户)权限", example = "false")
    private Boolean saasTenant;

    @ApiModelProperty(value = "父账户id", example = "u_abc123")
    private String parentId;

    private Timestamp gmtCreate;

    @ApiModelProperty(value = "更新时间", example = "1597847779000")
    private Timestamp gmtModified;
}
