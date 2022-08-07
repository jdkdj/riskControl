package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonNull;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

public class NullParser {
    public static @NotNull JsonNull parse(@NotNull JsonReader json) throws SyntaxException {
        int line = json.line();
        int col = json.column();
        int pos = json.pos();

        json.mustConsume("null");
        return new JsonNull(json.src(), pos, line, col);
    }
}
