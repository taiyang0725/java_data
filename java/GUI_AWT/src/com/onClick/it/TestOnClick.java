package com.onClick.it;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TestOnClick implements WindowListener {

	private Frame f = new Frame("Test");
	private TextArea ta = new TextArea(6, 40);
	private Button b1 = new Button("Button-1");
	private Button b2 = new Button("Button-2");

	private void initView() {
		FirstListener f1=new FirstListener();//创建监听器实例
		
		b1.addActionListener(f1);
		b1.addActionListener(new SecondListener());
		
		b2.addActionListener(f1);
		
		f.add(ta);
		
		Panel p=new Panel();
		p.add(b1);
		p.add(b2);
		
		f.add(p,BorderLayout.SOUTH);
		f.pack();
		f.setVisible(true);

	}

	class FirstListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
            ta.append("点击了,事件源："+e.getActionCommand()+"\n");
		}

	}

	class SecondListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ta.append("点击了："+e.getActionCommand());
		}
	}
	//监听窗口的方法,实现事件监听器接口，需要实现所有未实现的方法
	public void windowListener(){
		f.addWindowListener(this);
		
	}
	//监听窗口的方法,继承事件适配器，只需实现心想的方法
	public void windowListenerAd(){
		f.addWindowListener(new MyAdapter());
		
	}
	public static void main(String[] args) {
		TestOnClick click=new TestOnClick();
		click.initView();
		//click.windowListener();
		click.windowListenerAd();
	}
	//窗口监听器未实现的方法
	@Override
	public void windowActivated(WindowEvent arg0) {

		ta.append("窗口被激活！\n");
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		
		ta.append("窗口被成功关闭！\n");
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		
		ta.append("用户试图关闭窗口！\n");
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		
		ta.append("窗口失去焦点！\n");
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		
		ta.append("窗口被恢复！\n");
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		
		ta.append("窗口被最小化！\n");
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		
		ta.append("窗口被初次打开！\n");
		
	}
	class MyAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			super.windowClosing(e);
			System.exit(0);
		}
		
	}
}
