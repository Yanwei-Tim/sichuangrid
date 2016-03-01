package com.tianque.publicSecurity.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.domain.Skynet;
import com.tianque.publicSecurity.domain.vo.SearchSkynetVo;

/**
 * 天网表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-10 14:21:16
 */
public interface SkynetService extends BaseService<Skynet, SearchSkynetVo> {

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @param id
	 * @param orgId
	 * @param skynetNo
	 * @return
	 */
	String hasDuplicateSkynetNo(Long id, Long orgId, String skynetNo);

	/**
	 * 根据ids更新天网的关注情况
	 * 
	 * @param idsLong
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseByIds(Long[] ids, Long isEmphasis, String logoutReason,
			Date logoutTime);

	/**
	 * 根据参数更新天网信息
	 * 
	 * @param id
	 * @param toOrgId
	 * @param orgInternalCode
	 */
	void updateByParam(Long id, Long toOrgId, String orgInternalCode);

	/**
	 * 根据编号和组织机构id得到天网信息
	 * 
	 * @param skynetNo
	 * @param toOrgId
	 *            目标网格
	 * @return
	 */
	Skynet getSkynetBySkynetNo(String skynetNo, Long toOrgId);

	/**
	 * 根据编号和目标网格id统计
	 * 
	 * @param code
	 * @param toOrgId
	 *            目标网格
	 * @return
	 */
	Integer countByCode(String code, Long toOrgId);

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
