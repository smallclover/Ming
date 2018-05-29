package com.ming.util;

import com.ming.base.AttributeInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.attribute.*;
import com.ming.base.constant.*;
import com.ming.core.Opcode;
import com.ming.core.U1;

/**
 * class文件输出格式化工具类
 */
public class PrintFormatUtils {

    /**
     * 根据指定的常量值类型，打印相应的数据
     *
     * @param ci  常量值
     * @param str 该常量值的索引指向的常量的值
     * @return
     */
    public static String printConstant(ConstantInfo ci, String str) {
        String format = null;
        if (ci instanceof ConstantUtf8Info) {
            ConstantUtf8Info info = (ConstantUtf8Info) ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[length]: " + info.getLength().getValue() + "\n"
                    + "[bytes]: " + str + "\n";
        } else if (ci instanceof ConstantClassInfo) {
            ConstantClassInfo info = (ConstantClassInfo) ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[name_index]: " + info.getNameIndex().getValue() + "-" + str + "\n";
        } else if (ci instanceof ConstantNameAndTypeInfo) {
            ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo) ci;
            String[] value = str.split("-");
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[name_index]: " + info.getNameIndex().getValue() + "-" + value[0] + "\n"
                    + "[descriptor_index]: " + info.getDescriptorIndex().getValue() + "-" + value[1] + "\n";
        } else if (ci instanceof ConstantStringInfo) {
            ConstantStringInfo info = (ConstantStringInfo) ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[string_index]: " + info.getStringIndex().getValue() + "-" + str + "\n";
        } else if (ci instanceof ConstantMethodrefInfo) {
            ConstantMethodrefInfo info = (ConstantMethodrefInfo) ci;
            String[] value = str.split("-");
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[class_index]: " + info.getClassIndex().getValue() + "-" + value[0] + "\n"
                    + "[name_and_type_index]: " + info.getNameAndTypeIndex().getValue() + "-" + value[1] + "-" + value[2] + "\n";
        } else if (ci instanceof ConstantLongInfo) {
            ConstantLongInfo info = (ConstantLongInfo) ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[value]: " + info.getHighBytes().toHex() + info.getLowBytes().toHex() + "\n";
        } else if (ci instanceof ConstantDoubleInfo) {
            ConstantDoubleInfo info = (ConstantDoubleInfo) ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[value]: " + info.getHighBytes().toHex() + info.getLowBytes().toHex() + "\n";
        }

        return format;
    }

    /**
     *
     * @param ai
     * @param str
     * @return
     */
    public static String printAttribute(AttributeInfo ai, String str) {
        String format = null;
        if (ai instanceof AttributeConstantValueInfo) {
            AttributeConstantValueInfo info = (AttributeConstantValueInfo) ai;
            String[] value = str.split("-");
            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + value[0] + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[constantvalue_index]: " + info.getConstantValueIndex().getValue() + "-" + value[1] + "\n";
        }

        if (ai instanceof AttributeSourceFileInfo) {
            AttributeSourceFileInfo info = (AttributeSourceFileInfo) ai;
            String[] value = str.split("-");
            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + value[0] + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[sourcefile_index]: " + info.getSourcefileIndex().getValue() + "-" + value[1] + "\n";
        }

        if (ai instanceof AttributeLineNumberTableInfo) {
            AttributeLineNumberTableInfo info = (AttributeLineNumberTableInfo) ai;
            String[] value = str.split("-");

            StringBuffer sb = new StringBuffer();
            AttributeLineNumberTableInfo.LineNumberTable[] row =  info.getRow();
            for(int i = 0; i < info.getRow().length; i ++) {
                sb.append("[start_pc]: " + row[i].getStartPc().getValue() + "\n");
                sb.append("[line_number]: " + row[i].getLineNumber().getValue() + "\n");
            }

            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + value[0] + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[line_number_table_length]: " + info.getLineNumberTableLength().getValue() + "\n"
                    + sb.toString() + "\n";
        }

        if (ai instanceof AttributeCodeInfo) {
            AttributeCodeInfo info = (AttributeCodeInfo) ai;
            String [] value = str.split("-");

            U1[] code = info.getCode();

            StringBuffer sb_code = new StringBuffer();
            for(int i = 0; i < code.length; i ++) {
                String opCode = Opcode.getOpcode(code[i].getValue());
                sb_code.append(code[i].getValue() + "-" + opCode + "\n");
            }
            String etl = "[exception_table_length]: " + info.getExceptionTableLength().getValue() + "\n";

            AttributeCodeInfo.ExceptionTable[] exceptionTable = info.getExceptionTable();

            StringBuffer sb_exception_table = new StringBuffer();
            for(int i = 0; i < exceptionTable.length; i ++) {
                sb_exception_table.append("[" + i + "]");
                sb_exception_table.append("[start_pc]: " + exceptionTable[i].getStartPc().getValue() + "\n");
                sb_exception_table.append("[end_pc]: " + exceptionTable[i].getEndPc().getValue() + "\n");
                sb_exception_table.append("[handler_pc]: " + exceptionTable[i].getHandlerPc().getValue() + "\n");
                sb_exception_table.append("[catch_type]: " + exceptionTable[i].getCatchType().getValue() + "\n");

            }

            String ac  = "[attributes_count]: " + info.getAttributesCount().getValue() + "\n";

            AttributeInfo[] attibureChild = info.getAttributes();
            String formatChild = null;
            for (int i = 0; i < attibureChild.length; i++) {
                formatChild = printAttribute(attibureChild[i], value[1]);
            }
            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + value[0] + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[max_stack]: " + info.getMaxStack().getValue() + "\n"
                    + "[max_locals]: " + info.getMaxLocals().getValue() + "\n"
                    + "[code_length]: " + info.getCodeLength().getValue() + "\n"
                    + "[code]: " + "\n"
                    + sb_code.toString()
                    + etl
                    + sb_exception_table.toString()
                    + ac
                    + formatChild + "\n";
        }

        return format;
    }
}
