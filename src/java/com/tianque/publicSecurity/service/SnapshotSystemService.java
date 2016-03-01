package com.tianque.publicSecurity.service;

import java.util.Date;
import java.util.List;

import com.tianque.core.base.BaseService;
import com.tianque.plugin.transfer.vo.ErrorMessageVo;
import com.tianque.publicSecurity.domain.SnapshotSystem;
import com.tianque.publicSecurity.domain.vo.SearchSnapshotSystemVo;

/**
 * 抓拍系统表:业务逻辑层接口
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
public interface SnapshotSystemService extends
		BaseService<SnapshotSystem, SearchSnapshotSystemVo> {

	/**
	 * 验证是否存在相同的编号
	 * 
	 * @param id
	 * @param orgId
	 * @param snapshotNo
	 * @return
	 */
	String hasDuplicateSnapshotNo(Long id, Long orgId, String snapshotNo);

	/**
	 * 根据ids更新抓拍系统的关注情况
	 * 
	 * @param idsLong
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseByIds(Long[] idsLong, Long isEmphasis,
			String logoutReason, Date logoutTime);

	/**
	 * 根据参数更新抓拍系统信息
	 * 
	 * @param id
	 * @param toOrgId
	 * @param orgInternalCode
	 */
	void updateByParam(Long id, Long toOrgId, String orgInternalCode);

	/**
	 * 根据编号和组织机构id得到抓拍系统信息
	 * 
	 * @param snapshotNo
	 * @param orgId
	 * @return
	 */
	SnapshotSystem getSnapshotSystemBySnapshotNo(String snapshotNo, Long orgId);

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
			List<ErrorMessageVo> errorList, List<Long> errorIdList,
			String orgName, String orgInternalCode);

}
