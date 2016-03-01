package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.service.BaseInfoService;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.dao.PopulationTypeDao;
import com.tianque.domain.PopulationTypeBean;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.jms.OperateMode;
import com.tianque.plugin.tqSearch.sqlMap.UpdateSqlMap;
import com.tianque.service.PopulationTypeService;
import com.tianque.service.util.PopulationType;
import com.tianque.shard.util.ShardConversion;

@Service("populationTypeService")
@Transactional
public class PopulationTypeServiceImpl implements PopulationTypeService {

	@Autowired
	private PopulationTypeDao populationTypeDao;
	@Autowired
	private BaseInfoService baseInfoService;
	@Autowired
	private ShardConversion shardConversion;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PopulationTypeService#addPopulationType(com.tianque
	 * .domain.PopulationType )
	 */
	@Override
	public void addPopulationType(PopulationTypeBean populationType) {
		try {
			this.populationTypeDao.addPopulationType(populationType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的addPopulationType方法出现异常，原因：",
					"保存实口与业务关联关系出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PopulationTypeService#
	 * deletePopulationTypeByActualIdAndType(java.lang .Long, java.lang.String)
	 */
	@Override
	public void deletePopulationTypeByActualIdAndType(Long actualId,
			String actualType) {
		try {
			this.populationTypeDao.deletePopulationTypeByActualIdAndType(
					actualId, actualType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的deletePopulationTypeByActualIdAndType方法出现异常，原因：",
					"删除实口与业务关联关系出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PopulationTypeService#
	 * deletePopulationTypeByPopulationIdAndType(java. lang.Long,
	 * java.lang.String)
	 */
	@Override
	@SolrServerIndex(mode=OperateMode.EDIT,value=UpdateSqlMap.POPULATION_TYPE_KEY)
	public void deletePopulationTypeByPopulationIdAndType(Long populationId,
			String populationType) {
		try {
			this.populationTypeDao.deletePopulationTypeByPopulationIdAndType(
					populationId, populationType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的deletePopulationTypeByPopulationIdAndType方法出现异常，原因：",
					"删除业务与实口关联关系出现错误", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.service.PopulationTypeService#getPopulationTypeByActualIdAndType
	 * (java.lang.Long, java.lang.String)
	 */
	@Override
	public List<PopulationTypeBean> getPopulationTypeByActualIdAndType(
			Long actualId, String actualType) {
		List<PopulationTypeBean> populationTypeList = new ArrayList<PopulationTypeBean>();
		try {
			populationTypeList = this.populationTypeDao
					.getPopulationTypeByActualIdAndType(actualId, actualType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的getPopulationTypeByActualIdAndType方法出现异常，原因：",
					"根据人口信息主键和类型获取关联关系出现错误", e);
		}
		return populationTypeList;
	}

	@Override
	public List<PopulationTypeBean> getPopulationTypeByActualIdAndType(
			Long actualId, String actualType, String orgInternalCode) {
		List<PopulationTypeBean> populationTypeList = new ArrayList<PopulationTypeBean>();
		try {
			populationTypeList = this.populationTypeDao
					.getPopulationTypeByActualIdAndType(actualId, actualType);
			for (PopulationTypeBean populationTypeBean : populationTypeList) {
				populationTypeBean.setOrgInternalCode(orgInternalCode);
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的getPopulationTypeByActualIdAndType方法出现异常，原因：",
					"根据人口信息主键和类型获取关联关系出现错误", e);
		}
		return populationTypeList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PopulationTypeService#
	 * getPopulationTypeByPopulationIdAndType(java.lang .Long, java.lang.String)
	 */
	@Override
	public PopulationTypeBean getPopulationTypeByPopulationIdAndType(
			Long populationId, String populationType) {
		PopulationTypeBean populationTypeBean = null;
		if (PopulationType.AIDSPOPULATIONS_KEY.equals(populationType)) {
			populationType = PopulationType.AIDSPOPULATIONS;
		}
		try {
			populationTypeBean = this.populationTypeDao
					.getPopulationTypeByPopulationIdAndType(populationId,
							populationType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的getPopulationTypeByPopulationIdAndType方法出现异常，原因：",
					"根据业务人员主键和类型获取关联关系出现错误", e);
		}
		return populationTypeBean;
	}

	@Override
	public PopulationTypeBean getPopulationTypeByPopulationIdAndType(
			Long populationId, String populationType, String orgInternalCode) {
		PopulationTypeBean populationTypeBean = null;
		if (PopulationType.AIDSPOPULATIONS_KEY.equals(populationType)) {
			populationType = PopulationType.AIDSPOPULATIONS;
		}
		try {
			populationTypeBean = this.populationTypeDao
					.getPopulationTypeByPopulationIdAndType(populationId,
							populationType);
			if(populationTypeBean == null){
				return populationTypeBean;
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的getPopulationTypeByPopulationIdAndType方法出现异常，原因：",
					"根据业务人员主键和类型获取关联关系出现错误", e);
		}
		populationTypeBean.setOrgInternalCode(orgInternalCode);
		return populationTypeBean;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.service.PopulationTypeService#
	 * deletePopulationTypeByActualIdAndTypeAndPopulationType (java.lang.Long,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public void deletePopulationTypeByActualIdAndTypeAndPopulationType(
			Long actualId, String actualType, String populationType) {
		try {
			this.populationTypeDao
					.deletePopulationTypeByActualIdAndTypeAndPopulationType(
							actualId, actualType, populationType);
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类PopulationTypeServiceImpl的deletePopulationTypeByActualIdAndTypeAndPopulationType方法出现异常，原因：",
					"删除人口信息与业务信息关联关系出现错误", e);
		}
	}

	@Override
	public PopulationTypeBean getActualPopulationTypeBeanByOrgIdAndIdCardNo(
			Long orgId, String idCardNo) {
		if (orgId == null || "".equals(idCardNo) || idCardNo == null) {
			throw new BusinessValidationException("身份证和网格不能为空");
		}
		Countrymen countrymen = baseInfoService.existBaseInfo(idCardNo);
		if (countrymen == null) {
			return null;
		}
		String shardCode = shardConversion.getShardCode(orgId);
		return populationTypeDao.getActualPopulationTypeBeanByOrgIdAndIdCardNo(
				orgId, countrymen.getId(), shardCode);
	}
}
