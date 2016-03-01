package com.tianque.component.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;

import com.tianque.domain.Organization;

public class OrganizationSeqComparator<T> implements Comparator<T> {
	private String orgPropertyName;

	private Field orgField;

	public OrganizationSeqComparator(String propertyName) {
		this.orgPropertyName = propertyName;
	}

	@Override
	public int compare(T o1, T o2) {
		if (o1 == null && o2 == null)
			return 0;
		if (o1 == null)
			return -1;
		if (o2 == null)
			return 1;
		try {
			Field orgField = getAccessibledOrgProperty(o1);
			Organization org1 = (Organization) orgField.get(o1);
			Organization org2 = (Organization) orgField.get(o2);
			return compareOrgSeq(org1, org2);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int compareOrgSeq(Organization org1, Organization org2) {
		if (org1 == null && org2 == null)
			return 0;
		if (org1 == null)
			return -1;
		if (org2 == null)
			return 1;
		return org1.getSeq().compareTo(org2.getSeq());
	}

	private Field getAccessibledOrgProperty(T object) throws Exception {
		if (orgField == null) {
			orgField = getOrgProperty(object);
			if (orgField != null && !orgField.isAccessible()) {
				orgField.setAccessible(true);
			}
		}
		return orgField;
	}

	private Field getOrgProperty(T object) {
		Field result = null;
		Class clazz = object.getClass();
		while (result == null && !clazz.equals(Object.class)) {
			try {
				result = clazz.getDeclaredField(orgPropertyName);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
			}
			clazz = clazz.getSuperclass();
		}
		return result;
	}

}
