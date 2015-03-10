package com.dialog.it;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/*
 *定义一个JPanel类的扩展类，该类的对象包含多个 
 *纵向排列的JRadioButton控件，且Panel扩展类可以
 * 指定一个字符串作为TitledBorder
 *
 **/
class ButtonPanel extends JPanel {

	private ButtonGroup group;

	public ButtonPanel(String title, String[] options) {
		setBorder(BorderFactory.createTitledBorder(
				BorderFactory.createEtchedBorder(), title));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		group = new ButtonGroup();
		for (int i = 0; options != null && i < options.length; i++) {

			JRadioButton b = new JRadioButton(options[i]);
			b.setActionCommand(options[i]);
			add(b);
			group.add(b);
			b.setSelected(i == 0);

		}

	}

	// 定义一个方法，用于返回用户选择的选项
	public String getSelection() {
		return group.getSelection().getActionCommand();

	}

}
