# 常量池(ConstantPool)

## 常量池通用格式

```java

cp_info {
  u1 tag;
  u1 info[];
}

```
## 常量池类型与其对应的值

常量池类型 | 值
--------- | ---
CONSTANT_Class | 7
CONSTANT_Fieldref | 9
CONSTANT_Methodref | 10
CONSTANT_InterfaceMethodref | 11
CONSTANT_String | 8
CONSTANT_Integer | 3
CONSTANT_Float | 4
CONSTANT_Long | 5
CONSTANT_Double | 6
CONSTANT_NameAndType | 12
CONSTANT_Utf8 | 1
CONSTANT_MethodHandle | 15
CONSTANT_MethodType | 16
CONSTANT_InvokeDynamic | 18

### 常量池类型说明
  + [CONSTANT_Class](CONSTANT_Class.md)
