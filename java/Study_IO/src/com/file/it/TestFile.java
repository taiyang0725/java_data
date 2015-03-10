package com.file.it;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class TestFile {
	public static void main(String[] args) throws IOException {
		//fileTest();
		fileGLQ();
		

	}


	private static void fileGLQ() {
		File file = new File(".");
		String [] fileList = file.list(new MyFilenameFilter());
		for (String string : fileList) {
			System.out.println(string);
		}
	}


	private static void fileTest() throws IOException {
		// 以当前路径来创建一个File对象
		File file = new File(".");
		// 直接获取文件名，输出一点
		System.out.println(file.getName());
		// 获取相对路径的父路径可能出错
		System.out.println(file.getParent());
		// 获取绝对路径
		System.out.println(file.getAbsoluteFile());
		// 获取上一级路径
		System.out.println(file.getAbsoluteFile().getParent());

		// 在当前路径下创建一个临时文件
		File tmFile = File.createTempFile(System.currentTimeMillis() + "QQ",
				".txt", file);

		// 指定当JVM退出时删除该文件
		tmFile.deleteOnExit();

		// 以系统时间作为新文件名来创建新文件
		File newFile = new File(System.currentTimeMillis() + ".txt");
		System.out.println("newFile对象是否存在：" + newFile.exists());

		// 以指定newFile对象创建一个文件
		newFile.createNewFile();
		// 以newFile对象来创建一个目录，因为newFile已经存在
		// 所以下面方法返回false，既无法创建该目录
		newFile.mkdir();

		// 使用List方法来列出当前路径下的所有文件和路径
		String[] fileList = file.list();
		System.out.println("===============当前路径下所有文件如下=================");
		for (String string : fileList) {
			System.out.println(string);
		}

		// listroots静态方法列出所有的磁盘根路径
		File[] roots = File.listRoots();
		System.out.println("==============系统所有根路径如下====================");
		for (File file2 : roots) {
			System.out.println(file2);
		}
	}
	
	
	
}
//文件过滤器
	class MyFilenameFilter implements FilenameFilter{

		@Override
		public boolean accept(File dir, String name) {
			
			//文件名以Java结尾，或者文件对应一个路径，返回true
			return name.endsWith(".java") || 
					new File(name).isDirectory();
		}
		
	}
