package com.ming.base;

import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

/**
 * @author smallclover
 * 表示类中的方法
 * tag = 10
 */
public class ConstantMethodrefInfo extends ConstantInfo {
    private static final U1 tag = new U1(10);
    //指向声明方法的类描述符CONSTANT_Class_info的索引项
    private U2 class_index = null;
    //指向方法名称及类型描述符CONSTANT_NameAndType_info的索引项
    private U2 name_and_type_index = null;

    public ConstantMethodrefInfo(ClassFileReader cfr) {
        class_index = cfr.readU2();
        name_and_type_index = cfr.readU2();
    }
}
