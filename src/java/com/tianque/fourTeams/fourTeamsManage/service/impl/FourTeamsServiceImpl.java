package com.tianque.fourTeams.fourTeamsManage.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.OrganizationVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.fourTeams.fourTeamsManage.dao.FourTeamsDao;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeamMembers;
import com.tianque.fourTeams.fourTeamsManage.domain.FourTeams;
import com.tianque.fourTeams.fourTeamsManage.service.FourTeamsService;
import com.tianque.fourTeams.fourTeamsManage.util.FourteamsUitl;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.util.ParametersConvertUtil;

@Service("fourTeamsService")
@Transactional
public class FourTeamsServiceImpl extends AbstractBaseService implements
		FourTeamsService {

	public final static Logger logger = LoggerFactory
			.getLogger(FourTeamsServiceImpl.class);

	@Autowired
	private FourTeamsDao fourTeamsDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("fourTeamsValidatorImpl")
	@Autowired
	private DomainValidator<FourTeams> domainValidator;

	@Qualifier("fourTeamMemberValidatorImpl")
	@Autowired
	private DomainValidator<FourTeamMembers> fourTeamsMemberValidator;

	@Override
	public PageInfo<FourTeams> findFourTeams(FourTeams fourTeams, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			PageInfo<FourTeams> pageInfo = fourTeamsDao.findFourTeams(
					fourTeams, page, rows, sidx, sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询四支队伍列表信息报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findSearchFourTeams(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			PageInfo<FourTeams> pageInfo = fourTeamsDao.findSearchFourTeams(
					fourTeams, page, rows, sidx, sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("四支队伍高级查询报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findSearchTeamName(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			PageInfo<FourTeams> pageInfo = fourTeamsDao.findSearchTeamName(
					fourTeams, page, rows, sidx, sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("通过名称查询队伍信息报错", e);
		}
	}

	@Override
	public PageInfo<FourTeamMembers> searchTeamMemberName(Long id, Long orgId,
			String names, Integer page, Integer rows, String sidx, String sord) {
		try {
			PageInfo<FourTeamMembers> pageInfo = fourTeamsDao
					.searchTeamMemberName(id, orgId, names, page, rows, sidx,
							sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("通过名称查询信息报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findSearchFourTeamMembers(FourTeams fourTeams,
			String screeningLevel, Integer page, Integer rows, String sidx,
			String sord) {
		try {
			if (fourTeams == null || fourTeams.getOrganization() == null
					|| fourTeams.getOrganization().getId() == null) {
				throw new BusinessValidationException("队伍层级参数传入报错");
			}
			Organization org = organizationDubboService
					.getFullOrgById(fourTeams.getOrganization().getId());
			if (org == null) {
				throw new BusinessValidationException("组织机构层级信息不存在");
			}
			fourTeams.setOrganization(org);
			fourTeams.setOrgIdsList(findOrgIdsList(org.getId()));
			PageInfo<FourTeams> pageInfo = fourTeamsDao
					.findSearchFourTeamMembers(fourTeams, screeningLevel, page,
							rows, sidx, sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询四支队伍成员信息报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findSubFourTeams(FourTeams fourTeams,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			if (fourTeams != null && fourTeams.getOrganization() != null) {
				Organization org = organizationDubboService
						.getSimpleOrgById(fourTeams.getOrganization().getId());
				if (org != null) {
					OrganizationVo orgVo = new OrganizationVo();
					orgVo.setOrgInternalCode(org.getOrgInternalCode());
					List<Long> orgIdList = organizationDubboService
							.findOrgIdsBySearchVo(orgVo);
					fourTeams.setOrgIdsList(ParametersConvertUtil
							.convertToListString(orgIdList));
				}
			}
			PageInfo<FourTeams> pageInfo = fourTeamsDao.findSubFourTeams(
					fourTeams, page, rows, sidx, sord);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询四支队伍数量信息报错", e);
		}
	}

	/**
	 * 数据验证
	 */
	private void fourTeamsValidator(FourTeams fourTeams) {
		ValidateResult baseDataValidator = domainValidator.validate(fourTeams);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public FourTeams addTeam(FourTeams fourTeams) {
		try {
			if (fourTeams == null || fourTeams.getOrgCode() == null) {
				throw new BusinessValidationException("四支队伍新增信息报错");
			}
			setContacterChinesePinyin(fourTeams);
			fourTeamsValidator(fourTeams);
			Organization org = organizationDubboService
					.getOrganizationByOrganizationCode(fourTeams.getOrgCode());
			fourTeams.setOrganization(org);
			fourTeams.setMemberNumber(FourteamsUitl.FOURTEAM_DEFAUTL_NUMS);
			Integer indexId = fourTeamsDao.indexIdNumber();
			fourTeams.setIndexId(indexId);
			fourTeams = fourTeamsDao.addTeam(fourTeams);
			if (fourTeams != null && fourTeams.getId() != null) {
				updateFourTeamsSubTeamNumber(fourTeams.getParentTeamId());
			}
			return fourTeams;
		} catch (Exception e) {
			throw new ServiceValidationException("四支队伍新增信息报错", e);
		}
	}

	private void setContacterChinesePinyin(FourTeams fourTeams) {
		if (fourTeams != null && fourTeams.getNames() == null) {
			throw new BusinessValidationException("队伍基础信息未获得");
		}
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(fourTeams.getNames());
		fourTeams.setFullPinyin(pinyin.get("fullPinyin"));
		fourTeams.setSimplePinyin(pinyin.get("simplePinyin"));
	}

	private void setContacterChinesePinyinMember(FourTeamMembers fourTeamMembers) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(fourTeamMembers.getNames());
		fourTeamMembers.setFullPinyin(pinyin.get("fullPinyin"));
		fourTeamMembers.setSimplePinyin(pinyin.get("simplePinyin"));
	}

	@Override
	public Integer indexIdNumber() {
		return fourTeamsDao.indexIdNumber();
	}

	@Override
	public void updateFourTeamsSubTeamNumber(Long id) {
		try {
			fourTeamsDao.updateFourTeamsSubTeamNumber(id);
		} catch (Exception e) {
			throw new ServiceValidationException("四支队伍修改信息报错", e);
		}
	}

	@Override
	public FourTeams getFourTeams(Long id) {
		try {
			FourTeams fourTeams = fourTeamsDao.getFourTeams(id);
			return fourTeams;
		} catch (Exception e) {
			throw new ServiceValidationException("查询四支队伍信息报错", e);
		}
	}

	@Override
	public boolean deleteFourTeams(String[] ids, Long parentId) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("请选择一条数据进行删除");
		}
		if (parentId == null) {
			throw new BusinessValidationException("删除四只队伍信息传入参数未获得");
		}

		if (!hasTeamMember(ids)) {
			throw new BusinessValidationException("您选择的队伍下有成员存在，无法删除");
		}
		fourTeamsDao.deleteFourTeams(ids);
		fourTeamsDao.updateTeamByDeleteFourTeams(parentId, ids.length);
		return true;
	}

	private boolean hasTeamMember(String[] ids) {
		try {
			for (String id : ids) {
				Integer num = fourTeamsDao.findTeamHasMember(id);
				if (num != null && num != 0) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			throw new ServiceValidationException("判断队伍下是否有成员报错", e);
		}
	}

	@Override
	public void updateTeamByDeleteFourTeams(Long id, Integer number) {
		try {
			fourTeamsDao.updateTeamByDeleteFourTeams(id, number);
		} catch (Exception e) {
			throw new ServiceValidationException("修改队伍成员数量信息报错", e);
		}
	}

	@Override
	public FourTeams findFourTeamEdit(Long id) {
		FourTeams fourTeams = fourTeamsDao.getFourTeam(id);

		return fourTeams;
	}

	@Override
	public FourTeams findFourTeamView(Long id) {
		try {
			FourTeams fourTeams = fourTeamsDao.findFourTeamView(id);
			return fourTeams;
		} catch (Exception e) {
			throw new ServiceValidationException("查询基础队伍信息报错", e);
		}
	}

	@Override
	public FourTeams findFourTeamAdd(Long id) {
		try {
			FourTeams fourTeams = fourTeamsDao.getFourTeams(id);
			if (fourTeams == null || fourTeams.getOrgCode() == null) {
				throw new BusinessValidationException("四支队伍信息不存在");
			}
			Organization org = organizationDubboService
					.getOrganizationByOrganizationCode(fourTeams.getOrgCode());
			if (org == null) {
				throw new BusinessValidationException("四支队伍所在层级不存在报错");
			}
			fourTeams.setOrganization(org);
			return fourTeams;
		} catch (Exception e) {
			throw new ServiceValidationException("四支队伍新增报错", e);
		}
	}

	@Override
	public FourTeams editTeam(FourTeams fourTeams) {
		try {
			setContacterChinesePinyin(fourTeams);
			fourTeamsValidator(fourTeams);
			fourTeams = fourTeamsDao.editTeam(fourTeams);
			return fourTeams;
		} catch (Exception e) {
			throw new ServiceValidationException("四支队伍修改报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findserviceFourTeams(FourTeams fourTeams,
			Long organizationId, String screeningLevel, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org != null && org.getOrgInternalCode() != null
					&& fourTeams != null) {
				fourTeams.setOrgCode(org.getOrgInternalCode());
				fourTeams.setOrganization(org);
				fourTeams.setOrgIdsList(findOrgIdsList(org.getId()));
			}
			PageInfo<FourTeams> pageInfo = fourTeamsDao.findserviceFourTeams(
					fourTeams, screeningLevel, page, rows, sidx, sord);

			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询服务队伍信息报错", e);
		}
	}

	@Override
	public PageInfo<FourTeamMembers> findMemberFourTeams(Long id, Integer page,
			Integer rows, String sidx, String sord) {
		try {
			PageInfo<FourTeamMembers> pageInfo = fourTeamsDao
					.findMemberFourTeams(id, page, rows, sidx, sord);

			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询四支队伍列表信息报错", e);
		}
	}

	@Override
	public void updateFourTeamsSubTeamMemberNumber(Long id) {
		try {
			fourTeamsDao.updateFourTeamsSubTeamMemberNumber(id);
		} catch (Exception e) {
			throw new ServiceValidationException("修改队伍所有成员数量报错", e);
		}
	}

	private void fourTeamsMemberValidator(FourTeamMembers fourTeamMembers) {
		ValidateResult baseDataValidator = fourTeamsMemberValidator
				.validate(fourTeamMembers);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public FourTeamMembers addTeamMember(FourTeamMembers fourTeamMembers) {
		try {
			fourTeamsMemberValidator(fourTeamMembers);
			setContacterChinesePinyinMember(fourTeamMembers);
			fourTeamMembers = fourTeamsDao.addTeamMember(fourTeamMembers);
			if (fourTeamMembers != null && fourTeamMembers.getId() != null) {
				updateFourTeamsSubTeamMemberNumber(fourTeamMembers.getTeamId());
			}
			return fourTeamMembers;
		} catch (Exception e) {
			throw new ServiceValidationException("新增队伍成员信息报错", e);
		}
	}

	@Override
	public boolean deleteFourTeamMembers(String[] ids, Long id) {
		try {
			if (ids == null || id == null) {
				throw new BusinessValidationException("删除队伍成员信息参数未获得");
			}
			fourTeamsDao.deleteFourTeamMembers(ids);
			fourTeamsDao.updateTeamByDeleteFourTeamMembers(id, ids.length);
			return true;
		} catch (Exception e) {
			throw new ServiceValidationException("删除队伍 成员信息报错", e);
		}
	}

	@Override
	public FourTeamMembers findFourTeamMemberEdit(Long id) {
		try {
			FourTeamMembers fourTeamMembers = fourTeamsDao
					.findFourTeamMemberEdit(id);
			return fourTeamMembers;
		} catch (Exception e) {
			throw new ServiceValidationException("查询队伍成员信息报错", e);
		}
	}

	@Override
	public FourTeamMembers editTeamMember(FourTeamMembers fourTeamMembers) {
		try {
			fourTeamsMemberValidator(fourTeamMembers);
			setContacterChinesePinyinMember(fourTeamMembers);
			fourTeamMembers = fourTeamsDao.editTeamMember(fourTeamMembers);
			return fourTeamMembers;
		} catch (Exception e) {
			throw new ServiceValidationException("修改队伍成员信息报错", e);
		}
	}

	@Override
	public FourTeams getFourTeamMembersByType(String type) {
		try {
			return fourTeamsDao.getFourTeamMembersByType(type);
		} catch (Exception e) {
			throw new ServiceValidationException("通过成员类型查询成员信息报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findTeamsByConditionForIssue(
			FourTeams fourTeams, Integer page, Integer rows, String sidx,
			String sord) {
		if (fourTeams == null || fourTeams.getOrganization() == null
				|| fourTeams.getOrganization().getId() == null) {
			throw new BusinessValidationException("查询队伍信息时，所在层级信息未获得");
		}
		PageInfo<FourTeams> pageInfo = null;
		try {
			Organization org = organizationDubboService
					.getFullOrgById(fourTeams.getOrganization().getId());
			// 如果是职能部门，则使用父节点的组织机构
			if (org.getOrgType()
					.getDisplayName()
					.equals(OrganizationType.getInternalProperties()
							.get(OrganizationType.FUNCTIONAL_ORG)
							.getDisplayName())) {
				org = organizationDubboService.getSimpleOrgById(org
						.getParentOrg().getId());
			}
			fourTeams.setOrganization(org);
			pageInfo = fourTeamsDao.findTeamsByConditionForIssue(fourTeams,
					page, rows, sidx, sord);
		} catch (Exception e) {
			throw new ServiceValidationException("查询当前层级队伍信息报错", e);
		}
		return pageInfo;
	}

	@Override
	public Boolean repeatTeamName(Long teamId, String names, Long orgId) {
		try {
			FourTeams fourteams = fourTeamsDao.repeatTeamName(names, orgId);
			if (fourteams != null && !fourteams.getId().equals(teamId)) {
				return true;// 存在相同的队伍名称
			}
			return false;
		} catch (Exception e) {
			throw new ServiceValidationException("判断队伍名称同层级是否重名报错", e);
		}
	}

	@Override
	public PageInfo<FourTeams> findScreeningFourteamsForPageInfo(
			String fourteamsType, Long organizationId, String screeningLevel,
			Integer page, Integer rows, String sidx, String sord) {
		try {
			if (organizationId == null) {
				throw new BusinessValidationException("筛选队伍信息层级参数未获得");
			}
			Organization org = organizationDubboService
					.getSimpleOrgById(organizationId);
			if (org == null) {
				throw new BusinessValidationException("筛选队伍时，层级信息查询报错");
			}
			FourTeams fourTeams = new FourTeams();
			fourTeams.setOrgIdsList(findOrgIdsList(org.getId()));
			fourTeams.setOrganization(org);
			fourTeams.setTeamType(fourteamsType);

			PageInfo<FourTeams> pageInfo = fourTeamsDao
					.findScreeningFourteamsForPageInfo(fourTeams,
							screeningLevel, page, rows, sidx, sord);
			if (pageInfo != null && pageInfo.getResult() != null
					&& pageInfo.getResult().size() != 0) {
				for (FourTeams team : pageInfo.getResult()) {
					if (team.getOrganization() != null
							&& team.getOrganization().getId() != null) {
						org = organizationDubboService.getSimpleOrgById(team
								.getOrganization().getId());
						team.setOrganization(org);
					}
				}
			}
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("四支队伍筛选报错", e);
		}
	}

	/********************** 组织机构迁移合并方法 ***************************/
	@Override
	public Integer updateFourteamsOrganizationByIds(Long orgId, String orgCode,
			List<String> ids) {
		if (orgId == null || (!StringUtil.isStringAvaliable(orgCode))) {
			throw new BusinessValidationException("根据主键修改队伍所在的组织信息报错");
		}
		if (ids == null || ids.size() == 0) {
			throw new BusinessValidationException("所需要修改的队伍信息未得到");
		}
		try {
			return fourTeamsDao.updateFourteamsOrganizationByIds(orgId,
					orgCode, ids);
		} catch (Exception e) {
			throw new ServiceValidationException("根据主键修改队伍组织机构信息报错", e);
		}
	}

	@Override
	public void updateFourTeamsNameByIds(String fourTeamsName, Long id) {
		if (id == null) {
			throw new BusinessValidationException("所需要修改的队伍信息未得到");
		}
		try {
			fourTeamsDao.updateFourTeamsNameByIds(fourTeamsName, id);
		} catch (Exception e) {
			throw new ServiceValidationException("根据主键修改队伍组织机构信息报错", e);
		}
	}

	@Override
	public int deleteFourteams(String[] ids) {
		return fourTeamsDao.deleteFourTeams(ids);
	}

	@Override
	public List<FourTeams> findteamsByOrgIdAndOrgCode(Long orgId, String orgCode) {
		if (orgId == null || orgCode == null || orgCode == "") {
			throw new BusinessValidationException("根据组织信息查询四支队伍数据报错");
		}
		return fourTeamsDao.findteamsByOrgIdAndOrgCode(orgId, orgCode);
	}

	private List<String> findOrgIdsList(Long orgId) {
		OrganizationVo organizationVo = new OrganizationVo();
		organizationVo.setParentOrgId(orgId);
		List<Long> orgIdList = organizationDubboService
				.findOrgIdsBySearchVo(organizationVo);
		return ParametersConvertUtil.convertToListString(orgIdList);
	}
}
