package com.tianque.baseInfo.scenicManage.scenicSpot.service;

import com.tianque.baseInfo.scenicManage.scenicSpot.domain.ScenicSpot;
import com.tianque.exception.base.BusinessValidationException;

public class ScenicSpotServiceHelper {

	public static void checkScenicSpot(ScenicSpot scenicSpot) {
		if (scenicSpot == null || scenicSpot.getName() == null
				|| "".equals(scenicSpot.getName().trim())) {
			throw new BusinessValidationException("请输入景点名称！");
		}
		if (scenicSpot.getOrganization() == null
				|| scenicSpot.getOrganization().getId() == null) {
			throw new BusinessValidationException("请输入所属网格！");
		}
	}
}