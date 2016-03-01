package com.tianque.publicSecurity.dao;

import java.util.Date;

import com.tianque.core.base.BaseDao;
import com.tianque.publicSecurity.domain.Bayonet;
import com.tianque.publicSecurity.domain.vo.SearchBayonetVo;

/**
 * 卡口表:数据操作层接口
 * 
 * @author
 * @date 2014-02-11 10:44:36
 */
public interface BayonetDao extends BaseDao<Bayonet, SearchBayonetVo> {

	/**
	 * 根据编号得到卡口信息
	 * 
	 * @param skynetNo
	 * @return
	 */
	Bayonet getBayonetByBayonetNo(String bayonetNo, Long orgId);

	/**
	 * 根据id更新卡口的关注情况
	 * 
	 * @param id
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason,
			Date logoutTime);

	/**
	 * 根据参数修改卡口数据
	 * 
	 * @param id
	 * @param toOrgId
	 * @param orgInternalCode
	 */
	void updateByParam(Long id, Long toOrgId, String orgInternalCode);

	/**
	 * 根据编号和组织机构id统计
	 * 
	 * @param bayonetNo
	 * @param toOrgId
	 * @return
	 */
	Integer countByCode(String bayonetNo, Long toOrgId);

}
