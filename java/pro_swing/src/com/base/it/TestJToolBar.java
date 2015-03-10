package com.base.it;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

/*
 *测试工具条 
 **/
public class TestJToolBar {

	private JFrame jf = new JFrame("测试工具条");
	private JTextArea jta = new JTextArea(6, 35);
	private JToolBar jtb = new JToolBar();
	private JMenuBar jmb = new JMenuBar();
	private JMenu edit = new JMenu("编辑");
	private Clipboard clipboard = Toolkit.getDefaultToolkit()
			.getSystemClipboard();
	// 创建‘粘贴’Action，该Action用于创建菜单项、工具按钮和普通按钮
	javax.swing.Action pasteA = new AbstractAction("粘贴", new ImageIcon(
			"image/1.png")) {

		@Override
		public void actionPerformed(ActionEvent e) {
			/* 如果剪贴板中包含stringFlavor内容 */
			if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {

				try {
					String content = (String) clipboard
							.getData(DataFlavor.stringFlavor);// 取出内容

					jta.replaceRange(content, jta.getSelectionStart(),
							jta.getSelectionEnd());// 将选中内容替换成剪贴板中的内容
				} catch (UnsupportedFlavorException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		}
	};
	javax.swing.Action copyA = new AbstractAction("复制", new ImageIcon(
			"image/2.png")) {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			StringSelection contents = new StringSelection(
					jta.getSelectedText());
			// 将StringSelection对象放入剪贴板
			clipboard.setContents(contents, null);
			/* 如果剪贴板中包含stringFlavor内容 */
			if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
				pasteA.setEnabled(true);// 将pasteA激活

			}
		}
	};

	private void init() {
		pasteA.setEnabled(false);// 默认处于不激活状态
		jf.add(new JScrollPane(jta));

		JButton copy = new JButton(copyA);
		JButton paste = new JButton(pasteA);

		JPanel jp = new JPanel();
		jp.add(copy);
		jp.add(paste);

		jf.add(jp, BorderLayout.SOUTH);

		// 工具条中添加Action对象，该对象将会转换成工具按钮
		jtb.add(copyA);
		jtb.addSeparator();
		jtb.add(pasteA);

		edit.add(copyA);
		edit.add(pasteA);

		jmb.add(edit);
		jf.setJMenuBar(jmb);

		// 设置工具条和工具按钮之间的页边距
		jtb.setMargin(new Insets(20, 10, 5, 30));

		jf.add(jtb, BorderLayout.NORTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.pack();
		jf.setVisible(true);

	}
	public static void main(String[] args) {
		new TestJToolBar().init();
	}

}
