package com.stream.it;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * 流的重定向
 * */
public class RedirectOut {

	String path = "E:\\Test\\test.txt";
	String path1 = "E:\\Test\\1.txt";

	/**
	 * 输出流从定向
	 * */
	private void out() {
		PrintStream ps = null;

		try {
			ps = new PrintStream(new FileOutputStream(path));// 一次性创建PrintStream输出流

			System.setOut(ps);// 将标注输出重定向到ps输出流

			System.out.println("天行健，君子以自强不息");// 向标准输出输出一个字符串

			System.out.println(new RedirectOut());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (ps != null) {
				ps.close();
			}
		}

	}

	public static void main(String[] args) {

		System.out.println("Hello World!!!");
		System.out.println(Math.PI);
		//new RedirectOut().out();
		new RedirectOut().input();
	}

	/**
	 * 输入流重定向
	 * */
	private void input() {
		FileInputStream stream = null;

		try {
			stream = new FileInputStream(path);
			System.setIn(stream);// 将标准输入重定向到stream输入流

			Scanner sc = new Scanner(System.in);// 检测键盘，获取标准输入
			sc.useDelimiter("\n");// 增加下面一行将只把回车作为分隔符
			// 判断是否还有下一个输入项
			while (sc.hasNext()) {
				System.out.println("键盘输入内容是：" + sc.next());

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(stream!=null){
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
