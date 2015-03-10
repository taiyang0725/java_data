package com;

public class Test {//二分法
	public static int binary(int[] array, int value) {
		int low = 0;
		int high = array.length - 1;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (value == array[middle]) {
				return middle;
			}
			if (value > array[middle]) {
				low = middle + 1;
			}
			if (value < array[middle]) {
				high = middle - 1;
			}
		}
		return -1;
	}
//递归法
	public  int fact(int a) {
		if (a == 1) {
			return 1;
		}
		return a * fact(a - 1);

	}

	public static void main(String[] args) {
//		int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		int value = binary(a, 9);
//		 System.out.println(value);
		Test t=new Test();
		int c=t.fact(10);
		System.out.println(c);
	}
}
