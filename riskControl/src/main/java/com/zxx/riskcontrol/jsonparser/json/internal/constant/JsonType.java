package com.zxx.riskcontrol.jsonparser.json.internal.constant;

public enum JsonType {
    //json 类型枚举
    BOOLEAN(1, "BOOL"),
    NULL(2, "NULL"),
    NUMBER(3, "NUMBER"),
    STRING(4, "STRING"),
    ARRAY(5, "ARRAY"),
    OBJECT(6, "OBJECT");

    int code;
    final String str;

    JsonType(int code, String str) {
        this.code = code;
        this.str = str;
    }

    public String str() {
        return str;
    }

    public boolean isBool() {
        return code == 1;
    }

    public boolean isNull() {
        return code == 2;
    }
    public boolean isNumber() {
        return code == 3;
    }
    public boolean isString() {
        return code == 4;
    }
    public boolean isArray() {
        return code == 5;
    }
    public boolean isObject() {
        return code == 6;
    }



}
