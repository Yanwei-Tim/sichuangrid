package com.tianque.baseInfo.actualHouse.dataConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.excel.definition.HouseContext;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractHouseValidator;

@Component("actualHouseDataConverter")
public class ActualHouseDataConverter extends
		AbstractDataConverter<RentalHouse> {

	@Qualifier("actualHouseValidator")
	@Autowired
	private AbstractHouseValidator<HouseInfo> validator;

	@Autowired
	private ActualHouseService houseInfoService;

	@Autowired
	private RentalHouseService rentalHouseService;

	@Override
	public boolean isRepeatData(RentalHouse domain) {
		// fateson add 没法确定唯一性 <后期可能使用地址>
		return false;
		/*
		 * HouseInfo houseInfo =
		 * houseInfoService.getHouseInfoByHouseCodeAndOrgId(
		 * domain.getHouseCode(), domain.getOrganization().getId()); if (null ==
		 * houseInfo) { return false; } else { domain.setId(houseInfo.getId());
		 * return true; }
		 */
	}

	@Override
	public RentalHouse persistenceDomain(RentalHouse domain) {
		if (null != domain.getAddressType()
				&& domain.getAddressType().getInternalId() != CurrentAddressType.OTHER) {
			domain.setAddress(domain.getAddress() + "小区" + domain.getBlock()
					+ "幢" + domain.getUnit() + "单元" + domain.getRoom() + "室");
		} else {
			domain.setAddress(domain.getAddress());
		}

		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		domain.setCreateDate(new Date());
		checkHouseSource(domain);
		// fateson add 先新增实有房，获得id 放到出租房中的houseid中
		HouseInfo houseInfo = new HouseInfo();
		PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class, houseInfo,
				domain);
		HouseInfo houseInfos = houseInfoService.addHouseInfo(houseInfo);
		domain.setHouseId(houseInfos.getId());
		// 新增出租房
		maintainRentalHouse(domain);

		return domain;
	}

	@Override
	public RentalHouse updateDomain(RentalHouse domain) {
		if (null != domain.getAddressType()
				&& domain.getAddressType().getInternalId() != CurrentAddressType.OTHER) {
			domain.setAddress(domain.getCommunity() + "小区" + domain.getBlock()
					+ "幢" + domain.getUnit() + "单元" + domain.getRoom() + "室");
		} else {
			domain.setAddress(domain.getAddress());
		}

		if (null != domain.getOrganization()) {
			domain.setOrgInternalCode(domain.getOrganization()
					.getOrgInternalCode());
		}
		domain.setCreateDate(new Date());
		checkHouseSource(domain);
		houseInfoService.updateHouseInfo(domain);
		maintainRentalHouse(domain);
		return domain;
	}

	private void checkHouseSource(RentalHouse domain) {
		if (validator.typeIsNotNull(domain.getHouseSource())) {
			if ("自有产权".equals(domain.getHouseSource().getDisplayName())) {
				domain.setRentalBuildings(null);
			} else {
				domain.setOwnProperty(null);
			}
		}
	}

	private void maintainRentalHouse(RentalHouse domain) {
		RentalHouse savedRentalHouse = rentalHouseService
				.getHouseInfoByHouseCodeAndOrgId(domain.getHouseCode(), domain
						.getOrganization().getId());
		if (domain.getIsRentalHouse()) {
			if (null == savedRentalHouse) {
				rentalHouseService.addHouseInfo(domain);
			} else {
				domain.setId(savedRentalHouse.getId());
				PropertyUtil.copyPropertiesWithRecursion(RentalHouse.class,
						savedRentalHouse, domain);
				rentalHouseService.updateHouseBaseInfo(savedRentalHouse);
				rentalHouseService.updateHouseBusinessInfo(savedRentalHouse);
				List<Long> ids = new ArrayList<Long>();
				ids.add(savedRentalHouse.getId());
				rentalHouseService.updateEmphasiseByIds(ids, Long.valueOf(0));
			}
		} else {
			if (null != savedRentalHouse) {
				domain.setId(savedRentalHouse.getId());
				PropertyUtil.copyPropertiesWithRecursion(RentalHouse.class,
						savedRentalHouse, domain);
				rentalHouseService.updateHouseBaseInfo(savedRentalHouse);
				rentalHouseService.updateHouseBusinessInfo(savedRentalHouse);
				List<Long> ids = new ArrayList<Long>();
				ids.add(savedRentalHouse.getId());
				rentalHouseService.updateEmphasiseByIds(ids, Long.valueOf(1));
			}
		}
	}

	@Override
	public ValidateResult validate(RentalHouse actualhouse, int realRow) {
		ExcelImportHelper.realRow.set(realRow);
		return validator.validate(actualhouse);

	}

	@Override
	public RentalHouse convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		RentalHouse houseInfo = new RentalHouse();
		String[][] excelColumArray = HouseContext.getHouseInfoImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), houseInfo, vr);
		if (null != houseInfo.getAddressType()
				&& houseInfo.getAddressType().getInternalId() != CurrentAddressType.OTHER) {
			houseInfo.setAddress(houseInfo.getCommunity() + "小区"
					+ houseInfo.getBlock() + "幢" + houseInfo.getUnit() + "单元"
					+ houseInfo.getRoom() + "室");
		} else {
			houseInfo.setAddress(houseInfo.getAddress());
		}

		if (null != houseInfo.getOrganization()) {
			houseInfo.setOrgInternalCode(houseInfo.getOrganization()
					.getOrgInternalCode());
		}
		houseInfo.setCreateDate(new Date());
		return houseInfo;
	}
}
