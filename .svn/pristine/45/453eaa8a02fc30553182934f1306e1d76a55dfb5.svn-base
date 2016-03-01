package com.tianque.publicSecurity.dao;

import java.util.Date;

import com.tianque.core.base.BaseDao;
import com.tianque.publicSecurity.domain.SnapshotSystem;
import com.tianque.publicSecurity.domain.vo.SearchSnapshotSystemVo;

/**
 * 抓拍系统表:数据操作层接口
 * 
 * @author
 * @date 2014-02-11 15:12:12
 */
public interface SnapshotSystemDao extends
		BaseDao<SnapshotSystem, SearchSnapshotSystemVo> {

	/**
	 * 根据编号得到抓拍系统信息
	 * 
	 * @param snapshotNo
	 * @param orgId
	 * @return
	 */
	SnapshotSystem getSnapshotSystemBySnapshotNo(String snapshotNo, Long orgId);

	/**
	 * 根据id更新抓拍系统的关注情况
	 * 
	 * @param id
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason,
			Date logoutTime);

	/**
	 * 根据参数更新抓拍系统信息
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
