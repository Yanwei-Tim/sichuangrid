package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class DirectoryReportType extends BaseProperty {

	public final static int MONTH_REPORT = 1;
	public final static int QUARTER_REPORT = 2;
	public final static int SEMIYEARLY_REPORT = 3;
	public final static int YEAR_REPORT = 4;

	public final static String MONTH_REPORT_NAME = "月报";
	public final static String QUARTER_REPORT_NAME = "季报";
	public final static String SEMIYEARLY_REPORT_NAME = "半年报";
	public final static String YEAR_REPORT_NAME = "年报";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(MONTH_REPORT, MONTH_REPORT_NAME));
		properties.add(getInternalProperty(QUARTER_REPORT, QUARTER_REPORT_NAME));
		properties.add(getInternalProperty(SEMIYEARLY_REPORT, SEMIYEARLY_REPORT_NAME));
		properties.add(getInternalProperty(YEAR_REPORT, YEAR_REPORT_NAME));
		return properties;
	}

	public final static String DIRECTORY_REPORT_TYPE_KEY = PropertyTypes.DIRECTORY_REPORT_TYPE;
	public final static String DIRECTORY_CHECK_NAME = "治安重点整治";
	public final static String DIRECTORY_ISSUEDEAL_NAME = "矛盾纠纷排查";
}
