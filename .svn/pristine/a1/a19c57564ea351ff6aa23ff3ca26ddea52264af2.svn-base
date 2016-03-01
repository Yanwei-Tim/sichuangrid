package com.tianque.baseInfo.internetBar.dao;

import java.util.Date;

import com.tianque.baseInfo.internetBar.domain.InternetBar;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.vo.SearchInternetBarVo;

/**
 * 网吧信息
 */
public interface InternetBarDao {
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
	public void deleteInternetBarById(Long id);

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
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate);

	/**
	 * 通过名字和orgId查找网吧
	 * 
	 * @param placeName
	 * @param id
	 * @return
	 */
	public InternetBar getInternetBarByPlaceNameAndOrgId(String placeName,
			Long id);

	public PageInfo<InternetBar> searchInternetBarForPage(Integer pageNum,
			Integer pageSize, String sortField, String order,
			SearchInternetBarVo searchInternetBarVo);

	public Integer getCount(SearchInternetBarVo searchInternetBarVo);

}
