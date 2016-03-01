package com.tianque.openLayersMap.dao;

import com.tianque.core.vo.PageInfo;
import com.tianque.openLayersMap.domian.vo.GirdTeamVo;

/**
 * 二维地图  网格员管理
 * @author songzhiqiang
 *
 */
public interface GridTeamMapDao {
	/**
	 * 根据类型返回网格员信息
	 * @return
	 */
	public PageInfo<GirdTeamVo> getGridTeamInfoByType(String orgCode,Long type,Integer pageNum, Integer pageSize);
	
	/**
	 * 根据搜索条件统计网格员管理人数
	 * @return
	 */
	public Integer get2DMapInfoBySerachType(String orgCode,String serachType);
	
	/**
	 * 根据地图右侧图层点击统计网格员管理人数
	 * @param orgCode 
	 * @return
	 */
	public Integer get2DMapInfoByRigthSerachType(String orgCode);
	
	/**
	 * 根据id得到网格员信息
	 * @param id
	 * @return
	 */
	public GirdTeamVo getGirdTeamVoInfoById(Long id);

}
