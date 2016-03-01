package com.tianque.controller.mobileUpdate;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.tianque.controller.BaseFileUploadAction;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.exception.base.BusinessValidationException;

public class FileUploadForMobileUpdateAction extends BaseFileUploadAction {

	protected void proccessUploadFileByMobile(String param) throws Exception {
		File judgeFile = new File(upload.getPath());
		if (!judgeFile.exists()) {
			throw new BusinessValidationException("文件为空，无法上传，请您重新选择");
		}
		FileInputStream fileInputStream = new FileInputStream(upload);
		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				fileInputStream);

		File file = createStoreFileByMobile(param);

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

	/**
	 * 创建存放目录
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public File createStoreFileByMobile(String param) throws Exception {
		File storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.UPLOAD_FILE_FOLDER + File.separator
				+ getFileSuffix().substring(1).toLowerCase() + File.separator
				+ param);
		if (!storedFile.getParentFile().isDirectory()) {
			storedFile.getParentFile().mkdirs();
		}
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

}
