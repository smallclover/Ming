package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

public class ConstantFieldrefInfo extends ConstantInfo {
    private static final U1 tag = new U1(10);
    private U2 class_index = null;
    private U2 name_and_type_index = null;

    public ConstantFieldrefInfo(ClassFileReader cfr) {
        class_index = cfr.readU2();
        name_and_type_index = cfr.readU2();
    }

	@Override
	public String toString() {
		return "[tag: " + tag.getValue() + "]\n"
				+ "[class_index: " + class_index.getValue() + "]\n"
				+ "[name_and_type_index: " + name_and_type_index.getValue() + "]\n";
	}
}
