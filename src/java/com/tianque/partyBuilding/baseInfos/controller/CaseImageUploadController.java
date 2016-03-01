package com.tianque.partyBuilding.baseInfos.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.BaseFileUploadAction;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.Organization;
import com.tianque.partyBuilding.baseInfos.domain.CaseImageUpload;
import com.tianque.partyBuilding.baseInfos.service.CaseImageUploadService;

/**
 * 图片上传表:服务前端控制类
 * 
 * @author
 */
@Namespace("/caseImageUploadManage")
@Scope("request")
@Controller("caseImageUploadController")
public class CaseImageUploadController extends BaseFileUploadAction {
	private static Logger logger = LoggerFactory
			.getLogger(DistrictBasiccaseController.class);

	@Autowired
	private CaseImageUploadService caseImageUploadService;

	private static int imgWidth = 310;
	private static int imgHeight = 235;

	private CaseImageUpload caseImageUpload;
	private String imgPath;
	private Organization organization;

	/**
	 * 新增、修改图片
	 * 
	 * @throws Exception
	 */
	@Action(value = "updateOrSaveCaseImgUrl", results = { @Result(name = "error", type = "json", params = {
			"root", "true" }) })
	public void updateOrSaveCaseImgUrl() throws Exception {
		long startTime = System.currentTimeMillis();
		if (!getUpload().exists()) {
			return;
		}
		if (getUpload().length() / 1024 / 1024 >= 2) {
			caseImageUpload.setSuccess(false);
			printCaseImage(caseImageUpload);
			return;
		}
		if (getUpload() != null) {
			proccessUploadFile();
			imgPath = getStoredFilePath() + "/" + getStoredFileName();
			String sign = scanImage(FileUtil.getWebRoot() + File.separator
					+ imgPath);
			if (sign.equals("error")) {
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html;charset=GBK");
				PrintWriter printWriter = response.getWriter();
				printWriter.print("0");
				return;
			}
			caseImageUpload.setImgUrl(imgPath);
		}
		if (caseImageUpload.getId() == null) {
			caseImageUpload = caseImageUploadService.add(caseImageUpload);
			printCaseImage(caseImageUpload);
			caseImageUpload.setSuccess(true);

		} else {
			caseImageUpload = caseImageUploadService.get(caseImageUpload
					.getId());
			caseImageUpload.setImgUrl(imgPath);
			caseImageUpload = caseImageUploadService.update(caseImageUpload);
			caseImageUpload.setSuccess(true);
			printCaseImage(caseImageUpload);
		}
		if ((System.currentTimeMillis() - startTime) > 3000) {
			logger.error("处理时间大于3000毫秒   {} ", imgPath);
		}
	}

	/**
	 * 删除图片
	 * 
	 * @param org
	 * @return
	 */
	@Action(value = "deletePhotosAndOrgById", results = {
			@Result(name = "success", type = "json", params = { "root",
					"opreationResult", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePhotosAndOrgById() throws Exception {
		if (organization == null || caseImageUpload == null
				|| caseImageUpload.getImgUrl() == null
				|| caseImageUpload.getId() == null
				|| organization.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		String imgUrl = caseImageUpload.getImgUrl();
		opreationResult = caseImageUploadService
				.deleteCaseImageUploadByIdAndOrgId(caseImageUpload.getId(),
						organization.getId());
		if (opreationResult == true) {
			String ss = FileUtil.getWebRoot() + File.separator + imgUrl;
			File img = new File(ss);
			if (img.isFile()) {
				img.delete();
				return SUCCESS;
			}
		}

		return ERROR;
	}

	private String scanImage(String imageSrc) throws Exception {
		try {
			File file = new File(imageSrc);
			BufferedImage bufferedImage = ImageIO.read(file);
			BufferedImage scanBufferedImage = Scalr.resize(bufferedImage,
					imgWidth, imgHeight, Scalr.OP_ANTIALIAS);
			ImageIO.write(scanBufferedImage, "jpeg", file);
		} catch (Exception e) {
			logger.error("异常信息", e);
			e.printStackTrace();
		}
		return "success";
	}

	private void printCaseImage(CaseImageUpload caseImageUpload)
			throws Exception {
		try {
			JSONObject jsonObject = JSONObject
					.fromObject(createNewCaseImageUpload(caseImageUpload));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=GBK");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(jsonObject.toString());
		} catch (IOException e) {
			logger.error("异常信息", e);
		}
	}

	private CaseImageUpload createNewCaseImageUpload(
			CaseImageUpload caseImageUpload) {
		CaseImageUpload caseImage = new CaseImageUpload();
		caseImage.setImgUrl(caseImageUpload.getImgUrl());
		caseImage.setId(caseImageUpload.getId());
		caseImage.setSuccess(caseImageUpload.isSuccess());
		return caseImage;
	}

	public CaseImageUpload getCaseImageUpload() {
		return caseImageUpload;
	}

	public void setCaseImageUpload(CaseImageUpload caseImageUpload) {
		this.caseImageUpload = caseImageUpload;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
