package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.constant.ConstantUtf8Info;
import com.ming.core.ConstantPool;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 *
 * @author smallclover
 * 只存在于method_info结构中。Code属性存放字节码等与方法相关的信息
 *
 *
 *
 * 不是必须的属性，如果该方法是抽象方法将不会拥有该属性
 * 需要注意的是JDK8种接口可以添加default方法，default方法可以拥有方法体，
 * 所以是有Code属性
 *
 */
public class AttributeCodeInfo extends AttributeInfo {
	// 属性的名字的索引，指向常量池ConstantUtf8Info，值固定为[Code]
    private U2 attribute_name_index;
    // Code属性的长度，表示后面紧跟着的N个字节用来表示该属性的信息
    private U4 attribute_length;
    // 表示局部变量表所需要的存储空间大小
    private U2 max_stack;
    // 操作数栈深度的最大值
    private U2 max_locals;
    // 表示机器码的长度
    private U4 code_length;
    // 机器码
    private U1 code [];
    // 异常信息表的长度
    private U2 exception_table_length;
    // 异常信息
    private ExceptionTable exception_table[];
    // 表示Code属性表的其他属性的数目
    private U2 attributes_count;

    private AttributeInfo attributes [];


    public AttributeCodeInfo(U2 name_index, ClassFileReader cfr, ConstantPool cp) {
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
    	exception_table = new ExceptionTable[etl];
    	for(int i = 0; i < etl; i++) {
    		exception_table[i] = new ExceptionTable(cfr);
    	}
    	attributes_count = cfr.readU2();
    	int ac = attributes_count.getValue();
    	attributes = new AttributeInfo[ac];
    	for(int i = 0; i < ac; i ++) {
    		U2 inner_attribute_name_index = cfr.readU2();
            ConstantInfo[] ci = cp.getConstantInfo();
            attributes[i] = AttributeInfo.getSpecificAttributeInfo(inner_attribute_name_index, ((ConstantUtf8Info)ci[inner_attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
            // System.out.println(attributes[i].getClass().getSimpleName());
            // System.out.println(attributes[i]);
    	}

    }


    /**
     *
     * @author smallclover
     * 异常的跳转信息
     * 处理的是
     * try{}catch(){}类型的异常
     */
    private class ExceptionTable {
    	private U2 start_pc;
    	private U2 end_pc;
    	private U2 handler_pc;
    	private U2 catch_type;

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
		String parent_info = "[attribute_name_index]: " + attribute_name_index.getValue() + "\n"
				+ "[attribute_length]: " + attribute_length.getValue() + "\n"
				+ "[max_stack]: " + max_stack.getValue() + "\n"
				+ "[max_locals]: " + max_locals.getValue() + "\n"
				+ "[code_length]: " + code_length.getValue() + "\n";
		StringBuffer sb_code = new StringBuffer();
		for(int i = 0; i < code.length; i ++) {
			sb_code.append(code[i].getValue() + "\n");
		}
		String etl = "[exception_table_length]: " + exception_table_length.getValue() + "\n";
		StringBuffer sb_exception_table = new StringBuffer();
		for(int i = 0; i < exception_table.length; i ++) {
			sb_exception_table.append("[" + i + "]");
			sb_exception_table.append("[start_pc]: " + exception_table[i].getStartPc().getValue() + "\n");
			sb_exception_table.append("[end_pc]: " + exception_table[i].getEndPc().getValue() + "\n");
			sb_exception_table.append("[handler_pc]: " + exception_table[i].getHandlerPc().getValue() + "\n");
			sb_exception_table.append("[catch_type]: " + exception_table[i].getCatchType().getValue() + "\n");

		}
		String ac  = "[attributes_count]: " + attributes_count.getValue() + "\n";
		return parent_info + sb_code.toString() + etl + sb_exception_table.toString() + ac;
	}

}
