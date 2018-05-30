package com.ming.print;

import com.ming.base.AttributeInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.FieldInfo;
import com.ming.base.MethodInfo;
import com.ming.base.attribute.*;
import com.ming.base.constant.*;
import com.ming.core.*;
import com.ming.main.ClassFileParser;


public class PrintClassInfo {

    private static ClassFile cf;
    private static ConstantInfo[] ci;

    /**
     * 初始化class文件路径
     *
     * @param filePath class文件路径
     */
    public static void init(String filePath) {
        //class文件实体类，可以通过该类来获得class文件的所有属性
        //具体可以获得属性可以参考ClassFile实体类
        cf = ClassFileParser.getClassFile(filePath);
        ci = cf.getConstantPool().getConstantInfo();
    }

    /**
     * 一次性打印出class文件的所有的信息
     */
    public static void printAll() {
        // 获取magic
        System.out.println("[magic]: " + cf.getMagic().toHex());
        // 获取次版本
        System.out.println("[minor_version]: " + cf.getMinorVersion().getValue());
        // 获取主版本
        System.out.println("[major_version]: " + cf.getMajorVersion().getValue());
        // 获取常量池数量
        System.out.println("[constant_pool_count]: " + cf.getConstantPoolCount().getValue());

        System.out.println("[constant_pool]: ");

        for (int i = 1; i < ci.length; i++) {
            if (ci[i] == null) {
                continue;// double 和long的情况会占用两个索引值n和n+1，而n+1是null
            }
            System.out.println("[" + i + "]: " + ci[i].getClass().getSimpleName());
            // 打印该常量项的信息
            System.out.println(printConstant(ci[i], getConstantValueByIndex(i)));
        }

        // 获取访问标志
        int accessFlags_class = cf.getAccessFlags().getValue();
        System.out.println("[access_flags]: " + accessFlags_class + "-" + Modifier.getModifier().getAccessFlag(accessFlags_class));
        // 获取该类
        System.out.println("[this_class]: " + cf.getThisClass().getValue());
        // 获取父类
        System.out.println("[super_class]: " + cf.getSuperClass().getValue());
        // 获取接口数量
        System.out.println("[interfaces_count]: " + cf.getInterfacesCount().getValue());

        System.out.println("[interfaces]: ");

        // 获取接口
        U2[] interfaces = cf.getInterfaces();
        if (interfaces != null) {
            for (int i = 0; i < interfaces.length; i++) {
                int nameIndex = interfaces[i].getValue();
                String name = getConstantValueByIndex(nameIndex);
                System.out.println("[" + i + "]: " + nameIndex + "-" + name);
            }
        }

        System.out.println();

        // 获取字段数量
        System.out.println("[fields_count]: " + cf.getFieldsCount().getValue());
        // 获取字段详细信息
        System.out.println("[fields]: \n");
        FieldInfo[] fi = cf.getFields();
        for (int i = 0; i < fi.length; i++) {
            System.out.println("[field " + (i + 1) + " ]: ");
            int accessflags_field = fi[i].getAccessFlags().getValue();
            System.out.println("[access_flags]: " + accessflags_field + "-" + Modifier.getModifier().getAccessFlag(accessflags_field));
            int nameIndex = fi[i].getNameIndex().getValue();
            System.out.println("[name_index]: " + nameIndex + "-" + getConstantValueByIndex(nameIndex));
            int descriptorIndex = fi[i].getDescriptorIndex().getValue();
            System.out.println("[descriptor_index]: " + descriptorIndex + "-" + getConstantValueByIndex(descriptorIndex));
            System.out.println("[attributes_count]: " + fi[i].getAttributesCount().getValue() + "\n");
            AttributeInfo[] ai = fi[i].getAttributes();
            for (int j = 0; j < ai.length; j++) {
                System.out.println("[" + (j + 1) + "]: " + ai[j].getClass().getSimpleName());
                //System.out.println(ai[j]);
                System.out.println(printAttribute(ai[j]));
            }
        }

        System.out.println();

        // 获取方法数量
        System.out.println("[methods_count]: " + cf.getMethodsCount().getValue());
        // 获取方法的具体信息
        System.out.println("[methods]: \n");
        MethodInfo[] mi = cf.getMethods();
        for (int i = 0; i < mi.length; i++) {
            System.out.println("[method " + (i + 1) + " ]: ");
            int accessflags_method = mi[i].getAccessFlags().getValue();
            System.out.println("[access_flags]: " + accessflags_method + "-" + Modifier.getModifier().getAccessFlag(accessflags_method));
            int nameIndex = mi[i].getNameIndex().getValue();
            System.out.println("[name_index]: " + nameIndex + "-" + getConstantValueByIndex(nameIndex));
            int descriptorIndex = mi[i].getDescriptorIndex().getValue();
            System.out.println("[descriptor_index]: " + descriptorIndex + "-" + getConstantValueByIndex(descriptorIndex));
            System.out.println("[attributes_count]: " + mi[i].getAttributesCount().getValue() + "\n");

            // todo 类似code属性之下还有其他的属性，也需要列举出来
            AttributeInfo[] ai = mi[i].getAttributes();
            for (int j = 0; j < ai.length; j++) {
                System.out.println("[" + (j + 1) + "]: " + ai[j].getClass().getSimpleName());
                //System.out.println(ai[j]);
                System.out.println(printAttribute(ai[j]));
            }
        }

        System.out.println();

        // 获取属性数量
        System.out.println("[attributes_count]: " + cf.getAttributesCount().getValue());
        System.out.println("[attributes]: \n");
        // 获取属性的具体信息
        AttributeInfo[] ai = cf.getAttributes();
        for (int i = 0; i < ai.length; i++) {
            System.out.println("[" + i + "]: " + ai[i].getClass().getSimpleName());
            //System.out.println(ai[i]);
            System.out.println(printAttribute(ai[i]));
        }
    }

    /**
     * 根据给定的索引获取常量项的最终值
     */
    private static String getConstantValueByIndex(int index) {
        String result = null;
        if (ci[index] instanceof ConstantUtf8Info) {
            result = ((ConstantUtf8Info) ci[index]).convertHexToString();
        } else if (ci[index] instanceof ConstantClassInfo) {
            int classNameIndex = ((ConstantClassInfo) ci[index]).getNameIndex().getValue();
            result = getConstantValueByIndex(classNameIndex);
        } else if (ci[index] instanceof ConstantNameAndTypeInfo) {
            int nameIndex = ((ConstantNameAndTypeInfo) ci[index]).getNameIndex().getValue();
            String nameStr = getConstantValueByIndex(nameIndex);
            int descriptorIndex = ((ConstantNameAndTypeInfo) ci[index]).getDescriptorIndex().getValue();
            String descriptorStr = getConstantValueByIndex(descriptorIndex);
            result = nameStr + "-" + descriptorStr;
        } else if (ci[index] instanceof ConstantStringInfo) {
            int stringIndex = ((ConstantStringInfo) ci[index]).getStringIndex().getValue();
            result = getConstantValueByIndex(stringIndex);
        } else if (ci[index] instanceof ConstantMethodrefInfo) {
            int methodClassIndex = ((ConstantMethodrefInfo) ci[index]).getClassIndex().getValue();
            String methodClassStr = getConstantValueByIndex(methodClassIndex);
            int methodDescriptorIndex = ((ConstantMethodrefInfo) ci[index]).getNameAndTypeIndex().getValue();
            String methodDescriptorStr = getConstantValueByIndex(methodDescriptorIndex);

            result = methodClassStr + "-" + methodDescriptorStr;
        }
        return result;
    }


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
     * 根据属性类型来获得属性索引的值
     * @param ai
     * @return
     */
    private static String  getAttributeValue(AttributeInfo ai) {
        String result = null;
        if (ai instanceof AttributeConstantValueInfo) {
            AttributeConstantValueInfo acvi = (AttributeConstantValueInfo) ai;
            int name_index = acvi.getAttributeNameIndex().getValue();
            String name = getConstantValueByIndex(name_index);
            int att_constantvalue_index =  acvi.getConstantValueIndex().getValue();
            String attConstantValue = getConstantValueByIndex(att_constantvalue_index);

            result = name + "-" + attConstantValue;
        } else if (ai instanceof AttributeLineNumberTableInfo) {
            AttributeLineNumberTableInfo alnti = (AttributeLineNumberTableInfo) ai;
            int attribute_name_index = alnti.getAttributeNameIndex().getValue();
            String attributeName = getConstantValueByIndex(attribute_name_index);
            result = attributeName;
        } else if (ai instanceof AttributeLocalVariableTableInfo) {
            AttributeLocalVariableTableInfo alvti = (AttributeLocalVariableTableInfo) ai;
            int attribute_name_index = alvti.getAttributeNameIndex().getValue();
            String attributeName = getConstantValueByIndex(attribute_name_index);
            result = attributeName;
        } else if (ai instanceof AttributeSourceFileInfo) {
            AttributeSourceFileInfo asfi = (AttributeSourceFileInfo) ai;
            int attribute_name_index = asfi.getAttributeNameIndex().getValue();
            String attributeName = getConstantValueByIndex(attribute_name_index);
            int source_file_index = asfi.getSourcefileIndex().getValue();
            String sourceFile = getConstantValueByIndex(source_file_index);
            result = attributeName + "-" + sourceFile;
        } else if (ai instanceof AttributeCodeInfo) {
            AttributeCodeInfo aci = (AttributeCodeInfo) ai;
            int attribute_name_index = aci.getAttributeNameIndex().getValue();
            String attributeName = getConstantValueByIndex(attribute_name_index);
            result = attributeName;
        }

        return result;
    }


    /**
     *
     * @param ai
     * @return
     */
    public static String printAttribute(AttributeInfo ai) {
        String str = getAttributeValue(ai);
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

            StringBuffer sb = new StringBuffer();
            AttributeLineNumberTableInfo.LineNumberTable[] row =  info.getRow();
            for(int i = 0; i < info.getRow().length; i ++) {
                sb.append("[row " + i + "]: " + "\n");
                sb.append("[start_pc]: " + row[i].getStartPc().getValue() + "\n");
                sb.append("[line_number]: " + row[i].getLineNumber().getValue() + "\n");
            }

            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + str + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[line_number_table_length]: " + info.getLineNumberTableLength().getValue() + "\n"
                    + sb.toString() + "\n";
        }

        if (ai instanceof AttributeLocalVariableTableInfo) {
            AttributeLocalVariableTableInfo info = (AttributeLocalVariableTableInfo) ai;
            StringBuffer sb = new StringBuffer();

            AttributeLocalVariableTableInfo.LocalVariableTable [] localVariableTable = info.getTable();
            for (int i = 0; i < localVariableTable.length; i++) {
                sb.append("[" + i + "]: " + "\n");
                sb.append("[start_pc]: " + localVariableTable[i].getStartPc().getValue() + "\n");
                sb.append("[length]: " + localVariableTable[i].getLength().getValue() + "\n");
                int name_index = localVariableTable[i].getNameIndex().getValue();
                String name = getConstantValueByIndex(name_index);
                sb.append("[name_index]: " + name_index + "-" + name + "\n");
                int descriptor_index = localVariableTable[i].getDescriptorIndex().getValue();
                String descriptor = getConstantValueByIndex(descriptor_index);
                sb.append("[descriptor_index]: " + descriptor_index + "-" + descriptor + "\n");
                sb.append("[index]: " + localVariableTable[i].getIndex().getValue() + "\n");
            }

            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + str + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[line_variable_table_length]: " + info.getLineVariableTableLength().getValue() + "\n"
                    + sb.toString();
        }

        if (ai instanceof AttributeCodeInfo) {
            AttributeCodeInfo info = (AttributeCodeInfo) ai;

            U1[] code = info.getCode();

            StringBuffer sb_code = new StringBuffer();
            sb_code.append("[code]: " + "\n");
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

            AttributeInfo[] attributeChild = info.getAttributes();
            StringBuffer sb_attribute = new StringBuffer();
            for (int i = 0; i < attributeChild.length; i++) {
                 sb_attribute.append(printAttribute(attributeChild[i]));
            }
            format = "[attribute_name_index]: " + info.getAttributeNameIndex().getValue() + "-" + str + "\n"
                    + "[attribute_length]: " + info.getAttributeLength().getValue() + "\n"
                    + "[max_stack]: " + info.getMaxStack().getValue() + "\n"
                    + "[max_locals]: " + info.getMaxLocals().getValue() + "\n"
                    + "[code_length]: " + info.getCodeLength().getValue() + "\n"
                    + "[code]: " + "\n"
                    + sb_code.toString()
                    + etl
                    + sb_exception_table.toString()
                    + ac
                    + sb_attribute.toString();
        }

        return format;
    }

}
