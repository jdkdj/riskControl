package com.zxx.riskcontrol.demo;

import com.zxx.riskcontrol.demo.factory.RuleFactory;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonArray;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonString;

import java.util.ArrayList;
import java.util.List;

public class ParserUtil {
    public static Condition parseCondition(JsonNode node) throws SyntaxException {
        final JsonString conditionName = node.mustGet("condition").string();
        final ConditionParser<?> parser = RuleFactory.getConditionParser(conditionName.value());
        if (parser == null) {
            throw SyntaxException.error(conditionName, SyntaxErrorEnum.UNKNOWN_TYPE,
                    "未知的Condition" + "类型:" + conditionName.value() + ":所有已知类型:" + RuleFactory.getConditionNames());
        }
        final JsonNode params = node.mustGet("params");
        return parser.parse(params);
    }

    public static List<Condition> parseConditionList(JsonNode node) throws SyntaxException {
        final JsonArray conditionList = node.array();
        List<Condition> collect = new ArrayList<>();
        for (JsonNode item : conditionList) {
            final Condition condition = parseCondition(item);
            collect.add(condition);
        }
        return collect;
    }

    public static Action parseAction(JsonNode node) throws SyntaxException {
        final JsonString action = node.mustGet("action").string();
        final ActionParser<?> parser = RuleFactory.getActionParser(action.value());
        if (parser == null) {
            throw SyntaxException.error(action, SyntaxErrorEnum.UNKNOWN_TYPE,
                    "未知的Action" + "类型:" + action.value() + ":所有已知类型:" + RuleFactory.getActionNames());
        }
        final JsonNode params = node.mustGet("params");
        return parser.parse(params);
    }

    public static List<Action> parseActionList(JsonNode node) throws SyntaxException {
        final JsonArray conditionList = node.array();
        List<Action> collect = new ArrayList<>();
        for (JsonNode item : conditionList) {
            final Action condition = parseAction(item);
            collect.add(condition);
        }
        return collect;
    }
}
