package com.tianque.baseInfo.primaryOrg.primaryMembers.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.primaryOrg.constant.FourLevelPlatformOrgType;
import com.tianque.baseInfo.primaryOrg.domain.FourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrgVo;
import com.tianque.baseInfo.primaryOrg.domain.PrimaryOrganizations;
import com.tianque.baseInfo.primaryOrg.domain.vo.SearchFourLevelPlatformVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.constant.PrimaryMemberType;
import com.tianque.baseInfo.primaryOrg.primaryMembers.dao.PrimaryMembersDao;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.IsFourLevelPlatform;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMemberVo;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembers;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryMembersOrgType;
import com.tianque.baseInfo.primaryOrg.primaryMembers.domain.PrimaryOrgOption;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersOrgTypeService;
import com.tianque.baseInfo.primaryOrg.primaryMembers.service.PrimaryMembersService;
import com.tianque.baseInfo.primaryOrg.service.FourLevelPlatformService;
import com.tianque.baseInfo.primaryOrg.service.PrimaryOrgService;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.DeleteSqlMap;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 成员业务处理实现类
 * 
 */
@Service("primaryMembersService")
@Transactional
public class PrimaryMembersServiceImpl extends AbstractBaseService implements
		PrimaryMembersService {
	@Autowired
	private PrimaryMembersDao primaryMembersDao;
	@Autowired
	private PrimaryOrgService primaryOrgService;
	@Autowired
	private PrimaryMembersOrgTypeService primaryMembersOrgTypeService;
	@Autowired
	private FourLevelPlatformService fourLevelPlatformService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("primaryMembersValidator")
	@Autowired
	private DomainValidator<PrimaryMembers> domainValidator;
	@Autowired
	private PropertyDictService propertyDictService;

	@Override
	public PrimaryMembers addPrimaryMembers(PrimaryMembers primaryMembers,
			String optionOrgIds) {
		primaryMembersValidator(primaryMembers);
		try {
			primaryMembers = primaryMembersDao
					.addPrimaryMembers(primaryMembers);

			if (primaryMembers != null && optionOrgIds != null
					&& optionOrgIds.trim().length() != 0) {
				addPrimaryMembersOrgTypes(primaryMembers, optionOrgIds);
			}
			return primaryMembers;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的addPrimaryMembers方法出现异常，原因：",
					"新增成员出现错误", e);
		}
	}

	private void addPrimaryMembersOrgTypes(PrimaryMembers primaryMembers,
			String optionOrgIds) {
		if (optionOrgIds == null) {
			throw new BusinessValidationException("参数错误！");
		}
		String[] ids = optionOrgIds.split(",");
		PrimaryMembersOrgType primaryMembersOrgType = null;
		for (int i = 0; i < ids.length; i++) {
			primaryMembersOrgType = new PrimaryMembersOrgType();
			primaryMembersOrgType.setPrimaryMembersId(primaryMembers.getId());
			String ids_one = ids[i].split("-")[0];
			String ids_two = ids[i].split("-")[1];
			if (ids[i].split("-").length == 2 && ids_one != null
					&& isNumeric(ids_one) && ids_two != null
					&& isNumeric(ids_two)) {
				primaryMembersOrgType.setPrimaryOrgId(Long.parseLong(ids_two));
				primaryMembersOrgType.setIsFourLevelPlatform(Long
						.parseLong(ids_one));
			}
			primaryMembersOrgType.setId(primaryMembersOrgTypeService
					.addPrimaryMembersOrgType(primaryMembersOrgType));
		}
	}

	private boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	@Override
	public PageInfo<PrimaryOrgOption> findPrimaryOrganizations(Long orgId,
			String displayLevel, String orgTeamClazz, int internalId, int page,
			int rows, String sidx, String sord, String teamTypeDomainName) {
		if (orgId == null) {
			throw new BusinessValidationException("参数错误！");
		}
		Organization org = organizationDubboService.getSimpleOrgById(orgId);
		if (org == null) {
			throw new BusinessValidationException("参数错误！");
		}
		if ("fourLevelPlatform".equals(orgTeamClazz)) {
			SearchFourLevelPlatformVo searchFourLevelPlatformVo = new SearchFourLevelPlatformVo();
			searchFourLevelPlatformVo.setOrgInternalCode(org
					.getOrgInternalCode());
			searchFourLevelPlatformVo.setDisplayLevel(displayLevel);
			PageInfo<FourLevelPlatform> fourLevelPlatforms = fourLevelPlatformService
					.findPagerBySearchVo(searchFourLevelPlatformVo, page, rows,
							sidx, sord);
			if (fourLevelPlatforms.getResult() == null
					|| fourLevelPlatforms.getResult().size() == 0) {
				return new PageInfo<PrimaryOrgOption>();
			}
			List<PrimaryOrgOption> primaryOrgOptions = new ArrayList<PrimaryOrgOption>();
			for (int i = 0; i < fourLevelPlatforms.getResult().size(); i++) {
				primaryOrgOptions.add(recombinePrimaryOrgOption(
						fourLevelPlatforms.getResult().get(i).getId(),
						getTeamTypeNameByTeamType(fourLevelPlatforms
								.getResult().get(i).getTeamType()),
						fourLevelPlatforms.getResult().get(i).getName(),
						IsFourLevelPlatform.FourLevelPlat_IS, 0));
			}
			return resultPageInfo(primaryOrgOptions,
					fourLevelPlatforms.getCurrentPage(),
					fourLevelPlatforms.getPerPageSize(),
					fourLevelPlatforms.getTotalRowSize());

		} else {
			PrimaryOrgVo primaryOrgVo = new PrimaryOrgVo();
			primaryOrgVo.setOrg(org);
			primaryOrgVo.setDisplayLevel(displayLevel);
			PropertyDict teamClazz = new PropertyDict();
			teamClazz.setId(Long.parseLong(orgTeamClazz));
			primaryOrgVo.setTeamClazz(teamClazz);
			primaryOrgVo.setTeamTypeDomainName(teamTypeDomainName);
			PageInfo<PrimaryOrgVo> primaryOrganizationses = primaryOrgService
					.findPrimaryOrgsForOrgOption(primaryOrgVo, internalId,
							page, rows, sidx, sord);
			if (primaryOrganizationses.getResult() == null
					|| primaryOrganizationses.getResult().size() == 0) {
				return new PageInfo<PrimaryOrgOption>();
			}

			List<PrimaryOrgOption> primaryOrgOptions = new ArrayList<PrimaryOrgOption>();
			for (int i = 0; i < primaryOrganizationses.getResult().size(); i++) {
				primaryOrgOptions.add(recombinePrimaryOrgOption(
						primaryOrganizationses.getResult().get(i).getId(),
						primaryOrganizationses.getResult().get(i).getTeamType()
								.getId().toString(), primaryOrganizationses
								.getResult().get(i).getDetailName(),
						IsFourLevelPlatform.FourLevelPlat_NO,
						primaryOrganizationses.getResult().get(i)
								.getIsSynchronize()));
			}
			return resultPageInfo(primaryOrgOptions,
					primaryOrganizationses.getCurrentPage(),
					primaryOrganizationses.getPerPageSize(),
					primaryOrganizationses.getTotalRowSize());

		}
	}

	private PageInfo<PrimaryOrgOption> resultPageInfo(
			List<PrimaryOrgOption> primaryOrgOptions, int currentPage,
			int perPageSize, long totalRowSize) {
		PageInfo<PrimaryOrgOption> pageInfo = new PageInfo<PrimaryOrgOption>();
		pageInfo.setResult(primaryOrgOptions);
		pageInfo.setCurrentPage(currentPage);
		pageInfo.setPerPageSize(perPageSize);
		pageInfo.setTotalRowSize(totalRowSize);
		return pageInfo;
	}

	/**
	 * 重组所在组织页面选择显示的对象
	 * 
	 */
	private PrimaryOrgOption recombinePrimaryOrgOption(Long id,
			String primaryOrgType, String primaryOrgName,
			int isFourLevelPlatform, int isSynchronize) {
		PrimaryOrgOption primaryOrgOption = new PrimaryOrgOption();
		primaryOrgOption.setId(id);
		primaryOrgOption.setPrimaryOrgType(primaryOrgType);
		primaryOrgOption.setPrimaryOrgName(primaryOrgName);
		primaryOrgOption.setIsFourLevelPlatform(isFourLevelPlatform);
		primaryOrgOption.setIsSynchronize(isSynchronize);
		return primaryOrgOption;
	}

	/**
	 * 根据组织类型id给组织类型名称赋值
	 * 
	 */
	private String getTeamTypeNameByTeamType(Long teamTypeId) {
		String TeamTypeName = null;
		if (Long.valueOf(OrganizationLevel.DISTRICT).equals(teamTypeId)) {
			TeamTypeName = FourLevelPlatformOrgType.DISTRICT_ORGTYPE;
		} else if (Long.valueOf(OrganizationLevel.TOWN).equals(teamTypeId)) {
			TeamTypeName = FourLevelPlatformOrgType.TOWN_ORGTYPE;
		} else if (Long.valueOf(OrganizationLevel.VILLAGE).equals(teamTypeId)) {
			TeamTypeName = FourLevelPlatformOrgType.VILLAGE_ORGTYPE;
		} else if (Long.valueOf(OrganizationLevel.GRID).equals(teamTypeId)) {
			TeamTypeName = FourLevelPlatformOrgType.GRID_ORGTYPE;
		}
		return TeamTypeName;
	}

	/***
	 * 显示成员列表信息
	 * 
	 * @menthod:findPrimaryMembers
	 * @param :primaryMemberVo,pageNum,pageSize,sidx,sord
	 */
	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembers(
			PrimaryMemberVo primaryMemberVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		try {
			if (null == primaryMemberVo || null == primaryMemberVo.getOrg()) {
				throw new BusinessValidationException("组织信息不能为空！");
			}
			if (null != primaryMemberVo.getPrimaryorgInfo()
					&& !"".equals(primaryMemberVo.getPrimaryorgInfo())) {
				String[] primaryorgInfoArr = primaryMemberVo
						.getPrimaryorgInfo().split(",");
				if (primaryorgInfoArr.length > 0) {
					String[] ids = new String[primaryorgInfoArr.length];
					String[] isFourLevel = new String[primaryorgInfoArr.length];
					for (int i = 0; i < primaryorgInfoArr.length; i++) {
						String[] primaryorgInfo = primaryorgInfoArr[i]
								.split("-");
						ids[i] = primaryorgInfo[0];
						isFourLevel[i] = primaryorgInfo[1];
					}
					Arrays.sort(ids);
					Arrays.sort(isFourLevel);
					StringBuffer primaryOrgIds = new StringBuffer();
					if (ids.length > 0) {
						for (int i = 0; i < ids.length; i++) {
							primaryOrgIds.append("," + ids[i]);
						}
					}
					StringBuffer isFourLevelPlatforms = new StringBuffer();
					if (isFourLevel.length > 0) {
						for (int i = 0; i < isFourLevel.length; i++) {
							isFourLevelPlatforms.append("," + isFourLevel[i]);
						}
					}
					primaryMemberVo.setPrimaryOrgIds(primaryOrgIds.toString()
							.replaceFirst(",", ""));
					primaryMemberVo
							.setIsFourLevelPlatforms(isFourLevelPlatforms
									.toString().replaceFirst(",", ""));
				}
			}
			primaryMemberVo.setOrg(organizationDubboService
					.getSimpleOrgById(primaryMemberVo.getOrg().getId()));
			primaryMemberVo = fullPrimaryMemberVoTeamClass(primaryMemberVo);
			primaryMemberVo.setSortField(sidx);
			primaryMemberVo.setOrder(sord);
			PageInfo<PrimaryMembers> membersinfo = primaryMembersDao
					.findPrimaryMembers(primaryMemberVo, pageNum, pageSize);
			if (null != membersinfo && null != membersinfo.getResult()
					&& membersinfo.getResult().size() > 0) {
				// 成员列表信息时
				for (PrimaryMembers members : membersinfo.getResult()) {
					if (PrimaryMemberType.ISPRIMARYMEMBER
							.equals(primaryMemberVo.getIsPrimaryMember())) {
						members.setPrimaryOrgName(getPrimaryOrgNameByMemberId(members));
					}
				}
			}
			return membersinfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的findPrimaryMembers方法出现异常，原因：",
					"成员列表信息出现错误", e);
		}
	}

	/***
	 * 组织维护成员显示成员列表信息
	 * 
	 * @menthod:findPrimaryMembers
	 * @param :primaryMemberVo,pageNum,pageSize,sidx,sord
	 */
	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembersByOrg(
			PrimaryMemberVo primaryMemberVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (null == primaryMemberVo || null == primaryMemberVo.getOrg()) {
			throw new BusinessValidationException("组织信息不能为空！");
		}
		try {
			if (null != primaryMemberVo.getPrimaryorgInfo()
					&& !"".equals(primaryMemberVo.getPrimaryorgInfo())) {
				String[] primaryorgInfoArr = primaryMemberVo
						.getPrimaryorgInfo().split(",");
				if (primaryorgInfoArr.length > 0) {
					String[] ids = new String[primaryorgInfoArr.length];
					String[] isFourLevel = new String[primaryorgInfoArr.length];
					for (int i = 0; i < primaryorgInfoArr.length; i++) {
						String[] primaryorgInfo = primaryorgInfoArr[i]
								.split("-");
						ids[i] = primaryorgInfo[0];
						isFourLevel[i] = primaryorgInfo[1];
					}
					Arrays.sort(ids);
					Arrays.sort(isFourLevel);
					StringBuffer primaryOrgIds = new StringBuffer();
					if (ids.length > 0) {
						for (int i = 0; i < ids.length; i++) {
							primaryOrgIds.append("," + ids[i]);
						}
					}
					StringBuffer isFourLevelPlatforms = new StringBuffer();
					if (isFourLevel.length > 0) {
						for (int i = 0; i < isFourLevel.length; i++) {
							isFourLevelPlatforms.append("," + isFourLevel[i]);
						}
					}
					primaryMemberVo.setPrimaryOrgIds(primaryOrgIds.toString()
							.replaceFirst(",", ""));
					primaryMemberVo
							.setIsFourLevelPlatforms(isFourLevelPlatforms
									.toString().replaceFirst(",", ""));
				}
			}
			primaryMemberVo.setOrg(organizationDubboService
					.getSimpleOrgById(primaryMemberVo.getOrg().getId()));
			repairSortField(primaryMemberVo);
			PageInfo<PrimaryMembers> membersinfo = primaryMembersDao
					.findPrimaryMembersByOrg(primaryMemberVo, pageNum, pageSize);
			if (null != membersinfo && null != membersinfo.getResult()
					&& membersinfo.getResult().size() > 0) {
				// 成员列表信息时
				for (PrimaryMembers members : membersinfo.getResult()) {
					if (PrimaryMemberType.ISPRIMARYMEMBER
							.equals(primaryMemberVo.getIsPrimaryMember())) {
						members.setPrimaryOrgName(getPrimaryOrgNameByMemberId(members));
					}
				}
			}
			return membersinfo;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的findPrimaryMembers方法出现异常，原因：",
					"成员列表信息出现错误", e);
		}
	}

	/**
	 * 判断排序字段
	 * 
	 * @param primaryMemberVo
	 */
	private void repairSortField(PrimaryMemberVo primaryMemberVo) {
		if (primaryMemberVo.getSortField() != null
				&& "".equals(primaryMemberVo.getSortField())) {
			if (primaryMemberVo.getPrimaryOrgId() != null
					&& "id".equals(primaryMemberVo.getSortField())) {
				primaryMemberVo.setSortField("primt.seq asc," + "pm."
						+ primaryMemberVo.getSortField());
			} else {
				primaryMemberVo.setSortField("pm."
						+ primaryMemberVo.getSortField());
			}
		} else {
			primaryMemberVo.setSortField("primt.seq asc");
		}
	}

	/**
	 * 填充组织大类
	 * 
	 * @param primaryMemberVo
	 * @return
	 */
	private PrimaryMemberVo fullPrimaryMemberVoTeamClass(
			PrimaryMemberVo primaryMemberVo) {
		if (primaryMemberVo.getTeamClazz() != null
				&& primaryMemberVo.getTeamClazz().getInternalId() != 0) {
			List<PropertyDict> dicts = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(
							PropertyTypes.TEAMCLAZZ, primaryMemberVo
									.getTeamClazz().getInternalId());
			if (dicts != null && dicts.size() > 0) {
				primaryMemberVo.setTeamClazz(dicts.get(0));
			}

		}
		return primaryMemberVo;
	}

	/**
	 * 通过成成员ID得到组织名称
	 * 
	 * @menthod:getPrimaryOrgName
	 * @param members
	 * @return
	 */
	private String getPrimaryOrgNameByMemberId(PrimaryMembers members) {
		if (members.getId() == null) {
			throw new BusinessValidationException("成员ID有误！");
		}
		List<PrimaryOrganizations> primaryOrglist = primaryOrgService
				.getPrimaryOrganizationByprimaryMemberId(members.getId());
		String primaryOrgName = "";
		if (null != primaryOrglist && primaryOrglist.size() > 0) {
			for (int i = 0; i < primaryOrglist.size(); i++) {
				if (i > 1) {
					primaryOrgName += "...";
					break;
				}
				primaryOrgName += "," + primaryOrglist.get(i).getDetailName();
			}
			primaryOrgName = primaryOrgName.replaceFirst(",", "");
		}
		return primaryOrgName;
	}

	/**
	 * 根据ID单个查询
	 * 
	 * @param id
	 * @return
	 */
	public PrimaryMembers getPrimaryMembersById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("没有选中任何数据");
		}
		try {
			return primaryMembersDao.getPrimaryMembersById(id);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的getPrimaryMembersById方法出现异常，原因：",
					"单个成员出现错误", e);
		}
	}

	/**
	 * 根据姓名或者电话进行查询相同的信息
	 * 
	 * @param primaryMembers
	 * @param page
	 * @param rows
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<PrimaryMembers> findPrimaryMembersByNameOrMobile(
			PrimaryMembers primaryMembers, Integer page, Integer rows,
			String sidx, String sord) {
		try {
			if (primaryMembers == null || primaryMembers.getId() == null) {
				throw new BusinessValidationException("没有选中任何数据");
			}
			if (null == primaryMembers.getOrgCode()) {
				throw new BusinessValidationException("组织信息不能为空！");
			}
			return primaryMembersDao.findPrimaryMembersByNameOrMobile(
					primaryMembers, page, rows);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的findPrimaryMembersByNameOrMobile方法出现异常，原因：",
					"根据姓名或者电话进行查询相同的信息出现错误", e);
		}
	}

	/**
	 * 合并
	 * 
	 * @param serviceTeamMemberBase
	 * @param serviceTeamMemberVo
	 * @param selectedIds
	 */
	@Override
	public PrimaryMembers combinePrimaryMembers(PrimaryMembers primaryMembers,
			String ids) {
		try {
			List<Long> combinePrimaryMembersIds = getSelectedIds(ids);
			if (combinePrimaryMembersIds == null) {
				throw new BusinessValidationException("没有要合并的信息!");
			}
			List<PrimaryMembers> list = primaryMembersDao
					.findPrimaryMembersByIds(combinePrimaryMembersIds);
			if (list == null) {
				throw new BusinessValidationException("没有要合并的信息!");
			}
			for (PrimaryMembers primaryMember : list) {
				// 根据成员库ID去查询对应的关联组织信息
				List<PrimaryMembersOrgType> primaryMembersOrgTypes = primaryMembersOrgTypeService
						.findPrimaryMembersOrgTypeByMember(primaryMember
								.getId());
				compareCombinePrimaryMembers(primaryMembers,
						primaryMembersOrgTypes);
				deletePrimaryMembers(combinePrimaryMembersIds);
			}
			// 先修改成员库信息
			primaryMembers = primaryMembersDao
					.updatePrimaryMembers(primaryMembers);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的combinePrimaryMembers方法出现异常，原因：",
					"成员合并出现错误", e);
		}
		return primaryMembers;
	}

	/**
	 * 比较合并信息
	 * 
	 * @param primaryMembers
	 * @param primaryMembersOrgTypes
	 * @return
	 */
	private void compareCombinePrimaryMembers(PrimaryMembers primaryMembers,
			List<PrimaryMembersOrgType> primaryMembersOrgTypes) {
		if (primaryMembersOrgTypes != null && primaryMembersOrgTypes.size() > 0) {
			for (int i = 0; i < primaryMembersOrgTypes.size(); i++) {
				PrimaryMembersOrgType membersOrgType = primaryMembersOrgTypes
						.get(i);
				PrimaryMembersOrgType oldPrimaryMembersOrgTypes = primaryMembersOrgTypeService
						.findPrimaryMembersOrgTypeByMemberAndPrimaryOrg(
								primaryMembers.getId(),
								membersOrgType.getPrimaryOrgId());// 根据成员和组织查询
				if (oldPrimaryMembersOrgTypes == null) {
					membersOrgType.setPrimaryMembersId(primaryMembers.getId());
					primaryMembersOrgTypeService
							.updatePrimaryMembers(membersOrgType);
				} else {
					primaryMembersOrgTypeService.deltePrimaryMembersOrgType(
							membersOrgType.getPrimaryMembersId(),
							membersOrgType.getPrimaryOrgId());
				}
			}
		}
	}

	/**
	 * 任职操作
	 */
	@Override
	public PrimaryMembers havajobPrimaryMember(Long isHaveJob, Long id) {
		PrimaryMembers primaryMembers = null;
		try {
			primaryMembers = primaryMembersDao.getPrimaryMembersById(id);
			primaryMembers.setIsHaveJob(isHaveJob);
			primaryMembers = primaryMembersDao
					.updatePrimaryMembers(primaryMembers);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的havajobPrimaryMember方法出现异常，原因：",
					"任职操作出现错误", e);
		}
		return primaryMembers;
	}

	@Override
	@SolrServerIndex(mode = OperateMode.DELETE, value = DeleteSqlMap.PRIMARYMEMBERS_KEY)
	public void deletePrimaryMembersById(Long[] ids) {
		if (ids == null || ids.length == 0) {
			throw new BusinessValidationException("删除出错！");
		}
		// for (int i = 0; i < ids.length; i++) {
		// primaryMembersOrgTypeService
		// .deltePrimaryMembersOrgTypeByPrimaryMembersId(ids[i]);
		// primaryMembersDao.deletePrimaryMembersById(ids[i]);
		// }
		List<Long> idList = StringUtil.parseLong(ids);
		primaryMembersOrgTypeService
				.deltePrimaryMembersOrgTypeByPrimaryMembersIds(idList);
		primaryMembersDao.deletePrimaryMembersByIds(idList);
	}

	@Override
	public PageInfo<PrimaryMembers> findPrimaryMembers(Long orgId,
			Long isHaveJob, Integer page, Integer rows, String sidx, String sord) {
		return primaryMembersDao.findPrimaryMembers(orgId, isHaveJob, page,
				rows, sidx, sord);
	}

	@Override
	public PrimaryMembers updatePrimaryMembers(PrimaryMembers primaryMembers,
			String optionOrgIds) {
		if (primaryMembers == null) {
			throw new BusinessValidationException("修改出错！");
		}
		try {
			primaryMembersOrgTypeService
					.deltePrimaryMembersOrgTypeByPrimaryMembersId(primaryMembers
							.getId());
			if (optionOrgIds != null && optionOrgIds.trim().length() != 0) {
				addPrimaryMembersOrgTypes(primaryMembers, optionOrgIds);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的updatePrimaryMembers方法出现异常，原因：",
					"修改出现错误", e);
		}
		return primaryMembersDao.updatePrimaryMembers(primaryMembers);
	}

	private void deletePrimaryMembers(List<Long> ids) {
		// for (int i = 0; i < ids.size(); i++) {
		// primaryMembersDao.deletePrimaryMembersById(ids.get(i));
		// }
		primaryMembersDao.deletePrimaryMembersByIds(ids);
	}

	/**
	 * 获取选中的ID集合
	 * 
	 * @param selectedIds
	 *            解析字符串
	 * @return List<Long>
	 */
	private List<Long> getSelectedIds(String selectedIds) {
		String[] selectIdStrs = selectedIds.split(",");
		List<Long> ids = new ArrayList<Long>();
		for (String string : selectIdStrs) {
			Long id = Long.parseLong(string);
			ids.add(id);
		}
		return ids;
	}

	private void primaryMembersValidator(PrimaryMembers primaryMembers) {
		ValidateResult baseDataValidator = domainValidator
				.validate(primaryMembers);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public void setMemberSeq(Long id, Integer seq) {
		if (id == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			primaryMembersDao.setPrimaryOrgSeq(id, seq);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PrimaryMemberServiceImpl的setMemberSeq方法出现异常，原因：",
					"设置排序出现错误", e);
		}
	}
}
