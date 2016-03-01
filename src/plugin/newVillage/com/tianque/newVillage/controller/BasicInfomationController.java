package com.tianque.newVillage.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.controller.BaseFileUploadAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.Organization;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.mobile.vo.MobilePropertyDict;
import com.tianque.newVillage.domain.BasicInfoMation;
import com.tianque.newVillage.domain.NewVillageLeaderTeam;
import com.tianque.newVillage.service.BasicInfomationService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Scope("prototype")
@Namespace("/baseinfo/basicInfomationManage")
@Controller("basicInfomationController")
public class BasicInfomationController extends BaseFileUploadAction {
	private Logger logger = LoggerFactory
			.getLogger(BasicInfomationController.class);
	private static int imgWidth = 310;
	private static int imgHeight = 235;
	private BasicInfoMation basicInfoMation;
	private List<NewVillageLeaderTeam> leaderTeamList;
	private NewVillageLeaderTeam newVillageLeaderTeam;
	private Organization organization;
	private String imgPath;
	private Long id;
	private List<MobilePropertyDict> mobilePropertyDicts;// 封装数据字典中学历

	@Autowired
	private BasicInfomationService basicInfomationService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private PropertyDictService propertyDictService;

	/***
	 * 界面组织跳转
	 */
	@Actions(value = { @Action(value = "disptchOperate", results = {
			@Result(name = "photoEdit", location = "/newCountryside/basesicInfo/maintainPhotos.jsp"),
			@Result(name = "basicInfomationEdit", location = "/newCountryside/basesicInfo/maintainBasicInfomation.jsp"),
			@Result(name = "basicInfoMationEditNature", location = "/newCountryside/basesicInfo/maintainOrganizationNatureDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) }) })
	public String disptchOperate() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "操作失败，未获得组织机构信息";
			return ERROR;
		}
		if (DialogMode.PHOTO_EDIT.equals(mode)
				|| DialogMode.BASICINFOMATIONEDIT.equals(mode)
				|| DialogMode.BASICINFOMATIONEDITNATURE.equals(mode)) {
			if (basicInfoMation != null && basicInfoMation.getId() != null) {
				basicInfoMation = basicInfomationService
						.getBasicInfoMationById(basicInfoMation.getId());
			} else {
				basicInfoMation = basicInfomationService
						.getBasicInfoMationByOrgId(organization.getId());
				if (basicInfoMation == null || basicInfoMation.getId() == null) {
					basicInfoMation = new BasicInfoMation();
					Organization simOrg = organizationDubboService
							.getSimpleOrgById(organization.getId());
					basicInfoMation.setOrganization(simOrg);
				}
			}
			return mode;
		}
		return SUCCESS;
	}

	@Action(value = "dispatchLeaderOperate", results = {
			@Result(name = "success", location = "/newCountryside/basesicInfo/villageLeaderTeamDlg.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String dispatchLeaderOperate() throws Exception {
		if (mode.equals(DialogMode.EDIT_MODE)) {
			if (null == newVillageLeaderTeam
					|| null == newVillageLeaderTeam.getId()) {
				errorMessage = "对象或id为空";
				return ERROR;
			}
			newVillageLeaderTeam = basicInfomationService
					.getNewVillageLeaderTeamById(newVillageLeaderTeam.getId());
		} else if (mode.equals(DialogMode.ADD_MODE)) {
			newVillageLeaderTeam = new NewVillageLeaderTeam();
		}
		// 从字典中查出学历
		//		mobilePropertyDicts = propertyDictService
		//				.findMobilePropertyDictByDomainName(PropertyTypes.SCHOOLING);
		return SUCCESS;
	}

	/***
	 * 辖区领导班子排序
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "leaderSort", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String leaderSort() throws Exception {
		return SUCCESS;
	}

	/***
	 * 清除图片
	 */
	@Action(value = "deleteVillagePhoto", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteVillagePhoto() throws Exception {
		if (basicInfoMation == null || basicInfoMation.getId() == null) {
			errorMessage = "操作失败，数据关键信息未获得";
			return ERROR;
		}
		basicInfoMation = basicInfomationService
				.getBasicInfoMationByOrgId(basicInfoMation.getId());
		basicInfoMation.setImgUrl("");
		basicInfomationService.updateBasicInfomationImgUrl(basicInfoMation);
		return SUCCESS;
	}

	/***
	 * 根据组织机构ID获取基础信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "getBasicInfomationByOrgId", results = {
			@Result(name = "success", type = "json", params = { "root",
					"basicInfoMation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String getBasicInfomationByOrgId() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "获取基础信息失败，未得到组织机构信息";
			return ERROR;
		}
		basicInfoMation = basicInfomationService
				.getBasicInfoMationByOrgId(organization.getId());
		return SUCCESS;
	}

	/***
	 * 根据组织机构ID获取领导班子成员
	 * 
	 * @return
	 */
	@Action(value = "getTeamListByOrgId", results = {
			@Result(name = "success", location = "/newCountryside/basesicInfo/newVillageLeaderList.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String getTeamListByOrgId() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "获取基础信息失败，未得到组织机构信息";
			return ERROR;
		}
		leaderTeamList = basicInfomationService
				.getNewVillageLeaderTeamByOrgId(organization.getId());
		return SUCCESS;
	}

	/***
	 * 新增或修改基础信息数据
	 * 
	 * @return
	 */
	@Action(value = "maintainBasicInfomation", results = {
			@Result(name = "success", type = "json", params = { "root",
					"basicInfoMation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainBasicInfomation() throws Exception {
		if (basicInfoMation == null) {
			errorMessage = "操作失败，基础信息数据未获得";
			return ERROR;
		}
		if (basicInfoMation.getId() != null) {
			// 修改
			basicInfoMation = basicInfomationService
					.updateBasicInfoMation(basicInfoMation);
		} else {
			// 新增
			basicInfoMation = basicInfomationService
					.addBasicInfoMation(basicInfoMation);
		}

		return SUCCESS;
	}

	/***
	 * 新增领导班子成员数据
	 * 
	 * @return
	 */
	@Action(value = "maintainLeaderTeam", results = {
			@Result(name = "success", type = "json", params = { "root",
					"newVillageLeaderTeam", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String maintainLeaderTeam() throws Exception {
		if (newVillageLeaderTeam == null) {
			errorMessage = "操作失败，成员数据未获得";
			return ERROR;
		}
		if (newVillageLeaderTeam.getId() != null) {
			// 修改
			newVillageLeaderTeam = basicInfomationService
					.updateNewVillageLeaderTeam(newVillageLeaderTeam);
		} else {
			// 新增
			newVillageLeaderTeam = basicInfomationService
					.addNewVillageLeaderTeam(newVillageLeaderTeam);
		}
		return SUCCESS;
	}

	/***
	 * 修改基础信息数据的简介
	 */
	@Action(value = "updateBasicInfomationbasicIntroduction", results = {
			@Result(name = "success", type = "json", params = { "root",
					"basicInfoMation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String updateBasicInfomationbasicIntroduction() throws Exception {
		if (basicInfoMation == null) {
			errorMessage = "操作失败，基础信息数据未获得";
			return ERROR;
		}
		if (basicInfoMation.getId() != null) {
			// 修改
			basicInfoMation = basicInfomationService
					.updateBasicInfomationbasicIntroduction(basicInfoMation);
		} else {
			// 新增
			basicInfoMation = basicInfomationService
					.addBasicInfoMation(basicInfoMation);
		}
		return SUCCESS;
	}

	/***
	 * 删除领导班子成员数据
	 * 
	 * @return
	 */
	@Action(value = "deleteLeaderTeamById", results = {
			@Result(name = "success", type = "json", params = { "root", "true",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public String deleteLeaderTeamById() throws Exception {
		if (id == null) {
			errorMessage = "删除失败，未获得关键数据";
			return ERROR;
		}
		basicInfomationService.deleteNewVillageLeaderTeamById(id);
		return SUCCESS;
	}

	private BasicInfoMation createBasicInfoMation(
			BasicInfoMation basicInfomation) {
		BasicInfoMation basicInfomationImg = new BasicInfoMation();
		basicInfomationImg.setImgUrl(basicInfomation.getImgUrl());
		basicInfomationImg.setId(basicInfomation.getId());
		return basicInfomationImg;
	}

	private void printBasicInfoMation(BasicInfoMation basicInfomation) {
		try {
			JSONObject jsonObject = JSONObject
					.fromObject(createBasicInfoMation(basicInfomation));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=GBK");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(jsonObject.toString());
		} catch (IOException e) {
			throw new ServiceValidationException("编辑失败", e);
		}
	}

	private String scanImage(String imageSrc) throws Exception {
		try {
			File file = new File(imageSrc);
			BufferedImage bufferedImage = ImageIO.read(file);
			BufferedImage scanBufferedImage = Scalr.resize(bufferedImage,
					imgWidth, imgHeight, Scalr.OP_ANTIALIAS);
			ImageIO.write(scanBufferedImage, "jpeg", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "success";
	}

	@Action(value = "updateOrSaveVillageProfileImgUrl", results = {
			@Result(name = "success", type = "json", params = { "root",
					"basicInfoMation", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage", "ignoreHierarchy", "false" }) })
	public void updateOrSaveVillageProfileImgUrl() throws Exception {
		long startTime = System.currentTimeMillis();
		if (!getUpload().exists()) {
			return;
		}
		if (getUpload().length() / 1024 / 1024 >= 2) {
			printBasicInfoMation(basicInfoMation);
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
			basicInfoMation.setImgUrl(imgPath);
		}
		if (basicInfoMation.getId() == null) {
			Organization simOrganization = organizationDubboService
					.getSimpleOrgById(organization.getId());
			basicInfoMation.setOrganization(simOrganization);
			basicInfoMation = basicInfomationService
					.addBasicInfoMation(basicInfoMation);
			printBasicInfoMation(basicInfoMation);

		} else {

			BasicInfoMation basicInfo = basicInfomationService
					.getBasicInfoMationByOrgId(organization.getId());// 通过组织机构ID和基础信息ID查询信息=========================================================
			basicInfo.setImgUrl(imgPath);
			// 修改基础信息数据=========================================================
			basicInfomationService.updateBasicInfomationImgUrl(basicInfo);
			printBasicInfoMation(basicInfoMation);
		}
		if ((System.currentTimeMillis() - startTime) > 3000) {
			logger.error("处理时间大于3000毫秒   {} ", imgPath);
		}
	}

	public NewVillageLeaderTeam getNewVillageLeaderTeam() {
		return newVillageLeaderTeam;
	}

	public void setNewVillageLeaderTeam(
			NewVillageLeaderTeam newVillageLeaderTeam) {
		this.newVillageLeaderTeam = newVillageLeaderTeam;
	}

	public BasicInfoMation getBasicInfoMation() {
		return basicInfoMation;
	}

	public void setBasicInfoMation(BasicInfoMation basicInfoMation) {
		this.basicInfoMation = basicInfoMation;
	}

	public List<NewVillageLeaderTeam> getLeaderTeamList() {
		return leaderTeamList;
	}

	public void setLeaderTeamList(List<NewVillageLeaderTeam> leaderTeamList) {
		this.leaderTeamList = leaderTeamList;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public List<MobilePropertyDict> getMobilePropertyDicts() {
		return mobilePropertyDicts;
	}

	public void setMobilePropertyDicts(
			List<MobilePropertyDict> mobilePropertyDicts) {
		this.mobilePropertyDicts = mobilePropertyDicts;
	}

}
