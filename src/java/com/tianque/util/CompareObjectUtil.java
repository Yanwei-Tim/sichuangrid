package com.tianque.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tianque.baseInfo.actualHouse.domain.HouseInfo;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.baseInfo.base.domain.LogoutDetail;
import com.tianque.baseInfo.companyPlace.constant.ListingType;
import com.tianque.baseInfo.householdStaff.domain.HouseholdStaff;
import com.tianque.core.base.BaseDomain;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.property.RenovateType;
import com.tianque.domain.vo.CompareObjectLog;

public class CompareObjectUtil {

	public static final String PROPERTYDICT = "PropertyDict";
	public static final String ORGANIZATION = "Organization";
	/** 操作日志中不需要包含的字段 */
	static final String[] excludeProperties, objectProperties;

	static {
		excludeProperties = new String[] { "baseInfoId", "addressInfoId",
				"houseId", "orgInternalCode", "logOut", "isEmphasis",
				"currentAddressType", "attentionPopulationType",
				"organization", "actualPopulationType", "uuid", "fullPinyin",
				"simplePinyin", "houseType", "rentalHouse", "community",
				"block", "unit", "room", "imgUrl", "isSignGuarantee",
				"isSafetyChannel", "isFireChannels", "houseOperateSource",
				"addressType", "builddatasId", "orginternalcode",
				"isEmphasisDate", "isEmphasisReason", "companyPlaceSource",
				"companyPlaceId", "lastRecordTime", "typeIdsForMobile",
				"levelIdsForMobile", "mongoOrgCode", "isRemind" };

		objectProperties = new String[] { "hasLicense", "cloakRoom",
				"isUnmarriedPregnant", "isLevied", "isMaternityCard" };// 需要特殊处理的字段
	}

	public static <T> List<CompareObjectLog> compareAll(T src, T old)
			throws Exception {
		Class cls = src.getClass();
		return compareAll(cls, src, old);
	}

	public static <T> List<CompareObjectLog> compareAll(Class cls, T src, T old)
			throws Exception {
		List<CompareObjectLog> compareList = compareThis(cls, src, old);
		Class superCls = cls.getSuperclass();
		if (!superCls.equals(BaseDomain.class)) {
			compareList.addAll(compareAll(superCls, src, old));
		}
		return compareList;
	}

	public static <T> List<CompareObjectLog> compareBusiness(T src, T old)
			throws Exception {
		Class cls = src.getClass();
		return compareBusiness(cls, src, old);
	}

	public static <T> List<CompareObjectLog> compareHouseInfoBusiness(T src,
			T old) throws Exception {
		Class cls = src.getClass();
		return compareHouseInfoBusiness(cls, src, old);
	}

	public static <T> List<CompareObjectLog> compareBusiness(Class cls, T src,
			T old) throws Exception {
		List<CompareObjectLog> compareList = compareThis(cls, src, old);
		Class superCls = cls.getSuperclass();
		if (superCls != null) {
			if (!superCls.equals(Countrymen.class)) {
				compareList.addAll(compareBusiness(superCls, src, old));
			}
		}
		return compareList;
	}

	public static <T> List<CompareObjectLog> compareHouseInfoBusiness(
			Class cls, T src, T old) throws Exception {
		List<CompareObjectLog> compareList = compareThis(cls, src, old);
		Class superCls = cls.getSuperclass();
		if (superCls != null) {
			if (!superCls.equals(HouseInfo.class)
					&& !superCls.equals(BaseDomain.class)) {
				compareList
						.addAll(compareHouseInfoBusiness(superCls, src, old));
			}
		}
		return compareList;
	}

	public static <T> List<CompareObjectLog> compareThis(T src, T old)
			throws Exception {
		Class cls = src.getClass();
		return compareThis(cls, src, old);
	}

	public static <T> List<CompareObjectLog> compareThis(Class clazz, T src,
			T old) throws Exception {
		List<CompareObjectLog> compareList = new ArrayList<CompareObjectLog>();
		Field[] fs = clazz.getDeclaredFields();
		for (Field f : fs) {
			int mod = f.getModifiers();
			if ((mod & Modifier.FINAL) != 0) {
				continue;
			}
			if (includeStr(f.getName(), excludeProperties))
				continue;
			CompareObjectLog log = equals(f, src, old);
			if (log != null) {
				compareList.add(log);
			}
		}
		return compareList;
	}

	private static boolean includeStr(String str, String[] array) {
		if (null == array || array.length <= 0) {
			return false;
		}
		for (int i = 0; i < array.length; i++)
			if (str.equals(array[i]))
				return true;
		return false;
	}

	private static <T> CompareObjectLog equals(Field f, T src, T old)
			throws Exception {
		f.setAccessible(true);
		Object newValue = f.get(src);
		Object oldValue = f.get(old);
		CompareObjectLog log = null;
		if (PropertyDict.class.equals(f.getGenericType())) {// 如果该属性的类型是字典项的类型
			log = comparePropertyDict((PropertyDict) newValue,
					(PropertyDict) oldValue);
			if (log != null)
				log.setEname(f.getName());
		} else if (Organization.class.equals(f.getGenericType())) {
			log = compareOrganization((Organization) newValue,
					(Organization) oldValue);
			if (log != null)
				log.setEname(f.getName());
			// 注销是操作，未完成的方法
			// } else if (f.getType().newInstance() instanceof BaseDomain) {
			// Iterator<CompareObjectLog> it = compareAll(f.getType(),
			// (BaseDomain) newValue, (BaseDomain) oldValue).iterator();
			// while (it.hasNext()) {
			// log = it.next();
			// }
		} else if (Date.class.equals(f.getGenericType())) {
			log = compareDate(newValue, oldValue);
			if (log != null) {
				log.setEname(f.getName());
			}
		} else if (f.getType().isAssignableFrom(Boolean.class)
				|| "boolean".equals(f.getType().getName())) {
			log = compareObjectType(newValue, oldValue);
			if (log != null) {
				log.setEname(f.getName());
				String type = f.getGenericType().toString();
				log.setType(type.substring(type.lastIndexOf(".") + 1));
			}
		} else if (f.getType().isPrimitive()
				|| f.getType().isAssignableFrom(Byte.class)
				|| f.getType().isAssignableFrom(Short.class)
				|| f.getType().isAssignableFrom(Integer.class)
				|| f.getType().isAssignableFrom(Long.class)
				|| f.getType().isAssignableFrom(Character.class)
				|| f.getType().isAssignableFrom(Float.class)
				|| f.getType().isAssignableFrom(Double.class)
				|| f.getType().isAssignableFrom(String.class)
				|| f.getType().isAssignableFrom(BigDecimal.class)) {
			if (RenovateType.LISTENCORRECTION.equals(f.getName())) {
				log = compareLongToString(newValue, oldValue,
						RenovateType.LISTENCORRECTION);
			} else if (ListingType.LISTEDLEVE.equals(f.getName())) {
				log = compareLongToString(newValue, oldValue,
						ListingType.LISTEDLEVE);
			} else if (includeStr(f.getName(), objectProperties)) {
				log = compareObjectType(newValue, oldValue);
			} else {
				log = compareBaseType(newValue, oldValue);
			}
			if (log != null) {
				log.setEname(f.getName());
				String type = f.getGenericType().toString();
				log.setType(type.substring(type.lastIndexOf(".") + 1));
			}
		}
		return log;
	}

	private static <T> CompareObjectLog compareLongToString(Object newValue,
			Object oldValue, String type) {
		CompareObjectLog temp = new CompareObjectLog();
		if ((oldValue == null || "".equals(oldValue))
				&& (newValue == null || "".equals(newValue))) {
			temp = null;
		} else if (oldValue == null || "".equals(oldValue)) {
			temp.setOldValue("null");
			temp.setNewValue(String.valueOf(newValue));
		} else if (oldValue.equals(newValue)) {
			temp = null;
		} else {
			if (RenovateType.LISTENCORRECTION.equals(type)) {
				temp.setOldValue(RenovateType.getRenovateTypeMap(oldValue));
				temp.setNewValue(newValue == null || "".equals(newValue) ? "null"
						: RenovateType.getRenovateTypeMap(newValue));
			} else if (ListingType.LISTEDLEVE.equals(type)) {
				temp.setOldValue(ListingType.getListingType(oldValue));
				temp.setNewValue(newValue == null || "".equals(newValue) ? "null"
						: ListingType.getListingType(newValue));
			}
		}
		return temp;
	}

	private static <T> CompareObjectLog comparePropertyDict(
			PropertyDict newValue, PropertyDict oldValue) {
		CompareObjectLog temp = new CompareObjectLog("PropertyDict");
		if ((oldValue == null || oldValue.getId() == null)
				&& (newValue == null || newValue.getId() == null)) {
			temp = null;
		} else if (oldValue == null || oldValue.getId() == null) {
			temp.setOldValue("null");
			temp.setNewValue(String.valueOf(newValue.getId()));
		} else if (oldValue.equals(newValue)) {
			temp = null;
		} else {
			temp.setOldValue(String.valueOf(oldValue.getId()));
			temp.setNewValue(newValue == null || newValue.getId() == null ? "null"
					: String.valueOf(newValue.getId()));
		}
		return temp;
	}

	private static <T> CompareObjectLog compareOrganization(
			Organization newValue, Organization oldValue) {
		CompareObjectLog temp = new CompareObjectLog("Organization");
		if (oldValue == null && newValue == null) {
			temp = null;
		} else if (oldValue == null) {
			temp.setOldValue("null");
			temp.setNewValue(String.valueOf(newValue.getId()));
		} else if (oldValue.equals(newValue)) {
			temp = null;
		} else {
			temp.setOldValue(String.valueOf(oldValue.getId()));
			temp.setNewValue(newValue == null ? "null" : String
					.valueOf(newValue.getId()));
		}
		return temp;
	}

	private static <T> CompareObjectLog compareBaseType(Object newValue,
			Object oldValue) {
		CompareObjectLog temp = new CompareObjectLog();
		if ((oldValue == null || "".equals(oldValue))
				&& (newValue == null || "".equals(newValue))) {
			temp = null;
		} else if (oldValue == null || "".equals(oldValue)) {
			temp.setOldValue("null");
			temp.setNewValue(String.valueOf(newValue));
		} else if (oldValue.equals(newValue)) {
			temp = null;
		} else {
			temp.setOldValue(String.valueOf(oldValue));
			temp.setNewValue(newValue == null || "".equals(newValue) ? "null"
					: String.valueOf(newValue));
		}
		return temp;
	}

	private static <T> CompareObjectLog compareObjectType(Object newValue,
			Object oldValue) {
		CompareObjectLog temp = new CompareObjectLog();
		newValue = transferObjectToString(newValue);
		oldValue = transferObjectToString(oldValue);
		if ((oldValue == null || "".equals(oldValue))
				&& (newValue == null || "".equals(newValue))) {
			temp = null;
		} else if (oldValue == null || "".equals(oldValue)) {
			temp.setOldValue("否");
			temp.setNewValue(String.valueOf(newValue));
		} else if (oldValue.equals(newValue)) {
			temp = null;
		} else {
			temp.setOldValue(String.valueOf(oldValue));
			temp.setNewValue(newValue == null || "".equals(newValue) ? "null"
					: String.valueOf(newValue));
		}
		return temp;
	}

	private static String transferObjectToString(Object value) {
		if (value == null) {
			return "否";
		}
		if (value instanceof Integer) {
			if ((Integer) value == 1) {
				return "是";
			} else {
				return "否";
			}
		}
		if (value instanceof Long) {
			if ((Integer) value == 1) {
				return "是";
			} else {
				return "否";
			}
		}
		if (value instanceof String) {
			if (value.equals("1")) {
				return "是";
			} else {
				return "否";
			}
		}
		if (value instanceof Boolean) {
			if ((Boolean) value) {
				return "是";
			} else {
				return "否";
			}
		}
		return "";
	}

	private static <T> CompareObjectLog compareDate(Object newValue,
			Object oldValue) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CompareObjectLog temp = new CompareObjectLog("Date");
		if (oldValue == null && newValue == null) {
			temp = null;
		} else if (oldValue == null) {
			temp.setOldValue("null");
			temp.setNewValue(sdf.format(newValue));
		} else if (oldValue.equals(newValue)) {
			temp = null;
		} else {
			temp.setOldValue(sdf.format(oldValue));
			temp.setNewValue(newValue == null ? "null" : sdf.format(newValue));
		}
		return temp;
	}

	public static void main(String[] args) {
		PropertyDict p = new PropertyDict();
		p.setId(1L);
		p.setDisplayName("property1");

		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode(".");

		LogoutDetail ld = new LogoutDetail();
		ld.setLogout(1L);
		ld.setLogoutDate(new Date());

		Countrymen oldObject = new HouseholdStaff();
		oldObject.setAddressInfoId(1L);// Long
		oldObject.setActualPopulationType("aaa");// String
		oldObject.setBirthday(new Date());// Date
		oldObject.setIsHaveHouse(true);// Boolean
		oldObject.setGender(p);// PropertyDict
		oldObject.setOrganization(org);// Organization
		oldObject.setLogoutDetail(ld);// Object
		oldObject.setAttentionPopulationTypeCname("haha");

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		p = new PropertyDict();
		p.setId(2L);
		org = new Organization();
		org.setId(2L);
		ld = new LogoutDetail();
		ld.setLogout(2L);

		Countrymen newObject = new HouseholdStaff();
		newObject.setAddressInfoId(2L);// Long
		((HouseholdStaff) newObject).setActualtype("bbb");// String
		newObject.setBirthday(new Date());// Date
		newObject.setIsHaveHouse(false);// Boolean
		newObject.setGender(p);// PropertyDict
		newObject.setOrganization(org);// Organization
		newObject.setLogoutDetail(ld);// Object

		try {
			Long time = System.currentTimeMillis();
			System.out.println(compareThis(newObject, oldObject)
					+ " -------- compareThis");
			List<CompareObjectLog> logs = compareAll(newObject, oldObject);
			for (CompareObjectLog log : logs) {
				System.out.println(log + " -------- compareAll");
			}
			// System.out.println(dataChangesLog(map));
			System.out.println(System.currentTimeMillis() - time);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
