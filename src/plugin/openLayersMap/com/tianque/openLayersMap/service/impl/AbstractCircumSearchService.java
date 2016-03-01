package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.openLayersMap.domian.vo.CircumInfoVo;
import com.tianque.openLayersMap.service.BaseService;
import com.tianque.openLayersMap.service.CircumSearchService;
import com.tianque.openLayersMap.service.SysGisTypeManageService;

/**
 * 周边搜索抽象类（重点人员、重点场所）
 * 
 * @date 2013-3-15
 * @param <T>
 */
public abstract class AbstractCircumSearchService<T> extends BaseService
		implements CircumSearchService<T> {

	@Autowired
	protected SysGisTypeManageService sysGisTypeManageService;

	@Override
	public PageInfo<T> findCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (circumInfoVo == null || circumInfoVo.getElements() == null
				|| circumInfoVo.getRange() == null
				|| circumInfoVo.getLon() == null
				|| circumInfoVo.getLat() == null) {
			throw new BusinessValidationException("参数错误!");
		}
		String[] temp = circumInfoVo.getElements().split(",");
		StringBuffer elements_ = new StringBuffer();

		for (int i = 0; i < temp.length; i++) {
			if (temp[i].trim().length() == 0)
				continue;
			elements_.append("'");
			elements_.append(temp[i]);
			elements_.append("'");
			if (i < temp.length - 1) {
				elements_.append(",");
			}
		}
		circumInfoVo.setElements(elements_.toString());
		PageInfo<T> pageInfo = doFindCircumInfoByElementsAndRangeAndCenterLonLat(
				circumInfoVo, pageNum, pageSize, sidx, sord);
		return pageInfo;
	}

	protected abstract PageInfo<T> doFindCircumInfoByElementsAndRangeAndCenterLonLat(
			CircumInfoVo circumInfoVo, Integer pageNum, Integer pageSize,
			String sidx, String sord);

}
