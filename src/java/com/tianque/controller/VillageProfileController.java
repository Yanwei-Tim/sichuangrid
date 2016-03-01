package com.tianque.controller;

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
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.LeaderTeams;
import com.tianque.domain.Organization;
import com.tianque.domain.VillageProfile;
import com.tianque.service.LeaderTeamsService;
import com.tianque.service.VillageProfileService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Transactional
@Scope("prototype")
@Namespace("/baseinfo/villageProfile")
@SuppressWarnings("serial")
@Controller("villageProfileController")
public class VillageProfileController extends BaseFileUploadAction {

	private static int imgWidth = 310;
	private static int imgHeight = 235;
	private Logger logger = LoggerFactory
			.getLogger(VillageProfileController.class);
	@Autowired
	private VillageProfileService villageProfileService;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private LeaderTeamsService leaderTeamsService;

	private VillageProfile villageProfile = new VillageProfile();
	private Organization organization;
	private String imgPath;
	private boolean falg;
	private LeaderTeams leaderTeams;
	private List<LeaderTeams> leaderTeamList;
	private String dialogName;

	public String getVillageProfileAndOrgById() throws Exception {
		if (organization == null || organization.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		organization = organizationDubboService.getSimpleOrgById(organization
				.getId());
		villageProfile.setOrganization(organization);
		villageProfile = villageProfileService.findVillageProfileById(
				villageProfile.getId(), organization.getId());
		if (villageProfile == null) {
			villageProfile = new VillageProfile();
			villageProfile.setOrganization(organization);
		}
		return mode;
	}

	public void updateOrSaveVillageProfileImgUrl() throws Exception {
		long startTime = System.currentTimeMillis();
		if (!getUpload().exists()) {
			return;
		}
		if (getUpload().length() / 1024 / 1024 >= 2) {
			villageProfile.setSuccess(false);
			printVillageProfile(villageProfile);
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
			villageProfile.setImgUrl(imgPath);
		}
		if (villageProfile.getId() == null) {
			villageProfile = villageProfileService
					.addVillageProfile(villageProfile);
			printVillageProfile(villageProfile);
			villageProfile.setSuccess(true);

		} else {
			VillageProfile vpf = villageProfileService.findVillageProfileById(
					villageProfile.getId(), villageProfile.getOrganization()
							.getId());
			vpf.setImgUrl(imgPath);
			villageProfile = villageProfileService.updateVillageProfile(vpf);
			villageProfile.setSuccess(true);
			printVillageProfile(villageProfile);
		}
		if ((System.currentTimeMillis() - startTime) > 3000) {
			logger.error("处理时间大于3000毫秒   {} ", imgPath);
		}
	}

	public String deletePhotosAndOrgById() throws Exception {
		if (organization == null || villageProfile == null) {
			errorMessage = "参数错误";
			return ERROR;
		} else if (villageProfile.getImgUrl() == null
				|| villageProfile.getId() == null
				|| organization.getId() == null) {
			errorMessage = "参数错误";
			return ERROR;
		}
		String imgUrl = villageProfile.getImgUrl();
		villageProfile = villageProfileService.deletePhotosAndOrgById(imgUrl,
				villageProfile.getId(), organization.getId());
		if (villageProfile.getImgUrl() == null) {
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

	private void printVillageProfile(VillageProfile villageProfile) {
		try {
			JSONObject jsonObject = JSONObject
					.fromObject(createNewVillageProfile(villageProfile));
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=GBK");
			PrintWriter printWriter = response.getWriter();
			printWriter.print(jsonObject.toString());
		} catch (IOException e) {
			logger.error("异常信息", e);
		}
	}

	private VillageProfile createNewVillageProfile(VillageProfile villageProfile) {
		VillageProfile villageProfil = new VillageProfile();
		villageProfil.setImgUrl(villageProfile.getImgUrl());
		villageProfil.setId(villageProfile.getId());
		villageProfil.setSuccess(villageProfile.isSuccess());
		return villageProfil;
	}

	public String updateOrAddVillageProfile() throws Exception {
		if (villageProfile.getId() == null) {
			villageProfile = villageProfileService
					.addVillageProfile(villageProfile);
		} else {
			VillageProfile vpf = villageProfileService.findVillageProfileById(
					villageProfile.getId(), villageProfile.getOrganization()
							.getId());
			villageProfile.setImgUrl(vpf.getImgUrl());
			if (mode.equals("baseVillageProfile")) {
				villageProfile = villageProfileService
						.updateVillageProfile(villageProfile);
			} else {
				// VillageProfile vpf = villageProfileService
				// .findVillageProfileById(villageProfile.getId(),
				// villageProfile.getOrganization().getId());
				if (mode.equals("natureGeography")) {
					vpf.setNatureGeography(villageProfile.getNatureGeography());
				} else if (mode.equals("economyGeography")) {
					vpf.setEconomyGeography(villageProfile
							.getEconomyGeography());
				} else if (mode.equals("communityGeography")) {
					vpf.setCommunityGeography(villageProfile
							.getCommunityGeography());
				} else if (mode.equals("itemEngineering")) {
					vpf.setItemEngineering(villageProfile.getItemEngineering());
				} else if (mode.equals("editIntroduction")) {
					vpf.setIntroduction(villageProfile.getIntroduction());
				}
				villageProfile = villageProfileService
						.updateVillageProfile(vpf);
			}
		}
		return SUCCESS;
	}

	@Action(value = "dispatchLeaderOperate", results = {
			@Result(name = "success", location = "/baseinfo/villageProfile/leaderTeamDlg.jsp"),
			@Result(name = "error", location = "/error.jsp") })
	public String dispatchLeaderOperate() throws Exception {
		if (mode.equals(DialogMode.VIEW_MODE)) {

		} else if (mode.equals(DialogMode.EDIT_MODE)) {
			if (null != leaderTeams && null != leaderTeams.getId()) {
				leaderTeams = leaderTeamsService.getLeaderTeamsById(leaderTeams
						.getId());
			} else {
				errorMessage = "对象或id为空";
				return ERROR;
			}
		} else if (mode.equals(DialogMode.ADD_MODE)) {

		}
		return SUCCESS;
	}

	@Action(value = "addLeaderTeam", results = { @Result(name = "success", type = "json", params = {
			"root", "leaderTeams", "ignoreHierarchy", "false" }) })
	public String addLeaderTeam() throws Exception {
		if (leaderTeams != null && leaderTeams.getOrgId() != null) {
			organization = organizationDubboService
					.getSimpleOrgById(leaderTeams.getOrgId());
			leaderTeams.setOrgCode(organization.getOrgInternalCode());
			leaderTeamsService.addLeaderTeams(leaderTeams);
		} else {
			errorMessage = "参数错误";
			return ERROR;
		}
		return SUCCESS;
	}

	@Action(value = "deleteLeaderTeam", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String deleteLeaderTeam() throws Exception {
		if (null != leaderTeams && null != leaderTeams.getId()) {
			leaderTeamsService.deleteLeaderTeamsById(leaderTeams.getId());
			return SUCCESS;
		} else {
			errorMessage = "参数错误";
			return ERROR;
		}
	}

	@Action(value = "updateLeaderTeam", results = { @Result(name = "success", type = "json", params = {
			"root", "leaderTeams", "ignoreHierarchy", "false" }) })
	public String updateLeaderTeam() throws Exception {
		if (null != leaderTeams && null != leaderTeams.getId()) {
			leaderTeams = leaderTeamsService.updateLeaderTeamsById(leaderTeams);
			return SUCCESS;
		} else {
			errorMessage = "参数错误";
			return ERROR;
		}
	}

	@Action(value = "leaderTeamList", results = { @Result(name = "success", location = "/baseinfo/villageProfile/leaderList.jsp") })
	public String leaderTeamList() throws Exception {
		if (null != leaderTeams && null != leaderTeams.getOrgId()) {
			leaderTeamList = leaderTeamsService
					.getLeaderTeamsListByOrgId(leaderTeams);
			return SUCCESS;
		} else {
			errorMessage = "参数错误";
			return ERROR;
		}
	}

	@Action(value = "leaderSort", results = { @Result(name = "success", type = "json", params = {
			"root", "true" }) })
	public String leaderSort() throws Exception {
		leaderTeamsService.sortLeaderById(leaderTeamList);
		return SUCCESS;
	}

	// 没有显式的调用
	// public String getVillageProfileByFullPinYin() throws Exception {
	// if (null == organization.getFullPinyin()) {
	// return ERROR;
	// } else {
	// setVillageProfile(villageProfileService
	// .getVillageProfileByFullPinYin(organization.getFullPinyin()));
	// }
	// return SUCCESS;
	// }

	public boolean isFalg() {
		return falg;
	}

	public void setFalg(boolean falg) {
		this.falg = falg;
	}

	public Organization getOrganization() {
		if (null == organization) {
			organization = new Organization();
		}
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public VillageProfile getVillageProfile() {
		return villageProfile;
	}

	public void setVillageProfile(VillageProfile villageProfile) {
		this.villageProfile = villageProfile;
	}

	public LeaderTeams getLeaderTeams() {
		return leaderTeams;
	}

	public void setLeaderTeams(LeaderTeams leaderTeams) {
		this.leaderTeams = leaderTeams;
	}

	public List<LeaderTeams> getLeaderTeamList() {
		return leaderTeamList;
	}

	public void setLeaderTeamList(List<LeaderTeams> leaderTeamList) {
		this.leaderTeamList = leaderTeamList;
	}

	public String getDialogName() {
		return dialogName;
	}

	public void setDialogName(String dialogName) {
		this.dialogName = dialogName;
	}
}
