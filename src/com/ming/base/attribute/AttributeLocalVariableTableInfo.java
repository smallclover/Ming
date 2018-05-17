package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 * 局部变量表
 * @author smallclover
 *
 */
public class AttributeLocalVariableTableInfo extends AttributeInfo{
	private U2 attribute_name_index;
	private U4 attribute_length;
	private U2 line_variable_table_length;
	private LocalVariableTable[] table;

	public AttributeLocalVariableTableInfo(U2 name_index, ClassFileReader cfr) {
		attribute_name_index = name_index;
		attribute_length = cfr.readU4();
		line_variable_table_length = cfr.readU2();
		int lvtl = line_variable_table_length.getValue();
		table = new LocalVariableTable[lvtl];
		for(int i = 0; i < lvtl; i ++) {
			table[i] = new LocalVariableTable(cfr);
		}
	}





	public U2 getAttributeNameIndex() {
		return attribute_name_index;
	}




	public U4 getAttributeLength() {
		return attribute_length;
	}




	public U2 getLine_variableTableLength() {
		return line_variable_table_length;
	}




	public LocalVariableTable[] getTable() {
		return table;
	}



	private class LocalVariableTable {
		private U2 start_pc;
		private U2 length;
		private U2 name_index;
		private U2 descriptor_index;
		private U2 index;

		public LocalVariableTable(ClassFileReader cfr) {
			start_pc = cfr.readU2();
			length = cfr.readU2();
			name_index = cfr.readU2();
			descriptor_index = cfr.readU2();
			index = cfr.readU2();
		}

		public U2 getStartPc() {
			return start_pc;
		}

		public U2 getLength() {
			return length;
		}

		public U2 getNameIndex() {
			return name_index;
		}

		public U2 getDescriptorIndex() {
			return descriptor_index;
		}

		public U2 getIndex() {
			return index;
		}

	}

	@Override
	public String toString() {
		String parent_info = "[attribute_name_index: " + attribute_name_index.getValue() + "]\n"
				+ "[attribute_length: " + attribute_length.getValue() + "]\n"
				+ "[line_variable_table_length: " + line_variable_table_length.getValue() + "]\n";
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < table.length; i ++) {
			sb.append("[start_pc: " + table[i].getStartPc().getValue() + "]\n");
			sb.append("[length: " + table[i].getLength().getValue() + "]\n");
			sb.append("[name_index: " + table[i].getNameIndex().getValue() + "]\n");
			sb.append("[descriptor_index: " + table[i].getDescriptorIndex().getValue() + "]\n");
			sb.append("[index: " + table[i].getIndex().getValue() + "]\n");
		}

		return parent_info + sb.toString();
	}

}
