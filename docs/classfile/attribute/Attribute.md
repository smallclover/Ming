# 属性(Attribute)

## 属性通用格式

```cpp
attribute_info {  
    u2 attribute_name_index;  
    u4 attribute_length;  
    u1 info[attribute_length]; 
} 
```

## 属性类型

+ ConstantValue
+ Code
+ StackMapTable
+ Exceptions
+ InnerClasses
+ EnclosingMethod
+ Synthetic
+ Signature
+ SourceFile
+ SourceDebugExtension
+ LineNumberTable
+ LocalVariableTable
+ LocalVariableTypeTable
+ Deprecated
+ RuntimeVisibleAnnotations
+ RuntimeInvisibleAnnotations
+ RuntimeVisibleParameterAnnotations
+ RuntimeInvisibleParameterAnnotations
+ AnnotationDefault
+ BootstrapMethods