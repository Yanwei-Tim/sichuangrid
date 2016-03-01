package com.tianque.baseInfo.optimalObject.dao;

import java.util.List;

import com.tianque.baseInfo.base.dao.BaseInfoPopulationBaseDao;
import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOptimalObjectVo;

/**
 * 优抚对象信息数据库操作接口。
 */
public interface OptimalObjectDao extends
		BaseInfoPopulationBaseDao<OptimalObject, SearchOptimalObjectVo> {

	/**
	 * 判断身份证信息是否存在
	 * 
	 * @param id
	 * @param idCard
	 * @return
	 */
	public OptimalObject getOptimalObjectByIdAndIdCardAndOrgId(Long id,
			List<String> idCardNoList, Long orgId);

	/**
	 * 根据查询条件分页查询优抚对象信息
	 * 
	 * @param queryStraitenedResident
	 *            优抚人员查询条件对象
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo<OptimalObject> findOptimalObjectForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	/**
	 * 根据查询条件高级查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchOptimalObjectVo
	 * @return
	 */
	public PageInfo<OptimalObject> searchOptimalObjects(Integer pageNum,
			Integer pageSize, SearchOptimalObjectVo searchOptimalObjectVo);

	/**
	 * 获取所有优抚对象信息
	 * 
	 * @param searchOptimalObjectVo
	 * @return
	 */
	public List<OptimalObject> searchAllOptimalObjects(
			SearchOptimalObjectVo searchOptimalObjectVo);

	public void updateEmphasiseById(Long long1, Long isEmphasis);

	public PageInfo fastSearchOptimalObject(
			SearchOptimalObjectVo searchOptimalObjectVo, Integer page,
			Integer rows, String sidx, String sord);

	public void updateDeathAndLogoutStateById(Long long1, boolean death,
			Long logoutState);

	public Integer getCount(SearchOptimalObjectVo searchOptimalObjectVo);
	
	public void updateTableUpdateDateById(Long id);
}
