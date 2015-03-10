package com.special.frame.it;

import javax.swing.Icon;

public class Book {
  
	private String name;
	private Icon icon;
	private String desc;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(String name, Icon icon, String desc) {
		super();
		this.name = name;
		this.icon = icon;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Icon getIcon() {
		return icon;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Book [name=" + name + ", icon=" + icon + ", desc=" + desc + "]";
	}
	
	
}
