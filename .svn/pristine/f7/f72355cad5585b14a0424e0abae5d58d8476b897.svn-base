package com.tianque.baseInfo.primaryOrg.dao;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.domain.GridTeam;
import com.tianque.baseInfo.primaryOrg.domain.vo.GridTeamVo;
import com.tianque.core.vo.PageInfo;

public interface GridTeamDao {

	/***
	 * 网格队伍成员列表查询
	 */
	public PageInfo<GridTeam> findGridTeamForList(GridTeam gridTeam,
			Integer page, Integer rows, String sidx, String sord);

	/***
	 * 新增
	 */
	public GridTeam addGridTeam(GridTeam gridTeam);

	/***
	 * 修改
	 */
	public GridTeam updateGridTeam(GridTeam gridTeam);

	/***
	 * 删除
	 */
	public void deleteGridTeamByIds(String[] ids);

	/**
	 * 根据用户IDs删除网格员
	 * @param userIds
	 */
	public void deleteGridTeamByUserIds(Long[] userIds);

	/**
	 * 通过ID查询数据
	 */
	public GridTeam getGridTeamById(Long id);

	/**
	 * 查询指定机构及其下辖 网格员总数(已激活的)
	 */
	public Long countGridTeam(String orgCode);

	/**
	 * 查询指定机构及其下辖 专职/兼职网格员数量
	 */
	public Long countGridTeamByOrgAndPositionType(String orgCode,
			Long positionTypeId);

	/**
	 * 查询指定机构及其下辖 手机端网格员数量
	 */
	public Long countPhoneGridTeam(String orgCode);

	/**
	 * 查询指定机构下 网格员中自定义填写的网格总数
	 * @param id
	 * @return
	 */
	public Long countUserDefinedGrid(String orgCode);

	/**
	 * 根据电话号码来查询网格员
	 * @param phoneNumber
	 * @return
	 */
	public List<GridTeam> getGridTeamByPhoneNumber(String phoneNumber);

	/**
	 * 根据组织机构和身份证信息来查询唯一网格员信息
	 * @param orgId
	 * @param idCardNo
	 * @return
	 */
	public GridTeam getGridTeamByIdCardNo(Long orgId, String idCardNo);

	public GridTeam getGridTeamByUserId(Long userId);

	/**
	 * 网格员统计报表
	 * @param parentOrgId 父机构Id;gridOrgLevelId 网格层级字典Id;网格员全职字典Id fullTimeId;网格员兼职字典Id fullTimeId
	 * @return
	 */
	public List<GridTeamVo> statisticsGridTeam(Long parentOrgId, Long gridOrgLevelId,
			Long fullTimeId, Long partTimeId);

}
