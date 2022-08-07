package com.zxx.riskcontrol.demo.rules.action;

import com.zxx.riskcontrol.demo.ActionParser;
import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

/**
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/28 17:32
 */
public class DisableUserActionParser implements ActionParser<DisableUserAction> {
    @Override
    public final @NotNull DisableUserAction parse(@NotNull JsonNode params) throws SyntaxException {
        final String reason = params.mayGetString("reason").orElse(null);
        return new DisableUserAction(reason);
    }
}
