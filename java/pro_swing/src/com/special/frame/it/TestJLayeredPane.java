package com.special.frame.it;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class TestJLayeredPane {

	JFrame jf = new JFrame("Test");
	JLayeredPane layeredPane = new JLayeredPane();

	private void init() {
		// 向JLayeredPane中添加三个组件
		layeredPane.add(new ContentPanel(10, 20, "Java", "image/1.png"),
				JLayeredPane.MODAL_LAYER);

		layeredPane.add(new ContentPanel(100, 60, "Android", "image/2.png"),
				JLayeredPane.DEFAULT_LAYER);

		layeredPane.add(new ContentPanel(190, 100, "Python", "image/3.png"), 4);

		layeredPane.setPreferredSize(new Dimension(400, 300));
		layeredPane.setVisible(true);

		jf.add(layeredPane);
		jf.pack();
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);

	}

	/*
	 * 扩展了JPanel类，可以直接创建一个放在指定位置且有指定标题、反之指定图标JPanel对象
	 */
	class ContentPanel extends JPanel {

		public ContentPanel(int xPos, int yPos, String title, String ico) {
			setBorder(BorderFactory.createTitledBorder(
					BorderFactory.createEtchedBorder(), title));

			JLabel jLabel = new JLabel(new ImageIcon(ico));
			add(jLabel);
			setBounds(xPos, yPos, 160, 200);

		}

	}
	public static void main(String[] args) {
		new TestJLayeredPane().init();
	}
}
