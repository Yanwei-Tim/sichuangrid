package com.tianque.domain.vo;

import java.io.Serializable;

/**
 * 【说明】：
 * 【编码】：张能军
 * 【公司】：Copyright (c) 2010 by 杭州天阙科技有限公司
 * 【日期】：2010-12-29
 * 【创建时间】：上午11:29:24
 * 【工程】：zhoushangrid
 * 【目录】：com.tianque.domain.vo
 */
@SuppressWarnings("serial")
public class SearchInhabitantAutocompleteVo implements Serializable {

	/**
	 * 户口号
	 */
	private String yhh;
	/**
	 * 身份证号
	 */
	private String idCardNo;
	/**
	 * 户主姓名
	 */
	private String headName;
	/**
	 * 户籍地址
	 */
	private String houseAddress;
	/**
	 * 家庭电话
	 */
	private String telephone;
	/**
	 * 户主身份证号
	 */
	private String headIdCardNo;
	/**
	 * 姓名
	 */
	private String name;
	/**
	 * id
	 */
	private String id;
	/**
	 * 性别
	 */
	private Long gender;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getHeadIdCardNo() {
		return headIdCardNo;
	}

	public void setHeadIdCardNo(String headIdCardNo) {
		this.headIdCardNo = headIdCardNo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getHeadName() {
		return headName;
	}

	public void setHeadName(String headName) {
		this.headName = headName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public String getYhh() {
		return yhh;
	}

	public void setYhh(String yhh) {
		this.yhh = yhh;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

}
