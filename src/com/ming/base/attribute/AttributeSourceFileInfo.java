package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

public class AttributeSourceFileInfo extends AttributeInfo{
    private U2 attribute_name_index;
    private U4 attribute_length;
    private U2 sourcefile_index;

	public AttributeSourceFileInfo(U2 name_index, ClassFileReader cfr) {
		attribute_name_index = name_index;
		attribute_length = cfr.readU4();
		sourcefile_index = cfr.readU2();
	}

	@Override
	public String toString() {
		return "[attribute_name_index: " + attribute_name_index.getValue() + "]\n"
			  + "[attribute_length: " + attribute_length.getValue() + "]\n"
			  + "[sourcefile_index: " + sourcefile_index.getValue() + "]\n";
	}
}
