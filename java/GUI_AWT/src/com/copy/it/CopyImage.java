package com.copy.it;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CopyImage {
	// 系统剪贴板
	private Clipboard clipboard = Toolkit.getDefaultToolkit()
			.getSystemClipboard();

	private static final int AREA_WIDTH = 500;// 画图区宽度
	private static final int AREA_HEIGHT = 400;// 画图区高度
	private static final int ZERO = 0;

	// 保存上一次鼠标拖动事件的鼠标坐标
	private int preX = -1;
	private int preY = -1;

	PopupMenu pop = new PopupMenu();// 定义右键菜单设置画笔颜色
	MenuItem red = new MenuItem("Red");
	MenuItem green = new MenuItem("Green");
	MenuItem blue = new MenuItem("Blue");

	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT,
			BufferedImage.TYPE_INT_BGR);

	// 使用ArrayList来保存所有粘贴进来的Image，即图层管理
	List<Image> imageList = new ArrayList<Image>();

	// 获取image对象的Graphics
	Graphics g = image.getGraphics();

	private Frame f = new Frame("简单手绘程序");
	private DrawCanvas drawCanvas = new DrawCanvas();

	private Color foreColor = new Color(255, 0, 0);// 设置画笔颜色

	private Button copy = new Button("Copy");
	private Button paste = new Button("Paste");

	private void init() {
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("Red")) {
					foreColor = new Color(255, 0, 0);
				}
				if (e.getActionCommand().equals("Green")) {
					foreColor = new Color(0, 255, 0);
				}
				if (e.getActionCommand().equals("Blue")) {
					foreColor = new Color(0, 0, 255);
				}

			}
		};
		red.addActionListener(actionListener);
		green.addActionListener(actionListener);
		blue.addActionListener(actionListener);

		pop.add(blue);
		pop.add(green);
		pop.add(red);

		drawCanvas.add(pop);

		g.fillRect(ZERO, ZERO, AREA_WIDTH, AREA_HEIGHT);// 将image对象的背景填充为白色
		drawCanvas.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
		// 鼠标拖动事件
		drawCanvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseDragged(e);
				if (preX > 0 && preY > 0) {
					g.setColor(foreColor);
					g.drawLine(preX, preY, e.getX(), e.getY());
				}
				preX = e.getX();
				preY = e.getY();

				drawCanvas.repaint();
			}

		});
		// 鼠标点击事件
		drawCanvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);
				if (e.isPopupTrigger()) {
					pop.show(drawCanvas, e.getX(), e.getY());
				}
				preX = -1;
				preY = -1;
			}

		});

		f.add(drawCanvas);
		Panel p = new Panel();

		copy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				ImageSelection selection = new ImageSelection(image);// 将image对象封装成ImageSelection对像

				clipboard.setContents(selection, null);// 将ImageSelection对象放入剪贴板

			}
		});

		paste.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if (clipboard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
					try {
						imageList.add((Image) clipboard
								.getData(DataFlavor.imageFlavor));
					} catch (UnsupportedFlavorException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					drawCanvas.repaint();
				}

			}
		});

		p.add(copy);
		p.add(paste);
		f.add(p, BorderLayout.SOUTH);
		
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

	class DrawCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			g.drawImage(image, 0, 0, null);

			for (Image img : imageList) {
				g.drawImage(img, 0, 0, null);
			}

		}

	}

	public static void main(String[] args) {
		new CopyImage().init();
	}
}
