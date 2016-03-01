package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class PublicComplexPlacesType extends BaseProperty  {
	public final static int PUBLIC_AMENITIES = 0;
	public final static int SCENIC_SPOTS = 1;
	public final static int STATION_WHARF = 2;
	public final static int MARKET_PLACE = 3;

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(PUBLIC_AMENITIES, "公共娱乐场所"));
		properties.add(getInternalProperty(SCENIC_SPOTS, "景点"));
		properties.add(getInternalProperty(STATION_WHARF, "车站码头"));
		properties.add(getInternalProperty(MARKET_PLACE, "市场"));
		return properties;
	}

	public final static String PUBLICCOMPLEXPLACES_TYPE_KEY = PropertyTypes.PUBLICCOMPLEXPLACES_TYPE;
}
