package com.special.frame.it;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/*
 *内外多窗口显示 
 * 
 ***/
public class TestInOutWindon {

	private final int DESKTOP_WIDTH = 480;
	private final int DESKTOP_HEIGHT = 360;
	private final int FRAME_DISTANCE = 30;
	private JFrame jf = new JFrame("MDI");

	// 定义一个虚拟的桌面
	private MyDesktopPane desktopPane = new MyDesktopPane();

	// 保存下一个内部窗口的坐标点
	private int nextFrameX;
	private int nextFrameY;

	// 定义内部窗口为虚拟窗口的1/2大小
	private int width = DESKTOP_WIDTH / 2;
	private int height = DESKTOP_HEIGHT / 2;

	// 为主窗口定义两个菜单
	JMenu file = new JMenu("文件");
	JMenu window = new JMenu("窗口");

	// 定义newAction用于创建菜单和工具按钮
	Action newAction = new AbstractAction("新建", new ImageIcon("image/1.png")) {

		@Override
		public void actionPerformed(ActionEvent e) {
			// 创建内部窗口
			final JInternalFrame iFrame = new JInternalFrame("新文档", true, // 可改变大小
					true, // 可关闭
					true, // 可最大化
					true); // 可最小化

			iFrame.add(new JScrollPane(new JTextArea(8, 40)));

			// 将内部窗口添加到虚拟桌面中
			desktopPane.add(iFrame);

			// 设置内部窗口的原始位置（内部窗口默认大小是0*0，放在0，0位置）
			iFrame.reshape(nextFrameX, nextFrameY, width, height);

			// 是该窗口可见，并尝试选中它
			iFrame.show();

			// 计算下一个内部窗口的位置
			nextFrameX += FRAME_DISTANCE;
			nextFrameY += FRAME_DISTANCE;

			if (nextFrameX + width > desktopPane.getWidth()) {
				nextFrameX = 0;
			}
			if (nextFrameY + height > desktopPane.getHeight()) {
				nextFrameY = 0;
			}

		}
	};

	Action exitAction = new AbstractAction("退出", new ImageIcon("image/2.png")) {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			System.exit(0);

		}
	};

	private void init() {

		// 为窗口安装菜单条和工具栏
		JMenuBar menuBar = new JMenuBar();
		JToolBar toolBar = new JToolBar();

		jf.setJMenuBar(menuBar);
		menuBar.add(file);
		file.add(newAction);
		file.add(exitAction);

		toolBar.add(newAction);
		toolBar.add(exitAction);
		menuBar.add(window);

		JMenuItem nexItem = new JMenuItem("下一个");
		nexItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				desktopPane.selectNextWindows();
			}
		});
		window.add(nexItem);

		JMenuItem cascadeItem = new JMenuItem("级联");
		cascadeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 级联显示窗口，内部窗口的大小是外部窗口的0.75
				desktopPane.cascadeWindows(FRAME_DISTANCE, 0.75);

			}
		});
		window.add(cascadeItem);

		JMenuItem titleItem = new JMenuItem("平铺");
		titleItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 平铺显示所有内部窗口
				desktopPane.titleWindows();
			}
		});
		window.add(titleItem);

		final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem(
				"仅显示拖动窗口的轮廓");
		dragOutlineItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 根据该菜单项是否来决定采用哪种拖动模式
				desktopPane.setDragMode(dragOutlineItem.isSelected() ? JDesktopPane.OUTLINE_DRAG_MODE
						: JDesktopPane.LIVE_DRAG_MODE);
			}
		});
		window.add(dragOutlineItem);
		desktopPane.setPreferredSize(new Dimension(480, 360));

		jf.add(desktopPane);
		jf.add(toolBar, BorderLayout.NORTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);

	}

	class MyDesktopPane extends JDesktopPane {
		/*
		 * 将所有窗口以及联方式显示 其中offset是两个窗口的位移距离 scale是内部窗口与JDesktopPane大小比例
		 * 
		 * *
		 */
		private void cascadeWindows(int offset, double scale) {

			// 定义级联显示窗口时内部窗口的大小
			int width = (int) (getWidth() * scale);
			int height = (int) (getHeight() * scale);

			// 用于保存级联窗口是内部窗口的大小
			int x = 0;
			int y = 0;

			for (JInternalFrame frames : getAllFrames()) {

				try {
					// 取消内部窗口的最大化、最小化
					frames.setMaximum(false);
					frames.setIcon(false);

					// 把窗口重新放置在指定位置
					frames.reshape(x, y, width, height);
					x += offset;
					y += offset;

					// 如果到了虚拟桌面边界
					if (x + width > getWidth()) {
						x = 0;
					}
					if (y + height > getHeight()) {
						y = 0;
					}
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		/*
		 * 将所有窗口以平铺方式显示*
		 */
		private void titleWindows() {

			// 统计所有窗口
			int frameCount = 0;
			for (JInternalFrame frame : getAllFrames()) {
				frameCount++;
			}

			// 计算需要多少行、多少列才可以平铺所有窗口
			int rows = (int) Math.sqrt(frameCount);
			int cols = frameCount / rows;

			// 需要额外增加到其他列中的窗口
			int extra = frameCount % rows;

			// 计算平铺式内部窗口的大小
			int width = getWidth() / cols;
			int height = getHeight() / rows;

			// 用于保存平铺窗口时每个窗口在横向、纵向上的索引
			int x = 0;
			int y = 0;

			for (JInternalFrame frames : getAllFrames()) {

				try {
					// 取消内部窗口的最大化、最小化
					frames.setMaximum(false);
					frames.setIcon(false);

					// 将窗口放在指定位置
					frames.reshape(x * width, y * height, width, height);
					y++;

					// 每排完一列窗口
					if (y == rows) {

						// 开始排放下一列窗口
						y = 0;
						x++;

						// 如果额外多出的窗口与剩下的列数相等，则后面列都需要多排列一个窗口
						if (extra == cols - x) {

							rows++;
							height = getHeight() / rows;

						}

					}
				} catch (PropertyVetoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

		/*
		 * 选中下一个非图标窗口
		 * 
		 * *
		 */
		private void selectNextWindows() {
			JInternalFrame[] frames = getAllFrames();
			for (int i = 0; i < frames.length; i++) {
				if (frames[i].isSelected()) {
					/*
					 * 找出下一个非最小化窗口，尝试选中它， 如果选中失败，则继续尝试选中下一个窗口
					 * 
					 * *
					 */
					int next = (i + 1) & frames.length;

					while (next != i) {
						// 如果该窗口不是处于最小化状态

						try {
							frames[next].setSelected(true);
							frames[next].toFront();
							frames[i].toBack();
							return;
						} catch (PropertyVetoException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					next = (next + 1) % frames.length;

				}
			}

		}

	}
	public static void main(String[] args) {
		new TestInOutWindon().init();
	}
}
