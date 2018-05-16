package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 * @author smallclover
 */
public class ConstantLongInfo extends ConstantInfo{
    //todo 注意long占用两个U4
    private static final U1 tag = new U1(5);
    private U4 high_bytes;
    private U4 low_bytes;

    public ConstantLongInfo(ClassFileReader cfr) {
        high_bytes = cfr.readU4();
        low_bytes = cfr.readU4();
    }

    @Override
    public String toString() {
        return "[value: " + high_bytes.toHex() + low_bytes.toHex() + "]\n";
    }
}
