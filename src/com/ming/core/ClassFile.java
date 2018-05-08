package com.ming.core;

/**
 *
 * @author smallclover
 * class file structure
 */
public class ClassFile {
	private U4 magic;
	private U2 minor_version;
	private U2 major_version;
	private U2 constant_pool_count;
	//constant_pool
	private U2 access_flags;
	private U2 this_class;
	private U2 super_class;
	private U2 interfaces_count;
	//interfaces
	private U2 fields_count;
	//fields
	private U2 methods_count;
	//methods;
	private U2 attributes_count;
	//attributes

	public U4 getMagic() {
		return magic;
	}
	public void setMagic(U4 magic) {
		this.magic = magic;
	}
	public U2 getMinorVersion() {
		return minor_version;
	}
	public void setMinorVersion(U2 minor_version) {
		this.minor_version = minor_version;
	}
	public U2 getMajorVersion() {
		return major_version;
	}
	public void setMajorVersion(U2 major_version) {
		this.major_version = major_version;
	}
	public U2 getConstantPoolCount() {
		return constant_pool_count;
	}
	public void setConstantPoolCount(U2 constant_pool_count) {
		this.constant_pool_count = constant_pool_count;
	}
	public U2 getAccessFlags() {
		return access_flags;
	}
	public void setAccessFlags(U2 access_flags) {
		this.access_flags = access_flags;
	}
	public U2 getThisClass() {
		return this_class;
	}
	public void setThisClass(U2 this_class) {
		this.this_class = this_class;
	}
	public U2 getSuperClass() {
		return super_class;
	}
	public void setSuperClass(U2 super_class) {
		this.super_class = super_class;
	}
	public U2 getInterfacesCount() {
		return interfaces_count;
	}
	public void setInterfacesCount(U2 interfaces_count) {
		this.interfaces_count = interfaces_count;
	}
	public U2 getFieldsCount() {
		return fields_count;
	}
	public void setFieldsCount(U2 fields_count) {
		this.fields_count = fields_count;
	}
	public U2 getMethodsCount() {
		return methods_count;
	}
	public void setMethodsCount(U2 methods_count) {
		this.methods_count = methods_count;
	}
	public U2 getAttributesCount() {
		return attributes_count;
	}
	public void setAttributesCount(U2 attributes_count) {
		this.attributes_count = attributes_count;
	}

}
