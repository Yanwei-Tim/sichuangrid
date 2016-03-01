package com.tianque.plugin.datatransfer.dataconvert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.log4j.Logger;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.SpringBeanUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.Organization;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.facade.organization.OrganizationServiceFacade;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.base.service.AbstractDataManageService;
import com.tianque.plugin.dataManage.validate.DomainValidatorTemp;
import com.tianque.util.DateFormat;

public abstract class AbstractTempDataConverter<T extends BaseDomain> extends
		AbstractDataConverter<T> {

	private final Logger logger = Logger.getLogger(getClass());
	protected AbstractDataManageService dataManageService;
	protected DomainValidatorTemp validator;
	private static String OVERSEAPERSONNELTEMP = "OverseaPersonnelTemp";

	@Override
	public T persistenceDomain(T domain) {
		try {
			Organization org = ReflectionUtil.getTargetOrganization(domain);
			if (null == org) {
				throw new BusinessValidationException("所属网格不能为空!");
			}
			if (org.getOrgLevel().getInternalId() > OrganizationLevel.DISTRICT) {
				throw new BusinessValidationException("所属网格应该在区县以下!");
			}
			// 对场所中的组织机构的添加时的特殊处理
			Ognl.setValue("orgInternalCode", domain, org.getOrgInternalCode());
			if (!OVERSEAPERSONNELTEMP.equalsIgnoreCase(domain.getClass()
					.getSimpleName())) {
				try {
					String idCardNo = ReflectionUtil.getTargetIdCardNo(domain);
					Ognl.setValue("birthday", domain, DateFormat.parseDate(
							getBirthDayTextFromIdCard(idCardNo),
							DateFormat.DEFAULT_DATE_FORMAT));
				} catch (Exception e) {
					logger.warn("对身份证号码不存在的异常不进行处理（场所、房屋、未落户人口无此方法）", e);
				}
			}

			OrganizationServiceFacade facade = (OrganizationServiceFacade) SpringBeanUtil
					.getBeanFromSpringByBeanNameForFacade("organizationServiceFacade");
			Ognl.setValue("claimDetail.districtOrgId", domain,
					facade.getDistrictorgId(org.getId()));
		} catch (Exception e) {
			logger.error("获取对象的组织机构organization错误", e);
		}
		try {
			ReflectionUtil.getTargetClaimDetail(domain).setImportDepartment(
					ThreadVariable.getUser().getOrganization());

		} catch (Exception e) {
			throw new ServiceValidationException("获取对象的认领状态ClaimDetail错误", e);
		}
		try {
			Ognl.setValue("claimDetail.importDepartment", domain,
					ThreadVariable.getUser().getOrganization());

			Ognl.setValue("claimDetail.importDate", domain, ThreadVariable
					.getSession().getAccessTime());
		} catch (OgnlException e) {
			logger.error("导入的数据设置属性值出错", e);
		}
		return (T) dataManageService.addTemp(getRuntimeType(), domain);
	}

	@Override
	public ValidateResult validate(T domain, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return validator.validate(domain);
	}

	@Override
	public T convertToDomain(String[] cellValues, ValidateResult result,
			String[][] beanDatas, Organization headerOrg) {
		setUploadOrganization(headerOrg);
		cellValues = validateHelper.cellValueTrim(cellValues);
		T returnObject = null;
		try {
			returnObject = getRuntimeType().newInstance();
			ExcelImportHelper.newProcImportDate(beanDatas, cellValues,
					getUploadOrganization(), returnObject, result);
		} catch (Exception e) {
			throw new ServiceValidationException(e.getMessage(), e);
		}
		if (!"organization".equals(beanDatas[0][1])) {

			try {
				Ognl.setValue("Organization", returnObject, headerOrg);
			} catch (OgnlException e) {
				e.printStackTrace();
			}
		}

		return returnObject;
	}

	private Class<T> getRuntimeType() {
		Class<T> entityClass = null;
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] parameterizedType = ((ParameterizedType) type)
					.getActualTypeArguments();

			entityClass = (Class<T>) parameterizedType[0];
		}
		return entityClass;
	}

	@Override
	public T updateDomain(T domain) {
		return null;
	}

	@Override
	public T convertToDomain(String[] cellValues, ValidateResult result) {
		return null;
	}

	private String getBirthDayTextFromIdCard(String idCard) {
		if (idCard != null && idCard.length() == 18) {
			idCard = idCard.substring(6, 14);
			if (idCard.substring(4, 6).length() <= 0
					|| idCard.substring(4, 6).length() > 12) {
				return "";
			} else if (idCard.substring(6, 8).length() <= 0
					|| idCard.substring(6, 8).length() > 31) {
				return "";
			} else {
				return idCard.substring(0, 4) + "-" + idCard.substring(4, 6)
						+ "-" + idCard.substring(6, 8);
			}
		} else if (idCard != null && idCard.length() == 15) {
			idCard = idCard.substring(6, 12);
			if (idCard.substring(2, 4).length() <= 0
					|| idCard.substring(2, 4).length() > 12) {
				return "";
			} else if (idCard.substring(4, 6).length() <= 0
					|| idCard.substring(4, 6).length() > 31) {
				return "";
			} else {
				return "19" + idCard.substring(0, 2) + "-"
						+ idCard.substring(2, 4) + "-" + idCard.substring(4, 6);
			}
		}
		return "";

	}

	public AbstractDataManageService getDataManageService() {
		return dataManageService;
	}

	public void setDataManageService(AbstractDataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}

	public DomainValidatorTemp getValidator() {
		return validator;
	}

	public void setValidator(DomainValidatorTemp validator) {
		this.validator = validator;
	}

}
