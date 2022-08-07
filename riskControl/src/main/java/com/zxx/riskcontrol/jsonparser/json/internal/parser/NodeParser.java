package com.zxx.riskcontrol.jsonparser.json.internal.parser;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import com.zxx.riskcontrol.jsonparser.json.utils.CharUtils;

import java.io.SyncFailedException;


public class NodeParser {
    public static JsonNode parse(JsonReader json) throws SyntaxException, SyncFailedException {
        json.consumeWhiteSpaces();
        if (json.isEmpty()) {
            throw SyntaxException.error(json, SyntaxErrorEnum.SOURCE_EMPTY, "json不应为空");
        }
        JsonNode result;
        int ch = json.peek();
        switch (ch) {
            case 'f':
            case 't':
                result = JsonNode.boolNode(BoolParser.parse(json));
                break;
            case 'n':
                result = JsonNode.nullNode(NullParser.parse(json));
                break;
            case '\"':
                result = JsonNode.stringNode(StringParser.parse(json));
                break;
            case '[':
                result = JsonNode.arrayNode(ArrayParser.parse(json));
                break;
            case '{':
                result = JsonNode.objectNode(ObjectParser.parse(json));
                break;
            default:
                if (CharUtils.isDigit0to9(ch) || ch == '-') {
                    result = JsonNode.numberNode(NumberParser.parse(json));
                } else {
                    throw SyntaxException.error(json,SyntaxErrorEnum.UNEXPECTED_CHAR,
                            "非法的json起始字符:" + ch);
                }
        }
        json.consumeWhiteSpaces();
        return result;

    }
}
