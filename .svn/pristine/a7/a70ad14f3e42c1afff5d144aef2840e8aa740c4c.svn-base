package com.tianque.plugin.analyzing.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.BaseInfoTables;
import com.tianque.domain.Organization;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.dao.StatisticsBaseInfoDao;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.domain.PersonnelDetailDataVo;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("statisticsPlaceService")
public class StatisticsPlaceServiceImpl implements StatisticsPlaceService {

	@Autowired
	private StatisticsBaseInfoDao statisticsBaseInfoDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public List<PersonnelAreaDataVo> getImportantPlaceAreaDataByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		List<PersonnelAreaDataVo> placeAreaDataVos = new ArrayList<PersonnelAreaDataVo>();

		List<Organization> orgainizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		for (Organization organization : orgainizations) {
			placeAreaDataVos
					.add(getImportantPlaceAreaDataVoByOrgId(organization));
		}
		return placeAreaDataVos;
	}

	@Override
	public HighchartColumnVo getImportantPlaceColumnByOrgId(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.IMPORTANTPLACE_KEY));
		highchartColumn.setSeries(getImportantPlaceColumnDataByOrgId(orgId));
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		return highchartColumn;
	}

	private List<HighchartDataColumnVo> getImportantPlaceColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		positiveinfoColumns
				.addAll(getImportantPlaceColumnDataByOrgs(organizations));
		return positiveinfoColumns;
	}

	private List<HighchartDataColumnVo> getImportantPlaceColumnDataByOrgs(
			List<Organization> organizations) {
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		int[] data = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			data[i] = getImportantPlaceCountSumByOrgInternalCode(organizations
					.get(i).getOrgInternalCode());
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(BaseInfoTables
					.getTypeDisplayNames(BaseInfoTables.IMPORTANTPLACE_KEY));
		}
		positiveinfoColumns.add(positiveinfoColumnData);

		return positiveinfoColumns;
	}

	private int getImportantPlaceCountSumByOrgInternalCode(
			String orgInternalCode) {
		int sum = 0;
		for (Entry<String, String> entry : BaseInfoTables.importantPlaceTables
				.entrySet()) {
			sum += getImportantPlaceCountByOrgInternalCode(orgInternalCode,
					entry.getValue());
		}
		return sum;
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

	private PersonnelAreaDataVo getImportantPlaceAreaDataVoByOrgId(
			Organization organization) {
		PersonnelAreaDataVo placeAreaDataVo = new PersonnelAreaDataVo();
		placeAreaDataVo.setOrg(organization);
		placeAreaDataVo
				.setPersonnelDetailDatas(getImportantPlaceDetailDatasByOrgInternalCode(organization
						.getOrgInternalCode()));
		return placeAreaDataVo;
	}

	private List<PersonnelDetailDataVo> getImportantPlaceDetailDatasByOrgInternalCode(
			String orgInternalCode) {
		int amountSum = 0;
		List<PersonnelDetailDataVo> personnelDetailDataVosTemp = new ArrayList<PersonnelDetailDataVo>();
		List<PersonnelDetailDataVo> personnelDetailDataVos = new ArrayList<PersonnelDetailDataVo>();
		for (Entry<String, String> entry : BaseInfoTables.importantPlaceTables
				.entrySet()) {
			PersonnelDetailDataVo personnelDetailDataVo = new PersonnelDetailDataVo();
			personnelDetailDataVo.setName(BaseInfoTables
					.getTypeDisplayNames(entry.getKey()));
			personnelDetailDataVo
					.setAmount(getImportantPlaceCountByOrgInternalCode(
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
						.parseDouble(new java.text.DecimalFormat("#.0000")
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

	private Integer getImportantPlaceCountByOrgInternalCode(
			String orgInternalCode, String tableName) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (BaseInfoTables.getTypeValue(BaseInfoTables.FIRESAFETYKEY_KEY)
				.equals(tableName)
				|| BaseInfoTables.getTypeValue(
						BaseInfoTables.SAFETYPRODUCTIONKEY_KEY).equals(
						tableName)
				|| BaseInfoTables.getTypeValue(BaseInfoTables.SECURITYKEY_KEY)
						.equals(tableName)) {
			map.put("keyType", tableName);
			tableName = "enterprises";
		}
		return statisticsBaseInfoDao
				.getCountByOrgInternalCodeAndTableNameAndMap(orgInternalCode,
						tableName, map);
	}

	@Override
	public List<Object[]> getImportantPlacePieByOrgId(Long orgId) {
		List<Object[]> importantPersonlPieDatas = new ArrayList<Object[]>();
		double sum = getImportantPlaceCountSumByOrgInternalCode(organizationDubboService
				.getSimpleOrgById(orgId).getOrgInternalCode());
		for (Entry<String, String> entry : BaseInfoTables.importantPlaceTables
				.entrySet()) {
			Object[] importantPersonlData = new Object[2];

			double importantPersonlCount = getImportantPlaceCountByOrgInternalCode(
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
	public HighchartColumnVo getImportantPlaceCountFromHistory(Long orgId,
			String typeTableName, int year, int month) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo highchartColumn = new HighchartColumnVo();
		highchartColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.IMPORTANTPLACE_KEY));
		List<HighchartDataColumnVo> positiveinfoColumns = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		List<HighchartDataColumnVo> datas = new ArrayList<HighchartDataColumnVo>();

		HighchartDataColumnVo positiveinfoColumnData = new HighchartDataColumnVo();
		int[] data = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			int sum = 0;
			for (Entry<String, String> entry : BaseInfoTables.importantPlaceTables
					.entrySet()) {

				sum += getImportantPlaceCountByTime(organizations.get(i)
						.getOrgInternalCode(),
						BaseInfoTables.getTypeDisplayNames(entry.getKey()),
						BaseInfoTables.IMPORTANTPLACE_KEY, year, month);
			}
			data[i] = sum;
			positiveinfoColumnData.setData(data);
			positiveinfoColumnData.setName(BaseInfoTables
					.getTypeDisplayNames(BaseInfoTables.IMPORTANTPLACE_KEY));
		}
		datas.add(positiveinfoColumnData);

		positiveinfoColumns.addAll(datas);
		highchartColumn.setSeries(positiveinfoColumns);
		highchartColumn.setCategories(getOrgArraysByParentId(orgId));
		return highchartColumn;
	}

	private int getImportantPlaceCountByTime(String orgInternalCode,
			String tableName, String baseinfotype, int year, int month) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("year", year);
		map.put("month", month);
		map.put("orgInternalCode", orgInternalCode);
		map.put("typeName", tableName);
		map.put("baseinfotype", baseinfotype);
		return statisticsBaseInfoDao.getCountFromHistory(map);
	}

	@Override
	public int getImportantPlaceCountByOrgId(Long orgId) {
		int sum = 0;
		if (organizationDubboService != null
				&& organizationDubboService.getSimpleOrgById(orgId) != null) {
			sum = getImportantPlaceCountSumByOrgInternalCode(organizationDubboService
					.getSimpleOrgById(orgId).getOrgInternalCode());
		}
		return sum;
	}

}
