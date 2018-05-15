package com.ming.main;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.ming.base.AttributeInfo;
import com.ming.base.ConstantInfo;
import com.ming.base.FieldInfo;
import com.ming.base.MethodInfo;
import com.ming.base.constant.ConstantUtf8Info;
import com.ming.core.ClassFile;
import com.ming.core.ConstantPool;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.io.ClassFileReader;

/**
 * @author smallclover
 * 读取示例Class文件，并将内容打印出来
 * 示例文件：Sample.java
 */
public class ClassFileParser {
    private static InputStream is = null;
    private static ClassFile cf = new ClassFile();
    private static ClassFileReader cfr = null;
    private static ConstantPool cp = null;
    //这里可以更改为任意的class文件
    private static String defaultClassFilePath;
    /**
     * 第一步读取class文件
     * @param classFilePath
     * @return
     */
    private static void readClassFile(String classFilePath) {
        File file = new File(classFilePath);

        try {
            is = new FileInputStream(file);
            cfr = new ClassFileReader(is.readAllBytes());
            cp = new ConstantPool();
            cf.setConstantPool(cp);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
    private static void magic() throws IOException {
    	U4 magic = cfr.readU4();
		cf.setMagic(magic);
		// System.out.println("[magic: " + magic.toHex() + "]");
    }

    /**
     * 设置jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     * @throws IOException
     */
    private static void minorVersion() throws IOException {
		U2 minorVersion = cfr.readU2();
		cf.setMinorVersion(minorVersion);
		// System.out.println("[minor_version: " + minorVersion.getValue() + "]");
    }

    /**
     * 设置jdk的主版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     * @throws IOException
     */
    private static void majorVersion() throws IOException {
		U2 majorVersion = cfr.readU2();
		cf.setMajorVersion(majorVersion);
		// System.out.println("[major_version: "+majorVersion.getValue() + "]");
    }

    /**
     * 设置 常量池（constant_pool_count）数量
     * constantPoolCount 占用两个字节的无符号整数
     * @throws IOException
     */
    private static void constantPoolCount() throws IOException {
    	U2 constantPoolCount = cfr.readU2();
		cf.setConstantPoolCount(constantPoolCount);
    	// System.out.println("[constant_pool_count: "+ constantPoolCount.getValue() + "]");
    }

    /**
     * read constant pool info ( null )
     * @throws IOException
     */
    private static void constantInfo() throws IOException {
    	int length = cf.getConstantPoolCount().getValue();
    	ConstantInfo[] cpInfo = new ConstantInfo[length];
    	for( int i = 1; i < length;i ++) {

    	    //todo 对于double，long可能会有问题
    		U1 tag = cfr.readU1();
    		cpInfo[i] = ConstantInfo.getSpecificConstantInfo(tag.getValue() ,cfr);
    	}
    	cp.setConstantInfo(cpInfo);
/*    	for(ConstantInfo content: cp.getConstantInfo()) {
            if (content == null) {
                continue;
            }
    		System.out.println(content.getClass().getSimpleName());
    		System.out.println(content);
    	}*/
    }

    /**
     * print access_flags
     * @throws IOException
     */
    private static void accessFlags() throws IOException {
        U2 accessFlags = cfr.readU2();
        cf.setAccessFlags(accessFlags);
        // System.out.println("[access_flags: " + accessFlags.getValue() + "]\n");
    }

    private static void thisClass() {
        U2 thisClass = cfr.readU2();
        cf.setThisClass(thisClass);
        // System.out.println("[this_class: " + thisClass.getValue() + "]\n");
    }

    private static void superClass() {
        U2 superClass = cfr.readU2();
        cf.setSuperClass(superClass);
        // System.out.println("[super_class:" + superClass.getValue() + "]\n");
    }

    private static void interfacesCount(){
        U2 interfacesCount = cfr.readU2();
        cf.setInterfacesCount(interfacesCount);
        // System.out.println("[interfaces_count:" + interfacesCount.getValue() + "]\n");
    }

    private static void interfaces() {
        //todo
        int length = cf.getInterfacesCount().getValue();
        if (length == 0) {
            //System.out.println(0);
        }
        U2[] interfaces = new U2[length];
        for (int i = 0; i < length;i ++) {
            interfaces[i] = cfr.readU2();
        }
/*        for (U2 u2 : interfaces) {
            System.out.println(u2.getValue());
        }*/
    }

    private static void fieldCount() {
        U2 fieldCount = cfr.readU2();
        cf.setFieldsCount(fieldCount);
        // System.out.println("[field_count: " + fieldCount.getValue() + "]\n");
    }

    private static void fields() {
        int length = cf.getFieldsCount().getValue();

        FieldInfo[] fis = new FieldInfo[length];
        for (int i = 0; i < fis.length; i ++) {
            fis[i] = new FieldInfo();
        }
        for (int i = 0; i < length; i++) {
            fis[i].setAccessFlags(cfr.readU2());
            fis[i].setNameIndex(cfr.readU2());
            fis[i].setDescriptorIndex(cfr.readU2());
            // System.out.println("[access_flags: "+ fis[i].getAccessFlags().getValue() +"]");
            // System.out.println("[name_index: "+ fis[i].getNameIndex().getValue() +"]");
            // System.out.println("[descriptor_index: "+ fis[i].getDescriptorIndex().getValue() +"]");
            U2 att_length = cfr.readU2();
            fis[i].setAttributesCount(att_length);
            // System.out.println("[attributes_count: "+ fis[i].getAttributesCount().getValue() +"]");
            AttributeInfo[] attributes = new AttributeInfo[att_length.getValue()];
            for (int j = 0; j < attributes.length; j++) {
                U2 attribute_name_index = cfr.readU2();
                ConstantInfo[] ci = cp.getConstantInfo();
                //System.out.println(((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString());
                attributes[j] = AttributeInfo.getSpecificAttributeInfo(attribute_name_index, ((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
                // System.out.println(attributes[j].getClass().getSimpleName());
                //System.out.println(attributes[j]);
            }
        }
    }

    private static void methodCount() {
    	U2 methodCount = cfr.readU2();
    	cf.setMethodsCount(methodCount);
    	// System.out.println("[method_count: " + methodCount.getValue() + "]\n");
    }

    private static void methods() {
    	int length = cf.getMethodsCount().getValue();

        MethodInfo[] mis = new MethodInfo[length];
        for (int i = 0; i < mis.length; i ++) {
            mis[i] = new MethodInfo();
        }
        for (int i = 0; i < length; i++) {
            mis[i].setAccessFlags(cfr.readU2());
            mis[i].setNameIndex(cfr.readU2());
            mis[i].setDescriptorIndex(cfr.readU2());
            // System.out.println("[access_flags: "+ mis[i].getAccessFlags().getValue() +"]");
            // System.out.println("[name_index: "+ mis[i].getNameIndex().getValue() +"]");
            // System.out.println("[descriptor_index: "+ mis[i].getDescriptorIndex().getValue() +"]");
            U2 att_length = cfr.readU2();
            mis[i].setAttributesCount(att_length);
            // System.out.println("[attributes_count: "+ mis[i].getAttributesCount().getValue() +"]");
            AttributeInfo[] attributes = new AttributeInfo[att_length.getValue()];
            for (int j = 0; j < attributes.length; j++) {
                U2 attribute_name_index = cfr.readU2();
                ConstantInfo[] ci = cp.getConstantInfo();
                //System.out.println(((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString());
                attributes[j] = AttributeInfo.getSpecificAttributeInfo(attribute_name_index, ((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
                // System.out.println(attributes[j].getClass().getSimpleName());
                // System.out.println(attributes[j]);
                //todo 读取code数据
            }
        }
    }

    private static void attributeCount() {
    	U2 attributes_count = cfr.readU2();
    	cf.setAttributesCount(attributes_count);
    	// System.out.println("[attributes_count: " + attributes_count.getValue() + "]\n");
    }

    private static void attributes() {
        AttributeInfo[] attributes = new AttributeInfo[cf.getAttributesCount().getValue()];
        for (int j = 0; j < attributes.length; j++) {
            U2 attribute_name_index = cfr.readU2();
            ConstantInfo[] ci = cp.getConstantInfo();
            //System.out.println(((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString());
            attributes[j] = AttributeInfo.getSpecificAttributeInfo(attribute_name_index, ((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
            // System.out.println(attributes[j].getClass().getSimpleName());
            // System.out.println(attributes[j]);
            //todo 读取code数据
        }
    }

    private static void init() {
        ClassFileParser.readClassFile(defaultClassFilePath);
        try {
            ClassFileParser.magic();
            ClassFileParser.minorVersion();
            ClassFileParser.majorVersion();
            ClassFileParser.constantPoolCount();
            ClassFileParser.constantInfo();
            ClassFileParser.accessFlags();
            ClassFileParser.thisClass();
            ClassFileParser.superClass();
            ClassFileParser.interfacesCount();
            ClassFileParser.interfaces();
            ClassFileParser.fieldCount();
            ClassFileParser.fields();
            ClassFileParser.methodCount();
            ClassFileParser.methods();
            ClassFileParser.attributeCount();
            ClassFileParser.attributes();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public static ClassFile getClassFile(String classFilePath) {
        defaultClassFilePath = classFilePath;
        init();
        return cf;
    }

}
