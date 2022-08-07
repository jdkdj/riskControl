package com.zxx.riskcontrol.jsonparser.json.ast;

/**
 * json bool node
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 11:25
 */
public class JsonBool extends JsonBase {
    final boolean value;

    public JsonBool(final String src, final int pos, final int line, final int column, final boolean value) {
        super(src, pos, line, column);
        this.value = value;
    }

    public boolean value() {
        return value;
    }

    public boolean isTrue() {
        return value;
    }

    public boolean isFalse() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonBool{" +
                "value=" + value +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
