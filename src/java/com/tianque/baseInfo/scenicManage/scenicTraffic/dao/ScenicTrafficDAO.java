package com.tianque.baseInfo.scenicManage.scenicTraffic.dao;

import java.util.Date;

import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.vo.SearchScenicTrafficVo;
import com.tianque.core.vo.PageInfo;

/**
 * @ClassName: ScenicTrafficDAO
 * @Description: 景区交通
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 11:34:08 AM
 */
public interface ScenicTrafficDAO {

	ScenicTraffic addScenicTraffic(ScenicTraffic scenicTraffic);

	void deleteScenicTrafficById(Long id);

	ScenicTraffic getScenicTrafficById(Long id);

	PageInfo searchScenicTrafficForPage(Integer pageNum, Integer pageSize,
			String sortField, String order,
			SearchScenicTrafficVo searchScenicTrafficVo);

	ScenicTraffic updateScenicTraffic(ScenicTraffic scenicTraffic);

	void updateEmphasiseById(Long id, Boolean emphasis, String logoutReason,
			Date logoutTime);

	Integer getCount(SearchScenicTrafficVo searchScenicTrafficVo);

}
