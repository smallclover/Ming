package com.ming.test;

import javafx.fxml.FXML;

import java.io.*;

/**
 * @author smallclover
 * 读取示例Class文件，并将内容打印出来
 * 示例文件：Sample.java
 */
public class ClassFileParser {
    private static InputStream is = null;
    //这里可以更改为任意的class文件
    private static String defaultClassFilePath="C:\\Users\\smallclover\\Desktop\\Simple.class";
    /**
     * 第一步读取class文件
     * @param classFilePath
     * @return
     */
    public static InputStream readClassFile(String classFilePath) {
        File file = new File(classFilePath);

        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
            e.printStackTrace();
        }

        return is;
    }

    /**
     * 获取class文件魔数（magic）
     * magic = 0xcafebabe (16 进制)
     * magic占用四个字节的无符号整数
     * byte代表一个字节，但是是有符号整数，取值范围是[-127~128]
     * 所以要把byte变为无符号，只需与0xff做位与运算即可（将符号为参与运算，这样经过位运算之后类型变为int类型的时候高位补位会自动补0）
     * 0xff = 11111111
     */
    public static void magic() {
        byte[] magic = new byte[4];
        try {
            is.read(magic, 0, 4);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        for (byte b : magic) {
            System.out.println(Integer.toHexString(b & 0xff));
        }*/
    }

    /**
     * 打印jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     */
    public static void minorVersion() {
        byte[] minorVersion = new byte[2];
        int sum = 0;
        try {
            is.read(minorVersion, 0, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //进行字节拼接转换为int类型
        //先将高位的八个bit向左移动8位，留出低八位填充剩下的8bit
        sum = ((minorVersion[0] & 0xff) << 8 | (minorVersion[1] & 0xff));
/*        for (byte b : minorVersion) {
            System.out.println(Integer.toHexString(b & 0xff));
        }*/
        System.out.println(sum);
    }

    /**
     * 打印jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     */
    public static void majorVersion() {
        byte[] majorVersion = new byte[2];
        int sum = 0;

        try {
            is.read(majorVersion, 0, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
/*        for (byte b : majorVersion) {
            System.out.println(Integer.toHexString(b & 0xff));
        }*/
        sum = ((majorVersion[0] & 0xff) << 8 | (majorVersion[1] & 0xff));
        System.out.println(sum);
    }

    public static void main(String[] args) {
        ClassFileParser.readClassFile(defaultClassFilePath);
        ClassFileParser.magic();
        ClassFileParser.minorVersion();
        ClassFileParser.majorVersion();
    }

}
