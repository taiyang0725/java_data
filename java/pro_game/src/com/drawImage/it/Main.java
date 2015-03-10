package com.drawImage.it;

import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.Timer;
/*
 *弹球小游戏 
 **/
public class Main {

	private final int TABLE_WIDTH = 300;// 桌面宽度
	private final int TABLE_HEIGHT = 400;// 桌面高度
	private final int RACKET_Y = 370;// 球拍垂直位置

	/*
	 * 球拍的高度和宽度
	 */
	private final int RACKET_HEIGHT = 20;
	private final int RACKET_WIDTH = 60;

	private final int BALL_SIZE = 16;// 小球的大小

	private Frame f = new Frame("MINI-GAME");
	private Random random = new Random();// 随机数
	private Panel panel = new Panel();

	private Button btnStart = new Button("START");
	private Button btnStop = new Button("Stop");

	private int ySpeed = 10;// 小球纵向运行速度

	private double xyRate = random.nextDouble() - 0.5;// 返回一个-0.5~0.5的比率，用于控制小球的运行方向

	private int xSpeed = (int) (ySpeed * xyRate * 2);// 小球的横向速度

	// ballX和ballY代表小球的坐标
	private int ballX = random.nextInt(200) + 20;
	private int ballY = random.nextInt(10) + 20;

	private int racketX = random.nextInt(200);// 球拍的水平位置

	private MyCanvas tableArea = new MyCanvas();

	private String shape = "";// 用于保存绘制什么图形的字符串属性

	private Timer timer;// 定义一个计时器

	private boolean isLose;
	private boolean isStart;// 游戏开始
	private boolean isStop;// 游戏暂停

	private void initGame() {
		tableArea.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		f.add(tableArea);

		panel.add(btnStart);
		panel.add(btnStop);
		//f.add(panel, BorderLayout.SOUTH);

		// 定义键盘监听器
		KeyAdapter keyProcessor = new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				// 如果按下左右键，球拍水平坐标分别减小、增大
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (racketX > 0) {
						racketX -= 10;
					}
				}

				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (racketX < TABLE_WIDTH - RACKET_WIDTH) {
						racketX += 10;
					}
				}
			}
		};
		/*
		 * 为窗口和tableArea添加键盘监听器
		 */
		f.addKeyListener(keyProcessor);
		tableArea.addKeyListener(keyProcessor);

		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.exit(0);
			}
		});

		/*
		 * 定义每0.1s执行一次监听器
		 */
		 ActionListener taskPerformer = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*
				 * 如果小球碰到左边边框
				 */
				if (ballX <= 0 || ballX >= TABLE_WIDTH - BALL_SIZE) {
					xSpeed = -xSpeed;
				}
				/*
				 * 如果小球高度超出了球拍位置，且横向不在球拍范围之内，游戏结束
				 */
				if (ballY >= RACKET_Y - BALL_SIZE
						&& (ballX < racketX || ballX > racketX + RACKET_WIDTH)) {

					timer.stop();
					isLose = true;// 设置游戏是否结束的标志位true
					tableArea.repaint();
				} else if (ballY <= 0
						|| (ballY >= RACKET_Y - BALL_SIZE && ballX > racketX && ballX <= racketX
								+ RACKET_WIDTH)) {
					ySpeed = -ySpeed;
				}
				ballY += ySpeed;
				ballX += xSpeed;
				tableArea.repaint();
			}
		};
		
		
		timer = new Timer(100, taskPerformer);
		timer.start();
		
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

			}
		});

		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				

			}
		});

		
		
		f.pack();
		f.setVisible(true);
	}

	class MyCanvas extends Canvas {
		@Override
		public void paint(Graphics g) {
			// TODO Auto-generated method stub
			super.paint(g);
			// 如果游戏已经结束
			if (isLose) {
				g.setColor(new Color(255, 0, 0));
				g.setFont(new Font("Times", Font.BOLD, 30));
				g.drawString("Game Over!", 50, 200);
			} else {
				// 设置颜色，并绘制小球
				g.setColor(new Color(240, 240, 80));
				g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
				// 设置颜色，并绘制球拍
				g.setColor(new Color(80, 80, 200));
				g.fillRect(racketX, RACKET_Y, RACKET_WIDTH, RACKET_HEIGHT);
			}

		}

	}

	public static void main(String[] args) {
		new Main().initGame();
	}

}
