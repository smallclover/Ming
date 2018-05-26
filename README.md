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
+ 0.1.2
  - 进一步优化输出的信息，现在可以在打印信息的时候直接显示出常量池索引所指向的常量
+ 0.1.3
  - 现在可以打印出方法的操作码

## 已知可能存在的问题

+ class文件使用的是MUTF-8的编码格式，与UTF-8有微妙的不同，在某些情况下可能错在无法解析的情况。
+ 有一些类型虽然声明了，但是还没有具体的添加内容，（才不是因为懒呢），如果碰到没有实装的类型，会报错，我会尽量今早将所有的类型进行实装。
+ 遇到没有实装类型的错误类型有两种
  - 没有找到指定的属性类型：cant find specific attribute
  - 没有找到指定的常量类型："can't find specific type tag: \[该常量类型所对应的具体的数值]"
  
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

参考文件

+ ```java com.ming.print.PrintClassInfo.java``` 具体信息的输出
+ ```java com.ming.test.Test.java``` 调用PrintClassInfo#printAll() 打印所有信息


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

## 目录说明（最后更新时间 2018/05/26）

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
        + Opcode.java
        + U1.java
        + U2.java
        + U4.java
        + U8.java
      - io
        + ClassFileReader.java
      - main
        + ClassFileParser.java
      - print
        + PrintClassInfo.java
      - test
        + Test.java
+ docs
  - classfile
    + ClassFile.md
    + constant
      - CONSTANT_Class_info.md
      - CONSTANT_Fieldref_info.md
    + Attribute


