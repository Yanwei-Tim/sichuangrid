package com.tianque.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.SmsReceivedBox;
import com.tianque.domain.User;
import com.tianque.service.SmsReceivedBoxService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("smsReceivedBoxController")
public class SmsReceivedBoxController extends BaseAction {

	@Autowired
	private SmsReceivedBoxService smsReceivedBoxService;

	private SmsReceivedBox smsReceivedBox;
	private User user;
	private long existedCount;
	private boolean bol;
	private Integer year;
	private Integer month;

	public String findSmsReceivedBoxByOwnerId() {
		user = ThreadVariable.getUser();
		if (user == null || user.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(
					smsReceivedBoxService.findSmsReceivedBoxByOwnerId(user
							.getOrganization().getId(), year, month, page,
							rows, sidx, sord));
		}
		return SUCCESS;
	}

	public String getSmsReceivedBoxById() {
		if (smsReceivedBox == null || smsReceivedBox.getId() == null) {
			existedCount = 0;
		} else {
			smsReceivedBox = smsReceivedBoxService
					.getSmsReceivedBoxById(smsReceivedBox.getId());
		}
		return SUCCESS;
	}

	public String dispatchProcessPage() {
		if (isEmpty(smsReceivedBox.getId())) {
			errorMessage = "未选择短信";
			return ERROR;
		}

		smsReceivedBox = smsReceivedBoxService
				.getSmsReceivedBoxById(smsReceivedBox.getId());
		if (smsReceivedBox != null) {
			user = ThreadVariable.getUser();
		}
		return SUCCESS;
	}

	public String processSmsReceived() {
		if (smsReceivedBox == null || smsReceivedBox.getId() == null) {
			this.errorMessage = "请选中一条记录!";
			return ERROR;
		}
		if (!validateInput()) {
			return ERROR;
		}
		user = ThreadVariable.getUser();
		smsReceivedBox.setProcessFlag(true);
		smsReceivedBox.setHandleUser(user);
		smsReceivedBoxService.updateSmsReceivedBox(smsReceivedBox);
		return SUCCESS;
	}

	public String isTreated() {
		if (smsReceivedBox == null || smsReceivedBox.getId() == null) {
			this.errorMessage = "请选中一条记录!";
			return ERROR;
		} else {
			smsReceivedBox = smsReceivedBoxService
					.getSmsReceivedBoxById(smsReceivedBox.getId());
			bol = smsReceivedBox.isProcessFlag();
			return SUCCESS;
		}
	}

	public String deleteSmsReceivedBoxById() {
		if (smsReceivedBox == null || smsReceivedBox.getId() == null) {
			bol = false;
		} else {
			bol = smsReceivedBoxService.deleteSmsReceivedBoxById(smsReceivedBox
					.getId());
		}
		return SUCCESS;
	}

	private boolean validateInput() {
		boolean result = true;
		if (!StringUtil.isStringAvaliable(smsReceivedBox.getDisposition())) {
			this.errorMessage = "请输处理意见!";
			result = false;
		} else if (smsReceivedBox.getDisposition().trim().length() < 2) {
			this.errorMessage = "处理意见至少需要输入2个字符!";
			result = false;
		}
		return result;
	}

	private PageInfo<SmsReceivedBox> emptyPage(int pageSize) {
		PageInfo<SmsReceivedBox> pageInfo = new PageInfo<SmsReceivedBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsReceivedBox>());
		return pageInfo;
	}

	private boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof String && "".equals(obj.toString().trim()))
			return true;
		if (obj instanceof Long && 0L == Long.valueOf(obj.toString()))
			return true;
		return false;
	}

	public SmsReceivedBox getSmsReceivedBox() {
		return smsReceivedBox;
	}

	public void setSmsReceivedBox(SmsReceivedBox smsReceivedBox) {
		this.smsReceivedBox = smsReceivedBox;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getExistedCount() {
		return existedCount;
	}

	public void setExistedCount(long existedCount) {
		this.existedCount = existedCount;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

}
