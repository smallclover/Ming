# CONSTANT_Fieldref_info

## 说明

表示类中的字段

## 结构

```java
CONSTANT_Fieldref_info {
   u1 tag;
   u2 class_index;
   u2 name_and_type_index;
}
```
## 结构项说明
+ tag 表示该常量池项的类型，CONSTANT_Class_info的tag值是9
+ class_index 对常量池的一个有效索引。指向常量池中[CONSTANT_Class_info](CONSTANT_Class_info.md)类型的项，表示当前字段或方法是这
个类或接口的成员
+ name_and_type_index 对常量池的一个有效索引。指向常量池中[CONSTANT_NameAndType_info](CONSTANT_NameAndType_info.md)类型的项，表示当前字段或方法的名
字和描述符
