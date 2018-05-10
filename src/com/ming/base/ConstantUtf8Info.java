package com.ming.base;

import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

/**
 * @author smallclover
 * constant_utf8_info
 * 用于表示字符串常量的值
 * tag = 1
 */
public class ConstantUtf8Info extends ConstantInfo {
	private static final U1 tag = new U1(1);
	private U2 length = null;
	private U1[] u1 = null;

	public ConstantUtf8Info(ClassFileReader cfr)  {
		length = cfr.readU2();
		u1 = new U1[length.getValue()];
		for(int i = 0; i < u1.length; i ++) {
			u1[i] = cfr.readU1();
		}
	}

	public String getValue() {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < u1.length; i ++) {
			sb.append(u1[0]);
		}
		return sb.toString();
	}
}
