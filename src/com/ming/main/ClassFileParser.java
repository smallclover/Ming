package com.ming.main;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

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
 * 解析class文件，然后将其赋值给ClassFile
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
            //cfr = new ClassFileReader(is.readAllBytes()); //the method since jdk9
            cfr = new ClassFileReader(Files.readAllBytes(Paths.get(defaultClassFilePath)));// maybe support jdk1.4 ?
            cp = new ConstantPool();
            cf.setConstantPool(cp);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取 class文件魔数（magic）
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
    }

    /**
     * 读取 jdk的次版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     * @throws IOException
     */
    private static void minorVersion() throws IOException {
		U2 minorVersion = cfr.readU2();
		cf.setMinorVersion(minorVersion);
    }

    /**
     * 读取 jdk的主版本（现在似乎已经弃用，值一直是0）
     * minorVersion 占用两个字节的无符号整数
     * @throws IOException
     */
    private static void majorVersion() throws IOException {
		U2 majorVersion = cfr.readU2();
		cf.setMajorVersion(majorVersion);
    }

    /**
     * 读取 常量池（constant_pool_count）数量
     * constantPoolCount 占用两个字节的无符号整数
     * @throws IOException
     */
    private static void constantPoolCount() throws IOException {
    	U2 constantPoolCount = cfr.readU2();
		cf.setConstantPoolCount(constantPoolCount);
    }

    /**
     * 读取常量池具体信息
     * @throws IOException
     */
    private static void constantInfo() throws IOException {
    	int length = cf.getConstantPoolCount().getValue();
    	ConstantInfo[] cpInfo = new ConstantInfo[length];
    	// 常量池中的项目是从索引值1开始的
    	int i = 1;
    	while(i < length) {
    		U1 tag = cfr.readU1();
    		if(tag.getValue() == 6 || tag.getValue() == 5) {
        		cpInfo[i] = ConstantInfo.getSpecificConstantInfo(tag.getValue() ,cfr);
        		i += 2; // 如果常量池中的类型时double或者long，将会占用两个连续的位置
                // 假设占用的第一个索引是n，那么n+1将不可用。
    		}else {
        		cpInfo[i] = ConstantInfo.getSpecificConstantInfo(tag.getValue() ,cfr);
        		i ++;
    		}

    	}

    	cp.setConstantInfo(cpInfo);

    }

    /**
     * 读取 access_flags
     * @throws IOException
     */
    private static void accessFlags() throws IOException {
        U2 accessFlags = cfr.readU2();
        cf.setAccessFlags(accessFlags);
    }

    /**
     * 读取 this_class
     */
    private static void thisClass() {
        U2 thisClass = cfr.readU2();
        cf.setThisClass(thisClass);
    }

    /**
     * 读取 super_class
     */
    private static void superClass() {
        U2 superClass = cfr.readU2();
        cf.setSuperClass(superClass);
    }

    /**
     * 读取 interface_count
     */
    private static void interfacesCount(){
        U2 interfacesCount = cfr.readU2();
        cf.setInterfacesCount(interfacesCount);
    }

    /**
     * 读取 interfaces
     */
    private static void interfaces() {
        int length = cf.getInterfacesCount().getValue();
        if (length == 0) {
            return;
        }
        U2[] interfaces = new U2[length];
        for (int i = 0; i < length;i ++) {
            interfaces[i] = cfr.readU2();
        }
        cf.setInterfaces(interfaces);
    }

    private static void fieldCount() {
        U2 fieldCount = cfr.readU2();
        cf.setFieldsCount(fieldCount);
    }

    /**
     * 读取属性的详细信息
     */
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
            U2 att_length = cfr.readU2();
            fis[i].setAttributesCount(att_length);
            AttributeInfo[] attributes = new AttributeInfo[att_length.getValue()];
            for (int j = 0; j < attributes.length; j++) {
                U2 attribute_name_index = cfr.readU2();
                ConstantInfo[] ci = cp.getConstantInfo();
                attributes[j] = AttributeInfo.getSpecificAttributeInfo(attribute_name_index, ((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
            }

            fis[i].setAttributes(attributes);
        }

        cf.setFields(fis);
    }

    /**
     * 读取方法数量
     */
    private static void methodCount() {
    	U2 methodCount = cfr.readU2();
    	cf.setMethodsCount(methodCount);
    }

    /**
     * 读取方法的具体信息
     */
    private static void methods() {
    	int length = cf.getMethodsCount().getValue();

        MethodInfo[] mis = new MethodInfo[length];
        for (int i = 0; i < mis.length; i ++) {
            mis[i] = new MethodInfo();
        }
        for (int i = 0; i < length; i++) {
            if (true){
                // todo 代码要进行重构
            }
            mis[i].setAccessFlags(cfr.readU2());
            mis[i].setNameIndex(cfr.readU2());
            mis[i].setDescriptorIndex(cfr.readU2());
            U2 att_length = cfr.readU2();
            mis[i].setAttributesCount(att_length);
            AttributeInfo[] attributes = new AttributeInfo[att_length.getValue()];
            for (int j = 0; j < attributes.length; j++) {
                U2 attribute_name_index = cfr.readU2();
                ConstantInfo[] ci = cp.getConstantInfo();
                attributes[j] = AttributeInfo.getSpecificAttributeInfo(attribute_name_index, ((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
                //todo 读取code数据 这里可能需要对代码进行重构
            }
            mis[i].setAttributes(attributes);
        }

        cf.setMethods(mis);
    }

    private static void attributeCount() {
    	U2 attributes_count = cfr.readU2();
    	cf.setAttributesCount(attributes_count);
    }

    private static void attributes() {
        AttributeInfo[] attributes = new AttributeInfo[cf.getAttributesCount().getValue()];
        for (int j = 0; j < attributes.length; j++) {
            U2 attribute_name_index = cfr.readU2();
            ConstantInfo[] ci = cp.getConstantInfo();
            attributes[j] = AttributeInfo.getSpecificAttributeInfo(attribute_name_index, ((ConstantUtf8Info)ci[attribute_name_index.getValue()]).convertHexToString(), cfr, cp);
            //todo 属性嵌套？？？
        }
        cf.setAttributes(attributes);
    }

    /**
     * 初始化所有数据，外部调用ClassFile时，数据已经全部被加载好。
     */
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
