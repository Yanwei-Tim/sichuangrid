package com.tianque.baseInfo.rentalHouse.dataConverter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.actualHouse.service.ActualHouseService;
import com.tianque.baseInfo.rentalHouse.domain.RentalHouse;
import com.tianque.baseInfo.rentalHouse.service.RentalHouseService;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.AbstractDataConverter;
import com.tianque.domain.property.CurrentAddressType;
import com.tianque.excel.definition.HouseContext;
import com.tianque.service.HousesDependentService;
import com.tianque.util.PropertyUtil;
import com.tianque.validate.AbstractHouseValidator;

@Component("rentalHouseDataConverter")
public class RentalHouseDataConverter extends
		AbstractDataConverter<RentalHouse> {

	@Autowired
	private ValidateHelper validateHelper;

	@Qualifier("actualHouseValidator")
	@Autowired
	private AbstractHouseValidator<RentalHouse> updateValidator;

	@Autowired
	private RentalHouseService rentalHouseService;

	@Autowired
	private HousesDependentService housesDependentService;

	@Autowired
	private ActualHouseService actualHouseService;

	@Override
	public RentalHouse convertToDomain(String[] cellValues, ValidateResult vr) {
		cellValues = validateHelper.cellValueTrim(cellValues);
		RentalHouse houseInfo = new RentalHouse();
		String[][] excelColumArray = HouseContext.getHouseInfoImportArray();
		ExcelImportHelper.procImportDate(excelColumArray, cellValues,
				getUploadOrganization(), houseInfo, vr);
		if (null != houseInfo.getAddressType()
				&& houseInfo.getAddressType().getInternalId() != CurrentAddressType.OTHER) {
			houseInfo.setAddress(houseInfo.getAddress() + "小区"
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

	@Override
	public boolean isRepeatData(RentalHouse domain) {
		// 这里 没法确定唯一性 ，全部属于新增数据 《后期可能考虑地址确定唯一》
		return false;
		// return rentalHouseService.hasDuplicateHouseInfo(domain
		// .getOrganization().getId(), domain.getHouseCode(), domain
		// .getId());
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
		RentalHouse rentalHouses = new RentalHouse();
		// fateson add 如果没有说明是出租房导入 默认是出租房 excel中去掉了 是否是出租房的选项
		// start
		if (domain.getIsRentalHouse() == null) {
			domain.setIsRentalHouse(true);
		}
		// end
		if (domain.getIsRentalHouse()) {
			HouseInfo houseInfo = new HouseInfo();
			PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class,
					houseInfo, domain, new String[] { "id" });
			// 新增实有房
			HouseInfo temp = actualHouseService.addHouseInfo(houseInfo);
			domain.setHouseId(temp.getId());
			temp.setIsRentalHouse(true);
			// 新增 出租房
			rentalHouses = rentalHouseService.addHouseInfo(domain);
		}
		// 这里使用 地址确定唯一性 但是没必要 没必要
		/*
		 * if (domain.getIsRentalHouse()) { // 这里需要县新增 实有房屋在 使用使用房屋中的 houseid
		 * 设置到 rentalhouse中的houseid中去 HouseInfo temp = actualHouseService
		 * .getHouseInfoByHouseAddressAndOrgId( domain.getAddress(),
		 * domain.getOrganization() .getId()); // 添加实有房 if (temp == null) { //
		 * 复制出租房中的值 HouseInfo houseInfo = new HouseInfo();
		 * PropertyUtil.copyPropertiesWithRecursion(HouseInfo.class, houseInfo,
		 * domain, new String[] { "id" }); temp =
		 * actualHouseService.addHouseInfo(houseInfo);
		 * domain.setHouseId(temp.getId()); temp.setIsRentalHouse(true);
		 * rentalHouses = rentalHouseService.addHouseInfo(domain); } else { //
		 * 否则一经存在 temp.setIsRentalHouse(true); temp =
		 * actualHouseService.updateHouseInfo(temp); // 如果不是出租房 ，现在导入该房屋为出租房 if
		 * (!temp.getIsRentalHouse()) { domain.setHouseId(temp.getId());
		 * temp.setIsRentalHouse(true); rentalHouses =
		 * rentalHouseService.addHouseInfo(domain); } else { //
		 * 如果该房屋先前就是出租房，只需要修改出租房即可 // TODO } } }
		 */

		return rentalHouses;

	}

	@Override
	public RentalHouse updateDomain(RentalHouse domain) {
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
		// fateson add 如果没有说明是出租房导入 默认是出租房 excel中去掉了 是否是出租房的选项
		// start
		if (domain.getIsRentalHouse() == null) {
			domain.setIsRentalHouse(true);
		}
		// end
		if (domain.getIsRentalHouse()) {
			this.housesDependentService
					.synActualHouseToRentalHouseForUpdate(domain);
		}
		rentalHouseService.updateHouseBaseInfo(domain);
		return rentalHouseService.updateHouseBusinessInfo(domain);
	}

	@Override
	public ValidateResult validate(RentalHouse house, int realRow) {
		ValidateResult result = new ValidateResult();
		ExcelImportHelper.realRow.set(realRow);
		house.setIsRentalHouse(true);// 出租屋导入设置为true，用于区分实有房屋导入和出租屋导入
		result = updateValidator.validate(house);
		result.addAllErrorMessage(updateValidator
				.validateSpecializedInfo(house));
		return result;
	}

}
