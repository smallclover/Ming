package com.ming.core;

/**
 *
 * @author smalllclover
 * class access and property modifiers
 */
public class Modifier {
	private static final int ACC_PUBLIC = 0x0001;
	private static final int ACC_PRIVATE = 0x0002;
	private static final int ACC_STATIC = 0x0008;
	private static final int ACC_FINAL = 0x0010;
	private static final int ACC_SUPER = 0x0020;
	private static final int ACC_INTERFACE = 0x0200;
	private static final int ACC_ABSTRACT = 0x0400;
	private static final int ACC_SYNTHETIC = 0x1000;
	private static final int ACC_ANNOTATION = 0x2000;
	private static final int ACC_ENUM = 0x4000;


	public Modifier() {
	}

	/**
	 * 根据传来的值，返回指定的访问标识符
	 * @param value
	 * @return
	 */
	public String getAccessFlag(int value) {
		StringBuffer sb = new StringBuffer();
		if ((value & ACC_PUBLIC) > 0) {
			sb.append("public ");
		}

		if ((value & ACC_PRIVATE) > 0) {
			sb.append("private ");
		}

		if ((value & ACC_FINAL) > 0) {
			sb.append("final ");
		}

		if ((value & ACC_SUPER) > 0) {
			sb.append("super ");
		}

		if ((value & ACC_INTERFACE) > 0) {
			sb.append("interface ");
		}

		if ((value & ACC_ABSTRACT) > 0) {
			sb.append("abstract ");
		}

		if ((value & ACC_SYNTHETIC) > 0) {
			sb.append("synthetic ");
		}

		if ((value & ACC_ANNOTATION) > 0) {
			sb.append("annotation ");
		}

		if ((value & ACC_ENUM) > 0) {
			sb.append("enum ");
		}

		if ((value & ACC_STATIC) > 0) {
			sb.append("static ");
		}

		return sb.toString();
	}

	public static Modifier getModifier() {
		return new Modifier();
	}
}
