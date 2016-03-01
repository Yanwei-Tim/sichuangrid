package com.tianque.baseInfo.optimalObject.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.optimalObject.domain.OptimalObject;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchOptimalObjectVo;

/**
 * 优抚对象业务接口。
 */
public interface OptimalObjectService extends BaseInfoPopulationTemplateService {

	/**
	 * 添加优抚对象信息
	 * 
	 * @param optimalObject
	 *            optimalObject
	 * @return optimalObject optimalObject
	 */
	public OptimalObject addOptimalObject(OptimalObject optimalObject);

	/**
	 * 根据ID获取优抚对象信息
	 * 
	 * @param id
	 *            优抚对象人员ID
	 * @return optimalObject 优抚对象
	 */
	public OptimalObject getOptimalObjectById(Long id);

	/**
	 * 修改优抚对象基本信息
	 * 
	 * @param optimalObject
	 *            优抚对象
	 * @return optimalObject 优抚对象
	 */
	public OptimalObject updateOptimalObject(OptimalObject optimalObject);

	/**
	 * 修改优抚对象业务信息
	 * 
	 * @param optimalObject
	 *            优抚对象
	 * @return optimalObject 优抚对象
	 */
	public OptimalObject updateOptimalObjectBusiness(OptimalObject optimalObject);

	/**
	 * 根据ID删除优抚对象
	 * 
	 * @param id
	 *            优抚对象ID
	 */
	public void deleteOptimalObjectById(Long id);

	/**
	 * 根据查询条件分页查询优抚对象信息
	 * 
	 * @param orgId
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 * @return PageInfo
	 */
	public PageInfo<OptimalObject> findOptimalObjectForPageByOrgInternalCode(
			Long orgId, Integer pageNum, Integer pageSize, String sidx,
			String sord, Long isEmphasis);

	/**
	 * 判断身份证号码是否已经存在
	 * 
	 * @param orgId
	 * @param idCardNo
	 * @param exceptedId
	 * @return
	 */
	public boolean hasDuplicateOptimalObject(Long orgId, String idCardNo,
			Long exceptedId);

	/**
	 * 根据身份证号获取优抚对象信息
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @return
	 */
	public OptimalObject getOptimalObjectIdCardNo(String idCardNo, Long orgId);

	/**
	 * 根据身份证号更新优抚对象信息
	 * 
	 * @param idCardNo
	 * @param orgId
	 * @param optimalObject
	 * @return
	 */
	public OptimalObject updateOptimalObjectByIdCardNo(String idCardNo,
			Long orgId, OptimalObject optimalObject);

	public OptimalObject updateOptimalObjectBaseInfo(OptimalObject population);

	public void deleteOptimalObjectByIds(Long[] analyzePopulationIds);

	public PageInfo searchOptimalObjects(
			SearchOptimalObjectVo searchOptimalObjectVo, Integer page,
			Integer rows, String sidx, String sord);

	public PageInfo fastSearchOptimalObject(
			SearchOptimalObjectVo searchOptimalObjectVo, Integer page,
			Integer rows, String sidx, String sord);

	/**
	 * 获取导出模版
	 * 
	 * @return
	 */
	public String[][] getExportPopertyArray();

	/**
	 * 获取导出数据
	 * 
	 * @param searchDruggyVo
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public List<OptimalObject> searchOptimalObjectsForExport(
			SearchOptimalObjectVo searchOptimalObjectVo, Integer pageNum,
			Integer pageSize, String sidx, String sord);

	/**
	 * 修改死亡
	 * 
	 * @param analyzePopulationIds
	 * @param death
	 */
	public List<OptimalObject> updateDeathByIds(Long[] analyzePopulationIds,
			boolean death);

	OptimalObject hasDuplicateOptimalObject(Long orgId, String idCardNo);

	public OptimalObject addOptimalObjectBaseInfo(OptimalObject population);

	public Integer getCount(SearchOptimalObjectVo searchOptimalObjectVo);
}
