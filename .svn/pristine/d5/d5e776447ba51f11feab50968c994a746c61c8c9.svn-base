package com.tianque.dao;

import java.util.List;

import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyMemberInfo;
import com.tianque.domain.vo.SearchPartyMemberInfoVo;

/**
 * 本级党建党员信息数据库操作接口。
 */
public interface LowerPartyMemberInfoDao {

	/**
	 * 根据ID获取党员信息
	 * 
	 * @param id
	 *            党员信息ID
	 * @return PartyMemberInfo 党员信息
	 */
	public PartyMemberInfo getPartyMemberInfoById(Long id);

	/**
	 * 根据组织结构查询当前组织机构及其以下的党员分页信息
	 * 
	 * @param partyOrgId
	 *            党支部id
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
	 * 根据查询条件高级查询
	 * 
	 * @param pageNum
	 * @param pageSize
	 * @param searchPartyMemberInfoVo
	 * @return
	 */
	public PageInfo<PartyMemberInfo> searchPartyMemberInfos(Integer pageNum,
			Integer pageSize, SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	public List<PartyMemberInfo> searchAllPartyMemberInfos(
			SearchPartyMemberInfoVo searchPartyMemberInfoVo);

	public Integer getCount(SearchPartyMemberInfoVo searchPartyMemberInfoVo);

}
