package com.tianque.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageContentType {

	public static boolean upLoadImageVerification(File upLoadImage) {
		if (!upLoadImage.exists()) {
			return false;
		}
		Pattern pattern = Pattern.compile("^.*?\\.(jpg|gif|png|jpeg|bmp)$");
		Matcher matcher = pattern.matcher(upLoadImage.getName().toLowerCase());
		if (!matcher.matches()) {
			return false;
		}
		if (!isPermitImageType(upLoadImage)) {
			return false;
		}
		return true;
	}

	private final static boolean isPermitImageType(File file) {
		if (isImage(file)) {
			try {
				ImageInputStream imageInputStream = ImageIO
						.createImageInputStream(file);
				Iterator<ImageReader> iterator = ImageIO
						.getImageReaders(imageInputStream);
				if (!iterator.hasNext()) {
					return false;
				}
				ImageReader reader = iterator.next();
				imageInputStream.close();
				Pattern pattern = Pattern.compile("(jpg|gif|png|jpeg|bmp)$");
				Matcher matcher = pattern.matcher(reader.getFormatName()
						.toLowerCase());
				return matcher.matches();
			} catch (IOException e) {
				return false;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	private static final boolean isImage(File file) {
		boolean flag = false;
		try {
			BufferedImage bufferedImage = ImageIO.read(file);
			int width = bufferedImage.getWidth();
			int height = bufferedImage.getHeight();
			if (width == 0 || height == 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (IOException e) {
			flag = false;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
}
