package com.tianque.approval.service;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.dao.ItemDao;
import com.tianque.approval.domain.ApprovalItem;
import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.ItemAttachment;
import com.tianque.approval.domain.vo.SearchItem;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.OperationFailedException;

@Service("itemService")
@Transactional
public class ItemServiceImpl implements ItemService {

	@Qualifier("itemValidator")
	@Autowired
	private DomainValidator<Item> itemValidator;
	@Autowired
	private ItemDao itemDao;
	@Autowired
	private ItemAttachmentService itemAttachmentService;
	@Autowired
	private ApprovalItemService approvalItemService;

	@Override
	public Item addItem(Item item, String[] attachFiles) {
		ValidateResult baseDataValidator = itemValidator.validate(item);
		if (null != baseDataValidator && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		itemNumberWhetherToRepeat(item);
		autoFillChinesePinyin(item);
		item = itemDao.add(item);
		addItemAttachment(attachFiles, item);
		return item;
	}

	private void autoFillChinesePinyin(Item item) {
		Map<String, String> pinyin = Chinese2pinyin.changeChinese2Pinyin(item
				.getItemName());
		item.setSimplePinyin(pinyin.get("simplePinyin"));
		item.setFullPinyin(pinyin.get("fullPinyin"));
	}

	private void itemNumberWhetherToRepeat(Item item) {
		Item im = itemDao.getItemByItemNumber(item.getItemNumber());
		if (null != im && null == item.getId()) {
			throw new BusinessValidationException("事项编号已重复");
		}
		if (null != im && null != item.getId()
				&& !im.getId().equals(item.getId())) {
			throw new BusinessValidationException("事项编号已重复");
		}
	}

	@Override
	public Item updateItem(Item item, String[] attachFiles) {
		ValidateResult baseDataValidator = itemValidator.validate(item);
		if (null != baseDataValidator && baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
		if (null == item.getId()) {
			throw new BusinessValidationException("事项不能为空");
		}
		itemNumberWhetherToRepeat(item);
		autoFillChinesePinyin(item);
		addItemAttachment(attachFiles, item);
		return itemDao.update(item);
	}

	@Override
	public PageInfo<Item> findItemPage(SearchItem searchItem, Integer pageNum,
			Integer pageSize, String sortField, String order) {
		return itemDao.findItemPage(searchItem, pageNum, pageSize, sortField,
				order);
	}

	@Override
	public void deleteItemById(Long id) {
		if (null == id || id < 0) {
			throw new BusinessValidationException("事项id不合法");
		}
		isItemUse(id);
		itemAttachmentService.deleteItemAttachmentByItemId(id);
		itemDao.delete(id);
	}

	private void isItemUse(Long itemId) {
		List<ApprovalItem> list = approvalItemService
				.findApprovalItemByItemId(itemId);
		if (null != list && list.size() > 0) {
			throw new BusinessValidationException("事项已被使用不能删除");
		}
	}

	@Override
	public Item getItemById(Long id) {
		if (null == id || id < 0) {
			return null;
		}
		return itemDao.get(id);
	}

	private void addItemAttachment(String[] fileName, Item item) {
		Map<String, Long> map = findItemAttachment(item);
		if (null != fileName && fileName.length != 0) {
			ItemAttachment itemAttachment = null;
			StoredFile sf = null;
			for (int i = 0; i < fileName.length; i++) {
				// id是否存在，存在则获取
				if (!StringUtil.isStringAvaliable(fileName[i].substring(0,
						fileName[i].indexOf(",")))) {
					itemAttachment = new ItemAttachment();
					sf = copyTmpFileToStoredFile(
							fileName[i].substring(fileName[i].indexOf(",") + 1),
							GridProperties.ITEM_ATTACHFILE);
					itemAttachment.setFileActualUrl(sf.getFullName());
					itemAttachment.setFileName(fileName[i]
							.substring(fileName[i].indexOf(",") + 1));
					itemAttachment.setItem(item);
					itemAttachmentService.addItemAttachment(itemAttachment);
				} else {
					map.remove(fileName[i].substring(fileName[i].indexOf(",") + 1));
				}
			}
		}
		deleteItemAttachmentByItemId(map);
	}

	private StoredFile copyTmpFileToStoredFile(String tmpFileName,
			String storedFileDir) {
		try {
			return FileUtil.copyTmpFileToStoredFile(tmpFileName, storedFileDir);
		} catch (Exception e) {
			throw new OperationFailedException("copy文件出错", e);
		}
	}

	private Map<String, Long> findItemAttachment(Item item) {
		List<ItemAttachment> list = itemAttachmentService
				.findItemAttachmentByItemId(item.getId());
		Map<String, Long> map = new HashMap<String, Long>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map.put(list.get(i).getFileName(), list.get(i).getId());
			}
		}
		return map;
	}

	private void deleteItemAttachmentByItemId(Map<String, Long> map) {
		Set<String> key = map.keySet();
		ItemAttachment itemAttachment = null;
		for (Iterator it = key.iterator(); it.hasNext();) {
			itemAttachment = itemAttachmentService.getItemAttachmentById(map
					.get(it.next()));
			if (itemAttachment != null) {
				itemAttachmentService.deleteItemAttachmentById(itemAttachment
						.getId());
				deleteFile(itemAttachment.getFileActualUrl());
			}
		}
	}

	private void deleteFile(String sPath) {
		String downFilePath = FileUtil.getWebRoot() + File.separator + sPath;
		File file = new File(downFilePath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
		}
	}

}
