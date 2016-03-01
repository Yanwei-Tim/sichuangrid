package com.tianque.plugin.orgchange.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.Chinese2pinyin;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.plugin.orgchange.common.OrgChangeUtils;
import com.tianque.plugin.orgchange.domain.OrgChangeInfo;
import com.tianque.plugin.orgchange.domain.OrgChangeLog;
import com.tianque.plugin.orgchange.domain.OrgMapping;
import com.tianque.plugin.orgchange.domain.OrganizationsBackupVo;
import com.tianque.plugin.orgchange.service.OrganizationsBackupService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * Organizations变数据变更执行者
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@Component("organizationHandler")
public class OrganizationHandler {

	private final static Logger logger = LoggerFactory
			.getLogger(OrganizationHandler.class);

	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private OrganizationsBackupService organizationsBackupService;

	/**
	 * 组织机构迁移操作，改变所迁移组织的parentId、orgcode和seq，并更新父组织的subcount，如果所迁移的组织非网格，
	 * 则还需要修改被迁移组织所有附属组织的orgcode和seq，刷新它们的缓存
	 * 
	 * @param orgChangeInfo
	 * @param orgChangeLog
	 * @return 组织机构变更对应关系
	 */
	@Transactional
	public List<OrgMapping> transfer(OrgChangeInfo orgChangeInfo,
			OrgChangeLog orgChangeLog) {
		Organization sourceOrg = organizationDubboService
				.getSimpleOrgById(orgChangeInfo.getSourceOrgId());
		if (sourceOrg == null) {
			orgChangeLog.setErrorDesc("迁移源组织机构"
					+ orgChangeInfo.getSourceOrgName() + "["
					+ orgChangeInfo.getSourceOrgId() + "]不存在。");
			throw new OrgChangeHandlerException(orgChangeLog);
		}
		orgChangeInfo.setSourceParentid(sourceOrg.getParentOrg().getId());
		Organization targetOrg = organizationDubboService
				.getSimpleOrgById(orgChangeInfo.getTargetorgId());
		if (targetOrg == null) {
			orgChangeLog.setErrorDesc("迁移目标组织机构"
					+ orgChangeInfo.getSourceOrgName() + "["
					+ orgChangeInfo.getSourceOrgId() + "]不存在。");
			throw new OrgChangeHandlerException(orgChangeLog);
		}
		return transfer(orgChangeInfo, orgChangeLog, sourceOrg, targetOrg);

	}

	/**
	 * 组织机构合并操作，只能合并同一层级的组织机构，并删除源组织，如果非网格组织机构合并则就是批量迁移。
	 * 
	 * @param orgChangeInfo
	 * @param orgChangeLog
	 * @return
	 */
	public List<OrgMapping> merge(OrgChangeInfo orgChangeInfo,
			OrgChangeLog orgChangeLog) {
		List<OrgMapping> orgMappingList = new ArrayList<OrgMapping>();
		Organization sourceOrg = organizationDubboService
				.getSimpleOrgById(orgChangeInfo.getSourceOrgId());
		if (sourceOrg == null) {
			orgChangeLog.setErrorDesc("合并源组织机构"
					+ orgChangeInfo.getSourceOrgName() + "["
					+ orgChangeInfo.getSourceOrgId() + "]不存在。");
			throw new OrgChangeHandlerException(orgChangeLog);
		}
		Organization targetOrg = organizationDubboService
				.getSimpleOrgById(orgChangeInfo.getTargetorgId());
		if (targetOrg == null) {
			orgChangeLog.setErrorDesc("合并目标组织机构"
					+ orgChangeInfo.getSourceOrgName() + "["
					+ orgChangeInfo.getSourceOrgId() + "]不存在。");
			throw new OrgChangeHandlerException(orgChangeLog);
		}
		if (sourceOrg.getOrgLevel().getId()
				.equals(targetOrg.getOrgLevel().getId())) {
			OrgMapping orgMapping = new OrgMapping(orgChangeInfo,
					sourceOrg.getId(), sourceOrg.getOrgInternalCode(),
					targetOrg.getId(), targetOrg.getOrgInternalCode());
			if (sourceOrg.getParentOrg() != null) {
				orgMapping.setOldParentOrgId(sourceOrg.getParentOrg().getId());
				orgMapping.setOldOrgName(sourceOrg.getOrgName());
			}

			List<Organization> temp = organizationDubboService
					.findOrganizationByOrgIdAndNum(orgMapping.getOldOrgId(),
							OrganizationType.DISTRICT_LEVEL);
			if (temp != null && !temp.isEmpty())
				orgMapping.setOldDistrictOrgId(temp.get(0).getId());
			String departmentNo = sourceOrg.getDepartmentNo();
			if (sourceOrg.getDepartmentNo() != null
					&& departmentNo.length() > 3) {
				orgMapping.setDeptNoTail(departmentNo.substring(0, 4));
			}
			orgMappingList.add(orgMapping);
			List<Organization> children = organizationDubboService
					.findOrganizationsByParentId(sourceOrg.getId());
			for (Organization organization : children) {
				orgMappingList.addAll(transfer(orgChangeInfo, orgChangeLog,
						organization, targetOrg));
			}
			// organizationDubboService.deleteOrgById(orgChangeInfo.getSourceOrgId());
			orgChangeLog
					.setSuccessDesc(orgChangeLog.getDescription() + ":合并成功");
		} else {
			orgChangeLog.setErrorDesc("被合并的两个组织机构层级不同无法合并");
			throw new OrgChangeHandlerException(orgChangeLog);
		}

		return orgMappingList;
	}

	public void backup(OrgChangeInfo orgChangeInfo, OrgChangeLog orgChangeLog) {
		if (logger.isDebugEnabled())
			logger.debug("begin backup.orgChangeInfo:{},orgChangeLog:{}",
					orgChangeInfo, orgChangeLog);
	}

	public void recover(OrgChangeInfo orgChangeInfo, OrgChangeLog orgChangeLog) {
		if (logger.isDebugEnabled())
			logger.debug("begin backup.orgChangeInfo:{},orgChangeLog:{}",
					orgChangeInfo, orgChangeLog);
	}

	private List<OrgMapping> transfer(OrgChangeInfo orgChangeInfo,
			OrgChangeLog orgChangeLog, Organization sourceOrg,
			Organization targetOrg) {
		Organization sourceParent = organizationDubboService
				.getSimpleOrgById(sourceOrg.getParentOrg().getId());
		List<OrgMapping> orgMappingList = new ArrayList<OrgMapping>();
		if (sourceParent == null) {
			orgChangeLog.setErrorDesc("被迁移的组织机构父组织为空["
					+ orgChangeInfo.getSourceOrgId() + "]");
			throw new OrgChangeHandlerException(orgChangeLog);
		}

		if (sourceParent.getOrgLevel().getId()
				.equals(targetOrg.getOrgLevel().getId())) {
			OrganizationsBackupVo organizationsBackupVo = new OrganizationsBackupVo(
					sourceOrg.getId(), OrgChangeUtils.TRANSFER, new Date());
			organizationsBackupService.updateChangeInfo(organizationsBackupVo);
			// 参数
			OrgMapping orgMapping = new OrgMapping();
			orgMapping.setOrgChangeInfo(orgChangeInfo);
			orgMapping.setOldOrgId(targetOrg.getId());
			orgMapping.setOldOrgCode(sourceOrg.getOrgInternalCode());
			sourceOrg.setParentOrg(targetOrg);
			// 名称有重复是添加后缀 ，超过20个后截取，如：xxx[父组织名称]
			checkOrgName(sourceOrg, targetOrg, sourceParent);
			// 修改orgCode，才能真正生成OrgMapping
			changeOrgCode(orgChangeInfo, orgMappingList, sourceOrg, targetOrg,
					orgMapping);
			// 更新seq
			changeSeq(sourceOrg, targetOrg);
			// 更新部门编号
			updateSourceOrganizationDeptNo(sourceOrg, targetOrg);
			organizationDubboService.updateOrgSeqAndParentId(sourceOrg.getId(),
					sourceOrg.getSeq(), targetOrg.getId());
			organizationDubboService
					.updateOrgNameAndOrgTypeAndContactWay(sourceOrg);
			orgChangeLog
					.setSuccessDesc(orgChangeLog.getDescription() + ":迁移成功");
		} else {
			orgChangeLog.setErrorDesc("被迁移的组织机构父组织和目标组织不同一层级");
			throw new OrgChangeHandlerException(orgChangeLog);
		}

		return orgMappingList;
	}

	/**
	 * 名称有重复是添加后缀 ，超过20个后截取，如：xxx[父组织名称]
	 * 
	 * @param sourceOrg
	 * @param targetOrgId
	 * @param sourceParent
	 */
	private void checkOrgName(Organization sourceOrg, Organization targetOrg,
			Organization sourceParent) {
		List<Organization> result = organizationDubboService
				.findOrganizationsByOrgNameAndParentId(sourceOrg.getId(),
						sourceOrg.getOrgName(), targetOrg.getId());
		if (!result.isEmpty()) {
			String orgName = sourceOrg.getOrgName() + "["
					+ sourceParent.getOrgName() + "]";
			if (orgName.length() > 20) {
				orgName = orgName.substring(0, 18);
				// 添加个随机数防止截取后出现重复
				orgName = orgName + (new Random().nextInt(10)) + "]";
			}
			sourceOrg.setOrgName(orgName);
			Map<String, String> pinyin = Chinese2pinyin
					.changeChinese2Pinyin(orgName);
			sourceOrg.setSimplePinyin(pinyin.get("simplePinyin"));
			sourceOrg.setFullPinyin(pinyin.get("fullPinyin"));
		}
	}

	private void changeOrgCode(OrgChangeInfo orgChangeInfo,
			List<OrgMapping> orgMappingList, Organization sourceOrg,
			Organization targetOrg, OrgMapping orgMapping) {
		// 重新生成orgcode
		Organization parentOrg = organizationDubboService.updateOrgSubCount(
				targetOrg.getId(), 1);
		String internalCode = parentOrg.getOrgInternalCode()
				+ organizationDubboService.getMaxCodeById(targetOrg.getId())
				+ ".";
		orgMapping.setNewOrgCode(internalCode);
		sourceOrg.setOrgInternalCode(internalCode);

		// 更新子部门orgcode
		List<Organization> children = organizationDubboService
				.findOrganizationsByOrgInternalCode(orgMapping.getOldOrgCode());
		for (Organization organization : children) {
			String oldOrgCode, newOrgCode;
			Long id;
			OrgMapping item;
			if (!orgMapping.getOldOrgId().equals(organization.getId())) {
				oldOrgCode = organization.getOrgInternalCode();
				newOrgCode = oldOrgCode.replaceAll(orgMapping.getOldOrgCode(),
						orgMapping.getNewOrgCode());
				id = organization.getId();
				item = new OrgMapping(orgChangeInfo, id, oldOrgCode, id,
						newOrgCode);
				if (sourceOrg.getParentOrg() != null) {
					item.setOldParentOrgId(organization.getParentOrg().getId());
					item.setOldOrgName(organization.getOrgName());
				}
				List<Organization> temp = organizationDubboService
						.findOrganizationByOrgIdAndNum(item.getOldOrgId(),
								OrganizationType.DISTRICT_LEVEL);
				if (temp != null && !temp.isEmpty())
					item.setOldDistrictOrgId(temp.get(0).getId());
				String departmentNo = sourceOrg.getDepartmentNo();
				if (sourceOrg.getDepartmentNo() != null
						&& departmentNo.length() > 3) {
					item.setDeptNoTail(departmentNo.substring(0, 4));
				}
				orgMappingList.add(item);

				organizationDubboService.updateOrgInternalCode(id, newOrgCode);
			}
		}
	}

	private void changeSeq(Organization sourceOrg, Organization targetOrg) {
		Integer seq = organizationDubboService
				.findChildrenMaxSeqByParentId(targetOrg.getId());
		if (null == seq) {
			seq = 0;
		}
		sourceOrg.setSeq(seq.longValue() + 1);
	}

	// 修改要迁移的部门deptNo
	private void updateSourceOrganizationDeptNo(
			Organization sourceOrganization, Organization targetOrganization) {
		// 迁移的部门的原deptNo
		String oldDeptNo = sourceOrganization.getDepartmentNo();
		String newDeptNo = null;
		PropertyDict propertyDict = propertyDictService
				.getPropertyDictById(sourceOrganization.getOrgType().getId());
		if (propertyDict.getInternalId() == OrganizationType.ADMINISTRATIVE_REGION) {
			List<String> childDeptNos = organizationDubboService
					.getDepartmentnosByParentOrgId(targetOrganization.getId());

			List<String> availableDeptNos = getAvailableDeptNos(childDeptNos,
					targetOrganization);
			// 新的部门deptNo = 目标部门的deptNo + 当前可用deptNo
			// 330220 = 3302 + 20
			newDeptNo = targetOrganization.getDepartmentNo()
					+ availableDeptNos.get(0);
		} else if (propertyDict.getInternalId() == OrganizationType.FUNCTIONAL_ORG) {
			Organization deptOrg = new Organization();
			Random random = new Random();
			do {
				newDeptNo = targetOrganization.getDepartmentNo()
						+ random.nextInt(10)
						+ (char) (65 + new Random().nextInt(26))
						+ (char) (65 + new Random().nextInt(26));
				deptOrg.setDepartmentNo(newDeptNo);
			} while (!organizationDubboService
					.validateRepeatDepartmentNo(deptOrg));
		}

		// 修改当前层级的deptNo
		sourceOrganization.setDepartmentNo(newDeptNo);
		// 修改该层级下的所有下辖的deptNo
		organizationDubboService.editChildOrganizationDeptNo(oldDeptNo,
				newDeptNo);
	}

	// 得到可用的部门编号集合
	// 若被迁移的目标子层级的dept是两位数(不连带父层级的deptNo)，且1-50号都有值
	// 则可用deptNo的集合为51-99
	private List<String> getAvailableDeptNos(List<String> childDeptNos,
			Organization targetOrganization) {
		String targetDeptNo = targetOrganization.getDepartmentNo();
		List<String> currentChildDeptNo = getCurrentChildDeptNos(childDeptNos,
				targetDeptNo);
		List<String> availableDeptNos = new ArrayList<String>();
		PropertyDict orgLevel = propertyDictService
				.getPropertyDictById(targetOrganization.getOrgLevel().getId());
		// 生成可用deptNo的范围
		int tempNoSize = 0;
		if (OrganizationLevel.CITY == orgLevel.getInternalId()) {// 当前层级为区县，则deptNo为两位数
			tempNoSize = 100;
		} else {// 乡镇(街道)层级及以下，当前的部门deptNo的长度需要三位数
			tempNoSize = 1000;
		}
		List<String> tempList = getStrListByMaxSize(tempNoSize);
		for (String no : tempList) {
			// 当前层级的部门deptNo(不连带父级)
			// 不存在就添加到可用集合中
			if (!isExistObjectFromList(currentChildDeptNo, no)) {
				availableDeptNos.add(no);
			}
		}
		return availableDeptNos;
	}

	private List<String> getCurrentChildDeptNos(List<String> childDeptNos,
			String targetDeptNo) {
		if (childDeptNos == null)
			return new ArrayList<String>();
		List<String> list = new ArrayList<String>();
		for (String childNo : childDeptNos) {
			list.add(childNo.substring(targetDeptNo.length(), childNo.length()));
		}
		return list;
	}

	private List<String> getStrListByMaxSize(int maxSize) {
		List<String> list = new ArrayList<String>();
		if (maxSize == 100) {// 拼接成01、02、03...
			for (int i = 1; i < maxSize; i++) {
				if (i < 10) {
					list.add("0" + String.valueOf(i));
					continue;
				}
				list.add(String.valueOf(i));
			}
		} else {// 拼接成001、002、003、010、011...
			for (int i = 1; i < maxSize; i++) {
				if (i < 10) {
					list.add("00" + String.valueOf(i));
					continue;
				} else if (10 <= i && i < 100) {
					list.add("0" + String.valueOf(i));
					continue;
				}
				list.add(String.valueOf(i));
			}
		}
		return list;
	}

	private boolean isExistObjectFromList(List<String> list, String value) {
		for (String str : list) {
			if (str.equals(value)) {
				return true;
			}
		}
		return false;
	}

}
