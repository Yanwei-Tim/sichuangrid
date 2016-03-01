package com.tianque.publicSecurity.dao;

import java.util.Date;

import com.tianque.core.base.BaseDao;
import com.tianque.publicSecurity.domain.VideoSystem;
import com.tianque.publicSecurity.domain.vo.SearchVideoSystemVo;

/**
 * 抓拍系统表:数据操作层接口
 */
public interface VideoSystemDao extends BaseDao<VideoSystem, SearchVideoSystemVo> {

	/**
	 * 根据编号得到视频系统信息
	 * 
	 * @param snapshotNo
	 * @param orgId
	 * @return
	 */
	VideoSystem getVideoSystemByVideoNo(String videoNo, Long orgId);

	/**
	 * 根据id更新视频系统的关注情况
	 * 
	 * @param id
	 * @param isEmphasis
	 * @param logoutReason
	 * @param logoutTime
	 */
	void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason, Date logoutTime);

	/**
	 * 根据参数更新视频系统信息
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
