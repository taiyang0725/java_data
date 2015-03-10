package com.stream.it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KeyInTest {

	private void study() {
		BufferedReader br = null;
		try {

			InputStreamReader reader = new InputStreamReader(System.in);// 将System.in对象转换成Reader对象

			br = new BufferedReader(reader);// 将普通的Reader包装成BufferedReader

			String buffer = null;
			// 采用循环方式一行一行读取
			while ((buffer = br.readLine()) != null) {
				// 如果读取字符串为“exit”，程序退出
				if (buffer.equals("exit")) {
					System.exit(1);
				}
				System.out.println("输入内容为：" + buffer);// 打印读取内容
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new KeyInTest().study();
	}

}
