package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 *
 * @author smallclover
 * constant_integer_info
 */
public class ConstantIntegerInfo extends ConstantInfo {
	private static final U1 tag = new U1(3);
	private U4 value;

	public ConstantIntegerInfo(ClassFileReader cfr) {
		value = cfr.readU4();
	}

    @Override
    public String toString() {
        return "[value]: " + value.toHex() + "\n";
    }
	//todo
}
