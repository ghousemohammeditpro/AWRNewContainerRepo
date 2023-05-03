package com.awr.autotrustproxy.util;

public class HexString {
    public HexString() {
        super();
    }
    public static String toHexString(byte[] ba) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < ba.length; i++)
            str.append(String.format("%x", ba[i]));
        return str.toString();
    }
}
