package com.tianque.plugin.dataManage.util;

import org.apache.log4j.Logger;

import com.tianque.core.util.ConvertXmlAndBean;
import com.tianque.core.util.FileUtil;

public class DataManageBaseInfoReader {
	Logger logger = Logger.getLogger(DataManageBaseInfoReader.class);

	/**
	 * 通过xml返回定义的对象集合
	 * 
	 * @param xmlPath
	 * @return
	 */
	public DataManageBaseInfoList getDataManageBaseInfo(String xmlPath) {
		try {
			ConvertXmlAndBean xmlToBean = new ConvertXmlAndBean();
			DataManageBaseInfoList dataManageBaseInfo = (DataManageBaseInfoList) xmlToBean
					.xmlToBean(xmlPath, DataManageBaseInfoList.class,
							getMapInputDataManageBaseInfoPath());
			return dataManageBaseInfo;
		} catch (Exception e) {
			logger.error("获取BaseInfotList出错", e);
			return null;
		}
	}

	/**
	 * 对象xml需要一个mapping的xml进行解析
	 * 
	 * @return
	 */
	protected String getMapInputDataManageBaseInfoPath() {
		return FileUtil.getWebRoot() + "/WEB-INF/classes/config/dataManageBaseInfoMapInfo.xml";
	}

}
