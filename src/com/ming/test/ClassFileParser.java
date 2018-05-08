package com.ming.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.ming.core.U2;
import com.ming.core.U4;

/**
 * @author smallclover
 * 读取示例Class文件，并将内容打印出来
 * 示例文件：Sample.java
 */
public class ClassFileParser {
    private static InputStream is = null;
    //这里可以更改为任意的class文件
    private static String defaultClassFilePath="E:\\tools\\pleiades\\workspace\\HelloWorld.class";
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
    	try {
			U4 magic = new U4(is);
			System.out.println(magic.toHex());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }

    /**
     * 打印jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     */
    public static void minorVersion() {
    	try {
			U2 minorVersion = new U2(is);
			System.out.println(minorVersion.getValue());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }

    /**
     * 打印jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     */
    public static void majorVersion() {
    	try {
			U2 majorVersion = new U2(is);
			System.out.println(majorVersion.getValue());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
    }

    public static void main(String[] args) {
        ClassFileParser.readClassFile(defaultClassFilePath);
        ClassFileParser.magic();
        ClassFileParser.minorVersion();
        ClassFileParser.majorVersion();
    }

}
