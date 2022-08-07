package com.zxx.riskcontrol.jsonparser.json.ast;

import org.jetbrains.annotations.NotNull;

/**
 * json number node
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 11:26
 */
public class JsonNumber extends JsonBase {
    /**
     * number的字符表达，按需解析double、int
     */
    @NotNull
    final String value;

    public JsonNumber(final String src, int pos, int line, int column, final String value) {
        super(src, pos, line, column);
        this.value = value;
    }

    public @NotNull String value() {
        return value;
    }

    public @NotNull String toStr() {
        return value;
    }

    public double toDouble() {
        return Double.parseDouble(value);
    }

    public int toInt() {
        return Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return "JsonNumber{" +
                "value=" + value +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
