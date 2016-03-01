package com.tianque.openLayersMap.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.dao.PersonTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.HousePropertyInfoVo;
import com.tianque.openLayersMap.domian.vo.PersonInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.HousePropertyService;
import com.tianque.openLayersMap.service.PersonService;
import com.tianque.openLayersMap.util.GisGlobalValueMap;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.PropertyDictService;

/**
 * 二维地图 人口信息个性化方法的实现
 * 
 * @author zhanghuafei
 * 
 */
@Service("personService")
public class PersonServiceImpl extends BaseService implements PersonService {
	@Autowired
	private PersonTwoDimensionMapDao personTwoDimensionMapDao;
	@Autowired
	private HousePropertyService housePropertyService;
	@Autowired
	private PropertyDictService propertyDictService;

	@Autowired
	private ShardConversion shardConversion;

	@Override
	public Integer countTwoDimensionMapPageInfoByOrgInternalCodeAndTableName(
			String orgInternalCode, String tableName) {
		if (null == orgInternalCode || null == tableName) {
			throw new BusinessValidationException("参数错误!");
		}
		return personTwoDimensionMapDao.countTwoDimensionMapPageInfoByOrgId(
				orgInternalCode, tableName,
				shardConversion.getShardCodeByOrgCode(orgInternalCode));
	}

	@Override
	public Integer countHouseHasPopulation(Long houseId, String type) {
		if (null == houseId || null == type) {
			throw new BusinessValidationException("参数错误!");
		}
		return personTwoDimensionMapDao.countHouseHasPopulation(houseId, type);
	}

	@Override
	public PageInfo<PersonInfoVo> findPersonByOrgIdAndTypeName(Long orgId,
			Long buildDataId, String typeName, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		if (orgId == null || typeName == null || buildDataId == null) {
			throw new BusinessValidationException("参数错误!");
		}

		PageInfo<PersonInfoVo> resultPageInfo = new PageInfo<PersonInfoVo>();
		List<HousePropertyInfoVo> houInfoVos = housePropertyService
				.findHousePropertyInfoVoListByBuilddatasId(buildDataId, orgId);
		List<PersonInfoVo> personInfoVos = new ArrayList<PersonInfoVo>();
		PageInfo<PersonInfoVo> pageInfo = new PageInfo<PersonInfoVo>();
		String shardCode = shardConversion.getShardCode(orgId);
		for (int i = 0; i < houInfoVos.size(); i++) {
			HousePropertyInfoVo housePropertyInfoVo = houInfoVos.get(i);
			if (housePropertyInfoVo != null
					&& housePropertyInfoVo.getId() != null) {
				if (typeName.equals("person")) {
					pageInfo = personTwoDimensionMapDao.findPersonsByHouseId(
							housePropertyInfoVo.getId(), pageNum, pageSize,
							sidx, sord, shardCode);
					setDisplayName(pageInfo.getResult());
				} else {
					String tableName = GisGlobalValueMap.keyPersonalType
							.get(typeName);
					String personTypeName = GisGlobalValueMap.keyPersonal
							.get(tableName);
					pageInfo = personTwoDimensionMapDao
							.findPersonsByHouseIdAndType(
									housePropertyInfoVo.getId(), tableName,
									personTypeName, typeName, pageNum,
									pageSize, sidx, sord, shardCode);
					setDisplayName(pageInfo.getResult());
				}
			}
			personInfoVos.addAll(pageInfo.getResult());
		}
		resultPageInfo.setResult(personInfoVos);
		resultPageInfo.setTotalRowSize(personInfoVos.size());
		return resultPageInfo;
	}

	private void setDisplayName(List<PersonInfoVo> list) {
		if (list == null || list.isEmpty()) {
			return;
		}
		for (Iterator<PersonInfoVo> it = list.iterator(); it.hasNext();) {
			PersonInfoVo bean = it.next();
			PropertyDict dict = null;
			if (bean.getGenderId() != null
					&& bean.getGenderId().getId() != null) {
				dict = propertyDictService.getPropertyDictById(bean
						.getGenderId().getId());
				if (dict != null) {
					bean.setGender(dict.getDisplayName());
					bean.setGenderId(dict);
				}
			}
			PropertyDict dict2 = null;
			if (bean.getCertificateTypeId() != null
					&& bean.getCertificateTypeId().getId() != null) {
				if (bean.getCertificateTypeId().getId() == 0) {
					bean.setCertificateType("身份证");
				} else {
					dict2 = propertyDictService.getPropertyDictById(bean
							.getCertificateTypeId().getId());
				}
			}
			if (dict2 != null) {
				bean.setCertificateType(dict2.getDisplayName());
				bean.setCertificateTypeId(dict2);
			}
		}
	}

	@Override
	public Integer countPopulationByBuildDataId(Long buildDataId, String type,
			Long orgId) {
		if (buildDataId == null) {
			throw new BusinessValidationException("楼宇id不能为空！");
		}
		return personTwoDimensionMapDao.countPopulationByBuildDataId(
				buildDataId, type, shardConversion.getShardCode(orgId));
	}

}
