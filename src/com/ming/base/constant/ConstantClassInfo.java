package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

/**
 *
 * @author smallclover
 * constant_class_info
 * 类或接口的符号引用
 * tag = 7
 */
public class ConstantClassInfo extends ConstantInfo {
	private static final U1 tag = new U1(7);
	private U2 name_index = null;

	public ConstantClassInfo(ClassFileReader cfr) {
		name_index = cfr.readU2();
	}

	public U2 getNameIndex() {
		return name_index;
	}

	public U1 getTag() {
		return tag;
	}
}
