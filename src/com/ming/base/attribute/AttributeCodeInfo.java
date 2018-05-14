package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

public class AttributeCodeInfo extends AttributeInfo {
    private U2 attribute_name_index;
    private U4 attribute_length;
    private U2 max_stack;
    private U2 max_locals;
    private U4 code_length;
    private U1 code [];
    private U2 exception_table_length;
    private ExceptionTable exception_table[];
    private U2 attributes_count;
    private AttributeInfo attributes [];


    public AttributeCodeInfo(U2 name_index, ClassFileReader cfr) {
    	attribute_name_index = name_index;
    	attribute_length = cfr.readU4();
    	max_stack = cfr.readU2();
    	max_locals = cfr.readU2();
    	code_length = cfr.readU4();
    	long cl = code_length.getValue();
    	code = new U1[(int) cl];
    	for(int i = 0; i < cl; i ++) {
    		code[i] = cfr.readU1();
    	}

    	exception_table_length = cfr.readU2();
    	int etl = exception_table_length.getValue();
    	for(int i = 0; i < etl; i++) {
    		exception_table[i] = new ExceptionTable(cfr);
    	}
    	attributes_count = cfr.readU2();
    	//attributes

    }



    private class ExceptionTable {
    	U2 start_pc;
    	U2 end_pc;
    	U2 handler_pc;
    	U2 catch_type;

    	public ExceptionTable(ClassFileReader cfr) {
    		start_pc = cfr.readU2();
    		end_pc = cfr.readU2();
    		handler_pc = cfr.readU2();
    		catch_type = cfr.readU2();
    	}


		public U2 getStartPc() {
			return start_pc;
		}
		public void setStartPc(U2 start_pc) {
			this.start_pc = start_pc;
		}
		public U2 getEndPc() {
			return end_pc;
		}
		public void setEndPc(U2 end_pc) {
			this.end_pc = end_pc;
		}
		public U2 getHandlerPc() {
			return handler_pc;
		}
		public void setHandlerPc(U2 handler_pc) {
			this.handler_pc = handler_pc;
		}
		public U2 getCatchType() {
			return catch_type;
		}
		public void setCatchType(U2 catch_type) {
			this.catch_type = catch_type;
		}


    }





	public U2 getAttributeNameIndex() {
		return attribute_name_index;
	}





	public void setAttributeNameIndex(U2 attribute_name_index) {
		this.attribute_name_index = attribute_name_index;
	}





	public U4 getAttributeLength() {
		return attribute_length;
	}





	public void setAttributeLength(U4 attribute_length) {
		this.attribute_length = attribute_length;
	}





	public U2 getMaxStack() {
		return max_stack;
	}





	public void setMaxStack(U2 max_stack) {
		this.max_stack = max_stack;
	}





	public U2 getMaxLocals() {
		return max_locals;
	}





	public void setMaxLocals(U2 max_locals) {
		this.max_locals = max_locals;
	}





	public U4 getCodeLength() {
		return code_length;
	}





	public void setCodeLength(U4 code_length) {
		this.code_length = code_length;
	}





	public U1[] getCode() {
		return code;
	}





	public void setCode(U1[] code) {
		this.code = code;
	}





	public U2 getExceptionTableLength() {
		return exception_table_length;
	}





	public void setExceptionTableLength(U2 exception_table_length) {
		this.exception_table_length = exception_table_length;
	}





	public ExceptionTable[] getExceptionTable() {
		return exception_table;
	}





	public void setExceptionTable(ExceptionTable[] exception_table) {
		this.exception_table = exception_table;
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


	@Override
	public String toString() {
		return "[attribute_name_index: " + attribute_name_index.getValue() + "]\n";
	}
}
