package com.zxx.riskcontrol.demo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class CardTradeDO implements Serializable {
    private static final long serialVersionUID = 1L;


    @ApiModelProperty(value = "卡交易记录id", example = "ct_sdasdf1")
    private String cardTradeId;

    @ApiModelProperty(value = "卡授权记录id", example = "ca_sdasdf1")
    private String cardAuthId;

    @ApiModelProperty(value = "供应商的卡交易id(如果有)", example = "trade_sdasd")
    private String vendorTradeId;

    @ApiModelProperty(value = "供应商的卡交易额外id(如果有)")
    private String vendorTradeExtraId;

    @ApiModelProperty(value = "用户id", example = "u_sdasdf1")
    private String userId;

    @ApiModelProperty(value = "父账户id", example = "u_sdasdf2")
    private String parentId;

    @ApiModelProperty(value = "卡id", example = "c_sdasdf1")
    private String cardId;

    @ApiModelProperty(value = "卡所属卡段id", example = "v_sdasdf1")
    private String cardVendorId;

    @ApiModelProperty(value = "卡序列号", example = "0115998859851505EC3EE5")
    private String cardSeqNo;

    @ApiModelProperty(value = "卡号", example = "4000009990000104")
    private String cardNumber;

    @ApiModelProperty(value = "交易描述", example = "描述信息")
    private String desp;

    @ApiModelProperty(value = "消费金额", example = "消费金额")
    private Long amount;

    @ApiModelProperty(value = "卡消费后余额", example = "100000")
    private Long balance;

    @ApiModelProperty(value = "卡消费后余额", example = "100000")
    private Long lastBalance;

    @ApiModelProperty(value = "消费币种", example = "USD")
    private String unit;

    @ApiModelProperty(value = "交易时间", example = "1597847779")
    private Timestamp tradeAt;

    @ApiModelProperty(value = "授权时间", example = "1597847779")
    private Timestamp authAt;

    @ApiModelProperty(value = "创建时间", example = "1597847779")
    private Timestamp gmtCreate;

    @ApiModelProperty(value = "更新时间", example = "1597847779")
    private Timestamp gmtModified;

    @ApiModelProperty(value = "标签状态 false 为分配 true 已分配", example = "1597847779")
    private Boolean labelStatus;
}
