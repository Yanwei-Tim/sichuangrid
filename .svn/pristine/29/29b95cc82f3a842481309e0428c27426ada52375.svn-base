package com.tianque.web.tag;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

import net.sf.json.JSONArray;
import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.IssueType;
import com.tianque.domain.IssueTypeDomain;
import com.tianque.domain.Session;
import com.tianque.issue.constants.IssueTypes;
import com.tianque.service.IssueTypeService;

public class IssueTypeReleationSelectTag extends TagSupport {
	private static Logger logger = LoggerFactory
			.getLogger(IssueTypeReleationSelectTag.class);
	private static final String EMPTY_OPTION_TEMPLATE = "<option value='' id=''>请选择</option>";
	private static final String SELECTED_OPTION_TEMPLATE = "<option value='%s' id='%s' internalId='%s' selected>%s</option>";
	private static final String OPTION_TEMPLATE = "<option value='%s' id='%s' internalId='%s'>%s</option>";

	/**
	 * key 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'key'的值取出来， 放到<option
	 * value="PropertyDict.get'key'">中.
	 */
	private String key = "id";
	/**
	 * label 里面的值是对应PropertyDict里面的一个属性. 将PropertyDict.get'label'的值取出来，
	 * 放到<option >PropertyDict.get'label'</option>中.
	 */
	private String label = "displayName";
	/**
	 * 对应PropertyDomain表中的domainName.
	 */
	private String name = "";
	/**
	 * 判断是否允许有空的选项存在.
	 */
	private boolean notNull = false;
	/**
	 * 默认选中的哪一个选项.
	 */
	private String defaultValue = "";
	private boolean showInternalId = true;
	/**
	 * 当前select的id
	 */
	private String id = "";
	/**
	 * 级联的select的Id
	 */
	private String reletionId = "";

	/** 是否操作div元素 */
	private String isOperateDiv = "";
	/**
	 * 级联的name
	 */
	private String reletionName = "";
	private IssueTypeService issueTypeService;

	private String defaultTypeValue = "";

	public int doStartTag() throws JspException {
		if (name == null || name.length() == 0) {
			return Tag.SKIP_BODY;
		}
		Session session = (Session) ThreadVariable.getSession();
		if (session == null || session.getUserId() == null) {
			return Tag.SKIP_BODY;
		}
		issueTypeService = getIssueTypeService();

		String propertyType = getValueByExpression(name);
		String[] propertyTypes = propertyType.split(",");
		List<IssueTypeDomain> issueTypeDomains = new ArrayList<IssueTypeDomain>();
		for (String domainName : propertyTypes) {
			issueTypeDomains.add(issueTypeService
					.getIssueTypeDoaminByDomainName(IssueTypes
							.getChineseName(domainName)));
		}
		toHTML(issueTypeDomains);

		return Tag.EVAL_PAGE;
	}

	/**
	 * 将所有的Code转换成Option
	 * 
	 * @param codes
	 */
	public void toHTML(List<IssueTypeDomain> issueTypeDomains) {
		JspWriter out = this.pageContext.getOut();
		StringBuffer sb = new StringBuffer(50 * issueTypeDomains.size());
		// 放置一个空选项
		if (!notNull)
			sb.append(EMPTY_OPTION_TEMPLATE);
		try {
			// 根据分析得到需要选中选项的值
			for (IssueTypeDomain issueTypeDomain : issueTypeDomains) {
				if (issueTypeDomain.getId().toString().equals(defaultValue)) {
					sb.append(String.format(SELECTED_OPTION_TEMPLATE,
							issueTypeDomain.getId(), issueTypeDomain.getId(),
							"", issueTypeDomain.getDomainName()));
				} else {
					sb.append(String.format(OPTION_TEMPLATE,
							issueTypeDomain.getId(), issueTypeDomain.getId(),
							"", issueTypeDomain.getDomainName()));
				}
			}
			if (!"".equals(reletionId)) {
				sb.append(createRelationSelectJs(issueTypeDomains));
			}
			out.print(sb.toString());
		} catch (Exception e) {
			logger.error("异常信息", e);
		}
	}

	private String createRelationSelectJs(List<IssueTypeDomain> issueTypeDomains) {
		List<IssueType> issueTypeList = new ArrayList<IssueType>();
		for (IssueTypeDomain issueTypeDomain : issueTypeDomains) {
			issueTypeList.addAll(issueTypeService
					.findIssueTypesByDomainId(issueTypeDomain.getId()));
		}
		String arrayName = "issueTypeArr";
		StringBuffer sb = new StringBuffer(50);
		sb.append("<script>");
		sb.append("var isOperateDiv=" + isOperateDiv + ";\n");
		sb.append("$(document).ready(function(){");
		sb.append("\n " + arrayName + "=" + JSONArray.fromObject(issueTypeList)
				+ ";");
		sb.append("\nfor(var i=0;i<" + arrayName + ".length;i++){\nif("
				+ arrayName + "[i].id=='" + defaultTypeValue + "'){\n$(\"#"
				+ reletionId + "\").empty();" + "\n$(\"#" + reletionId
				+ "\").removeAttr(\"disabled\");\n" + "$(\"#" + reletionId
				+ "\").append(\"<option value='\"+" + arrayName
				+ "[i].id+\"' selected>\"+" + arrayName
				+ "[i].issueTypeName+\"</option>\");\n}\n}");
		sb.append("\n$(\"#" + id + "\").change(function(){")
				.append("\n$(\"#"
						+ reletionId
						+ "\").empty();"
						+ "\nif($(\"#"
						+ id
						+ "\").find(\"option:selected\").text()=='请选择'){$(\"#"
						+ reletionId
						+ "\").attr(\"disabled\",\"true\");}else{$(\"#"
						+ reletionId
						+ "\").removeAttr(\"disabled\")}\n  var currentId=$(\"#"
						+ id + "\").find(\"option:selected\").attr(\"id\");")
				.append("\nfor(var i=0;i<" + arrayName
						+ ".length;i++){\nif(currentId==" + arrayName
						+ "[i].issueTypeDomain.id){\n" + "$(\"#" + reletionId
						+ "\").append(\"<option value='\"+" + arrayName
						+ "[i].id+\"'>\"+" + arrayName
						+ "[i].issueTypeName+\"</option>\");\n}}");
		sb.append("\nif(isOperateDiv=='1' && typeof operateDiv !='undefined'){operateDiv();}");// 是否操作div元素
		sb.append("});\n})");
		sb.append("</script>");
		return sb.toString();
	}

	private IssueTypeService getIssueTypeService() {
		ApplicationContext applicationContext = WebApplicationContextUtils
				.getWebApplicationContext(this.pageContext.getServletContext());

		IssueTypeService issueTypeService = (IssueTypeService) applicationContext
				.getBean("issueTypeService");
		return issueTypeService;
	}

	/**
	 * 即保留对原来写类别中文名的支持，又支持OGNL表达式写法.
	 * 
	 * @return
	 */
	private String getValueByExpression(String name) {
		String result = name;
		try {
			OgnlContext context = new OgnlContext();
			Object parseExpression = Ognl.parseExpression(name);
			Object value = Ognl.getValue(parseExpression, context);
			if (value != null)
				result = value.toString();
		} catch (OgnlException e) {
			logger.error("异常信息", e);
		}
		return result;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isNotNull() {
		return notNull;
	}

	public void setNotNull(boolean notNull) {
		this.notNull = notNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isShowInternalId() {
		return showInternalId;
	}

	public void setShowInternalId(boolean showInternalId) {
		this.showInternalId = showInternalId;
	}

	public String getReletionId() {
		return reletionId;
	}

	public void setReletionId(String reletionId) {
		this.reletionId = reletionId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReletionName() {
		return reletionName;
	}

	public void setReletionName(String reletionName) {
		this.reletionName = reletionName;
	}

	public void setIsOperateDiv(String isOperateDiv) {
		this.isOperateDiv = isOperateDiv;
	}

	public String getIsOperateDiv() {
		return isOperateDiv;
	}

	public String getDefaultTypeValue() {
		return defaultTypeValue;
	}

	public void setDefaultTypeValue(String defaultTypeValue) {
		this.defaultTypeValue = defaultTypeValue;
	}

}
