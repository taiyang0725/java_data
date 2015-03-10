package com.fileFilter.it;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

/*
 * 简单的图片查看器
 * */
public class ImageViews {
	// 定义图片预览组件的大小
	private final int PREVIEV_SIZE = 100;
	private JFrame jf = new JFrame("简单图片观看");
	private JMenuBar menuBar = new JMenuBar();
	// label用于显示图片
	private JLabel label = new JLabel();
	// 以当前路径创建文件选择器
	private JFileChooser chooser = new JFileChooser(".");
	// 定义文件过滤器
	ExtensionFileFilter filter = new ExtensionFileFilter();

	private void init() {
		/*
		 * ---------下面开始初始化JFileChooser的相关属性------------- 创建一个FileFilter
		 */
		filter.addExtension("jpg");
		filter.addExtension("jpeg");
		filter.addExtension("gif");
		filter.addExtension("png");
		filter.setDescription("图片文件(*.jpg,*.jpeg,*.gif,*.png)");
		chooser.addChoosableFileFilter(filter);

		// 禁止“文件类型”下拉列表中显示“所有文件”选项
		chooser.setAcceptAllFileFilterUsed(false);

		// 为文件选择器指定自定义的FileView对象
		chooser.setFileView(new FileIconView(filter));

		// 为文件选择器指定一个预览图片的附件
		chooser.setAccessory(label);
		// 设置预览图片组件的大小和边框
		label.setPreferredSize(new Dimension(PREVIEV_SIZE, PREVIEV_SIZE));
		label.setBorder(BorderFactory.createEtchedBorder());

		// 用于检测被选择文件的改变事件
		chooser.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				// JFileChooser的被选文件已经发生变化
				if (evt.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
					// 获取用户选择的新文件
					File f = (File) evt.getNewValue();
					if (f == null) {
						label.setIcon(null);
						return;
					}
					// 将所选文件读入ImageIcon对象中
					ImageIcon icon = new ImageIcon(f.getPath());
					// 如果图像太大，则缩小它
					if (icon.getIconWidth() > PREVIEV_SIZE) {
						icon = new ImageIcon(icon.getImage().getScaledInstance(
								PREVIEV_SIZE, -1, Image.SCALE_DEFAULT));
					}
					// 改变label的图标
					label.setIcon(icon);

				}
			}
		});

		JMenu menu = new JMenu("文件");
		menuBar.add(menu);
		JMenuItem oPItem = new JMenuItem("打开");
		menu.add(oPItem);
		// 单击oPItem菜单项显示“打开文件”的对话框
		oPItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 设置文件对话框的当前路径
				// chooser.setCurrentDirectory(new File("."));
				// 显示文件对话框
				int result = chooser.showDialog(jf, "打开图片文件");
				// 如果用户选择了APPROVE(赞同)按钮，即打开，保存及其等效按钮
				if (result == JFileChooser.APPROVE_OPTION) {
					String name = chooser.getSelectedFile().getPath();
					// 显示指定图片
					label.setIcon(new ImageIcon(name));

				}

			}
		});
		JMenuItem exit = new JMenuItem("退出");
		menu.addSeparator();
		menu.add(exit);
		exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		jf.setJMenuBar(menuBar);
		//添加用于显示图片的JLabel组件
		jf.add(new JScrollPane(label));
		jf.setPreferredSize(new Dimension(500,400));
		
		jf.pack();
		jf.setVisible(true);
	}
	public static void main(String[] args) {
		new ImageViews().init();
	}

}
