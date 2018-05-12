package com.ming.base.constant;

import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

public class ConstantStringInfo extends ConstantInfo {
    private static final U1 tag = new U1(8);
    private U2 string_index;

    public ConstantStringInfo(ClassFileReader cfr) {
        this.string_index = cfr.readU2();
    }

    public U2 getStringIndex() {
        return string_index;
    }
}
