package com.tianque.baseInfo.utils;

import java.util.List;

public class Utils {
	/*
	 * 将集合中的最后一个元素移到第一个元素
	 */
	@Deprecated
	public void lastremovefirst(Object object, List list) {
		/*
		 * object = list.get(list.size() - 1); list.remove(list.size() - 1);
		 * list.add(0, object);
		 */
	}

	/** 把Long类型的list转成数组 **/
	public static Long[] analyticalIds(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			Long[] idArray = new Long[ids.size()];
			for (int i = 0; i < ids.size(); i++) {
				idArray[i] = ids.get(i);
			}
			return idArray;
		}
		return null;
	}

	public static int[] analyticalIds2Int(List<Long> ids) {
		if (ids != null && ids.size() > 0) {
			int[] idArray = new int[ids.size()];
			for (int i = 0; i < ids.size(); i++) {
				idArray[i] = ids.get(i).intValue();
			}
			return idArray;
		}
		return null;
	}
}
