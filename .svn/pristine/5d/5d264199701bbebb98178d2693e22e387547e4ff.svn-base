package com.tianque.service.mobileUpdate.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.mobileUpdate.MobileUpdateDao;
import com.tianque.domain.MobileUpdate;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.PropertyTypes;
import com.tianque.domain.vo.MobileUpdateSearchVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.mobileUpdate.MobileUpdateService;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Service("mobileUpdateService")
@Transactional
public class MobileUpdateServiceImpl implements MobileUpdateService {
	@Autowired
	private MobileUpdateDao mobileUpdateDao;
	@Autowired
	private PropertyDictService propertyDictService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	@Override
	public MobileUpdate addMobileUpdate(MobileUpdate mobileUpdate) {
		try {
			// 一种终端类别只能存在一条记录
			if (mobileUpdate == null || null == mobileUpdate.getDepartmentNo()
					|| "".equals(mobileUpdate.getDepartmentNo())) {
				throw new BusinessValidationException("参数错误");
			}
			String departmentNo = mobileUpdate.getDepartmentNo();
			if (null == departmentNo || "".equals(departmentNo)) {
				throw new BusinessValidationException("不存在终端类别信息,请确认");
			}
			if (existsSpecifiedDepartmentNo(departmentNo)) {
				throw new BusinessValidationException("该部门下已存在更新信息,请确认");
			}
			return mobileUpdateDao.addMobileUpdate(mobileUpdate);
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常," + e.getMessage());
		}
	}

	private boolean existsSpecifiedCategory(Long categoryId, Long oldCategoryId) {
		if (categoryId == null || oldCategoryId == null) {
			throw new BusinessValidationException("参数错误");
		}
		// 如果没有修改CategoryId,就不进行验证
		if (categoryId.equals(oldCategoryId)) {
			return false;
		}
		Integer count = mobileUpdateDao
				.getMobileUpdateCountByCategoryId(categoryId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean existsSpecifiedCategory(Long categoryId) {
		if (categoryId == null) {
			throw new BusinessValidationException("系统发生异常,参数错误");
		}
		Integer count = mobileUpdateDao
				.getMobileUpdateCountByCategoryId(categoryId);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean existsSpecifiedDepartmentNo(String departmentNo,
			String oldDepartmentNo) {
		if (null == departmentNo || "".equals(departmentNo)) {
			throw new BusinessValidationException("系统发生异常,参数错误");
		}
		if (departmentNo.equals(oldDepartmentNo)) {
			return false;
		}
		Integer count = mobileUpdateDao
				.getMobileUpdateCountByDepartmentNo(departmentNo);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	private boolean existsSpecifiedDepartmentNo(String departmentNo) {
		if (null == departmentNo || "".equals(departmentNo)) {
			throw new BusinessValidationException("系统发生异常,参数错误");
		}
		Integer count = mobileUpdateDao
				.getMobileUpdateCountByDepartmentNo(departmentNo);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public MobileUpdate updateMobileUpdate(MobileUpdate mobileUpdate,
			String oldOrgDepartmentNo) {
		try {
			if (mobileUpdate == null || null == mobileUpdate.getDepartmentNo()
					|| "".equals(mobileUpdate.getDepartmentNo())) {
				throw new BusinessValidationException("参数不能为空");
			}
			String departmentNo = mobileUpdate.getDepartmentNo();
			if (null == departmentNo || "".equals(departmentNo)) {
				throw new BusinessValidationException("不存在终端类别信息,请确认");
			}
			if (existsSpecifiedDepartmentNo(departmentNo, oldOrgDepartmentNo)) {
				throw new BusinessValidationException("该部门下已存在更新信息,请确认");
			}
			return mobileUpdateDao.updateMobileUpdate(mobileUpdate);
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常," + e.getMessage());
		}

	}

	@Override
	public void deleteMobileUpdate(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		try {
			mobileUpdateDao.deleteMobileUpdate(id);
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常");
		}
	}

	public PageInfo<MobileUpdate> findMobileUpdateBySearchVo(
			MobileUpdateSearchVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (searchVo == null) {
			throw new BusinessValidationException("参数错误");
		}
		try {
			PageInfo<MobileUpdate> pageInfo = mobileUpdateDao
					.searchMobileUpdate(searchVo, pageNum, pageSize, sortField,
							order);
			List<MobileUpdate> list = pageInfo.getResult();
			for (MobileUpdate mus : list) {
				if (mus.getOrganization() != null
						&& mus.getOrganization().getId() != null) {
					Organization organization = organizationDubboService
							.getSimpleOrgById(mus.getOrganization().getId());
					if (organization != null) {
						mus.setOrganization(organization);
					}
				}
			}
			return pageInfo;
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常");
		}

	}

	@Override
	public MobileUpdate getMobileUpdateById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		try {
			return mobileUpdateDao.getMobileUpdateById(id);
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常");
		}

	}

	@Override
	public MobileUpdate getMobileUpdateByCategoryId(Long categoryId) {
		if (categoryId == null) {
			throw new BusinessValidationException("参数不能为空");
		}
		try {
			return mobileUpdateDao.getMobileUpdateByCategoryId(categoryId);
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常");
		}
	}

	@Override
	public MobileUpdate getMobileUpdateByCategoryName(String categoryName) {
		if (StringUtils.isEmpty(categoryName)) {
			throw new BusinessValidationException("参数不能为空");
		}

		List<PropertyDict> dicts = null;
		try {
			dicts = propertyDictService
					.findPropertyDictByDomainName(PropertyTypes.TERMINAL_CATEGORY);
		} catch (Exception e) {
			throw new BusinessValidationException("系统发生异常");
		}
		if (dicts == null || dicts.isEmpty()) {
			throw new BusinessValidationException("系统发生异常");
		}
		for (PropertyDict dict : dicts) {
			if (dict.getDisplayName().equals(categoryName)) {
				return getMobileUpdateByCategoryId(dict.getId());
			}
		}

		throw new BusinessValidationException("不存在该终端类别");
	}

}
