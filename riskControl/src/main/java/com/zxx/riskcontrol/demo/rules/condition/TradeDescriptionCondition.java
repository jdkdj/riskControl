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
@RuleComponent(desc = "交易描述信息")
public class TradeDescriptionCondition implements Condition {
    final List<String> containAny;

    public TradeDescriptionCondition(List<String> containAny) {
        if (containAny == null) {
            this.containAny = new ArrayList<>();
            return;
        }
        this.containAny = containAny;
    }

    @Override
    public boolean match(@NotNull BizContext context) {
        for (String s : containAny) {
            if (context.getCardTrade().getDesp().contains(s)) {
                return true;
            }
        }
        return false;
    }
}
