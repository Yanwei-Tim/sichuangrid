package com.tianque.tenHouseholdsJoint.service;

import java.util.List;

import org.oproject.framework.orm.PageResult;

import com.tianque.domain.DefaultSortAndPage;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.SendMsgInfo;

public interface SendMsgInfoService {
	public abstract SendMsgInfo addSendMsgInfo(SendMsgInfo sendMsgInfo);

	public abstract void updateSendMsgInfo(SendMsgInfo sendMsgInfo);

	public abstract void deleteSendMsgInfo(Long id);

	public abstract PageResult<SendMsgInfo> querySendMsgInfosForPageResult(
			MessageInfoVo messageInfoVo, DefaultSortAndPage sortAndPage);
	
	public abstract SendMsgInfo getSendMsgInfoById(Long id);
	/**
	 * 查询同组用户获取的发送信息
	 * @param messageInfoVo
	 * @return
	 */
	public abstract List<SendMsgInfo> findTeamFamilySendMsgInfos(
			MessageInfoVo messageInfoVo);
}
