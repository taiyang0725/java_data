package com.special.frame.it;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestJTabbedPane {

	JFrame jf = new JFrame("测试Tab页面");
	// 创建一个Tab页面的标签放在左边，采用换行布局策略的JTabbedPane
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.LEFT,
			JTabbedPane.WRAP_TAB_LAYOUT);

	ImageIcon icon = new ImageIcon("image/1.png");
	String[] layouts = { "换行布局", "滚动条布局" };
	String[] positions = { "左边", "顶部", "右边", "底部" };
	Map<String, String> books = new LinkedHashMap<String, String>();

	private void init() {
		books.put("三国演义", "image/1.png");
		books.put("水浒传", "image/2.png");
		books.put("红楼梦", "image/3.png");
		books.put("西游记", "image/1.png");
		books.put("封神演义", "image/2.png");
		String tip = "本书封面照片";

		// 向JTabbedPane中添加5个标签页面，指定标题、图标、提示，但该标签页面组件是null
		for (String bookName : books.keySet()) {
			tabbedPane.addTab(bookName, icon, null, tip);
		}
		jf.add(tabbedPane, BorderLayout.CENTER);
		// 为JTabbedPane添加监听器
		tabbedPane.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				// 如果被选的组件依然是空
				if (tabbedPane.getSelectedComponent() == null) {
					// 获取所选标签页
					int n = tabbedPane.getSelectedIndex();
					// 为指定标签也加在内容
					loadTab(n);
				}

			}
		});
		// 系统默认选择第一页，加载第一页内容
		loadTab(0);
		tabbedPane.setPreferredSize(new Dimension(500, 300));

		// 增加控制标签布局，标签位置的单选按钮
		JPanel btnPanel = new JPanel();
		ChangeAction action = new ChangeAction();
		btnPanel.add(new ButtonPanel(action, "选择标签布局策略", layouts));
		btnPanel.add(new ButtonPanel(action, "选择标签位置", positions));

		jf.add(btnPanel, BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);

	}

	/*
	 * 指定标签页加载内容
	 */
	private void loadTab(int index) {
		String title = tabbedPane.getTitleAt(index);
		// 根据标签页的标题获取对应图书封面
		ImageIcon bookImage = new ImageIcon(books.get(title));
		tabbedPane.setComponentAt(index, new JLabel(bookImage));
		// 改变标签页图标
		tabbedPane.setIconAt(index, new ImageIcon("image/1.png"));

	}

	/*
	 * 定义改变标签页的布局策略，放置位置监听器
	 */
	class ChangeAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JRadioButton source = (JRadioButton) e.getSource();
			String selection = source.getActionCommand();
			if (selection.equals(layouts[0])) {
				tabbedPane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
			} else if (selection.equals(layouts[1])) {
				tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			} else if (selection.equals(positions[0])) {
				tabbedPane.setTabLayoutPolicy(JTabbedPane.LEFT);
			} else if (selection.equals(positions[1])) {
				tabbedPane.setTabLayoutPolicy(JTabbedPane.TOP);
			} else if (selection.equals(positions[2])) {
				tabbedPane.setTabLayoutPolicy(JTabbedPane.RIGHT);
			} else if (selection.equals(positions[3])) {
				tabbedPane.setTabLayoutPolicy(JTabbedPane.BOTTOM);
			}
		}
	}
	
	class ButtonPanel extends JPanel {

		private ButtonGroup group;

		public ButtonPanel(TestJTabbedPane.ChangeAction action, String title,
				String[] labels) {
			setBorder(BorderFactory.createTitledBorder(BorderFactory.
					createEtchedBorder(),title));
			
			setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			group=new ButtonGroup();
			
			for (int i = 0; labels!=null && i < labels.length; i++) {
				JRadioButton b=new JRadioButton(labels[i]);
				b.setActionCommand(labels[i]);
				add(b);
				
				b.addActionListener(action);
				group.add(b);
				b.setSelected(i==0);
			}

		}

	}
	public static void main(String[] args) {
		new TestJTabbedPane().init();
		
	}
}
