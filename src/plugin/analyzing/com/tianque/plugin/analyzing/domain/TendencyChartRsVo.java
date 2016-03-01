package com.tianque.plugin.analyzing.domain;

import org.apache.commons.lang.StringUtils;

/**
 * 专用于研判分析趋势图（优化）
 * @author zhoupengfei
 * @since 2013/12/20
 */
public class TendencyChartRsVo implements Comparable {
	/**key,eg:2013-9-positiveInfo**/
	private String key;
	/**num,eg:89**/
	private Integer num = 0;

	@Override
	public int compareTo(Object o) {
		if (!(o instanceof TendencyChartRsVo)) {
			throw new RuntimeException("不是该类型实例");
		}
		TendencyChartRsVo other = (TendencyChartRsVo) o;
		String[] otherKeyArr = other.key.split("_");
		String[] myKeyArr = key.split("_");
		int otherYear = Integer.parseInt(otherKeyArr[0]);
		int year = Integer.parseInt(myKeyArr[0]);

		int otherMonth = Integer.parseInt(otherKeyArr[1]);
		int month = Integer.parseInt(myKeyArr[1]);
		if (year == otherYear && month == otherMonth) {
			return 0;
		} else if (year >= otherYear && month > otherMonth) {
			return 1;
		} else {
			return -1;
		}
	}

	public String getTypeName() {
		if (!StringUtils.isEmpty(key)) {
			String[] myKeyArr = key.split("_");
			if (myKeyArr.length == 3) {
				return myKeyArr[2];
			}
		}
		return "";
	}

	public String getKeyTime() {
		if (!StringUtils.isEmpty(key)) {
			return key.substring(0, key.lastIndexOf("_"));
		}
		return "";
	}

	public String toString() {
		return key + ":" + num;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

}
