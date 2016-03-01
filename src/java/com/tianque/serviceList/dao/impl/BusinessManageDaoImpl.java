/**
 * 
 */
package com.tianque.serviceList.dao.impl;

import org.springframework.stereotype.Repository;

import com.tianque.core.base.BaseDaoImpl;
import com.tianque.serviceList.dao.BusinessManageDao;
import com.tianque.serviceList.dao.DrugsSaftyDao;
import com.tianque.serviceList.dao.FoodSaftyDao;
import com.tianque.serviceList.domain.BusinessManage;
import com.tianque.serviceList.domain.DrugsSafty;
import com.tianque.serviceList.domain.FoodSafty;

/**
 * @作者:彭乐
 * @功能: 
 * @时间:2015-11-27 上午10:55:54
 * @邮箱:pengle@hztianque.com
 */
@Repository("businessManageDaoImpl")
public class BusinessManageDaoImpl extends BaseDaoImpl<BusinessManage, BusinessManage> implements BusinessManageDao{

}
