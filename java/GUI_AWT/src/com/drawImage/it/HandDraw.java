package com.drawImage.it;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

/*
 *触屏写字 
 **/
public class HandDraw {

	private final int AREA_WIDTH = 500;// 画图区宽度
	private final int AREA_HEIGHT = 400;// 画图区高度

	// 下面的preX、preY保存了上一次鼠标拖动事件的鼠标坐标
	private int preX = -1;
	private int preY = -1;

	// 定义右键菜单设置画笔颜色
	PopupMenu pop = new PopupMenu();
	MenuItem red = new MenuItem("RED");
	MenuItem green = new MenuItem("GREEN");
	MenuItem blue = new MenuItem("BLUE");

	// 定义一个BufferedImage对象
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT,
			BufferedImage.TYPE_INT_RGB);

	Graphics g = image.getGraphics();// 获取image对象的Graphics

	private Frame f = new Frame("简单手绘程序");
	private DrawCanvas drawArea = new DrawCanvas();

	private Color foreColor = new Color(255, 0, 0);// 保存画笔颜色

	private void init() {
		// 右键菜单事件监听器
		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (e.getActionCommand().equals("GREEN")) {
					foreColor = new Color(0, 255, 0);
				}
				if (e.getActionCommand().equals("RED")) {
					foreColor = new Color(255, 0, 0);
				}
				if (e.getActionCommand().equals("BLUE")) {
					foreColor = new Color(0, 0, 255);
				}

			}
		};

		red.addActionListener(menuListener);
		green.addActionListener(menuListener);
		blue.addActionListener(menuListener);

		pop.add(blue);
		pop.add(red);
		pop.add(green);

		drawArea.add(pop);

		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);// 将Image对象背景填充成白色
		drawArea.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
		// 监听鼠标移动事件
		drawArea.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(MouseEvent e) {
			}

			@Override
			public void mouseDragged(MouseEvent e) {
				if (preX > 0 && preY > 0) {
					g.setColor(foreColor);// 设置当前颜色
					// 绘制从上一次鼠标拖动事件点到本次鼠标拖动事件点的线段
					g.drawLine(preX, preY, e.getX(), e.getY());
				}
				// 将当前鼠标事件点的x、y坐标保存起来
				preX = e.getX();
				preY = e.getY();
				// 重绘drawArea对象,即刷新界面
				drawArea.repaint();
			}
		});
		// 监听鼠标事件
		drawArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				// 弹出右键菜单
				if (e.isPopupTrigger()) {
					pop.show(drawArea, e.getX(), e.getY());
				}
				// 松开鼠标键时，把上一次鼠标拖动事件的x、y坐标设为-1
				preX = -1;
				preY = -1;
			}
		});

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				System.exit(0);
			}
		});
		f.add(drawArea);
		f.pack();
		f.setVisible(true);

	}

	class DrawCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			// 将Image绘制到该组件上
			g.drawImage(image, 0, 0, null);
		}
	}

	public static void main(String[] args) {
		new HandDraw().init();
	}

}
