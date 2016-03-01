package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;
import com.tianque.issue.constants.IssueTypes;

public class PeopleLiveService extends BaseProperty {
	public final static String PEOPLELIVE_SERVICE_KEY = IssueTypes.PEOPLELIVE_SERVICE;

	public final static int DIFFICULTY_SALVATION = 1;
	public final static int EDUCATION = 2;
	public final static int MEDICARE_SECURITY = 3;
	public final static int WORKING = 4;
	public final static int HOURSE = 5;
	public final static int TRAFFIC = 6;
	public final static int CERTIFICATE = 7;
	public final static int ENTERPRISE = 8;
	public final static int QUALIFICATION = 9;
	public final static int BUSINESS = 10;
	public final static int BABY = 11;
	public final static int PUBLICUTILITIES = 12;
	public final static int OTHER = 13;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(DIFFICULTY_SALVATION, "教育服务"));
		properties.add(getInternalProperty(EDUCATION, "社保服务"));
		properties.add(getInternalProperty(MEDICARE_SECURITY, "就业服务"));
		properties.add(getInternalProperty(WORKING, "医疗服务"));
		properties.add(getInternalProperty(HOURSE, "住房服务"));
		properties.add(getInternalProperty(TRAFFIC, "交通服务"));

		properties.add(getInternalProperty(CERTIFICATE, "证件办理"));
		properties.add(getInternalProperty(ENTERPRISE, "企业服务"));
		properties.add(getInternalProperty(QUALIFICATION, "资质服务"));
		properties.add(getInternalProperty(BUSINESS, "经营纳税"));
		properties.add(getInternalProperty(BABY, "婚育收养"));
		properties.add(getInternalProperty(PUBLICUTILITIES, "公共事业"));
		properties.add(getInternalProperty(OTHER, "其他"));

		return properties;
	}

}
