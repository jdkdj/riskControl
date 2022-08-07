package com.zxx.riskcontrol.jsonparser.json.utils;

public class CharUtils {
    static public boolean isAlpha(int c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }

    static public boolean isDigit1to9(int c) {
        return c >= '1' && c <= '9';
    }

    static public boolean isDigit0to9(int c) {
        return c >= '0' && c <= '9';
    }
}
