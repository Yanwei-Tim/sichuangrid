package com.tianque.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.User;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;
import com.tianque.userAuth.api.ContacterDubboService;

/***
 * 我的群组
 */
@SuppressWarnings("serial")
@Controller("myGroupController")
@Scope("prototype")
@Transactional
public class MyGroupController extends BaseAction {
	@Autowired
	private ContacterDubboService contacterDubboService;
	/** 我的群组实体类 */
	private MyGroup myGroup;
	/** 我的群组集合 */
	private List<MyGroup> myGroups;
	/** 群组里面包含的站内来联系人的id集合 */
	private List<Long> contacterIds;

	private List<MyContacter> myContacters;

	private List<WorkContacter> workContacters;

	private List<ContacterVo> contacterVOs;

	private ContacterVo contacterVo;

	private String belongClass;
	private Long contacterId;
	private Long orgId;
	private Map<String, Object> ajaxContacters;
	private String condition;// 快速检索
	private String delContacterIds;// 删除联系人的ID
	private String groupIds;// 批量删除群组信息
	private Boolean isCopySelect = false;// 区分是主送还是抄送

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddMyGroup();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return prepareUpdateMyGroup();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return viewMyGroup();
		}
		return SUCCESS;
	}

	public String dispatchMyGroup() throws Exception {
		if (myGroup != null && myGroup.getId() != null)
			myGroup = contacterDubboService.getSimpleMyGroupById(myGroup
					.getId());
		if (myGroup == null) {
			myGroup = new MyGroup();
		}
		return SUCCESS;
	}

	public String dispatchMyGroupHasContacter() throws Exception {
		if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			if (myGroup != null && myGroup.getId() != null) {
				contacterVOs = contacterDubboService
						.findMyGroupHasContactersByGroupId(myGroup.getId());
			}
		} else if (DialogMode.MYGROUP_EDIT_MEMBER.equals(mode)
				|| DialogMode.VIEW_MODE.equals(mode)) {
			return DialogMode.MYGROUP_EDIT_MEMBER;
		}

		return SUCCESS;
	}

	private String prepareAddMyGroup() {
		myGroup = new MyGroup();
		return SUCCESS;
	}

	private String prepareUpdateMyGroup() {
		if (myGroup != null && myGroup.getId() != null) {
			myGroup = contacterDubboService.getSimpleMyGroupById(myGroup
					.getId());
		}
		return SUCCESS;

	}

	private String viewMyGroup() {
		return SUCCESS;
	}

	/**
	 * 新增群组
	 * 
	 * @return
	 */
	@PermissionFilter(ename = "addGroup")
	public String addMyGroup() throws Exception {
		if (myGroup == null) {
			errorMessage = "操作失败，请重试！";
			return ERROR;
		}
		if (!validateMyGroup(myGroup)) {
			return ERROR;
		}
		myGroup.setOwner(ThreadVariable.getUser());
		myGroup = contacterDubboService.addMyGroup(myGroup);
		return SUCCESS;
	}

	@PermissionFilter(ename = "editHasContacter")
	public String addMyGroupHasContacter() throws Exception {
		if (myGroup == null || myGroup.getId() == null)
			return ERROR;
		if (contacterIds != null) {
			for (Long contacterId : contacterIds) {
				contacterDubboService.addMyGroupHasContacter(myGroup.getId(),
						contacterId);
			}
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "updateGroup")
	public String updateMyGroup() throws Exception {
		if (!validateMyGroup(myGroup))
			return ERROR;
		myGroup.setOwner(ThreadVariable.getUser());
		myGroup = contacterDubboService.updateMyGroup(myGroup);
		return SUCCESS;
	}

	/***
	 * 编辑群组里面的联系人
	 * 
	 * @return
	 */
	public String updateMyGroupHasContacter() throws Exception {
		if (myGroup == null || myGroup.getId() == null) {
			errorMessage = "请选择群组！";
			return ERROR;
		}
		updateMyGroupHasContacter(myGroup);
		return SUCCESS;
	}

	/**
	 * 删除群组中的联系人
	 * 
	 * @param myGroup
	 */
	public String deleteMyGroupContater() throws Exception {
		if (myGroup == null || myGroup.getId() == null) {
			errorMessage = "操作失败，请重试";
			return ERROR;
		}
		if (delContacterIds == null || delContacterIds.trim().length() == 0) {
			errorMessage = "请选择一条数据进行删除！";
			return ERROR;
		}
		String[] ids = delContacterIds.split(",");
		contacterIds = getContacterIds(ids);
		for (Long id : contacterIds) {
			contacterDubboService.deleteMyGroupHasSingleContacterByTwoId(
					myGroup.getId(), id);
		}
		return SUCCESS;
	}

	private List<Long> getContacterIds(String[] ids) {
		List<Long> contacterIdList = new ArrayList<Long>();
		for (String id : ids) {
			contacterIdList.add(Long.parseLong(id));
		}

		return contacterIdList;
	}

	private void updateMyGroupHasContacter(MyGroup myGroup) {
		List<ContacterVo> contacterVos = contacterDubboService
				.findMyGroupHasContactersByGroupId(myGroup.getId());
		List<Long> existsContacterId = asSingleValue(contacterVos);
		processOldContacter(myGroup, existsContacterId, contacterIds);
		processNewContacter(myGroup, existsContacterId, contacterIds);
	}

	private List<Long> asSingleValue(List<ContacterVo> contacterVos) {
		List<Long> result = new ArrayList<Long>();
		if (contacterVos != null) {
			for (ContacterVo contacterVo : contacterVos) {
				result.add(contacterVo.getId());
			}
		}
		return result;
	}

	private void processOldContacter(MyGroup myGroup, List<Long> oldContacter,
			List<Long> newContacter) {
		if (newContacter == null || newContacter.size() == 0) {
			contacterDubboService.deleteMyGroupHasAllContacterByGroupId(myGroup
					.getId());
		} else {
			for (Long contacterId : oldContacter) {
				if (!newContacter.contains(contacterId)) {
					contacterDubboService
							.deleteMyGroupHasSingleContacterByTwoId(
									myGroup.getId(), contacterId);
				}
			}
		}
	}

	private void processNewContacter(MyGroup myGroup, List<Long> oldContacter,
			List<Long> newContacter) {
		if (newContacter == null || newContacter.size() == 0)
			return;
		if (oldContacter == null || oldContacter.size() == 0) {
			for (Long contacterId : newContacter) {
				contacterDubboService.addMyGroupHasContacter(myGroup.getId(),
						contacterId);
			}
		} else {
			for (Long contacterId : newContacter) {
				if (!oldContacter.contains(contacterId)) {
					contacterDubboService.addMyGroupHasContacter(
							myGroup.getId(), contacterId);
				}
			}
		}
	}

	@PermissionFilter(ename = "deleteGroup")
	public String deleteMyGroup() throws Exception {
		if (myGroup == null || myGroup.getId() == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		contacterDubboService.deleteContacterById(myGroup.getId());
		contacterDubboService.deleteMyGroupHasAllContacterByGroupId(myGroup
				.getId());
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteAllGroup")
	public String deleteAllGroup() throws Exception {
		if (groupIds == null || groupIds.length() == 0) {
			this.errorMessage = "请至少选择一条数据进行删除操作！";
			return ERROR;
		}
		contacterDubboService.deleteAllMyGroup(groupIds.split(","));
		return SUCCESS;
	}

	@PermissionFilter(ename = "deleteHasContacter")
	public String deleteMyGroupHasContacter() throws Exception {
		if (myGroup == null || myGroup.getId() == null || contacterId == null) {
			this.errorMessage = "参数错误!";
			return ERROR;
		}
		contacterDubboService.deleteMyGroupHasSingleContacterByTwoId(
				myGroup.getId(), contacterId);
		return SUCCESS;
	}

	public String findMyGroupsForPage() throws Exception {
		if (ThreadVariable.getUser() == null
				|| ThreadVariable.getUser().getId() == null) {
			errorMessage = "查询出错，请重试";
			return ERROR;
		}
		PageInfo<MyGroup> pageInfo = contacterDubboService
				.findMyGroupByOwnerId(ThreadVariable.getUser().getId(), page,
						rows, sidx, sord);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String loadContacterList() throws Exception {
		User me = ThreadVariable.getUser();
		ajaxContacters = new HashMap<String, Object>();
		if (belongClass == null || "".equals(belongClass.trim())
				|| MyContacter.MYCONTACTER.equals(belongClass)) {
			myContacters = contacterDubboService.findMyContacterByOwnerId(me
					.getId());
		}
		if (belongClass == null || "".equals(belongClass.trim())
				|| WorkContacter.WORKCONTACTER.equals(belongClass)) {
			if (orgId != null) {
				workContacters = contacterDubboService
						.findWorkContactersByOrganizationId(orgId);
			} else {
				workContacters = contacterDubboService.findWorkContacter();
			}
		}
		ajaxContacters.put("myContacters", myContacters);
		ajaxContacters.put("workContacters", workContacters);
		return SUCCESS;
	}

	public String findMyGroupHasContacters() throws Exception {
		if (myGroup == null || myGroup.getId() == null) {
			gridPage = new GridPage();
		} else {
			PageInfo<ContacterVo> pageInfo = contacterDubboService
					.findMyGroupHasContactersByGroupId(myGroup.getId(),
							myGroup.getBelongClass(), myGroup.getName(), page,
							rows, sidx, sord);
			gridPage = new GridPage(pageInfo);
		}
		return SUCCESS;
	}

	public String findMyGroupHasContactersToSelectPersion() throws Exception {
		if (myGroup == null || myGroup.getId() == null) {
			myGroups = new ArrayList<MyGroup>();
		} else {
			contacterId = myGroup.getId();
			myGroups = contacterDubboService.findMygroupMemberByGroupId(myGroup
					.getId());
		}
		return SUCCESS;
	}

	public String searchMyGroupByCondition() throws Exception {
		User user = ThreadVariable.getUser();
		if (user == null || user.getId() == null) {
			errorMessage = "用户信息不存在，请重新登录重试";
			return ERROR;
		}
		if (MyContacter.WORKCONTACTER.equals(belongClass)) {
			if (myGroup == null || myGroup.getId() == null) {
				errorMessage = "查询出错，请重试！";
				return ERROR;
			}
		} else {
			myGroup = new MyGroup();
		}
		if (contacterVo == null) {
			contacterVo = new ContacterVo();
		}
		contacterVo.setOwner(user);
		gridPage = new GridPage(contacterDubboService.findMyGroupByCondition(
				myGroup.getId(), contacterVo, belongClass, page, rows, sidx,
				sord));
		return SUCCESS;
	}

	private boolean validateMyGroup(MyGroup myGroup) {
		if (myGroup == null) {
			this.errorMessage = "参数错误";
			return false;
		}
		if (myGroup.getName() == null || "".equals(myGroup.getName().trim())) {
			this.errorMessage = "群组名称为空";
			return false;
		}
		return true;
	}

	public MyGroup getMyGroup() {
		return myGroup;
	}

	public void setMyGroup(MyGroup myGroup) {
		this.myGroup = myGroup;
	}

	public List<MyGroup> getMyGroups() {
		return myGroups;
	}

	public void setMyGroups(List<MyGroup> myGroups) {
		this.myGroups = myGroups;
	}

	public List<Long> getContacterIds() {
		return contacterIds;
	}

	public void setContacterIds(List<Long> contacterIds) {
		this.contacterIds = contacterIds;
	}

	public List<MyContacter> getMyContacters() {
		return myContacters;
	}

	public void setMyContacters(List<MyContacter> myContacters) {
		this.myContacters = myContacters;
	}

	public List<WorkContacter> getWorkContacters() {
		return workContacters;
	}

	public void setWorkContacters(List<WorkContacter> workContacters) {
		this.workContacters = workContacters;
	}

	public String getBelongClass() {
		return belongClass;
	}

	public void setBelongClass(String belongClass) {
		this.belongClass = belongClass;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public Map<String, Object> getAjaxContacters() {
		return ajaxContacters;
	}

	public void setAjaxContacters(Map<String, Object> ajaxContacters) {
		this.ajaxContacters = ajaxContacters;
	}

	public List<ContacterVo> getContacterVOs() {
		return contacterVOs;
	}

	public void setContacterVOs(List<ContacterVo> contacterVOs) {
		this.contacterVOs = contacterVOs;
	}

	public Long getContacterId() {
		return contacterId;
	}

	public void setContacterId(Long contacterId) {
		this.contacterId = contacterId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDelContacterIds() {
		return delContacterIds;
	}

	public void setDelContacterIds(String delContacterIds) {
		this.delContacterIds = delContacterIds;
	}

	public ContacterVo getContacterVo() {
		return contacterVo;
	}

	public void setContacterVo(ContacterVo contacterVo) {
		this.contacterVo = contacterVo;
	}

	public String getGroupIds() {
		return groupIds;
	}

	public void setGroupIds(String groupIds) {
		this.groupIds = groupIds;
	}

	public Boolean getIsCopySelect() {
		return isCopySelect;
	}

	public void setIsCopySelect(Boolean isCopySelect) {
		this.isCopySelect = isCopySelect;
	}

}
