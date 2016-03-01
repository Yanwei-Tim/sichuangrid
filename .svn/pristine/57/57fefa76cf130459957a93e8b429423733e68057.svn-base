package com.tianque.tenHouseholdsJoint.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.tenHouseholdsJoint.dao.SendMsgInfoDAO;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.SendMsgInfo;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;
import com.tianque.tenHouseholdsJoint.service.SendMsgInfoService;

@Service("sendMsgInfoService")
@Transactional
public class SendMsgInfoServiceImpl implements SendMsgInfoService {
	@Autowired
	private SendMsgInfoDAO sendMsgInfoDAO;
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private FamilyTeamService familyTeamService;
	
	@Override
	public SendMsgInfo addSendMsgInfo(SendMsgInfo sendMsgInfo) {
		Long id = sendMsgInfoDAO.addSendMsgInfo(sendMsgInfo);
		return getSendMsgInfoById(id);
	}

	@Override
	public void updateSendMsgInfo(SendMsgInfo sendMsgInfo) {
		sendMsgInfoDAO.updateSendMsgInfo(sendMsgInfo);
	}

	@Override
	public void deleteSendMsgInfo(Long id) {
		sendMsgInfoDAO.deleteSendMsgInfo(id);
	}

	@Override
	public PageResult<SendMsgInfo> querySendMsgInfosForPageResult(
			MessageInfoVo messageInfoVo, DefaultSortAndPage sortAndPage) {
		formatMessageInfoSearchVo(messageInfoVo,sortAndPage);
		PageResult<SendMsgInfo> pageInfo = sendMsgInfoDAO.querySendMsgInfosForPageResult(messageInfoVo,
				sortAndPage.getPage(), sortAndPage.getRows());
		appendFamilyInfoAndFamilyTeam(pageInfo);
		return pageInfo;
	}

	@Override
	public SendMsgInfo getSendMsgInfoById(Long id) {
		SendMsgInfo sendMsgInfo = sendMsgInfoDAO.getSendMsgInfoById(id);
		if(sendMsgInfo!=null){
			sendMsgInfo.setUser(familyInfoService.getFamilyInfoById(sendMsgInfo.getUserId()));
			sendMsgInfo.setTeam(familyTeamService.getFamilyTeamById(sendMsgInfo.getTeamId()));
		}
		return sendMsgInfo;
	}

	private void appendFamilyInfoAndFamilyTeam(PageResult<SendMsgInfo> pageInfo){
		if(pageInfo!=null){
			Map<Object,FamilyInfo> users = new HashMap<Object, FamilyInfo>();
			Map<Object,FamilyTeam> teams = new HashMap<Object, FamilyTeam>();
			List<SendMsgInfo> list = pageInfo.getResultList();
			for (int i = 0; i <list.size() ; i++) {
				SendMsgInfo msgInfo = list.get(i);
				if(!users.containsKey(msgInfo.getUserId())){
					users.put(msgInfo.getUserId(), familyInfoService.getFamilyInfoById(msgInfo.getUserId()));
				}
				if(!teams.containsKey(msgInfo.getTeamId())){
					teams.put(msgInfo.getTeamId(), familyTeamService.getFamilyTeamById(msgInfo.getTeamId()));
				}
				msgInfo.setUser(users.get(msgInfo.getUserId()));
				msgInfo.setTeam(teams.get(msgInfo.getTeamId()));
				list.set(i, msgInfo);
			}
		}
	}
	
	private void formatMessageInfoSearchVo(MessageInfoVo messageInfoVo,DefaultSortAndPage sortAndPage){
		if(StringUtil.isStringAvaliable(sortAndPage.getSidx())){
			messageInfoVo.setSortField(sortAndPage.getSidx());
		}
		if(StringUtil.isStringAvaliable(sortAndPage.getSord())){
			messageInfoVo.setOrder(sortAndPage.getSord());
		}
		if(StringUtil.isStringAvaliable(messageInfoVo.getUserName())
				|| StringUtil.isStringAvaliable(messageInfoVo.getTelephone())){
			FamilyInfo familyInfo = new FamilyInfo();
			familyInfo.setName(messageInfoVo.getUserName());
			familyInfo.setHelpLine(messageInfoVo.getTelephone());
			List<FamilyInfo> familyInfos = familyInfoService.findFamilyInfosForList(familyInfo);
			StringBuffer userIds = new StringBuffer("0");
			for (FamilyInfo info : familyInfos) {
				userIds.append(userIds.length()>0?",":"");
				userIds.append(info.getId());
			}
			messageInfoVo.setUserIds(userIds.toString());
		}
		if(StringUtil.isStringAvaliable(messageInfoVo.getHouseMaster())){
			FamilyTeam familyTeam = new FamilyTeam();
			familyTeam.setHouseMaster(messageInfoVo.getHouseMaster());
			List<FamilyTeam> familyTeams = familyTeamService.findFamilyTeamsForList(familyTeam);
			StringBuffer teamIds = new StringBuffer("0");
			for (FamilyTeam team : familyTeams) {
				teamIds.append(teamIds.length()>0?",":"");
				teamIds.append(team.getId());
			}
			messageInfoVo.setTeamIds(teamIds.toString());
		}
	}

	@Override
	public List<SendMsgInfo> findTeamFamilySendMsgInfos(
			MessageInfoVo messageInfoVo) {
		List<SendMsgInfo> result = new ArrayList<SendMsgInfo>();
		List<FamilyInfo> familyInfos = familyInfoService.findTeamFamilyInfosByTeamId(messageInfoVo.getTeamId());
		List<SendMsgInfo> sendMsgInfos = sendMsgInfoDAO.querySendMsgInfosForList(messageInfoVo);
		for (FamilyInfo familyInfo : familyInfos) {
			SendMsgInfo sendMsgInfo = new SendMsgInfo();
			sendMsgInfo.setUser(familyInfo);
			sendMsgInfo.setUserId(familyInfo.getId());
			for (SendMsgInfo msgInfo : sendMsgInfos) {
				if(msgInfo.getUserId().equals(familyInfo.getId())){
					msgInfo.setUser(familyInfo);
					sendMsgInfo = msgInfo;
					break;
				}
			}
			result.add(sendMsgInfo);
		}
		return result;
	}
}
