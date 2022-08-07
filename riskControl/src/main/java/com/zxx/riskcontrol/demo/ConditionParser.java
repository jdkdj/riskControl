package com.zxx.riskcontrol.demo;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

public interface ConditionParser<T extends Condition> {
    /**
     * 解析json，生成脚本执行组件
     *
     * @param node
     * @return
     * @throws SyntaxException
     */
    @NotNull T parse(@NotNull JsonNode node) throws SyntaxException;
}
