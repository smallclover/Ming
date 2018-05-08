package com.ming.core;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author smallclover
 * 2 byte unsigned integer
 */
public class U1 {
	private short value;

	public U1(byte b) {
		this.value = (short) (b & 0xff);
	}

	public U1(int value) {
		this.value = (short) value;
	}

	public short getValue() {
		return value;
	}

	public String toHex() {
		return Integer.toHexString(value);
	}
}
