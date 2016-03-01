package com.tianque.partyBuilding.members.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.util.CollectionUtil;
import com.tianque.core.util.PeopleHelper;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.domain.Organization;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.partyBuilding.doubleRegActivities.constant.DoubleRegActivitiesType;
import com.tianque.partyBuilding.doubleRegActivities.constant.YesOrNoType;
import com.tianque.partyBuilding.doubleRegActivities.domain.MembersEnroll;
import com.tianque.partyBuilding.doubleRegActivities.service.MembersEnrollService;
import com.tianque.partyBuilding.members.constant.MemberType;
import com.tianque.partyBuilding.members.dao.MemberDao;
import com.tianque.partyBuilding.members.domain.Member;
import com.tianque.partyBuilding.members.domain.MemberAssociatePartyOrg;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.IdCardUtil;

@Transactional
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberAssociatePartyOrgService associatePartyOrgService;
	@Autowired
	private MembersEnrollService membersEnrollService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	@Qualifier("memberValidatorImpl")
	private DomainValidator<Member> memberDomainValidator;
	@Autowired
	@Qualifier("peopleHelper")
	PeopleHelper peopleHelper;

	// 添加党员的基本信息
	@Override
	public Member add(Member member) {
		if (member == null) {
			throw new BusinessValidationException("成员为空，新增成员失败");
		}
		member.setGender(peopleHelper.autoFillGender(PropertyTypes.GENDER,
				member.getIdCardNo()));
		member = memberDao.addMember(member);
		return member;
	}

	// 修改党员的基本信息
	@Override
	public Member update(Member member) {
		if (member == null) {
			throw new BusinessValidationException("成员为空，新增成员失败");
		}
		member.setGender(peopleHelper.autoFillGender(PropertyTypes.GENDER,
				member.getIdCardNo()));
		member = memberDao.updateMember(member);
		return member;
	}

	@Override
	public Member findByIdCardNo(String idCardNo) {
		if (idCardNo == null) {
			throw new BusinessValidationException("身份证不能为空");
		}
		return memberDao.getMemberByIdCardNo(idCardNo);
	}

	private Member addMember(Member member) {
		if (member == null) {
			throw new BusinessValidationException("成员为空，新增成员失败");
		}
		ValidateResult result = memberDomainValidator.validate(member);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		MemberAssociatePartyOrg associatePartyOrg = autoBuildMemberAssociatePartyOrg(member);
		fillDefaultBirthday(member);
		member.setGender(peopleHelper.autoFillGender(PropertyTypes.GENDER,
				member.getIdCardNo()));
		member = memberDao.addMember(member);
		autoFillMember(member,
				addMemberAssociatePartyOrg(member, associatePartyOrg));
		return member;
	}

	private void fillDefaultBirthday(Member domain) {
		if (StringUtil.isStringAvaliable(domain.getIdCardNo())) {
			domain.setBirthday(IdCardUtil.parseBirthday(domain.getIdCardNo()));
		}
	}

	private MemberAssociatePartyOrg addMemberAssociatePartyOrg(Member member,
			MemberAssociatePartyOrg associatePartyOrg) {
		associatePartyOrg.setMember(member);
		return associatePartyOrgService
				.addMemberAssociatePartyOrg(associatePartyOrg);
	}

	private void autoFillMember(Member member,
			MemberAssociatePartyOrg associatePartyOrg) {
		member.setPartyOrg(associatePartyOrg.getPartyOrg());
		member.setPartyOrgType(associatePartyOrg.getPartyOrgType());
		member.setLogOut(associatePartyOrg.getLogOut());
		member.setAssociateId(associatePartyOrg.getId());
	}

	private MemberAssociatePartyOrg autoBuildMemberAssociatePartyOrg(
			Member member) {
		MemberAssociatePartyOrg associatePartyOrg = new MemberAssociatePartyOrg();
		Organization org = null;
		if (null == member.getOrgId()) {
			org = organizationDubboService.getSimpleOrgById(ThreadVariable
					.getOrganization().getId());
		} else {
			org = organizationDubboService.getSimpleOrgById(member.getOrgId());
		}
		associatePartyOrg.setPartyOrg(member.getPartyOrg());
		associatePartyOrg.setPartyOrgType(member.getPartyOrgType());
		associatePartyOrg.setOrganization(org);
		associatePartyOrg.setOrgInternalCode(org.getOrgInternalCode());
		associatePartyOrg.setLogOut(member.getLogOut());
		associatePartyOrg.setMember(member);
		return associatePartyOrg;
	}

	private Member updateMember(Member member) {
		if (member == null || member.getId() == null) {
			throw new BusinessValidationException("成员为空，修改成员失败");
		}
		ValidateResult result = memberDomainValidator.validate(member);
		if (result.hasError()) {
			throw new BusinessValidationException(result.getErrorMessages());
		}
		MemberAssociatePartyOrg associatePartyOrg = associatePartyOrgService
				.updateMemberAssociatePartyOrg(autoBuildMemberAssociatePartyOrg(member));
		fillDefaultBirthday(member);
		member.setGender(peopleHelper.autoFillGender(PropertyTypes.GENDER,
				member.getIdCardNo()));
		member = memberDao.updateMember(member);
		autoFillMember(member, associatePartyOrg);
		return member;
	}

	@Override
	public Member getMemberByPartyOrgTypeAndId(Member member) {
		if (member == null || member.getPartyOrgType() == null
				|| member.getId() == null) {
			throw new BusinessValidationException("获取成员失败");
		}
		MemberAssociatePartyOrg associatePartyOrg = associatePartyOrgService
				.getMemberAssociatePartyOrg(autoBuildMemberAssociatePartyOrg(member));
		member = memberDao.getMemberById(member.getId());
		autoFillMember(member, associatePartyOrg);
		return member;
	}

	@Override
	@SolrServerIndex(mode=OperateMode.DELETE,value=DeleteSqlMap.PARTYMEMBERS_KEY)
	public boolean deleteMemberByIds(Integer partyOrgType, List<Long> ids) {
		if (!CollectionUtil.isAvaliable(ids)) {
			throw new BusinessValidationException("删除成员失败!");
		}
		boolean result = findMemberIsEnroll(ids);
		if (result == false) {
			throw new BusinessValidationException("删除失败，请先删除党员双报到信息!");
		}
		return associatePartyOrgService
				.deleteMemberAssociatePartyOrgByMemberIds(partyOrgType, ids);
	}

	@Override
	public PageInfo<Member> findMembersForPage(Member member, Pager pager) {
		if (member.getPartyOrgType() == null) {
			throw new BusinessValidationException("查询成员失败");
		}
		return memberDao.findMembersForPage(member, pager);
	}

	@Override
	public Map<String, Object> exsistedIdCard(Member member) {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isExistMemberInPartyOrg = isExistMemberInPartyOrg(member);
		if (!isExistMemberInPartyOrg) {
			member = memberDao.getMemberByIdCardNo(member.getIdCardNo());
			Organization reportOrganization = getorganization(member);
			if (member != null && reportOrganization != null) {
				member.setReportOrganization(reportOrganization);
			}
		}
		map.put("member", member == null ? new Member() : member);
		map.put("isExsisted", isExistMemberInPartyOrg);
		return map;
	}

	private Organization getorganization(Member member) {
		Organization organization = null;
		if (member != null && member.getReportOrganization() != null) {
			organization = organizationDubboService.getFullOrgById(member
					.getReportOrganization().getId());
		}
		return organization;
	}

	@Override
	public Member editMember(Member member) {
		if (!StringUtil.isStringAvaliable(member.getIdCardNo())) {
			throw new BusinessValidationException("身份证不允许为空");
		}
		boolean isExistMember = isExistMember(member);// 存在为true
		boolean isExistMemberInPartyOrg = isExistMemberInPartyOrg(member);// 存在为true
		if (!isExistMember && !isExistMemberInPartyOrg) {
			member = addMember(member);
			if (member != null) {
				memberEnroll(member);
			}
		}
		if (isExistMember) {
			if (isExistMemberInPartyOrg) {
				member = updateMember(member);
				if (member != null) {
					memberEnroll(member);
				}
			} else {
				MemberAssociatePartyOrg associatePartyOrg = autoBuildMemberAssociatePartyOrg(member);
				fillDefaultBirthday(member);
				member.setGender(peopleHelper.autoFillGender(
						PropertyTypes.GENDER, member.getIdCardNo()));
				member = memberDao.updateMember(member);
				associatePartyOrg.setMember(member);
				associatePartyOrg = associatePartyOrgService
						.addMemberAssociatePartyOrg(associatePartyOrg);
				autoFillMember(member, associatePartyOrg);
			}
		}
		return member;
	}

	/**
	 * 判断该党员是否已经存在，存在为true，反之false
	 * 
	 * @param member
	 * @return
	 */
	private boolean isExistMember(Member member) {
		Member temp = memberDao.getMemberByIdCardNo(member.getIdCardNo());
		if (temp != null) {
			member.setId(temp.getId());
		}
		return temp != null;
	}

	@Override
	public boolean isExistMemberInPartyOrg(Member member) {
		boolean isExistMemberInPartyOrg = false;
		if (MemberType.INTERACTIVE_PARTY.contains(member.getPartyOrgType())) {
			// 如果三个互斥中都不存在，则将信息提取出来
			isExistMemberInPartyOrg = associatePartyOrgService
					.isExistMemberInPartyOrg(member, MemberType.INTERACTIVE);
		} else {
			isExistMemberInPartyOrg = associatePartyOrgService
					.isExistMemberInPartyOrg(member, !MemberType.INTERACTIVE);
		}
		return isExistMemberInPartyOrg;
	}

	@Override
	public boolean isExsistedPartyOrg(String partyOrg, Integer partyOrgType) {
		return memberDao.isExsistedPartyOrg(partyOrg, partyOrgType);
	}

	@Override
	public Member findMemberById(Long id) {
		return memberDao.getMemberById(id);
	}

	private MembersEnroll memberEnroll(Member member) {
		MembersEnroll membersEnroll = null;
		// 判断是否双报到
		if (member.getReportOrganization() != null) {
			MembersEnroll membersEnroll1 = membersEnrollService
					.getMembersEnrollByMemberID(member.getId(),
							DoubleRegActivitiesType.AUTHORITY_CLASS);
			findMembersEnrollByMemberID(member, membersEnroll1);
		}
		return membersEnroll;
	}

	private void findMembersEnrollByMemberID(Member member,
			MembersEnroll membersEnroll1) {
		if (membersEnroll1 == null) {
			Organization organization = organizationDubboService
					.getSimpleOrgById(member.getReportOrganization().getId());
			if (organization != null) {
				MembersEnroll membersEnroll = new MembersEnroll();
				membersEnroll.setOrganization(organization);
				membersEnroll.setOrgInternalCode(organization
						.getOrgInternalCode());
				membersEnroll.setIsEnroll(YesOrNoType.NO_VALUE);// 未报到
				membersEnroll.setLogoutStatus(YesOrNoType.YES_VALUE);// 未注销
				membersEnroll.setMember(member);
				membersEnroll
						.setRegActivitiesType(DoubleRegActivitiesType.AUTHORITY_CLASS);
				membersEnrollService.add(membersEnroll);
			}
		} else {
			Organization organization = organizationDubboService
					.getSimpleOrgById(member.getReportOrganization().getId());
			if (organization != null) {
				// 状态为转出
				membersEnroll1.setIsTurnOut(0L);
				membersEnrollService.update(membersEnroll1);

				// 重新插入一条数据
				membersEnroll1.setOrganization(organization);
				membersEnroll1.setIsEnroll(1L);
				membersEnroll1.setOrgInternalCode(organization
						.getOrgInternalCode());
				membersEnroll1.setIsTurnOut(null);
				membersEnroll1.setLogoutStatus(YesOrNoType.YES_VALUE);// 未注销
				membersEnroll1
						.setRegActivitiesType(DoubleRegActivitiesType.AUTHORITY_CLASS);
				membersEnrollService.add(membersEnroll1);
			}
		}
	}

	private boolean findMemberIsEnroll(List<Long> ids) {
		boolean result = true;
		if (ids != null) {
			List<MembersEnroll> membersEnrollList = membersEnrollService
					.getMembersEnrollByMemberIDs(ids, null);
			if (membersEnrollList != null && membersEnrollList.size() > 0) {
				result = false;
			}
		}
		return result;
	}
}
