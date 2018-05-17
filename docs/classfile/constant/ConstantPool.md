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
CONSTANT_Class_info | 7
CONSTANT_Fieldref_info | 9
CONSTANT_Methodref_info | 10
CONSTANT_InterfaceMethodref_info | 11
CONSTANT_String_info | 8
CONSTANT_Integer_info | 3
CONSTANT_Float_info | 4
CONSTANT_Long_info | 5
CONSTANT_Double_info | 6
CONSTANT_NameAndType_info | 12
CONSTANT_Utf8_info | 1
CONSTANT_MethodHandle_info | 15
CONSTANT_MethodType_info | 16
CONSTANT_InvokeDynamic_info | 18

### 常量池类型说明
  + [CONSTANT_Class_info](CONSTANT_Class_info.md)
  + [CONSTANT_Fieldref_info](CONSTANT_Fieldref_info.md)
  + [CONSTANT_Methodref_info](CONSTANT_Methodref_info.md)
  + [CONSTANT_InterfaceMethodref_info](CONSTANT_InterfaceMethodref_info.md)
  + [CONSTANT_String_info](CONSTANT_String_info.md)
  + [CONSTANT_Integer_info](CONSTANT_Integer_info.md)
  + [CONSTANT_Float_info](CONSTANT_Float_info.md)
  + [CONSTANT_Long_info](CONSTANT_Long_info.md)
  + [CONSTANT_Double_info](CONSTANT_Double_info.md)
  + [CONSTANT_NameAndType_info](CONSTANT_NameAndType_info.md)
  + [CONSTANT_Utf8_info](CONSTANT_Utf8_info.md)
  + [CONSTANT_MethodHandle_info](CONSTANT_MethodHandle_info.md)
  + [CONSTANT_InvokeDynamic_info](CONSTANT_InvokeDynamic_info.md)
