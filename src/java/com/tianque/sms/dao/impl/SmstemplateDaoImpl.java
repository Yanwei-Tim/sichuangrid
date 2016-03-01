package com.tianque.sms.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.sms.dao.SmstemplateDao;
import com.tianque.sms.domain.Smstemplate;
import com.tianque.sms.domain.vo.SearchSmstemplateVo;

/**
 * 短信模板:数据操作层
 * 
 * @author
 * @date 2013-07-03 11:27:49
 */
@Repository("smstemplateDao")
public class SmstemplateDaoImpl extends BaseDaoImpl<Smstemplate, SearchSmstemplateVo> implements SmstemplateDao {

	@Override
	public Smstemplate getSmstemplateBySearchSmstemplateVo(SearchSmstemplateVo searchSmstemplateVo) {
		// TODO Auto-generated method stub
		return (Smstemplate) getSqlMapClientTemplate().queryForObject("smstemplate.getSmstemplatesBySearchVo",
				searchSmstemplateVo);
	}

}
