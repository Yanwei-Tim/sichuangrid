package com.tianque.baseInfo.publicPlace.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.publicPlace.domain.PublicPlace;
import com.tianque.core.base.AbstractBaseDao;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.jms.sender.ActiveMQMessageSender;

@Repository("publicPlaceDao")
public class PublicPlaceDaoImpl extends AbstractBaseDao implements
		PublicPlaceDao {

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;

	@Override
	public PublicPlace addPlace(PublicPlace place) {
		validatePlace(place);
		Long id = (Long) getSqlMapClientTemplate().insert(
				"publicPlace.addPlace", place);
		return getPlaceById(id);
	}

	@Override
	public void deletePlaceById(Long id) {
		// SiteMsg msg = new SiteMsg(getPlaceById(id), OperateMode.DELETE);
		getSqlMapClientTemplate().delete("publicPlace.deletePlaceById", id);
		// activeMQMessageSender.send(msg);
	}

	@Override
	public PublicPlace getPlaceById(Long id) {
		if (null == id || id < 1L) {
			throw new BusinessValidationException("从数据库中查找数据时，id为空,请检查...");
		}
		return (PublicPlace) getSqlMapClientTemplate().queryForObject(
				"publicPlace.getPlaceById", id);
	}

	@Override
	public PublicPlace updatePlace(PublicPlace place) {
		validatePlace(place);
		getSqlMapClientTemplate().update("publicPlace.updatePlace", place);
		return getPlaceById(place.getId());
	}

	private boolean isStrotFieldAvaliable(String sortField) {
		return sortField != null && !"".equals(sortField.trim());
	}

	private PageInfo<PublicPlace> createPage(int rows, int page,
			Integer countNum, List list) {
		PageInfo<PublicPlace> pageInfo = new PageInfo<PublicPlace>();
		pageInfo.setResult(list);
		pageInfo.setTotalRowSize(countNum);
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		return pageInfo;
	}

	@Override
	public PageInfo<PublicPlace> findPublicPlaceForPageByOrgInternalCode(
			String orgInternalCode, Integer page, Integer rows, String sidx,
			String sord, Boolean isEmphasis) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("orgInternalCode", orgInternalCode);
		map.put("isEmphasis", isEmphasis);
		map.put("importantLocationType", "PUBLICPLACE");
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"publicPlace.countPlaces", map);

		if (isStrotFieldAvaliable(sidx)) {
			map.put("sortField", sidx);
		}
		map.put("order", sord);
		List<PublicPlace> list = getSqlMapClientTemplate().queryForList(
				"publicPlace.findPlaces", map, (page - 1) * rows, rows);

		return createPage(rows, page, countNum, list);
	}

	/**
	 * y验证方法
	 * 
	 * @param publicPlace
	 */
	private void validatePlace(PublicPlace place) {
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
		if (null != place.getRegistrationNumber()
				&& !"".equals(place.getRegistrationNumber().trim())) {
			if (place.getRegistrationNumber().trim().length() > 30) {
				throw new BusinessValidationException("备案登记号码长度不能大于30个字符");
			}
		}
		if (null != place.getOperationArea()
				&& !"".equals(place.getOperationArea().toString().trim())) {
			if (place.getOperationArea().toString().trim().length() > 13) {
				throw new BusinessValidationException("营业面积长度不能大于10个字符");
			}
		}
		if (null != place.getTotalArea()
				&& !"".equals(place.getTotalArea().toString().trim())) {
			if (place.getTotalArea().toString().trim().length() > 13) {
				throw new BusinessValidationException("总面积面积长度不能大于10个字符");
			}
		}
		if (null != place.getVouchedPeopleCount()
				&& !"".equals(place.getVouchedPeopleCount().toString().trim())) {
			if (place.getVouchedPeopleCount().toString().trim().length() > 10) {
				throw new BusinessValidationException("核定人数长度不能大于10个字符");
			}
		}
		if (null != place.getPrivateRoomCount()
				&& !"".equals(place.getPrivateRoomCount().toString().trim())) {
			if (place.getPrivateRoomCount().toString().trim().length() > 10) {
				throw new BusinessValidationException("包间数输入长度不能大于10个字符");
			}
		}
		if (null != place.getSurrounding()
				&& !"".equals(place.getSurrounding().trim())) {
			if (place.getSurrounding().trim().length() > 150) {
				throw new BusinessValidationException("周围环境输入长度不能大于150个字符");
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
		getSqlMapClientTemplate()
				.update("publicPlace.updateEmphasiseById", map);

	}

	@Override
	public PublicPlace getPlaceByPlaceAddress(String placeName, Long orgId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orgId", orgId);
		map.put("placeName", placeName);
		PublicPlace place = (PublicPlace) getSqlMapClientTemplate()
				.queryForObject("publicPlace.getPlaceByPlaceName", map);
		return place;
	}
}
