package com.tianque.plugin.tqSearch.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TqSearchVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String type;
	/** AND 关联的搜索条件 */
	private Map<String, Object> searchs;
	/** OR 关联的搜索条件 */
	private Map<String, Object> orSearchs;
	/** 排序字段及方式 key:排序字段，value:排序方式 */
	private Map<String, String> sortFields;
	/** 输入框——搜索值 */
	private String searchText;
	/** 输入框——搜索字段名 */
	private String searchFields;
	/** 显示页 */
	private Integer page = 1;
	/** 一页行数 */
	private Integer rows = 10;

	private Long id;
	/** 指定查询输出结构格式 */
	private String returnType = "json";// xml、json、python、ruby、php、phps、custom
	/** 指定返回结果字段 */
	private List<String> returnFields;

	/** 登录用户 */
	private String userName;
	/** 当前session */
	private String sessionId;

	/** 组织机构 */
	private Long orgId;

	private String sql;
	/**业务表名，或表标识符*/
	private String keyTableType;
	
	public TqSearchVo() {
		super();
		this.searchs = new HashMap<String, Object>();
		this.orSearchs = new HashMap<String, Object>();
		this.sortFields = new HashMap<String, String>();
	}
	
	public TqSearchVo(String type, String sql) {
		super();
		this.type = type;
		this.sql = sql;
		this.searchs = new HashMap<String, Object>();
		this.orSearchs = new HashMap<String, Object>();
		this.sortFields = new HashMap<String, String>();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public Map<String, Object> getSearchs() {
		return searchs;
	}

	public void setSearchs(Map<String, Object> searchs) {
		this.searchs = searchs;
	}

	public Map<String, Object> getOrSearchs() {
		return orSearchs;
	}

	public void setOrSearchs(Map<String, Object> orSearchs) {
		this.orSearchs = orSearchs;
	}

	public Map<String, String> getSortFields() {
		return sortFields;
	}

	public void setSortFields(Map<String, String> sortFields) {
		this.sortFields = sortFields;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<String> getReturnFields() {
		return returnFields;
	}

	public void setReturnFields(List<String> returnFields) {
		this.returnFields = returnFields;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}

	public String getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

	public String getKeyTableType() {
		return keyTableType;
	}

	public void setKeyTableType(String keyTableType) {
		this.keyTableType = keyTableType;
	}
}
