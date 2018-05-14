package com.ming.base;

import com.ming.base.constant.ConstantClassInfo;
import com.ming.base.constant.ConstantDoubleInfo;
import com.ming.base.constant.ConstantFieldrefInfo;
import com.ming.base.constant.ConstantFloatInfo;
import com.ming.base.constant.ConstantIntegerInfo;
import com.ming.base.constant.ConstantLongInfo;
import com.ming.base.constant.ConstantMethodrefInfo;
import com.ming.base.constant.ConstantNameAndTypeInfo;
import com.ming.base.constant.ConstantStringInfo;
import com.ming.base.constant.ConstantUtf8Info;
import com.ming.io.ClassFileReader;

public class ConstantInfo {

	private static final int NULL = 0;
	private static final int CONSTANT_Utf8_info = 1;
	private static final int CONSTANT_Integer_info = 3;
	private static final int CONSTANT_Float_info = 4;
	private static final int CONSTANT_Long_info = 5;
	private static final int CONSTANT_Double_info = 6;
	private static final int CONSTANT_Class_info = 7;
	private static final int CONSTANT_String_info = 8;
	private static final int CONSTANT_Fieldref_info = 9;
	private static final int CONSTANT_Methodref_info = 10;
	private static final int CONSTANT_NameAndType_info = 12;


	public ConstantInfo() {

	}

	public static ConstantInfo getSpecificConstantInfo(int tag, ClassFileReader cfr) {
		switch (tag) {
			case CONSTANT_Utf8_info:
				return new ConstantUtf8Info(cfr);
			case CONSTANT_Integer_info:
				return new ConstantIntegerInfo();
			case CONSTANT_Float_info:
				return new ConstantFloatInfo();
			case CONSTANT_Long_info:
				return new ConstantLongInfo();
			case CONSTANT_Double_info:
				return new ConstantDoubleInfo();
			case CONSTANT_Class_info:
				return new ConstantClassInfo(cfr);
			case CONSTANT_String_info:
				return new ConstantStringInfo(cfr);
			case CONSTANT_Fieldref_info:
				return new ConstantFieldrefInfo(cfr);
			case CONSTANT_Methodref_info:
				return new ConstantMethodrefInfo(cfr);
			case CONSTANT_NameAndType_info:
				return new ConstantNameAndTypeInfo(cfr);
			default:
				throw new RuntimeException("can't find specific type");
		}
	}

}
