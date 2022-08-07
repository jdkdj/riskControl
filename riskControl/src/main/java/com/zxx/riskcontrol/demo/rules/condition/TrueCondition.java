package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;


public class TrueCondition implements Condition {
    public TrueCondition() {
    }

    @Override
    public boolean match(@NotNull BizContext context) {
        return true;
    }
}
