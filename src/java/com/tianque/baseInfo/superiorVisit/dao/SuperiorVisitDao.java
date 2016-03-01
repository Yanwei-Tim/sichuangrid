package com.tianque.baseInfo.superiorVisit.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.superiorVisit.domain.SuperiorVisit;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchSuperiorVisitVo;
import com.tianque.domain.vo.VisitTypeVo;

/**
 * 重点上访人员数据库操作接口。
 */
public interface SuperiorVisitDao extends
		BaseInfoPopulationBaseDao<SuperiorVisit, SearchSuperiorVisitVo> {

	/**
	 * 根据查询条件分页查询信访人员
	 */
	public PageInfo<SuperiorVisit> findSuperiorVisitForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize, String sidx, String sord,
			Long isEmphasis);

	public void updateEmphasiseById(Long id, Long isEmphasis);

	/**
	 * 新增重点上访人员上访类别
	 */
	public void addSuperiorVisitType(Long superiorVisitId, Long visitType);

	/**
	 * 删除重点上访人员上访类别
	 */
	public void deleteVisitTypeBySuperiorVisitId(Long superiorVisitId);

	/**
	 * 根据上访人员id查找对应重点上访人员类别
	 */
	public List<VisitTypeVo> findVisitTypeById(Long superiorVisitId);

	public void updateDeathAndLogoutStateById(Long id, Boolean death, Long logoutState);
	
	public void updateTableUpdateDateById(Long id);
}
