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
	public U2 getMinor_version() {
		return minor_version;
	}
	public void setMinor_version(U2 minor_version) {
		this.minor_version = minor_version;
	}
	public U2 getMajor_version() {
		return major_version;
	}
	public void setMajor_version(U2 major_version) {
		this.major_version = major_version;
	}
	public U2 getConstant_pool_count() {
		return constant_pool_count;
	}
	public void setConstant_pool_count(U2 constant_pool_count) {
		this.constant_pool_count = constant_pool_count;
	}
	public U2 getAccess_flags() {
		return access_flags;
	}
	public void setAccess_flags(U2 access_flags) {
		this.access_flags = access_flags;
	}
	public U2 getThis_class() {
		return this_class;
	}
	public void setThis_class(U2 this_class) {
		this.this_class = this_class;
	}
	public U2 getSuper_class() {
		return super_class;
	}
	public void setSuper_class(U2 super_class) {
		this.super_class = super_class;
	}
	public U2 getInterfaces_count() {
		return interfaces_count;
	}
	public void setInterfaces_count(U2 interfaces_count) {
		this.interfaces_count = interfaces_count;
	}
	public U2 getFields_count() {
		return fields_count;
	}
	public void setFields_count(U2 fields_count) {
		this.fields_count = fields_count;
	}
	public U2 getMethods_count() {
		return methods_count;
	}
	public void setMethods_count(U2 methods_count) {
		this.methods_count = methods_count;
	}
	public U2 getAttributes_count() {
		return attributes_count;
	}
	public void setAttributes_count(U2 attributes_count) {
		this.attributes_count = attributes_count;
	}

}
