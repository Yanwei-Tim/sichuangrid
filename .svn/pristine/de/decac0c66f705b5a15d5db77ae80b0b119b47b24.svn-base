package com.tianque.init;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tianque.core.datatransfer.ExcelReader;
import com.tianque.core.datatransfer.data.ExcelData;
import com.tianque.core.util.FileUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.domain.property.OrganizationType;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

public class OrganizationExcelNewInit implements Initialization {
	private List<Organization> arrayList = new ArrayList<Organization>();
	private boolean isProvinceInit = false;
	private PropertyDictService propertyDictService;
	private OrganizationDubboService organizationDubboService;
	private Organization country = new Organization();
	private ExcelData excelData = new ExcelData();
	private int[] codeArray = { 0, 2, 4, 6, 9, 12, 15 };
	private int[] internalArray = { 6, 5, 4, 3, 2, 1, 0 };// 两个数组位数对应orgLevel层级
	// 表示是全国级别的初始化还是省以下的初始化
	private boolean isCountry = true;
	private int sumLevel = 5;// 默认值为5，表示excel初始化是全国级别

	public int getSumLevel() {
		return sumLevel;
	}

	public void setSumLevel(int sumLevel) {
		this.sumLevel = sumLevel;
	}

	public boolean isProvinceInit() {
		return isProvinceInit;
	}

	public void setProvinceInit(boolean isProvinceInit) {
		this.isProvinceInit = isProvinceInit;
	}

	public boolean isCountry() {
		return isCountry;
	}

	public void setCountry(boolean isCountry) {
		this.isCountry = isCountry;
	}

	public int[] getCodeArray() {
		return codeArray;
	}

	public void setCodeArray(int[] codeArray) {
		this.codeArray = codeArray;
	}

	public int[] getInternalArray() {
		return internalArray;
	}

	public void setInternalArray(int[] internalArray) {
		this.internalArray = internalArray;
	}

	public ExcelData getExcelData() {
		return excelData;
	}

	public void setExcelData(ExcelData excelData) {
		this.excelData = excelData;
	}

	public List<Organization> getArrayList() {
		return arrayList;
	}

	public void setArrayList(List<Organization> arrayList) {
		this.arrayList = arrayList;
	}

	public Organization getCountry() {
		return country;
	}

	public void setCountry(Organization country) {
		this.country = country;
	}

	public OrganizationExcelNewInit(PropertyDictService propertyDictService,
			OrganizationDubboService organizationDubboService, boolean isProvinceInit) {
		this.propertyDictService = propertyDictService;
		this.organizationDubboService = organizationDubboService;
		this.isProvinceInit = isProvinceInit;
	}

	@Override
	public void init() throws Exception {
		initLevel();
		if (isCountry) {
			country = organizationDubboService.addOrganization(getRootOrganization());
		}
		for (int j = 0; j < sumLevel; j++) {
			arrayList.add(getRootOrganization());
		}
		if (!isProvinceInit) {
			this.executeHashMapSheet(getExcelData(FileUtil.getWebRoot()
					+ "/WEB-INF/classes/organizationConfig-development.xls"));
		} else {
			this.executeHashMapSheet(getExcelData(FileUtil.getWebRoot()
					+ "/WEB-INF/classes/organizationConfig-production.xls"));
		}
	}

	/**
	 * 获取经过处理的excel data
	 * 
	 * @param filePath
	 *        (excel的文件名;
	 * @return
	 */
	public ExcelData getExcelData(String filePath) {
		try {
			File file = new File(filePath);
			FileInputStream fileInputStream = new FileInputStream(file);
			ExcelData excelData = ExcelReader.readFile(fileInputStream);
			return excelData;
		} catch (Exception e) {
			System.err.println("读取excel文件时出现问题!");
		}
		return null;
	}

	/**
	 * 解析获得的 excelData,处理sheet层数据
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void executeHashMapSheet(ExcelData excelData) throws Exception {
		for (int i = 0; i < excelData.getSheetCount(); i++) {
			String[][] table = excelData.getSheetDatas(i);
			if (table.length > 0) {
				executeTable(table);
			}
		}
	}

	/**
	 * 处理sheet层下table数据
	 * 
	 * @param args
	 * @throws Exception
	 */
	public void executeTable(String[][] table) throws Exception {
		for (int i = 0; i < table.length; i++) {
			String[] row = table[i];
			// System.err.println("表的行数: " + table.length);
			// System.err.println("表的列数: " + row.length);
			validateRow(row);
			if (i != 0) {
				executeRow(row);
			}
		}
	}

	/**
	 * 判断所给的行的格式
	 * 
	 * @throws Exception
	 */
	public void validateRow(String[] row) throws Exception {
		if (row.length % 2 != 0) {
			System.err.println(row.length);
			throw new Exception("给的表的格式有问题!");
		}
	}

	/**
	 * 处理table下每一行的数据
	 */
	public void executeRow(String[] row) {
		for (int i = 0; i < row.length; i++) {
			if (i % 2 == 0) {
				String name = row[i];
				String orgInternalCode = row[i + 1].trim();
				if (name.equals("") && orgInternalCode.equals("")) {
					continue;
				}
				executeColumn(name, orgInternalCode, i);
			}
		}
	}

	/**
	 * i 第多少列.
	 * 改变arraylist中的
	 */

	public void executeColumn(String name, String orgInternalCode, int column) {
		for (int i = 0; i < arrayList.size(); i++) {
			if (arrayList.get(i).getOrgName().equals(name)
					|| arrayList.get(i).getOrgInternalCode().equals(orgInternalCode)) {
				if (null != arrayList.get(i).getId()) {
					return;
				}
			}
		}
		if (isCountry) {
			// column/2的值就相当于省级从低到高的排序
			if (column == 0) {
				Organization organization = new Organization();
				organization.setOrgName(name);
				organization.setCreateDate(Calendar.getInstance().getTime());
				List<PropertyDict> orgTypes = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_REGION);
				organization.setOrgType(orgTypes.get(0));
				List<PropertyDict> orgLevels = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(OrganizationLevel.ORG_LEVEL_KEY,
								OrganizationLevel.PROVINCE);
				organization.setOrgLevel(orgLevels.get(0));
				organization.setCreateUser("超级管理员");
				organization.setParentOrg(country);
				organization.setOrgInternalCode(orgInternalCode);
				organization.setDepartmentNo(orgInternalCode);
				arrayList.set(0, organizationDubboService.addOrganization(organization));
				return;
			}
		} else {
			if (column == 0) {
				Organization organization = new Organization();
				organization.setOrgName(name);
				organization.setCreateDate(Calendar.getInstance().getTime());
				List<PropertyDict> orgTypes = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
								OrganizationType.ADMINISTRATIVE_REGION);
				organization.setOrgType(orgTypes.get(0));

				int internalCode = 7;
				for (int i = 0; i < codeArray.length; i++) {
					if (codeArray[i] == orgInternalCode.trim().length()) {
						internalCode = this.internalArray[i];
						break;
					}
				}
				if (internalCode == 7) {
					try {
						throw new Exception("Excel中传入组织机构码位数有问题!");
					} catch (Exception e) {
					}
				}
				List<PropertyDict> orgLevels = propertyDictService
						.findPropertyDictByDomainNameAndInternalId(OrganizationLevel.ORG_LEVEL_KEY,
								internalCode);
				organization.setOrgLevel(orgLevels.get(0));
				organization.setCreateUser("超级管理员");
				organization.setParentOrg(null);
				organization.setOrgInternalCode(orgInternalCode);
				organization.setDepartmentNo(orgInternalCode);
				arrayList.set(0, organizationDubboService.addOrganization(organization));
				return;
			}

		}

		Organization organization = updateArrayList(name, orgInternalCode, column);
		organization.setParentOrg(arrayList.get(column / 2 - 1));
		organizationDubboService.addOrganization(organization);

	}

	/**
	 * 更新存储父Organization的arrayList
	 * 
	 * @return
	 */
	public Organization updateArrayList(String name, String orgInternalCode, int column) {
		Organization organization = new Organization();
		organization.setOrgName(name);
		organization.setCreateUser("超级管理员");
		List<PropertyDict> orgTypes = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
						OrganizationType.ADMINISTRATIVE_REGION);
		organization.setOrgType(orgTypes.get(0));
		/**
		 * 此处计算level更换.
		 */
		int internalCode = 7;
		for (int i = 0; i < codeArray.length; i++) {
			if (codeArray[i] == orgInternalCode.length()) {
				internalCode = this.internalArray[i];
				break;
			}
		}
		if (internalCode == 7) {
			try {
				throw new Exception("Excel中传入组织机构码位数有问题!");
			} catch (Exception e) {
			}
		}
		List<PropertyDict> orgLevels = propertyDictService
				.findPropertyDictByDomainNameAndInternalId(OrganizationLevel.ORG_LEVEL_KEY,
						internalCode);
		organization.setOrgLevel(orgLevels.get(0));
		organization.setCreateDate(Calendar.getInstance().getTime());
		organization.setOrgInternalCode(orgInternalCode);
		organization.setDepartmentNo(orgInternalCode);
		arrayList.set(column / 2, organization);
		return organization;
	}

	public Organization getRootOrganization() {
		if (isCountry) {// 如果为全国级别的初始化
			Organization organization = new Organization();
			organization.setOrgName("中国");
			organization.setCreateUser("超级管理员");
			List<PropertyDict> orgTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
							OrganizationType.ADMINISTRATIVE_REGION);
			organization.setOrgType(orgTypes.get(0));
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(OrganizationLevel.ORG_LEVEL_KEY,
							OrganizationLevel.COUNTRY);
			organization.setOrgLevel(orgLevels.get(0));
			organization.setCreateDate(Calendar.getInstance().getTime());
			return organization;
		} else {
			Organization organization = new Organization();
			String[][] table = excelData.getSheetDatas(0);
			organization.setOrgName(table[1][0]);
			organization.setCreateUser("超级管理员");
			List<PropertyDict> orgTypes = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(OrganizationType.ORG_TYPE_KEY,
							OrganizationType.ADMINISTRATIVE_REGION);
			organization.setOrgType(orgTypes.get(0));
			List<PropertyDict> orgLevels = propertyDictService
					.findPropertyDictByDomainNameAndInternalId(OrganizationLevel.ORG_LEVEL_KEY,
							sumLevel);
			organization.setOrgLevel(orgLevels.get(0));
			organization.setCreateDate(Calendar.getInstance().getTime());
			return organization;
		}
	}

	/**
	 * 判断初始化级别
	 * 
	 * @return
	 */
	public boolean initLevel() {
		if (!isProvinceInit) {
			excelData = getExcelData(FileUtil.getWebRoot()
					+ "/WEB-INF/classes/organizationConfig-development.xls");
		} else {
			excelData = getExcelData(FileUtil.getWebRoot()
					+ "/WEB-INF/classes/organizationConfig-production.xls");
		}
		String[][] table = excelData.getSheetDatas(0);
		if (table[0].length < 10) {// 省级别以下的初始化录入数据
			isCountry = false;
			sumLevel = table[0].length / 2;
			return false;
		} else { // 全国级别的
			isCountry = true;
			return true;
		}
	}
}
