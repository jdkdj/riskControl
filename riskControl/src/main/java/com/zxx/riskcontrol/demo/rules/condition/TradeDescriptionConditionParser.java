package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.ConditionParser;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TradeDescriptionConditionParser implements ConditionParser<TradeDescriptionCondition> {
    @Override
    public @NotNull TradeDescriptionCondition parse(@NotNull JsonNode node) throws SyntaxException {
        final List<String> whitelist = node.mustGetStrList("containAny");
        return new TradeDescriptionCondition(whitelist);
    }
}
