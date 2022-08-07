package com.zxx.riskcontrol.jsonparser.json.ast;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * json string node
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 11:28
 */
public class JsonString extends JsonBase {
    final @NotNull String value;

    public JsonString(@NotNull final String src, int pos, int line, int column, @NotNull final String value) {
        super(src, pos, line, column);

        this.value = value;
    }

    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "JsonString{" +
                "value='" + value + '\'' +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o instanceof String) {
            return value.equals((String)o);
        }
        if (!(o instanceof JsonString)) {
            return false;
        }
        JsonString that = (JsonString) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
