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
	private byte[] bytes = new byte[4];
	private long value;

	public U4(InputStream is) throws IOException {
		is.read(bytes, 0, 4);
		this.value = 0L;
		toDec();
	}
	private void toDec(){
		for (int i = 0; i < bytes.length; i ++) {
			value <<= 8;
			value |= (bytes[i] & 0xff);
		}
	}
	public long getValue() {
		return value;
	}

	public String toHex() {
		getValue();
		return Long.toHexString(value);
	}
}
