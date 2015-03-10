package com.stream.it;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class FileInputStreamTest {
	String path = "C:\\Users\\user.MICROSO-02V570Q.000\\Desktop\\小工具\\Linux常用命令大全.txt";
	String path1 = "C:\\Users\\user.MICROSO-02V570Q.000\\Desktop\\小工具\\π.txt";
	String path2 = "C:\\Users\\user.MICROSO-02V570Q.000\\Desktop\\小工具\\python笔记.doc";
	public static void main(String[] args) {
		new FileInputStreamTest().read();
		new FileInputStreamTest().input();
	}

	/**
	 * 读取字节流
	 * */
	private void input() {
		FileInputStream stream = null;

		try {
			stream = new FileInputStream(path1);// 创建字节输入流

			byte[] b = new byte[1024]; // 创建一个长度是1024的byte数组（竹筒）

			int hasRead = 0;// 用于保存实际读取的字节数

			// 使用循环来重复取数据（取水）
			while ((hasRead = stream.read(b)) > 0) {
				// 取出竹筒中的水滴（字节），将字节数组转换成字符串
				System.out.println(new String(b, 0, hasRead));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 读取字符流
	 * **/
	private void read() {
		FileReader reader = null;

		try {
			reader = new FileReader(path2);// 创建字符输入流

			char[] c = new char[32];// 创建一个长度是32的竹筒

			int hasRead = 0;// 用于保存实际读取的字节数

			// 使用循环来重复取数据（取水）
			while ((hasRead = reader.read(c)) > 0) {
				System.out.println(new String(c, 0, hasRead));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

}
