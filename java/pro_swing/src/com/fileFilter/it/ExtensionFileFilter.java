package com.fileFilter.it;

import java.io.File;
import java.util.ArrayList;

import javax.swing.filechooser.FileFilter;

/*
 *创建 FileFilter的子类，用以实现文件过滤器功能
 **/
public class ExtensionFileFilter extends FileFilter {

	private String description;

	private ArrayList<String> extensions;
	
	public ExtensionFileFilter() {
		super();
		extensions = new ArrayList<String>();
		
	}
	
	/*
	 * 自定义方法，用于添加文件扩展名
	 */
	public void addExtension(String info) {
		
		if (!info.startsWith(".")) {
			info = "." + info;
			extensions.add(info.toLowerCase());
		}

	}

	

	/*
	 * 用于设置该文件过滤器的描述文本
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/*
	 * 未实现的方法，判断该文件过滤器是否接受该文件
	 */
	@Override
	public boolean accept(File f) {
		// 如果该文件是路径，接受该文件
		if (f.isDirectory()) {
			return true;
		}
		// 将文件名转换为小写（全部转换为小写后比较，用于忽略文件名大小写）
		String name = f.getName().toLowerCase();
		// 便利所有可接受的扩展名，如果扩展名相同，该文件就可接受
		for (String extension : extensions) {
			if (name.endsWith(extension)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * 未实现的方法，返回该文件过滤器的描述文本
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return description;
	}

}
