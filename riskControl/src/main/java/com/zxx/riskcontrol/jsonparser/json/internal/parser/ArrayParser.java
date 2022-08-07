package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonArray;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.SyncFailedException;

public class ArrayParser {
    public static @NotNull JsonArray parse(@NotNull JsonReader json) throws SyntaxException, SyncFailedException {
        final JsonArray array = new JsonArray(json.src(), json.pos(), json.line(), json.column());
        json.mustConsume("[");
        json.consumeWhiteSpaces();
        if (json.mayParseToken("]")) {
            return array;
        }
        while (true) {
            final JsonNode value = NodeParser.parse(json);
            array.add(value);
            if (json.mayParseToken("]")) {
                return array;
            }
            json.mustConsume(",");
        }
    }
}
