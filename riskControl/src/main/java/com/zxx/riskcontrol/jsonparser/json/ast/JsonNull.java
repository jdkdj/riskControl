package com.zxx.riskcontrol.jsonparser.json.ast;

/**
 * json null node
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 12:57
 */
public class JsonNull extends JsonBase {
    public JsonNull(final String src, final int pos, final int line, final int column) {
        super(src, pos, line, column);
    }

    @Override
    public String toString() {
        return "JsonNull{" +
                "pos=" + pos +
                ", line=" + line +
                ", column=" + column +
                '}';
    }
}
