package com.tianque.plugin.dataManage.common;

import java.util.List;
import java.util.Map;

public interface DataManageDBJobDao {
	/**
	 * 查找未处理数据数量
	 * 
	 * @param tableName
	 * @return
	 */
	public int countUnDisposeData(Map<String, Object> map);

	/**
	 * 修改处理后的可认领数据状态
	 * 
	 * @param map
	 */
	public void updateCanClaimData(Map<String, Object> map);

	/**
	 * 查找重复数据
	 * 
	 * @param queryMap
	 * @return
	 */
	public Map<String, Object> getRepeatData(Map<String, Object> queryMap);

	/**
	 * 修改未认领重复数据的状态
	 * 
	 * @param queryMap
	 */
	public void updateRepeatData(Map<String, Object> queryMap);

	/**
	 * 获取未处理数据集合
	 * 
	 * @param tableName
	 * @return
	 */
	public List<Map> getUnDisposeData(Map<String, Object> map);

	/**
	 * 修改被认领的重复数据
	 * 
	 * @param queryMap
	 */
	public void updateBeClaimRepeatData(Map<String, Object> queryMap);
}
