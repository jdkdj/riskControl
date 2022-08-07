package com.zxx.riskcontrol.demo.context;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * session
 *
 * @author zhangsen@gmail.com
 * @since 2020/08/17 19:41
 */
@Data
public class CardAuthDO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "卡授权记录id", example = "ca_sdasdf1")
    private String cardAuthId;

    @ApiModelProperty(value = "外部供应商的卡授权id(如果有)", example = "auth_sdasd")
    private String vendorAuthId;

    @ApiModelProperty(value = "供应商的卡授权额外id(如果有)", example = "11281555085423100000000")
    private String vendorAuthExtraId;

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

    @ApiModelProperty(value = "授权描述", example = "描述信息")
    private String desp;

    @ApiModelProperty(value = "金额(统一处理 消费为负数，退款为正数)", example = "消费金额")
    private Long amount;

    @ApiModelProperty(value = "消费币种", example = "USD")
    private String unit;

    @ApiModelProperty(value = "被拒绝授权等原因", example = "balance not enough")
    private String reason;


    @ApiModelProperty(value = "交易成功后的交易id", example = "ctd_sdas")
    private String cardTradeId;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "授权时间", example = "1597847779")
    private Timestamp authAt;

    @ApiModelProperty(value = "创建时间", example = "1597847779")
    private Timestamp gmtCreate;

    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间", example = "1597847779")
    private Timestamp gmtModified;

    private Boolean labelStatus;
}
