package com.tianque.baseInfo.companyPlace.constant;

/**
 * 挂牌等级
 * 
 * @author liulei
 * 
 */
public class ListingType {
	public static String LISTEDLEVE = "listedLeve";
	public static String LISTING_RED_NAME = "红牌";
	public static String LISTING_YELLOW_NAME = "黄牌";

	public static Long LISTING_RED_VALUE = 0L;
	public static Long LISTING_YELLOW_VALUE = 1L;

	public static String getListingType(Object key) {
		if (key.equals(LISTING_RED_VALUE)) {
			return LISTING_RED_NAME;
		} else if (key.equals(LISTING_YELLOW_VALUE)) {
			return LISTING_YELLOW_NAME;
		} else {
			return null;
		}
	}

}
