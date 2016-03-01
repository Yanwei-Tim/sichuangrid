package com.tianque.baseInfo.permanentAddress.dao;

import java.util.List;
import java.util.Map;

import com.tianque.baseInfo.permanentAddress.domain.PermanentAddress;
import com.tianque.baseInfo.permanentAddress.domain.PermanentAddressLog;
import com.tianque.core.vo.PageInfo;

public interface PermanentAddressDao {

	public PermanentAddress getPermanentAddressByNo(
			PermanentAddress permanentAddress);

	public PermanentAddress getPermanentAddressByAddressNo(String addressNo);

	public PermanentAddress addPermanentAddress(
			PermanentAddress permanentAddress, PermanentAddressLog palog);

	public PageInfo<PermanentAddress> getPermanentAddressByLevel(String level,
			Integer page, Integer rows, String sidx, String sord);

	public List<PermanentAddress> getPermanentAddressByParentid(String parentid);

	public List<PermanentAddress> getPermanentAddressByParentName(
			String parentName);

	public List<PermanentAddress> getPermanentAddressByParentNameAndPId(
			String provinceName, String parentName);

	public PageInfo<PermanentAddress> findPermanentAddress(Integer pageNum,
			Integer pageSize, String sortField, String order);

	public boolean deletePermanentAddress(String addressNo,
			PermanentAddressLog palog);

	public List<PermanentAddress> getPermanentAddressByLevel(String level);

	public PageInfo<PermanentAddress> searchPermanentAddress(String str,
			Integer page, Integer rows, String sidx, String sord);

	public PermanentAddress updatePermanentAddress(
			PermanentAddress permanentAddress, PermanentAddressLog palog);

	public List<PermanentAddress> getPermanentAddressByAddressNameAndPIdAndAddressNo(
			PermanentAddress permanentAddress);

	public String getPermanentAddressByParentNames(Map allParentAddress);

	public List<String> getPermanentAddressByParentNameAndAddressLevel(
			String parentName, String addressLevel);

	public List<PermanentAddress> getPermanentAddressByAddressNameAndAddressLevel(
			String addressName, String addressLevel);

	public List<String> getPermanentAddressByParentNameAndAddressNameAndAddressLevel(
			String addressName, String addressLevel, String parentName);

}
