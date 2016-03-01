package com.tianque.dao.mobileUpdate.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.mobileUpdate.MobileUpdateDao;
import com.tianque.domain.MobileUpdate;
import com.tianque.domain.vo.MobileUpdateSearchVo;

@Repository("mobileUpdateDao")
public class MobileUpdateDaoImpl extends BaseDaoImpl<MobileUpdate, MobileUpdateSearchVo> implements
		MobileUpdateDao {

	@Override
	public MobileUpdate addMobileUpdate(MobileUpdate mobileUpdate) {
		MobileUpdate result = super.add(mobileUpdate);
		return result;
	}

	@Override
	public MobileUpdate updateMobileUpdate(MobileUpdate mobileUpdate) {
		MobileUpdate result = super.update(mobileUpdate);
		return result;
	}

	@Override
	public void deleteMobileUpdate(Long id) {
		super.delete(id);
	}

	@Override
	public PageInfo<MobileUpdate> searchMobileUpdate(MobileUpdateSearchVo searchVo,
			Integer pageNum, Integer pageSize, String sortField, String order) {

		return super.findPagerBySearchVo(searchVo, pageNum, pageSize, sortField, order);
	}

	@Override
	public MobileUpdate getMobileUpdateById(Long id) {
		return super.get(id);
	}

	@Override
	public Integer getMobileUpdateCountByCategoryId(Long categoryId) {
		Object result = this.getSqlMapClientTemplate().queryForObject(
				"mobileUpdate.getMobileUpdateCountByCategoryId", categoryId);
		if (result == null) {
			return 0;
		}

		return (Integer) result;
	}
	
	@Override
	public Integer getMobileUpdateCountByDepartmentNo(String departmentNo){
		Object result = this.getSqlMapClientTemplate().queryForObject(
				"mobileUpdate.getMobileUpdateCountByDepartmentNo", departmentNo);
		if (result == null) {
			return 0;
		}

		return (Integer) result;
	}

	@Override
	public MobileUpdate getMobileUpdateByCategoryId(Long categoryId) {
		Object result = this.getSqlMapClientTemplate().queryForObject(
				"mobileUpdate.getMobileUpdateByCategoryId", categoryId);
		if (result == null) {
			return null;
		}
		return (MobileUpdate) result;
	}

}
