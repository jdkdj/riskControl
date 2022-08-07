package com.zxx.riskcontrol.demo.rules.action;

import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.annotation.RuleComponent;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/28 17:30
 */
@RuleComponent(desc = "推送到风控工单")
public class PushRiskAction implements Action {
    @Unmodifiable
    final String message;

    public PushRiskAction(@NotNull String message) {
        this.message = message;
    }

    @Override
    public void execute(@NotNull BizContext context) {
        final String userId = context.getUser().getUserId();
        System.out.println("推送用户 " + userId + " 风控工单... 消息: " + message);
    }
}
