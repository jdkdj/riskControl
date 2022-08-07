package com.zxx.riskcontrol.jsonparser.json.ast;

import com.zxx.riskcontrol.jsonparser.json.JsonNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * json array node
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 11:26
 */
public class JsonArray extends JsonBase implements Iterable<JsonNode> {
    List<JsonNode> array;

    public JsonArray(final String src, final int pos, final int line, final int column) {
        super(src, pos, line, column);
        this.array = new ArrayList<>();
    }

    public void add(@NotNull JsonNode node) {
        array.add(node);
    }

    public int size() {
        return array.size();
    }

    public @NotNull JsonNode index(int i) {
        return array.get(i);
    }

    @Override
    public String toString() {
        return "JsonArray{" +
                "array =" + array +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    @NotNull
    @Override
    public Iterator<JsonNode> iterator() {
        return array.iterator();
    }

    @Override
    public void forEach(Consumer<? super JsonNode> action) {
        array.forEach(action);
    }

    @Override
    public Spliterator<JsonNode> spliterator() {
        return array.spliterator();
    }
}
