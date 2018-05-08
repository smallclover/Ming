package com.ming.core;

import java.io.IOException;
import java.io.InputStream;

public class U2 {
	private byte[] bytes = new byte[2];
	private int value;
	public U2(InputStream is) throws IOException {
		is.read(bytes, 0, 2);
		this.value = 0;
	}

	public int getValue() {
		for (int i = 0; i < bytes.length; i ++) {
			value <<= 8;
			value |= (bytes[i] & 0xff);
		}

		return value;
	}

	public String toHex() {
		getValue();
		return Integer.toHexString(value);
	}

}
