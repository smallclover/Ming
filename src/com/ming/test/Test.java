package com.ming.test;

import com.ming.base.ConstantInfo;
import com.ming.core.ClassFile;
import com.ming.main.ClassFileParser;


public class Test {

    public static void main(String[] args) {
        //class文件的绝对路径
        String path = "C:/Users/smallclover/Desktop/Simple.class";

        //class文件实体类，可以通过该类来获得class文件的所有属性
        //具体可以获得属性可以参考ClassFile实体类
        ClassFile cf = ClassFileParser.getClassFile(path);


        System.out.println(cf.getMagic().toHex());
        ConstantInfo[] ci = cf.getConstantPool().getConstantInfo();

        for (int i = 1; i < ci.length; i ++) {
            System.out.println(ci[i].getClass().getSimpleName());
            System.out.println(ci[i]);
        }
    }
}
