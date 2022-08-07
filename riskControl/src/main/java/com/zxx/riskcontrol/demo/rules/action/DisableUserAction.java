package com.zxx.riskcontrol.demo.rules.action;

import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.annotation.RuleComponent;
import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Optional;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/28 17:30
 */
@RuleComponent(desc = "关闭用户action")
public class DisableUserAction implements Action {
    @Unmodifiable
    final String reason;

    public DisableUserAction(@Nullable String message) {
        this.reason = Optional.ofNullable(message).orElse("风控禁用用户默认消息...");
    }

    @Override
    public void execute(@NotNull BizContext context) {
        final String userId = context.getUser().getUserId();
        System.out.println("关闭用户... 原因是: " + reason);
    }
}
