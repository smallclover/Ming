package com.ming.core;

/**
 *
 * @author smalllclover
 * class access and property modifiers
 */
public class Modifier {
	public static final int ACC_PUBLIC = 0x0001;
	public static final int ACC_FINAL = 0x0010;
	public static final int ACC_SUPER = 0x0020;
	public static final int ACC_INTERFACE = 0x0200;
	public static final int ACC_ABSTRACT = 0x0400;
	public static final int ACC_SYNTHETIC = 0x1000;
	public static final int ACC_ANNOTATION = 0x2000;
	public static final int ACC_ENUM = 0x4000;

	public static void main(String[] args) {
		System.out.println(Integer.toHexString(ACC_PUBLIC | ACC_SUPER));
		if (1 == ACC_PUBLIC) {
			System.out.println("yes");
		}
	}
}
