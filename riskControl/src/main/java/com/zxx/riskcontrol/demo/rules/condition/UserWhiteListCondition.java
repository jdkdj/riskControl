package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.annotation.RuleComponent;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/30 11:36
 */
@RuleComponent(desc = "白名单用户，白名单内的用户跳过监测")
public class UserWhiteListCondition implements Condition {
    final List<String> whiteList;

    public UserWhiteListCondition(List<String> whiteList) {
        if (whiteList == null) {
            this.whiteList = new ArrayList<>();
            return;
        }
        this.whiteList = whiteList;
    }

    @Override
    public boolean match(@NotNull BizContext context) {
        return !whiteList.contains(context.getUser().getUserId());
    }
}
