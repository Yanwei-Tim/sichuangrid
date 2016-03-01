package com.tianque.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;

@SuppressWarnings("serial")
public class BaseFileUploadAction extends BaseAction {
	protected File upload;
	private String uploadFileName;

	private String storedFilePath;
	private String storedFileName;
	private File storedFile;

	protected void proccessUploadFile() throws Exception {

		FileInputStream fileInputStream = new FileInputStream(upload);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				fileInputStream);

		File file = createStoreFile();

		FileOutputStream fileOutputStream = new FileOutputStream(file);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
				fileOutputStream);

		int i = -1;
		while ((i = bufferedInputStream.read()) != -1) {
			bufferedOutputStream.write(i);
		}

		bufferedInputStream.close();
		fileInputStream.close();

		bufferedOutputStream.close();
		fileOutputStream.close();
	}

	private File createStoreFile() throws Exception {
		storedFile = new File(FileUtil.getWebRoot() + File.separator
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

	protected String getFileSuffix() {
		return uploadFileName.substring(uploadFileName.lastIndexOf("."));
	}

	private String createStoreFilePath() {
		storedFilePath = GridProperties.UPLOAD_FILE_FOLDER + File.separator
				+ getFileSuffix().substring(1).toLowerCase() + File.separator
				+ Calendar.getInstance().get(Calendar.YEAR) + File.separator
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

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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

	public File getStoredFile() {
		return storedFile;
	}

	public void setStoredFile(File storedFile) {
		this.storedFile = storedFile;
	}

}
