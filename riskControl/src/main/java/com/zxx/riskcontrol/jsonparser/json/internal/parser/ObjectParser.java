package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonObject;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonString;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.SyncFailedException;

public class ObjectParser {
    public static @NotNull JsonObject parse(@NotNull JsonReader json) throws SyntaxException, SyncFailedException {
        final JsonObject result = new JsonObject(json.src(), json.pos(), json.line(), json.column());

        json.mustConsume("{");
        json.consumeWhiteSpaces();
        if (json.mayParseToken("}")) {
            return result;
        }
        while (true) {
            json.consumeWhiteSpaces();
            final JsonString key = StringParser.parse(json);
            json.consumeWhiteSpaces();
            json.mustConsume(":");
            final JsonNode value = NodeParser.parse(json);

            result.put(key, value);

            if (json.mayParseToken("}")) {
                return result;
            }
            json.mustConsume(",");
        }
    }
}
