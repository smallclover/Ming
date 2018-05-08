package com.ming.core;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author smallclover
 *
 * 4 byte unsigned integer
 */
public class U4 {
	private long value;

	public U4(byte[] bytes) {
		for (int i = 0; i < bytes.length; i ++) {
			value <<= 8;
			value |= (bytes[i] & 0xff);
		}
	}

	public U4(int value) {
		this.value = value;
	}

	public long getValue() {
		return value;
	}

	public String toHex() {
		return Long.toHexString(value);
	}
}
