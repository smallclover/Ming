# Code_attribute

## 说明
Code_attribute 属于method_info的属性表，一个方法只有一个Code属性，如果方法被声明为native或者abstract类型，
那么对应的method_info结构没有Code属性。

## 格式

```cpp

Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    { u2 start_pc;
    u2 end_pc;
    u2 handler_pc;
    u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}

```

## 结构项说明
+ attribute_name_index 指向常量池的索引，表示字符串"Code"

+ attribute_length　当前属性的长度，不包括开始的6个字节（u2 + u4）
+ max_stack 当前方法的操作数栈在运行执行的的最大深度
+ max_locals 用于存放方法参数和方法内部定义的局部变量
+ code_length 当前方法的 code[]数组的字节数①， code_length 的值必须大于 0，即 code[]数组不能为空。
+ code[code_length] code[]数组给出了实现当前方法的 Java 虚拟机字节码
+ exception_table_length exception_table[]数组的成员个数量。
+ exception_table[exception_table_length] 数组的每个成员表示 code[]数组中的一个异常处理器（try/catch），
  - start_pc
  - end_pc
  - handler_pc
  - catch_type
+ attributes_count Code 属性中 attributes 表的成员个数。
+ attributes[attributes_count] 一个 Code 属性可以有任意数量的可选属性与之关联

