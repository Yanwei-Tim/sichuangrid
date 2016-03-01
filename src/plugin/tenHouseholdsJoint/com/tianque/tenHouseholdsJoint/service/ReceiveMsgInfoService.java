package com.tianque.tenHouseholdsJoint.service;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.DefaultSortAndPage;
import com.tianque.tenHouseholdsJoint.domain.Message;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.ReceiveMsgInfo;

public interface ReceiveMsgInfoService {
	public abstract Long addMessage(Message message);
	
	public abstract ReceiveMsgInfo addReceiveMsgInfo(
			ReceiveMsgInfo receiveMsgInfo);

	public abstract void updateReceiveMsgInfo(ReceiveMsgInfo receiveMsgInfo);

	public abstract void deleteReceiveMsgInfo(Long id);

	/** 受理信息 */
	public abstract void dealReceiveMsgInfo(Long id);

	/** 通知信息 */
	public abstract void reportReceiveMsgInfo(Long id);

	public abstract PageResult<ReceiveMsgInfo> queryReceiveMsgInfosForPageResult(
			MessageInfoVo messageInfoVo, DefaultSortAndPage sortAndPage);

	public abstract ReceiveMsgInfo getReceiveMsgInfoById(Long id);

	public Integer getReceiveBoxConditionNum(String orgCode);

}
