package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 * Java源码行号和机器指令对应关系
 * @author smallclover
 *
 */
public class AttributeLineNumberTableInfo extends AttributeInfo {
	private U2 attribute_name_index;
	private U4 attribute_length;
	private U2 line_number_table_length;
	private LineNumberTable[] row;




	public U2 getAttributeNameIndex() {
		return attribute_name_index;
	}



	public U4 getAttributeLength() {
		return attribute_length;
	}



	public U2 getLineNumberTableLength() {
		return line_number_table_length;
	}



	public LineNumberTable[] getRow() {
		return row;
	}



	public AttributeLineNumberTableInfo(U2 name_index, ClassFileReader cfr) {
		attribute_name_index = name_index;
		attribute_length = cfr.readU4();
		line_number_table_length = cfr.readU2();
		int lntl = line_number_table_length.getValue();
		row = new LineNumberTable[lntl];
		for(int i = 0; i < lntl; i ++) {
			row[i] = new LineNumberTable(cfr);
		}
	}



	public class LineNumberTable {
		private U2 start_pc;
		private U2 line_number;

		public LineNumberTable(ClassFileReader cfr) {
			start_pc = cfr.readU2();
			line_number = cfr.readU2();
		}

		public U2 getStartPc() {
			return start_pc;
		}

		public U2 getLineNumber() {
			return line_number;
		}

	}

}
