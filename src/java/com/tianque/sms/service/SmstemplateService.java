package com.tianque.sms.service;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.base.BaseService;
import com.tianque.sms.domain.Smstemplate;
import com.tianque.sms.domain.vo.SearchSmstemplateVo;

/**
 * 短信模板:业务逻辑层接口
 * 
 * @author
 * @date 2013-07-03 11:27:49
 */
public interface SmstemplateService extends BaseService<Smstemplate, SearchSmstemplateVo> {

	public String getContent(BaseDomain baseDomain, String templateType);
}
