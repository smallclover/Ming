# CONSTANT_Class_info

## 说明

  用来表示类或接口
  
## 结构

```java
CONSTANT_Class_info {
  u1 tag;
  u2 name_index;
}

```
## 结构项说明
+ tag 表示该常量池项的类型，CONSTANT_Class_info的tag值是7
+ name_index 对常量池的一个有效索引。指向常量池中[CONSTANT_Utf8_info](CONSTANT_Utf8_info.md)类型的项，表示一个有效的类或接口二进制名称的内部形式
