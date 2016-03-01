package com.tianque.baseInfo.handicapped.service;

import java.util.List;

import com.tianque.baseInfo.base.service.BaseInfoPopulationTemplateService;
import com.tianque.baseInfo.handicapped.domain.Handicapped;
import com.tianque.baseInfo.handicapped.domain.HandicappedSdisabilityType;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchHandicappedVo;

public interface HandicappedService extends BaseInfoPopulationTemplateService {

	/**
	 * 添加 一条 残疾人 信息
	 * 
	 * @param handicapped
	 * @return
	 */
	public Handicapped addHandicapped(Handicapped handicapped);

	/**
	 * 修改 一条 残疾人 信息
	 * 
	 * @param handicapped
	 * @return
	 */
	public Handicapped updateHandicapped(Handicapped handicapped);

	/**
	 * 分页 查询 残疾人
	 * 
	 * @param query
	 * @param handicapped
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @return
	 */
	public PageInfo<Handicapped> findHandicappedsForPageByOrganizationId(
			Long organizationId, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	/**
	 * 得到 一条 残疾人 信息
	 * 
	 * @param id
	 * @return
	 */
	public Handicapped getHandicappedById(Long id);

	/**
	 * 删除 一条 残疾人 信息
	 * 
	 * @param id
	 */
	public boolean deleteHandicapped(Long id);

	public Handicapped getHandicappedByIdCardNoAndOrganizationId(
			String idCardNo, Long organizationId);

	public Handicapped updateHandicappedByName(String name, Long id,
			Handicapped domain);

	/**
	 * 根据网格 身份证 和 ID 判断 残疾人 信息是否存在
	 */
	public boolean hasDuplicateHandicapped(Long orgId, String idCardNo,
			Long exceptedId);

	Handicapped getHandicappedByIdCardNo(String idCardNo, Long orgId);

	Handicapped hasDuplicateHandicapped(Long orgId, String idCardNo);

	/**
	 * 收索残疾人对象
	 * 
	 * @param pageNum
	 *            页数
	 * @param pageSize
	 *            每页的条目数
	 * @param sortField
	 *            排序字段
	 * @param order
	 *            排序方式
	 * @param searchHandicappedVo
	 * @return
	 */
	public PageInfo<Handicapped> searchHandicappeds(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHandicappedVo searchHandicappedVo);

	/**
	 * 
	 * @Title: searchHandicappedsForMobile
	 * @Description: TODO为手机端增加查询残疾人方法（去重）
	 * @param @param pageNum
	 * @param @param pageSize
	 * @param @param sortField
	 * @param @param order
	 * @param @param searchHandicappedVo
	 * @param @return 设定文件
	 * @return PageInfo<Handicapped> 返回类型
	 * @author wanggz
	 * @date 2014-9-23 上午10:05:47
	 */
	public PageInfo<Handicapped> searchHandicappedsForMobile(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchHandicappedVo searchHandicappedVo);

	/**
	 * 根据searchHandicappedVo 查询所有的残疾人信息
	 * 
	 * @param searchHandicappedVo
	 * @return
	 */
	public List<Handicapped> searchAllHandicappeds(
			SearchHandicappedVo searchHandicappedVo);

	/**
	 * 更新残疾人的业务信息
	 * 
	 * @param population
	 * @return
	 */
	public Handicapped updateHandicappedBusiness(Handicapped population);

	/**
	 * 更新残疾人的基本信息
	 * 
	 * @param population
	 * @return
	 */
	public Handicapped updateHandicappedBaseInfo(Handicapped population);

	public void deleteHandicappedByIds(Long[] analyzePopulationIds);

	public String[][] getExportPopertyArray();

	public List<Handicapped> updateDeathByIds(Long[] analyzePopulationIds,
			boolean death);

	public Handicapped addHandicappedBaseInfo(Handicapped population);

	public Integer getCount(SearchHandicappedVo searchHandicappedVo);

	public void deleteDisbilityType(
			HandicappedSdisabilityType handicappedSdisabilityType);

	public List<HandicappedSdisabilityType> queryDisabilityLevelById(
			HandicappedSdisabilityType handicappedSdisabilityType);

	List<HandicappedSdisabilityType> editHandicapped(Handicapped population,
			boolean isDataManage);

	void saveHandicappedList(
			List<HandicappedSdisabilityType> handicappedSdisabilityTypeList,
			Long id);

	public void saveHandicappedSdisabilityTypeToReal(
			HandicappedSdisabilityType handicappedSdisabilityType);
}
