package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class ReportDateType extends BaseProperty {

	public final static String REPORTDATETYPE_KEY = PropertyTypes.REPORT_DATE_TYPE;
	public final static int GROUPBYMONTH = 0;
	public final static int GROUPBYQUARTER = 1;
	public final static int GROUPBYYEAR = 2;
	public final static String GROUPBYMONTH_KEY = "按月份统计";
	public final static String GROUPBYQUARTER_KEY = "按季度统计";
	public final static String GROUPBYYEAR_KEY = "按年度统计";

	public final static int ORGLEVELDEALSTAT = 9;
	public final static int ORGLEVELDEALSTAT_ADMINORG = 10;
	public final static int ORGLEVELDEALSTAT_FUNORG = 11;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(GROUPBYMONTH, GROUPBYMONTH_KEY));
		properties.add(getInternalProperty(GROUPBYQUARTER, GROUPBYQUARTER_KEY));
		properties.add(getInternalProperty(GROUPBYYEAR, GROUPBYYEAR_KEY));

		return properties;
	}

	public static Calendar getDefaultCalendar() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2012, 1, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

}
