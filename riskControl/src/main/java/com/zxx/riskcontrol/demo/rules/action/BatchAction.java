package com.zxx.riskcontrol.demo.rules.action;

import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.annotation.RuleComponent;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.List;

@RuleComponent(desc = "Action列表: 一次执行脚本列表各个Action")
public class BatchAction implements Action {
    @Unmodifiable
    final List<Action> actions;

    public BatchAction(List<Action> actions) {
        this.actions = actions;
    }


    @Override
    public void execute(@NotNull BizContext context) {
        for (Action action : actions) {
            action.execute(context);
        }
    }
}
