package com.tianque.baseInfo.permanentAddress.service;

import java.util.List;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.vo.PageInfo;

public interface PermanentAddressService {

	public PermanentAddress getPermanentAddressByNo(
			PermanentAddress PermanentAddress);

	public PermanentAddress getPermanentAddressByAddressNo(String addressNo);

	public PermanentAddress addPermanentAddress(
			PermanentAddress PermanentAddress);

	public PageInfo<PermanentAddress> getPermanentAddressByLevel(String level,
			Integer page, Integer rows, String sidx, String sord);

	public List<PermanentAddress> getPermanentAddressByLevel(String level);

	public List<PermanentAddress> getPermanentAddressByParentid(String parentid);

	public List<PermanentAddress> getPermanentAddressByParentName(
			String parentName);

	public List<PermanentAddress> getPermanentAddressByParentNameAndPId(
			String parentName);

	public PageInfo<PermanentAddress> findPermanentAddress(Integer page,
			Integer rows, String sidx, String sord);

	public boolean deletePermanentAddress(String addressNo);

	public PermanentAddress updatePermanentAddress(
			PermanentAddress permanentAddress);

	public PageInfo<PermanentAddress> searchPermanentAddress(String str,
			Integer page, Integer rows, String sidx, String sord);

	public String getParentAddressNoByParentName(String addressLevel,
			String selectStr);

	public List<String> getPermanentAddressByParentNameAndAddressLevel(
			String parentName, String addressLevel);

	public List<PermanentAddress> getPermanentAddressByAddressNameAndAddressLevel(
			String addressName, String addressLevel);

	public List<String> getPermanentAddressByParentNameAndAddressNameAndAddressLevel(
			String addressName, String addressLevel, String parentName);

	/**
	 * 添加户籍地清洗日志
	 * */
	public PermanentAddressLog addPermanentAddressClean(
			PermanentAddress permanentAddress);

}
