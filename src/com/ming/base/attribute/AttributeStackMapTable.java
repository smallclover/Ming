package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 *
 * @author smallclover
 * JDK6 [Extended Attr] StackMapTable
 *
 */
public class AttributeStackMapTable extends AttributeInfo{
	private U2 attribute_name_index;
	private U4 attribute_length;
	private Raw[] raw;

	public AttributeStackMapTable(U2 name_index, ClassFileReader cfr) {
		attribute_name_index = name_index;
		attribute_length = cfr.readU4();
		long al = attribute_length.getValue();
		raw = new Raw[(int) al];
		for(int i = 0; i < al; i ++) {
			raw[i] = new Raw(cfr);
		}
	}

	private class Raw {
		private U1 frame;// problem
		public Raw(ClassFileReader cfr) {
			frame = cfr.readU1();
		}
	}

	@Override
	public String toString() {
		return "[attribute_name_index]: " + attribute_name_index.getValue() + "\n";
	}
}
