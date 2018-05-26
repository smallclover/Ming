# ConstantValue_attribute

## 说明

+ ConstantValue 属性位于 field_info结构的属性表中。
+ ConstantValue 属性表示一个常量字段的值。在一个 field_info 结构的属性表中最多只能有
一个 ConstantValue 属性。
+ 如果该字段为静态类型（即 field_info 结构的 access_flags
项设置了 ACC_STATIC 标志），则说明这个 field_info 结构表示的常量字段值将被
分配为它的 ConstantValue 属性表示的值。

## 结构

```cpp
ConstantValue_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 constantvalue_index;
}
```

## 结构项说明

+ attribute_name_index 指向常量池的有效索引，表示字符串“ConstantValue”。
+ attribute_length 值固定为 2。
+ constantvalue_index 指向常量池的有效索引。 常量池在该索引处的项给出该属性表示的常量值。