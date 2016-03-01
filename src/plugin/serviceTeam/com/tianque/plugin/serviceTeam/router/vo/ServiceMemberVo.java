package com.tianque.plugin.serviceTeam.router.vo;

import java.util.Date;
import java.util.List;

import com.tianque.core.vo.BaseVo;
import com.tianque.domain.PropertyDict;

/**
 * @ClassName: ServiceMemberVo
 * @Description: TODO()
 * @author N-147
 * @date 2013-6-30 下午5:59:43 业务角度可以看到的服务人员
 */
public class ServiceMemberVo extends BaseVo {
	private Long id;
	private Long teamId;
	private Long memberId;
	/** 姓名 */
	private String memberName;
	/** 性别 **/
	private PropertyDict gender;
	/** 身份证号 */
	private String idCardNo;
	/** 身份（与被服务人员关系） **/
	private String relation;
	/** 固定电话 **/
	private String homePhone;
	/** 手机号码 */
	private String mobile;
	/** 职务 */
	private PropertyDict position;
	/** 职责 **/
	private String duties;
	/** 职位 **/
	private String job;
	/** 是否在职 **/
	private Long onDuty;
	/** 离职/再重新担任原因 **/
	private String shiftDutyReason;
	/** 离职/再重新担任日期 **/
	private Date shiftDutyDate;
	/** 备注 **/
	private String remark;
	/** 所在团队名称 */
	private String teamName;
	/* 是否是团队成员 */
	private Long teamMember;
	/* 服务对象类型 */
	private String objectType;
	/* 服务对象id */
	private Long objectId;
	/* 服务对象类型 */
	private List<String> objectTypeList;

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public PropertyDict getGender() {
		return gender;
	}

	public void setGender(PropertyDict gender) {
		this.gender = gender;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public PropertyDict getPosition() {
		return position;
	}

	public void setPosition(PropertyDict position) {
		this.position = position;
	}

	public String getDuties() {
		return duties;
	}

	public void setDuties(String duties) {
		this.duties = duties;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Long getOnDuty() {
		return onDuty;
	}

	public void setOnDuty(Long onDuty) {
		this.onDuty = onDuty;
	}

	public String getShiftDutyReason() {
		return shiftDutyReason;
	}

	public void setShiftDutyReason(String shiftDutyReason) {
		this.shiftDutyReason = shiftDutyReason;
	}

	public Date getShiftDutyDate() {
		return shiftDutyDate;
	}

	public void setShiftDutyDate(Date shiftDutyDate) {
		this.shiftDutyDate = shiftDutyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(Long teamMember) {
		this.teamMember = teamMember;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public List<String> getObjectTypeList() {
		return objectTypeList;
	}

	public void setObjectTypeList(List<String> objectTypeList) {
		this.objectTypeList = objectTypeList;
	}

}
