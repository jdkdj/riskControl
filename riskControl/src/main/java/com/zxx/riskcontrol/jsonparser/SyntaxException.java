package com.zxx.riskcontrol.jsonparser;

import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonBase;
import com.zxx.riskcontrol.jsonparser.json.internal.reader.JsonReader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SyntaxException extends Exception{
    /**
     * 源文件
     */
    final String src;
    /**
     * 错误字符位置
     */
    int pos;
    /**
     * 错误行号
     */
    int line;
    /**
     * 错误列号
     */
    int column;
    /**
     * 错误代码
     */
    int code;
    /**
     * 错误消息
     */
    final @NotNull String msg;
    /**
     * 错误补充消息
     */
    final @NotNull String detail;

    /**
     * 构造函数
     *
     * @param json   JsonReader
     * @param se     错误枚举值
     * @param detail 错误补充消息
     */
    public static SyntaxException error(@NotNull JsonReader json, @NotNull final SyntaxErrorEnum se, @Nullable final String detail) {
        return new SyntaxException(json.src(), json.pos(), json.line(), json.column(), se.getCode(), se.getMsg(), detail);
    }

    /**
     * 构造函数
     *
     * @param json JsonReader
     * @param se   错误枚举值
     */
    public static SyntaxException error(@NotNull JsonReader json, @NotNull SyntaxErrorEnum se) {
        return error(json, se, null);
    }

    /**
     * 构造函数
     *
     * @param json   JsonBase
     * @param se     错误枚举值
     * @param detail 错误补充消息
     */
    public static SyntaxException error(@NotNull JsonBase json, @NotNull SyntaxErrorEnum se, @Nullable final String detail) {
        return new SyntaxException(json.src(), json.pos(), json.line(), json.column(), se.getCode(), se.getMsg(), detail);
    }

    /**
     * 构造函数
     *
     * @param json JsonBase
     * @param se   错误枚举值
     */
    public static SyntaxException error(JsonBase json, SyntaxErrorEnum se) {
        return error(json, se, null);
    }

    /**
     * 禁止直接用构造函数
     *
     * @param src    源文件
     * @param pos    错误字符位置
     * @param line   错误行号
     * @param column 列号
     * @param code   错误代码
     * @param msg    错误消息
     * @param detail 错误补充消息
     */
    private SyntaxException(final String src, int pos, int line, int column, int code, @NotNull final String msg, @Nullable final String detail) {
        this.src = src;
        this.pos = pos;
        this.line = line;
        this.column = column;
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }

    public @NotNull String src() {
        return src;
    }

    public int pos() {
        return pos;
    }

    public int line() {
        return line;
    }

    public int column() {
        return column;
    }

    public int code() {
        return code;
    }

    public @NotNull String msg() {
        return msg;
    }

    public @Nullable String detail() {
        return detail;
    }

    public @NotNull String errorLocation() {
        return src.substring(pos);
    }

    @Override
    public String toString() {
        return "SyntaxException{" +
                "src='" + src + '\'' +
                ", pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                ", code=" + code +
                ", msg='" + msg + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }

    @Override
    public @NotNull String getMessage() {
        if (detail != null) {
            return msg + ":" + detail;
        }
        return msg;
    }
}
