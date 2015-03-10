package com.chooser.it;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

public class HandDrawColor {
	/* 画图区宽度* */
	private final int AREA_WIDTH = 500;
	/* 画图区高度* */
	private final int AREA_HEIGHT = 400;
	/* 下面的preX、preY保存了上一次鼠标拖动事件的鼠标坐标* */
	private int preX = -1;
	private int preY = -1;
	/* 定义一个右键菜单设置画笔颜色* */
	JPopupMenu pop = new JPopupMenu();
	JMenuItem chooserColor = new JMenuItem("选择颜色");
	/*
	 * 定义一个BufferedImage对象
	 */
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT,
			BufferedImage.TYPE_INT_RGB);
	/* 获取image对象的Graphics* */
	Graphics g = image.getGraphics();
	private JFrame jf = new JFrame("简单手绘程序");
	private DrawCanvas drawCanvas = new DrawCanvas();
	/* 用于保存画笔的颜色* */
	private Color foreColor = new Color(255, 0, 0);

	private void init() {
		chooserColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 下面代码直接弹出一个模式的颜色选择器对话框并返回用户选择的颜色
				 * foreColor=JColorChooser.showDialog(jf, "选择画笔颜色", foreColor);
				 * 下面代码则可以弹出一个非模式的颜色选择对话框并可以分别为“确定按钮”“取消按钮”指定事件监听器
				 */
				final JColorChooser colorPane = new JColorChooser(foreColor);
				JDialog jd = JColorChooser.createDialog(jf, "选择画笔颜色", false,
						colorPane, new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								foreColor = colorPane.getColor();

							}
						}, null);

				jd.setVisible(true);

				
			}
		});

		/*将菜单项组合成右键菜单**/
		pop.add(chooserColor);
		/*将右键菜单添加到drawArea对象中**/
		drawCanvas.setComponentPopupMenu(pop);
		/*将image对象的背景颜色填充成白色**/
		g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
		drawCanvas.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
		/*监听鼠标移动动作**/
		drawCanvas.addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				if(preX>0 && preY>0){
					/*设置当前颜色**/
					g.setColor(foreColor);
					/*绘制从上一次鼠标拖动事件到本次鼠标拖动点之间的线段**/
					g.drawLine(preX, preX, e.getX(), e.getY());
					
				}
				/*将当前鼠标事件点的x/y坐标保存起来**/
				preX=e.getX();
				preY=e.getY();
				
				drawCanvas.repaint();
			}
		});
		
		/*监听鼠标事件**/
		drawCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				preX=-1;
				preY=-1;
			}
			
			
		});
		
		jf.add(drawCanvas);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);
	}

	class DrawCanvas extends JPanel {
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(image, 0, 0, null);
		}
	}
	public static void main(String[] args) {
		new HandDrawColor().init();
	}

}
