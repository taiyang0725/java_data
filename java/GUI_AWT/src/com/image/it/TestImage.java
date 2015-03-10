package com.image.it;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.ScrollPane;
import java.awt.TextField;

public class TestImage {
	private static Frame frame = new Frame("测试窗口");
	private static GridBagLayout gb=new GridBagLayout();
	private static GridBagConstraints gbc=new GridBagConstraints();
	
	public static void main(String[] args) {
		
		//scrollContainer();
		//setFlowlayout();
		//setBorderayout();
		setGridBagLayout();
	}
     /*Frame容器*/
	public static void frameContainer() {
		// 创建一个panel容器
		Panel panel = new Panel();
		// 向容器中添加组件
		panel.add(new TextField(20));
		panel.add(new Button("Hello World"));
		// 将Panal容器添加到Frame窗口中
		frame.add(panel);
		// 设置窗口大小、位置
		frame.setBounds(30, 30, 300, 300);
		// 将窗口显示出来（默认处于隐藏状态）
		frame.setVisible(true);

	}
	/*ScrollPane容器,一个带滚动条的容器*/
	public static void scrollContainer() {
		// 创建一个ScrollPane容器,指定总是具有滚动条
		ScrollPane pane=new ScrollPane(ScrollPane.SCROLLBARS_ALWAYS);
		// 向容器中添加组件
		pane.add(new TextField(20));
		pane.add(new Button("Hello World"));
		// 将Pana容器添加到Frame窗口中
		frame.add(pane);
		// 设置窗口大小、位置
		frame.setBounds(30, 30, 300, 300);
		// 将窗口显示出来（默认处于隐藏状态）
		frame.setVisible(true);

	}
	/*Java中的布局*/
	//Flowlayout  组件像流水一样，遇到障碍就折回，重头开始
	public static void setFlowlayout(){
		// 设置Frame容器使用FlowLayout布局管理器
		frame.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		//向窗口中10个按钮
		for (int i = 0; i <10; i++) {
			frame.add(new Button("cike"+i));
		}
		//设置窗口为最佳大小
		frame.pack();
		frame.setVisible(true);	
	}
	//Borderayout 将容器分为 EAST、SOUTH、WEST、NORTH、CENTER五个区域
		public static void setBorderayout(){
			Frame f = new Frame("测试窗口");
			// 设置Frame容器使用BorderLayout布局管理器
			f.setLayout(new BorderLayout(30,5));//30.5表示水平间距，垂直间距
			f.add(new Button("A"),BorderLayout.EAST);
			f.add(new Button("B"),BorderLayout.SOUTH);
			f.add(new Button("C"),BorderLayout.WEST);
			f.add(new Button("D"),BorderLayout.NORTH);
			f.add(new Button("E"));
			//设置窗口为最佳大小
			f.pack();
			f.setVisible(true);	
		}
		private static void addBtn(Button btn){
			gb.setConstraints(btn, gbc);
			frame.add(btn);	
		}
		//GridBagLayout 功能强大的布局管理器
		public static void setGridBagLayout(){
		    
			Button [] btn=new Button[10];
			frame.setLayout(gb);
			for (int i = 0; i < btn.length; i++) {
				btn[i]=new Button("A"+i);
			}
			//所有组件都可以横向、纵向上扩大
			gbc.fill=GridBagConstraints.BOTH;
			
			gbc.weightx=1;
			addBtn(btn[0]);
			addBtn(btn[1]);
			addBtn(btn[2]);
			//该GridBagConstraints控制的GUI组件将会成为横向的最后一个元素
			gbc.gridwidth=GridBagConstraints.REMAINDER;
			addBtn(btn[3]);
			
			//该GridBagConstraints控制的GUI组件将会成为横向的最后一个元素
			gbc.weightx=0;
			addBtn(btn[4]);
			
			gbc.gridwidth=2;//该GridBagConstraints控制的GUI组件将横跨两个网格
			addBtn(btn[5]);
			
			//gbc.gridwidth=1;//该GridBagConstraints控制的GUI组件将横向跨一个网格
			//gbc.gridheight=2;//该GridBagConstraints控制的GUI组件将纵向跨两个网格
			gbc.gridwidth=GridBagConstraints.REMAINDER;//该GridBagConstraints控制的GUI组件将会成为横向的最后一个元素
			addBtn(btn[6]);
			
			gbc.gridwidth=1;//该GridBagConstraints控制的GUI组件将横向跨一个网格
			gbc.gridheight=2;//该GridBagConstraints控制的GUI组件将纵向跨两个网格
			gbc.weighty=1;////该GridBagConstraints控制的GUI组件纵向扩大的权重是1
			addBtn(btn[7]);
			
			gbc.weighty=0;//设置按钮在纵向上不会扩张
			gbc.gridwidth=GridBagConstraints.REMAINDER;//该GridBagConstraints控制的GUI组件将会成为横向的最后一个元素
			//gbc.gridheight=1;//该GridBagConstraints控制的GUI组件将纵向跨一个网格
			addBtn(btn[8]);
			
			addBtn(btn[9]);
			
			frame.pack();
			frame.setVisible(true);	
		}
}
