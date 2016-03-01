package com.tianque.openLayersMap.service;

import java.util.List;

/**
 * 详情查看
 * 
 * @date 2013-3-15
 * @param <T>
 */
public interface DetailViewService<T> {

	/**
	 * 根据Id、表名和类型得到详情查看popup页面
	 * 
	 * @param id
	 * @param tableName
	 *            大类表名
	 * @param type
	 * @param childTableName
	 *            子类表名
	 * @param isSerachMode
	 *            是否为搜索功能
	 * @param functionType
	 *            功能类型(精确搜索、辖区分布、图层)
	 * @return
	 */
	public List<Object> getViewPopupInfoByIdAndTableNameAndType(Long id,
			String tableName, String type, String childTableName,
			Boolean isSerachMode, String functionType, Long orgId);

}
