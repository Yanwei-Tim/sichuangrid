package com.tianque.baseInfo.internetBar.service;

import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchInternetBarVo;

/**
 * 网吧信息
 */
public interface InternetBarService {
	/**
	 * 新增网吧信息
	 * 
	 * @param internetBar
	 * @return
	 */
	public InternetBar addInternetBar(InternetBar internetBar);

	/**
	 * 通过id查找上网服务信息
	 * 
	 * @param id
	 * @return
	 */
	public InternetBar getInternetBarById(Long id);

	/**
	 * 通过id删除上网服务信息
	 * 
	 * @param id
	 */
	public void deleteInternetBarByIds(Long[] ids);

	/**
	 * 修改上网服务信息
	 * 
	 * @param internetBar
	 * @return
	 */
	public InternetBar updateInternetBar(InternetBar internetBar);

	/**
	 * 修改网吧的注销状态
	 * 
	 * @param id
	 *            网吧id
	 * @param isEmphasis
	 *            注销状态
	 * @param logoutReason
	 *            注销原因
	 * @param logoutDate
	 *            注销时间
	 */
	public InternetBar updateEmphasiseById(Long id, InternetBar location);

	/**
	 * 是否有重复的网吧
	 * 
	 * @param orgId
	 *            组织机构id
	 * @param placeName
	 *            网吧名称
	 * @param exceptedId
	 *            网吧id
	 * @return
	 */
	public Boolean hasDuplicateInternetBar(Long orgId, String placeName,
			Long exceptedId);

	/**
	 * 数据管理用
	 * 
	 * @param orgId
	 * @param placeName
	 * @return
	 */
	public InternetBar hasDuplicateInternetBar(Long orgId, String placeName);

	/**
	 * 通过searchInternetBarVo 查询上网服务场所的分页对象
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param sortField
	 * @param order
	 * @param searchInternetBarVo
	 * @return
	 */
	public PageInfo<InternetBar> searchInternetBarForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchInternetBarVo searchInternetBarVo);

	/**
	 * 修改上网服务信息
	 * 
	 * @param internetBar
	 * @return
	 */
	public InternetBar updateInternetBarForImport(InternetBar internetBar);

	public Integer getCount(SearchInternetBarVo searchInternetBarVo);

}
