package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonString;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

public class StringParser {
    public static @NotNull JsonString parse(@NotNull JsonReader json) throws SyntaxException {
        int pos = json.pos();
        int line = json.line();
        int col = json.column();
        String value = json.parseString();
        return new JsonString(json.src(), pos, line, col, value);
    }
}
