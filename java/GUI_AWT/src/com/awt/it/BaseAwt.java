package com.awt.it;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;

import javax.swing.Box;


public class BaseAwt {
	Frame f=new Frame("Test");
	
	Button ok=new Button("True");//定义一个按钮
	CheckboxGroup cbg=new CheckboxGroup();
	Checkbox male=new Checkbox("Boy",cbg,true);//定义一个单选框（处于cbg一组),初始化处于选中状态
	Checkbox female=new Checkbox("Girl",cbg,false);//定义一个单选框（处于cbg一组),初始化处于没有选中状态
	Checkbox married=new Checkbox("YES or NO",false);//定义一个复选框,初始化处于没有选中状态
	Choice colorChooser=new Choice();//定义一个下拉选择框
	List colorList=new List(6,true);//定义一个列表选择框
	TextArea ta=new TextArea(5,20);//定义一个5行、20列的多行文本域
	TextField name=new TextField(50);//定义一个50列的单行文本域
	
	public void initAwt(){
		colorChooser.add("Red");
		colorChooser.add("Green");
		colorChooser.add("Yellow");
		colorList.add("Red");
		colorList.add("Green");
		colorList.add("Yellow");
		
		Panel bottom=new Panel();//创建一个装载文本框、按钮的panel
		bottom.add(name);
		bottom.add(ok);
		f.add(bottom,BorderLayout.SOUTH);
		
		Panel checkPanel=new Panel();//创建一个装载下拉选择框、三个Checkbox的Panel
		checkPanel.add(colorChooser);
		checkPanel.add(male);
		checkPanel.add(female);
		checkPanel.add(married);
		
		Box topLeft=Box.createVerticalBox();//创建一个垂直排列组件的Box，装载多行文本域
		topLeft.add(ta);
		topLeft.add(checkPanel);
		
		Box top=Box.createHorizontalBox();//创建一个水平排列的Box，装topleft、colorlist
		top.add(topLeft);
		top.add(colorList);
		
		f.add(top);
		f.pack();
		f.setVisible(true);		
	}
	public static void main(String[] args) {
		new BaseAwt().initAwt();
	}
}
