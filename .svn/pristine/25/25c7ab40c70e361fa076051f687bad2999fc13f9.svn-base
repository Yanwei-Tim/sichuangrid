package com.tianque.baseInfo.rectificativePerson.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.ActualPopulation;
import com.tianque.baseInfo.rectificativePerson.dao.SearchRectificativePersonDao;
import com.tianque.baseInfo.rectificativePerson.domain.RectificativePerson;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.BaseInfoTables;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.SearchRectificativePersonVo;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.plugin.analyzing.domain.HighchartColumnVo;
import com.tianque.plugin.analyzing.domain.HighchartDataColumnVo;
import com.tianque.plugin.analyzing.domain.PersonnelAreaDataVo;
import com.tianque.plugin.analyzing.domain.PersonnelDetailDataVo;
import com.tianque.service.ActualPopulationProcessorService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.sysadmin.service.PropertyDomainService;
import com.tianque.userAuth.api.PermissionDubboService;
import com.tianque.util.IdCardUtil;

@Transactional
@Service("searchRectificativePersonService")
public class SearchRectificativePersonServiceImpl implements
		SearchRectificativePersonService {

	@Autowired
	SearchRectificativePersonDao searchRectificativePersonDao;
	@Autowired
	PropertyDictService propertyDictService;
	@Autowired
	PropertyDomainService propertyDomainService;
	@Autowired
	OrganizationDubboService organizationDubboService;
	@Autowired
	private GlobalSettingService globalSettingService;
	@Autowired
	private ActualPopulationProcessorService actualPopulationProcessorService;
	@Autowired
	private PermissionDubboService permissionDubboService;

	@Override
	public Long getCount(String orgInternalCode) {
		return searchRectificativePersonDao.getCount(orgInternalCode);
	}

	@Override
	public Map<String, Long> getExecuteTypeCount(String orgInternalCode) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		Map<String, Long> executeTypeCount = new HashMap<String, Long>();
		for (PropertyDict propertyDict : propertyDicts) {
			executeTypeCount.put(
					propertyDict.getDisplayName(),
					null == searchRectificativePersonDao.getExecuteTypeCount(
							orgInternalCode, propertyDict.getId()) ? 0
							: searchRectificativePersonDao.getExecuteTypeCount(
									orgInternalCode, propertyDict.getId()));
		}
		return executeTypeCount;
	}

	@Override
	public Map<String, Long> helpedCount(String orgInternalCode) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		Map<String, Long> executeTypeCount = new HashMap<String, Long>();

		for (PropertyDict propertyDict : propertyDicts) {
			executeTypeCount.put(propertyDict.getDisplayName(),
					searchRectificativePersonDao.getHelpedCount(
							orgInternalCode, propertyDict.getId()));
		}
		return executeTypeCount;
	}

	@Override
	public Map<String, Long> notHelpedCount(String orgInternalCode) {
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		Map<String, Long> executeTypeCount = new HashMap<String, Long>();
		for (PropertyDict propertyDict : propertyDicts) {
			executeTypeCount.put(propertyDict.getDisplayName(),
					searchRectificativePersonDao.getNotHelpedCount(
							orgInternalCode, propertyDict.getId()));
		}
		return executeTypeCount;
	}

	@Override
	public List<PersonnelAreaDataVo> returnDataList(Long orgId) {
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);
		List<PersonnelAreaDataVo> list = new ArrayList<PersonnelAreaDataVo>();
		PersonnelAreaDataVo PersonnelAreaDataVo;
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		for (Organization organization : organizations) {
			PersonnelAreaDataVo = new PersonnelAreaDataVo();

			Map<String, Long> mapType = getExecuteTypeCount(organization
					.getOrgInternalCode());
			Map<String, Long> mapHelp = helpedCount(organization
					.getOrgInternalCode());
			Map<String, Long> mapNotHelp = notHelpedCount(organization
					.getOrgInternalCode());

			List<PersonnelDetailDataVo> listDetail = new ArrayList<PersonnelDetailDataVo>();
			PersonnelDetailDataVo rc;
			Long helpSum = 0L;
			Long noHelpSum = 0L;
			Long amountSum = 0L;
			for (int i = 0; i < propertyDicts.size(); i++) {
				rc = new PersonnelDetailDataVo();
				rc.setName(propertyDicts.get(i).getDisplayName());
				rc.setAmount(mapType.get(propertyDicts.get(i).getDisplayName())
						.intValue());
				amountSum = amountSum
						+ mapType.get(propertyDicts.get(i).getDisplayName());
				rc.setHelped(mapHelp.get(propertyDicts.get(i).getDisplayName())
						.intValue());
				helpSum = helpSum
						+ mapHelp.get(propertyDicts.get(i).getDisplayName());
				rc.setNoHelp(mapNotHelp.get(
						propertyDicts.get(i).getDisplayName()).intValue());
				noHelpSum = noHelpSum
						+ mapNotHelp.get(propertyDicts.get(i).getDisplayName());
				listDetail.add(rc);
			}
			PersonnelDetailDataVo rd = new PersonnelDetailDataVo();
			rd.setName("合计");
			rd.setHelped(helpSum.intValue());
			rd.setNoHelp(noHelpSum.intValue());
			rd.setAmount(amountSum.intValue());
			listDetail.add(rd);

			PersonnelAreaDataVo.setAmount(getCount(
					organization.getOrgInternalCode()).intValue());
			PersonnelAreaDataVo.setOrg(organization);
			PersonnelAreaDataVo.setPersonnelDetailDatas(listDetail);
			list.add(PersonnelAreaDataVo);
		}
		return list;
	}

	@Override
	public HighchartColumnVo returnColumnList(Long orgId) {
		if (orgId == null) {
			throw new BusinessValidationException("orgId不能为空");
		}
		HighchartColumnVo RectificativeColumn = new HighchartColumnVo();
		RectificativeColumn.setModuleName(BaseInfoTables
				.getTypeDisplayNames(BaseInfoTables.RECTIFICATIVEPERSON_KEY));
		RectificativeColumn.setCategories(getOrgArraysByParentId(orgId));
		RectificativeColumn.setSeries(getRectificativeColumnDataByOrgId(orgId));
		return RectificativeColumn;
	}

	private List<HighchartDataColumnVo> getRectificativeColumnDataByOrgId(
			Long orgId) {
		List<HighchartDataColumnVo> RectificativeColumnDatas = new ArrayList<HighchartDataColumnVo>();
		List<Organization> organizations = organizationDubboService
				.findAdminOrgsByParentId(orgId);

		HighchartDataColumnVo RectificativeColumnDataHelp = new HighchartDataColumnVo();
		RectificativeColumnDataHelp.setName("帮教");
		int[] dataHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataHelp[i] = getHelp(organizations.get(i).getOrgInternalCode());
		}
		RectificativeColumnDataHelp.setData(dataHelp);
		RectificativeColumnDatas.add(RectificativeColumnDataHelp);

		HighchartDataColumnVo RectificativeColumnDataNoHelp = new HighchartDataColumnVo();
		RectificativeColumnDataNoHelp.setName("未帮教");
		int[] dataNoHelp = new int[organizations.size()];
		for (int i = 0; i < organizations.size(); i++) {
			dataNoHelp[i] = getNoHelp(organizations.get(i).getOrgInternalCode());
		}
		RectificativeColumnDataNoHelp.setData(dataNoHelp);
		RectificativeColumnDatas.add(RectificativeColumnDataNoHelp);

		return RectificativeColumnDatas;
	}

	private int getHelp(String org) {
		int sum = 0;
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		Map<String, Long> map = helpedCount(org);
		for (int i = 0; i < propertyDicts.size(); i++) {
			sum += (map.get(propertyDicts.get(i).getDisplayName()).intValue());
		}
		return sum;
	}

	private int getNoHelp(String org) {
		int sum = 0;
		Map<String, Long> map = notHelpedCount(org);
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		for (int i = 0; i < propertyDicts.size(); i++) {
			sum += (map.get(propertyDicts.get(i).getDisplayName()).intValue());
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

	// private void addHelpColumn(List<HighchartDataColumnVo>
	// listHighchartDataColumnVo , PersonnelAreaDataVo PersonnelAreaDataVo ,Long
	// help , Long noHelp ,int i){
	// for(PersonnelDetailDataVo PersonnelDetailDataVo :
	// PersonnelAreaDataVo.getPersonnelDetailDatas()){
	// help = PersonnelDetailDataVo.getHelped() + help;
	// noHelp = PersonnelDetailDataVo.getNoHelp() + noHelp;
	// for(HighchartDataColumnVo hi : listHighchartDataColumnVo){
	// if(hi.getName().equals(PersonnelDetailDataVo.getName())){
	// int[] data = hi.getData();
	// data[i] = PersonnelDetailDataVo.getAmount();
	// hi.setData(data);
	// break;
	// }
	// }
	// }
	// for(HighchartDataColumnVo hi : listHighchartDataColumnVo){
	// if(hi.getName().equals("监管")){
	// int[] dataHelp = hi.getData();
	// dataHelp[i] = Integer.parseInt(help.toString());
	// hi.setData(dataHelp);
	// }
	// if(hi.getName().equals("未监管")){
	// int[] dataNoHelp = hi.getData();
	// dataNoHelp[i] = Integer.parseInt(noHelp.toString());
	// hi.setData(dataNoHelp);
	// }
	// }
	// }
	// private void addHelpVo(List<PersonnelAreaDataVo>
	// list,List<HighchartDataColumnVo> listHighchartDataColumnVo , int i , Long
	// help , Long noHelp){
	// HighchartDataColumnVo helpVo = new HighchartDataColumnVo();
	// HighchartDataColumnVo noHelpVo = new HighchartDataColumnVo();
	//
	// int[] dataHelp = new int[list.size()];
	// int[] dataNoHelp = new int[list.size()];
	//
	// dataHelp[i] = Integer.parseInt(help.toString());
	// dataNoHelp[i] = Integer.parseInt(noHelp.toString());
	//
	// helpVo.setName("监管");
	// helpVo.setStack("是否已监管");
	// helpVo.setData(dataHelp);
	//
	// noHelpVo.setName("未监管");
	// noHelpVo.setStack("是否已监管");
	// noHelpVo.setData(dataNoHelp);
	//
	//
	// listHighchartDataColumnVo.add(helpVo);
	// listHighchartDataColumnVo.add(noHelpVo);
	// }
	@Override
	public List<Object[]> returnPieList(Long orgId) {
		Organization organization = organizationDubboService
				.getFullOrgById(orgId);
		List<PropertyDict> propertyDicts = propertyDictService
				.findPropertyDictByPropertyDomainId(propertyDomainService
						.getPropertyDomainByDomainName(
								PropertyTypes.EXECUTE_TYPE).getId());
		Object[] objects;
		List<Object[]> listObjects = new ArrayList<Object[]>();
		Long count = searchRectificativePersonDao.getCount(organization
				.getOrgInternalCode());
		for (PropertyDict propertyDict : propertyDicts) {
			objects = new Object[2];

			Long detoxiCateCaseCount = ((null == searchRectificativePersonDao
					.getExecuteTypeCount(organization.getOrgInternalCode(),
							propertyDict.getId())) ? 0L
					: searchRectificativePersonDao.getExecuteTypeCount(
							organization.getOrgInternalCode(),
							propertyDict.getId())) * 100;
			Double doubleDetoxi = Double.parseDouble(detoxiCateCaseCount
					.toString());
			if (count == 0) {
				objects[1] = 0;
			} else {
				objects[1] = Double.parseDouble(new DecimalFormat("0.00")
						.format(doubleDetoxi
								/ searchRectificativePersonDao
										.getCount(organization
												.getOrgInternalCode())));

			}
			objects[0] = propertyDict.getDisplayName() + "( "
					+ detoxiCateCaseCount / 100 + " )";
			listObjects.add(objects);
		}
		return listObjects;
	}

	@Override
	public PageInfo<RectificativePerson> searchRectificativePerson(
			SearchRectificativePersonVo condition, int pageNum, int pageSize,
			String sortField, String order) {
		PageInfo<RectificativePerson> positiveInfoPageInfos = searchRectificativePersonDao.searchRectificativePerson(
				condition, pageNum, pageSize, sortField, order);
		//隐藏身份证中间4位
		positiveInfoPageInfos=hiddenIdCard(positiveInfoPageInfos);
		return positiveInfoPageInfos;
		
	}
	
	//隐藏身份证中间4位
		private PageInfo<RectificativePerson> hiddenIdCard(PageInfo<RectificativePerson> pageInfo){
					//判断权限，有权限不隐藏
					if(permissionDubboService.
							isUserHasPermission(ThreadVariable.getUser().getId(), "isRectificativePersonManagementNotHidCard")){
						return pageInfo;
					}
					List<RectificativePerson> list = pageInfo.getResult();
					int index=0;
					for (RectificativePerson verification:list) {
						verification.setIdCardNo(IdCardUtil.hiddenIdCard(verification.getIdCardNo()));
						list.set(index, verification);
						index++;
					}
					pageInfo.setResult(list);
					return pageInfo;
			}


	@Override
	public List findRectificativePersonNameAndPinyinAndOrgInternalCode(
			String name, String orgInternalCode) {
		return searchRectificativePersonDao
				.findRectificativePersonNameAndPinyinAndOrgInternalCode(name,
						orgInternalCode);
	}

	@Override
	public List<RectificativePerson> searchRectificativePersonForExport(
			SearchRectificativePersonVo rectificativePersonCondition,
			Integer page, Integer rows, String sidx, String sord) {
		List<RectificativePerson> list = searchRectificativePersonDao
				.searchRectificativePersonForExport(
						rectificativePersonCondition, page, rows, sidx, sord);
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return list;
		} else {
			if (null != list && list.size() > 0) {
				ActualPopulation actualPopulation = null;
				for (RectificativePerson rectificativePerson : list) {
					actualPopulation = actualPopulationProcessorService
							.getActualPopulationbyOrgIdAndIdCardNo(
									rectificativePerson.getOrganization()
											.getId(), rectificativePerson
											.getIdCardNo());
					if (null != actualPopulation) {
						rectificativePerson.setHouseCode(actualPopulation
								.getHouseCode());
						rectificativePerson.setNoHouseReason(actualPopulation
								.getNoHouseReason());
					}
				}
			}
			return list;
		}
	}

	@Override
	public String[][] getExportPopertyArray() {
		if (GlobalSetting.NOT_DEPENDENT
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			return SpecialGroupsContext
					.getRectificativePersonPropertyArraySlf();
		} else {
			return SpecialGroupsContext
					.getRectificativePersonPropertyArrayRla();
		}

	}

	@Override
	public Integer getCounts(SearchRectificativePersonVo personVo) {
		return searchRectificativePersonDao.getCounts(personVo);
	}
}
