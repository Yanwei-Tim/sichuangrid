package com.tianque.domain.constants;

import java.util.ArrayList;
import java.util.List;

public class NewsType {
	public static final String NEWS = "文字新闻";
	public static final String PIC_NEWS = "图片新闻";
	public static final List<String> NEWS_TYPE = new ArrayList<String>();
	static {
		NEWS_TYPE.add(NEWS);
		NEWS_TYPE.add(PIC_NEWS);

	}
}
