package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.annotation.RuleComponent;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/30 11:36
 */
@RuleComponent(desc = "用户匹配，查看用户是否在匹配条件内")
public class MatchUserCondition implements Condition {
    final List<String> matchAny;

    public MatchUserCondition(List<String> matchAny) {
        this.matchAny = matchAny;
    }

    @Override
    public boolean match(@NotNull BizContext context) {
        if (matchAny.contains(context.getUser().getUserId())) {
            return true;
        }
        return false;
    }
}
