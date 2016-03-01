package com.tianque.plugin.analyzing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.companyPlace.constacts.ModulTypes;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.analyzing.dao.LoginManageDAO;
import com.tianque.plugin.analyzing.domain.LoginManage;
import com.tianque.plugin.analyzing.util.AnalyseUtil;
import com.tianque.service.WorkCalendarService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.tableManage.service.TableManageService;

@Service("loginManageService")
@Transactional
public class LoginManageServiceImpl extends AbstractBaseService implements
		LoginManageService {
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private WorkCalendarService workCalendarService;
	@Autowired
	private TableManageService tableService;
	@Autowired
	private LoginManageDAO loginManageDAO;
	@Autowired
	private CacheService cacheService;

	@Override
	public List<LoginManage> queryLoginManageForList(LoginManage loginManage) {
		if (loginManage == null || loginManage.getOrganization() == null
				|| loginManage.getOrganization().getId() == null) {
			throw new BusinessValidationException("查询出错");
		}
		String key = MemCacheConstant.getLoginManageCachkey(
				MemCacheConstant.LOGINMANAGETABLEFIRST_CACHKEY, loginManage
						.getOrganization().getId(), loginManage.getYear(),
				loginManage.getMonth(), ModulTypes.STATISTICSTABLELIST);

		List<LoginManage> list = null;
		if (key != null) {
			list = (List<LoginManage>) cacheService.get(
					MemCacheConstant.ANALYSE_LOGINMANAGE_NAMESPACE, key);
			if (list != null) {
				return list;
			}
		}

		tableService.createAnalyseTable(AnalyseUtil.LOGINMANGE_TABLE_NAME,
				AnalyseUtil.LOGINMANAGESQL, loginManage.getYear(),
				loginManage.getMonth());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("orgId", loginManage.getOrganization().getId());
			map.put("year", loginManage.getYear());
			map.put("month", loginManage.getMonth());

			list = dealDataColumnVoListForHj(loginManageDAO
					.queryLoginManageForList(map));
			if (key != null) {
				cacheService.set(
						MemCacheConstant.ANALYSE_LOGINMANAGE_NAMESPACE, key,
						ModulTypes.CACHETIME, list);
			}
			return list;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LoginManageServiceImpl的queryLoginManageForList方法出现异常，原因：",
					"研判分析登录情况查询出错！", e);
		}
	}

	@Override
	public List<LoginManage> queryLoginManageByOrgIdForList(
			LoginManage loginManage) {
		if (loginManage == null || loginManage.getOrganization() == null
				|| loginManage.getOrganization().getId() == null) {
			throw new BusinessValidationException("查询出错");
		}
		String key = MemCacheConstant.getLoginManageCachkey(
				MemCacheConstant.LOGINMANAGETABLESECOND_CACHKEY, loginManage
						.getOrganization().getId(), loginManage.getYear(),
				loginManage.getMonth(), ModulTypes.STATISTICSTABLELIST);
		List<LoginManage> list = null;
		if (key != null) {
			list = (List<LoginManage>) cacheService.get(
					MemCacheConstant.ANALYSE_LOGINMANAGE_NAMESPACE, key);
			if (list != null) {
				return list;
			}
		}

		tableService.createAnalyseTable(AnalyseUtil.LOGINMANGE_TABLE_NAME,
				AnalyseUtil.LOGINMANAGESQL, loginManage.getYear(),
				loginManage.getMonth());
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Long> orgList = organizationDubboService
					.findOrgIdByParentId(loginManage.getOrganization().getId());
			map.put("orgList", orgList);
			map.put("year", loginManage.getYear());
			map.put("month", loginManage.getMonth());

			list = dealDataColumnVoListForHj(loginManageDAO
					.queryLoginManageByOrgIdForList(map));
			if (key != null) {
				cacheService.set(
						MemCacheConstant.ANALYSE_LOGINMANAGE_NAMESPACE, key,
						ModulTypes.CACHETIME, list);
			}
			return list;
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类LoginManageServiceImpl的queryLoginManageForList方法出现异常，原因：",
					"研判分析登录情况查询出错！", e);
		}
	}

	private List<LoginManage> dealDataColumnVoListForHj(List<LoginManage> list) {
		List<LoginManage> newList = new ArrayList<LoginManage>();
		int allLoginCount = 0;
		int threeMonthsLoginCount = 0;
		int outThreeMonthsLoginCount = 0;
		int nerverLoginCount = 0;
		LoginManage newLoginManage = new LoginManage();
		for (LoginManage loginManage : list) {
			newList.add(loginManage);
			allLoginCount += loginManage.getAllLoginCount() == null ? 0
					: loginManage.getAllLoginCount();
			threeMonthsLoginCount += loginManage.getThreeMonthsLoginCount() == null ? 0
					: loginManage.getThreeMonthsLoginCount();
			outThreeMonthsLoginCount += loginManage
					.getOutThreeMonthsLoginCount() == null ? 0 : loginManage
					.getOutThreeMonthsLoginCount();
			nerverLoginCount += loginManage.getNerverLoginCount() == null ? 0
					: loginManage.getNerverLoginCount();
		}
		newLoginManage.setAllLoginCount(allLoginCount);
		newLoginManage.setThreeMonthsLoginCount(threeMonthsLoginCount);
		newLoginManage.setOutThreeMonthsLoginCount(outThreeMonthsLoginCount);
		newLoginManage.setNerverLoginCount(nerverLoginCount);
		newLoginManage.setOrgName("合计");
		newList.add(newLoginManage);
		return newList;
	}

	@Override
	public void addLoginManageJob(int year, int month) {
		try {
			Boolean isCreate = tableService.createAnalyseTable(
					AnalyseUtil.LOGINMANGE_TABLE_NAME,
					AnalyseUtil.LOGINMANAGESQL, year, month);
			tableService.createAnalyseIndex(AnalyseUtil.LOGINMANGE_TABLE_NAME,
					AnalyseUtil.ORGID_LOGINMANAGE, year, month);
			if (!isCreate) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("year", year);
				map.put("month", month);
				loginManageDAO.deleteLoginMange(map);
			}
			addLoginManage(year, month);
		} catch (Exception e) {
			throw new ServiceValidationException(e.toString(), e);
		}
	}

	/**
	 * 新增登录情况信息
	 * 
	 * @param organizations
	 * @param year
	 * @param month
	 */
	private void addLoginManage(int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("nowTime", CalendarUtil.format(CalendarUtil.now()));
		loginManageDAO.addLoginManage(map);
	}
}
