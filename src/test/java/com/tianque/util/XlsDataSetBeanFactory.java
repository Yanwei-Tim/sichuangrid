package com.tianque.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.converters.DateConverter;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.excel.XlsDataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 从EXCEL数据集文件创建Bean
 */
public class XlsDataSetBeanFactory {
	protected final static Logger logger = LoggerFactory.getLogger(XlsDataSetBeanFactory.class);

	public static <T> List<T> createBeans(String file, String tableName, Class<T> clazz,
			Object testObj) throws Exception {
		List<T> beans = new ArrayList<T>();
		IDataSet expected = new XlsDataSet(testObj.getClass().getResourceAsStream(file));
		ITable table = expected.getTable(tableName);
		Column[] columns = table.getTableMetaData().getColumns();
		for (int i = 0; i < table.getRowCount(); i++) {
			T bean = clazz.newInstance();
			createBean(table, columns, i, bean);
			beans.add(bean);
		}
		return beans;
	}

	private static Object createBean(ITable table, Column[] columns, int row, Object bean)
			throws Exception {
		Map<String, Object> props = new HashMap<String, Object>();
		BeanUtilsBean beanUtils = createBeanUtils();
		for (Column c : columns) {
			Object value = table.getValue(row, c.getColumnName());
			String propName = underlineToCamel(c.getColumnName());
			String[] propNames = propName.split("\\.");
			props.put(propNames[0], createBean(propNames, value, bean));
		}
		beanUtils.populate(bean, props);
		return bean;
	}

	public static <T> T createBean(String file, String tableName, Class<T> domainClazz,
			Object testObj) {
		try {
			IDataSet expected = new XlsDataSet(testObj.getClass().getResourceAsStream(file));
			ITable table = expected.getTable(tableName);
			Column[] columns = table.getTableMetaData().getColumns();
			T bean = domainClazz.newInstance();
			createBean(table, columns, 0, bean);
			return bean;
		} catch (Exception e) {
			logger.error("excel转换错误：", e);
			return null;
		}
	}

	public static Object createBean(String file, String tableName, Object bean, Object testObj)
			throws Exception {
		IDataSet expected = new XlsDataSet(testObj.getClass().getResourceAsStream(file));
		ITable table = expected.getTable(tableName);
		Column[] columns = table.getTableMetaData().getColumns();
		createBean(table, columns, 0, bean);
		return bean;
	}

	private static Object createBean(String[] names, Object value, Object parentNode)
			throws Exception, InvocationTargetException, InstantiationException {
		Map<String, Object> map = new HashMap<String, Object>();
		Object bean = null;
		String key = names[0];
		try {
			if (names.length == 1) {
				Class parentClass = parentNode.getClass();
				PropertyDescriptor descriptor = new PropertyDescriptor(key, parentClass);
				Class subClass = descriptor.getPropertyType();
				if (subClass.isEnum()) {
					return Enum.valueOf(subClass, value.toString());
				}
				return value;
			} else {
				String[] subStr = new String[names.length - 1];
				System.arraycopy(names, 1, subStr, 0, subStr.length);

				PropertyDescriptor descriptor = new PropertyDescriptor(key, parentNode.getClass());
				Class subClass = descriptor.getPropertyType();
				bean = descriptor.getReadMethod().invoke(parentNode);
				if (null == bean) {
					bean = subClass.newInstance();
				}
				key = subStr[0];
				map.put(key, createBean(subStr, value, bean));
				BeanUtilsBean beanUtils = createBeanUtils();
				beanUtils.populate(bean, map);
				descriptor.getWriteMethod().invoke(parentNode, bean);
			}
		} catch (Exception e) {
			logger.error("出错了！" + key + "这个属性：" + e);
			throw e;
		}
		return bean;
	}

	private static String underlineToCamel(String str) {
		String pattern[] = str.split("_");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < pattern.length; i++) {
			if (i == 0) {
				builder.append(pattern[i]);
			} else {
				builder.append(pattern[i].substring(0, 1).toUpperCase());
				builder.append(pattern[i].substring(1));
			}
		}
		return builder.toString();
	}

	private static BeanUtilsBean createBeanUtils() {
		ConvertUtilsBean convertUtilsBean = createConverUtils();
		return new BeanUtilsBean(convertUtilsBean);
	}

	private static ConvertUtilsBean createConverUtils() {
		DateConverter dateConverter = new DateConverter();
		dateConverter.setPattern("yyyy-MM-dd HH:mm:ss");
		ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean();
		convertUtilsBean.register(dateConverter, java.util.Date.class);
		convertUtilsBean.register(dateConverter, Timestamp.class);
		convertUtilsBean.register(dateConverter, java.sql.Date.class);
		return convertUtilsBean;
	}
}