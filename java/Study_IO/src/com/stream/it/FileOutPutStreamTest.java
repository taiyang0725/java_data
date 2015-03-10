package com.stream.it;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileOutPutStreamTest {
	String path = "C:\\Users\\user.MICROSO-02V570Q.000\\Desktop\\小工具\\Linux常用命令大全.txt";
	String path1 = "C:\\Users\\user.MICROSO-02V570Q.000\\Desktop\\小工具\\π.txt";
	String path2 = "C:\\Users\\user.MICROSO-02V570Q.000\\Desktop\\小工具\\π_new.txt";

	private int hasRead;

	public static void main(String[] args) {
		// new FileOutPutStreamTest().output();
		// new FileOutPutStreamTest().writeData();
		new FileOutPutStreamTest().addData();
		new FileOutPutStreamTest().insertData();

		System.out.println("Great Wall");
	}

	/**
	 * 输出字节流
	 * */
	private void output() {

		FileInputStream inputStream = null;// 输入流
		FileOutputStream outputStream = null;// 输出流

		try {
			inputStream = new FileInputStream(path1);// 创建字节输入流
			outputStream = new FileOutputStream(path2);// 创建字节输出流

			byte[] b = new byte[32];

			// 循环从输入流中取出数据
			while ((hasRead = inputStream.read(b)) > 0) {

				// 每读一次，及写入文件输出流，边读边写
				outputStream.write(b, 0, hasRead);

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 输出字符流
	 * */

	private void writeData() {
		FileWriter writer = null;

		try {
			writer = new FileWriter(path2);
			writer.write("        锦瑟-李商隐\r\n");
			writer.write("锦瑟无端五十弦，一弦一柱思华年。\r\n");
			writer.write("庄生晓梦迷蝴蝶，望帝春心托杜鹃。\r\n");
			writer.write("沧海月明珠有泪，蓝田玉暖玉生烟。\r\n");
			writer.write("此情可待成追忆，只是当时已惘然。\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 向文件最后追加内容的方法
	 * */
	private void addData() {
		RandomAccessFile raf = null;

		try {
			raf = new RandomAccessFile(new File(path2), "rw");// 以读写方式打开一个RandomAccessFile对象

			raf.seek(raf.length());// 将记录指针移到文件的最后

			raf.write("好漂亮的诗啊！\r\n".getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (raf != null) {
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	/**
	 * 向文件指定地方插入数据的方法
	 * */
	private void insertData() {
		RandomAccessFile raf=null;
		FileOutputStream tmpout=null;
        FileInputStream tmpInput=null;
        
		try {
			File tmp=File.createTempFile("tmp",null);//创建一个临时文件来保存插入点后的数据
			tmp.deleteOnExit();
			
			raf=new RandomAccessFile(new File(path2),"rw");
			tmpout=new FileOutputStream(tmp);
			tmpInput=new FileInputStream(tmp);
			raf.seek(200);
			
			/*-------------下面将插入点后的内容读入临时文件中保存-----*/
			byte[] bbuf=new byte[1024];
			//用于保存实际读取的字节数
			int hasRead=0;
			//使用循环的方式读取插入点后的数据
			while((hasRead=raf.read(bbuf))>0){
				tmpout.write(bbuf, 0, hasRead);
				
			}
			/*插入内容*/
			raf.seek(200);//把文件记录指针重新定位到100位置
			raf.write("千呼万唤始出来,犹抱琵琶半遮面\r\n".getBytes());//追加需要插入的内容
			while ((hasRead=tmpInput.read(bbuf))>0) {
				raf.write(bbuf,0,hasRead);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(raf!=null){
				try {
					raf.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
