package com.tianque.baseInfo.primaryOrg.service;

import java.util.List;

import com.tianque.baseInfo.primaryOrg.domain.GridTeam;
import com.tianque.baseInfo.primaryOrg.domain.vo.GridTeamVo;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;

public interface GridTeamService {

	/***
	 * 网格员列表查询
	 */
	public PageInfo<GridTeam> findGridTeamForList(GridTeam gridTeam,Integer page,
			Integer rows, String sidx, String sord);
	
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
	
	/***
	 * 根据userIds删除
	 */
	public void deleteGridTeamByUserIds(Long[] userIds);
	
	/**
	 * 通过ID查询数据
	 */
	public GridTeam getGridTeamById(Long id);
	
	/**
	 * 用户转为网格员
	 * @param gridTeam
	 * @return
	 */
	public GridTeam addUserToGridTeam(User user);
	
	/**
	 * 根据电话号码来查询网格员
	 */
	public List<GridTeam> getGridTeamByPhoneNumber(String phoneNumber);
	/**
	 * 当前组织机构中是否存在该条身份证信息
	 */
	public GridTeam getGridTeamByIdCardNo(Long orgId, String idCardNo);
	/**
	 * 网格员报表
	 */
	public List<GridTeamVo> gridTeamStatistics(Long orgId);
	
	/**
	 * 根据userId查询网格员
	 */
	public GridTeam getGridTeamByUserId(Long userId);
	
	/**
	 * 用户信息更改时  用户相关信息变更
	 * @param user
	 */
	public void bindUserToGridTeam(User user);
}
