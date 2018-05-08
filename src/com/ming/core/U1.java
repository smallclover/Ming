package com.ming.core;

import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author smallclover
 * 2 byte unsigned integer
 */
public class U1 {
	private byte[] b = new byte[1];
	private int value;

	public U1(InputStream is) throws IOException {
		is.read(b, 0, 1);
		this.value = 0;
		toDec();
	}
	private void toDec() {
		value = b[0] & 0xff;
	}
	public int getValue() {
		return value;
	}

	public String toHex() {
		return Integer.toHexString(value);
	}
}
