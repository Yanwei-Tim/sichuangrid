package com.tianque.domain.property;

import java.util.ArrayList;
import java.util.List;

import com.tianque.core.property.BaseProperty;
import com.tianque.core.property.GridInternalProperty;

public class WorkingRecordTypes extends BaseProperty {
	public final static int MEETING_RECORD = 0;
	public final static int FILE = 1;
	public final static int ACTIVITY_RECORD = 2;
	public final static int DOCUMENT = 3;
	public final static int OTHER = 4;

	public final static String MEETING_RECORD_NAME = "会议记录";
	public final static String FILE_NAME = "政策文件";
	public final static String ACTIVITY_RECORD_NAME = "活动记录";
	public final static String DOCUMENT_NAME = "文档";
	public final static String OTHER_NAME = "其他";

	private static List<GridInternalProperty> properties = new ArrayList<GridInternalProperty>();

	public static List<GridInternalProperty> getInternalProperties() {
		if (properties.size() > 0) {
			return properties;
		}
		properties.add(getInternalProperty(MEETING_RECORD, MEETING_RECORD_NAME));
		properties.add(getInternalProperty(FILE, FILE_NAME));
		properties.add(getInternalProperty(ACTIVITY_RECORD, ACTIVITY_RECORD_NAME));
		properties.add(getInternalProperty(DOCUMENT, DOCUMENT_NAME));
		properties.add(getInternalProperty(OTHER, OTHER_NAME));
		return properties;
	}

	public final static String WORKING_RECORD_TYPES_KEY = PropertyTypes.WORKINGRECORD_TYPE;
}
