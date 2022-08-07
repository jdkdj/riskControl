package com.zxx.riskcontrol.demo.rules.action;


import com.zxx.riskcontrol.demo.Action;
import com.zxx.riskcontrol.demo.ActionParser;
import com.zxx.riskcontrol.demo.ParserUtil;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class BatchActionParser implements ActionParser<BatchAction> {

    @Override
    public @NotNull BatchAction parse(@NotNull JsonNode params) throws SyntaxException {
        final List<Action> actions = ParserUtil.parseActionList(params);
        return new BatchAction(actions);
    }
}
