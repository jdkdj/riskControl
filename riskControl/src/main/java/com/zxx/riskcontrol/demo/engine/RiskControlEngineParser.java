package com.zxx.riskcontrol.demo.engine;

import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.Condition;
import com.zxx.riskcontrol.demo.rules.action.BatchAction;
import com.zxx.riskcontrol.demo.rules.action.BatchActionParser;
import com.zxx.riskcontrol.demo.rules.condition.MatchAllCondition;
import com.zxx.riskcontrol.demo.rules.condition.MatchAllConditionParser;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import com.zxx.riskcontrol.jsonparser.json.JsonParser;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonArray;
import org.apache.commons.lang3.tuple.Pair;

import java.io.SyncFailedException;
import java.util.ArrayList;
import java.util.List;

public class RiskControlEngineParser {
    public static RiskControlEngine parse(String trigger, String actions) throws SyntaxException, SyncFailedException {
        final JsonNode triggerJsonNode = JsonParser.parse(trigger);
        final MatchAllCondition triggerConditions = new MatchAllConditionParser().parse(triggerJsonNode);

        final JsonNode actionsJsonNode = JsonParser.parse(actions);

        final List<Pair<Condition, Action>> actionStrategyList = new ArrayList<>();
        final JsonArray strategyList = actionsJsonNode.array();

        for (JsonNode strategy : strategyList) {
            final JsonNode ifNode = strategy.mustGet("if");
            final JsonNode thenNode = strategy.mustGet("then");
            final MatchAllCondition matchAllIf = new MatchAllConditionParser().parse(ifNode);
            final BatchAction batchAction = new BatchActionParser().parse(thenNode);

            actionStrategyList.add(Pair.of(matchAllIf, batchAction));
        }

        return new RiskControlEngine(triggerConditions, actionStrategyList);
    }
}
