package com.tianque.tenHouseholdsJoint.dao;

import java.util.List;

import org.oproject.framework.orm.PageResult;
import org.oproject.framework.orm.ibatis.bytecode.codegenerator.annotations.DynamicIbatisDAO;
import org.springframework.stereotype.Repository;

import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.SendMsgInfo;

@DynamicIbatisDAO(value = "SendMsgInfoDAO", sqlMapClientTemplate = "sqlMapClientTemplate")
@Repository("SendMsgInfoDAO")
public interface SendMsgInfoDAO {

	public abstract Long addSendMsgInfo(SendMsgInfo sendMsgInfo);

	public abstract void updateSendMsgInfo(SendMsgInfo sendMsgInfo);

	public abstract void deleteSendMsgInfo(Long id);

	public abstract PageResult<SendMsgInfo> querySendMsgInfosForPageResult(
			MessageInfoVo messageInfoVo, int pageNum, int pageSize);

	public abstract SendMsgInfo getSendMsgInfoById(Long id);

	public abstract List<SendMsgInfo> querySendMsgInfosForList(
			MessageInfoVo messageInfoVo);

}
