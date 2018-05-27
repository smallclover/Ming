package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

public class ConstantFloatInfo extends ConstantInfo{
	private static final U1 tag = new U1(4);
	private U4 value;

	public ConstantFloatInfo(ClassFileReader cfr) {
		value = cfr.readU4();
	}

}
