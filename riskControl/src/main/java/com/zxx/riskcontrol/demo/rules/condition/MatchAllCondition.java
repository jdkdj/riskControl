package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

public class MatchAllCondition implements Condition {
    @Unmodifiable
    final List<Condition> conditionList;

    public MatchAllCondition(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    @Override
    public boolean match(@NotNull BizContext context) {
        for (Condition condition : conditionList) {
            if (!condition.match(context)) {
                return false;
            }
        }
        return true;
    }
}
