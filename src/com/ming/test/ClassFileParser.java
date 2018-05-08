package com.ming.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.ming.base.ConstantClassInfo;
import com.ming.base.ConstantUtf8Info;
import com.ming.core.ClassFile;
import com.ming.core.ConstantPool;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.core.U4;

/**
 * @author smallclover
 * 读取示例Class文件，并将内容打印出来
 * 示例文件：Sample.java
 */
public class ClassFileParser {
    private static InputStream is = null;
    private static ClassFile cf = new ClassFile();
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
     * @throws IOException
     */
    public static void magic() throws IOException {
        byte[] bytes = new byte[4];
        is.read(bytes, 0, 4);
		U4 magic = new U4(bytes);
		cf.setMagic(magic);
		System.out.println(magic.toHex());
    }

    /**
     * 打印jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     * @throws IOException
     */
    public static void minorVersion() throws IOException {
        byte[] bytes = new byte[2];
        is.read(bytes, 0 , 2);
		U2 minorVersion = new U2(bytes);
		cf.setMinorVersion(minorVersion);
		System.out.println(minorVersion.getValue());
    }

    /**
     * 打印jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     * @throws IOException
     */
    public static void majorVersion() throws IOException {
        byte[] bytes = new byte[2];
        is.read(bytes, 0 , 2);
		U2 majorVersion = new U2(bytes);
		cf.setMajorVersion(majorVersion);
		System.out.println(majorVersion.getValue());
    }

    /**
     * print constant_pool_count
     * constantPoolCount 2 byte unsigned integer
     * @throws IOException
     */
    public static void constantPoolCount() throws IOException {
        byte[] bytes = new byte[2];
        is.read(bytes, 0 , 2);
    	U2 constantPoolCount =  new U2(bytes);
		cf.setConstantPoolCount(constantPoolCount);
    	System.out.println(cf.getConstantPoolCount().getValue());
    }

    /**
     * read constant pool info ( null )
     * @throws IOException
     */
    public static void constantInfo() throws IOException {
    	int length = cf.getConstantPoolCount().getValue();
    	ConstantPool cp = new ConstantPool();
    	while (length != 0) {
    	    byte[] byte_tag = new byte[1];
    	    is.read(byte_tag, 0, 1);
    		U1 tag = new U1(byte_tag[0]);
    		switch(tag.getValue()) {
    			case 1 :
    			    byte[] byte_length = new byte[2];
                    is.read(byte_length, 0, 2);
    				U2 len = new U2(byte_length);
                    //is.read()
    				//cp.getConstantPool().add(new ConstantUtf8Info(len.getValue()));
    				break;
    			case 7:
    			    byte[] byte_name_index = new byte[2];
    				cp.getConstantPool().add(new ConstantClassInfo(byte_name_index));
    				break;
    		}
    		length --;
    	}
    }

    /**
     * print access_flags
     * @throws IOException
     */
    public static void accessFlags() throws IOException {
        byte[] bytes = new byte[2];
        is.read(bytes, 0 , 2);
		U2 accessFlags = new U2(bytes);
		System.out.println(accessFlags.toHex());

    }

    public static void main(String[] args) {
        ClassFileParser.readClassFile(defaultClassFilePath);
        try {
            ClassFileParser.magic();
            ClassFileParser.minorVersion();
            ClassFileParser.majorVersion();
            ClassFileParser.constantPoolCount();
            ClassFileParser.constantInfo();
            //ClassFileParser.accessFlags();
        } catch(IOException e) {
        	e.printStackTrace();
        }

    }

}
