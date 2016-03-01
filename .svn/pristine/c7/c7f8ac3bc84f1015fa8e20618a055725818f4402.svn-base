package com.tianque.partyBuilding.baseInfos.dao;

import java.util.List;

import com.tianque.core.base.BaseDao;
import com.tianque.partyBuilding.baseInfos.domain.CaseImageUpload;
import com.tianque.partyBuilding.baseInfos.domain.vo.SearchCaseImageUploadVo;

/**
 * 图片上传表:数据操作层接口
 * 
 * @author
 */
public interface CaseImageUploadDao extends BaseDao<CaseImageUpload, SearchCaseImageUploadVo> {

	/**
	 * 根据组织机构id得到图片上传信息列表
	 * 
	 * @param id
	 * @return
	 */
	public List<CaseImageUpload> getCaseImageUploadsByOrgId(Long orgId);

	public List<CaseImageUpload> findByOrgIdAndType(Long orgId, String type);

	/**
	 * 根据id和orgId删除图片信息
	 * 
	 * @param id
	 * @param orgId
	 * @return
	 */
	public boolean deleteCaseImageUploadByIdAndOrgId(Long id, Long orgId);

}
