package com.tianque.baseInfo.nurturesWomen.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.nurturesWomen.domain.NurturesWomen;
import com.tianque.baseInfo.nurturesWomen.service.NurturesWomenService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.globalSetting.service.GlobalSettingService;
import com.tianque.core.globalSetting.util.GlobalSetting;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.excel.definition.SpecialGroupsContext;
import com.tianque.util.IdCardUtil;
import com.tianque.validate.AbstractCountrymenValidator;

@Component("nurturesWomenDataConverter")
public class NurturesWomenDataConverter extends
		AbstractDataConverter<NurturesWomen> {
	@Autowired
	private NurturesWomenService nurturesWomenService;
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	@Qualifier("nurturesWomenValidator")
	private AbstractCountrymenValidator<NurturesWomen> nurturesWomenValidator;
	@Autowired
	private GlobalSettingService globalSettingService;

	@Override
	public ValidateResult validate(NurturesWomen nurturesWomen, int realRow) {
		return validate(nurturesWomenValidator, nurturesWomen, realRow);
	}

	@Override
	public NurturesWomen convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		NurturesWomen result = new NurturesWomen();
		String[][] excelColumArray = null;
		if (GlobalSetting.NOT_ADD_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getNurturesWomenImportArrayNap();
		} else if (GlobalSetting.SYNC_ACTUAL_POPULATION
				.equals(globalSettingService
						.getGlobalValue(GlobalSetting.BUSINESS_DEPENDENT_ACTUAL_POPULATION))) {
			excelColumArray = SpecialGroupsContext
					.getNurturesWomenImportArraySap();
		} else {
			excelColumArray = SpecialGroupsContext
					.getNurturesWomenImportArrayNdt();
		}
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), result, vr);
		if (null != result.getOrganization()) {
			result.setOrgInternalCode(result.getOrganization()
					.getOrgInternalCode());
		}
		return result;
	}

	@Override
	public boolean isRepeatData(NurturesWomen domain) {
		boolean flag = nurturesWomenService.hasDuplicateNurturesWomen(domain
				.getOrganization().getId(), domain.getIdCardNo(), null);
		if (flag) {
			domain.setId(nurturesWomenService
					.getNurturesWomenByIdCardNoAndOrganizationId(
							domain.getIdCardNo(),
							domain.getOrganization().getId()).getId());
		}
		return flag;
	}

	@Override
	public NurturesWomen persistenceDomain(NurturesWomen domain) {
		if (checkDataExitInCache(NewBaseInfoTables.NURTURESWOMEN_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString())) {
			return domain;
		}
		setManCurrentAddress(domain);
		fillDefaultBirthday(domain);
		if (StringUtil.isStringAvaliable(domain.getManIdcardNo())) {
			domain.setManBirthday(IdCardUtil.parseBirthday(domain
					.getManIdcardNo()));
		}
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		nurturesWomenService.addNurturesWomen(domain);
		cleanDataInCache(NewBaseInfoTables.NURTURESWOMEN_KEY,
				domain.getIdCardNo(), domain.getOrganization().getId()
						.toString());
		return domain;
	}

	/**
	 * 设置男方 常住地址
	 * 
	 * @param domain
	 */
	private void setManCurrentAddress(NurturesWomen domain) {
		if (null != domain.getManCurrentAddressType()
				&& domain.getManCurrentAddressType().getInternalId() != CurrentAddressType.OTHER) {
			domain.setManCurrentAddress(domain.getManCommunity() + "小区"
					+ domain.getManBlock() + "幢" + domain.getManUnit() + "单元"
					+ domain.getManRoom() + "室");
		} else {
			domain.setManCurrentAddress(domain.getManCommunity());
		}
	}

	@Override
	public NurturesWomen updateDomain(NurturesWomen domain) {
		setManCurrentAddress(domain);
		fillDefaultBirthday(domain);
		if (StringUtil.isStringAvaliable(domain.getManIdcardNo())) {
			domain.setManBirthday(IdCardUtil.parseBirthday(domain
					.getManIdcardNo()));
		}
		// checkBusinessLogic(domain);
		convertBaseInfoReferencePersistence(domain);
		nurturesWomenService.updateNurturesWomen(domain);
		return domain;
	}

}
