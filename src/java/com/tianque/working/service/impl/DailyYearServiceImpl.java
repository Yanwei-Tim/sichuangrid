package com.tianque.working.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.platformMessage.domain.PlatformMessage;
import com.tianque.platformMessage.factory.PlatformMessageFactory;
import com.tianque.platformMessage.service.PlatformMessageService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.working.dao.DailyYearDao;
import com.tianque.working.domain.DailyDirectory;
import com.tianque.working.domain.DailyYear;
import com.tianque.working.service.DailyDirectoryService;
import com.tianque.working.service.DailyLogAttachFileService;
import com.tianque.working.service.DailyYearService;
import com.tianque.working.service.WorkingRecordService;
import com.tianque.working.vo.DailyDirectoryVo;

@Service("dailyYearService")
@Transactional
public class DailyYearServiceImpl implements DailyYearService {

	@Autowired
	public DailyYearDao dailyYearDao;
	@Autowired
	private DailyDirectoryService dailyDirectoryService;
	@Autowired
	public OrganizationDubboService organizationDubboService;
	@Autowired
	public DailyLogAttachFileService dailyLogAttachFileService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private WorkingRecordService workingRecordService;
	@Autowired
	private PlatformMessageFactory platformMessageFactory;
	@Autowired
	private PlatformMessageService platformMessageService;

	@Override
	public DailyYear addDailyYear(DailyYear dailyYear) {
		if (!validate(dailyYear))
			throw new BusinessValidationException();
		return dailyYearDao.addDailyYear(dailyYear);
	}

	@Override
	public PageInfo<DailyYear> findDailyYearForPageByOrgId(Long orgId,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (orgId == null) {
			throw new BusinessValidationException("网格不能为空！");
		}
		return dailyYearDao.findDailyYearForPageByOrgId(orgId, pageNum,
				pageSize, sidx, sord);
	}

	@Override
	public List<DailyYear> findDailyYearList() {
		return dailyYearDao.findDailyYearList();
	}

	@Override
	public DailyYear getSimpleDailyYearById(Long id) {
		return dailyYearDao.getSimpleDailyYearById(id);
	}

	private boolean validate(DailyYear dailyYear) {
		if (dailyYear == null)
			return false;
		if (dailyYear.getName() == null || "".equals(dailyYear.getName()))
			return false;
		return true;
	}

	@Override
	public boolean checkUniqueDailyYearByOrgIdAndYear(DailyYear dailyYear) {
		if (dailyYear == null || dailyYear.getMakedOrganization() == null
				|| dailyYear.getMakedOrganization().getId() == null) {
			return true;
		}
		int count = dailyYearDao.countDailyYearByOrgIdAndYear(dailyYear
				.getMakedOrganization().getId(), dailyYear.getYearDate());
		if (count > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<DailyYear> findDailyYearsByOrgId(Long orgId) {
		return dailyYearDao.findDailyYearByOrgId(orgId);
	}

	@Override
	public List<DailyYear> findDailyYearsByOrgIdAndStatus(Long orgId,
			Long status) {
		if (orgId == null || status == null) {
			throw new BusinessValidationException();
		}
		List<DailyYear> lists = dailyYearDao.findDailyYearsByOrgIdAndStatus(
				orgId, status);
		if (null != lists && lists.size() > 0) {
			int nowYear = Calendar.getInstance().get(Calendar.YEAR);
			for (DailyYear dailyYear : lists) {
				if (nowYear == dailyYear.getYearDate().intValue()) {
					DailyYear first = dailyYear;
					lists.remove(dailyYear);
					lists.add(0, first);
					break;
				}
			}
		}
		return lists;
	}

	@Override
	public DailyYear getInitYear() {
		return dailyYearDao.getInitYear();
	}

	@Override
	public DailyYear findDailyYearByParentOrgIdAndYear(Long currentOrgId,
			String name) {
		Organization organization = organizationDubboService
				.getSimpleOrgById(currentOrgId);
		if (null != organization.getParentOrg()) {
			currentOrgId = organization.getParentOrg().getId();
		}
		return dailyYearDao.findDailyYearByParentOrgIdAndYear(currentOrgId,
				name);
	}

	@Override
	public String deleteDailyYearById(Long yearId) {
		if (yearId == null) {
			throw new BusinessValidationException();
		}
		String mag = this.validateDeleteDailyYearById(yearId);
		if (!"true".equals(mag)) {
			return mag;
		}
		dailyDirectoryService.deleteDailyDirectoryByYearId(yearId);
		dailyYearDao.deleteDailyYear(yearId);
		return "true";
	}

	@Override
	public String validateDeleteDailyYearById(Long dailyYearId) {
		DailyYear year = dailyYearDao.getSimpleDailyYearById(dailyYearId);
		if (null != year.getStatus() && 1L == year.getStatus().longValue()) {
			return "该年度目录台账已经启用，不能删除！";
		}
		List<DailyDirectory> result = dailyDirectoryService
				.findDailyDirectoryByDailyYearId(dailyYearId);
		if (null == result || result.size() <= 0) {
			return "true";
		}
		long count = 0;
		StringBuffer sb = new StringBuffer("");
		for (DailyDirectory directory : result) {
			count = workingRecordService.countSubTablesValue(directory.getId())
					.longValue();
			if (count > 0) {
				sb.append(directory.getFullName()).append("、");
			}
			if (sb.length() > 30) {
				sb.deleteCharAt(sb.length() - 1).append("等、");
				break;
			}
		}
		if (StringUtil.isStringAvaliable(sb.toString())) {
			return sb.substring(0, sb.length() - 1) + "目录下有工作台帐信息，无法删除!";
		}
		return "true";
	}

	@Override
	public void initDailyDirectory(Long templateId, DailyYear dailyYear) {
		if (dailyYear == null || dailyYear.getId() == null) {
			return;
		}
		if (templateId == null || templateId.longValue() <= 0) {
			initEmptyTemplate(dailyYear);
			return;
		}
		List<DailyDirectory> list = dailyDirectoryService
				.findDailyTrunkDirectoryByDailyYearId(templateId);
		if (list == null || list.size() <= 0) {
			return;
		}
		List<DailyDirectory> lastDailyDirectorys = new ArrayList<DailyDirectory>();
		for (DailyDirectory temp : list) {
			if (temp.getType() != null && temp.getType().getId() != null) {
				addCopyDirectory(dailyYear, temp, null);
				lastDailyDirectorys.add(temp);
			}
		}
		list.removeAll(lastDailyDirectorys);
		for (DailyDirectory temp : list) {
			addCopyDirectory(dailyYear, temp, null);
		}
	}

	private void initEmptyTemplate(DailyYear dailyYear) {
		List<DailyDirectory> list = dailyDirectoryService
				.findDailyTrunkDirectoryByDailyYearId(1L);
		List<PropertyDict> propertyList = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DAILYDIRECTORY_TYPE);

		for (DailyDirectory directory : list) {
			if (directory.getType() == null
					|| directory.getType().getId() == null) {
				continue;
			}
			for (PropertyDict pro : propertyList) {
				if (directory.getType().getId().equals(pro.getId())) {
					addCopyDirectory(dailyYear, directory, null);
				}
			}
		}
	}

	private void addCopyDirectory(DailyYear dailyYear,
			DailyDirectory directory, DailyDirectory parentDirectory) {
		directory.setDailyYear(dailyYear);
		directory.setParentDailyDirectory(parentDirectory);
		List<DailyDirectory> subDailyDirectorys = dailyDirectoryService
				.findDailySubDirectoryByParentId(directory.getId());
		parentDirectory = dailyDirectoryService.addDailyDirectory(directory);
		for (DailyDirectory subDailyDirectory : subDailyDirectorys) {
			addCopyDirectory(dailyYear, subDailyDirectory, parentDirectory);
		}
	}

	@Override
	public List<DailyDirectoryVo> findDailyDirectory(Long dailyYearId) {
		List<DailyDirectoryVo> result = new ArrayList<DailyDirectoryVo>();
		if (null == dailyYearId) {
			return result;
		}
		List<DailyDirectory> dailyDirectorys = dailyDirectoryService
				.findDailyTrunkDirectoryByDailyYearId(dailyYearId);
		exchangeToDailyDirectoryVO(dailyDirectorys, result, 2);
		return result;
	}

	private void exchangeToDailyDirectoryVO(
			List<DailyDirectory> dailyDirectorys,
			List<DailyDirectoryVo> result, int level) {
		for (DailyDirectory dailyDirectory : dailyDirectorys) {
			List<DailyDirectory> childDirectory = dailyDirectoryService
					.findDailySubDirectoryByParentId(dailyDirectory.getId());
			setAttachFile(dailyDirectory);
			setFullType(dailyDirectory);
			if (null == childDirectory || childDirectory.size() <= 0) {
				result.add(new DailyDirectoryVo(dailyDirectory, level, true));
			} else {
				result.add(new DailyDirectoryVo(dailyDirectory, level, false));
				exchangeToDailyDirectoryVO(childDirectory, result, level + 1);
			}
		}
	}

	private void setFullType(DailyDirectory dailyDirectory) {
		if (dailyDirectory.getType() != null
				&& dailyDirectory.getType().getId() != null) {
			dailyDirectory.setType(propertyDictService
					.getPropertyDictById(dailyDirectory.getType().getId()));
		}
		dailyDirectory.setDailyDirectoryAttachFiles(dailyDirectoryService
				.findDailyDirectoryAttachFilesByDailyDirectoryId(dailyDirectory
						.getId()));
	}

	private void setAttachFile(DailyDirectory dailyDirectory) {
		dailyDirectory.setDailyDirectoryAttachFiles(dailyDirectoryService
				.findDailyDirectoryAttachFilesByDailyDirectoryId(dailyDirectory
						.getId()));
	}

	@Override
	public DailyYear updateDailyYear(DailyYear dailyYear) {
		if (dailyYear == null || dailyYear.getId() == null
				|| !StringUtil.isStringAvaliable(dailyYear.getName())) {
			throw new BusinessValidationException("更新失败");
		}
		return dailyYearDao.updateDailyYear(dailyYear);
	}

	@Override
	public DailyYear startDailyYearById(Long dailyYearId) {
		if (dailyYearId == null) {
			throw new BusinessValidationException();
		}
		DailyYear dailyYear = this.getSimpleDailyYearById(dailyYearId);
		if (dailyYear == null) {
			throw new BusinessValidationException();
		}
		dailyYear.setStatus(1L);
		sendPltformMessageToOrg(dailyYear);
		return dailyYearDao.startAndStopDailyYearById(dailyYear);
	}

	// 台帐启用时给直属下辖部门发送平台消息
	private void sendPltformMessageToOrg(DailyYear dailyYear) {
		PlatformMessage pm = platformMessageFactory
				.createStartDailyYearPlatformMessage(dailyYear);
		List<Organization> orgs = organizationDubboService
				.findOrganizationsByParentId(dailyYear.getMakedOrganization()
						.getId());
		// 有下辖部门的时候才发平台消息
		if (orgs != null && orgs.size() > 0) {
			for (Organization org : orgs) {
				pm.setReceiverId(org.getId());
				platformMessageService.sendPlatformMessageToOrg(pm);
			}
		}

	}

	@Override
	public DailyYear stopDailyYearById(Long dailyYearId) {
		if (dailyYearId == null) {
			throw new BusinessValidationException();
		}
		DailyYear dailyYear = this.getSimpleDailyYearById(dailyYearId);
		if (dailyYear == null) {
			throw new BusinessValidationException();
		}
		dailyYear.setStatus(0L);
		return dailyYearDao.startAndStopDailyYearById(dailyYear);
	}

	@Override
	public DailyYear getDailyYearByOrgIdAndYear(Long currentOrgId, Integer year) {
		if (null == currentOrgId || null == year) {
			throw new BusinessValidationException();
		}
		return dailyYearDao.getDailyYearByOrgIdAndYear(currentOrgId, year);
	}

	@Override
	public DailyYear setWhetherAutoStartDailyYear(DailyYear dailyYear) {
		if (dailyYear == null) {
			throw new BusinessValidationException("更新失败");
		}
		return dailyYearDao.setWhetherAutoStartDailyYear(dailyYear);
	}

	@Override
	public List<DailyYear> findAutoStartDailyYear() {
		return dailyYearDao.findAutoStartDailyYear();
	}

	@Override
	public List<DailyYear> findManuallyStartDailyYear() {
		return dailyYearDao.findManuallyStartDailyYear();
	}
}
