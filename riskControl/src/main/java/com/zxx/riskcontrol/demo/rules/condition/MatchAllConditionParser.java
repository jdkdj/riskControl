package com.zxx.riskcontrol.demo.rules.condition;

import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.ConditionParser;
import com.zxx.riskcontrol.demo.ParserUtil;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MatchAllConditionParser implements ConditionParser<MatchAllCondition> {
    @Override
    public @NotNull MatchAllCondition parse(@NotNull JsonNode node) throws SyntaxException {
        final List<Condition> conditions = ParserUtil.parseConditionList(node);
        return new MatchAllCondition(conditions);
    }
}
