package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

public class ConstantDoubleInfo extends ConstantInfo{
    //todo 注意double占用两个U4
	private static final U1 tag = new U1(6);
	private U4 high_bytes;
	private U4 low_bytes;

	public ConstantDoubleInfo(ClassFileReader cfr) {
		high_bytes = cfr.readU4();
		low_bytes = cfr.readU4();
	}

	public U1 getTag() {
		return tag;
	}

	public U4 getHighBytes() {
		return high_bytes;
	}

	public U4 getLowBytes() {
		return low_bytes;
	}

}
