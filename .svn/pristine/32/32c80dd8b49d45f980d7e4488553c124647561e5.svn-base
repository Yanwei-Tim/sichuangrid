package com.tianque.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Contacter;
import com.tianque.domain.MyArea;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.property.OrganizationType;
import com.tianque.domain.vo.ContacterVo;
import com.tianque.service.SmsSendBoxService;
import com.tianque.userAuth.api.ContacterDubboService;

@Transactional
@Scope("prototype")
@SuppressWarnings("serial")
@Controller("smsSendBoxController")
public class SmsSendBoxController extends BaseAction {

	@Autowired
	private SmsSendBoxService smsSendBoxService;

	@Autowired
	private ContacterDubboService contacterDubboService;

	private List<MyContacter> myContacters;
	private List<MyGroup> myGroups;
	private List<WorkContacter> workContacters;
	private List<MyArea> myAreas;

	private SmsSendBox smsSendBox;
	private User user;
	private long existedCount;
	private Long orgId;
	private boolean bol;
	private Integer year;
	private Integer month;
	private String orgDatas;
	private String exceptOrgDatas;

	public String dispatch() {
		smsSendBox = new SmsSendBox();
		user = ThreadVariable.getUser();
		orgId = user.getOrganization().getId();
		myContacters = contacterDubboService.findMyContacterByOwnerId(user
				.getId());
		myGroups = contacterDubboService.findMyGroupByOwnerId(user.getId());
		return SUCCESS;
	}

	public String findWorkContacters() {
		if (orgId != null) {
			workContacters = contacterDubboService
					.findWorkContactersByOrganizationId(orgId);
		} else {
			return ERROR;
		}
		return SUCCESS;
	}

	public String sendSmsProcess() {
		if (!validateInput()) {
			return ERROR;
		}
		List<Contacter> contacters = new ArrayList<Contacter>();
		for (Long contacterId : getContacterIds(smsSendBox.getReceiver())) {
			ContacterVo contacter = contacterDubboService
					.getSimpleContacterById(contacterId);
			contacters.add(contacter);
		}
		String receiver = getReceivers(smsSendBox.getReceiver());
		smsSendBox = smsSendBoxService.addSmsSendBox(
				constructSmsSendBox(receiver), contacters);
		return SUCCESS;
	}

	public String findSmsSendBoxByOwnerId() {
		user = ThreadVariable.getUser();
		if (user == null || user.getId() == null) {
			gridPage = new GridPage(emptyPage(rows));
			return SUCCESS;
		} else {
			gridPage = new GridPage(smsSendBoxService.findSmsSendBoxByOwnerId(
					user.getId(), year, month, page, rows, sidx, sord));
		}
		return SUCCESS;
	}

	public String getSmsSendBoxById() {
		if (smsSendBox == null || smsSendBox.getId() == null) {
			existedCount = 0;
		} else {
			smsSendBox = smsSendBoxService
					.getSmsSendBoxById(smsSendBox.getId());
		}
		return SUCCESS;
	}

	public String deleteSmsSendBoxById() {
		if (smsSendBox == null || smsSendBox.getId() == null) {
			bol = false;
		} else {
			bol = smsSendBoxService.deleteSmsSendBoxById(smsSendBox.getId());
		}
		return SUCCESS;
	}

	public String findAreaContacters() {
		if (orgDatas != null && !"".equals(orgDatas)) {
			myAreas = contacterDubboService
					.findMyAreaByOrgIdAndOrgLevelAndOrgType(
							handleData(),
							Arrays.asList(new Integer[] { OrganizationType.ADMINISTRATIVE_REGION }),
							handleExceptData());

		}
		return SUCCESS;
	}

	private boolean validateInput() {
		boolean result = true;
		if (!StringUtil.isStringAvaliable(smsSendBox.getReceiver())) {
			this.errorMessage = "请输收件人!";
			return result;
		} else if (smsSendBox.getReceiver().trim().length() < 2) {
			this.errorMessage = "收件人至少需要输入2个字符!";
			return result;
		}
		if (!StringUtil.isStringAvaliable(smsSendBox.getSmsContent())) {
			this.errorMessage = "请输入短信内容!";
			return result;
		} else if (smsSendBox.getSmsContent().trim().length() < 1) {
			this.errorMessage = "收件人至少需要输入1个字符!";
			return result;
		}
		return result;
	}

	private SmsSendBox constructSmsSendBox(String receiver) {
		user = ThreadVariable.getUser();
		if (smsSendBox == null) {
			smsSendBox = new SmsSendBox();
		}
		smsSendBox.setSendUser(user);
		smsSendBox.setSendMobile(user.getMobile());
		smsSendBox.setReceiver(receiver);
		smsSendBox.setSender(user.getName());
		return smsSendBox;
	}

	/**
	 * 根据页面收件人数组，获取收件人的id
	 * 
	 * @param receiverAndId
	 * @return
	 */
	private String getReceivers(String receiverAndId) {
		String[] targetId = receiverAndId.split(",");
		StringBuffer receivers = new StringBuffer();
		for (int i = 0; i < targetId.length; i++) {
			int index = targetId[i].lastIndexOf("-");
			receivers.append(",");
			receivers.append(targetId[i].substring(index + 1));
		}
		return receivers.toString().substring(1);
	}

	private List<Long> getContacterIds(String receiverAndId) {
		String[] targetId = receiverAndId.split(",");
		List<Long> receiverIds = new ArrayList<Long>();
		for (int i = 0; i < targetId.length; i++) {
			int index = targetId[i].lastIndexOf("-");
			String id = targetId[i].substring(index + 1);
			Long contacterId = Long.parseLong(id);
			receiverIds.add(contacterId);
		}
		return receiverIds;
	}

	private PageInfo<SmsSendBox> emptyPage(int pageSize) {
		PageInfo<SmsSendBox> pageInfo = new PageInfo<SmsSendBox>();
		pageInfo.setTotalRowSize(0);
		pageInfo.setCurrentPage(0);
		pageInfo.setPerPageSize(pageSize);
		pageInfo.setResult(new ArrayList<SmsSendBox>());
		return pageInfo;
	}

	private List<Long> handleExceptData() {
		if (exceptOrgDatas == null || "".equals(exceptOrgDatas)) {
			return null;
		} else {
			List<String> temp = Arrays.asList(exceptOrgDatas.split(","));
			List<Long> exceptOrgIds = new ArrayList<Long>();
			for (int i = 0; i < temp.size(); i++) {
				if ("".equals(temp.get(i))) {
					continue;
				}
				exceptOrgIds.add(Long.valueOf(temp.get(i)));
			}
			return exceptOrgIds;
		}
	}

	private Map<Long, List<Integer>> handleData() {
		List<String> list = Arrays.asList(orgDatas.split(","));
		Map<Long, List<Integer>> map = new HashMap<Long, List<Integer>>();
		for (int i = 0; i < list.size(); i++) {
			String[] key = list.get(i).split("-");
			// if(key.length!=2){
			// continue;
			// }
			if (map.get(Long.valueOf(key[1])) == null) {
				map.put(Long.valueOf(key[1]), new ArrayList<Integer>());
			}
			map.get(Long.valueOf(key[1])).add(Integer.valueOf(key[0]));
		}
		return map;
	}

	public List<MyContacter> getMyContacters() {
		return myContacters;
	}

	public void setMyContacters(List<MyContacter> myContacters) {
		this.myContacters = myContacters;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
	}

	public List<WorkContacter> getWorkContacters() {
		return workContacters;
	}

	public void setWorkContacters(List<WorkContacter> workContacters) {
		this.workContacters = workContacters;
	}

	public SmsSendBox getSmsSendBox() {
		return smsSendBox;
	}

	public void setSmsSendBox(SmsSendBox smsSendBox) {
		this.smsSendBox = smsSendBox;
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

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public boolean isBol() {
		return bol;
	}

	public void setBol(boolean bol) {
		this.bol = bol;
	}

	public List<MyArea> getMyAreas() {
		return myAreas;
	}

	public void setMyAreas(List<MyArea> myAreas) {
		this.myAreas = myAreas;
	}

	public String getOrgDatas() {
		return orgDatas;
	}

	public void setOrgDatas(String orgDatas) {
		this.orgDatas = orgDatas;
	}

	public String getExceptOrgDatas() {
		return exceptOrgDatas;
	}

	public void setExceptOrgDatas(String exceptOrgDatas) {
		this.exceptOrgDatas = exceptOrgDatas;
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
