package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.ConditionParser;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/30 11:36
 */
public class TrueConditionParser implements ConditionParser<TrueCondition> {
    @Override
    public @NotNull TrueCondition parse(@NotNull JsonNode node) throws SyntaxException {
        return new TrueCondition();
    }
}
