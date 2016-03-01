package com.tianque.partyBuilding.baseInfos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.partyBuilding.baseInfos.dao.CaseImageUploadDao;
import com.tianque.partyBuilding.baseInfos.domain.CaseImageUpload;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchCaseImageUploadVo;

/**
 * 图片上传表:数据操作层
 * 
 * @author
 */
@Repository("caseImageUploadDao")
public class CaseImageUploadDaoImpl extends BaseDaoImpl<CaseImageUpload, SearchCaseImageUploadVo> implements
		CaseImageUploadDao {

	@Override
	public List<CaseImageUpload> getCaseImageUploadsByOrgId(Long orgId) {
		return getSqlMapClientTemplate().queryForList("caseImageUpload.getCaseImageUploadsByOrgId", orgId);
	}

	@Override
	public List<CaseImageUpload> findByOrgIdAndType(Long orgId, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("baseInfoType", type);
		return getSqlMapClientTemplate().queryForList("caseImageUpload.findByOrgIdAndType", map);
	}

	@Override
	public boolean deleteCaseImageUploadByIdAndOrgId(Long id, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("orgId", orgId);
		return getSqlMapClientTemplate().delete("caseImageUpload.deleteCaseImageUploadByIdAndOrgId", map) > 0;
	}

}
