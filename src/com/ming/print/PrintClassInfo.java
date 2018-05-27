package com.ming.print;

import com.ming.base.AttributeInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.FieldInfo;
import com.ming.base.MethodInfo;
import com.ming.base.constant.*;
import com.ming.core.ClassFile;
import com.ming.core.U2;
import com.ming.main.ClassFileParser;


public class PrintClassInfo {

    private static ClassFile cf;
    private static ConstantInfo[] ci;

    /**
     * 初始化class文件路径
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
            System.out.println(printByConstantTypeAndValue(ci[i], getValueByIndex(i)));
        }

        // 获取访问标志
        System.out.println("[access_flags]: " + cf.getAccessFlags().getValue());
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
                System.out.println("[" + i + "]: " + interfaces[i].getValue());
            }
        }

        System.out.println();

        // 获取属性数量
        System.out.println("[fields_count]: " + cf.getFieldsCount().getValue());
        // 获取属性详细信息
        System.out.println("[fields]: \n");
        FieldInfo[] fi =  cf.getFields();
        for (int i = 0; i < fi.length; i++) {
            System.out.println("[field " + (i + 1) + " ]: ");
            System.out.println("[access_flags]: " + fi[i].getAccessFlags().getValue());
            System.out.println("[name_index]: " + fi[i].getNameIndex().getValue());
            System.out.println("[descriptor_index]: " + fi[i].getDescriptorIndex().getValue());
            System.out.println("[attributes_count]: " + fi[i].getAttributesCount().getValue() + "\n");
            AttributeInfo[] ai = fi[i].getAttributes();
            for (int j = 0; j < ai.length; j++) {
                System.out.println("[" + (j + 1) + "]: " + ai[j].getClass().getSimpleName());
                System.out.println(ai[j]);
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
            System.out.println("[access_flags]: " + mi[i].getAccessFlags().getValue());
            System.out.println("[name_index]: " + mi[i].getNameIndex().getValue());
            System.out.println("[descriptor_index]: " + mi[i].getDescriptorIndex().getValue());
            System.out.println("[attributes_count]: " + mi[i].getAttributesCount().getValue() + "\n");

            // todo 类似code属性之下还有其他的属性，也需要列举出来
            AttributeInfo[] ai = mi[i].getAttributes();
            for (int j = 0; j < ai.length; j++) {
                System.out.println("[" + (j + 1) + "]: " + ai[j].getClass().getSimpleName());
                System.out.println(ai[j]);
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
            System.out.println(ai[i]);
        }
    }

    /**
     * 根据给定的索引获取常量项的最终值
     */
    private static String getValueByIndex(int index) {
        String result = null;
        if (ci[index] instanceof ConstantUtf8Info) {
            result = ((ConstantUtf8Info)ci[index]).convertHexToString();
        } else if (ci[index] instanceof ConstantClassInfo) {
            int classNameIndex = ((ConstantClassInfo)ci[index]).getNameIndex().getValue();
            result = getValueByIndex(classNameIndex);
        } else if (ci[index] instanceof ConstantNameAndTypeInfo) {
            int nameIndex = ((ConstantNameAndTypeInfo) ci[index]).getNameIndex().getValue();
            String nameStr = getValueByIndex(nameIndex);
            int descriptorIndex = ((ConstantNameAndTypeInfo) ci[index]).getDescriptorIndex().getValue();
            String descriptorStr = getValueByIndex(descriptorIndex);
            result = nameStr + "-" + descriptorStr;
        } else if (ci[index] instanceof ConstantStringInfo) {
            int stringIndex = ((ConstantStringInfo)ci[index]).getStringIndex().getValue();
            result = getValueByIndex(stringIndex);
        } else if (ci[index] instanceof ConstantMethodrefInfo) {
            int methodClassIndex = ((ConstantMethodrefInfo)ci[index]).getClassIndex().getValue();
            String methodClassStr = getValueByIndex(methodClassIndex);
            int methodDescriptorIndex = ((ConstantMethodrefInfo)ci[index]).getNameAndTypeIndex().getValue();
            String methodDescriptorStr = getValueByIndex(methodDescriptorIndex);

            result = methodClassStr + "-" + methodDescriptorStr;
        }
        return result;
    }

    /**
     * 根据指定的常量值类型，打印相应的数据
     * @param ci 常量值
     * @param str 该常量值的索引指向的常量的值
     * @return
     */
    private static String printByConstantTypeAndValue(ConstantInfo ci, String str) {
        String format = null;
        if (ci instanceof ConstantUtf8Info) {
            ConstantUtf8Info info = (ConstantUtf8Info)ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[length]: " + info.getLength().getValue() + "\n"
                    + "[bytes]: " + str + "\n";
        } else if (ci instanceof ConstantClassInfo) {
            ConstantClassInfo info = (ConstantClassInfo)ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[name_index]: " + info.getNameIndex().getValue() + "-" + str + "\n";
        } else if (ci instanceof ConstantNameAndTypeInfo) {
            ConstantNameAndTypeInfo info = (ConstantNameAndTypeInfo)ci;
            String[] value = str.split("-");
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[name_index]: " + info.getNameIndex().getValue() + "-" + value[0] + "\n"
                    + "[descriptor_index]: " + info.getDescriptorIndex().getValue() + "-" + value[1] + "\n";
        } else if (ci instanceof ConstantStringInfo) {
            ConstantStringInfo info = (ConstantStringInfo)ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[string_index]: " + info.getStringIndex().getValue() + "-" + str + "\n";
        } else if (ci instanceof ConstantMethodrefInfo) {
            ConstantMethodrefInfo info = (ConstantMethodrefInfo)ci;
            String[] value = str.split("-");
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[class_index]: " + info.getClassIndex().getValue() + "-" + value[0] + "\n"
                    + "[name_and_type_index]: " + info.getNameAndTypeIndex().getValue() + "-" + value[1] + "-" + value[2] + "\n";
        } else if (ci instanceof ConstantLongInfo) {
            ConstantLongInfo info = (ConstantLongInfo)ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[value]: " + info.getHighBytes().toHex() + info.getLowBytes().toHex() + "\n";
        } else if (ci instanceof ConstantDoubleInfo) {
            ConstantDoubleInfo info = (ConstantDoubleInfo)ci;
            format = "[tag]: " + info.getTag().getValue() + "\n"
                    + "[value]: " + info.getHighBytes().toHex() + info.getLowBytes().toHex() + "\n";
        }

        return format;
    }

}
