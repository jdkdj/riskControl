package com.zxx.riskcontrol.jsonparser.json;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.ast.*;
import com.zxx.riskcontrol.jsonparser.json.internal.constant.JsonType;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * json ast node
 *
 * <p>专用的json node，目的是为了方便实现json各种定制化操作
 * <p>设计主要考虑方便定制api和可读性，而不关注性能。
 * <p>但性能足够，甚至部分场景性能超过jackson(可能原因是不需要考虑通用性，逻辑简单), 参见BenchmarkTest
 *
 * @since 2022/08/2
 */
public class JsonNode {
    final private JsonType type;
    final private JsonBase node;

    public JsonNode(@NotNull JsonType type, @NotNull JsonBase node) {
        this.type = type;
        this.node = node;
    }

    static public JsonNode boolNode(@NotNull JsonBool node) {
        return new JsonNode(JsonType.BOOLEAN, node);
    }

    static public JsonNode nullNode(@NotNull JsonNull node) {
        return new JsonNode(JsonType.NULL, node);
    }

    static public JsonNode numberNode(@NotNull JsonNumber node) {
        return new JsonNode(JsonType.NUMBER, node);
    }

    static public JsonNode stringNode(@NotNull JsonString node) {
        return new JsonNode(JsonType.STRING, node);
    }

    static public JsonNode arrayNode(@NotNull JsonArray node) {
        return new JsonNode(JsonType.ARRAY, node);
    }

    static public JsonNode objectNode(@NotNull JsonObject node) {
        return new JsonNode(JsonType.OBJECT, node);
    }

    public JsonBase getNode() {
        return node;
    }

    @Override
    public String toString() {
        return "JsonNode{" +
                "type=" + type +
                ", node=" + node +
                '}';
    }

    public boolean isNull() {
        return type.isNull();
    }

    public boolean isBool() {
        return type.isBool();
    }

    public boolean isNumber() {
        return type.isNumber();
    }

    public boolean isString() {
        return type.isString();
    }

    public boolean isObject() {
        return type.isObject();
    }

    public boolean isArray() {
        return type.isArray();
    }

    public @NotNull JsonBool bool() throws SyntaxException {
        if (!type.isBool()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE,
                    "期望:" + JsonType.BOOLEAN.str() + "实际:" + type.str());
        }
        return (JsonBool) node;
    }

    public @NotNull JsonString string() throws SyntaxException {
        if (!type.isString()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE,
                    "期望:" + JsonType.STRING.str() + "实际:" + type.str());
        }
        return (JsonString) node;
    }

    public @NotNull JsonNumber number() throws SyntaxException {
        if (!type.isNumber()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE,
                    "期望:" + JsonType.NUMBER.str() + "实际:" + type.str());
        }
        return (JsonNumber) node;
    }

    public @NotNull JsonObject object() throws SyntaxException {
        if (!type.isObject()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE,
                    "期望:" + JsonType.OBJECT.str() + "实际:" + type.str());
        }
        return (JsonObject) node;
    }

    public @NotNull JsonArray array() throws SyntaxException {
        if (!type.isArray()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE,
                    "期望:" + JsonType.ARRAY.str() + "实际:" + type.str());
        }
        return (JsonArray) node;
    }

    public int count() throws SyntaxException {
        if (type.isArray()) {
            return this.array().size();
        }
        if (type.isObject()) {
            return this.object().size();
        }
        throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "count函数仅适用于Array或Object");
    }

    public @NotNull Optional<JsonNode> mayGet(@NotNull final String key) throws SyntaxException {
        if (!type.isObject()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "mayGet函数仅适用于Object");
        }
        return Optional.ofNullable(this.object().get(key));
    }

    public @NotNull JsonNode mustGet(@NotNull final String key) throws SyntaxException {
        if (!type.isObject()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "mustGet函数仅适用于Object");
        }
        return this.object().mustGet(key);
    }

    public int mustGetInt(@NotNull final String key) throws SyntaxException {
        return this.mustGet(key).toInt();
    }

    public @Nullable Optional<Integer> mayGetInt(@NotNull final String key) throws SyntaxException {
        final JsonNode jsonNode = this.mayGet(key).orElse(null);
        if (jsonNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(jsonNode.toInt());
        }
    }

    public @NotNull List<Integer> mustGetIntList(@NotNull final String key) throws SyntaxException {
        return this.mustGet(key).toIntList();
    }

    public @NotNull List<String> mustGetStrList(@NotNull final String key) throws SyntaxException {
        return this.mustGet(key).toStrList();
    }

    public @NotNull Optional<List<Integer>> mayGetIntList(@NotNull final String key) throws SyntaxException {
        final JsonNode jsonNode = this.mayGet(key).orElse(null);
        if (jsonNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(jsonNode.toIntList());
        }
    }

    public long mustGetLong(@NotNull final String key) throws SyntaxException {
        return this.mustGet(key).toLong();
    }

    public @NotNull Optional<Long> mayGetLong(@NotNull final String key) throws SyntaxException {
        final JsonNode jsonNode = this.mayGet(key).orElse(null);
        if (jsonNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(jsonNode.toLong());
        }
    }

    public @NotNull String mustGetString(@NotNull final String key) throws SyntaxException {
        return this.mustGet(key).string().value();
    }

    public @NotNull Optional<String> mayGetString(@NotNull final String key) throws SyntaxException {
        final JsonNode jsonNode = this.mayGet(key).orElse(null);
        if (jsonNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(jsonNode.string().value());
        }
    }

    public @NotNull Date mustGetDate(@NotNull final String key) throws SyntaxException {
        return this.mustGet(key).toDate();
    }

    public @NotNull Date mustGetDate(@NotNull final String key, @NotNull final String format) throws SyntaxException {
        return this.mustGet(key).toDate(format);
    }

    public @NotNull Optional<Date> mayGetDate(@NotNull final String key) throws SyntaxException {
        final JsonNode jsonNode = this.mayGet(key).orElse(null);
        if (jsonNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(jsonNode.toDate());
        }
    }

    public @NotNull Optional<Date> mayGetDate(@NotNull final String key, @NotNull final String format) throws SyntaxException {
        final JsonNode jsonNode = this.mayGet(key).orElse(null);
        if (jsonNode == null) {
            return Optional.empty();
        } else {
            return Optional.of(jsonNode.toDate(format));
        }
    }

    public @NotNull JsonNode index(int idx) throws SyntaxException {
        if (!type.isArray()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "index函数仅适用于Array");
        }

        return this.array().index(idx);
    }

    public int toInt() throws SyntaxException {
        if (!type.isNumber()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "toInt函数仅适用于Number Node");
        }
        try {
            return Integer.parseInt(this.number().value());
        } catch (NumberFormatException e) {
            throw SyntaxException.error(node, SyntaxErrorEnum.PARSE_INTEGER_FAILED, "toInt解析失败, e:" + e.getMessage());
        }
    }

    public @NotNull List<Integer> toIntList() throws SyntaxException {
        if (!type.isArray()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "toIntList函数仅适用于Array Node");
        }
        List<Integer> result = new ArrayList<>();
        for (JsonNode item : this.array()) {
            result.add(item.toInt());
        }
        return result;
    }

    public @NotNull List<String> toStrList() throws SyntaxException {
        if (!type.isArray()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "toStrList函数仅适用于Array Node");
        }
        List<String> result = new ArrayList<>();
        for (JsonNode item : this.array()) {
            result.add(item.string().value());
        }
        return result;
    }

    public long toLong() throws SyntaxException {
        if (!type.isNumber()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "toInt函数仅适用于Number Node");
        }
        try {
            return Long.parseLong(this.number().value());
        } catch (NumberFormatException e) {
            throw SyntaxException.error(node, SyntaxErrorEnum.PARSE_LONG_FAILED,
                    "toLong解析失败, value: " + this.number().value() + " , e:" + e.getMessage());
        }
    }

    public Double toDouble() throws SyntaxException {
        if (!type.isNumber()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "toInt函数仅适用于Number Node");
        }
        try {
            return Double.parseDouble(this.number().value());
        } catch (NumberFormatException e) {
            throw SyntaxException.error(node, SyntaxErrorEnum.PARSE_DOUBLE_FAILED,
                    "toDouble解析失败, value: " + this.number().value() + " , e:" + e.getMessage());
        }
    }

    public Date toDate(String pattern) throws SyntaxException {
        if (!type.isString()) {
            throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_NODE_TYPE, "toDate函数仅适用于String Node");
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.parse(this.string().value());
        } catch (ParseException e) {
            throw SyntaxException.error(node, SyntaxErrorEnum.PARSE_DATE_FAILED,
                    "toDate解析失败, pattern: " + pattern + " value: " + this.string().value() + " , e:" + e.getMessage());
        }
    }

    public Date toDate() throws SyntaxException {
        return toDate("yyyy-MM-dd HH:mm:ss");
    }

    public String toJson() throws SyntaxException {
        return toJson("", 0, "");
    }

    public String toJson(String indent, @NotNull final String lineEnding) throws SyntaxException {
        return toJson(indent, 0, lineEnding);
    }

    /**
     * 格式化后的json字符串
     *
     * @param indent     缩进，通常是 '\t'或者空格，或者为空
     * @param lineEnding 换行符， 通常是'\n', 或者为空
     * @return 格式化话后的 json 字符串
     * @throws SyntaxException 语法异常
     */
    private String toJson(String indent, int depth, @NotNull final String lineEnding) throws SyntaxException {
        if (type.isNull()) {
            return "null";
        }
        if (type.isBool()) {
            if (bool().isTrue()) {
                return "true";
            } else {
                return "false";
            }
        }
        if (type.isNumber()) {
            return number().value();
        }
        if (type.isString()) {
            return '"' + string().value() + '"';
        }

        if (type.isArray()) {
            final String thisIndents = StringUtils.repeat(indent, depth);
            final String nextIndents = StringUtils.repeat(indent, depth + 1);
            StringBuilder s = new StringBuilder();

            final JsonArray array = this.array();
            s.append('[');
            final Iterator<JsonNode> iterator = array.iterator();
            while (iterator.hasNext()) {
                JsonNode n = iterator.next();
                s.append(lineEnding)
                        .append(nextIndents)
                        .append(n.toJson(indent, depth + 1, lineEnding));

                if (iterator.hasNext()) {
                    s.append(',');
                }
            }
            s.append(lineEnding).append(thisIndents).append(']');
            return s.toString();
        }

        if (type.isObject()) {
            final String thisIndents = StringUtils.repeat(indent, depth);
            final String nextIndents = StringUtils.repeat(indent, depth + 1);
            StringBuilder s = new StringBuilder();
            final JsonObject obj = this.object();
            s.append('{');
            final @NotNull Iterator<Pair<JsonString, JsonNode>> iterator = obj.iterator();
            while (iterator.hasNext()) {
                final Pair<JsonString, JsonNode> pair = iterator.next();
                s.append(lineEnding)
                        .append(nextIndents)
                        .append('"').append(pair.getKey().value()).append('"')
                        .append(":")
                        .append(pair.getValue().toJson(indent, depth + 1, lineEnding));

                if (iterator.hasNext()) {
                    s.append(',');
                }
            }
            s.append(lineEnding).append(thisIndents).append('}');
            return s.toString();
        }
        throw SyntaxException.error(node, SyntaxErrorEnum.UNEXPECTED_LOGIC, "不可能有其他类型");
    }
}
