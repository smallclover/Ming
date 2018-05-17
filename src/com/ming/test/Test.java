package com.ming.test;

import com.ming.base.ConstantInfo;
import com.ming.core.ClassFile;
import com.ming.core.U2;
import com.ming.main.ClassFileParser;


public class Test {

    public static void main(String[] args) {

        //class文件的绝对路径
        String path = "C:\\Users\\nesjuser01\\Desktop\\Simple.class";

        //class文件实体类，可以通过该类来获得class文件的所有属性
        //具体可以获得属性可以参考ClassFile实体类
        ClassFile cf = ClassFileParser.getClassFile(path);

        // 获取magic
        System.out.println(cf.getMagic().toHex());
        // 获取次版本
        System.out.println(cf.getMinorVersion().getValue());
        // 获取主版本
        System.out.println(cf.getMajorVersion().getValue());
        // 获取常量池数量
        System.out.println(cf.getConstantPoolCount().getValue());
        // 获取常量池内容
        ConstantInfo[] ci = cf.getConstantPool().getConstantInfo();

        for (int i = 1; i < ci.length; i ++) {
            if (ci[i] == null) {
                continue;// double 和long的情况会占用两个索引值n和n+1，而n+1是null
            }
            System.out.println(ci[i].getClass().getSimpleName());
            System.out.println(ci[i]);
        }

        // 获取访问标志
        System.out.println(cf.getAccessFlags().getValue());
        // 获取该类
        System.out.println(cf.getThisClass().getValue());
        // 获取父类
        System.out.println(cf.getSuperClass().getValue());
        // 获取接口数量
        System.out.println(cf.getInterfacesCount().getValue());
        // 获取接口
        U2[] interfaces = cf.getInterfaces();
        if(interfaces != null) {
            for (int i = 0; i < interfaces.length; i++) {
                System.out.println(interfaces[i].getValue());
            }
        }

    }
}
