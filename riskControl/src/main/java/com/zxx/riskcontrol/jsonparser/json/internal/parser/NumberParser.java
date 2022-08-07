package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonNumber;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

public class NumberParser {
    public static @NotNull JsonNumber parse(@NotNull JsonReader json) throws SyntaxException {
        String value = json.parserNumber();
        return new JsonNumber(json.src(), json.pos(), json.line(), json.column(), value);
    }
}
