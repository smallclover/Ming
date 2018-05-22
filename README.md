# Ming
Class文件解析器
## 关于
对Java编译后的Class文件进行解析

## 版本(更新说明)

+ 0.1.0
  - 修复读取Double和Long类型时出现找不到指定类型的错误
  - 移除jdk9的方法InputStream#readAllBytes()，替换为Files#readAllBytes()，以支持更低版本
+ 0.1.1
  - 完善了测试方法的输出信息，现在基本可以完整的展示整个class文件的信息

## 使用方法

### 环境
支持JDK8及其以上版本

### 测试用例(因为还没有完全完成这个解析器，所以如果想要运行请尽量使用该示例)

HelloWorld.java

```java

public class HelloWorld {
  private static final String str = "HelloWorld";
  public static void main(String[] args) {
    int a = 0;
    int b = 2;
    System.out.println("HelloWorld");
  }
}
```

### 运行

参考文件```java com.ming.test.Test.java```


## 进展

项目 | 是否完成 | 进度
---- | ---- | ----
magic | √ | 100%
minor_version | √ | 100%
major_version | √ | 100%
constant_pool_count | √ | 100%
constant_pool[constant_pool_count] | × | 90%
access_flags | √ | 100%
this_class | √ | 100%
super_class | √ | 100%
interfaces_count | √ | 100%
interfaces[interfaces_count] | √ | 100%
fields_count | √ | 100%
fields[fields_count] | × | 90%
methods_count | √ | 100%
methods[methods_count] | × | 90%
attributes_count | √ | 100%
attributes[attributes_count] | × | 90%

## 目录说明（最后更新时间 2018/05/17）

+ src
  - com
    + ming
      - base
        + attribute
          - AttributeCodeInfo.java
          - AttributeConstantValueInfo.java
          - AttributeLineNumberTableInfo.java
          - AttributeLocalVariableTableInfo.java
          - AttributeStackMapTable.java
          - AttributeSoureFileInfo.java
        + constant
          - ConstantClassInfo.java
          - ConstantDoubleInfo.java
          - ConstantFieldrefInfo.java
          - ConstantFloatInfo.java
          - ConstantIntegerInfo.java
          - ConstantInterfaceMethodrefInfo.java
          - ConstantInvokeDynamicInfo.java
          - ConstantLongInfo.java
          - ConstantMethodHandleInfo.java
          - ConstantMethodTypeInfo.java
          - ConstantMethodrefInfo.java
          - ConstantNameAndTypeInfo.java
          - ConstantStringInfo.java
          - ConstantUtf8Info.java
        + AttributeInfo.java
        + ConstantInfo.java
        + FieldInfo.java
        + MethodInfo.java
      - core
        + ClassFile.java
        + ConstantPool.java
        + Modifier.java
        + U1.java
        + U2.java
        + U4.java
        + U8.java
      - io
        + ClassFileReader.java
      - main
        + ClassFileParser.java
      - test
        + Test.java
+ docs
  - classfile
    + ClassFile.md
    + constant
      - CONSTANT_Class_info.md
      - CONSTANT_Fieldref_info.md
    + attribure


