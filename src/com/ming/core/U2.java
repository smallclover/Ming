package com.ming.core;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author smallclover
 * 2 byte unsigned integer
 */
public class U2 {
	private byte[] bytes = new byte[2];
	private int value;
	public U2(InputStream is) throws IOException {
		is.read(bytes, 0, 2);
		this.value = 0;
		toDec();
	}

	private void toDec() {
		for (int i = 0; i < bytes.length; i ++) {
			value <<= 8;
			value |= (bytes[i] & 0xff);
		}

	}

	public int getValue() {
		return value;
	}

	public String toHex() {
		return Integer.toHexString(value);
	}

}
