package com.base.it;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SwingComponent {

	private JFrame f = new JFrame("测试");

	Icon okIcon = new ImageIcon("image/1.png");// 定义一个按钮，并为之指定图标
	JButton ok = new JButton("确认", okIcon);

	JRadioButton male = new JRadioButton("Boy", true);// 定义一个单选按钮，处于选中状态
	JRadioButton female = new JRadioButton("Girl", false);// 出于未选中状态

	ButtonGroup btng = new ButtonGroup();// 定义一个ButtonGroup，用于将上面两个JRadioButton组合在一起

	JCheckBox married = new JCheckBox("NO or YES", false);// 定义一个复选框，处于未选中状态

	String[] colors = new String[] { "Red", "Green", "Blue" };

	JComboBox colorChooser = new JComboBox(colors);// 定义一个下拉选择框

	JList colorList = new JList(colors);// 定义一个列表选择框

	JTextArea ta = new JTextArea(8, 20);// 定义一个8行、20列的多行文本

	JTextField name = new JTextField(40);// 定义一个40列的单行文本域

	JMenuBar mb = new JMenuBar();
	JMenu file = new JMenu("文件");
	JMenu edit = new JMenu("Edit");

	Icon newIcon = new ImageIcon("image/2.png");// 创建新建菜单项，并为之指定图标
	JMenuItem newItem = new JMenuItem("New", newIcon);
	Icon saveIcon = new ImageIcon("image/3.png");// 创建保存菜单项，并为之指定图标
	JMenuItem saveItem = new JMenuItem("Save", saveIcon);
	JMenuItem exitItem = new JMenuItem("Exit", new ImageIcon("image/1.png"));
	JCheckBoxMenuItem autoWrap = new JCheckBoxMenuItem("AutoWrap");

	JMenuItem copyItem = new JMenuItem("Copy", new ImageIcon("image/1.png"));
	JMenuItem pasterItem = new JMenuItem("Paster", new ImageIcon("image/1.png"));

	JMenu format = new JMenu("Format");
	JMenuItem comment = new JMenuItem("Comment");
	JMenuItem cancel = new JMenuItem("Cancel");

	JPopupMenu pop = new JPopupMenu();// 设置一个右键菜单用于设置程序风格
	ButtonGroup flavor = new ButtonGroup();// 用于组合三个风格菜单项的ButtonGroup

	// 设置三个单选框按钮，用于设定程序外观风格
	JRadioButtonMenuItem metals = new JRadioButtonMenuItem("Metal风格", true);
	JRadioButtonMenuItem windows = new JRadioButtonMenuItem("Windows风格");
	JRadioButtonMenuItem motif = new JRadioButtonMenuItem("Motif风格");

	// ----------------------用于执行初始化界面的方法---------------

	private void init() {

		JPanel bottom = new JPanel();// 创建一个装载文本框，按钮的Jpanel
		bottom.add(name);
		bottom.add(ok);
		f.add(bottom, BorderLayout.SOUTH);

		JPanel check = new JPanel();// 装载下拉选择框、三个JcheckBox
		check.add(colorChooser);
		check.add(male);
		check.add(female);
		check.add(married);
		btng.add(male);
		btng.add(female);

		// 创建一个垂直排列组件的Box，装载多行文本域
		Box topLeft = Box.createVerticalBox();
		// 使用JScrollPane作为普通组件的JViewPort
		JScrollPane taJsp = new JScrollPane(ta);
		topLeft.add(taJsp);
		topLeft.add(check);

		// 创建一个垂直排列的组件Box,装载topLeft，colorList
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		// 将top Box添加到窗口中间
		f.add(top);
		/*
		 * 下面开始组合菜单，并为菜单添加监听器为newItem设置快捷键，设置快捷键是要使用大写字母
		 */
		newItem.setAccelerator(KeyStroke
				.getKeyStroke('N', InputEvent.CTRL_MASK));
		newItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ta.append("用户点击了New\n");

			}
		});
		// 为file菜单添加菜单项
		file.add(newItem);
		file.add(saveItem);
		file.add(exitItem);
		// 为edit菜单添加菜单项
		edit.add(autoWrap);
		edit.addSeparator();// 添加分隔线
		edit.add(copyItem);
		edit.add(pasterItem);
		comment.setToolTipText("将程序代码注释起来！");
		format.add(comment);
		format.add(cancel);

		edit.add(new JMenuItem("-"));
		edit.add(format);

		// 将file、edit菜单添加到mb菜单条中
		mb.add(file);
		mb.add(edit);
		// 为F窗口设置菜单条
		f.setJMenuBar(mb);

		/*
		 * 下面开始组合右键菜单、并安装右键菜单
		 */
		flavor.add(metals);
		flavor.add(windows);
		flavor.add(motif);

		pop.add(metals);
		pop.add(windows);
		pop.add(motif);

		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getActionCommand().equals("Metal风格")) {
						changeFlavor(1);
					}
					if (e.getActionCommand().equals("Windows风格")) {
						changeFlavor(2);
					}
					if (e.getActionCommand().equals("Motif风格")) {
						changeFlavor(3);
					}
				} catch (ClassNotFoundException | InstantiationException
						| IllegalAccessException
						| UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		};
		metals.addActionListener(actionListener);
		windows.addActionListener(actionListener);
		motif.addActionListener(actionListener);

		ta.setComponentPopupMenu(pop);// 调用该方法即可设置右键菜单，无需使用事件机制
		// 设置关闭窗口时，退出程序
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.pack();
		f.setVisible(true);

	}

	private void changeFlavor(int i) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		switch (i) {
		case 1:
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			break;
		case 2:
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			break;
		case 3:
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			break;

		default:
			break;
		}
		/**更新f窗口内顶级容器以及内部所有组件的UI*/
		SwingUtilities.updateComponentTreeUI(f.getContentPane());
		/**更新mb菜单条以及内部所有组件UI*/
		SwingUtilities.updateComponentTreeUI(mb);
		SwingUtilities.updateComponentTreeUI(pop);
		
	}

	public static void main(String[] args) {
		
		JFrame.setDefaultLookAndFeelDecorated(true);
		new SwingComponent().init();
	}

}
