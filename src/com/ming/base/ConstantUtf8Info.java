package com.ming.base;

import com.ming.core.U1;
import com.ming.core.U2;

/**
 * constant_utf8_info
 * @author smallclover
 *
 */
public class ConstantUtf8Info implements ConstantInfo {
	private U1 tag = new U1(1);
	private U2 length = null;
	private byte[] bytes = null;

	public ConstantUtf8Info(byte[] length, byte[] bytes) {
		this.length = new U2(length);
		this.bytes = bytes;
	}

}
