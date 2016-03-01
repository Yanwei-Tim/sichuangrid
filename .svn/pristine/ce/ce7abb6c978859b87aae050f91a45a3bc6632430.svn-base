package com.tianque.baseInfo.primaryOrg.domain;

import java.io.Serializable;
import java.util.Date;

import com.tianque.core.util.BaseDomainIdEncryptUtil;
import com.tianque.core.vo.BaseVo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;

public class PrimaryOrgVo extends BaseVo implements Serializable {
	private Long id;
	/** 组织大类 **/
	private PropertyDict teamClazz;
	/** 组织小类 **/
	private PropertyDict teamType;
	/** 组织机构 **/
	private Organization org;
	/** 组织名称 */
	private String name;
	/** 组织名称 */
	private String detailName;
	/** 创建用户 */
	private String createUser;
	/** 修改用户 */
	private String updateUser;
	/** 创建日期 */
	private Date createDate;
	/** 修改日期 */
	private Date updateDate;
	/** 备注 **/
	private String remark;
	/** 仅显示本级、所有下辖、直属下辖 */
	private String displayLevel;
	/** 成员数 */
	private int memberNum;
	/** 记录数 */
	private int recordNum;
	/** 最小成员数 */
	private Long memberCountMin;
	/** 最大成员数 */
	private Long memberCountMax;
	/** 网格范围 **/
	private String orgScope;

	/** 数据来源方式 */
	private Integer isSynchronize;
	/** 排序字段 */
	private Integer seq;
	/** 确定是什么组织大类，主要用于群防群治显示社会志愿者同步的数据 */
	private Integer internalId;
	/** 确定是防群治 显示社会志愿者搜索，主要用于群防群治搜索显示社会志愿者同步的数据 */
	private Integer postulantduty;
	/** 党委部门（子类） */
	private PropertyDict departmentType;

	/** 是否是四级平台 */
	private Long isFourLevelPlatform;

	/** 业务指导部门 */
	private String guidanceDepartment;

	/** 主要功能 */
	private String mainFunction;
	
	/** 组织小类的字典项大类名称 */
	private String teamTypeDomainName;

	/** 新增加一个字段，用于判别是否是综治委、综治办、综治成员单位列表 */

	private String isCommissionOrganization;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PropertyDict getTeamClazz() {
		return teamClazz;
	}

	public void setTeamClazz(PropertyDict teamClazz) {
		this.teamClazz = teamClazz;
	}

	public PropertyDict getTeamType() {
		return teamType;
	}

	public void setTeamType(PropertyDict teamType) {
		this.teamType = teamType;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsSynchronize() {
		return isSynchronize;
	}

	public void setIsSynchronize(Integer isSynchronize) {
		this.isSynchronize = isSynchronize;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getDisplayLevel() {
		return displayLevel;
	}

	public void setDisplayLevel(String displayLevel) {
		this.displayLevel = displayLevel;
	}

	public int getMemberNum() {
		return memberNum;
	}

	public void setMemberNum(int memberNum) {
		this.memberNum = memberNum;
	}

	public int getRecordNum() {
		return recordNum;
	}

	public void setRecordNum(int recordNum) {
		this.recordNum = recordNum;
	}

	public Long getMemberCountMin() {
		return memberCountMin;
	}

	public void setMemberCountMin(Long memberCountMin) {
		this.memberCountMin = memberCountMin;
	}

	public Long getMemberCountMax() {
		return memberCountMax;
	}

	public void setMemberCountMax(Long memberCountMax) {
		this.memberCountMax = memberCountMax;
	}

	public String getOrgScope() {
		return orgScope;
	}

	public void setOrgScope(String orgScope) {
		this.orgScope = orgScope;
	}

	public Integer getInternalId() {
		return internalId;
	}

	public void setInternalId(Integer internalId) {
		this.internalId = internalId;
	}

	public Integer getPostulantduty() {
		return postulantduty;
	}

	public void setPostulantduty(Integer postulantduty) {
		this.postulantduty = postulantduty;
	}

	public String getEncryptId() {
		String orgCode = this.org.getOrgInternalCode();
		return BaseDomainIdEncryptUtil.encryptDomainId(getId(), orgCode, null);
	}

	public PropertyDict getDepartmentType() {
		return departmentType;
	}

	public void setDepartmentType(PropertyDict departmentType) {
		this.departmentType = departmentType;
	}

	public Long getIsFourLevelPlatform() {
		return isFourLevelPlatform;
	}

	public void setIsFourLevelPlatform(Long isFourLevelPlatform) {
		this.isFourLevelPlatform = isFourLevelPlatform;
	}

	public String getGuidanceDepartment() {
		return guidanceDepartment;
	}

	public void setGuidanceDepartment(String guidanceDepartment) {
		this.guidanceDepartment = guidanceDepartment;
	}

	public String getMainFunction() {
		return mainFunction;
	}

	public void setMainFunction(String mainFunction) {
		this.mainFunction = mainFunction;
	}

	public String getTeamTypeDomainName() {
		return teamTypeDomainName;
	}

	public void setTeamTypeDomainName(String teamTypeDomainName) {
		this.teamTypeDomainName = teamTypeDomainName;
	}

	public String getIsCommissionOrganization() {
		return isCommissionOrganization;
	}

	public void setIsCommissionOrganization(String isCommissionOrganization) {
		this.isCommissionOrganization = isCommissionOrganization;
	}

}