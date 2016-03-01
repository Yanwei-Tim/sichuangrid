package com.tianque.plugin.weChat.domain.messageTemplate;

import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;

/**
 * 模板消息
 */
public class TemplateMessageRecord extends BaseDomain {
	/**微信服务器模板id */
	private String templateId;
	/**标题 */
	private String title;
	/**模板编号 */
	private String templateNum;
	/**一级行业 */
	private String primaryIndustry;
	/**二级行业 */
	private String twoStageIndustry;
	/**微信公众（连接号）*/
	private String weChatUserId;
	/**所属组织机构*/
	private Organization org;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTemplateNum() {
		return templateNum;
	}

	public void setTemplateNum(String templateNum) {
		this.templateNum = templateNum;
	}

	public String getPrimaryIndustry() {
		return primaryIndustry;
	}

	public void setPrimaryIndustry(String primaryIndustry) {
		this.primaryIndustry = primaryIndustry;
	}

	public String getTwoStageIndustry() {
		return twoStageIndustry;
	}

	public void setTwoStageIndustry(String twoStageIndustry) {
		this.twoStageIndustry = twoStageIndustry;
	}

	public String getWeChatUserId() {
		return weChatUserId;
	}

	public void setWeChatUserId(String weChatUserId) {
		this.weChatUserId = weChatUserId;
	}

	public Organization getOrg() {
		return org;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

}
