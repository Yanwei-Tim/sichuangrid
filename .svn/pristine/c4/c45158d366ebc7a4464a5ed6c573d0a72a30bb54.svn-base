package com.tianque.newVillage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.newVillage.dao.BasicYearInfoDao;
import com.tianque.newVillage.domain.BasicYearInfo;
import com.tianque.newVillage.domain.NewVillageBasic;
import com.tianque.newVillage.service.BasicYearInfoService;
import com.tianque.newVillage.service.NewVillageBasicService;
import com.tianque.service.impl.LogableService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/**
 * @ClassName: BasicYearInfoServiceImpl
 * 
 */
@Service("basicYearInfoService")
@Transactional
public class BasicYearInfoServiceImpl extends LogableService implements
		BasicYearInfoService {
	@Autowired
	private BasicYearInfoDao basicYearInfoDao;
	@Autowired
	private NewVillageBasicService newVillageBasicService;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Qualifier("basicYearInfoValidator")
	@Autowired
	private DomainValidator<BasicYearInfo> domainValidator;

	@Override
	public BasicYearInfo addBasicYearInfo(BasicYearInfo basicYearInfo) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(basicYearInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			//查出是否有属于同一年份,同一组织的信息,查到则update
			NewVillageBasic newVillageBasic = newVillageBasicService
					.getNewVillageBasicById(basicYearInfo.getNewVillageBasic()
							.getId());
			List<BasicYearInfo> basicYearInfos = getBasicYearInfoByYearAndOrgId(
					newVillageBasic.getYear(), newVillageBasic
							.getOrganization().getId());
			//待带进*********
			//List<Long> ids = new ArrayList<Long>();
			for (BasicYearInfo oldbasicYearInfo : basicYearInfos) {
				//Long id = oldbasicYearInfo.getId();
				//ids.add(id);
				oldbasicYearInfo.setTotalPeopleNum(basicYearInfo
						.getTotalPeopleNum());
				oldbasicYearInfo.setTotalHouseholdsNum(basicYearInfo
						.getTotalHouseholdsNum());
				oldbasicYearInfo.setWastelandArea(basicYearInfo
						.getWastelandArea());
				oldbasicYearInfo.setWoodlandArea(basicYearInfo
						.getWoodlandArea());
				oldbasicYearInfo.setCultivatedLandArea(basicYearInfo
						.getCultivatedLandArea());
				oldbasicYearInfo.setOutWorkNum(basicYearInfo.getOutWorkNum());
				oldbasicYearInfo.setTotalArea(basicYearInfo.getTotalArea());
				oldbasicYearInfo.setLandTransfer(basicYearInfo
						.getLandTransfer());
				
				oldbasicYearInfo.setCollectiveFunds(basicYearInfo.getCollectiveFunds());
				oldbasicYearInfo.setCollectiveAssets(basicYearInfo.getCollectiveAssets());
				oldbasicYearInfo.setAssetsDescribe(basicYearInfo.getAssetsDescribe());
				
				basicYearInfoDao.updateBasicYearInfo(oldbasicYearInfo);
			}
			return basicYearInfoDao.addBasicYearInfo(basicYearInfo);
		} catch (Exception e) {
			throw new BusinessValidationException("新增信息出现错误");
		}
	}

	@Override
	public BasicYearInfo getBasicYearInfoById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("获取信息失败，未获得相关参数");
		}
		return basicYearInfoDao.getBasicYearInfoById(id);
	}

	@Override
	public void deleteBasicYearInfoById(String[] id) {
		if (id == null || id.length == 0) {
			throw new BusinessValidationException("删除数据失败，未获得相关参数");
		}
		basicYearInfoDao.deleteBasicYearInfoById(id);
	}

	@Override
	public BasicYearInfo updateBasicYearInfo(BasicYearInfo basicYearInfo) {
		// 数据校验
		ValidateResult baseDataValidator = domainValidator
				.validate(basicYearInfo);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		try {
			NewVillageBasic newVillageBasic = newVillageBasicService
					.getNewVillageBasicById(basicYearInfo.getNewVillageBasic()
							.getId());
			//带改
			List<BasicYearInfo> basicYearInfos = basicYearInfoDao
					.getBasicYearInfoByYearAndOrgId(newVillageBasic.getYear(),
							newVillageBasic.getOrganization().getId());
			//List<Long> ids = new ArrayList<Long>();
			for (BasicYearInfo oldbasicYearInfo : basicYearInfos) {
				//Long id = oldbasicYearInfo.getId();
				//ids.add(id);
				oldbasicYearInfo.setTotalPeopleNum(basicYearInfo
						.getTotalPeopleNum());
				oldbasicYearInfo.setTotalHouseholdsNum(basicYearInfo
						.getTotalHouseholdsNum());
				oldbasicYearInfo.setWastelandArea(basicYearInfo
						.getWastelandArea());
				oldbasicYearInfo.setWoodlandArea(basicYearInfo
						.getWoodlandArea());
				oldbasicYearInfo.setCultivatedLandArea(basicYearInfo
						.getCultivatedLandArea());
				oldbasicYearInfo.setOutWorkNum(basicYearInfo.getOutWorkNum());
				oldbasicYearInfo.setTotalArea(basicYearInfo.getTotalArea());
				oldbasicYearInfo.setLandTransfer(basicYearInfo
						.getLandTransfer());
				oldbasicYearInfo.setCollectiveFunds(basicYearInfo.getCollectiveFunds());
				oldbasicYearInfo.setCollectiveAssets(basicYearInfo.getCollectiveAssets());
				oldbasicYearInfo.setAssetsDescribe(basicYearInfo.getAssetsDescribe());
				basicYearInfoDao.updateBasicYearInfo(oldbasicYearInfo);
			}
			/*if (ids.size() > 0) {
				basicYearInfoDao.batchUpdateBasicYearInfo(basicYearInfo, ids);
			}*/
			return basicYearInfoDao.updateBasicYearInfo(basicYearInfo);
		} catch (Exception e) {
			throw new BusinessValidationException("修改信息出现错误");
		}
	}

	@Override
	public BasicYearInfo getBasicYearInfoByBasicId(Long id) {
		if (id == null) {
			throw new BusinessValidationException("获取信息失败，未获得相关参数");
		}
		return basicYearInfoDao.getBasicYearInfoByBasicId(id);
	}

	@Override
	public List<BasicYearInfo> getBasicYearInfoByYearAndOrgId(Integer year,
			Long orgId) {
		if (year == null || year <= 0 || orgId == null || orgId <= 0) {
			throw new BusinessValidationException("相关参数错误");
		}
		return basicYearInfoDao.getBasicYearInfoByYearAndOrgId(year, orgId);
	}
}
