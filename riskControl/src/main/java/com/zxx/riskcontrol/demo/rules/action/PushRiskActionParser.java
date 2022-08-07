package com.zxx.riskcontrol.demo.rules.action;

import com.zxx.riskcontrol.demo.ActionParser;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/28 17:32
 */
public class PushRiskActionParser implements ActionParser<PushRiskAction> {
    @Override
    public final @NotNull PushRiskAction parse(@NotNull JsonNode params) throws SyntaxException {
        final String message = params.mustGetString("message");
        return new PushRiskAction(message);
    }
}
