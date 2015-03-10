package com.drawImage.it;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;


public class DrawImage {
	
	private final String RECT_SHAPE="rect";//矩形
	private final String OVAL_SHAPE="oval";//椭圆
	
	private Frame f =new Frame("Draw-Image");
	
	private Button rect=new Button("Draw_rect");
	private Button oval=new Button("Draw_oval");
	
	private MyCanvas drawArea=new MyCanvas();
	
	private String shape="";//用于保存需要绘制什么图形的字符串属性
	
	private void init(){
		Panel panel=new Panel();
		rect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shape=RECT_SHAPE;

				drawArea.repaint();//重画Mycanvas对象，即调用它的update方法
				
			}
		});
		oval.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shape=OVAL_SHAPE;
          
				drawArea.repaint();//重画Mycanvas对象，即调用它的update方法
				
			}
		});
		
		panel.add(rect);
		panel.add(oval);
		drawArea.setPreferredSize(new Dimension(250, 180));
		
		f.add(drawArea);
		f.add(panel,BorderLayout.SOUTH);
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
	
	class MyCanvas extends Canvas{
		
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			
			Random random=new Random();
			
			if(shape.equals(RECT_SHAPE)){
				g.setColor(new Color(220, 100, 80));
				g.drawRect(random.nextInt(200), random.nextInt(100), 40, 60);
			}
			
			if(shape.equals(OVAL_SHAPE)){
				g.setColor(new Color(80, 120, 100));
				g.fillOval(random.nextInt(200), random.nextInt(100), 40, 60);
			}
		}
	}
	public static void main(String[] args) {
		new DrawImage().init();
	}

}
