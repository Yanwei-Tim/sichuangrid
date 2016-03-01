package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class JiasPartyBuildSupportingEffect extends BaseProperty {
	public final static int FEICHANGHAO = 0;
	public final static int HAO = 1;
	public final static int YIBAN = 2;
	public final static int BUHAO = 3;
	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(FEICHANGHAO, "非常好"));
		properties.add(getInternalProperty(HAO, "好"));
		properties.add(getInternalProperty(YIBAN, "一般"));
		properties.add(getInternalProperty(BUHAO, "不好"));
		return properties;
	}

}
