package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.openLayersMap.dao.BuildDataTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.BuildDataInfoVo;

/**
 * 楼宇详情查看实现类
 * 
 * @date 2013-3-18
 */
@Service("buildDataMapDetailViewService")
public class BuildDataMapDetailViewServiceImpl extends
		AbstractDetailViewService<BuildDataInfoVo> {

	@Autowired
	private BuildDataTwoDimensionMapDao buildDataTwoDimensionMapDao;

	@Override
	protected BuildDataInfoVo doGetViewPopupInfoByIdAndTableNameAndType(
			Long id, String childTableName, String type, Long orgId) {
		BuildDataInfoVo buildDataInfoVo = buildDataTwoDimensionMapDao
				.getBuildDataInfoById(id);
		return buildDataInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null;
	}

}
