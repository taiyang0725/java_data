package com.copy.it;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ImageSelection implements Transferable {

	private Image image;

	/*
	 * 构造方法，负责持有一个Image对象
	 */
	public ImageSelection(Image image) {
		super();
		this.image = image;
	}

	/*
	 * 取出该Transferable对象里的实际数据
	 */
	@Override
	public Object getTransferData(DataFlavor flavor)
			throws UnsupportedFlavorException, IOException {

		if (flavor.equals(DataFlavor.imageFlavor)) {
			return image;
		} else {
			throw new UnsupportedFlavorException(flavor);
		}

	}

	/*
	 * 返回该Transferable对象所支持的所有 DataFlavor
	 */
	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return new DataFlavor[] { DataFlavor.imageFlavor };
	}

	/*
	 * 返回该Transferable对象是否支持指定的DataFlavor
	 */
	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return flavor.equals(DataFlavor.imageFlavor);
	}

}
