package com.tianque.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.domain.HainingVillageMap;
import com.tianque.domain.Organization;
import com.tianque.service.HainingVillageMapService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("hainingVillageMapController")
@Namespace("/hainingVillageMap")
@Scope("prototype")
@Transactional
public class HainingVillageMapController extends BaseFileUploadAction {
	@Autowired
	private HainingVillageMapService hainingVillageMapService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	private HainingVillageMap hainingVillageMap;
	private String imgPath;
	private boolean flag;

	private static int imgWidth = 900;
	private static int imgHeight = 460;
	private String departmentNo;

	private Logger logger = Logger.getLogger(HainingVillageMapController.class);

	@Action(value = "dispatch", results = {
			@Result(name = "success", type = "dispatcher", location = "/workBench/haining/maintainPhotoDialog.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatch() throws Exception {
		if (hainingVillageMap == null
				|| isStringEmpty(hainingVillageMap.getDepartmentNo())) {
			errorMessage = "<p style='color:red;text-align:center;font-weight:bold;font-size:18px'>参数错误或缺少部门编号，请和管理员联系!</p>";
			return ERROR;
		}
		if (hainingVillageMap.getId() == null) {
			return SUCCESS;
		} else {
			hainingVillageMap = hainingVillageMapService
					.getHainingVillageMapByDepartmentNo(hainingVillageMap
							.getDepartmentNo());
			return SUCCESS;
		}
	}

	@Action(value = "getHainingVillageMapByDepartmentNo", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hainingVillageMap", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String getHainingVillageMapByDepartmentNo() throws Exception {
		if (hainingVillageMap == null
				|| isStringEmpty(hainingVillageMap.getDepartmentNo())) {
			errorMessage = "参数错误";
			return ERROR;
		}
		departmentNo = hainingVillageMap.getDepartmentNo();

		hainingVillageMap = hainingVillageMapService
				.getHainingVillageMapByDepartmentNo(hainingVillageMap
						.getDepartmentNo());
		if (hainingVillageMap == null) {
			hainingVillageMap = new HainingVillageMap();
		}
		Organization org = organizationDubboService
				.getOrgByDepartmentNo(departmentNo);
		if (org != null) {
			hainingVillageMap.setOrgName(org.getOrgName());
		}
		return SUCCESS;
	}

	@Action(value = "updateOrSaveVillageProfileImgUrl", results = {
			@Result(name = "success", type = "json", params = { "root",
					"hainingVillageMap", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public void updateOrSaveVillageProfileImgUrl() throws Exception {
		if (!getUpload().exists()) {
			hainingVillageMap.setDisposeSuccess(false);
			hainingVillageMap.setDisposeResult("文件不能为空");
			printHainingVillageMap(hainingVillageMap);
			return;
		}
		if (getUpload().length() / 1024 / 1024 >= 2) {
			hainingVillageMap.setDisposeSuccess(false);
			hainingVillageMap.setDisposeResult("文件大小不能超过2M");
			printHainingVillageMap(hainingVillageMap);
			return;
		}
		if (getUpload() != null) {
			proccessUploadFile();
			imgPath = getStoredFilePath() + "/" + getStoredFileName();
			String sign = scanImage(FileUtil.getWebRoot() + File.separator
					+ imgPath);
			if (sign.equals("error")) {
				hainingVillageMap.setDisposeSuccess(false);
				hainingVillageMap.setDisposeResult("文件在处理过程中发生异常，上传失败！");
				printHainingVillageMap(hainingVillageMap);
				return;
			}
		}
		if (hainingVillageMap.getId() == null) {
			hainingVillageMap.setImgUrl(imgPath);
			hainingVillageMap = hainingVillageMapService
					.addHainingVillageMap(hainingVillageMap);
			hainingVillageMap.setDisposeSuccess(true);
			hainingVillageMap.setDisposeResult("上传成功 ");
			printHainingVillageMap(hainingVillageMap);

		} else {
			HainingVillageMap result = hainingVillageMapService
					.getHainingVillageMapByDepartmentNo(hainingVillageMap
							.getDepartmentNo());
			result.setImgUrl(imgPath);
			hainingVillageMapService.updateHainingVillageMap(result);
			result.setDisposeSuccess(true);
			result.setDisposeResult("上传成功");
			printHainingVillageMap(result);
		}
	}

	@Action(value = "deleteImg", results = {
			@Result(name = "success", type = "json", params = { "root", "flag" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deleteImg() throws Exception {
		if (isStringEmpty(hainingVillageMap.getDepartmentNo(),
				hainingVillageMap.getImgUrl())) {
			errorMessage = "参数错误";
			return ERROR;
		}
		flag = hainingVillageMapService.deleteImg(hainingVillageMap);
		return SUCCESS;

	}

	private void printHainingVillageMap(HainingVillageMap hainingVillageMap)
			throws Exception {
		JSONObject jsonObject = JSONObject
				.fromObject(createNewHainingVillageMap(hainingVillageMap));
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		PrintWriter printWriter = response.getWriter();
		printWriter.print(jsonObject.toString());
	}

	private HainingVillageMap createNewHainingVillageMap(
			HainingVillageMap hainingVillageMap) {
		HainingVillageMap result = new HainingVillageMap();
		result.setImgUrl(hainingVillageMap.getImgUrl());
		result.setId(hainingVillageMap.getId());
		result.setDepartmentNo(hainingVillageMap.getDepartmentNo());
		result.setDisposeSuccess(hainingVillageMap.getDisposeSuccess());
		result.setDisposeResult(hainingVillageMap.getDisposeResult());
		return result;
	}

	private String scanImage(String imageSrc) throws Exception {
		try {
			File file = new File(imageSrc);
			BufferedImage bufferedImage = ImageIO.read(file);
			BufferedImage scanBufferedImage = Scalr.resize(bufferedImage,
					Scalr.Method.SPEED, Scalr.Mode.FIT_TO_HEIGHT, imgWidth,
					imgHeight, Scalr.OP_ANTIALIAS);
			ImageIO.write(scanBufferedImage, "jpeg", file);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "success";
	}

	private boolean isStringEmpty(String... str) {
		for (int i = 0; i < str.length; i++) {
			if (str[i] == null || "".equals(str[i])) {
				return true;
			}
		}
		return false;
	}

	public HainingVillageMapService getHainingVillageMapService() {
		return hainingVillageMapService;
	}

	public void setHainingVillageMapService(
			HainingVillageMapService hainingVillageMapService) {
		this.hainingVillageMapService = hainingVillageMapService;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public HainingVillageMap getHainingVillageMap() {
		return hainingVillageMap;
	}

	public void setHainingVillageMap(HainingVillageMap hainingVillageMap) {
		this.hainingVillageMap = hainingVillageMap;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public static int getImgWidth() {
		return imgWidth;
	}

	public static void setImgWidth(int imgWidth) {
		HainingVillageMapController.imgWidth = imgWidth;
	}

	public static int getImgHeight() {
		return imgHeight;
	}

	public static void setImgHeight(int imgHeight) {
		HainingVillageMapController.imgHeight = imgHeight;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public OrganizationDubboService getOrganizationDubboService() {
		return organizationDubboService;
	}

	public void setOrganizationDubboService(OrganizationDubboService organizationDubboService) {
		this.organizationDubboService = organizationDubboService;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

}
