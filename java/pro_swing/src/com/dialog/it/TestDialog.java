package com.dialog.it;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/*
 *各种对话框 
 **/
public class TestDialog {

	private JFrame jf = new JFrame("Test_JOptionPane");

	// 分别定义6个面板用于定义对话框的几种选项
	private ButtonPanel messagePanel;
	private ButtonPanel messageTypePanel;
	private ButtonPanel msgPanel;
	private ButtonPanel confirmPanel;
	private ButtonPanel optionsPanel;
	private ButtonPanel inputPanel;

	private String msg = "消息区内容";

	private Icon msgIcon = new ImageIcon("image/1.png");

	private Object msgObject = new Date();

	private Component msgComponent = new JButton("组件消息");
	private JButton msgBtn = new JButton("消息对话框");
	private JButton confrimBtn = new JButton("确认对话框");
	private JButton inputBtn = new JButton("输入对话框");
	private JButton optionBtn = new JButton("选项对话框");

	private void init() {

		JPanel top = new JPanel();
		top.setBorder(new TitledBorder(new EtchedBorder(), "对话框的通用选项",
				TitledBorder.CENTER, TitledBorder.TOP));
		top.setLayout(new GridLayout(1, 2));

		// 消息类型Panel，该Panel中的选项决定对话框的图标
		messageTypePanel = new ButtonPanel("选择消息类型", new String[] {
				"ERROR_MESSAGE", "INFORMATION_MESSAGE", "WARNING_MESSAGE",
				"QUESTION_MESSAGE", "PLAIN_MESSAGE" });

		// 消息内容类型Panel，该Panel中的选项决定对话框消息区的内容
		messagePanel = new ButtonPanel("选择消息内容的类型", new String[] { "字符串消息",
				"图标消息", "组件消息", "普通对话消息", "Object[]消息" });

		top.add(messageTypePanel);
		top.add(messagePanel);

		JPanel bottom = new JPanel();
		bottom.setBorder(new TitledBorder(new EtchedBorder(), "弹出不同的对话框",
				TitledBorder.CENTER, TitledBorder.TOP));
		bottom.setLayout(new GridLayout(1, 4));
		// 创建用于弹出消息对话框的Panel
		msgPanel = new ButtonPanel("消息对话框", null);
		msgBtn.addActionListener(new ShowAction());
		msgPanel.add(msgBtn);

		// 用于创建弹出确认对话框的Panel
		confirmPanel = new ButtonPanel("确认对话框", new String[] {
				"DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION",
				"OK_CANCEL_OPTION" });
		confrimBtn.addActionListener(new ShowAction());
		confirmPanel.add(confrimBtn);

		// 创建弹出输入对话框
		inputPanel = new ButtonPanel("输入对话框",
				new String[] { "单行文本框", "下拉类表选择框" });
		inputBtn.addActionListener(new ShowAction());
		inputPanel.add(inputBtn);

		// 创建选项对话框
		optionsPanel = new ButtonPanel("选项对话框", new String[] { "字符串选项", "图标选项",
				"对象选项" });
		optionBtn.addActionListener(new ShowAction());
		optionsPanel.add(optionBtn);

		bottom.add(msgPanel);
		bottom.add(confirmPanel);
		bottom.add(inputPanel);
		bottom.add(optionsPanel);

		Box box = new Box(BoxLayout.Y_AXIS);
		box.add(top);
		box.add(bottom);

		jf.add(box);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);

	}

	/*
	 * 根据用户选择返回选项类型
	 */
	private int getOptionType() {
		if (confirmPanel.getSelection().equals("DEFAULT_OPTION")) {
			return JOptionPane.DEFAULT_OPTION;

		} else if (confirmPanel.getSelection().equals("YES_NO_OPTION")) {
			return JOptionPane.YES_NO_OPTION;

		} else if (confirmPanel.getSelection().equals("YES_NO_CANCEL_OPTION")) {
			return JOptionPane.YES_NO_CANCEL_OPTION;

		} else {
			return JOptionPane.OK_CANCEL_OPTION;
		}

	}

	/*
	 * 根据用户选择返回消息
	 */
	private Object getMessage() {
		if (messagePanel.getSelection().equals("字符串消息")) {
			return msg;
		} else if (messagePanel.getSelection().equals("图标消息")) {
			return msg;
		} else if (messagePanel.getSelection().equals("组件消息")) {
			return msg;
		} else if (messagePanel.getSelection().equals("普通对象消息")) {
			return msg;
		} else {
			return new Object[] { msg, msgIcon, msgObject, msgComponent };
		}

	}

	/*
	 * 根据用户选择返回消息类型（决定图标区图标）
	 */
	private int getDialogYype() {
		if (messageTypePanel.getSelection().equals("ERROR_MESSAGE")) {
			return JOptionPane.ERROR_MESSAGE;
		} else if (messageTypePanel.getSelection()
				.equals("INFORMATION_MESSAGE")) {
			return JOptionPane.INFORMATION_MESSAGE;
		} else if (messageTypePanel.getSelection().equals("WARNING_MESSAGE")) {
			return JOptionPane.WARNING_MESSAGE;
		} else if (messageTypePanel.getSelection().equals("QUESTION_MESSAGE")) {
			return JOptionPane.QUESTION_MESSAGE;
		} else {
			return JOptionPane.PLAIN_MESSAGE;
		}
	}

	private Object[] getOptions() {
		if (optionsPanel.getSelection().equals("对象选项")) {
			return new Object[] { new Date(), new Date(), new Date() };
		} else if (optionsPanel.getSelection().equals("图标选项")) {
			return new Icon[] { new ImageIcon("image/1.png"),
					new ImageIcon("image/2.png"), new ImageIcon("image/3.png") };
		} else {
			
			return new String[] { "a", "b", "c", "d" };
		}
		

	}

	// 为按钮定义事件监听器
	private class ShowAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getActionCommand().equals("确认对话框")) {

				JOptionPane.showConfirmDialog(jf, getMessage(), "确认对话框",
						getOptionType(), getDialogYype());

			} else if (e.getActionCommand().equals("输入对话框")) {

				if (inputPanel.getSelection().equals("单行文本框")) {

					JOptionPane.showInputDialog(jf, getMessage(), "输入对话框",
							getDialogYype());

				} else {

					JOptionPane.showInputDialog(jf, getMessage(), "输入对话框",
							getDialogYype(), null, new String[] { "轻量级J2EE",
									"Struts2权威指南" }, "Struts2权威指南");
				}
			} else if (e.getActionCommand().equals("消息对话框")) {

				JOptionPane.showMessageDialog(jf, getMessage(), "消息对话框",
						getDialogYype());

			} else if (e.getActionCommand().equals("选项对话框")) {

				JOptionPane.showOptionDialog(jf, getMessage(), "选项对话框",
						getOptionType(), getDialogYype(), null, getOptions(),
						"a");
			}

		}

	}
	public static void main(String[] args) {
		new TestDialog().init();
	}

}
