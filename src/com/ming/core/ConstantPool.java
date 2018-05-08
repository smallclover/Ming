package com.ming.core;

import java.util.ArrayList;
import java.util.List;

import com.ming.base.ConstantClassInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.ConstantUtf8Info;

/**
 *
 * @author smallclover
 *
 * constant pool
 */
public class ConstantPool {
	private ConstantUtf8Info constant_utf8_info;
	private ConstantClassInfo constant_class_info;

	private List<ConstantInfo> constant_pool = new ArrayList<ConstantInfo>();

	public List<ConstantInfo> getConstantPool() {
		return constant_pool;
	}




}
