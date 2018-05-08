package com.ming.base;

/**
 * constant_utf8_info
 * @author smallclover
 *
 */
public class ConstantUtf8Info implements ConstantInfo {
	private int tag = 1;
	private int length = 0;
	private byte[] bytes = null;

	public ConstantUtf8Info(int length) {
		this.length = length;
		bytes = new byte[length];
	}

	public byte[] getBytes() {
		return bytes;
	}

}
