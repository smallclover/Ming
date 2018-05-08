package com.ming.core;

import java.io.IOException;
import java.io.InputStream;

public class U4 {
	private byte[] bytes = new byte[4];
	private long value;

	public U4(InputStream is) throws IOException {
		is.read(bytes, 0, 4);
		this.value = 0L;
	}

	public long getValue() {
		for (int i = 0; i < bytes.length; i ++) {
			value <<= 8;
			value |= (bytes[i] & 0xff);
		}

		return value;
	}

	public String toHex() {
		getValue();
		return Long.toHexString(value);
	}
}
