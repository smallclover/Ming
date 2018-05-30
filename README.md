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
+ 0.1.5
  - 现在可以正常打印出属性，方法，类的访问控制符，以及，方法，属性的详细信息

## 已知可能存在的问题

+ class文件使用的是MUTF-8的编码格式，与UTF-8有微妙的不同，在某些情况下可能错在无法解析的情况。
+ 有一些类型虽然声明了，但是还没有具体的添加内容，（才不是因为懒呢），如果碰到没有实装的类型，会报错，我会尽量今早将所有的类型进行实装。
+ 遇到没有实装类型的错误类型有两种
  - 没有找到指定的属性类型："can't find specific attribute"
  - 没有找到指定的常量类型："can't find specific type tag: \[该常量类型所对应的具体的数值]"
+ 方法中的操作符在某些情况下进行补位，所以，在某些情况下存在问题。
  
## 使用方法

### 环境
支持JDK8及其以上版本

### 测试用例

演示的输出结果，使用的是该示例字节码，如果使用其他的类只需传入类的class文件的路径，即可。如果出现报错，提交一个issue即可。

Simple.java

```java

package com.ming.test;

public class Simple implements Parent, Child{
    private int data;
	private static final String flag= "HelloWorld";
	private static final long num = 1L;
	private static final double dou = 0.1;
    public int add(int a, int b) {
      return a + b;
    }
	
	public void messger() {
		int c = 0;
		int d = 2;
		System.out.println(c + d);
	}
}

```

Parent.java

```java
package com.ming.test;

public interface Parent {

}
```

Child.java

```java
package com.ming.test;

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
[major_version]: 54
[constant_pool_count]: 54
[constant_pool]: 
[1]: ConstantMethodrefInfo
[tag]: 10
[class_index]: 5-java/lang/Object
[name_and_type_index]: 38-<init>-()V

[2]: ConstantFieldrefInfo
null
[3]: ConstantMethodrefInfo
[tag]: 10
[class_index]: 41-java/io/PrintStream
[name_and_type_index]: 42-println-(I)V

[4]: ConstantClassInfo
[tag]: 7
[name_index]: 43-com/ming/test/Simple

[5]: ConstantClassInfo
[tag]: 7
[name_index]: 44-java/lang/Object

[6]: ConstantClassInfo
[tag]: 7
[name_index]: 45-com/ming/test/Parent

[7]: ConstantClassInfo
[tag]: 7
[name_index]: 46-com/ming/test/Child

[8]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: data

[9]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: I

[10]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: flag

[11]: ConstantUtf8Info
[tag]: 1
[length]: 18
[bytes]: Ljava/lang/String;

[12]: ConstantUtf8Info
[tag]: 1
[length]: 13
[bytes]: ConstantValue

[13]: ConstantStringInfo
[tag]: 8
[string_index]: 47-HelloWorld

[14]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: num

[15]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: J

[16]: ConstantLongInfo
[tag]: 5
[value]: 01

[18]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: dou

[19]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: D

[20]: ConstantDoubleInfo
[tag]: 6
[value]: 3fb999999999999a

[22]: ConstantUtf8Info
[tag]: 1
[length]: 6
[bytes]: <init>

[23]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: ()V

[24]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: Code

[25]: ConstantUtf8Info
[tag]: 1
[length]: 15
[bytes]: LineNumberTable

[26]: ConstantUtf8Info
[tag]: 1
[length]: 18
[bytes]: LocalVariableTable

[27]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: this

[28]: ConstantUtf8Info
[tag]: 1
[length]: 22
[bytes]: Lcom/ming/test/Simple;

[29]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: add

[30]: ConstantUtf8Info
[tag]: 1
[length]: 5
[bytes]: (II)I

[31]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: a

[32]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: b

[33]: ConstantUtf8Info
[tag]: 1
[length]: 7
[bytes]: messger

[34]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: c

[35]: ConstantUtf8Info
[tag]: 1
[length]: 1
[bytes]: d

[36]: ConstantUtf8Info
[tag]: 1
[length]: 10
[bytes]: SourceFile

[37]: ConstantUtf8Info
[tag]: 1
[length]: 11
[bytes]: Simple.java

[38]: ConstantNameAndTypeInfo
[tag]: 12
[name_index]: 22-<init>
[descriptor_index]: 23-()V

[39]: ConstantClassInfo
[tag]: 7
[name_index]: 48-java/lang/System

[40]: ConstantNameAndTypeInfo
[tag]: 12
[name_index]: 49-out
[descriptor_index]: 50-Ljava/io/PrintStream;

[41]: ConstantClassInfo
[tag]: 7
[name_index]: 51-java/io/PrintStream

[42]: ConstantNameAndTypeInfo
[tag]: 12
[name_index]: 52-println
[descriptor_index]: 53-(I)V

[43]: ConstantUtf8Info
[tag]: 1
[length]: 20
[bytes]: com/ming/test/Simple

[44]: ConstantUtf8Info
[tag]: 1
[length]: 16
[bytes]: java/lang/Object

[45]: ConstantUtf8Info
[tag]: 1
[length]: 20
[bytes]: com/ming/test/Parent

[46]: ConstantUtf8Info
[tag]: 1
[length]: 19
[bytes]: com/ming/test/Child

[47]: ConstantUtf8Info
[tag]: 1
[length]: 10
[bytes]: HelloWorld

[48]: ConstantUtf8Info
[tag]: 1
[length]: 16
[bytes]: java/lang/System

[49]: ConstantUtf8Info
[tag]: 1
[length]: 3
[bytes]: out

[50]: ConstantUtf8Info
[tag]: 1
[length]: 21
[bytes]: Ljava/io/PrintStream;

[51]: ConstantUtf8Info
[tag]: 1
[length]: 19
[bytes]: java/io/PrintStream

[52]: ConstantUtf8Info
[tag]: 1
[length]: 7
[bytes]: println

[53]: ConstantUtf8Info
[tag]: 1
[length]: 4
[bytes]: (I)V

[access_flags]: 33-public super 
[this_class]: 4
[super_class]: 5
[interfaces_count]: 2
[interfaces]: 
[0]: 6-com/ming/test/Parent
[1]: 7-com/ming/test/Child

[fields_count]: 4
[fields]: 

[field 1 ]: 
[access_flags]: 2-private 
[name_index]: 8-data
[descriptor_index]: 9-I
[attributes_count]: 0

[field 2 ]: 
[access_flags]: 26-private final static 
[name_index]: 10-flag
[descriptor_index]: 11-Ljava/lang/String;
[attributes_count]: 1

[1]: AttributeConstantValueInfo
[attribute_name_index]: 12-ConstantValue
[attribute_length]: 2
[constantvalue_index]: 13-HelloWorld

[field 3 ]: 
[access_flags]: 26-private final static 
[name_index]: 14-num
[descriptor_index]: 15-J
[attributes_count]: 1

[1]: AttributeConstantValueInfo
[attribute_name_index]: 12-ConstantValue
[attribute_length]: 2
[constantvalue_index]: 16-null

[field 4 ]: 
[access_flags]: 26-private final static 
[name_index]: 18-dou
[descriptor_index]: 19-D
[attributes_count]: 1

[1]: AttributeConstantValueInfo
[attribute_name_index]: 12-ConstantValue
[attribute_length]: 2
[constantvalue_index]: 20-null


[methods_count]: 3
[methods]: 

[method 1 ]: 
[access_flags]: 1-public 
[name_index]: 22-<init>
[descriptor_index]: 23-()V
[attributes_count]: 1

[1]: AttributeCodeInfo
[attribute_name_index]: 24-Code
[attribute_length]: 47
[max_stack]: 1
[max_locals]: 1
[code_length]: 5
[code]: 
[code]: 
42-aload_0
183-invokespecial
0-nop
1-aconst_null
177-return_
[exception_table_length]: 0
[attributes_count]: 2
[attribute_name_index]: 25-LineNumberTable
[attribute_length]: 6
[line_number_table_length]: 1
[row 0]: 
[start_pc]: 0
[line_number]: 3

[attribute_name_index]: 26-LocalVariableTable
[attribute_length]: 12
[line_variable_table_length]: 1
[0]: 
[start_pc]: 0
[length]: 5
[name_index]: 27-this
[descriptor_index]: 28-Lcom/ming/test/Simple;
[index]: 0

[method 2 ]: 
[access_flags]: 1-public 
[name_index]: 29-add
[descriptor_index]: 30-(II)I
[attributes_count]: 1

[1]: AttributeCodeInfo
[attribute_name_index]: 24-Code
[attribute_length]: 66
[max_stack]: 2
[max_locals]: 3
[code_length]: 4
[code]: 
[code]: 
27-iload_1
28-iload_2
96-iadd
172-ireturn
[exception_table_length]: 0
[attributes_count]: 2
[attribute_name_index]: 25-LineNumberTable
[attribute_length]: 6
[line_number_table_length]: 1
[row 0]: 
[start_pc]: 0
[line_number]: 9

[attribute_name_index]: 26-LocalVariableTable
[attribute_length]: 32
[line_variable_table_length]: 3
[0]: 
[start_pc]: 0
[length]: 4
[name_index]: 27-this
[descriptor_index]: 28-Lcom/ming/test/Simple;
[index]: 0
[1]: 
[start_pc]: 0
[length]: 4
[name_index]: 31-a
[descriptor_index]: 9-I
[index]: 1
[2]: 
[start_pc]: 0
[length]: 4
[name_index]: 32-b
[descriptor_index]: 9-I
[index]: 2

[method 3 ]: 
[access_flags]: 1-public 
[name_index]: 33-messger
[descriptor_index]: 23-()V
[attributes_count]: 1

[1]: AttributeCodeInfo
[attribute_name_index]: 24-Code
[attribute_length]: 88
[max_stack]: 3
[max_locals]: 3
[code_length]: 14
[code]: 
[code]: 
3-iconst_0
60-istore_1
5-iconst_2
61-istore_2
178-getstatic
0-nop
2-iconst_m1
27-iload_1
28-iload_2
96-iadd
182-invokevirtual
0-nop
3-iconst_0
177-return_
[exception_table_length]: 0
[attributes_count]: 2
[attribute_name_index]: 25-LineNumberTable
[attribute_length]: 18
[line_number_table_length]: 4
[row 0]: 
[start_pc]: 0
[line_number]: 13
[row 1]: 
[start_pc]: 2
[line_number]: 14
[row 2]: 
[start_pc]: 4
[line_number]: 15
[row 3]: 
[start_pc]: 13
[line_number]: 16

[attribute_name_index]: 26-LocalVariableTable
[attribute_length]: 32
[line_variable_table_length]: 3
[0]: 
[start_pc]: 0
[length]: 14
[name_index]: 27-this
[descriptor_index]: 28-Lcom/ming/test/Simple;
[index]: 0
[1]: 
[start_pc]: 2
[length]: 12
[name_index]: 34-c
[descriptor_index]: 9-I
[index]: 1
[2]: 
[start_pc]: 4
[length]: 10
[name_index]: 35-d
[descriptor_index]: 9-I
[index]: 2


[attributes_count]: 1
[attributes]: 

[0]: AttributeSourceFileInfo
[attribute_name_index]: 36-SourceFile
[attribute_length]: 2
[sourcefile_index]: 37-Simple.java


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
        + InterfaceInfo.java
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
        + Child.java
        + Parent.java
        + Simple.java
        + Test.java
      - util
        + PrintFormatUtils  
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


