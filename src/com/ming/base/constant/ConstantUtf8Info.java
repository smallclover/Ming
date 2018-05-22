package com.ming.base.constant;

import com.ming.base.ConstantInfo;
import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.io.ClassFileReader;

/**
 * @author smallclover
 * constant_utf8_info
 * 用于表示字符串常量的值
 * tag = 1
 */
public class ConstantUtf8Info extends ConstantInfo {
	private static final U1 tag = new U1(1);
	private U2 length = null;
	private U1[] u1 = null;

	public ConstantUtf8Info(ClassFileReader cfr)  {
		length = cfr.readU2();
		u1 = new U1[length.getValue()];
		for(int i = 0; i < u1.length; i ++) {
			u1[i] = cfr.readU1();
		}
	}

	public String getValue() {
		StringBuffer sb = new StringBuffer();
		for (U1 content : u1) {
			sb.append(content.toHex());
		}
		return sb.toString();
	}


	public String convertHexToString(){
		String hex = getValue();

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		//49204c6f7665204a617661 split into two characters 49, 20, 4c...
		for( int i = 0; i < hex.length() - 1; i += 2 ){

			//grab the hex in pairs
			String output = hex.substring(i, (i + 2));
			//convert hex to decimal
			int decimal = Integer.parseInt(output, 16);
			//convert the decimal to character
			sb.append((char)decimal);

			temp.append(decimal);
		}

		return sb.toString();
	}

	@Override
	public String toString() {
		return "[tag]: " + tag.getValue() + "\n"
				+ "[length]: " + length.getValue() + "\n"
				+ "[bytes]: " + convertHexToString() + "\n";
	}
}
