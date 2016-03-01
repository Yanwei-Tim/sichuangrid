package com.tianque.plugin.analyzing.domain;

import java.io.Serializable;
import java.util.List;

public class HighchartColumnVo implements Serializable{
	private String moduleName; // 模块名
	private String[] categories; // 部门数组
	private List<HighchartDataColumnVo> series; // 各类型对应的数据

	public String[] getCategories() {
		return categories;
	}

	public void setCategories(String[] categories) {
		this.categories = categories;
	}

	public List<HighchartDataColumnVo> getSeries() {
		return series;
	}

	public void setSeries(List<HighchartDataColumnVo> series) {
		this.series = series;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
}
