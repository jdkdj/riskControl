package com.zxx.riskcontrol.demo;

import com.zxx.riskcontrol.demo.context.BizContext;
import org.jetbrains.annotations.NotNull;

/**
 * 检测context是否满足某个条件，例如:
 * 1. 交易明细包含...
 * 2. 用户白名单
 * 3. 用户拒付数达到..
 * 等等
 *
 */
public interface Condition {
    /**
     * 判断是否符合指定的条件
     *
     * @param context 执行过程中的上下文（可视为全局变量保存的地方）
     * @return 返回是否满足某条件
     */
    boolean match(@NotNull BizContext context);
}
