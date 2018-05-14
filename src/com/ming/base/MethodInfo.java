package com.ming.base;

import com.ming.core.U2;

public class MethodInfo {
    private U2 access_flags = null;
    private U2 name_index = null;
    private U2 descriptor_index = null;
    private U2 attributes_count = null;
    private AttributeInfo attributes[] = null;

	public U2 getAccessFlags() {
		return access_flags;
	}
	public void setAccessFlags(U2 access_flags) {
		this.access_flags = access_flags;
	}
	public U2 getNameIndex() {
		return name_index;
	}
	public void setNameIndex(U2 name_index) {
		this.name_index = name_index;
	}
	public U2 getDescriptorIndex() {
		return descriptor_index;
	}
	public void setDescriptorIndex(U2 descriptor_index) {
		this.descriptor_index = descriptor_index;
	}
	public U2 getAttributesCount() {
		return attributes_count;
	}
	public void setAttributesCount(U2 attributes_count) {
		this.attributes_count = attributes_count;
	}
	public AttributeInfo[] getAttributes() {
		return attributes;
	}
	public void setAttributes(AttributeInfo[] attributes) {
		this.attributes = attributes;
	}


}
