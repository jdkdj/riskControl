package com.zxx.riskcontrol.jsonparser.json.ast;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * json object node
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 11:28
 */
public class JsonObject extends JsonBase implements Iterable<Pair<JsonString, JsonNode>> {
    List<Pair<JsonString, JsonNode>> map = new ArrayList<>();

    public JsonObject(final String src, int pos, int line, int column) {
        super(src, pos, line, column);
    }

    public void put(@NotNull JsonString key, @NotNull JsonNode value) {
        Pair<JsonString, JsonNode> node = new ImmutablePair<>(key, value);
        map.add(node);
    }

    public @Nullable JsonNode get(@NotNull String key) {
        for (Pair<JsonString, JsonNode> node : map) {
            if (node.getKey().value().equals(key)) {
                return node.getValue();
            }
        }
        return null;
    }

    public @NotNull JsonNode mustGet(@NotNull String key) throws SyntaxException {
        for (Pair<JsonString, JsonNode> node : map) {
            if (node.getKey().value().equals(key)) {
                return node.getValue();
            }
        }
        throw SyntaxException.error(this, SyntaxErrorEnum.MISSING_OBJECT_KEY, "未找到期望的Object key:" + key);
    }

    public int size() {
        return map.size();
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "map=" + map +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    @NotNull
    @Override
    public Iterator<Pair<JsonString, JsonNode>> iterator() {
        return map.iterator();
    }

    @Override
    public void forEach(Consumer<? super Pair<JsonString, JsonNode>> action) {
        map.forEach(action);
    }

    @Override
    public Spliterator<Pair<JsonString, JsonNode>> spliterator() {
        return map.spliterator();
    }
}
