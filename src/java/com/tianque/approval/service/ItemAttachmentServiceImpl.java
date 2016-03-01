package com.tianque.approval.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.dao.ItemAttachmentDao;
import com.tianque.approval.domain.ItemAttachment;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.exception.base.BusinessValidationException;

@Service("itemAttachmentService")
@Transactional
public class ItemAttachmentServiceImpl implements ItemAttachmentService {

	@Qualifier("itemAttachmentValidator")
	@Autowired
	private DomainValidator<ItemAttachment> itemAttachmentValidator;

	@Autowired
	private ItemAttachmentDao itemAttachmentDao;

	@Override
	public ItemAttachment addItemAttachment(ItemAttachment itemAttachment) {
		// TODO Auto-generated method stub
		ValidateResult baseDataValidator = itemAttachmentValidator
				.validate(itemAttachment);
		if (baseDataValidator != null && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		return itemAttachmentDao.add(itemAttachment);
	}

	@Override
	public void deleteItemAttachmentByItemId(Long itemId) {
		// TODO Auto-generated method stub
		if (null == itemId || itemId < 0) {
			return;
		}
		itemAttachmentDao.deleteItemAttachmentByItemId(itemId);
	}

	@Override
	public List<ItemAttachment> findItemAttachmentByItemId(Long itemId) {
		// TODO Auto-generated method stub
		return itemAttachmentDao.findItemAttachmentByItemId(itemId);
	}

	@Override
	public void deleteItemAttachmentById(Long id) {
		// TODO Auto-generated method stub
		if (null == id || id < 0) {
			return;
		}
		itemAttachmentDao.delete(id);
	}

	@Override
	public ItemAttachment getItemAttachmentById(Long id) {
		// TODO Auto-generated method stub
		if (null == id || id < 0) {
			return null;
		}
		return itemAttachmentDao.get(id);
	}

}
