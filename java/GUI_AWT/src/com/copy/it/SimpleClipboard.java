package com.copy.it;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;

public class SimpleClipboard {

	private Frame f = new Frame("简单的剪贴板程序");
	// 获取系统剪贴板
	private Clipboard clipboard = Toolkit.getDefaultToolkit()
			.getSystemClipboard();
	/*
	 * 创建本地剪贴板的代码
	 * private Clipboard clipboard=new Clipboard("cb");
	 */
	private TextArea jtaCopyTo = new TextArea(5, 20);// 用于复制文本的文本框
	private TextArea jtaPaste = new TextArea(5, 20);// 用于粘贴文本的文本框

	private Button btnCopy = new Button("Copy");
	private Button btnPaste = new Button("Paste");

	private void init() {

		Panel p = new Panel();
		p.add(btnCopy);
		p.add(btnPaste);

//		ActionListener actionListener = new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				if (e.getActionCommand().equals("Copy")) {
//					// 将一个多行文本域里的字符串封装成StringSelection对象
//					StringSelection contents = new StringSelection(
//							jtaCopyTo.getText());
//					// 将StringSelection对象放入剪贴板
//					clipboard.setContents(contents, null);
//
//				}
//				if (e.getActionCommand().equals("Paste")) {
//					// 如果剪贴板中包含stringFlavor内容
//					if (clipboard
//							.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
//						try {
//							String content = (String) clipboard
//									.getData(DataFlavor.stringFlavor);
//							jtaPaste.append(content);
//						} catch (UnsupportedFlavorException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						} catch (IOException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//
//					}
//
//				}
//
//			}
//		};
//		btnCopy.addActionListener(actionListener);
//		btnPaste.addActionListener(actionListener);

		Box box = new Box(BoxLayout.X_AXIS);// 创建一个水平排列的BOX容器
		box.add(btnCopy);
		box.add(btnPaste);
		

		f.add(p, BorderLayout.SOUTH);
		f.add(box, BorderLayout.CENTER);
		
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				
				System.exit(0);
			}
		});

		f.pack();
		f.setVisible(true);

	}

	public static void main(String[] args) {
		new SimpleClipboard().init();

	}

}
