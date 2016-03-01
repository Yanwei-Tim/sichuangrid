package com.tianque.baseInfo.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tianque.domain.PropertyDict;

public class CollectionsUtil<T> {
	static Logger logger = Logger.getLogger(CollectionsUtil.class);
	
	public void sortList(List<T> lists, final String sord,
			final String sidx) {
		Collections.sort(lists, new Comparator<T>() {
			@Override
			public int compare(T o1, T o2) {
				if ("asc".equals(sord.toLowerCase())) {
					Object obj = invokeMemthod(o1);
					if (obj instanceof Long) {
						Object objTemp = invokeMemthod(o2);
						if (objTemp instanceof Long) {
							return (Long) obj > (Long) objTemp ? 1 : -1;
						} else {
							return 1;
						}
					} else {
						return obj.toString().compareTo(
								invokeMemthod(o2).toString());
					}
				} else {
					Object obj = invokeMemthod(o2);
					if (obj instanceof Long) {
						Object objTemp = invokeMemthod(o1);
						if (objTemp instanceof Long) {
							return (Long) obj > (Long) objTemp ? 1 : -1;
						} else {
							return 1;
						}
					} else {
						return obj.toString().compareTo(
								invokeMemthod(o1).toString());
					}
				}
			}

			private Object invokeMemthod(Object arg0) {
				Object obj = null;
				try {
					obj = arg0
							.getClass()
							.getMethod(
									"get" + (sidx.charAt(0) + "").toUpperCase()
											+ sidx.substring(1)).invoke(arg0);
				} catch (Exception e) {
					logger.error(e);
				}
				if (obj instanceof PropertyDict) {
					obj = ((PropertyDict) obj).getId();
				}
				if (obj instanceof Date) {
					obj = ((Date) obj).getTime();
				}
				return obj == null ? 0 : obj;
			}
		});
	}
}
