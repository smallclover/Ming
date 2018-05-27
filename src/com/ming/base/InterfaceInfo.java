package com.ming.base;

import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

public class InterfaceInfo {
    private static U2 name_index;

    public InterfaceInfo(ClassFileReader cfr) {
        name_index = cfr.readU2();
    }

    public static U2 getNameIndex() {
        return name_index;
    }
}
