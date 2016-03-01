package com.tianque.util;

import static org.unitils.util.AnnotationUtils.getMethodOrClassLevelAnnotation;

import java.lang.reflect.Method;

import org.unitils.dbunit.annotation.ExpectedDataSet;
import org.unitils.dbunit.datasetfactory.DataSetFactory;
import org.unitils.dbunit.util.MultiSchemaDataSet;

/**
 * @描述:
 * @版权:
 * @公司:
 * @作者:王熙斌
 * @创建时间：2012-12-21 下午12:44:27
 **/
public class DbUnitModule extends org.unitils.dbunit.DbUnitModule {

	@Override
	public MultiSchemaDataSet getExpectedDataSet(Method testMethod, Object testObject) {
		Class<?> testClass = testObject.getClass();
		ExpectedDataSet expectedDataSetAnnotation = getMethodOrClassLevelAnnotation(
				ExpectedDataSet.class, testMethod, testClass);
		if (expectedDataSetAnnotation == null) {
			// No @ExpectedDataSet annotation found
			return null;
		}

		// Create configured factory for data sets
		DataSetFactory dataSetFactory = getDataSetFactory(ExpectedDataSet.class, testMethod,
				testClass);

		// Get the dataset file name
		String[] dataSetFileNames = expectedDataSetAnnotation.value();
		if (dataSetFileNames.length == 0) {
			// empty means use default file name
			dataSetFileNames = new String[] { getDefaultExpectedDataSetFileName(testMethod,
					testClass, dataSetFactory.getDataSetFileExtension()) };
		}

		return getDataSet(testClass, dataSetFileNames, dataSetFactory);
	}
}
