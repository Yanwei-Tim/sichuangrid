package com.tianque.publicSecurity.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.domain.Bayonet;
import com.tianque.publicSecurity.domain.vo.SearchBayonetVo;

/**
 * 卡口表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-11 10:44:36
 */
public interface BayonetService extends BaseService<Bayonet, SearchBayonetVo> {

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @param id
	 * @param orgId
	 * @param bayonetNo
	 * @return
	 */
	String hasDuplicateBayonetNo(Long id, Long orgId, String bayonetNo);

	/**
	 * 根据ids更新卡口的关注情况
	 * 
	 * @param idsLong
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseByIds(Long[] idsLong, Long isEmphasis,
			String logoutReason, Date logoutTime);

	/**
	 * 根据参数更新卡口
	 * 
	 * @param id
	 * @param toOrgId
	 * @param orgInternalCode
	 */
	void updateByParam(Long id, Long toOrgId, String orgInternalCode);

	/**
	 * 根据编号和组织机构id得到卡口信息
	 * 
	 * @param bayonetNo
	 * @param toOrgId
	 * @return
	 */
	Bayonet getBayonetByBayonetNo(String bayonetNo, Long toOrgId);

	/**
	 * 根据编号和组织机构id统计
	 * 
	 * @param bayonetNo
	 * @param toOrgId
	 * @return
	 */
	Integer countByCode(String bayonetNo, Long toOrgId);

	/**
	 * 转移校验
	 * 
	 * @param id
	 * @param toOrgId
	 * @param code
	 * @param orgId
	 * @param errorList
	 * @param errorIdList
	 * @param orgName
	 * @param orgInternalCode
	 */
	void transferValidate(Long id, Long toOrgId, String code, Long orgId,
			List<ErrorMessageVo> errorList, List<Long> errorIdList,
			String orgName, String orgInternalCode);

}
