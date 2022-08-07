package com.zxx.riskcontrol.jsonparser.json.ast;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public abstract class JsonBase implements Serializable {
    @NotNull
    final String src;
    final int pos;
    final int line;
    final int column;

    public JsonBase(@NotNull final String src, int pos, int line, int column) {
        this.src = src;
        this.pos = pos;
        this.line = line;
        this.column = column;
    }
    @NotNull
    public final String src() {
        return src;
    }

    public final int pos() {
        return pos;
    }

    public final int line() {
        return line;
    }

    public final int column() {
        return column;
    }

    @Override
    public String toString() {
        return "JsonBase{" +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
