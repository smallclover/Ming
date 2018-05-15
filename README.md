# Ming
Class文件解析器
## 关于
对Java编译后的Class文件进行解析

## 使用方法

### 环境
JDK9 以上，包括JDK9
不支持JDK8的原因是因为，使用了InputStream#readAllBytes()方法，后来察看源码才知道，该方法是JDK9才加进来的，其实不用也可以的（笑）

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

找到 ```java  com.ming.main.ClassFileParser.java``` 文件，
修改```java defaultClassFilePath```字段为```java HelloWorld.class ```文件的路径
然后运行即可在控制台打印出class文件的解析结果。


## 进展

项目 | 是否完成 | 进度
---- | ---- | ----
magic | √ | 100%
minor_version | √ | 100%
major_version | √ | 100%
constant_pool_count | √ | 100%
constant_pool[constant_pool_count] | √ | 100%
access_flags | √ | 100%
this_class | √ | 100%
super_class | √ | 100%
interfaces_count | √ | 100%
interfaces[interfaces_count] | × | 0%
fields_count | √ | 100%
fields[fields_count] | × | 20%
methods_count | √ | 100%
methods[methods_count] | √ | 100%
attributes_count | × | 20%
attributes[attributes_count] | × | 20%

## 目录说明（暂时，会根据改变更新）

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
      - io
        + ClassFileReader.java
      - test
        + ClassFileParser.java


## Class文件结构

```java
ClassFile {
  u4 magic;	// 魔数 0xcafebabe
  u2 minor_version;	// 次版本号
  u2 major_version;	// 主版本号
  u2 constant_pool_count;	// 常量池大小
  cp_info constant_pool[constant_pool_count-1]; // 常量池
  u2 access_flags;	// 类访问标志,表明class文件定义的是类还是接口，访问级别是public还是private，等
  u2 this_class;	// 类本身
  u2 super_class;	// 类的父类
  u2 interfaces_count;	// 本类实现的接口数量
  u2 interfaces[interfaces_count];	// 类实现的接口
  u2 fields_count;		// 本来中含有字段数
  field_info fields[fields_count];	// 类的各个字段，注意区别于局部变量
  u2 methods_count;		// 本类中含有的方法数
  method_info methods[methods_count];	// 类的各个方法
  u2 attributes_count;			// 本类中含有的属性数量;
  attribute_info attributes[attributes_count];	// 类的各个属性
}
```

### 

