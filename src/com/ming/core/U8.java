package com.ming.core;

/**
 * 读取8个字节无符号整数
 */
public class U8 {
    private int value;

    public U8(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++) {
            this.value <<= 8;
            this.value |= (bytes[i] & 0xff);
        }
    }

    public U8(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String toHex() {
        return Integer.toHexString(value);
    }
}
