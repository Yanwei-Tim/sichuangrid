package com.tianque.baseInfo.primaryOrg.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.baseInfo.primaryOrg.dao.RedCuffTeamDao;
import com.tianque.baseInfo.primaryOrg.domain.RedCuffTeam;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.AbstractBaseDao;

@Repository("redCuffTeamDao")
public class RedCuffTeamDaoImpl extends AbstractBaseDao implements RedCuffTeamDao{

	@Override
	public PageInfo<RedCuffTeam> findRedCuffTeamForList(
			RedCuffTeam redCuffTeam, Integer page, Integer rows, String sidx,
			String sord) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sortField", sidx);
		map.put("order", sord);
		map.put("orgCode", redCuffTeam.getOrganization().getOrgInternalCode());
		if(redCuffTeam.getTeamType()!=null && redCuffTeam.getTeamType().getId()!=null){
			map.put("teamType", redCuffTeam.getTeamType().getId());
		}
		if (redCuffTeam.getSubTeamType() != null && redCuffTeam.getSubTeamType().getId() != null) {
			map.put("subTeamType", redCuffTeam.getSubTeamType().getId());
		}
		if (redCuffTeam.getNotNoBindingTime() != null) {
			map.put("notNoBindingTime", redCuffTeam.getNotNoBindingTime());
		}
		if(StringUtil.isStringAvaliable(redCuffTeam.getMemeberName())){
			map.put("memeberName", redCuffTeam.getMemeberName());
		}
		if(StringUtil.isStringAvaliable(redCuffTeam.getIdCardNo())){
			map.put("idCardNo",redCuffTeam.getIdCardNo());
		}
		if(redCuffTeam.getIsCertification()!=null){
			map.put("isCertification",redCuffTeam.getIsCertification());
		}
		if(redCuffTeam.getNation()!=null && redCuffTeam.getNation().getId()!=null){
			map.put("nation",redCuffTeam.getNation());
		}
		if(redCuffTeam.getPolitical()!=null && redCuffTeam.getPolitical().getId()!=null){
			map.put("political",redCuffTeam.getPolitical());
		}
		if(redCuffTeam.getEducation()!=null && redCuffTeam.getEducation().getId()!=null){
			map.put("education",redCuffTeam.getEducation());
		}
		if(redCuffTeam.getGender()!=null && redCuffTeam.getGender().getId()!=null){
			map.put("gender",redCuffTeam.getGender());
		}
		if(redCuffTeam.getBirthDate()!=null){
			map.put("birthDate",redCuffTeam.getBirthDate() );
		}
		if(StringUtil.isStringAvaliable(redCuffTeam.getPhoneNumber())){
			map.put("phoneNumber",redCuffTeam.getPhoneNumber() );
		}
		if(StringUtil.isStringAvaliable(redCuffTeam.getOccupation())){
			map.put("occupation",redCuffTeam.getOccupation() );
		}
		Integer countNum = (Integer) getSqlMapClientTemplate().queryForObject(
				"redCuffTeam.countFindRedCuffTeamForList", map);

		List<RedCuffTeam> list = getSqlMapClientTemplate().queryForList(
				"redCuffTeam.findRedCuffTeamForList", map, (page - 1) * rows, rows);
		PageInfo<RedCuffTeam> pageInfo = new PageInfo<RedCuffTeam>(page, rows,
				countNum, list);
		return pageInfo;
	}

	@Override
	public RedCuffTeam addRedCuffTeam(RedCuffTeam redCuffTeam) {
		Long id = (Long) getSqlMapClientTemplate().insert("redCuffTeam.addRedCuffTeam",redCuffTeam);
		return getRedCuffTeamById(id);
	}

	@Override
	public RedCuffTeam updateRedCuffTeam(RedCuffTeam redCuffTeam) {
		getSqlMapClientTemplate().update("redCuffTeam.updateRedCuffTeam",redCuffTeam);
		return getRedCuffTeamById(redCuffTeam.getId());
	}

	@Override
	public void deleteRedCuffTeamByIds(String[] ids) {
		getSqlMapClientTemplate().delete("redCuffTeam.deleteRedCuffTeamByIds",ids);
	}

	@Override
	public RedCuffTeam getRedCuffTeamById(Long id) {
		return (RedCuffTeam) getSqlMapClientTemplate().queryForObject("redCuffTeam.getRedCuffTeamById",id);
	}

	@Override
	public RedCuffTeam getRedCuffTeamByPhoneAndName(String phoneNumber,
			String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("phoneNumber", phoneNumber);
		map.put("name", name);
		return (RedCuffTeam) getSqlMapClientTemplate().queryForObject("redCuffTeam.getRedCuffTeamByPhoneAndName",map);
	}

	@Override
	public RedCuffTeam getRedCuffTeamByWechatno(String wechatNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("wechatNo", wechatNo);
		return (RedCuffTeam) getSqlMapClientTemplate().queryForObject("redCuffTeam.getRedCuffTeamByWechatno",map);
	}

	@Override
	public Integer weChatBindingRedCuffTeam(Integer isCertification,
			String wechatNo, Date wechatAuthenticationDate, Long id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isCertification", isCertification);
		map.put("wechatNo", wechatNo);
		map.put("wechatAuthenticationDate", wechatAuthenticationDate);
		map.put("id", id);
		return (Integer)getSqlMapClientTemplate().update("redCuffTeam.weChatBindingRedCuffTeam",map);
	}

	@Override
	public void bindingRedCuffTeamCoordinate(String latitudeX,
			String longitudeY, String precision, Date bindingTime,
			String wechatNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("latitudeX", latitudeX);
		map.put("longitudeY", longitudeY);
		map.put("precision", precision);
		map.put("bindingTime", bindingTime);
		map.put("wechatNo", wechatNo);
		getSqlMapClientTemplate().update("redCuffTeam.bindingRedCuffTeamCoordinate",map);
	}

	@Override
	public RedCuffTeam getRedCuffTeamByPhoneNumber(String phoneNumber) {
		return (RedCuffTeam) getSqlMapClientTemplate().queryForObject("redCuffTeam.getRedCuffTeamByPhoneNumber",phoneNumber);
	}

	@Override
	public String[] getWeChatNoByTeamType(String orgCode, String[] teamType, String[] subTeamType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamType", teamType);
		map.put("subTeamType", subTeamType);
		map.put("orgCode", orgCode);
		List<String> list = getSqlMapClientTemplate().queryForList(
				"redCuffTeam.getWeChatNoByTeamType", map);
		return list.toArray(new String[list.size()]);
	}

	@Override
	public List<RedCuffTeam> getRedCuffTeamListByTeamType(String orgCode, String[] teamType,
			String[] subTeamType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamType", teamType);
		map.put("subTeamType", subTeamType);
		map.put("orgCode", orgCode);
		return getSqlMapClientTemplate().queryForList("redCuffTeam.getRedCuffTeamListByTeamType",
				map);
	}

	@Override
	public int countRedCuffTeamListByTeamType(String orgCode, String[] teamType,
			String[] subTeamType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("teamType", teamType);
		map.put("subTeamType", subTeamType);
		map.put("orgCode", orgCode);
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"redCuffTeam.countRedCuffTeamListByTeamType", map);
	}

	@Override
	public List<RedCuffTeam> getRedCuffTeamNameAndPhoneListByOrgCode(String orgCode) {
		return getSqlMapClientTemplate().queryForList("redCuffTeam.getRedCuffTeamNameAndPhoneListByOrgCode",
				orgCode);
	}

}
