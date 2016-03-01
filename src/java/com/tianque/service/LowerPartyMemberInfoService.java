package com.tianque.service;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;

/**
 * 下辖党建党员信息业务接口。
 */
public interface LowerPartyMemberInfoService {

	/**
	 * 根据ID获取党员信息
	 * 
	 * @param id
	 *            党员信息ID
	 * @return PartyMemberInfo 党员信息
	 */
	public PartyMemberInfo getPartyMemberInfoById(Long id);

	/**
	 * 根据组织机构，查询下辖的所有的党员信息
	 * 
	 * @param orgInternalCode
	 *            组织机构的orgInternalCode
	 * @param pageNum
	 * @param pageSize
	 * @param sidx
	 * @param sord
	 * @param isEmphasis
	 *            注销状态
	 * @return PageInfo
	 */
	public PageInfo<PartyMemberInfo> findPartyMemberInfoForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord, Long isEmphasis);

	/**
	 * 根据条件查询党员分页信息
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyMemberInfoVo
	 * @return
	 */
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	/**
	 * 根据条件查询所有的党员信息，在导出时使用
	 * 
	 * @param searchPartyMemberInfoVo
	 * @return
	 */
	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo);
}
