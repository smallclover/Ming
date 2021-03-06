package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

/**
 * @author smallclover
 * 表示字段或方法的名称和类型
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {
    private static U1 tag = new U1(12);
    private U2 name_index = null;
    private U2 descriptor_index = null;

    public ConstantNameAndTypeInfo(ClassFileReader cfr) {
        this.name_index = cfr.readU2();
        this.descriptor_index = cfr.readU2();
    }


    public U1 getTag() {
        return tag;
    }

    public U2 getNameIndex() {
        return name_index;
    }

    public U2 getDescriptorIndex() {
        return descriptor_index;
    }
}
