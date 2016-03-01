/**
 * 
 */
package com.tianque.serviceList.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.objectweb.carol.jndi.enc.java.javaURLContextFactory;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.serviceList.dao.ReplyDao;
import com.tianque.serviceList.dao.ServiceListAttachDao;
import com.tianque.serviceList.domain.Reply;
import com.tianque.serviceList.domain.ServiceListAttch;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Repository("replyDaoImpl")
public class ReplyDaoImpl extends BaseDaoImpl<Reply, Reply> implements ReplyDao{

	@Override
	public void deleteReplyByIds(Long id, Integer type) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		getSqlMapClientTemplate().delete("reply.deleteReply", map);
	}

	@Override
	public ArrayList<Reply> getReplyByIdAndType(Long id,
			Integer type) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (ArrayList<Reply>)getSqlMapClientTemplate().queryForList("reply.getReplyByIdAndType", map);
	}

}
