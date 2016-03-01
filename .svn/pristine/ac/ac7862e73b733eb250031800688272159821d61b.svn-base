package com.tianque.baseInfo.scenicManage.scenicTraffic.service;

import com.tianque.baseInfo.scenicManage.scenicTraffic.domain.ScenicTraffic;
import com.tianque.baseInfo.scenicManage.scenicTraffic.vo.SearchScenicTrafficVo;
import com.tianque.core.vo.PageInfo;

/**
 * @ClassName: ScenicTraffiService
 * @Description: 景区交通
 * @author wangxiaohu wsmalltiger@163.com
 * @date Oct 31, 2013 11:06:23 AM
 */
public interface ScenicTrafficService {

	void deleteScenicTrafficByIds(Long[] analyzeLocationIds);

	ScenicTraffic addScenicTraffic(ScenicTraffic scenicTraffic);

	ScenicTraffic updateScenicTraffic(ScenicTraffic scenicTraffic);

	ScenicTraffic getScenicTrafficById(Long id);

	ScenicTraffic updateEmphasiseById(Long[] long1, ScenicTraffic scenicTraffic);

	ScenicTraffic updateScenicTrafficForImport(ScenicTraffic domain);

	Integer getCount(SearchScenicTrafficVo searchScenicTrafficVo);

	PageInfo<ScenicTraffic> searchScenicTrafficForPage(Integer page,
			Integer rows, String sidx, String sord,
			SearchScenicTrafficVo searchScenicTrafficVo);

}
