package com.tianque.plugin.dataManage.base.service;

import org.springframework.stereotype.Component;
import org.unitils.database.annotations.Transactional;

import com.tianque.baseInfo.companyPlace.domain.CompanyPlace;
import com.tianque.core.base.BaseDomain;
import com.tianque.domain.LocationBaseInfo;
import com.tianque.domain.NewEconomicOrganizations;
import com.tianque.plugin.dataManage.TargetDataVo;
import com.tianque.plugin.dataManage.base.ReflectionUtil;
import com.tianque.plugin.dataManage.location.actualHouseTemp.domain.ActualHouseTemp;
import com.tianque.plugin.dataManage.location.allPlaceCommonTemp.domain.AllPlaceCommonTemp;
import com.tianque.plugin.dataManage.location.companyPlaceTemp.domain.CompanyPlaceTemp;
import com.tianque.plugin.dataManage.location.neweconomicOrganizationsTemp.domain.NewEconomicOrganizationsTemp;
import com.tianque.plugin.dataManage.location.rentalHouseTemp.domain.RentalHouseTemp;

/**
 * @ClassName: LocationDataManageServiceImp
 * @author N-147
 * @date 2013-8-8 下午8:04:00
 */
@Component("locationDataManageService")
@Transactional
public class LocationDataManageServiceImpl extends AbstractDataManageService {

	@Override
	public TargetDataVo getTargetDataVo(Object population) {
		if (population instanceof RentalHouseTemp || population instanceof ActualHouseTemp) {
			return null;
		} else if (population instanceof NewEconomicOrganizationsTemp) {
			NewEconomicOrganizations location = null;
			try {
				location = (NewEconomicOrganizations) ReflectionUtil
						.executeLocationHasDuplicateMethod(population, "getPlaceName");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != location) {
				TargetDataVo vo = new TargetDataVo();
				if (null != location.getIsEmphasis()) {
					vo.setLogout(location.getIsEmphasis());
				}
				vo.setId(location.getId());
				return vo;
			}
		} else if (population instanceof AllPlaceCommonTemp) {
			LocationBaseInfo location = null;
			try {
				location = (LocationBaseInfo) ReflectionUtil.executeLocationHasDuplicateMethod(
						population, "getPlaceName");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != location) {
				TargetDataVo vo = new TargetDataVo();
				if (null != location.getIsEmphasis()) {
					vo.setLogout(location.getIsEmphasis() ? 1L : 0L);
				}
				vo.setId(location.getId());
				return vo;
			}
		} else if (population instanceof CompanyPlaceTemp) {
			CompanyPlace location = null;
			try {
				location = (CompanyPlace) ReflectionUtil.executeLocationHasDuplicateMethod(
						population, "getPlaceName");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != location) {
				TargetDataVo vo = new TargetDataVo();
				if (null != location.getIsEmphasis()) {
					vo.setLogout(Long.parseLong(String.valueOf(location.getIsEmphasis())));
				}
				vo.setId(location.getId());
				return vo;
			}
		} else {
			BaseDomain location = null;
			try {
				location = (BaseDomain) ReflectionUtil.executeLocationHasDuplicateMethod(
						population, "getAddress");
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (null != location) {
				TargetDataVo vo = new TargetDataVo();
				vo.setId(location.getId());
				return vo;
			}
		}
		return null;
	}

	@Override
	protected void asynchronousProcess(BaseDomain temp) {
		// TODO Auto-generated method stub

	}

	@Override
	public void validCanClaim(BaseDomain population) {
		// TODO Auto-generated method stub

	}

	@Override
	public BaseDomain dataConvertForLocation(BaseDomain population) {
		return population;
	}

}
