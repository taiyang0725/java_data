package com.cike.it;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 *五子棋 
 * */
public class Gobang {

	private BufferedImage table;// 棋盘
	private BufferedImage black;// 黑子
	private BufferedImage white;// 白子
	private BufferedImage selected;// 当鼠标移动的时候的选择框

	private static final int BOARD_SIZE = 15;// 定义棋盘大小
	// 定义棋盘宽、高多少个像素
	private static final int TABLE_WIDTH = 535;
	private static final int TABLE_HEIGHT = 536;

	private static final int RATE = TABLE_WIDTH / BOARD_SIZE;// 定义棋盘坐标的像素值和棋盘数组之间的比率

	// 定义棋盘坐标的像素值和棋盘数组之间的偏移量
	private static final int X_OFFSET = 5;
	private static final int Y_OFFSET = 6;

	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];// 定义一个二维数组来充当棋盘

	private JFrame f = new JFrame("五子棋");
	private ChessBoard chessBoard = new ChessBoard();// 对应的Canvas组件

	// 当前选中点的坐标
	private int selX = -1;
	private int selY = -1;

	public void init() {
		try {
			table = ImageIO.read(new File(""));
			black = ImageIO.read(new File(""));
			white = ImageIO.read(new File(""));
			selected = ImageIO.read(new File(""));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 把每个元素赋为“╋”，用于在控制台画出棋盘
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				board[i][j] = "╋";
			}

		}
		chessBoard.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
		chessBoard.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				// 将用户鼠标事件的坐标转化成棋子数组的坐标
				int xPos = (e.getX() - X_OFFSET) / RATE;
				int yPos = (e.getY() - Y_OFFSET) / RATE;

				board[xPos][yPos] = "●";

				/*
				 * 电脑随机生成两个整数，作为电脑下棋的坐标。赋给board数组 1.如果下棋的点已有棋子，不能重复下子
				 * 2.每次下棋后，需要扫描谁赢了
				 */
				chessBoard.repaint();
			}

			// 当鼠标退出棋盘后。复位选中的点坐标
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseExited(e);
				selX = -1;
				selY = -1;
				chessBoard.repaint();
			}
		});
		f.add(chessBoard);
		f.pack();
		f.setVisible(true);
	}

	class ChessBoard extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			// TODO Auto-generated method stub
			super.paintComponent(g);

			g.drawImage(table, 0, 0, null);// 将绘制五子棋盘

			// 绘制选中的红框
			if (selX >= 0 && selY >= 0) {
				g.drawImage(selected, selX * RATE + X_OFFSET, selY * RATE
						+ Y_OFFSET, null);
			}

			// 遍历数组，绘制棋子
			for (int i = 0; i < BOARD_SIZE; i++) {
				for (int j = 0; j < BOARD_SIZE; j++) {

					// 绘制黑子
					if (board[i][j].equals("●")) {
						g.drawImage(black, i * RATE + X_OFFSET, j * RATE
								+ Y_OFFSET, null);
					}

					// 绘制白子

					if (board[i][j].equals("○")) {

						g.drawImage(white, i * RATE + X_OFFSET, j * RATE
								+ Y_OFFSET, null);

					}

				}

			}

		}

	}

	public static void main(String[] args) {
		new Gobang().init();
	}

}
