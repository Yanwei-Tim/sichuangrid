package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import java.util.List;

import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Organization;

public interface ThreeDataConvert<T> {

	public Organization getUploadOrganization();

	public void setUploadOrganization(Organization organization);

	public ValidateResult validate(T domain, int realRow);

	@Deprecated
	/**
	 * 
	 * @param cellValues
	 * @param result
	 * @return
	 */
	public T convertToDomain(String[] cellValues, ValidateResult result);

	public T convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas, Organization headerOrg);

	public ValidateResult validate(String[] cellValues, int realRow);

	public T convertToDomain(String[] cellValues);

	public T persistenceDomain(T domain);

	public void persistenceDomain(List<T> data);

	public boolean isRepeatData(T domain);

	public T updateDomain(T domain);

	public void updateDomain(List<T> data);

	public int getCheckOrgOrNot();

	public void setCheckOrgOrNot(int checkOrgOrNotValue);

	/**
	 * @param headerOrg
	 *            设置 headorg 在对比 是否是 户籍还是流动人口时候用的着
	 */
	public void setheaderOrg(Organization headerOrg);

	public void registerProcess(T domain);

}
