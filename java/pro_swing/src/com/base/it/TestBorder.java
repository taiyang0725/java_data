package com.base.it;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
/*
 *测试边框  
 **/
public class TestBorder {
	
	private JFrame f=new JFrame("边框展示");
	
	private void init() {

	f.setLayout(new GridLayout(2,4));
    //使用静态工厂方法创建BevelBorder
	Border bb=BorderFactory.createBevelBorder(BevelBorder.RAISED,Color
			.RED,Color.GREEN,Color.BLUE,Color.GRAY);
	f.add(getPanelWithBordwe(bb, "BevelBorder"));
	
	//使用静态工厂方法创建LineBorder
	Border lb=BorderFactory.createLineBorder(Color.ORANGE,10);
	f.add(getPanelWithBordwe(lb, "LineBorder"));
	
	//使用静态工厂方法创建EmptyBorder,EmptyBorder就是在组件四周留空
	Border eb=BorderFactory.createEmptyBorder(20,5,10,30);
	f.add(getPanelWithBordwe(eb, "EmptyBorder"));
	
	//使用静态工厂方法创建EtchedBorder
	Border etb=BorderFactory.createEtchedBorder(EtchedBorder.RAISED, 
			Color.RED,Color.GREEN);
	f.add(getPanelWithBordwe(etb, "EtchedBorder"));
	
	
	//直接创建TitledBorder，TitledBorder边框就是为原有的边框添加标题
	TitledBorder tb=new TitledBorder(lb,"title",TitledBorder.LEFT,
			TitledBorder.BOTTOM,new Font("StSong", Font.BOLD, 18),Color.BLUE);
	f.add(getPanelWithBordwe(tb, "TitledBorder"));
	
	/*
	 * 直接创建MatteBorder,MatteBorder边框是EmptyBorder
	 * 的子类，他可以指定留空区域的颜色或背景
	 */
	MatteBorder mb=new MatteBorder(20,5,10,30,Color.GREEN);
	f.add(getPanelWithBordwe(mb, "MatteBorder"));
	
	//CompoundBorder边框将两个边框组合成新边框
	CompoundBorder cb=new CompoundBorder(new LineBorder(Color.RED,8), tb);
	f.add(getPanelWithBordwe(cb, "CompoundBorder"));
	
	f.pack();
	f.setVisible(true);
	
	
	}
	private JPanel getPanelWithBordwe(Border b,String name) {
		// TODO Auto-generated method stub
		JPanel p=new JPanel();
		p.add(new JLabel(name));
		
		p.setBorder(b);//为Panel组件设置边框
		return p;

	}
	public static void main(String[] args) {
		new TestBorder().init();
	}

}
