package com.ming.test;

public class Simple implements Parent, Child{
    private int data;
	private static final String flag= "HelloWorld";
	private static final long num = 1L;
	private static final double dou = 0.1;
    public int add(int a, int b) {
      return a + b;
    }
	
	public void messger() {
		int c = 0;
		int d = 2;
		System.out.println(c + d);
	}
}