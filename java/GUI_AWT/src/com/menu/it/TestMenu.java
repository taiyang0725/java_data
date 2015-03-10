package com.menu.it;

import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.sun.glass.events.KeyEvent;

public class TestMenu {

	private Frame f = new Frame("Test");// 窗口

	private MenuBar mb = new MenuBar();// 标题栏

	private PopupMenu p = new PopupMenu();// 鼠标右键菜单

	/* 标题栏菜单 */
	Menu file = new Menu("File");
	Menu edit = new Menu("Edit");

	/* file下的子菜单 */
	MenuItem newItem = new MenuItem("New");
	MenuItem saveItem = new MenuItem("Save");

	// 创建exititem菜单项，指定使用ctrl+x快捷键
	MenuItem exitItem = new MenuItem("Exit", new MenuShortcut(KeyEvent.VK_X));
	CheckboxMenuItem autoWrap = new CheckboxMenuItem("AutoWrap");
	// MenuItem autoWrap1=new MenuItem("autoWrap");
	MenuItem copy = new MenuItem("Copy");
	MenuItem paste = new MenuItem("Paste");
	Menu format = new Menu("Format");

	// 创建comment 菜单项，指定使用ctrl+shift+/快捷键
	MenuItem comment = new MenuItem("Comment", new MenuShortcut(
			KeyEvent.VK_SLASH, true));
	MenuItem cancel = new MenuItem("CancelComment");

	private TextArea ta = new TextArea(6, 40);

	/*
	 * 标题栏菜单
	 */
	public void initView() {
		// 以匿名内部类的形式创建菜单监听器
		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				ta.append("单击" + cmd + "菜单" + "\n");
				if (cmd.equals("Exit")) {
					System.exit(0);
				}

			}
		};
		comment.addActionListener(menuListener);
		cancel.addActionListener(menuListener);
		exitItem.addActionListener(menuListener);

		// 为file菜单添加菜单项
		file.add(newItem);
		file.add(saveItem);

		// 为edit菜单添加菜单项
		edit.add(autoWrap);
		// edit.add(autoWrap1);
		edit.addSeparator();// 使用addSeparator方法来添加菜单分隔线
		edit.add(copy);
		edit.add(paste);
		edit.add(new MenuItem("-"));// 使用new MenuItem("-")方法添加菜单分割线
		edit.add(format);// 将format菜单组合到edit菜单中，从而形成二级菜单

		// 为format菜单添加菜单项
		format.add(comment);
		format.add(cancel);

		// 将file、edit菜单添加到mb菜单条中
		mb.add(file);
		mb.add(edit);

		// 为f窗口设置菜单条
		f.setMenuBar(mb);
		f.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				ta.append("用户试图关闭窗口！\n");
				System.exit(0);
			}
		});
		f.add(ta);
		f.pack();
		f.setVisible(true);

	}

	/*
	 * 鼠标右键菜单
	 */
	private void TestPopupManu() {

		ActionListener menuListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String cmd = e.getActionCommand();
				ta.append("单击" + cmd + "菜单" + "\n");
				if (cmd.equals("Exit")) {
					System.exit(0);
				}

			}
		};
		comment.addActionListener(menuListener);
		cancel.addActionListener(menuListener);
		p.add(autoWrap);
		p.addSeparator();
		p.add(copy);
		p.add(paste);

		p.add(new MenuItem("-"));
		format.add(comment);
		format.add(cancel);
		p.add(format);

		final Panel panel = new Panel();
		panel.setPreferredSize(new Dimension(300, 160));

		panel.add(p);
		panel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseReleased(e);

				if (e.isPopupTrigger()) {

					p.show(panel, e.getX(), e.getY());
				}
			}

		});

		f.add(panel);
		f.add(ta, BorderLayout.NORTH);

		f.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);

				ta.append("用户试图关闭窗口！\n");
				System.exit(0);
			}
		});

		f.pack();
		f.setVisible(true);

	}

	public static void main(String[] args) {
		 new TestMenu().initView();
		//new TestMenu().TestPopupManu();
	}

}
