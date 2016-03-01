package com.tianque.partyBuilding.doubleRegActivities.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.partyBuilding.doubleRegActivities.constant.YesOrNoType;
import com.tianque.partyBuilding.doubleRegActivities.dao.MembersEnrollDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.MembersEnroll;
import com.tianque.partyBuilding.doubleRegActivities.service.MemberHasVolunteerJobsService;
import com.tianque.partyBuilding.doubleRegActivities.service.MembersEnrollService;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.service.MemberService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;

@Transactional
@Service("membersEnrollService")
public class MembersEnrollServiceImpl implements MembersEnrollService {
	@Autowired
	private MembersEnrollDao membersEnrollDao;
	@Autowired
	private MemberService memberService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private MemberHasVolunteerJobsService memberHasVolunteerJobsService;

	@Override
	public MembersEnroll add(MembersEnroll membersEnroll) {
		if (membersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		membersEnroll = membersEnrollDao.addMembersEnroll(membersEnroll);
		return membersEnroll;
	}

	@Override
	public MembersEnroll editAddMember(MembersEnroll membersEnroll) {
		if (membersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(membersEnroll.getOrganization().getId());

		MembersEnroll enroll = membersEnrollDao
				.getMembersEnrollByIdCardNo(membersEnroll.getMember()
						.getIdCardNo());// 查双报到信息

		if (enroll == null && membersEnroll.getMember().getId() == null) {
			// 党员和双报信息到都不存在
			membersEnroll = editAddMember(null, membersEnroll, organization);
		} else if (enroll == null && membersEnroll.getMember().getId() != null) {
			// 修改党员基本信息
			Member member = memberService.findMemberById(membersEnroll
					.getMember().getId());
			// 存在党员信息，不存在双报到信息
			membersEnroll = editAddMember(member, membersEnroll, organization);
		} else {
			// 存在党员信息和双报到信息
			membersEnroll.setIsEnroll(YesOrNoType.YES_VALUE);// 状态直接更改为报到成功
			editUpdateMember(membersEnroll);
		}
		return membersEnroll;
	}

	private MembersEnroll editAddMember(Member member,
			MembersEnroll membersEnroll, Organization organization) {
		if (member != null) {
			member.setIdCardNo(membersEnroll.getMember().getIdCardNo());
			member.setGender(membersEnroll.getMember().getGender());
			member.setName(membersEnroll.getMember().getName());
			member.setTelephone(membersEnroll.getMember().getTelephone());
			member.setBelongOrg(membersEnroll.getMember().getBelongOrg());
			member.setNativePlaceAddress(membersEnroll.getMember()
					.getNativePlaceAddress());
			member.setImgUrl(membersEnroll.getMember().getImgUrl());
			member.setReportOrganization(organization);
			member = memberService.update(member);
		} else {
			member = new Member();
			member.setIdCardNo(membersEnroll.getMember().getIdCardNo());
			member.setGender(membersEnroll.getMember().getGender());
			member.setName(membersEnroll.getMember().getName());
			member.setTelephone(membersEnroll.getMember().getTelephone());
			member.setBelongOrg(membersEnroll.getMember().getBelongOrg());
			member.setNativePlaceAddress(membersEnroll.getMember()
					.getNativePlaceAddress());
			member.setImgUrl(membersEnroll.getMember().getImgUrl());
			member.setReportOrganization(organization);
			member = memberService.add(member);
		}

		// 添加报到信息
		membersEnroll.setOrganization(organization);
		membersEnroll.setOrgInternalCode(organization.getOrgInternalCode());
		membersEnroll.setMember(member);
		membersEnroll = add(membersEnroll);
		return membersEnroll;
	}

	@Override
	public MembersEnroll update(MembersEnroll membersEnroll) {
		if (membersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		membersEnroll = membersEnrollDao.updateMembersEnroll(membersEnroll);
		return membersEnroll;
	}

	@Override
	public MembersEnroll editUpdateMember(MembersEnroll membersEnroll) {
		if (membersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		// 修改党员基本信息
		Member member = memberService.findMemberById(membersEnroll.getMember()
				.getId());
		member.setGender(membersEnroll.getMember().getGender());
		member.setName(membersEnroll.getMember().getName());
		member.setTelephone(membersEnroll.getMember().getTelephone());
		member.setBelongOrg(membersEnroll.getMember().getBelongOrg());
		member.setNativePlaceAddress(membersEnroll.getMember()
				.getNativePlaceAddress());
		member.setImgUrl(membersEnroll.getMember().getImgUrl());
		memberService.update(member);

		// 修改报到信息
		membersEnroll = update(membersEnroll);

		if (membersEnroll != null) {
			deleteMemberHasVolunteerJobsById(membersEnroll);
		}
		return membersEnroll;
	}

	// 删除关联志愿者岗位信息
	private void deleteMemberHasVolunteerJobsById(MembersEnroll membersEnroll) {
		if (membersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		memberHasVolunteerJobsService
				.deleteMemberHasVolunteerJobsById(membersEnroll.getId());// 先删除
	}

	@Override
	public MembersEnroll getMembersEnroll(MembersEnroll membersEnroll) {
		if (membersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		membersEnroll = membersEnrollDao.getMembersEnroll(membersEnroll);
		Organization org = organizationDubboService.getSimpleOrgById(membersEnroll.getOrganization().getId());
		membersEnroll.setOrganization(org);
		return membersEnroll;
	}

	@Override
	public MembersEnroll getMembersEnrollByIdCardNo(String idCardNo) {
		if (idCardNo == null) {
			throw new BusinessValidationException("请输入正确的身份证号");
		}
		return membersEnrollDao.getMembersEnrollByIdCardNo(idCardNo);
	}

	@Override
	public PageInfo<MembersEnroll> findMembersEnrollForPage(
			MembersEnroll MembersEnroll, Pager pager) {
		if (MembersEnroll == null) {
			throw new BusinessValidationException("对象为空");
		}
		PageInfo<MembersEnroll> pageInfo = membersEnrollDao.findMembersEnrollForPage(MembersEnroll, pager);
		appendOrgNameInfo(pageInfo.getResult());
		return pageInfo;
	}
	
	private  List<MembersEnroll> appendOrgNameInfo(List<MembersEnroll> membersEnrolls){
		if(membersEnrolls==null || membersEnrolls.size()==0){
			return membersEnrolls;
		}
		List<Organization> orgList = new ArrayList<Organization>();
		for (int i = 0; i < membersEnrolls.size(); i++) {
			orgList.add(membersEnrolls.get(i).getOrganization());
		}
		OrganizationVo orgVo = new OrganizationVo();
		orgVo.setOrgIdsList(ParametersConvertUtil.convertToListString(orgList));
		List<Organization> organizations = organizationDubboService.findNameAndRemarkBySearchVo(orgVo);
		for (int i = 0; i < membersEnrolls.size(); i++) {
			for (int j = 0; j < organizations.size(); j++) {
				if(organizations.get(j).getId().equals(membersEnrolls.get(i).getOrganization().getId())){
					membersEnrolls.get(i).setOrganization(organizations.get(j));
				}
			}
		}
		return membersEnrolls;
	}

	@Override
	public boolean deleteMembersEnroll(List<Long> ids, String regActivitiesType) {
		for (int i = 0; i < ids.size(); i++) {
			Long id = ids.get(i);
			MembersEnroll enroll = membersEnrollDao.getMembersEnrollById(id);
			if (enroll != null) {
				Member member = memberService.findMemberById(enroll.getMember()
						.getId());
				member.setReportOrganization(null);
				memberService.update(member);
				deleteMemberHasVolunteerJobsById(enroll);
			}
		}
		boolean bool = false;
		if (ids != null) {
			bool = membersEnrollDao.deleteMembersEnroll(ids, regActivitiesType);
		}
		return bool;
	}

	@Override
	public MembersEnroll getMembersEnrollById(Long id) {
		MembersEnroll membersEnroll = null;
		if (id != null) {
			membersEnroll = membersEnrollDao.getMembersEnrollById(id);
		}
		return membersEnroll;
	}

	@Override
	public MembersEnroll getMembersEnrollByMemberID(Long memberId,
			String regActivitiesType) {
		MembersEnroll membersEnroll = null;
		if (memberId != null) {
			membersEnroll = membersEnrollDao.getMembersEnrollByMemberID(
					memberId, regActivitiesType);
		}
		return membersEnroll;
	}

	@Override
	public List<MembersEnroll> getMembersEnrollByMemberIDs(
			List<Long> memberIds, String regActivitiesType) {
		List<MembersEnroll> membersEnrollList = null;
		if (memberIds != null) {
			membersEnrollList = membersEnrollDao.getMembersEnrollByMemberIDs(
					memberIds, regActivitiesType);
		}

		return membersEnrollList;
	}

	@Override
	public MembersEnroll logoutMemberEnroll(Long id, Long logoutStatus,
			Long isEnroll) {
		if (id == null) {
			throw new BusinessValidationException("没有选中要注销的报到信息");
		}
		if (logoutStatus == null) {
			throw new BusinessValidationException("没有选中要注销的报到信息");
		}
		membersEnrollDao.logoutMemberEnroll(id, logoutStatus, isEnroll);
		return null;
	}
}
