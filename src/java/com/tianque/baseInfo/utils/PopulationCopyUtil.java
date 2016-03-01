package com.tianque.baseInfo.utils;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.floatingPopulation.domain.FloatingPopulation;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;

public class PopulationCopyUtil {
	
	public static void copyBaseInfoMessage(Countrymen copy,Countrymen population){
		if(copy == null || population == null){
			return;
		}
		population.setCareer(copy.getCareer());
		population.setDeath(copy.isDeath());
		population.setPoliticalBackground(copy
				.getPoliticalBackground());
		population.setNativePoliceStation(copy
				.getNativePoliceStation());
		population
				.setMaritalState(copy.getMaritalState());
		population.setUsedName(copy.getUsedName());
		population.setNation(copy.getNation());
		population.setFaith(copy.getFaith());
		population.setSchooling(copy.getSchooling());
		population.setBloodType(copy.getBloodType());
		population.setNativePlaceAddress(copy
				.getNativePlaceAddress());
		population.setProvince(copy.getProvince());
		population.setCity(copy.getCity());
		population.setDistrict(copy.getDistrict());
		population.setName(copy.getName());
		population.setIdCardNo(copy.getIdCardNo());
		population.setBirthday(copy.getBirthday());
		population.setEmail(copy.getEmail());
		population.setGender(copy.getGender());
		population.setFullPinyin(copy.getFullPinyin());
		population
				.setSimplePinyin(copy.getSimplePinyin());
		population
				.setMobileNumber(copy.getMobileNumber());
		population.setTelephone(copy.getTelephone());
		population.setStature(copy.getStature());
		population.setWorkUnit(copy.getWorkUnit());
		population.setImgUrl(copy.getImgUrl());
	}
	
	public static void copyAddressInfoMessage(Countrymen copy,Countrymen population){
		if(copy == null || population == null){
			return;
		}
		population.setIsHaveHouse(copy.getIsHaveHouse());
		population.setNoHouseReason(copy
				.getNoHouseReason());
		population.setCurrentAddress(copy
				.getCurrentAddress());
		population
				.setOtherAddress(copy.getOtherAddress());
		population.setRemark(copy.getRemark());
	}
	
	public static void copyFloatingPopulationMessage(FloatingPopulation copy,FloatingPopulation population){
		if(copy == null || population == null){
			return;
		}
		population.setBaseInfoId(copy.getBaseInfoId());
		population.setAddressInfoId(copy.getAddressInfoId());
		population.setLogOut(copy.getLogOut());
		population.setIsInflowing(copy.getIsInflowing());
		population.setInflowingReason(copy.getInflowingReason());
		population.setInflowingDate(copy.getInflowingDate());
		population.setInflowingProvince(copy.getInflowingProvince());
		population.setInflowingCity(copy.getInflowingCity());
		population.setInflowingDistrict(copy.getInflowingDistrict());
		population.setRegistrationType(copy.getRegistrationType());
		population.setRegisterDate(copy.getRegisterDate());
		population.setExpectedDatedue(copy.getExpectedDatedue());
		population.setCertificateNumber(copy.getCertificateNumber());
		population.setStayLocationType(copy.getStayLocationType());
		population.setEconomySource(copy.getEconomySource());
		population.setStayTimeLimit(copy.getStayTimeLimit());
		population.setPreparedStayTimeLimit(copy.getPreparedStayTimeLimit());
		population.setHasMarriedProve(copy.getHasMarriedProve());
		population.setResidenceType(copy.getResidenceType());
		population.setLogoutDetail(copy.getLogoutDetail());
		population.setSettleTime(copy.getSettleTime());
		population.setSourcesState(copy.getSourcesState());
		population.setOrganization(copy.getOrganization());
		population.setOrgInternalCode(copy.getOrgInternalCode());
		population.setCreateDate(copy.getCreateDate());
		population.setCreateUser(copy.getCreateUser());
		population.setUpdateDate(copy.getUpdateDate());
		population.setUpdateUser(copy.getUpdateUser());
		
	}
	
	public static void copyHouseholdStaffMessage(HouseholdStaff copy,HouseholdStaff population){
		if(copy == null || population == null){
			return;
		}
		population.setHomePhone(copy.getHomePhone());
		population.setBaseInfoId(copy.getBaseInfoId());
		population.setAddressInfoId(copy.getAddressInfoId());
		population.setRelationShipWithHead(copy.getRelationShipWithHead());
		population.setLogOut(copy.getLogOut());
		population.setCensusRegisterFamily(copy.getCensusRegisterFamily());
		population.setResidenceType(copy.getResidenceType());
		population.setAccountNumber(copy.getAccountNumber());
		population.setOutGone(copy.getOutGone());
		population.setOutReasons(copy.getOutReasons());
		population.setReasonsDate(copy.getReasonsDate());
		population.setOutProvince(copy.getOutProvince());
		population.setOutCity(copy.getOutCity());
		population.setOutDistrict(copy.getOutDistrict());
		population.setGoOutDetailedAddress(copy.getGoOutDetailedAddress());
		population.setResidentStatus(copy.getResidentStatus());
		population.setLogoutDetail(copy.getLogoutDetail());
		population.setSettleTime(copy.getSettleTime());
		population.setSourcesState(copy.getSourcesState());
		population.setHomePhone(copy.getHomePhone());
		population.setOrganization(copy.getOrganization());
		population.setOrgInternalCode(copy.getOrgInternalCode());
		population.setCreateDate(copy.getCreateDate());
		population.setCreateUser(copy.getCreateUser());
		population.setUpdateDate(copy.getUpdateDate());
		population.setUpdateUser(copy.getUpdateUser());
		
	}
}
