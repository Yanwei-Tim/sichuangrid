package com.tianque.baseInfo.publicComplexPlaces.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.publicComplexPlaces.domain.PublicComplexPlaces;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("publicComplexPlacesDao")
public class PublicComplexPlacesDaoImpl extends AbstractBaseDao implements
		PublicComplexPlacesDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PublicComplexPlaces addPlace(PublicComplexPlaces place) {
		validatePlace(place);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"publicComplexPlaces.addPlace", place);
		return getPlaceById(id);
	}

	@Override
	public void deletePlaceById(Long id) {
		// SiteMsg msg = new SiteMsg(getPlaceById(id), OperateMode.DELETE);
		getSqlMapClientTemplate().delete("publicComplexPlaces.deletePlaceById",
				id);
		// activeMQMessageSender.send(msg);
	}

	@Override
	public PublicComplexPlaces getPlaceById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("从数据库中查找数据时，id为空,请检查...");
		}
		return (PublicComplexPlaces) getSqlMapClientTemplate().queryForObject(
				"publicComplexPlaces.getPlaceById", id);
	}

	@Override
	public PublicComplexPlaces updatePlace(PublicComplexPlaces place) {
		validatePlace(place);
		getSqlMapClientTemplate().update("publicComplexPlaces.updatePlace",
				place);
		return getPlaceById(place.getId());
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<PublicComplexPlaces> createPage(int rows, int page,
			Integer countNum, List list) {
		PageInfo<PublicComplexPlaces> pageInfo = new PageInfo<PublicComplexPlaces>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<PublicComplexPlaces> findPublicComplexPlacesForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);
		map.put("importantLocationType", "PUBLICCOMPLEXPLACES");
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"publicComplexPlaces.countPlaces", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PublicComplexPlaces> list = getSqlMapClientTemplate()
				.queryForList("publicComplexPlaces.findPlaces", map,
						(page - 1) * rows, rows);

		return createPage(rows, page, countNum, list);
	}

	/**
	 * y验证方法
	 * 
	 * @param publicComplexPlaces
	 */
	private void validatePlace(PublicComplexPlaces place) {
		if (null == place.getPlaceName()
				&& "".equals(place.getPlaceName().trim())) {
			throw new BusinessValidationException("场所名称不能为空!");
		} else if (null != place.getPlaceName()
				|| !"".equals(place.getPlaceName().trim())) {
			if (place.getPlaceName().length() < 2
					|| place.getPlaceName().length() > 50) {
				throw new BusinessValidationException("场所名称的长度只能介于2到50个字符之间");
			}
		}
		if (null == place.getPlaceAddress()
				&& "".equals(place.getPlaceAddress().trim())) {
			throw new BusinessValidationException("场所地址不能为空!");
		} else if (null != place.getPlaceAddress()
				|| !"".equals(place.getPlaceAddress().trim())) {
			if (place.getPlaceAddress().length() < 2
					|| place.getPlaceAddress().length() > 50) {
				throw new BusinessValidationException("场所地址的长度只能介于2到50个字符之间");
			}
		}
		if (null != place.getManager() && !"".equals(place.getManager().trim())) {
			if (place.getManager().trim().length() > 20
					|| place.getManager().trim().length() < 2) {
				throw new BusinessValidationException("负责人输入的长度不能大于20或者小于2个字符!");
			}
		}
		if (null != place.getRemark() && !"".equals(place.getRemark().trim())) {
			if (place.getRemark().trim().length() > 300) {
				throw new BusinessValidationException("备注输入长度不能大于300个字符");
			}
		}
	}

	@Override
	public void updateEmphasiseById(Long id, Boolean isEmphasis,
			String logoutReason, Date logoutDate) {
		Map<String, Object> map = new HashMap();
		map.put("id", id);
		map.put("isEmphasis", isEmphasis);
		map.put("logOutReason", logoutReason);
		map.put("logOutTime", logoutDate);
		getSqlMapClientTemplate().update(
				"publicComplexPlaces.updateEmphasiseById", map);

	}

	@Override
	public PublicComplexPlaces getPlaceByPlaceAddress(String placeName,
			Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("placeName", placeName);
		PublicComplexPlaces place = (PublicComplexPlaces) getSqlMapClientTemplate()
				.queryForObject("publicComplexPlaces.getPlaceByPlaceName", map);
		return place;
	}
}
