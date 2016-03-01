package com.tianque.xichang.working.domain;

import com.tianque.domain.PropertyDict;

/** 三本台账报表统计辅助类 */
public class ReportGroupCount {

	/** 分组行：（建表类型） */
	private Long rowType;
	/** 分组列：（统计大类） */
	private PropertyDict item;
	private String items;
	/** 统计数 */
	private int count;

	public Long getRowType() {
		return rowType;
	}

	public void setRowType(Long rowType) {
		this.rowType = rowType;
	}

	public PropertyDict getItem() {
		return item;
	}

	public void setItem(PropertyDict item) {
		this.item = item;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
