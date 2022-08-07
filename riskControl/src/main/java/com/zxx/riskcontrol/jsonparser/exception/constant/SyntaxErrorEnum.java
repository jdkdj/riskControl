package com.zxx.riskcontrol.jsonparser.exception.constant;

public enum SyntaxErrorEnum {
    // 错误码
    SOURCE_EMPTY(4001, "源文件为空"),
    EOF_EXCEPTION(4002, "解析未完成，解析字符串已达到末尾"),
    ILLEGAL_ENDING(4003, "illegal ending"),
    UNEXPECTED_CHAR(4011, "异常的字符"),
    UNEXPECTED_TOKEN(4021, "异常的token"),
    UNEXPECTED_NODE_TYPE(4031, "json node类型异常"),
    MISSING_OBJECT_KEY(4032, "未找到期望的object key"),
    PARSE_NUMBER_UNFINISHED(4101, "解析Number未完成"),
    PARSE_STRING_UNFINISHED(4102, "解析String未完成"),
    PARSE_INTEGER_FAILED(4103, "解析Integer失败"),
    PARSE_LONG_FAILED(4104, "解析Long失败"),
    PARSE_DOUBLE_FAILED(4104, "解析Double失败"),
    PARSE_DATE_FAILED(4105, "解析Date类失败"),
    UNEXPECTED_LOGIC(5001, "异常逻辑"),
    NOT_IMPLEMENTED(5010, "未实现"),
    UNKNOWN_TYPE(6001, "未知的类型"),
            ;
    int code;
    final String msg;

    SyntaxErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
