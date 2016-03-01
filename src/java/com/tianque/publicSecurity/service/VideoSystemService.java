package com.tianque.publicSecurity.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.domain.VideoSystem;
import com.tianque.publicSecurity.domain.vo.SearchVideoSystemVo;

/**
 * 监控视频系统表:业务逻辑层接口
 */
public interface VideoSystemService extends BaseService<VideoSystem, SearchVideoSystemVo> {

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @param id
	 * @param orgId
	 * @param videoNo
	 * @return
	 */
	String hasDuplicateVideoNo(Long id, Long orgId, String videoNo);

	/**
	 * 根据ids更新监控视频系统的关注情况
	 * 
	 * @param idsLong
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseByIds(Long[] idsLong, Long isEmphasis, String logoutReason, Date logoutTime);

	/**
	 * 根据参数更新监控视频系统信息
	 * 
	 * @param id
	 * @param toOrgId
	 * @param orgInternalCode
	 */
	void updateByParam(Long id, Long toOrgId, String orgInternalCode);

	/**
	 * 根据编号和组织机构id得到监控视频系统信息
	 * 
	 * @param videoNo
	 * @param orgId
	 * @return
	 */
	VideoSystem getVideoSystemByVideoNo(String videoNo, Long orgId);

	/**
	 * 根据编号和目标网格id
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
			List<ErrorMessageVo> errorList, List<Long> errorIdList, String orgName,
			String orgInternalCode);

}
