package com.ming.base.attribute;

import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

public class AttributeConstantValueInfo extends AttributeInfo{
    private U2 attribute_name_index;
    private U4 attribute_length;
    private U2 constantvalue_index;



	public AttributeConstantValueInfo(U2 name_index, ClassFileReader cfr) {
		attribute_name_index = name_index;
		attribute_length = cfr.readU4();
		constantvalue_index = cfr.readU2();
	}

/*	@Override
	public void read(ClassFileReader cfr) {
		attribute_name_index = cfr.readU2();
		attribute_length = cfr.readU4();
		constantvalue_index = cfr.readU2();

	}*/

	public U2 getAttribute_name_index() {
		return attribute_name_index;
	}

	public void setAttribute_name_index(U2 attribute_name_index) {
		this.attribute_name_index = attribute_name_index;
	}

	public U4 getAttribute_length() {
		return attribute_length;
	}

	public void setAttribute_length(U4 attribute_length) {
		this.attribute_length = attribute_length;
	}

	public U2 getConstantvalue_index() {
		return constantvalue_index;
	}

	public void setConstantvalue_index(U2 constantvalue_index) {
		this.constantvalue_index = constantvalue_index;
	}

	@Override
	public String toString() {
		return "[ attribute_name_index: " + attribute_name_index.getValue() + "]\n"
			  + "[ attribute_length: " + attribute_length.getValue() + "]\n"
			  + "[ constantvalue_index: " + constantvalue_index.getValue() + "]\n";
	}
}
