package com.zxx.riskcontrol.jsonparser.json.internal.reader;

import com.zxx.riskcontrol.jsonparser.SyntaxException;
import com.zxx.riskcontrol.jsonparser.exception.constant.SyntaxErrorEnum;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonBase;
import com.zxx.riskcontrol.jsonparser.json.ast.JsonString;
import com.zxx.riskcontrol.jsonparser.json.utils.CharUtils;
import org.jetbrains.annotations.NotNull;

public class JsonReader {
   final @NotNull String src;
   int pos;
   int line;
   int column;
   final JsonStringBuffer buffer = new JsonStringBuffer();

   public JsonReader(@NotNull String src) {
       this.src = src;
       this.pos = 0;
       this.line = 1;
       this.column = 1;
   }

    public final String src() {
        return this.src;
    }

    public int pos() {
        return this.pos;
    }

    public int line() {
        return this.line;
    }

    public int column() {
        return this.column;
    }

    private void nextLine() {
       this.column = 1;
       this.line++;
       this.pos++;
    }

    private void nextColumn() {
       this.pos++;
       this.column++;
    }

    private void nextColumn(int n) {
       this.pos += n;
       this.column += n;
    }

    public void mustConsume(@NotNull String token) throws SyntaxException {
        if (isEmpty()) {
            throw SyntaxException.error(this, SyntaxErrorEnum.EOF_EXCEPTION);
        }
        if (token.length() > remain()) {
            throw SyntaxException.error(this, SyntaxErrorEnum.UNEXPECTED_TOKEN, "期望token" + token);
        }

        for (int i = pos, j = 0; j < token.length(); i++, j++) {
            if (src.charAt(i) != token.charAt(j)) {
                throw SyntaxException.error(this, SyntaxErrorEnum.UNEXPECTED_TOKEN, "期望token" + token);
            }
        }
        nextColumn(token.length());
    }

    public boolean mayParseToken(@NotNull String token) {
       if (token.length() > remain()) {
           return false;
       }

        for (int i = 0; i < token.length(); i++) {
            if (src.charAt(pos + i) != token.charAt(i)) {
                return false;
            }
        }

        pos += token.length();
        column += token.length();
        return true;
    }

    public int remain() {
       return src.length() - pos;
    }

    public int charAt(int i) {
       return src.charAt(i);
    }

    public boolean outRange(int i) {
       return i >= src.length();
    }

    public String remainSrc() {
       return src.substring(pos);
    }


    public boolean isEmpty() {
       return pos >= src.length();
    }

    public boolean isNotEmpty() {
       return pos < src.length();
    }

    public int peekNextChar() {
       if (pos + 1 >= src.length()) {
           return -1;
       }
       return src.charAt(pos + 1);
    }

    public char peek() {
       return src.charAt(pos);
    }

    public void consumeWhiteSpaces() {
        while (isNotEmpty()) {
            switch (peek()) {
                case ' ':
                case '\t':
                    nextColumn();
                    break;
                case '\r':
                    if (peekNextChar() == '\n') {
                        nextColumn();
                    }
                    nextLine();
                    break;
                case '\n':
                    nextLine();
                    break;
                default:
                    return;
            }
        }
    }



    public String parseString() throws SyntaxException {
        if (peek() != '\"') {
            throw SyntaxException.error(this, SyntaxErrorEnum.UNEXPECTED_LOGIC, "parse字符串，起始字符应是'\"'");
        }
        nextColumn();

        buffer.clear();
        boolean inEscape = false;
        while (isNotEmpty()) {
            if (inEscape) {
                switch (peek()) {
                    case '\"':
                        buffer.append('\"');
                        break;
                    case '\\':
                        buffer.append('\\');
                        break;
                    case '/':
                        buffer.append('/');
                        break;
                    case 'b':
                        buffer.append('\b');
                        break;
                    case 'f':
                        buffer.append('\f');
                        break;
                    case 'n':
                        buffer.append('\n');
                        break;
                    case 'r':
                        buffer.append('\r');
                        break;
                    case 't':
                        buffer.append('\t');
                        break;
                    case 'u':
                        throw SyntaxException.error(this,
                                SyntaxErrorEnum.NOT_IMPLEMENTED, "暂未实现，json特殊格式暂时不需要考虑");
                    default:
                        throw SyntaxException.error(this,
                                SyntaxErrorEnum.UNEXPECTED_CHAR, "非法的escape char:'" + peek() + "'");
                }
                nextColumn();
                inEscape = false;
            } else {
                char ch;
                switch (ch = peek()) {
                    case '\\':
                        inEscape = true;
                        nextColumn();
                        break;
                    case '\n':
                        buffer.append('\n');
                        nextLine();
                        break;
                    case '\r':
                        buffer.append('\r');
                        if (peekNextChar() == '\n') {
                            buffer.append('\n');
                            nextColumn();
                        }
                        nextLine();
                        break;
                    case '\"':
                        //找到字符串配对的结尾 " 符,解析字符串结束
                        nextColumn();
                        return buffer.toString();
                    default:
                        buffer.append(ch);
                        nextColumn();
                }
            }
        }
        return null;
    }

    public String parserNumber() throws SyntaxException {
       int step = pos;
       if (peek() == '-') {
           step++;
       }
       if (outRange(step)) {
           throw SyntaxException.error(this, SyntaxErrorEnum.PARSE_NUMBER_UNFINISHED);
       }
       if (src.charAt(step) == '0') {
           step++;
       } else {
           if (!CharUtils.isDigit0to9(src.charAt(step))) {
               throw SyntaxException.error(this, SyntaxErrorEnum.UNEXPECTED_CHAR, "非法字符:'" + charAt(step) + "'");
           }
           while (!outRange(step) && CharUtils.isDigit0to9(charAt(step))) {
               step ++;
           }
       }
       while (!outRange(step) && src.charAt(step) == '.') {
           step++;
           if (outRange(step)) {
               throw SyntaxException.error(this, SyntaxErrorEnum.PARSE_NUMBER_UNFINISHED);
           }
           if (!CharUtils.isDigit0to9(charAt(step))) {
               throw SyntaxException.error(this, SyntaxErrorEnum.UNEXPECTED_CHAR, "非法字符:'" + charAt(step) + "'");
           }
           while (!outRange(step) && CharUtils.isDigit0to9(charAt(step))) {
               step++;
           }
       }
       if (isExponent(step)) {
           step++;
           if (isPlusOrMinus(step)) {
               step ++;
           }
           if (outRange(step)) {
               throw SyntaxException.error(this, SyntaxErrorEnum.PARSE_NUMBER_UNFINISHED);
           }
           if (!CharUtils.isDigit0to9(charAt(step))) {
               throw SyntaxException.error(this, SyntaxErrorEnum.UNEXPECTED_CHAR, "非法字符:'" + charAt(step) + "'");
           }
           while (!outRange(step) && CharUtils.isDigit0to9(charAt(step))) {
               step++;
           }
       }
       String sNum = src.substring(pos, step);
       pos = step;
       nextColumn(step - pos);
       return sNum;
    }

    private boolean isExponent(int step) {
       if (outRange(step)) {
           return false;
       }
       return src.charAt(step) == 'e' || src.charAt(step) == 'E';
    }

    private boolean isPlusOrMinus(int step) {
       if (outRange(step)) {
           return false;
       }
       return src.charAt(step) == '+' || src.charAt(step) == '-';
    }
}




/**
 * JsonStringBuffer 为了解析Json字符串的辅助类
 * <p> 一方面char[]处理字符更快
 * <p> 另一方是因为 Java 原生的 StringBuilder 或者 StringBuffer 解析会有问题，其并适用于解析字符串的场合
 *
 * @author zhangsen@19pay.com.cn
 * @since 2020/04/18 15:0
 */
class JsonStringBuffer {
    private final char[] charBuffer = new char[1024];
    private int pos = 0;
    private final StringBuffer buffer = new StringBuffer();

    public void append(char i) {
        charBuffer[pos++] = i;
        if (pos == 1024) {
            buffer.append(charBuffer, 0, pos);
            pos = 0;
        }
    }

    @Override
    public String toString() {
        if (pos > 0) {
            buffer.append(charBuffer, 0, pos);
        }
        return buffer.toString();
    }

    public void clear() {
        pos = 0;
        buffer.setLength(0);
    }
}
