package com.tianque.approval.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.property.ApprovalItemStatus;
import com.tianque.approval.domain.vo.ApprovalItemVo;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;

@Repository("approvalItemDao")
public class ApprovalItemDaoImpl extends
		BaseDaoImpl<ApprovalItem, ApprovalItem> implements ApprovalItemDao {

	@Qualifier("approvalItemValidator")
	@Autowired
	private DomainValidator<ApprovalItem> approvalItemValidator;

	@Override
	protected void checkEntityWhenAdd(ApprovalItem entity) {
		ValidateResult baseDataValidator = approvalItemValidator
				.validate(entity);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	protected void checkEntityWhenUpdate(ApprovalItem entity) {
		ValidateResult baseDataValidator = approvalItemValidator
				.validate(entity);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		if (null == entity.getId()) {
			throw new BusinessValidationException("申请事项不能为空");
		}
	}

	@Override
	public PageInfo<ApprovalItem> findApprovalItemPage(String searchtxt,
			ApprovalItemVo approvalItemVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		// TODO Auto-generated method stub
		Map<String, Object> map = getSearchMap(approvalItemVo, sortField, order);
		if (!"".equals(searchtxt)) {
			map.put("searchtxt", searchtxt);
		}
		Integer totalRowSize = (Integer) getSqlMapClientTemplate()
				.queryForObject("approvalItem.countApprovalItemPage", map);
		List<Item> list = getSqlMapClientTemplate().queryForList(
				"approvalItem.findApprovalItemPage", map,
				(pageNum - 1) * pageSize, pageSize);
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurrentPage(pageNum);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setTotalRowSize(totalRowSize);
		pageInfo.setResult(list);
		return pageInfo;
	}

	private Map<String, Object> getSearchMap(ApprovalItemVo approvalItemVo,
			String sortField, String order) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sortField);
		map.put("order", order);
		if (null != approvalItemVo) {
			if (null != approvalItemVo.getStatus()) {
				map.put("status", approvalItemVo.getStatus());
				map.put("orStatus", ApprovalItemStatus.PENDING_REVIEW);
			}
			if (null != approvalItemVo.getOrganization()
					&& null != approvalItemVo.getOrganization().getId()) {
				map.put("orgId", approvalItemVo.getOrganization().getId());
			}
			if (null != approvalItemVo.getCreateOrg()
					&& null != approvalItemVo.getCreateOrg().getId()) {
				map.put("createOrg", approvalItemVo.getCreateOrg().getId());
			}
			if (null != approvalItemVo.getApprovalNumber()
					&& !"".equals(approvalItemVo.getApprovalNumber().trim())) {
				map.put("approvalNumber", approvalItemVo.getApprovalNumber());
			}
			if (null != approvalItemVo.getItemName()
					&& !"".equals(approvalItemVo.getItemName().trim())) {
				map.put("itemName", approvalItemVo.getItemName());
			}
		}
		return map;
	}

	@Override
	public ApprovalItem getApprovalItemByApprovalNumber(String approvalNumber) {
		// TODO Auto-generated method stub
		if (null == approvalNumber || "".equals(approvalNumber.trim())) {
			return null;
		}
		return (ApprovalItem) getSqlMapClientTemplate().queryForObject(
				"approvalItem.getApprovalItemByApprovalNumber", approvalNumber);
	}

	@Override
	public List<ApprovalItem> findApprovalItemByItemId(Long itemId) {
		// TODO Auto-generated method stub
		if (null == itemId) {
			return null;
		}
		return getSqlMapClientTemplate().queryForList(
				"approvalItem.findApprovalItemByItemId", itemId);
	}

	@Override
	public void updateApprovalItemByApprovalNumber(Long status,
			String approvalNumber) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("approvalNumber", approvalNumber);
		getSqlMapClientTemplate().update(
				"approvalItem.updateApprovalItemByApprovalNumber", map);
	}

	@Override
	public void deleteApprovalItemByApprovalNumber(String approvalNumber) {
		getSqlMapClientTemplate().delete(
				"approvalItem.deleteApprovalItemByApprovalNumber",
				approvalNumber);
	}
}
