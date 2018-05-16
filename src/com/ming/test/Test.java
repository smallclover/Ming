package com.ming.test;

import com.ming.core.ClassFile;
import com.ming.main.ClassFileParser;


public class Test {

    public static void main(String[] args) {
        //class文件的绝对路径
        String path = "D:/tools/pleiades/workspace/Simple.class";

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
/*        ConstantInfo[] ci = cf.getConstantPool().getConstantInfo();

        for (int i = 1; i < ci.length; i ++) {
            System.out.println(ci[i].getClass().getSimpleName());
            System.out.println(ci[i]);
        }*/


    }
}
