package com.tianque.tenHouseholdsJoint.dao;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.tenHouseholdsJoint.domain.Message;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.ReceiveMsgInfo;

@DynamicIbatisDAO(value = "ReceiveMsgInfoDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("ReceiveMsgInfoDAO")
public interface ReceiveMsgInfoDAO {
	public abstract Long addMessage(Message message);
	
	public abstract Long addReceiveMsgInfo(ReceiveMsgInfo receiveMsgInfo);

	public abstract void updateReceiveMsgInfo(ReceiveMsgInfo receiveMsgInfo);

	public abstract void deleteReceiveMsgInfo(Long id);

	public abstract PageResult<ReceiveMsgInfo> queryReceiveMsgInfosForPageResult(
			MessageInfoVo messageInfoVo, int pageNum, int pageSize);

	/** 修改受理状态 */
	public abstract void updateDealInfo(Long id);

	/** 修改通知状态 */
	public abstract void updateReportInfo(Long id);

	public abstract ReceiveMsgInfo getReceiveMsgInfoById(Long id);

	public Integer getReceiveBoxConditionNum(String orgCode);
}
