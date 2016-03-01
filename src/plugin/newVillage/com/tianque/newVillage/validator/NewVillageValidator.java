package com.tianque.newVillage.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.dataconvert.ValidateHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.domain.NewVillage;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Component("newVillageValidator")
public class NewVillageValidator implements DomainValidator<NewVillage> {
	@Autowired
	private ValidateHelper validateHelper;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public ValidateResult validate(NewVillage domain) {
		if (domain == null) {
			throw new BusinessValidationException("操作失败，请重试");
		}
		ValidateResult result = new ValidateResult();
		// 验证BasicYearInfo中orgid是否是乡镇层级
		if (domain.getOrganization() == null
				|| domain.getOrganization().getId() == null) {
			throw new BusinessValidationException("请选择层级");
		}
		if (organizationDubboService
				.getFullOrgById(domain.getOrganization().getId()).getOrgLevel()
				.getInternalId() != OrganizationLevel.VILLAGE) {
			throw new BusinessValidationException("层级错误,操作失败");
		}

		// 验证settlementsNumber聚居点数量
		if (domain.getSettlementsNumber() != null) {
			if (domain.getSettlementsNumber() > 99999999
					|| domain.getSettlementsNumber() < 0) {
				result.addErrorMessage("聚居点数量应在范围0-99999999");
			}
		}
		// 验证 新建数
		if (domain.getSettlementsBuild() != null) {
			if (domain.getSettlementsBuild() > 99999999
					|| domain.getSettlementsBuild() < 0) {
				result.addErrorMessage("新建数应在范围0-99999999");
			}
		}
		// 验证 改造数
		if (domain.getSettlementsReform() != null) {
			if (domain.getSettlementsReform() > 99999999
					|| domain.getSettlementsReform() < 0) {
				result.addErrorMessage("改造数应在范围0-99999999");
			}
		}
		// 验证 保护数
		if (domain.getSettlementsProtect() != null) {
			if (domain.getSettlementsProtect() > 99999999
					|| domain.getSettlementsProtect() < 0) {
				result.addErrorMessage("保护数应在范围0-99999999");
			}
		}
		//验证新建数+改造数+保护数是否大于总数
		if(domain.getSettlementsNumber()<domain.getSettlementsBuild()+domain.getSettlementsReform()+domain.getSettlementsProtect()){
			result.addErrorMessage("聚居点新建数、改造数、保护数之和不能大于聚居点总数");
		}
		// 验证 涉及农户
		if (domain.getInvolvingFarmers() != null) {
			if (domain.getInvolvingFarmers() > 99999999
					|| domain.getInvolvingFarmers() < 0) {
				result.addErrorMessage("涉及农户应在范围0-99999999");
			}
		}
		// 验证 houseCount房屋总数
		if (domain.getHouseCount() != null) {
			if (domain.getHouseCount() > 99999999
					|| domain.getHouseCount() < 0) {
				result.addErrorMessage("房屋总数应在范围0-99999999");
			}
		}
//		// 验证 masonryStructure砖混结构户数
//		if (domain.getMasonryStructure() != null) {
//			if (domain.getMasonryStructure() > 99999999
//					|| domain.getMasonryStructure() < 0) {
//				result.addErrorMessage("砖混结构户数应在范围0-99999999");
//			}
//			if (domain.getHouseCount() != null
//					&& domain.getHouseCount() < domain.getMasonryStructure()) {
//				result.addErrorMessage("砖混结构户数应该不大于房屋总数");
//			}
//		}
		// 验证无房户数
		if (domain.getNoHouseCount() != null) {
			if (domain.getNoHouseCount() > 99999999
					|| domain.getNoHouseCount() < 0) {
				result.addErrorMessage("无房户数应在范围0-99999999");
			}
		}
		// 验证危房户数
		if (domain.getDangerousHouseCount() != null) {
			if (domain.getDangerousHouseCount() > 99999999
					|| domain.getDangerousHouseCount() < 0) {
				result.addErrorMessage("危房户数应在范围0-99999999");
			}
		}
		// 验证housingDifficultCount住房困难户数
		if (domain.getHousingDifficultCount() != null) {
			if (domain.getHousingDifficultCount() > 99999999
					|| domain.getHousingDifficultCount() < 0) {
				result.addErrorMessage("住房困难户数应在范围0-99999999");
			}
		}
		// 验证lowRentHousing廉租房数
		if (domain.getLowRentHousing() != null) {
			if (domain.getLowRentHousing() > 99999999
					|| domain.getLowRentHousing() < 0) {
				result.addErrorMessage("廉租房数应在范围0-99999999");
			}
		}
		
		//验证农村人均安全住房面积
		if (domain.getCapitaHousingArea()!= null) {
			if (domain.getCapitaHousingArea() > 999999999.99
					|| domain.getCapitaHousingArea() < 0) {
				result.addErrorMessage("农村人均安全住房面积应在范围0-999999999.99");
			}
		}
		//验证入住廉租房数
		if (domain.getInLowRentHousing() != null) {
			if (domain.getInLowRentHousing() > 99999999
					|| domain.getInLowRentHousing() < 0) {
				result.addErrorMessage("入住廉租房数应在范围0-99999999");
			}
			if(domain.getInLowRentHousing()!=null && domain.getInLowRentHousing()>domain.getInLowRentHousing()){
				result.addErrorMessage("入住廉租房数应该小于廉租房总数");
			}
		}
		//验证廉租涉及人数
		if (domain.getNumberInvolved() != null) {
			if (domain.getNumberInvolved() > 99999999
					|| domain.getNumberInvolved() < 0) {
				result.addErrorMessage("廉租房涉及人数范围在0-99999999");
			}
		}
		return result;
	}
}
