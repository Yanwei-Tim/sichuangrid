/**
 * 
 */
package com.tianque.tenHouseholdsJoint.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.domain.Message;
import com.tianque.tenHouseholdsJoint.domain.ReceiveMsgInfo;
import com.tianque.tenHouseholdsJoint.domain.SendMsgInfo;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;
import com.tianque.tenHouseholdsJoint.service.MessageInfoService;
import com.tianque.tenHouseholdsJoint.service.ReceiveMsgInfoService;
import com.tianque.tenHouseholdsJoint.service.SendMsgInfoService;

/**
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年3月4日 上午10:20:06
 */
@Service("ReceiveMsgInfoService")
@Transactional
public class MessageInfoServiceImpl implements MessageInfoService {

	@Autowired
	private ReceiveMsgInfoService receiveMsgInfoService;
	@Autowired
	private SendMsgInfoService sendMsgInfoService;
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private FamilyTeamService familyTeamService;

	@Override
	public void addMessageInfo(Message message) {
		receiveMsgInfoService.addMessage(message);
		FamilyInfo currentFamilyInfo = familyInfoService.getFamilyInfoByHelpLine(message.getSendAddress());
		if(currentFamilyInfo!=null){
			addReceiveMsgInfo(message,currentFamilyInfo);
			if(currentFamilyInfo.getIsInformOther()==null 
					|| currentFamilyInfo.getIsInformOther()==0){
				return;
			}
			List<FamilyInfo> familyInfos = familyInfoService.findTeamFamilyInfosByTeamId(currentFamilyInfo.getTeamId());
			for (FamilyInfo familyInfo:familyInfos) {
				if(!familyInfo.getHelpLine().equals(message.getSendAddress())
						&& familyInfo.getIsReceiveOtherAlarm()!=null 
						&& familyInfo.getIsReceiveOtherAlarm()==1){
					addSendMsgInfo(message,familyInfo);
				}
			}
		}
	}
	private void addReceiveMsgInfo(Message message,FamilyInfo familyInfo){
		ReceiveMsgInfo receiveMsgInfo = new ReceiveMsgInfo();
		receiveMsgInfo.setUserId(familyInfo.getId());
		receiveMsgInfo.setTeamId(familyInfo.getTeamId());
		receiveMsgInfo.setSendDate(message.getCreateDate());
		receiveMsgInfo.setMessage(message);
		receiveMsgInfo.setOrganization(familyInfo.getOrganization());
		receiveMsgInfo.setIsDeal(false);
		receiveMsgInfo.setIsReport(true);
		receiveMsgInfoService.addReceiveMsgInfo(receiveMsgInfo);
	}
	private void addSendMsgInfo(Message message,FamilyInfo familyInfo){
		SendMsgInfo sendMsgInfo = new SendMsgInfo();
		sendMsgInfo.setUserId(familyInfo.getId());
		sendMsgInfo.setTeamId(familyInfo.getTeamId());
		sendMsgInfo.setSendDate(message.getCreateDate());
		sendMsgInfo.setMessage(message);
		sendMsgInfo.setOrganization(familyInfo.getOrganization());
		sendMsgInfoService.addSendMsgInfo(sendMsgInfo);
	}
	
}
