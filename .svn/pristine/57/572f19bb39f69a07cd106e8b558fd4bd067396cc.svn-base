package com.tianque.mobile.sysadmin.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.mobile.base.BaseMobileAction;
import com.tianque.mobile.sysadmin.ImageUploadMobileAdapter;

public class ImageUploadMobileAdapterImpl extends BaseMobileAction implements
		ImageUploadMobileAdapter {

	private File header;
	private File storedFile;
	private String storedFilePath;
	private String storedFileName;
	private String headerFileName;

	@Override
	public void uploadImg() throws Exception {
		File file = createStoreFile();
		BufferedImage im = ImageIO.read(header);
		ImageIO.write(im, getFileSuffix().substring(1), file);
		printOutData();
	}

	private void printOutData() throws IOException {
		ActionContext ctx = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) ctx
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
			pw.print(GridProperties.UPLOAD_FILE_FOLDER + storedFilePath
					+ File.separator + storedFileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
				pw = null;
			}
		}
	}

	private File createStoreFile() throws Exception {
		storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.UPLOAD_FILE_FOLDER + File.separator
				+ createStoreFilePath() + File.separator
				+ createStoreFileName());
		if (!storedFile.getParentFile().isDirectory()) {
			storedFile.getParentFile().mkdirs();
		}
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private String createStoreFilePath() {
		storedFilePath = File.separator + getFileSuffix().substring(1)
				+ File.separator + Calendar.getInstance().get(Calendar.YEAR)
				+ File.separator
				+ (Calendar.getInstance().get(Calendar.MONTH) + 1)
				+ File.separator
				+ (Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
		return storedFilePath;
	}

	private String createStoreFileName() {
		storedFileName = new StringBuffer()
				.append(Calendar.getInstance().get(Calendar.HOUR_OF_DAY))
				.append(Calendar.getInstance().get(Calendar.MINUTE))
				.append(Calendar.getInstance().get(Calendar.SECOND))
				.append(Calendar.getInstance().get(Calendar.MILLISECOND))
				.append((int) (Math.random() * 1000)).append(getFileSuffix())
				.toString();
		return storedFileName;
	}

	private String getFileSuffix() {
		return headerFileName.substring(headerFileName.lastIndexOf("."))
				.toLowerCase();
	}

	public File getHeader() {
		return header;
	}

	public void setHeader(File header) {
		this.header = header;
	}

	public File getStoredFile() {
		return storedFile;
	}

	public void setStoredFile(File storedFile) {
		this.storedFile = storedFile;
	}

	public String getStoredFilePath() {
		return storedFilePath;
	}

	public void setStoredFilePath(String storedFilePath) {
		this.storedFilePath = storedFilePath;
	}

	public String getStoredFileName() {
		return storedFileName;
	}

	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	public String getHeaderFileName() {
		return headerFileName;
	}

	public void setHeaderFileName(String headerFileName) {
		this.headerFileName = headerFileName;
	}

}
