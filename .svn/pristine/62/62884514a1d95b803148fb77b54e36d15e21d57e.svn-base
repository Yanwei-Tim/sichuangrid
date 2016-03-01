package com.tianque.domain.vo;

public class CompareObjectLog {
	private String ename;
	private String cname;
	private String type;
	private String oldValue;
	private String newValue;

	public CompareObjectLog() {
	}

	public CompareObjectLog(String type) {
		this.type = type;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOldValue() {
		return oldValue;
	}

	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("字段：").append(cname == null ? ename : cname)
				.append("(类型： ").append(type).append(")").append(" 从 ")
				.append(oldValue).append(" 修改为 ").append(newValue).append("; ");
		return buffer.toString();
	}
}
