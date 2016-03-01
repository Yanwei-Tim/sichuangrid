package com.tianque.publicSecurity.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.publicSecurity.dao.VideoSystemDao;
import com.tianque.publicSecurity.domain.VideoSystem;
import com.tianque.publicSecurity.domain.vo.SearchVideoSystemVo;

/**
 * 监控视频系统表:数据操作层
 */
@Repository("videoSystemDao")
public class VideoSystemDaoImpl extends BaseDaoImpl<VideoSystem, SearchVideoSystemVo> implements
		VideoSystemDao {

	@Override
	public VideoSystem getVideoSystemByVideoNo(String videoNo, Long orgId) {
		if (videoNo == null || orgId == null) {
			throw new BusinessValidationException("参数错误");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("videoNo", videoNo);
		map.put("orgId", orgId);
		return (VideoSystem) getSqlMapClientTemplate().queryForObject(
				"videoSystem.getVideoSystemByVideoNo", map);
	}

	@Override
	public void updateEmphasiseById(Long id, Long isEmphasis, String logoutReason, Date logoutTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logoutReason", logoutReason);
		map.put("logoutTime", logoutTime);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("videoSystem.updateEmphasiseById", map);

	}

	@Override
	public void updateByParam(Long id, Long toOrgId, String orgInternalCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("toOrgId", toOrgId);
		map.put("orgInternalCode", orgInternalCode);
		map.putAll(getUpdateDateAndUser());
		getSqlMapClientTemplate().update("videoSystem.updateByParam", map);

	}

	@Override
	public Integer countByCode(String code, Long toOrgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("toOrgId", toOrgId);
		return (Integer) getSqlMapClientTemplate().queryForObject("videoSystem.countByCode", map);
	}

}
