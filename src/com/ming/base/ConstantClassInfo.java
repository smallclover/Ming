package com.ming.base;

/**
 *
 * @author smallclover
 * constant_class_info
 */
public class ConstantClassInfo implements ConstantInfo {
	private int tag = 7;
	private int name_index = 0;
	public ConstantClassInfo(int name_index) {
		this.name_index = name_index;
	}


}
