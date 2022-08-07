package com.zxx.riskcontrol.demo;

import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;

public interface Action {
    /**
     * 执行动作
     *
     * @param context 执行过程中的上下文（可视为全局变量保存的地方）
     */
    void execute(@NotNull BizContext context);
}
