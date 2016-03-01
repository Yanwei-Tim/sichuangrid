package com.tianque.approval.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.tianque.approval.domain.ItemAttachment;
import com.tianque.core.base.BaseDaoImpl;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;

@Repository("itemAttachmentDao")
public class ItemAttachmentDaoImpl extends
		BaseDaoImpl<ItemAttachment, ItemAttachment> implements
		ItemAttachmentDao {

	@Qualifier("itemAttachmentValidator")
	@Autowired
	private DomainValidator<ItemAttachment> itemAttachmentValidator;

	@Override
	protected void checkEntityWhenAdd(ItemAttachment entity) {
		ValidateResult baseDataValidator = itemAttachmentValidator
				.validate(entity);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	protected void checkEntityWhenUpdate(ItemAttachment entity) {
	}

	@Override
	public List<ItemAttachment> findItemAttachmentByItemId(Long itemId) {
		if (null == itemId || itemId < 0) {
			return null;
		}
		return this.getSqlMapClientTemplate().queryForList(
				"itemAttachment.findItemAttachmentByItemId", itemId);
	}

	@Override
	public void deleteItemAttachmentByItemId(Long itemId) {
		getSqlMapClientTemplate().delete(
				"itemAttachment.deleteItemAttachmentByItemId", itemId);
	}
}
