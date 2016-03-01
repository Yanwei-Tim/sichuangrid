package com.tianque.controller;

import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.FileUtil;
import com.tianque.exception.base.BusinessValidationException;

@Transactional
@Controller("downloadFileController")
@Scope("request")
public class DownloadFileController extends BaseAction {

	private static Logger logger = LoggerFactory
			.getLogger(DownloadFileController.class);

	private String dataTemplatesName;

	public void downloadDataTemplate() throws Exception {
		try {
			dataTemplatesName = "Chrome.zip";
			inputStream = new java.io.FileInputStream(FileUtil.getWebRoot()
					+ "/download/" + dataTemplatesName);
			downloadFileName = new String(dataTemplatesName.getBytes("gbk"),
					"ISO8859-1");
			OutputStream outputStream = ServletActionContext.getResponse()
					.getOutputStream();
			ServletActionContext.getResponse().setContentType(
					"application/octet-stream;charset=GBK");
			ServletActionContext.getResponse().addHeader("Content-Disposition",
					"attachment; filename=" + downloadFileName);
			int read;
			while ((read = inputStream.read()) != -1) {
				outputStream.write(read);
			}
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			this.errorMessage = e.getMessage();
			logger.error("浏览器下载错误", e);
			throw new BusinessValidationException("文件打开失败!");
		} catch (UnsupportedEncodingException uee) {
			this.errorMessage = uee.getMessage();
			logger.error("浏览器下载错误", uee);
			throw new BusinessValidationException("文件打开失败!");
		} catch (Exception e) {
			this.errorMessage = e.getMessage();
			logger.error("浏览器下载错误", e);
		}
	}

	public String getDataTemplatesName() {
		return dataTemplatesName;
	}

	public void setDataTemplatesName(String dataTemplatesName) {
		this.dataTemplatesName = dataTemplatesName;
	}

}
