package com.tianque.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.tianque.jms.OperateMode;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SolrServerIndex
{

	String value();// 类型
	
	OperateMode mode(); //模式

}
