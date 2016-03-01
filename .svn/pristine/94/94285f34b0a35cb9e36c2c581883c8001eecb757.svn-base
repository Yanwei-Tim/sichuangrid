package com.tianque.baseInfo.companyPlace.dataConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.baseInfo.companyPlace.service.CompanyPlaceBaseSevice;
import com.tianque.core.cache.constant.MemCacheConstant;
import com.tianque.core.cache.service.CacheService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.util.NewBaseInfoTables;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.excel.definition.SpecialGroupsContext;

@Component("companyPlaceDataConverter")
public class CompanyPlaceDataConverter extends
		AbstractDataConverter<CompanyPlace> {
	@Autowired
	private CompanyPlaceBaseSevice companyPlaceBaseService;

	@Qualifier("companyPlaceImportValidator")
	@Autowired
	private DomainValidator<CompanyPlace> companyPlaceImportValidator;
	@Autowired
	private CacheService cacheService;

	/** 将Excel数据转为对象 */
	@Override
	public CompanyPlace convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		CompanyPlace domain = new CompanyPlace();

		ExcelImportHelper.procImportDate(
				SpecialGroupsContext.getCompanyplaceImportArray(), cellValues,
				getUploadOrganization(), domain, vr);
		return domain;
	}

	/** 保存数据到数据库 */
	@Override
	public CompanyPlace persistenceDomain(CompanyPlace domain) {
		CompanyPlace companyPlace = null;
		try {
			if (checkDataExitInCache(domain)) {
				return domain;
			}
			companyPlace = companyPlaceBaseService.addCompanyPlaceBase(domain,
					domain.getClassifiCationEn(), null);
		} finally {
			cleanDataInCache(domain);
		}
		return companyPlace;
	}

	/** 修改数据到数据库 */
	@Override
	public CompanyPlace updateDomain(CompanyPlace domain) {
		return null;
	}

	/** 验证Excel数据的正确性 */
	@Override
	public ValidateResult validate(CompanyPlace companyPlace, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return companyPlaceImportValidator.validate(companyPlace);
	}

	@Override
	public boolean isRepeatData(CompanyPlace domain) {
		return false;
	}

	private boolean checkDataExitInCache(CompanyPlace domain) {
		if (domain != null && domain.getName() != null
				&& !"".equals(domain.getName())
				&& domain.getOrganization() != null
				&& domain.getOrganization().getId() != null
				&& domain.getClassifiCation() != null
				&& domain.getClassifiCation().getId() != null) {
			String key = MemCacheConstant.getCompanyPlaceKey(
					MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
							.getClassifiCation().getId(), domain
							.getOrganization().getId());
			return !cacheService.add(MemCacheConstant.COMPANYPLACE_NAMESPACE,
					key, 300, NewBaseInfoTables.COMPANYPLACEKEY);
		}
		return false;
	}

	public void cleanDataInCache(CompanyPlace domain) {
		String key = MemCacheConstant.getCompanyPlaceKey(
				MemCacheConstant.COMPANYPLACEKEY, domain.getName(), domain
						.getClassifiCation().getId(), domain.getOrganization()
						.getId());
		cacheService.remove(MemCacheConstant.COMPANYPLACE_NAMESPACE, key);
	}
}
