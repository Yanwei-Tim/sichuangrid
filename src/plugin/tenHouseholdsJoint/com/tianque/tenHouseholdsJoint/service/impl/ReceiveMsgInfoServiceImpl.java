package com.tianque.tenHouseholdsJoint.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.StringUtil;
import com.tianque.domain.DefaultSortAndPage;
import com.tianque.tenHouseholdsJoint.dao.ReceiveMsgInfoDAO;
import com.tianque.tenHouseholdsJoint.domain.FamilyInfo;
import com.tianque.tenHouseholdsJoint.domain.FamilyTeam;
import com.tianque.tenHouseholdsJoint.domain.Message;
import com.tianque.tenHouseholdsJoint.domain.MessageInfoVo;
import com.tianque.tenHouseholdsJoint.domain.ReceiveMsgInfo;
import com.tianque.tenHouseholdsJoint.service.FamilyInfoService;
import com.tianque.tenHouseholdsJoint.service.FamilyTeamService;
import com.tianque.tenHouseholdsJoint.service.ReceiveMsgInfoService;
import com.tianque.tenHouseholdsJoint.service.SendMsgInfoService;

@Service("receiveMsgInfoService")
@Transactional
public class ReceiveMsgInfoServiceImpl implements ReceiveMsgInfoService {
	@Autowired
	private ReceiveMsgInfoDAO receiveMsgInfoDAO;
	@Autowired
	private FamilyInfoService familyInfoService;
	@Autowired
	private FamilyTeamService familyTeamService;
	@Autowired
	private SendMsgInfoService sendMsgInfoService;

	@Override
	public Long addMessage(Message message) {
		return receiveMsgInfoDAO.addMessage(message);
	}
	
	@Override
	public ReceiveMsgInfo addReceiveMsgInfo(ReceiveMsgInfo receiveMsgInfo) {
		Long id = receiveMsgInfoDAO.addReceiveMsgInfo(receiveMsgInfo);
		return getReceiveMsgInfoById(id);
	}

	@Override
	public void updateReceiveMsgInfo(ReceiveMsgInfo receiveMsgInfo) {
		receiveMsgInfoDAO.updateReceiveMsgInfo(receiveMsgInfo);
	}

	@Override
	public void deleteReceiveMsgInfo(Long id) {
		receiveMsgInfoDAO.deleteReceiveMsgInfo(id);
	}

	@Override
	public void dealReceiveMsgInfo(Long id) {
		receiveMsgInfoDAO.updateDealInfo(id);
	}

	@Override
	public void reportReceiveMsgInfo(Long id) {
		receiveMsgInfoDAO.updateReportInfo(id);
	}

	@Override
	public PageResult<ReceiveMsgInfo> queryReceiveMsgInfosForPageResult(
			MessageInfoVo messageInfoVo, DefaultSortAndPage sortAndPage) {
		formatMessageInfoSearchVo(messageInfoVo,sortAndPage);
		PageResult<ReceiveMsgInfo> pageInfo = receiveMsgInfoDAO
				.queryReceiveMsgInfosForPageResult(messageInfoVo,
						sortAndPage.getPage(), sortAndPage.getRows());
		appendFamilyInfoAndFamilyTeam(pageInfo);
		return pageInfo;
	}

	@Override
	public ReceiveMsgInfo getReceiveMsgInfoById(Long id) {
		ReceiveMsgInfo receiveMsgInfo = receiveMsgInfoDAO
				.getReceiveMsgInfoById(id);
		if(receiveMsgInfo!=null){
			receiveMsgInfo.setUser(familyInfoService
					.getFamilyInfoById(receiveMsgInfo.getUserId()));
			receiveMsgInfo.setTeam(familyTeamService
					.getFamilyTeamById(receiveMsgInfo.getTeamId()));
		}
		return receiveMsgInfo;
	}

	private void appendFamilyInfoAndFamilyTeam(
			PageResult<ReceiveMsgInfo> pageInfo) {
		if (pageInfo != null) {
			Map<Object, FamilyInfo> users = new HashMap<Object, FamilyInfo>();
			Map<Object, FamilyTeam> teams = new HashMap<Object, FamilyTeam>();
			List<ReceiveMsgInfo> list = pageInfo.getResultList();
			for (int i = 0; i < list.size(); i++) {
				ReceiveMsgInfo receiveMsgInfo = list.get(i);
				if (!users.containsKey(receiveMsgInfo.getUserId())) {
					users.put(receiveMsgInfo.getUserId(), familyInfoService
							.getFamilyInfoById(receiveMsgInfo.getUserId()));
				}
				if (!teams.containsKey(receiveMsgInfo.getTeamId())) {
					teams.put(receiveMsgInfo.getTeamId(), familyTeamService
							.getFamilyTeamById(receiveMsgInfo.getTeamId()));
				}
				receiveMsgInfo.setUser(users.get(receiveMsgInfo.getUserId()));
				receiveMsgInfo.setTeam(teams.get(receiveMsgInfo.getTeamId()));
				list.set(i, receiveMsgInfo);
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
		if(StringUtil.isStringAvaliable(messageInfoVo.getUserName())){
			FamilyInfo familyInfo = new FamilyInfo();
			familyInfo.setName(messageInfoVo.getUserName());
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
	public Integer getReceiveBoxConditionNum(String orgCode) {
		return receiveMsgInfoDAO.getReceiveBoxConditionNum(orgCode);
	}

}
