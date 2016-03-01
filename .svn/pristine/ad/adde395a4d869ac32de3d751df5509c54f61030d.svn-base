package com.tianque.tqSearch.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.SolrDocument;

import com.tianque.core.vo.PageInfo;
import com.tianque.tqSearch.domain.TqSolrDocumentList;

/**
 * 
 * @作者:龙振东
 * @功能: solr查询结果到实体对象的映射
 * @时间:2015-6-2 下午01:51:18
 * @邮箱:longzhendong@hztianque.com
 */
public class SolrResultRelationalObject {

	private static String SET = "set";

	private static String GET = "get";

	/**
	 * 
	 * @param SolrDocuments
	 * @param clazz
	 * @param searchType
	 *            对应的模块
	 * @return
	 * @throws Exception
	 */
	public static void conversionPageInfoResult(
			TqSolrDocumentList tqSolrDocuments, PageInfo pageInfo, Class clazz,
			String searchType) {
		Map<String, Method> targetObjectMethodMap = new HashMap<String, Method>();
		Map<String, Field> targetObjectFieldMap = new HashMap<String, Field>();
		fillAllMethodsAndFields(targetObjectMethodMap, targetObjectFieldMap,
				clazz);
		Map<String, String> indexObjectMap = IndexFieldAndObjectFieldMapping
				.getModeMap(searchType);
		List result = new ArrayList();
		ArrayList<SolrDocument> solrDocuments = tqSolrDocuments.getResults();
		try {
			if (solrDocuments != null && solrDocuments.size() > 0) {
				for (SolrDocument solrDocument : solrDocuments) {
					Object object = clazz.newInstance();
					for (String property : indexObjectMap.keySet()) {
						String[] childFields = property.split("\\.");
						Object[] obs = new Object[childFields.length];
						Class frontClass = null;
						for (int i = 0; i <= childFields.length - 1; i++) {
							if (i == 0) {
								String tempField = childFields[i].substring(0,
										1).toUpperCase()
										+ childFields[i].substring(1);
								String setMethod = SET + tempField;
								String getMethod = GET + tempField;
								Field field = targetObjectFieldMap
										.get(childFields[i]);
								if (field == null) {
									throw new RuntimeException(childFields[i]
											+ " property not find");
								}
								if (childFields.length == 1) {
									isFillPrimitiveProperyValue(
											targetObjectMethodMap, setMethod,
											field.getType(),
											indexObjectMap.get(property),
											object, solrDocument);
								} else {
									if (targetObjectMethodMap.get(getMethod)
											.invoke(object) == null) {
										obs[i] = field.getType().newInstance();
										targetObjectMethodMap.get(setMethod)
												.invoke(object, obs[i]);
									} else {
										obs[i] = targetObjectMethodMap.get(
												getMethod).invoke(object);
									}
								}
								frontClass = field.getType();
							} else {
								Map<String, Method> propertyMethodMap = new HashMap<String, Method>();
								Map<String, Field> propertyFieldMap = new HashMap<String, Field>();
								fillAllMethodsAndFields(propertyMethodMap,
										propertyFieldMap, frontClass);
								Field field = propertyFieldMap
										.get(childFields[i]);
								if (field == null) {
									throw new RuntimeException(childFields[i]
											+ " property not find");
								}
								Class handleClass = field.getType();
								String setMethod = SET
										+ childFields[i].substring(0, 1)
												.toUpperCase()
										+ childFields[i].substring(1);
								if (!isFillPrimitiveProperyValue(
										propertyMethodMap, setMethod,
										handleClass,
										indexObjectMap.get(property),
										obs[i - 1], solrDocument)) {
									obs[i] = handleClass.newInstance();
									propertyMethodMap.get(setMethod).invoke(
											obs[i - 1], obs[i]);
								}
								frontClass = handleClass;

							}
						}
					}
					result.add(object);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		pageInfo.setResult(result);
	}

	/**
	 * 
	 * @param propertyMethodMap
	 * @param setMethod
	 * @param handleClass
	 * @param solrField
	 * @param handleObject
	 * @param solrDocument
	 * @return
	 * @throws Exception
	 */
	private static boolean isFillPrimitiveProperyValue(
			Map<String, Method> propertyMethodMap, String setMethod,
			Class handleClass, String solrField, Object handleObject,
			SolrDocument solrDocument) throws Exception {
		if (solrDocument.getFieldValue(solrField) == null) {
			return true;
		}
		Object resultValues = solrDocument.getFieldValue(solrField);
		if (handleClass == Long.class || handleClass == Long.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Long.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Integer.class || handleClass == Integer.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Integer.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Byte.class || handleClass == Byte.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Byte.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Short.class || handleClass == Short.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Short.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Float.class || handleClass == Float.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Float.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Double.class || handleClass == Double.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Double.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Boolean.class || handleClass == Boolean.TYPE) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					Boolean.valueOf(resultValues.toString()));
			return true;
		} else if (handleClass == Date.class) {
			propertyMethodMap.get(setMethod).invoke(handleObject,
					(Date) resultValues);
			return true;
		} else if (handleClass == String.class) {
			propertyMethodMap.get(setMethod).invoke(handleObject, resultValues);
			return true;
		}
		return false;
	}

	/**
	 * 获取一个类里所有的字段和方法，包括父类的
	 * 
	 * @param methodMap
	 * @param fieldMap
	 * @param clazz
	 */
	private static void fillAllMethodsAndFields(Map methodMap, Map fieldMap,
			Class clazz) {
		for (Method method : clazz.getMethods()) {
			methodMap.put(method.getName(), method);
		}
		for (Field field : clazz.getDeclaredFields()) {
			fieldMap.put(field.getName(), field);
		}
		if (clazz.getSuperclass() != null) {
			fillAllMethodsAndFields(methodMap, fieldMap, clazz.getSuperclass());
		}
	}
}
