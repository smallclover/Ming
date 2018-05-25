package com.ming.test;

import com.ming.print.PrintClassInfo;

public class Test {
    public static void main(String[] args) {
        //class文件的绝对路径
        String path = "C:\\Users\\smallclover\\Desktop\\Simple.class";
        PrintClassInfo.init(path);
        PrintClassInfo.printAll();
    }
}
