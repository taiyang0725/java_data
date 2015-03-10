package com.fileFilter.it;

import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;

/*
 *自定义一个FileView类，用于为指定类型的指定图标 
 **/
public class FileIconView extends FileView {

	private FileFilter filter;

	public FileIconView(FileFilter filter) {
		super();
		this.filter = filter;
	}

	public Icon getIcon(File f) {
		if (!f.isDirectory() && filter.accept(f)) {

			return new ImageIcon("image/1.png");

		} else if (f.isDirectory()) {

			// 获取所有根目录
			File[] fList = File.listRoots();
			for (File tem : fList) {
				// 如果该路径是根路径
				if (tem.equals(f)) {
					return new ImageIcon("image/2.png");
				}
			}
			return new ImageIcon("image/3.png");
		} else {

			return null;
		}
	}

}
