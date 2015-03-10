package com.special.frame.it;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class TestSplitPane {
	
	Book[] books = new Book[] {
			new Book("Struts2权威指南", new ImageIcon("image/1.png"),
					"全面介绍Struts2权威指南\n知识"),
			new Book("轻量级J2EE", new ImageIcon("image/2.png"),
					"全面介绍Struts2权威指南\n知识"),
			new Book("基于J2EE的Ajax", new ImageIcon("image/3.png"),
					"全面介绍Struts2权威指南\n知识")
	};
	JFrame jf=new JFrame("Test");
	JList bookList=new JList(books);
	JLabel bookCover=new JLabel();
	JTextArea bookDesc=new JTextArea();
	
	private void init() {
		
		//为三个组件设置最佳大小
		bookList.setPreferredSize(new Dimension(150, 300));
		bookCover.setPreferredSize(new Dimension(300, 150));
		bookDesc.setPreferredSize(new Dimension(300, 150));
		
		bookList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Book book=(Book) bookList.getSelectedValue();
				bookCover.setIcon(book.getIcon());
				bookDesc.setText(book.getDesc());
			}
		});
		
		//创建一个垂直的分割面板
		//将bookCover放在上面，将bookDesc放在下面，支持连续布局
		
		JSplitPane left=new JSplitPane(JSplitPane.VERTICAL_SPLIT,true,
				bookCover,new JScrollPane(bookDesc));
		
		//打开一触即发的特性
		left.setOneTouchExpandable(true);
		
		//设置分割线的大小
		//left.setDividerSize(50);
		
		//设置分割面板根据所包含组件的最佳大小来调整布局
		left.resetToPreferredSizes();
		
		//创建一个水平的分割面板
		//将left组件放在左边，将bookList组件放在右边
		JSplitPane contant=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				left,new JScrollPane(bookList));
		
		jf.add(contant);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new TestSplitPane().init();
	}

}
