/**
 * 
 */
package com.tianque.serviceList.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.StoredFile;
import com.tianque.core.vo.PageInfo;
import com.tianque.serviceList.dao.ReplyDao;
import com.tianque.serviceList.dao.ServiceListAttachDao;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListAttch;
import com.tianque.serviceList.service.ReplyAttachService;
import com.tianque.serviceList.service.ServiceListAttachService;
import com.tianque.serviceList.service.ReplyService;

@Service("replyServiceImpl")
@Transactional
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private ReplyAttachService replyAttachService;

	@Override
	public Reply addReply(Reply reply) {
		return replyDao.add(reply);
	}

	@Override
	public void deleteReplyByIds(Long id, Integer type) {
		replyDao.deleteReplyByIds(id, type);
	}

	@Override
	public void deleteReply(Long id) {
		replyDao.delete(id);
	}

	@Override
	public Reply getReplyById(Long id,Integer type) {
		Reply reply=replyDao.get(id);
		reply.setReplyAttchs(replyAttachService.getReplyAttchByIdAndType(id, type));
		return reply;
	}

	@Override
	public ArrayList<Reply> getReplyByIdAndType(Long id, Integer type) {
		return replyDao.getReplyByIdAndType(id, type);
	}

	@Override
	public PageInfo<Reply> getReplyList(Reply reply, Integer page,
			Integer rows, String sidx, String sord) {
		PageInfo<Reply> pageInfo = replyDao.findPagerBySearchVo(reply, page, rows, sidx, sord);
		if(pageInfo!=null && pageInfo.getResult()!=null && pageInfo.getResult().size()!=0){
			for(Reply replyInfo:pageInfo.getResult()){
				replyInfo.setReplyAttchs(replyAttachService.getReplyAttchByIdAndType(replyInfo.getId(), reply.getServiceType()));
			}
		}
		return pageInfo;
	}

	@Override
	public List<Reply> queryServiceListReplyByMobile(Long serviceId,Integer serviceType) {
		// TODO Auto-generated method stub
		return replyDao.getReplyByIdAndType(serviceId,serviceType);
	}
	
}
