package com.ming.test;

import com.ming.base.ConstantInfo;
import com.ming.core.ClassFile;
import com.ming.main.ClassFileParser;


public class Test {

    public static void main(String[] args) {
        String path = "C:/Users/smallclover/Desktop/Simple.class";
        ClassFile cf = ClassFileParser.getClassFile(path);
        System.out.println(cf.getMagic().toHex());
        ConstantInfo[] ci = cf.getConstantPool().getConstantInfo();

        for (int i = 1; i < ci.length; i ++) {
            System.out.println(ci[i].getClass().getSimpleName());
            System.out.println(ci[i]);
        }
    }
}
