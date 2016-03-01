package com.tianque.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.dao.PartyOrgActivityFileDao;
import com.tianque.domain.PartyOrgActivityFile;
import com.tianque.exception.base.BusinessValidationException;

@Repository("partyOrgActivityFileDao")
public class PartyOrgActivityFileDaoImpl extends AbstractBaseDao implements
		PartyOrgActivityFileDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityFileDao#addPartyOrgActivityFile(com.tianque
	 * .domain. PartyOrgActivityFile)
	 */
	@Override
	public PartyOrgActivityFile addPartyOrgActivityFile(
			PartyOrgActivityFile partyOrgActivityFile) {
		Long id = (Long) this.getSqlMapClientTemplate().insert(
				"partyOrgActivityFile.addPartyOrgActivityFile",
				partyOrgActivityFile);
		return getPartyOrgActivityFileById(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityFileDao#getPartyOrgActivityFileById(java
	 * .lang.Long)
	 */
	@Override
	public PartyOrgActivityFile getPartyOrgActivityFileById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("查找方法中id为空,请检查...");
		}
		return (PartyOrgActivityFile) this.getSqlMapClientTemplate()
				.queryForObject(
						"partyOrgActivityFile.getPartyOrgActivityFileById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.PartyOrgActivityFileDao#
	 * getPartyOrgActivityFileByOrgActivityId(java.lang. Long)
	 */
	@Override
	public List<PartyOrgActivityFile> getPartyOrgActivityFileByOrgActivityId(
			Long orgActivityId) {
		return this.getSqlMapClientTemplate().queryForList(
				"partyOrgActivityFile.getPartyOrgActivityFileByOrgActivityId",
				orgActivityId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.tianque.dao.PartyOrgActivityFileDao#deletePartyOrgActivityFileById
	 * (java.lang.Long)
	 */
	@Override
	public void deletePartyOrgActivityFileById(Long id) {
		this.getSqlMapClientTemplate().delete(
				"partyOrgActivityFile.deletePartyOrgActivityFileById", id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.tianque.dao.PartyOrgActivityFileDao#
	 * deletePartyOrgActivityFileByOrgActivityId(java.lang .Long)
	 */
	@Override
	public void deletePartyOrgActivityFileByOrgActivityId(Long orgActivityId) {
		this.getSqlMapClientTemplate()
				.delete("partyOrgActivityFile.deletePartyOrgActivityFileByOrgActivityId",
						orgActivityId);
	}
}
