package com.tianque.publicSecurity.dao;

import java.util.Date;

import com.tianque.core.base.BaseDao;
import com.tianque.publicSecurity.domain.Skynet;
import com.tianque.publicSecurity.domain.vo.SearchSkynetVo;

/**
 * 天网表:数据操作层接口
 * 
 * @author
 * @date 2014-02-10 14:21:16
 */
public interface SkynetDao extends BaseDao<Skynet, SearchSkynetVo> {

	/**
	 * 根据编号得到天网信息
	 * 
	 * @param skynetNo
	 * @return
	 */
	Skynet getSkynetBySkynetNo(String skynetNo, Long orgId);

	/**
	 * 根据id更新天网的关注情况
	 * 
	 * @param id
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason,
			Date logoutTime);

	/**
	 * 根据参数修改天网数据
	 * 
	 * @param id
	 * @param toOrgId
	 * @param orgInternalCode
	 */
	void updateByParam(Long id, Long toOrgId, String orgInternalCode);

	/**
	 * 根据编号和目标网格id统计
	 * 
	 * @param code
	 * @param toOrgId
	 *            目标网格
	 * @return
	 */
	Integer countByCode(String code, Long toOrgId);

}
