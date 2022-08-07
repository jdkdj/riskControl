package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonBool;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

public class BoolParser {
    public static @NotNull JsonBool parse(JsonReader json) throws SyntaxException {
        int line = json.line();
        int col = json.column();
        int pos = json.pos();

        if (json.mayParseToken("true")) {
            return new JsonBool(json.src(), pos, line, col, true);
        } else if (json.mayParseToken("false")) {
            return new JsonBool(json.src(), pos, line, col, false);
        } else {
            throw SyntaxException.error(json, SyntaxErrorEnum.UNEXPECTED_TOKEN, "期望token: true|false");
        }
    }
}
