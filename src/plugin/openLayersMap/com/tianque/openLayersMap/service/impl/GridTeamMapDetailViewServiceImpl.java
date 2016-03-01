package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.openLayersMap.dao.GridTeamMapDao;
import com.tianque.openLayersMap.domian.vo.GirdTeamVo;

@Service("gridTeamMapDetailViewService")
public class GridTeamMapDetailViewServiceImpl extends
		AbstractDetailViewService<GirdTeamVo> {

	@Autowired
	private GridTeamMapDao gridTeamMapDao;

	@Override
	protected GirdTeamVo doGetViewPopupInfoByIdAndTableNameAndType(Long id,
			String tableName, String type, Long orgId) {
		return gridTeamMapDao.getGirdTeamVoInfoById(id);
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null;
	}

}
