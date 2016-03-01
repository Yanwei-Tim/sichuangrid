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
import com.tianque.serviceList.dao.ReplyAttchDao;
import com.tianque.serviceList.dao.ServiceListAttachDao;
import com.tianque.serviceList.domain.ReplyAttch;
import com.tianque.serviceList.domain.ServiceListAttch;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Repository("replyAttchDaoImpl")
public class ReplyAttchDaoImpl extends BaseDaoImpl<ReplyAttch, ReplyAttch> implements ReplyAttchDao{

	@Override
	public void deleteReplyAttchByIds(Long id, Integer type) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		getSqlMapClientTemplate().delete("replyAttch.deleteReplyAttch", map);
	}

	@Override
	public ArrayList<ReplyAttch> getReplyAttchByIdAndType(Long id,
			Integer type) {
		Map<String, Object>map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("type", type);
		return (ArrayList<ReplyAttch>)getSqlMapClientTemplate().queryForList("replyAttch.getReplyAttchByIdAndType", map);
	}

}
