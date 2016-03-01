package com.tianque.openLayersMap.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.openLayersMap.dao.IssueTwoDimensionMapDao;
import com.tianque.openLayersMap.domian.vo.IssueInfoVo;
import com.tianque.openLayersMap.util.GisGlobalValueMap;

@Service("issueMapDetailViewService")
public class IssueDetailViewServiceImpl extends
		AbstractDetailViewService<IssueInfoVo> {

	@Autowired
	private IssueTwoDimensionMapDao issueTwoDimensionMapDao;

	@Override
	protected IssueInfoVo doGetViewPopupInfoByIdAndTableNameAndType(Long id,
			String tableName, String type, Long orgId) {
		IssueInfoVo issueInfoVo = null;
		if (tableName.equals(GisGlobalValueMap.PERSONALREADYTHING)) {// 已办
			issueInfoVo = issueTwoDimensionMapDao
					.getAlreadyViewPopupInfoById(id);
		} else if (tableName.equals(GisGlobalValueMap.ALREADYTHING)
				|| tableName.equals(GisGlobalValueMap.GONETHROUGH)) {// 已办结
			issueInfoVo = issueTwoDimensionMapDao
					.getAlreadyThingViewPopupInfoById(id);
		} else {
			issueInfoVo = issueTwoDimensionMapDao.getViewPopupInfoById(id);
		}

		return issueInfoVo;
	}

	@Override
	protected boolean validateParams(Long id, String tableName, String type) {
		return id != null;
	}

}
