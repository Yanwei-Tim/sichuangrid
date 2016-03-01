package com.tianque.plugin.transfer.vo;

public class ModifyVo {
	/**重复数据的类型*/
	private String type;
	/**需要转移的对象id*/
	private Long oldId;
	/**转移目标处重复对象的id*/
	private Long newId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getOldId() {
		return oldId;
	}

	public void setOldId(Long oldId) {
		this.oldId = oldId;
	}

	public Long getNewId() {
		return newId;
	}

	public void setNewId(Long newId) {
		this.newId = newId;
	}
}
