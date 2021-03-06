package com.ming.base.attribute;

import com.ming.base.AttributeInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.constant.ConstantUtf8Info;
import com.ming.core.*;
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
            String name = ((ConstantUtf8Info)ci[inner_attribute_name_index.getValue()]).convertHexToString();
            attributes[i] = AttributeInfo.getSpecificAttributeInfo(inner_attribute_name_index, name, cfr, cp);
    	}

    }


    /**
     *
     * @author smallclover
     * 异常的跳转信息
     * 处理的是
     * try{}catch(){}类型的异常
     */
    public class ExceptionTable {
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
		public U2 getEndPc() {
			return end_pc;
		}
		public U2 getHandlerPc() {
			return handler_pc;
		}
		public U2 getCatchType() {
			return catch_type;
		}

    }


	public U2 getAttributeNameIndex() {
		return attribute_name_index;
	}

	public U4 getAttributeLength() {
		return attribute_length;
	}

	public U2 getMaxStack() {
		return max_stack;
	}

	public U2 getMaxLocals() {
		return max_locals;
	}

	public U4 getCodeLength() {
		return code_length;
	}

	public U1[] getCode() {
		return code;
	}

	public U2 getExceptionTableLength() {
		return exception_table_length;
	}

	public ExceptionTable[] getExceptionTable() {
		return exception_table;
	}

	public U2 getAttributesCount() {
		return attributes_count;
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}

}
