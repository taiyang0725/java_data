package com.cike.it;

import java.util.ArrayList;
import java.util.Collection;

public class Apple <T>{
	
	private T info;

	
	public Apple() {
		super();
		
	}

	public Apple(T info) {
		super();
		this.info = info;
	}

	public T getInfo() {
		return info;
	}

	public void setInfo(T info) {
		this.info = info;
	}

	public static void main(String[] args) {
		//传给T形参是String的实际类型，所以构造方法的参数只能是String
		Apple<String> apple=new Apple<String>("Hello World!");
		System.out.println(apple.getInfo());
		Apple<Integer> apple1=new Apple<Integer>(100);
		System.out.println(apple1.getInfo());
		
		
		String []str=new String[10];
		Collection<String> c=new ArrayList<String>();
		fromArrayToCollection(str, c);
		
		
	}
	/*泛型方法
	 * 修饰符 <T , S> 返回值类型  方法名（形参列表）
	 * {
	 *    方法体
	 * }
	 */
	//将一个数组元素添加到Collection集合中
	public static <T> void fromArrayToCollection(T [] a,Collection<T> c){
		
		for (T t : a) {
			c.add(t);
		}
		
	}
}
