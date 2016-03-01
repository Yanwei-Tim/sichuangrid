package com.tianque.qrcode.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.controller.BaseFileUploadAction;
import com.tianque.controller.annotation.EncryptAnnotation;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.domain.MobileUpdate;
import com.tianque.domain.Organization;
import com.tianque.domain.User;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.qrcode.domain.QrcodeDomain;
import com.tianque.qrcode.util.TwoDimensionCode;
import com.tianque.service.mobileUpdate.MobileUpdateService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PermissionService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 二维码生成:服务前端控制类
 * 
 * @author
 * 
 */
@Namespace("/twoDimensionCodeManage")
@Scope("request")
@Controller("twoDimensionCodeController")
public class TwoDimensionCodeController extends BaseFileUploadAction {
	private static Logger logger = LoggerFactory
			.getLogger(TwoDimensionCodeController.class);

	private static final String USER_QRCODETYPE = "userQrcodeType";// 二维码用户类型
	private static final String HOUSE_QRCODETYPE = "houseQrcodeType";// 二维码实有房屋类型
	private static final String MOBILE_QRCODETYPE = "mobileQrcodeType";// 二维码移动终端类型

	@Autowired
	private PermissionService permissionService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private ActualHouseService houseInfoService;
	@Autowired
	private MobileUpdateService mobileUpdateService;
	@Autowired
	private PropertyDictService propertyDictService;

	private String qrcodePath;
	private User user;
	private HouseInfo houseInfo;
	private MobileUpdate mobileUpdate;
	private String ids;
	private QrcodeDomain qrcodeDomain;
	private List<QrcodeDomain> qrcodeList = new ArrayList<QrcodeDomain>();
	private String qrcodeType;// 二维码类型

	/**
	 * 生成二维码
	 * 
	 * @throws Exception
	 */
	@Action(value = "generateQrcode", results = {
			// @Result(name = "success", type = "json", params = { "root",
			// "qrcodePathList" }),
			@Result(name = "success", location = "/sysadmin/userManage/qrcodeList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String generateQrcode() throws Exception {
		long startTime = System.currentTimeMillis();
		if (ids == null || "".equals(ids.trim()) || qrcodeType == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要操作的记录!";
			return ERROR;
		}

		Organization org = new Organization();
		TwoDimensionCode handler = new TwoDimensionCode();
		String content = null;
		for (Long id : idsLong) {
			if (USER_QRCODETYPE.equals(qrcodeType)) {
				user = permissionService.getSimpleUserById(id);
				qrcodePath = createStoreFile(user.getUserName() + ".png")
						.getPath();// 存入项目的二维码地址
				org = organizationDubboService.getSimpleOrgById(user
						.getOrganization().getId());
				if (org == null) {
					this.errorMessage = "组织机构不能为空";
					return ERROR;
				}
				content = "BEGIN:VCARD\nN:" + user.getName() + "\nTEL;WORK:"
						+ convertVal(user.getWorkPhone()) + "\nTEL;CELL:"
						+ convertVal(user.getMobile()) + "\nTEL;HOME:"
						+ convertVal(user.getHomePhone()) + "\nORG:"
						+ convertVal(user.getWorkCompany()) + "\nNOTE:"
						+ org.getOrgName() + "网格员\nEND:VCARD"
						+ "\n[com.tianque.ecommunity.userid:" + user.getId()
						+ "]";// 名片类型
				handler.encoderQRCode(content, qrcodePath);
				qrcodePath = GridProperties.QRCODE_PATH + "/"
						+ user.getUserName() + ".png";// 读取的地址
				qrcodeDomain = new QrcodeDomain(qrcodePath, user.getUserName());
				qrcodeList.add(qrcodeDomain);
			} else if (HOUSE_QRCODETYPE.equals(qrcodeType)) {
				houseInfo = houseInfoService.getHouseInfoById(id);
				qrcodePath = createStoreFile(
						String.valueOf(houseInfo.getAddress()) + ".png")
						.getPath();// 存入项目的二维码地址
				content = "" + houseInfo.getAddress() + ""
						+ "\n[com.tianque.ecommunity.houseid:"
						+ houseInfo.getId() + "]";// 普通类型
				handler.encoderQRCode(content, qrcodePath);
				qrcodePath = GridProperties.QRCODE_PATH + "/"
						+ houseInfo.getAddress() + ".png";// 读取的地址
				qrcodeDomain = new QrcodeDomain(qrcodePath,
						houseInfo.getAddress());
				qrcodeList.add(qrcodeDomain);
			} else if (MOBILE_QRCODETYPE.equals(qrcodeType)) {
				mobileUpdate = mobileUpdateService.getMobileUpdateById(id);
				qrcodePath = createStoreFile(
						String.valueOf(mobileUpdate.getUrl()) + ".png")
						.getPath();// 存入项目的二维码地址
				// content = "{URLTO:" + mobileUpdate.getUrl() + "}";
				String ip = propertyDictService
						.findPropertyDictByDomainName(PropertyTypes.MOBILE_IP)
						.get(0).getDisplayName();// 本地或者线上的HTTP的地址
				String url = mobileUpdate.getUrl().replaceAll("\\\\", "/");
				content = "" + ip + url + "";
				handler.encoderQRCode(content, qrcodePath);
				qrcodePath = GridProperties.QRCODE_PATH + "/"
						+ mobileUpdate.getUrl() + ".png";// 读取的地址
				qrcodeDomain = new QrcodeDomain(qrcodePath,
						mobileUpdate.getDepartmentNo());
				qrcodeList.add(qrcodeDomain);
			}

		}

		if ((System.currentTimeMillis() - startTime) > 3000) {
			logger.error("处理时间大于3000毫秒   {} ", qrcodePath);
		}
		return SUCCESS;

	}

	/**
	 * ID加密，房屋生成二维码
	 * 
	 * @return
	 * @throws Exception
	 */
	@EncryptAnnotation
	@Action(value = "generateQrcodeByEncryptId", results = {
			@Result(name = "success", location = "/sysadmin/userManage/qrcodeList.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String generateQrcodeByEncryptId() throws Exception {
		long startTime = System.currentTimeMillis();
		if (ids == null || "".equals(ids.trim()) || qrcodeType == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		Long[] idsLong = splitArray();
		if (idsLong.length == 0) {
			this.errorMessage = "请选择要操作的记录!";
			return ERROR;
		}

		TwoDimensionCode handler = new TwoDimensionCode();
		String content = null;
		for (Long id : idsLong) {
			if (HOUSE_QRCODETYPE.equals(qrcodeType)) {
				houseInfo = houseInfoService.getHouseInfoById(id);
				qrcodePath = createStoreFile(
						String.valueOf(houseInfo.getId()) + ".png").getPath();// 存入项目的二维码地址
				content = "" + houseInfo.getId() + ""
						+ "\n[com.tianque.ecommunity.houseid:"
						+ houseInfo.getId() + "]";// 普通类型
				handler.encoderQRCode(content, qrcodePath);
				qrcodePath = GridProperties.QRCODE_PATH + "/"
						+ houseInfo.getId() + ".png";// 读取的地址
				qrcodeDomain = new QrcodeDomain(qrcodePath, houseInfo.getId()
						+ ".png");
				qrcodeList.add(qrcodeDomain);
			}

		}

		if ((System.currentTimeMillis() - startTime) > 3000) {
			logger.error("处理时间大于3000毫秒   {} ", qrcodePath);
		}
		return SUCCESS;

	}

	private String convertVal(String val) {
		if (val == null) {
			return " ";
		}
		return val;
	}

	/**
	 * 创建存放目录
	 * 
	 * @param param
	 * @return
	 * @throws Exception
	 */
	private File createStoreFile(String param) throws Exception {
		File storedFile = new File(FileUtil.getWebRoot() + File.separator
				+ GridProperties.QRCODE_PATH + File.separator + param);
		if (!storedFile.getParentFile().isDirectory()) {
			storedFile.getParentFile().mkdirs();
		}
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	private Long[] splitArray() {
		String[] idsStr = ids.split(",");
		Long[] idsLong = new Long[idsStr.length];
		String id = null;
		for (int i = 0; i < idsStr.length; i++) {
			id = idsStr[i];
			if (id != null && !"".equals(id.trim())) {
				idsLong[i] = Long.parseLong(idsStr[i]);
			}
		}
		return idsLong;
	}

	public String getQrcodePath() {
		return qrcodePath;
	}

	public void setQrcodePath(String qrcodePath) {
		this.qrcodePath = qrcodePath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public QrcodeDomain getQrcodeDomain() {
		return qrcodeDomain;
	}

	public void setQrcodeDomain(QrcodeDomain qrcodeDomain) {
		this.qrcodeDomain = qrcodeDomain;
	}

	public List<QrcodeDomain> getQrcodeList() {
		return qrcodeList;
	}

	public void setQrcodeList(List<QrcodeDomain> qrcodeList) {
		this.qrcodeList = qrcodeList;
	}

	public String getQrcodeType() {
		return qrcodeType;
	}

	public void setQrcodeType(String qrcodeType) {
		this.qrcodeType = qrcodeType;
	}

	public HouseInfo getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(HouseInfo houseInfo) {
		this.houseInfo = houseInfo;
	}

	public MobileUpdate getMobileUpdate() {
		return mobileUpdate;
	}

	public void setMobileUpdate(MobileUpdate mobileUpdate) {
		this.mobileUpdate = mobileUpdate;
	}

}
