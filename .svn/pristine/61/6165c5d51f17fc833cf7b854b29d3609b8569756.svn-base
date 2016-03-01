package com.tianque.plugin.weChat.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.exception.ServiceException;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Organization;
import com.tianque.plugin.weChat.dao.EvaluationIssueHandleDao;
import com.tianque.plugin.weChat.domain.evaluationIssueHandle.EvaluationIssueHandle;
import com.tianque.plugin.weChat.domain.user.TencentUser;
import com.tianque.plugin.weChat.service.EvaluationIssueHandleService;
import com.tianque.plugin.weChat.service.TencentUserService;
import com.tianque.sysadmin.service.OrganizationDubboService;

/** 评价事件处理服务类 */
@Service("evaluationIssueHandleService")
@Transactional
public class EvaluationIssueHandleServiceImpl implements EvaluationIssueHandleService{

	@Autowired
	private EvaluationIssueHandleDao evaluationIssueHandleDao;
	@Autowired
	private OrganizationDubboService organizationDubboService;
	@Autowired
	private TencentUserService tencentUserService;
	
	@Override
	public String addEvaluationIssueHandle(
			EvaluationIssueHandle evaluationIssueHandle) {
		String msg=null;
        if(evaluationIssueHandle.getSerialNumber()==null){
        	msg="评价失败,未获取到事件服务单号!";
        	return msg;
        }else if(evaluationIssueHandle.getScoreStarNumber()==null){
        	msg="评价失败,未获取到满意度!";
        	return msg;
        }else if(evaluationIssueHandle.getScorePerson()==null){
        	msg="评价失败,未获取到评价人昵称!";
        	return msg;
        }else if(evaluationIssueHandle.getOrg()==null){
        	msg="评价失败,未获取到组织机构ID!";
        	return msg;
        }else if(evaluationIssueHandle.getToUserName()==null){
        	msg="评价失败,未获取到微信公众号!";
        	return msg;
        }else if(evaluationIssueHandle.getIssueName()==null){
        	msg="评价失败,未获取到事件名称!";
        	return msg;
        }
        
		TencentUser tencentUser = tencentUserService
				.getTencentUserByWeChatUserId(evaluationIssueHandle.getToUserName());
	
		if(tencentUser==null){
			msg = "评价失败!该微信公众号不存在!";
			return msg;
		} 
		Organization org = organizationDubboService.getSimpleOrgById(tencentUser
				.getOrganization().getId());
		if (org == null) {
			msg = "评价失败!组织机构未获取到!";
			return msg;
		}
        evaluationIssueHandle.setOrg(org); 
        evaluationIssueHandleDao.addEvaluationIssueHandle(evaluationIssueHandle);
		return "success";
	}

	@Override
	public EvaluationIssueHandle getEvaluationIssueHandleBySerialNumber(
			String serialNumber) {
		if(serialNumber==null){
			throw new ServiceException("查询失败,未获取关键参数!");
		}
		return evaluationIssueHandleDao.getEvaluationIssueHandleBySerialNumber(serialNumber);
	}

	@Override
	public PageInfo<EvaluationIssueHandle> findEvaluationIssueHandles(
			EvaluationIssueHandle evaluationIssueHandle, Integer pageNum,
			Integer pageSize, String sidx, String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("evaluationIssueHandle", evaluationIssueHandle);
		PageInfo<EvaluationIssueHandle> pageInfo = evaluationIssueHandleDao.findEvaluationIssueHandles(
				map, pageNum, pageSize);
	
		return pageInfo;
	}


	
}
