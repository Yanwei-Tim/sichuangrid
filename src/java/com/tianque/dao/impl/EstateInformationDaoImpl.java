package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.EstateInformationDao;
import com.tianque.domain.EstateInformation;
import com.tianque.exception.base.BusinessValidationException;

@Repository("estateInformationDao")
public class EstateInformationDaoImpl extends AbstractBaseDao implements
		EstateInformationDao {

	@Override
	public EstateInformation getEstateInformationById(Long id) {
		return (EstateInformation) getSqlMapClientTemplate().queryForObject(
				"estateInformation.getEstateInformationById", id);
	}

	@Override
	public EstateInformation addEstateInformation(
			EstateInformation estateInformation) {
		if (!validateNotNull(estateInformation))
			throw new BusinessValidationException("参数错误");
		Long id = (Long) getSqlMapClientTemplate().insert(
				"estateInformation.addEstateInformation", estateInformation);
		return getEstateInformationById(id);
	}

	@Override
	public PageInfo<EstateInformation> findEstateInformationForPageByOrgInternalCode(
			String orgInternalCode, Integer pageNum, Integer pageSize,
			String sidx, String sord) {
		if (orgInternalCode == null || "".equals(orgInternalCode.trim())) {
			return emptyPage(pageSize);
		}

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);

		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"estateInformation.countEstateInformation", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);

		List<EstateInformation> list = getSqlMapClientTemplate().queryForList(
				"estateInformation.findEstateInformations", map,
				(pageNum - 1) * pageSize, pageSize);

		return createPageInfo(pageNum, pageSize, countNum, list);
	}

	@Override
	public EstateInformation updateEstateInformation(
			EstateInformation estateInformation) {
		getSqlMapClientTemplate().update(
				"estateInformation.updateEstateInformation", estateInformation);
		return getEstateInformationById(estateInformation.getId());
	}

	@Override
	public void deleteEstateInformationById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		getSqlMapClientTemplate().delete(
				"estateInformation.deleteEstateInformationById", id);
	}

	@Override
	public int getExistedProprietaryNoCount(EstateInformation estateInformation) {
		if (estateInformation == null
				|| estateInformation.getProprietaryNo() == null) {
			throw new BusinessValidationException("房产证号没有获得");
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"estateInformation.getExistedProprietaryNoCount",
				estateInformation);
	}

	@Override
	public int getExistedLandPermitCount(EstateInformation estateInformation) {
		if (estateInformation == null
				|| estateInformation.getLandPermit() == null) {
			throw new BusinessValidationException("土地证号没有获得");
		}
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"estateInformation.getExistedLandPermitCount",
				estateInformation);
	}

	private boolean validateNotNull(EstateInformation estateInformation) {
		if (estateInformation == null) {
			return false;
		}
		if (estateInformation.getOrganization() == null
				|| estateInformation.getOrganization().getId() == null) {
			return false;
		}
		return true;
	}

	private PageInfo<EstateInformation> createPageInfo(int pageNum,
			int pageSize, Integer countNum, List<EstateInformation> list) {
		if (null == list || list.size() < 1) {
			return emptyPage(pageSize);
		}
		PageInfo<EstateInformation> pageInfo = new PageInfo<EstateInformation>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		return pageInfo;
	}

	private PageInfo<EstateInformation> emptyPage(int pageSize) {
		PageInfo<EstateInformation> pageInfo = new PageInfo<EstateInformation>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<EstateInformation>());
		return pageInfo;

	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

}
