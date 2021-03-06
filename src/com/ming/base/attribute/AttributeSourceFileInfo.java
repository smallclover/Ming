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

	public U2 getAttributeNameIndex() {
		return attribute_name_index;
	}

	public U4 getAttributeLength() {
		return attribute_length;
	}

	public U2 getSourcefileIndex() {
		return sourcefile_index;
	}

}
