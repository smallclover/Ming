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
+ 0.1.4
  - 修复部分bug

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

Simple.java

```java

public class Simple implements Parent, Child{
    private int data;
	private static final String flag= "HelloWorld";
	private static final long num = 1L;
	private static final double dou = 0.1;
    public int add(int a, int b) {
      return a + b;
    }
}

```

Parent.java

```java

public interface Parent {

}

```

Child.java

```java

public interface Child {

}

```

### 运行

参考文件

+ ```java com.ming.print.PrintClassInfo.java``` 具体信息的输出
+ ```java com.ming.test.Test.java``` 调用PrintClassInfo#printAll() 打印所有信息


### 运行结果示例

```cpp

[magic]: cafebabe
[minor_version]: 0
[major_version]: 52
[constant_pool_count]: 34
[constant_pool]: 
[1]: ConstantMethodrefInfo
[tag]: 10
[class_index]: 3-java/lang/Object
[name_and_type_index]: 28-<init>-()V

[2]: ConstantClassInfo
[tag]: 7
[name_index]: 29-Simple

[3]: ConstantClassInfo
[tag]: 7
[name_index]: 30-java/lang/Object

[4]: ConstantClassInfo
[tag]: 7
[name_index]: 31-Parent

[5]: ConstantClassInfo
[tag]: 7
[name_index]: 32-Child

[6]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: data

[7]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: I

[8]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: flag

[9]: ConstantUtf8Info
[tag]: 1
[length]: 18
[bytes]: Ljava/lang/String;

[10]: ConstantUtf8Info
[tag]: 1
[length]: 13
[bytes]: ConstantValue

[11]: ConstantStringInfo
[tag]: 8
[string_index]: 33-HelloWorld

[12]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: num

[13]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: J

[14]: ConstantLongInfo
[tag]: 5
[value]: 01

[16]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: dou

[17]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: D

[18]: ConstantDoubleInfo
[tag]: 6
[value]: 3fb999999999999a

[20]: ConstantUtf8Info
[tag]: 1
[length]: 6
[bytes]: <init>

[21]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: ()V

[22]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: Code

[23]: ConstantUtf8Info
[tag]: 1
[length]: 15
[bytes]: LineNumberTable

[24]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: add

[25]: ConstantUtf8Info
[tag]: 1
[length]: 5
[bytes]: (II)I

[26]: ConstantUtf8Info
[tag]: 1
[length]: 10
[bytes]: SourceFile

[27]: ConstantUtf8Info
[tag]: 1
[length]: 11
[bytes]: Simple.java

[28]: ConstantNameAndTypeInfo
[tag]: 12
[name_index]: 20-<init>
[descriptor_index]: 21-()V

[29]: ConstantUtf8Info
[tag]: 1
[length]: 6
[bytes]: Simple

[30]: ConstantUtf8Info
[tag]: 1
[length]: 16
[bytes]: java/lang/Object

[31]: ConstantUtf8Info
[tag]: 1
[length]: 6
[bytes]: Parent

[32]: ConstantUtf8Info
[tag]: 1
[length]: 5
[bytes]: Child

[33]: ConstantUtf8Info
[tag]: 1
[length]: 10
[bytes]: HelloWorld

[access_flags]: 33
[this_class]: 2
[super_class]: 3
[interfaces_count]: 2
[interfaces]: 
[0]: 4
[1]: 5

[fields_count]: 4
[fields]: 

[field 1 ]: 
[access_flags]: 2
[name_index]: 6
[descriptor_index]: 7
[attributes_count]: 0

[field 2 ]: 
[access_flags]: 26
[name_index]: 8
[descriptor_index]: 9
[attributes_count]: 1

[1]: AttributeConstantValueInfo
[attribute_name_index]: 10
[attribute_length]: 2
[constantvalue_index]: 11

[field 3 ]: 
[access_flags]: 26
[name_index]: 12
[descriptor_index]: 13
[attributes_count]: 1

[1]: AttributeConstantValueInfo
[attribute_name_index]: 10
[attribute_length]: 2
[constantvalue_index]: 14

[field 4 ]: 
[access_flags]: 26
[name_index]: 16
[descriptor_index]: 17
[attributes_count]: 1

[1]: AttributeConstantValueInfo
[attribute_name_index]: 10
[attribute_length]: 2
[constantvalue_index]: 18


[methods_count]: 2
[methods]: 

[method 1 ]: 
[access_flags]: 1
[name_index]: 20
[descriptor_index]: 21
[attributes_count]: 1

[1]: AttributeCodeInfo
[attribute_name_index]: 22
[attribute_length]: 29
[max_stack]: 1
[max_locals]: 1
[code_length]: 5
42-aload_0
183-invokespecial
0-nop
1-aconst_null
177-return_
[exception_table_length]: 0
[attributes_count]: 1

[method 2 ]: 
[access_flags]: 1
[name_index]: 24
[descriptor_index]: 25
[attributes_count]: 1

[1]: AttributeCodeInfo
[attribute_name_index]: 22
[attribute_length]: 28
[max_stack]: 2
[max_locals]: 3
[code_length]: 4
27-iload_1
28-iload_2
96-iadd
172-ireturn
[exception_table_length]: 0
[attributes_count]: 1


[attributes_count]: 1
[attributes]: 

[0]: AttributeSourceFileInfo
[attribute_name_index]: 26
[attribute_length]: 2
[sourcefile_index]: 27

```


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
      - Attribute.md
      - Code_attribute.md
      - ConstantValue_attribute.md


