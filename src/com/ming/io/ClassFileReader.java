package com.ming.io;

import com.ming.core.U1;
import com.ming.core.U2;
import com.ming.core.U4;
import com.ming.core.U8;

/**
 *
 * @author smallclover
 * 提供U1，U2, U4, U8 四个方法来读取无符号整数
 */
public class ClassFileReader {

	private byte[] classfile;
	private int currentIndex = 0;

	public ClassFileReader(byte[] classfile) {
		this.classfile = classfile;
	}

	/**
	 * read 1 byte unsigned integer
	 * 读取一个字节的无符号整数
	 * @return
	 */
	public U1 readU1() {
		U1 u1 = new U1(classfile[currentIndex]);
		currentIndex++;
		return u1;
	}

	/**
	 * read 2 byte unsigned integer
	 * 读取两个字节的无符号整数
	 * @return
	 */
	public U2 readU2() {
		byte[] uint16 = new byte[2];
		for(int i = 0; i < 2; i ++) {
			uint16[i] = classfile[currentIndex];
			currentIndex++;
		}
		U2 u2 = new U2(uint16);
		return u2;
	}

	/**
	 * read 4 byte unsigned integer
	 * 读取四个字节的无符号整数
	 * @return
	 */
	public U4 readU4() {
		byte[] uint32 = new byte[4];
		for(int i = 0; i < 4; i ++) {
			uint32[i] = classfile[currentIndex];
			currentIndex++;
		}
		U4 u4 = new U4(uint32);
		return u4;
	}

	/**
	 * 读取8个字节的无符号整数
	 * @return
	 */
	public U8 readU8() {
		byte[] uint64 = new byte[8];
		for (int i = 0;i < 8; i ++) {
			uint64[i] = classfile[currentIndex];
			currentIndex++;
		}

		U8 u8 = new U8(uint64);
		return u8;
	}

	private void checkIndex() {
		if(currentIndex >= classfile.length) {
			throw new ArrayIndexOutOfBoundsException();
		}
	}
}
