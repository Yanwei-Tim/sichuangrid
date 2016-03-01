package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class StatementsReportedType extends BaseProperty {
	public final static int CHECK = 1;
	public final static int ISSUEDEAL = 2;
	public final static int INVESTIGATION = 3;
	public final static int SIGNIFICANT_ISSUEDEAL = 4;
	public final static int INVESTIGATION_REMEDIATION = 5;

	public final static String CHECK_NAME = "治安重点排查整治类";
	public final static String ISSUEDEAL_NAME = "矛盾纠纷排查调处类";
	public final static String INVESTIGATION_NAME = "治安重点排查情况";
	public final static String SIGNIFICANT_ISSUEDEAL_NAME = "重大矛盾纠纷排查调处情况";
	public final static String INVESTIGATION_REMEDIATION_NAME = "排查整治强基促稳情况类";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(CHECK, CHECK_NAME));
		properties.add(getInternalProperty(ISSUEDEAL, ISSUEDEAL_NAME));
		properties.add(getInternalProperty(INVESTIGATION, INVESTIGATION_NAME));
		properties.add(getInternalProperty(SIGNIFICANT_ISSUEDEAL, SIGNIFICANT_ISSUEDEAL_NAME));
		properties.add(getInternalProperty(INVESTIGATION_REMEDIATION,
				INVESTIGATION_REMEDIATION_NAME));
		return properties;
	}

	public final static String STATEMENTS_REPORTED_TYPE_KEY = PropertyTypes.STATEMENTS_REPORTED_TYPE;

}
