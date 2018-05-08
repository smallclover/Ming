package com.ming.base;

import com.ming.core.U1;
import com.ming.core.U2;

/**
 *
 * @author smallclover
 * constant_class_info
 * 类或接口的符号引用
 * tag = 7
 */
public class ConstantClassInfo implements ConstantInfo {
	private U1 tag = new U1(7);
	private U2 name_index = null;

	public ConstantClassInfo(byte[] name_index) {
		 this.name_index = new U2(name_index);
	}

	public int getNameIndex() {
		return name_index.getValue();
	}
}
