package com.ming.base;

import com.ming.base.attribute.AttributeCodeInfo;
import com.ming.base.attribute.AttributeConstantValueInfo;
import com.ming.base.attribute.AttributeLineNumberTableInfo;
import com.ming.base.attribute.AttributeLocalVariableTableInfo;
import com.ming.base.attribute.AttributeSourceFileInfo;
import com.ming.base.attribute.AttributeStackMapTable;
import com.ming.core.ConstantPool;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

public abstract class AttributeInfo {
    // 被声明为 deprecated 的方法和字段
    private static final String ATTRIBUTE_Deprecated_info = "Deprecated";
    // Java 代码编译成的字节码指令
    private static final String ATTRIBUTE_Code_info = "Code";
    // 标示方法或字段是由编译器自动生成的
    private static final String ATTRIBUTE_Synthetic_info = "Synthetic";
    // 记录源文件的名称
    private static final String ATTRIBUTE_SourceFile_info = "SourceFile";
    // final 关键字定义的常量值
    private static final String ATTRIBUTE_ConstantValue_info = "ConstantValue";
    // 方法抛出的异常
    private static final String ATTRIBUTE_Exceptions_info = "Exceptions";
    // Java 源代码的行号与字节码指令的对应关系
    private static final String ATTRIBUTE_LineNumberTable_info = "LineNumberTable";
    // 方法的局部变量描述
    private static final String ATTRIBUTE_LocalVariableTable_info = "LocalVariableTable";

    private static final String ATTRIBUTE_StackMapTable_info = "StackMapTable";

    public AttributeInfo() {
    }

    public static AttributeInfo getSpecificAttributeInfo(U2 name_index, String name, ClassFileReader cfr, ConstantPool cp) {
        switch (name) {
            case ATTRIBUTE_Code_info :
                return new AttributeCodeInfo(name_index, cfr, cp);
            case ATTRIBUTE_SourceFile_info:
                return new AttributeSourceFileInfo(name_index, cfr);
            case ATTRIBUTE_ConstantValue_info:
                return new AttributeConstantValueInfo(name_index, cfr);
            case ATTRIBUTE_LineNumberTable_info:
                return new AttributeLineNumberTableInfo(name_index, cfr);
            case ATTRIBUTE_LocalVariableTable_info:
                return new AttributeLocalVariableTableInfo(name_index, cfr);
            case ATTRIBUTE_StackMapTable_info:
            	return new AttributeStackMapTable(name_index, cfr);
            default:

            	// JDK6 [Extended Attr] StackMapTable              Code-Attribute
                throw new RuntimeException("cant find specific attribute");
        }
    }

    //public abstract void read(ClassFileReader cfr);
}
