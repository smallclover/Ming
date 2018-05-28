package com.ming.core;


/**
 *
 * @author smallcloverc
 * 2 byte unsigned integer
 */
public class U2 {
	private int value;

	public U2(byte[] bytes) {
		for (int i = 0; i < bytes.length; i ++) {
			this.value <<= 8;
			this.value |= (bytes[i] & 0xff);
		}
	}

	public U2(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public String toHex() {
		return Integer.toHexString(value);
	}

}
