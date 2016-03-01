package com.tianque.approval.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.approval.domain.Item;
import com.tianque.approval.domain.ItemAttachment;
import com.tianque.approval.domain.vo.SearchItem;
import com.tianque.approval.service.ItemAttachmentService;
import com.tianque.approval.service.ItemService;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.FileUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.exception.base.OperationFailedException;

@Namespace("/approval/itemManage")
@Transactional
@Scope("prototype")
@Controller("itemController")
public class ItemController extends BaseAction {

	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemAttachmentService itemAttachmentService;

	private Long id;
	private Item item;
	private String[] attachFiles;
	private List<ItemAttachment> itemAttachmentList;
	private Long itemAttachmentId;
	private SearchItem searchItem;

	@Action(value = "updateItem", results = { @Result(name = "success", type = "json", params = {
			"root", "item", "ignoreHierarchy", "false" }) })
	public String updateItem() {
		item = itemService.updateItem(item, attachFiles);
		return SUCCESS;
	}

	@Action(value = "dispath", results = {
			@Result(name = "add", location = "/approval/item/maintainItemDlg.jsp"),
			@Result(name = "update", location = "/approval/item/maintainItemDlg.jsp"),
			@Result(name = "view", location = "/approval/item/itemViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispath() {
		if (DialogMode.ADD_MODE.equals(mode)) {
			return "add";
		} else if (DialogMode.EDIT_MODE.equals(mode)) {
			item = itemService.getItemById(id);
			itemAttachmentList = itemAttachmentService
					.findItemAttachmentByItemId(item.getId());
			return "update";
		} else if (DialogMode.VIEW_MODE.equals(mode)) {
			item = itemService.getItemById(id);
			itemAttachmentList = itemAttachmentService
					.findItemAttachmentByItemId(item.getId());
			return "view";
		}
		return SUCCESS;
	}

	@Action(value = "addItem", results = { @Result(name = "success", type = "json", params = {
			"root", "item", "ignoreHierarchy", "false" }) })
	public String addItem() {
		item = itemService.addItem(item, attachFiles);
		return SUCCESS;
	}

	@Action(value = "findItemPage", results = { @Result(name = "success", type = "json", params = {
			"root", "gridPage", "ignoreHierarchy", "false" }) })
	public String findItemPage() {
		gridPage = new GridPage(itemService.findItemPage(searchItem, page,
				rows, sidx, sord));
		return SUCCESS;
	}

	@Action(value = "deleteItemById", results = { @Result(name = "success", type = "json", params = {
			"root", "true", "ignoreHierarchy", "false" }) })
	public String deleteItemById() {
		itemService.deleteItemById(id);
		return SUCCESS;
	}

	@Actions({ @Action(value = "downLoadActualFile", results = {
			@Result(name = "success", type = "stream", params = {
					"contentType", "application/octet-stream;charset=GBK",
					"inputName", "inputStream", "contentDisposition",
					"inline;filename='${downloadFileName}'" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) }) })
	public String downLoadActualFile() {
		try {
			ItemAttachment itemAttachment = itemAttachmentService
					.getItemAttachmentById(itemAttachmentId);
			inputStream = new java.io.FileInputStream(createStoreFile(
					itemAttachment.getFileActualUrl(),
					itemAttachment.getFileName()));
		} catch (Exception e) {
			throw new OperationFailedException("事项管理附件下载出错:", e);
		}
		return SUCCESS;
	}

	private File createStoreFile(String path, String fileName)
			throws IOException {
		String downFilePath = FileUtil.getWebRoot() + File.separator + path;
		downloadFileName = new String(fileName.getBytes("gbk"), "ISO8859-1");
		File storedFile = new File(downFilePath);
		if (!storedFile.exists()) {
			storedFile.createNewFile();
		}
		return storedFile;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String[] getAttachFiles() {
		return attachFiles;
	}

	public void setAttachFiles(String[] attachFiles) {
		this.attachFiles = attachFiles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<ItemAttachment> getItemAttachmentList() {
		return itemAttachmentList;
	}

	public void setItemAttachmentList(List<ItemAttachment> itemAttachmentList) {
		this.itemAttachmentList = itemAttachmentList;
	}

	public Long getItemAttachmentId() {
		return itemAttachmentId;
	}

	public void setItemAttachmentId(Long itemAttachmentId) {
		this.itemAttachmentId = itemAttachmentId;
	}

	public SearchItem getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(SearchItem searchItem) {
		this.searchItem = searchItem;
	}

}
