package com.tianque.plugin.analyzing.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.dangerousGoodsPractitioner.dao.SearchDangerousGoodsPractitionerDao;
import com.tianque.baseInfo.idleYouth.dao.SearchIdleYouthDao;
import com.tianque.baseInfo.positiveInfo.domain.PositiveInfo;
import com.tianque.baseInfo.superiorVisit.dao.SearchSuperiorVisitDao;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.dao.BaseInfoStatTypeDao;
import com.tianque.plugin.analyzing.dao.StatisticsBaseInfoDao;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.domain.PersonnelDetailDataVo;
import com.tianque.plugin.analyzing.domain.StatAnalysePlaceVo;
import com.tianque.service.SearchPositiveInfosService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("statisticsPositiveinfoService")
public class StatisticsPersonnelServiceImpl implements
		StatisticsPersonnelService {

	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private SearchPositiveInfosService searchPositiveInfosService;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private SearchIdleYouthDao searchIdleYouthDao;
	@Autowired
	private StatisticsBaseInfoDao statisticsBaseInfoDao;
	@Autowired
	private SearchSuperiorVisitDao searchSuperiorVisitDao;
	@Autowired
	private SearchDangerousGoodsPractitionerDao searchDangerousGoodsPractitionerDao;
	@Autowired
	private BaseInfoStatTypeDao baseInfoStatTypeDao;

	@Override
	public List<PersonnelAreaDataVo> getPositiveinfoAreaDataByOrgId(Long orgId) {
		int sum = 0;
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> personnelAreaDataVos = new ArrayList<PersonnelAreaDataVo>();

		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		for (Organization organization : organizations) {
			PersonnelAreaDataVo personnelAreaDataVo = getPositiveinfoAreaDataVoByOrgId(organization);
			int count = getPositiveinfoAmountByOrgInternalCode(organization
					.getOrgInternalCode());

			if (count != 0) {
				personnelAreaDataVo.setAmount(count);
				sum += count;
			}
			personnelAreaDataVos.add(personnelAreaDataVo);

		}
		personnelAreaDataVos.add(lastRowPost("合计", sum, personnelAreaDataVos));
		return personnelAreaDataVos;
	}

	private PersonnelAreaDataVo lastRowPost(String string, int sum,
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		PersonnelAreaDataVo vo = new PersonnelAreaDataVo();
		PersonnelDetailDataVo result = new PersonnelDetailDataVo();
		result.setAmount(sum);
		Organization organization = new Organization();
		organization.setOrgName(string);
		vo.setOrg(organization);
		vo.setAmount(sum);

		vo.setPersonnelDetailDatas(getPositiveinfoAreaDataVoTypeCount(personnelAreaDataVos));

		return vo;
	}

	private List<PersonnelDetailDataVo> getPositiveinfoAreaDataVoTypeCount(
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo positiveinfoDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;
		int noResiteSum = 0;
		int resiteSum = 0;
		int recidivism = 0;

		for (PersonnelAreaDataVo domain : personnelAreaDataVos) {
			List<PersonnelDetailDataVo> personnelDetailDatas = domain
					.getPersonnelDetailDatas();
			for (PersonnelDetailDataVo result : personnelDetailDatas) {
				if (result.getName().equals("合计")) {
					amountSum += result.getAmount();
					helpedSum += result.getHelped();
					noHelpedSum += result.getNoHelp();
					noResiteSum += result.getNoResite();
					resiteSum += result.getResited();
					recidivism += result.getRecidivism();
				}
			}
		}

		positiveinfoDetailDataVoSum.setName("/");
		positiveinfoDetailDataVoSum.setAmount(amountSum);
		positiveinfoDetailDataVoSum.setHelped(helpedSum);
		positiveinfoDetailDataVoSum.setNoHelp(noHelpedSum);
		positiveinfoDetailDataVoSum.setResited(resiteSum);
		positiveinfoDetailDataVoSum.setNoResite(noResiteSum);
		positiveinfoDetailDataVoSum.setRecidivism(recidivism);

		PersonnelDetailDataVos.add(positiveinfoDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	@Override
	public List<PersonnelAreaDataVo> getImportantPersonelAreaDataByOrgId(
			Long orgId) {
		int sum = 0;
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> personnelAreaDataVos = new ArrayList<PersonnelAreaDataVo>();
		List<Organization> orgainizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		for (Organization organization : orgainizations) {
			int count = getImportantPersonelCountSumByOrgInternalCode(organization
					.getOrgInternalCode());
			PersonnelAreaDataVo vo = getImportantPersonelAreaDataVoByOrgId(organization);

			if (count != 0) {
				vo.setAmount(count);
			}
			sum += count;
			personnelAreaDataVos.add(vo);

		}
		personnelAreaDataVos.add(lastRowAll("合计", sum, personnelAreaDataVos));

		return personnelAreaDataVos;
	}

	private PersonnelAreaDataVo lastRowAll(String string, int sum,
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		PersonnelAreaDataVo vo = new PersonnelAreaDataVo();
		PersonnelDetailDataVo result = new PersonnelDetailDataVo();
		result.setAmount(sum);
		Organization organization = new Organization();
		organization.setOrgName(string);
		vo.setOrg(organization);
		vo.setAmount(sum);

		vo.setPersonnelDetailDatas(getAllAreaDataVoTypeCount(personnelAreaDataVos));

		return vo;
	}

	private List<PersonnelDetailDataVo> getAllAreaDataVoTypeCount(
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo positiveinfoDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		double amountPercentage = 0;

		for (PersonnelAreaDataVo domain : personnelAreaDataVos) {
			List<PersonnelDetailDataVo> personnelDetailDatas = domain
					.getPersonnelDetailDatas();
			for (PersonnelDetailDataVo result : personnelDetailDatas) {
				if (result.getName().equals("合计")) {
					amountSum += result.getAmount();
					amountPercentage = result.getAmountPercentage();
				}
			}
		}

		positiveinfoDetailDataVoSum.setName("/");
		positiveinfoDetailDataVoSum.setAmount(amountSum);
		positiveinfoDetailDataVoSum.setAmountPercentage(amountPercentage);

		PersonnelDetailDataVos.add(positiveinfoDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private PersonnelAreaDataVo getImportantPersonelAreaDataVoByOrgId(
			Organization organization) {
		PersonnelAreaDataVo personnelAreaDataVo = new PersonnelAreaDataVo();
		personnelAreaDataVo.setOrg(organization);
		personnelAreaDataVo
				.setPersonnelDetailDatas(getImportantPersonelDetailDatasByOrgInternalCode(organization
						.getOrgInternalCode()));
		return personnelAreaDataVo;
	}

	private List<PersonnelDetailDataVo> getImportantPersonelDetailDatasByOrgInternalCode(
			String orgInternalCode) {
		int amountSum = 0;
		List<PersonnelDetailDataVo> personnelDetailDataVosTemp = new ArrayList<PersonnelDetailDataVo>();
		List<PersonnelDetailDataVo> personnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		for (Entry<String, String> entry : BaseInfoTables.importantsPersonnelTables
				.entrySet()) {
			PersonnelDetailDataVo personnelDetailDataVo = new PersonnelDetailDataVo();
			personnelDetailDataVo.setName(BaseInfoTables
					.getTypeDisplayNames(entry.getKey()));
			personnelDetailDataVo
					.setAmount(getImportantPersonelCountByOrgInternalCode(
							orgInternalCode, entry.getValue()));
			if (personnelDetailDataVo.getAmount() > 0) {
				personnelDetailDataVo.setShowLink(true);
			}
			amountSum += personnelDetailDataVo.getAmount();

			personnelDetailDataVosTemp.add(personnelDetailDataVo);
		}

		for (PersonnelDetailDataVo personnelDetailDataVo : personnelDetailDataVosTemp) {
			if (amountSum == 0) {
				personnelDetailDataVo.setAmountPercentage(Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(0)));
			} else {
				personnelDetailDataVo.setAmountPercentage(Double
						.parseDouble(new java.text.DecimalFormat("#.0000")
								.format(personnelDetailDataVo.getAmount()
										/ (double) amountSum)));

			}

			personnelDetailDataVos.add(personnelDetailDataVo);
		}

		PersonnelDetailDataVo personnelDetailDataVoSum = new PersonnelDetailDataVo();
		personnelDetailDataVoSum.setName("合计");
		personnelDetailDataVoSum.setAmount(amountSum);
		if (amountSum == 0) {
			personnelDetailDataVoSum.setAmountPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.0000")
							.format(0)));
		} else {
			personnelDetailDataVoSum.setAmountPercentage(Double
					.parseDouble(new java.text.DecimalFormat("#.0000")
							.format(1)));

		}
		personnelDetailDataVos.add(personnelDetailDataVoSum);

		return personnelDetailDataVos;
	}

	private int getImportantPersonelCountByOrgInternalCode(
			String orgInternalCode, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		return statisticsBaseInfoDao
				.getCountByOrgInternalCodeAndTableNameAndMap(orgInternalCode,
						tableName, map);
	}

	private PersonnelAreaDataVo getPositiveinfoAreaDataVoByOrgId(
			Organization organization) {
		PersonnelAreaDataVo personnelAreaDataVo = new PersonnelAreaDataVo();
		try {
			personnelAreaDataVo.setOrg(organization);
			personnelAreaDataVo
					.setAmount(getPositiveinfoAmountByOrgInternalCode(organization
							.getOrgInternalCode()));
			personnelAreaDataVo
					.setPersonnelDetailDatas(getPositiveinfoDetailDatasByOrgInternalCode(organization
							.getOrgInternalCode()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personnelAreaDataVo;
	}

	private List<PersonnelDetailDataVo> getPositiveinfoDetailDatasByOrgInternalCode(
			String orgInternalCode) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.POSITIVEINFO);
		PersonnelDetailDataVo positiveinfoDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;
		int noResiteSum = 0;
		int resiteSum = 0;
		int recidivism = 0;

		for (PropertyDict propertyDict : propertyDicts) {
			PersonnelDetailDataVo personnelDetailDataVo = new PersonnelDetailDataVo();
			personnelDetailDataVo.setName(propertyDict.getDisplayName());
			personnelDetailDataVo
					.setAmount(getPositiveinfoCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setHelped(getHelpedPositiveinfoCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setNoHelp(getNoHelpPositiveinfoCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setNoResite(getNoResitePositiveinfoCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setResited(getResitedPositiveinfoCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setRecidivism(getRecidivismPositiveinfoCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			PersonnelDetailDataVos.add(personnelDetailDataVo);

			amountSum += personnelDetailDataVo.getAmount();
			helpedSum += personnelDetailDataVo.getHelped();
			noHelpedSum += personnelDetailDataVo.getNoHelp();
			noResiteSum += personnelDetailDataVo.getNoResite();
			resiteSum += personnelDetailDataVo.getResited();
			recidivism += personnelDetailDataVo.getRecidivism();

		}

		positiveinfoDetailDataVoSum.setName("合计");
		positiveinfoDetailDataVoSum.setAmount(amountSum);
		positiveinfoDetailDataVoSum.setHelped(helpedSum);
		positiveinfoDetailDataVoSum.setNoHelp(noHelpedSum);
		positiveinfoDetailDataVoSum.setResited(resiteSum);
		positiveinfoDetailDataVoSum.setNoResite(noResiteSum);
		positiveinfoDetailDataVoSum.setRecidivism(recidivism);

		PersonnelDetailDataVos.add(positiveinfoDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private int getRecidivismPositiveinfoCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("positiveInfoType", type);
		}
		map.put("recidivism", 1L);
		map.put("orgInternalCode", orgInternalCode);
		List<PositiveInfo> list = searchPositiveInfosService
				.getPositiveInfoByOrgInternalCodeAndMap(map);
		return createRecidivism(list);
	}

	private int createRecidivism(List<PositiveInfo> list) {
		for (int i = 0; i < list.size(); i++) {
			PositiveInfo positiveInfos = list.get(i);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String nowTime = sdf.format(positiveInfos.getCrimeDate());
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			if (!Integer.valueOf(nowTime.split("-")[0]).equals(year)) {
				list.remove(i);
			}
		}
		return list.size();
	}

	private int getResitedPositiveinfoCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("positiveInfoType", type);
		}
		map.put("isResettlementdate", 1L);
		return searchPositiveInfosService
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	private int getNoResitePositiveinfoCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("positiveInfoType", type);
		}
		map.put("isResettlementdate", 0L);
		return searchPositiveInfosService
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	private int getNoHelpPositiveinfoCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("positiveInfoType", type);
		}
		map.put("isHelped", 0L);
		return searchPositiveInfosService
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	private int getHelpedPositiveinfoCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("positiveInfoType", type);
		}
		map.put("isHelped", 1L);
		return searchPositiveInfosService
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	private int getPositiveinfoCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("positiveInfoType", type);
		return searchPositiveInfosService
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	private int getPositiveinfoAmountByOrgInternalCode(String orgInternalCode) {
		return searchPositiveInfosService
				.getCountPositiveInfoByOrgInternalCodeAndMap(orgInternalCode,
						new HashMap<String, Object>());
	}

	@Override
	public List<PersonnelAreaDataVo> getIdleyouthAreaDataByOrgId(Long orgId) {
		int sum = 0;
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> personnelAreaDataVos = new ArrayList<PersonnelAreaDataVo>();

		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		for (Organization organization : organizations) {
			int count = getIdleYouthAmountByOrgInternalCode(organization
					.getOrgInternalCode());
			PersonnelAreaDataVo personnelAreaDataVo = getIdleYouthDataVoByOrgId(organization);

			if (count != 0) {
				personnelAreaDataVo.setAmount(count);
				sum += count;
			}
			personnelAreaDataVos.add(personnelAreaDataVo);
		}
		personnelAreaDataVos.add(lastRowIdleyouth("合计", sum,
				personnelAreaDataVos));
		return personnelAreaDataVos;
	}

	private PersonnelAreaDataVo lastRowIdleyouth(String string, int sum,
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		PersonnelAreaDataVo vo = new PersonnelAreaDataVo();
		PersonnelDetailDataVo result = new PersonnelDetailDataVo();
		result.setAmount(sum);
		Organization organization = new Organization();
		organization.setOrgName(string);
		vo.setOrg(organization);
		vo.setAmount(sum);

		vo.setPersonnelDetailDatas(getIdleyouthAreaDataVoTypeCount(personnelAreaDataVos));

		return vo;
	}

	private List<PersonnelDetailDataVo> getIdleyouthAreaDataVoTypeCount(
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo IdleyouthDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;

		for (PersonnelAreaDataVo domain : personnelAreaDataVos) {
			List<PersonnelDetailDataVo> personnelDetailDatas = domain
					.getPersonnelDetailDatas();
			for (PersonnelDetailDataVo result : personnelDetailDatas) {
				if (result.getName().equals("合计")) {
					amountSum += result.getAmount();
					helpedSum += result.getHelped();
					noHelpedSum += result.getNoHelp();

				}
			}
		}

		IdleyouthDetailDataVoSum.setName("/");
		IdleyouthDetailDataVoSum.setAmount(amountSum);
		IdleyouthDetailDataVoSum.setHelped(helpedSum);
		IdleyouthDetailDataVoSum.setNoHelp(noHelpedSum);

		PersonnelDetailDataVos.add(IdleyouthDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private PersonnelAreaDataVo getIdleYouthDataVoByOrgId(
			Organization organization) {
		PersonnelAreaDataVo PersonnelAreaDataVo = new PersonnelAreaDataVo();
		PersonnelAreaDataVo.setOrg(organization);
		PersonnelAreaDataVo
				.setAmount(getIdleYouthAmountByOrgInternalCode(organization
						.getOrgInternalCode()));
		PersonnelAreaDataVo
				.setPersonnelDetailDatas(getIdleYouthDetailDatasByOrgInternalCode(organization
						.getOrgInternalCode()));
		return PersonnelAreaDataVo;
	}

	private List<PersonnelDetailDataVo> getIdleYouthDetailDatasByOrgInternalCode(
			String orgInternalCode) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.IDLEYOUTH_STAFF_TYPE);
		PersonnelDetailDataVo personnelDetailDataVoSum = new PersonnelDetailDataVo();
		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;

		for (PropertyDict propertyDict : propertyDicts) {
			PersonnelDetailDataVo personnelDetailDataVo = new PersonnelDetailDataVo();
			personnelDetailDataVo.setName(propertyDict.getDisplayName());
			personnelDetailDataVo
					.setAmount(getIdleYouthCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setHelped(getHelpedIdleYouthCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setNoHelp(getNoHelpIdleYouthCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			PersonnelDetailDataVos.add(personnelDetailDataVo);

			amountSum += personnelDetailDataVo.getAmount();
			helpedSum += personnelDetailDataVo.getHelped();
			noHelpedSum += personnelDetailDataVo.getNoHelp();
		}

		personnelDetailDataVoSum.setName("合计");
		personnelDetailDataVoSum.setAmount(amountSum);
		personnelDetailDataVoSum.setHelped(helpedSum);
		personnelDetailDataVoSum.setNoHelp(noHelpedSum);

		PersonnelDetailDataVos.add(personnelDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private int getNoHelpIdleYouthCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("staffType", type);
		}
		map.put("isHelped", 0L);
		return searchIdleYouthDao.getCountIdleyouthByOrgInternalCodeAndMap(
				orgInternalCode, map);
	}

	private int getHelpedIdleYouthCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("staffType", type);
		}
		map.put("isHelped", 1L);
		return searchIdleYouthDao.getCountIdleyouthByOrgInternalCodeAndMap(
				orgInternalCode, map);
	}

	private int getIdleYouthCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("staffType", type);
		return searchIdleYouthDao.getCountIdleyouthByOrgInternalCodeAndMap(
				orgInternalCode, map);
	}

	private int getIdleYouthAmountByOrgInternalCode(String orgInternalCode) {
		return searchIdleYouthDao.getCountIdleyouthByOrgInternalCodeAndMap(
				orgInternalCode, new HashMap<String, Object>());
	}

	@Override
	public HighchartColumnVo getPositiveinfoColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.POSITIVEINFO_KEY));
		highchartColumn.setSeries(getPositiveinfoColumnDataByOrgId(orgId));
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		return highchartColumn;
	}

	@Override
	public HighchartColumnVo getImportantPersonlColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.IMPORTANTPERSONNEL_KEY));
		highchartColumn.setSeries(getImportantPersonlColumnDataByOrgId(orgId));
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		return highchartColumn;
	}

	private String[] getOrgArraysByParentId(Long orgId) {
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		String[] orgNames = new String[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			orgNames[i] = organizations.get(i).getOrgName();
		}
		return orgNames;
	}

	private List<HighchartDataColumnVo> getPositiveinfoColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		positiveinfoColumns
				.addAll(getPositiveinfoTypeColumnDataByOrgs(organizations));
		positiveinfoColumns
				.addAll(getPositiveinfoHelpColumnDataByOrgs(organizations));
		positiveinfoColumns
				.addAll(getPositiveinfoResiteColumnDataByOrgs(organizations));
		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getImportantPersonlColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		positiveinfoColumns
				.addAll(getImportantPersonlColumnDataByOrgs(organizations));
		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getImportantPersonlColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		int[] data = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			data[i] = getImportantPersonelCountSumByOrgInternalCode(organizations
					.get(i).getOrgInternalCode());
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData
					.setName(BaseInfoTables
							.getTypeDisplayNames(BaseInfoTables.IMPORTANTPERSONNEL_KEY));
		}
		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
	}

	private int getImportantPersonelCountSumByOrgInternalCode(
			String orgInternalCode) {
		int sum = 0;
		for (Entry<String, String> entry : BaseInfoTables.importantsPersonnelTables
				.entrySet()) {
			sum += getImportantPersonelCountByOrgInternalCode(orgInternalCode,
					entry.getValue());
		}
		return sum;
	}

	private List<HighchartDataColumnVo> getPositiveinfoResiteColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnDataNoResite = new HighchartDataColumnVo();
		positiveinfoColumnDataNoResite.setName("已安置");
		int[] dataNoResite = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataNoResite[i] = getResitedPositiveinfoCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		positiveinfoColumnDataNoResite.setData(dataNoResite);
		positiveinfoColumnDataNoResite.setStack("是否安置");
		positiveinfoColumns.add(positiveinfoColumnDataNoResite);

		HighchartDataColumnVo positiveinfoColumnDataResite = new HighchartDataColumnVo();
		positiveinfoColumnDataResite.setName("未安置");
		int[] dataResite = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataResite[i] = getNoResitePositiveinfoCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		positiveinfoColumnDataResite.setData(dataResite);
		positiveinfoColumnDataResite.setStack("是否安置");
		positiveinfoColumns.add(positiveinfoColumnDataResite);
		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getPositiveinfoHelpColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnDataHelp = new HighchartDataColumnVo();
		positiveinfoColumnDataHelp.setName("已帮教");
		int[] dataHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataHelp[i] = getHelpedPositiveinfoCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		positiveinfoColumnDataHelp.setData(dataHelp);
		positiveinfoColumnDataHelp.setStack("是否帮教");
		positiveinfoColumns.add(positiveinfoColumnDataHelp);

		HighchartDataColumnVo positiveinfoColumnDataNoHelp = new HighchartDataColumnVo();
		positiveinfoColumnDataNoHelp.setName("未帮教");
		int[] dataNoHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataNoHelp[i] = getNoHelpPositiveinfoCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		positiveinfoColumnDataNoHelp.setData(dataNoHelp);
		positiveinfoColumnDataNoHelp.setStack("是否帮教");
		positiveinfoColumns.add(positiveinfoColumnDataNoHelp);

		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getPositiveinfoTypeColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.POSITIVEINFO);
		for (PropertyDict propertyDict : propertyDicts) {
			HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
			positiveinfoColumnData.setName(propertyDict.getDisplayName());

			int[] data = new int[organizations.size()];
			for (int i = 0; i < organizations.size(); i++) {
				data[i] = getPositiveinfoCountByOrgInternalCodeAndType(
						organizations.get(i).getOrgInternalCode(),
						propertyDict.getId());
			}
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setStack(PropertyTypes.POSITIVEINFO);
			positiveinfoColumns.add(positiveinfoColumnData);
		}
		return positiveinfoColumns;
	}

	@Override
	public HighchartColumnVo getIdleyouthColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo idleyouthColumn = new HighchartColumnVo();
		idleyouthColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.IDLEYOUTH_KEY));
		idleyouthColumn.setCategories(getOrgArraysByParentId(orgId));
		idleyouthColumn.setSeries(getIdleyouthColumnDataByOrgId(orgId));
		return idleyouthColumn;
	}

	private List<HighchartDataColumnVo> getIdleyouthColumnDataByOrgId(Long orgId) {
		List<HighchartDataColumnVo> idleyouthColumnDatas = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		HighchartDataColumnVo idleyouthColumnDataHelp = new HighchartDataColumnVo();
		idleyouthColumnDataHelp.setName("监护");
		int[] dataHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataHelp[i] = getHelpedIdleYouthCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		idleyouthColumnDataHelp.setData(dataHelp);
		idleyouthColumnDatas.add(idleyouthColumnDataHelp);

		HighchartDataColumnVo idleyouthColumnDataNoHelp = new HighchartDataColumnVo();
		idleyouthColumnDataNoHelp.setName("未监护");
		int[] dataNoHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataNoHelp[i] = getNoHelpIdleYouthCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		idleyouthColumnDataNoHelp.setData(dataNoHelp);
		idleyouthColumnDatas.add(idleyouthColumnDataNoHelp);

		return idleyouthColumnDatas;
	}

	@Override
	public List<Object[]> getPositiveInfoPieByOrgId(Long orgId) {
		List<Object[]> positiveInfoPieDatas = new ArrayList<Object[]>();

		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.POSITIVEINFO);
		double sum = getPositiveinfoCountByOrgInternalCodeAndType(
				organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode(), null);

		for (PropertyDict propertyDict : propertyDicts) {
			Object[] positiveInfoPieData = new Object[2];

			double positiveinfoCount = getPositiveinfoCountByOrgInternalCodeAndType(
					organizationDubboService.getSimpleOrgById(orgId)
							.getOrgInternalCode(), propertyDict.getId());
			if (sum == 0) {
				positiveInfoPieData[1] = 0;
			} else {
				positiveInfoPieData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(positiveinfoCount / sum * 100));
			}

			positiveInfoPieData[0] = propertyDict.getDisplayName()
					+ "( "
					+ new java.text.DecimalFormat("#")
							.format(positiveinfoCount) + " )";
			positiveInfoPieDatas.add(positiveInfoPieData);
		}
		return positiveInfoPieDatas;
	}

	@Override
	public List<Object[]> getIdleyouthPieByOrgId(Long orgId) {
		List<Object[]> idleyouthPieDatas = new ArrayList<Object[]>();
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.IDLEYOUTH_STAFF_TYPE);

		double sum = getIdleYouthCountByOrgInternalCodeAndType(
				organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode(), null);

		for (PropertyDict propertyDict : propertyDicts) {
			Object[] idleyouthPieData = new Object[2];

			double idleyouthCount = getIdleYouthCountByOrgInternalCodeAndType(
					organizationDubboService.getSimpleOrgById(orgId)
							.getOrgInternalCode(), propertyDict.getId());
			if (sum == 0) {
				idleyouthPieData[1] = 0;
			} else {
				idleyouthPieData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(idleyouthCount / sum * 100));
			}
			idleyouthPieData[0] = propertyDict.getDisplayName() + "( "
					+ new java.text.DecimalFormat("#").format(idleyouthCount)
					+ " )";
			idleyouthPieDatas.add(idleyouthPieData);
		}
		return idleyouthPieDatas;
	}

	@Override
	public List<Object[]> getImportantPersonlPieByOrgId(Long orgId) {
		List<Object[]> importantPersonlPieDatas = new ArrayList<Object[]>();
		double sum = getImportantPersonelCountSumByOrgInternalCode(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode());
		for (Entry<String, String> entry : BaseInfoTables.importantsPersonnelTables
				.entrySet()) {
			Object[] importantPersonlData = new Object[2];

			double importantPersonlCount = getImportantPersonelCountByOrgInternalCode(
					organizationDubboService.getSimpleOrgById(orgId)
							.getOrgInternalCode(), entry.getValue());
			if (sum == 0) {
				importantPersonlData[1] = 0;
			} else {
				importantPersonlData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(importantPersonlCount / sum * 100));
			}
			importantPersonlData[0] = BaseInfoTables.getTypeDisplayNames(entry
					.getKey())
					+ "( "
					+ new java.text.DecimalFormat("#")
							.format(importantPersonlCount) + " )";
			importantPersonlPieDatas.add(importantPersonlData);
		}
		return importantPersonlPieDatas;
	}

	@Override
	public HighchartColumnVo getSuperiorVisitColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.SUPERIORVISIT_KEY));
		highchartColumn.setSeries(getSuperiorVisitColumnDataByOrgId(orgId));
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		return highchartColumn;
	}

	private List<HighchartDataColumnVo> getSuperiorVisitColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		positiveinfoColumns
				.addAll(getSuperiorVisitHelpColumnDataByOrgs(organizations));
		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getSuperiorVisitHelpColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo superiorVisitColumnDataHelp = new HighchartDataColumnVo();
		superiorVisitColumnDataHelp.setName("已落实综治人");
		int[] dataHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataHelp[i] = getHelpedSuperiorVisitCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		superiorVisitColumnDataHelp.setData(dataHelp);
		superiorVisitColumnDataHelp.setStack("是否落实综治人");
		positiveinfoColumns.add(superiorVisitColumnDataHelp);

		HighchartDataColumnVo superiorVisitColumnDataNoHelp = new HighchartDataColumnVo();
		superiorVisitColumnDataNoHelp.setName("未落实综治人");
		int[] dataNoHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataNoHelp[i] = getNoHelpSuperiorVisitCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		superiorVisitColumnDataNoHelp.setData(dataNoHelp);
		superiorVisitColumnDataNoHelp.setStack("是否落实综治人");
		positiveinfoColumns.add(superiorVisitColumnDataNoHelp);
		return positiveinfoColumns;
	}

	private int getNoHelpSuperiorVisitCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("superiorVisits", type);
		}
		map.put("isHelped", 0L);
		return searchSuperiorVisitDao
				.getCountSuperiorVisitByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	private int getHelpedSuperiorVisitCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("superiorVisits", type);
		}
		map.put("isHelped", 1L);
		return searchSuperiorVisitDao
				.getCountSuperiorVisitByOrgInternalCodeAndMap(orgInternalCode,
						map);
	}

	@Override
	public List<PersonnelAreaDataVo> findStatAnalyseSuperiorVisit(Long orgId) {
		int sum = 0;
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> personnelAreaDataVos = new ArrayList<PersonnelAreaDataVo>();

		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		for (Organization organization : organizations) {

			PersonnelAreaDataVo personnelAreaDataVo = getSuperiorVisitAreaDataVoByOrgId(organization);
			int count = searchSuperiorVisitDao
					.getCountSuperiorVisitByOrgInternalCodeAndMap(
							organization.getOrgInternalCode(),
							new HashMap<String, Object>());
			if (count != 0) {
				personnelAreaDataVo.setAmount(count);
				sum += count;
			}
			personnelAreaDataVos.add(personnelAreaDataVo);
		}
		personnelAreaDataVos.add(lastSuperiorVisitRow("合计", sum,
				personnelAreaDataVos));
		return personnelAreaDataVos;
	}

	private PersonnelAreaDataVo lastSuperiorVisitRow(String string, int sum,
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		PersonnelAreaDataVo vo = new PersonnelAreaDataVo();
		PersonnelDetailDataVo result = new PersonnelDetailDataVo();
		result.setAmount(sum);
		Organization organization = new Organization();
		organization.setOrgName(string);
		vo.setOrg(organization);
		vo.setAmount(sum);

		vo.setPersonnelDetailDatas(getSuperiorVisitAreaDataVoTypeCount(personnelAreaDataVos));

		return vo;
	}

	private List<PersonnelDetailDataVo> getSuperiorVisitAreaDataVoTypeCount(
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo mentalPatientDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;

		for (PersonnelAreaDataVo domain : personnelAreaDataVos) {
			List<PersonnelDetailDataVo> personnelDetailDatas = domain
					.getPersonnelDetailDatas();
			for (PersonnelDetailDataVo result : personnelDetailDatas) {
				if (result.getName().equals("合计")) {
					amountSum += result.getAmount();
					helpedSum += result.getHelped();
					noHelpedSum += result.getNoHelp();
				}
			}
		}

		mentalPatientDetailDataVoSum.setName("/");
		mentalPatientDetailDataVoSum.setAmount(amountSum);
		mentalPatientDetailDataVoSum.setHelped(helpedSum);
		mentalPatientDetailDataVoSum.setNoHelp(noHelpedSum);

		PersonnelDetailDataVos.add(mentalPatientDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private PersonnelAreaDataVo getSuperiorVisitAreaDataVoByOrgId(
			Organization organization) {
		PersonnelAreaDataVo personnelAreaDataVo = new PersonnelAreaDataVo();
		personnelAreaDataVo.setOrg(organization);
		personnelAreaDataVo.setAmount(searchSuperiorVisitDao
				.getCountSuperiorVisitByOrgInternalCodeAndMap(
						organization.getOrgInternalCode(),
						new HashMap<String, Object>()));
		personnelAreaDataVo
				.setPersonnelDetailDatas(getSuperiorVisitDetailDatasByOrgInternalCode(organization
						.getOrgInternalCode()));
		return personnelAreaDataVo;
	}

	private List<PersonnelDetailDataVo> getSuperiorVisitDetailDatasByOrgInternalCode(
			String orgInternalCode) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo superiorVisitDetailDataVoSum = new PersonnelDetailDataVo();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isHelped", 1);
		superiorVisitDetailDataVoSum.setName("合计");
		superiorVisitDetailDataVoSum.setHelped(searchSuperiorVisitDao
				.getCountSuperiorVisitByOrgInternalCodeAndMap(orgInternalCode,
						map));
		map.put("isHelped", 0);
		superiorVisitDetailDataVoSum.setNoHelp(searchSuperiorVisitDao
				.getCountSuperiorVisitByOrgInternalCodeAndMap(orgInternalCode,
						map));
		superiorVisitDetailDataVoSum.setAmount(searchSuperiorVisitDao
				.getCountSuperiorVisitByOrgInternalCodeAndMap(orgInternalCode,
						new HashMap<String, Object>()));
		PersonnelDetailDataVos.add(superiorVisitDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private PersonnelAreaDataVo lastPoorPersonRow(String string, int sum,
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		PersonnelAreaDataVo vo = new PersonnelAreaDataVo();
		PersonnelDetailDataVo result = new PersonnelDetailDataVo();
		result.setAmount(sum);
		Organization organization = new Organization();
		organization.setOrgName(string);
		vo.setOrg(organization);
		vo.setAmount(sum);

		vo.setPersonnelDetailDatas(getPoorpersonAreaDataVoTypeCount(personnelAreaDataVos));
		return vo;
	}

	private List<PersonnelDetailDataVo> getPoorpersonAreaDataVoTypeCount(
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo poorPatientDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;

		for (PersonnelAreaDataVo domain : personnelAreaDataVos) {
			List<PersonnelDetailDataVo> personnelDetailDatas = domain
					.getPersonnelDetailDatas();
			for (PersonnelDetailDataVo result : personnelDetailDatas) {
				if (result.getName().equals("合计")) {
					amountSum += result.getAmount();
					helpedSum += result.getHelped();
					noHelpedSum += result.getNoHelp();

				}
			}
		}

		poorPatientDetailDataVoSum.setName("/");
		poorPatientDetailDataVoSum.setAmount(amountSum);
		poorPatientDetailDataVoSum.setHelped(helpedSum);
		poorPatientDetailDataVoSum.setNoHelp(noHelpedSum);

		PersonnelDetailDataVos.add(poorPatientDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	@Override
	public HighchartColumnVo getDangerousGoodsColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn
				.setModuleName(BaseInfoTables
						.getTypeDisplayNames(BaseInfoTables.DANGEROUSGOODSPRACTITIONER_KEY));
		highchartColumn.setSeries(getDangerousGoodsColumnDataByOrgId(orgId));
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		return highchartColumn;
	}

	private List<HighchartDataColumnVo> getDangerousGoodsColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		positiveinfoColumns
				.addAll(getDangerousGoodsHelpColumnDataByOrgs(organizations));
		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getDangerousGoodsHelpColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> poorPeopleColumns = new ArrayList<HighchartDataColumnVo>();
		HighchartDataColumnVo positiveinfoColumnDataHelp = new HighchartDataColumnVo();
		positiveinfoColumnDataHelp.setName("已落实综治人");
		int[] dataHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataHelp[i] = getHelpedDangerousGoodsCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		positiveinfoColumnDataHelp.setData(dataHelp);
		positiveinfoColumnDataHelp.setStack("是否落实综治人");
		poorPeopleColumns.add(positiveinfoColumnDataHelp);

		HighchartDataColumnVo poorPeopleColumnDataNoHelp = new HighchartDataColumnVo();
		poorPeopleColumnDataNoHelp.setName("未落实综治人");
		int[] dataNoHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataNoHelp[i] = getNoDangerousGoodsCountByOrgInternalCodeAndType(
					organizations.get(i).getOrgInternalCode(), null);
		}
		poorPeopleColumnDataNoHelp.setData(dataNoHelp);
		poorPeopleColumnDataNoHelp.setStack("是否落实综治人");
		poorPeopleColumns.add(poorPeopleColumnDataNoHelp);
		return poorPeopleColumns;
	}

	private int getHelpedDangerousGoodsCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("dangerousWorkingType", type);
		}
		map.put("isHelped", 1L);
		return searchDangerousGoodsPractitionerDao
				.getCountDangerousGoodsPractitionerByOrgInternalCodeAndMap(
						orgInternalCode, map);
	}

	private int getNoDangerousGoodsCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (type != null && type.longValue() != 0L) {
			map.put("dangerousWorkingType", type);
		}
		map.put("isHelped", 0L);
		return searchDangerousGoodsPractitionerDao
				.getCountDangerousGoodsPractitionerByOrgInternalCodeAndMap(
						orgInternalCode, map);
	}

	@Override
	public List<Object[]> getDangerousGoodsPractitionerPieByOrgId(Long orgId) {
		List<Object[]> poorPeoplePieDatas = new ArrayList<Object[]>();

		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DANGEROUS_WORKING_TYPE);
		double sum = getDangerousGoodsPractitionerCountByOrgInternalCodeAndType(
				organizationDubboService.getSimpleOrgById(orgId)
						.getOrgInternalCode(), null);

		for (PropertyDict propertyDict : propertyDicts) {
			Object[] poorPeoplePieData = new Object[2];

			double positiveinfoCount = getDangerousGoodsPractitionerCountByOrgInternalCodeAndType(
					organizationDubboService.getSimpleOrgById(orgId)
							.getOrgInternalCode(), propertyDict.getId());
			if (sum == 0) {
				poorPeoplePieData[1] = 0;
			} else {
				poorPeoplePieData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(positiveinfoCount / sum * 100));
			}
			poorPeoplePieData[0] = propertyDict.getDisplayName()
					+ "( "
					+ new java.text.DecimalFormat("#")
							.format(positiveinfoCount) + " )";
			poorPeoplePieDatas.add(poorPeoplePieData);
		}
		return poorPeoplePieDatas;
	}

	private int getDangerousGoodsPractitionerCountByOrgInternalCodeAndType(
			String orgInternalCode, Long type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dangerousWorkingType", type);
		return searchDangerousGoodsPractitionerDao
				.getCountDangerousGoodsPractitionerByOrgInternalCodeAndMap(
						orgInternalCode, map);
	}

	@Override
	public List<PersonnelAreaDataVo> getDangerousGoodsPractitionerAreaDataByOrgId(
			Long orgId) {
		int sum = 0;
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> personnelAreaDataVos = new ArrayList<PersonnelAreaDataVo>();

		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		for (Organization organization : organizations) {
			PersonnelAreaDataVo personnelAreaDataVo = getDangerousGoodsPractitionerAreaDataVoByOrgId(organization);
			int count = getDangerousGoodsPractitionerAmountByOrgInternalCode(organization
					.getOrgInternalCode());
			if (count != 0) {
				personnelAreaDataVo.setAmount(count);
				sum += count;
			}
			personnelAreaDataVos.add(personnelAreaDataVo);
		}
		personnelAreaDataVos.add(lastDangerousGoodsRow("合计", sum,
				personnelAreaDataVos));
		return personnelAreaDataVos;
	}

	private PersonnelAreaDataVo lastDangerousGoodsRow(String string, int sum,
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		PersonnelAreaDataVo vo = new PersonnelAreaDataVo();
		PersonnelDetailDataVo result = new PersonnelDetailDataVo();
		result.setAmount(sum);
		Organization organization = new Organization();
		organization.setOrgName(string);
		vo.setOrg(organization);
		vo.setAmount(sum);

		vo.setPersonnelDetailDatas(getDangerousGoodsAreaDataVoTypeCount(personnelAreaDataVos));

		return vo;
	}

	private List<PersonnelDetailDataVo> getDangerousGoodsAreaDataVoTypeCount(
			List<PersonnelAreaDataVo> personnelAreaDataVos) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		PersonnelDetailDataVo DangerousGoodsPatientDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;

		for (PersonnelAreaDataVo domain : personnelAreaDataVos) {
			List<PersonnelDetailDataVo> personnelDetailDatas = domain
					.getPersonnelDetailDatas();
			for (PersonnelDetailDataVo result : personnelDetailDatas) {
				if (result.getName().equals("合计")) {
					amountSum += result.getAmount();
					helpedSum += result.getHelped();
					noHelpedSum += result.getNoHelp();

				}
			}
		}

		DangerousGoodsPatientDetailDataVoSum.setName("/");
		DangerousGoodsPatientDetailDataVoSum.setAmount(amountSum);
		DangerousGoodsPatientDetailDataVoSum.setHelped(helpedSum);
		DangerousGoodsPatientDetailDataVoSum.setNoHelp(noHelpedSum);

		PersonnelDetailDataVos.add(DangerousGoodsPatientDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	private PersonnelAreaDataVo getDangerousGoodsPractitionerAreaDataVoByOrgId(
			Organization organization) {
		PersonnelAreaDataVo personnelAreaDataVo = new PersonnelAreaDataVo();
		personnelAreaDataVo.setOrg(organization);
		personnelAreaDataVo
				.setAmount(getDangerousGoodsPractitionerAmountByOrgInternalCode(organization
						.getOrgInternalCode()));
		personnelAreaDataVo
				.setPersonnelDetailDatas(getDangerousGoodsPractitionerDetailDatasByOrgInternalCode(organization
						.getOrgInternalCode()));
		return personnelAreaDataVo;
	}

	private int getDangerousGoodsPractitionerAmountByOrgInternalCode(
			String orgInternalCode) {
		return searchDangerousGoodsPractitionerDao
				.getCountDangerousGoodsPractitionerByOrgInternalCodeAndMap(
						orgInternalCode, new HashMap<String, Object>());
	}

	private List<PersonnelDetailDataVo> getDangerousGoodsPractitionerDetailDatasByOrgInternalCode(
			String orgInternalCode) {
		List<PersonnelDetailDataVo> PersonnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByDomainName(PropertyTypes.DANGEROUS_WORKING_TYPE);
		PersonnelDetailDataVo dangerousGoodsPractitionerDetailDataVoSum = new PersonnelDetailDataVo();

		int amountSum = 0;
		int helpedSum = 0;
		int noHelpedSum = 0;
		for (PropertyDict propertyDict : propertyDicts) {
			PersonnelDetailDataVo personnelDetailDataVo = new PersonnelDetailDataVo();
			personnelDetailDataVo.setName(propertyDict.getDisplayName());
			personnelDetailDataVo
					.setAmount(getDangerousGoodsPractitionerCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setHelped(getHelpedDangerousGoodsCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			personnelDetailDataVo
					.setNoHelp(getNoDangerousGoodsCountByOrgInternalCodeAndType(
							orgInternalCode, propertyDict.getId()));
			PersonnelDetailDataVos.add(personnelDetailDataVo);

			amountSum += personnelDetailDataVo.getAmount();
			helpedSum += personnelDetailDataVo.getHelped();
			noHelpedSum += personnelDetailDataVo.getNoHelp();

		}
		dangerousGoodsPractitionerDetailDataVoSum.setName("合计");
		dangerousGoodsPractitionerDetailDataVoSum.setAmount(amountSum);
		dangerousGoodsPractitionerDetailDataVoSum.setHelped(helpedSum);
		dangerousGoodsPractitionerDetailDataVoSum.setNoHelp(noHelpedSum);
		PersonnelDetailDataVos.add(dangerousGoodsPractitionerDetailDataVoSum);
		return PersonnelDetailDataVos;
	}

	@Override
	public List<Object[]> getImportantPersonlPieByOrgIdAndMonth(Long orgId,
			String typeName, int year, int month) {
		List<Object[]> importantPersonlPieDatas = new ArrayList<Object[]>();
		List<Map<String, Object>> maps = baseInfoStatTypeDao
				.findBaseInfoStatTypeForOrg(organizationDubboService
						.getSimpleOrgById(orgId).getOrgInternalCode(),
						typeName, year, month);
		double sum = 0L;
		for (int i = 0; i < maps.size(); i++) {
			BigDecimal total = (BigDecimal) maps.get(i).get("TOTAL");
			sum += total.doubleValue();
		}
		for (int i = 0; i < maps.size(); i++) {
			BigDecimal total = (BigDecimal) maps.get(i).get("TOTAL");
			Object[] importantPersonlData = new Object[2];
			if (sum == 0) {
				importantPersonlData[1] = 0;
			} else {
				importantPersonlData[1] = Double
						.parseDouble(new java.text.DecimalFormat("#.00")
								.format(total.doubleValue() / sum * 100));
			}
			importantPersonlData[0] = (String) maps.get(i).get("TYPENAME")
					+ "( "
					+ new java.text.DecimalFormat("#").format(total
							.doubleValue()) + " )";
			importantPersonlPieDatas.add(importantPersonlData);
		}
		return importantPersonlPieDatas;
	}

	public List<PersonnelAreaDataVo> getImportantPersonelAreaDataByOrgIdAndMonth(
			Long orgId, String typeName, int year, int month) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> personnelAreaDataVos = new ArrayList<PersonnelAreaDataVo>();

		List<Organization> orgainizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		for (Organization organization : orgainizations) {
			personnelAreaDataVos
					.add(getImportantPersonelAreaDataVoByOrgIdAndMonth(
							organization, typeName, year, month));
		}
		return personnelAreaDataVos;
	}

	private PersonnelAreaDataVo getImportantPersonelAreaDataVoByOrgIdAndMonth(
			Organization organization, String typeName, int year, int month) {
		PersonnelAreaDataVo personnelAreaDataVo = new PersonnelAreaDataVo();
		List<Map<String, Object>> maps = baseInfoStatTypeDao
				.findBaseInfoStatTypeForOrg(organization.getOrgInternalCode(),
						typeName, year, month);
		if (maps.size() == 0 || maps.isEmpty()) {
			return personnelAreaDataVo;
		}
		personnelAreaDataVo.setOrg(organization);
		personnelAreaDataVo
				.setPersonnelDetailDatas(getImportantPersonelDetailDatasByOrgInternalCodeAndMonth(
						maps, typeName, year, month,
						organization.getOrgInternalCode()));
		personnelAreaDataVo.setAmount(getBaseinfoStatSum(
				organization.getOrgInternalCode(), typeName, year, month));
		return personnelAreaDataVo;
	}

	private int getBaseinfoStatSum(String orgInternalCode, String typeName,
			int year, int month) {
		List<Map<String, Object>> maps = baseInfoStatTypeDao
				.findBaseInfoStatTypeForOrg(orgInternalCode, typeName, year,
						month);
		int sum = 0;
		for (int i = 0; i < maps.size(); i++) {
			BigDecimal total = (BigDecimal) maps.get(i).get("TOTAL");
			sum += total.doubleValue();
		}
		return sum;
	}

	private List<PersonnelDetailDataVo> getImportantPersonelDetailDatasByOrgInternalCodeAndMonth(
			List<Map<String, Object>> maps, String typeName, int year,
			int month, String orgInternalCode) {
		int helpSum = 0;
		int nohelpSum = 0;
		int resitedSum = 0;
		int recidivismSum = 0;
		int noresitedSum = 0;
		List<PersonnelDetailDataVo> personnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		int sum = getBaseinfoStatSum(orgInternalCode, typeName, year, month);
		PersonnelDetailDataVo personnelDetailDataVo = null;
		for (int i = 0; i < maps.size(); i++) {
			personnelDetailDataVo = new PersonnelDetailDataVo();
			BigDecimal total = (BigDecimal) maps.get(i).get("TOTAL");
			BigDecimal helped = (BigDecimal) maps.get(i).get("HELP");
			BigDecimal noHelp = (BigDecimal) maps.get(i).get("NOTHELP");
			BigDecimal resited = (BigDecimal) maps.get(i).get("RESITED");
			BigDecimal recidivism = (BigDecimal) maps.get(i).get("RECIDIVISM");
			personnelDetailDataVo.setName((String) maps.get(i).get("TYPENAME"));
			helpSum += helped.intValue();
			nohelpSum += noHelp.intValue();
			recidivismSum += recidivism.intValue();
			resitedSum += recidivism.intValue();
			noresitedSum += (total.intValue() - resited.intValue());
			personnelDetailDataVo.setAmount(total.intValue());
			personnelDetailDataVo.setHelped(helped.intValue());
			personnelDetailDataVo.setNoHelp(noHelp.intValue());
			personnelDetailDataVo.setResited(resited.intValue());
			personnelDetailDataVo.setNoResite(total.intValue()
					- resited.intValue());
			personnelDetailDataVo.setRecidivism(recidivism.intValue());
			personnelDetailDataVos.add(personnelDetailDataVo);
			personnelDetailDataVo
					.setAmountPercentage((total.doubleValue() / (double) sum));

		}
		if (!typeName.equals(BaseInfoTables.importantsPersonnelTables
				.get(BaseInfoTables.SUPERIORVISIT_KEY)) && maps.size() > 0) {
			personnelDetailDataVo = new PersonnelDetailDataVo();
			personnelDetailDataVo.setName("合计");
			personnelDetailDataVo.setAmount(sum);
			personnelDetailDataVo.setHelped(helpSum);
			personnelDetailDataVo.setNoHelp(nohelpSum);
			personnelDetailDataVo.setResited(resitedSum);
			personnelDetailDataVo.setNoResite(noresitedSum);
			personnelDetailDataVo.setRecidivism(recidivismSum);
			personnelDetailDataVos.add(personnelDetailDataVo);
			if (sum == 0) {
				personnelDetailDataVo.setAmountPercentage(1.00);
			} else {
				personnelDetailDataVo.setAmountPercentage(sum / sum);
			}
		}
		return personnelDetailDataVos;
	}

	@Override
	public List<StatAnalysePlaceVo> getImportantPlAreaDataByOrgIdAndMonth(
			Long orgId, String typeName, int year, int month) {
		List<StatAnalysePlaceVo> list = new ArrayList<StatAnalysePlaceVo>();
		List<Organization> orgList = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		for (int i = 0; i < orgList.size(); i++) {
			list.add(createAnalysePlaceVo(orgList.get(i), typeName, year, month));
		}
		return list;
	}

	private StatAnalysePlaceVo createAnalysePlaceVo(Organization organization,
			String typeName, int year, int month) {
		StatAnalysePlaceVo statAnalysePlaceVo = new StatAnalysePlaceVo();
		List<Map<String, Object>> maps = baseInfoStatTypeDao
				.findBaseInfoStatTypeForOrg(organization.getOrgInternalCode(),
						typeName, year, month);
		int total = 0;
		int helped = 0;
		for (int i = 0; i < maps.size(); i++) {
			BigDecimal helpe = (BigDecimal) maps.get(i).get("HELP");
			BigDecimal count = (BigDecimal) maps.get(i).get("TOTAL");
			total += count.intValue();
			helped += helpe.intValue();

		}
		statAnalysePlaceVo.setOrganization(organization);
		statAnalysePlaceVo.setTotal(total);
		// statAnalysePlaceVo.setPracticalPlace(helped);
		return statAnalysePlaceVo;
	}
}
