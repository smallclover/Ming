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

	@Override
	public String toString() {
		return "[tag]: " + tag.getValue() + "\n"
				+ "[name_index]: " + name_index.getValue() + "\n"
				+ "[descriptor_index]: " + descriptor_index.getValue() + "\n";
	}
}
