package com.zxx.riskcontrol.jsonparser.json;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.internal.parser.NodeParser;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;

import java.io.SyncFailedException;

public class JsonParser {
    public static JsonNode parse(@NotNull final String str) throws SyntaxException, SyncFailedException {
      return parse(new JsonReader(str));
    }
    public static JsonNode parse(JsonReader json) throws SyntaxException, SyncFailedException {
        final JsonNode node = NodeParser.parse(json);
        if (json.isNotEmpty()) {
            throw SyntaxException.error(json, SyntaxErrorEnum.ILLEGAL_ENDING, "json结尾有多余字符");
        }
        return node;
    }
}
