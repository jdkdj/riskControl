package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.ConditionParser;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/30 11:36
 */
public class UserWhiteListConditionParser implements ConditionParser<UserWhiteListCondition> {
    @Override
    public @NotNull UserWhiteListCondition parse(@NotNull JsonNode node) throws SyntaxException {
        final List<String> whitelist = node.mustGetStrList("whitelist");
        return new UserWhiteListCondition(whitelist);
    }
}
