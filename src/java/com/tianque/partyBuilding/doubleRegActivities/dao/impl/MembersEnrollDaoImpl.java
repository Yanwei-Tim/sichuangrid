package com.tianque.partyBuilding.doubleRegActivities.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.vo.PageInfo;
import com.tianque.core.vo.Pager;
import com.tianque.dao.impl.BaseDaoImpl;
import com.tianque.partyBuilding.doubleRegActivities.dao.MembersEnrollDao;
import com.tianque.partyBuilding.doubleRegActivities.domain.MembersEnroll;

@Repository("membersEnrollDao")
public class MembersEnrollDaoImpl extends BaseDaoImpl<MembersEnroll> implements
		MembersEnrollDao {
	@Override
	public MembersEnroll addMembersEnroll(MembersEnroll membersEnroll) {
		if (membersEnroll != null) {
			membersEnroll = add(membersEnroll);
		}
		return membersEnroll;
	}

	@Override
	public MembersEnroll updateMembersEnroll(MembersEnroll membersEnroll) {
		if (membersEnroll != null) {
			membersEnroll = update(membersEnroll, "updateMembersEnroll");
		}
		return membersEnroll;
	}

	@Override
	public MembersEnroll getMembersEnrollById(Long id) {
		return get(id);
	}

	@Override
	public MembersEnroll getMembersEnrollByIdCardNo(String idCardNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idcardno", idCardNo);
		map.put("orgId",ThreadVariable.getOrganization().getId());
		return get(map, "getMembersEnrollByIdCardNo");
	}

	@Override
	public MembersEnroll getMembersEnroll(MembersEnroll membersEnroll) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", membersEnroll.getId());
		map.put("regActivitiesType", membersEnroll.getRegActivitiesType());
		return get(map, "getMembersEnroll");
	}

	@Override
	public PageInfo<MembersEnroll> findMembersEnrollForPage(
			MembersEnroll membersEnroll, Pager pager) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", membersEnroll.getOrganization().getId());
		map.put("orgInternalCode", membersEnroll.getOrgInternalCode());
		map.put("regActivitiesType", membersEnroll.getRegActivitiesType());
		map.put("idcardno", membersEnroll.getMember() != null ? membersEnroll
				.getMember().getIdCardNo() : null);
		map.put("name", membersEnroll.getMember() != null ? membersEnroll
				.getMember().getName() : null);
		map.put("logoutStatus", membersEnroll.getLogoutStatus() == -1L ? null
				: membersEnroll.getLogoutStatus());
		return findPagerBySearchVo(map, pager, "MembersEnrollForPage");
	}

	@Override
	public boolean deleteMembersEnroll(List<Long> ids, String regActivitiesType) {
		boolean bool = false;
		if (ids != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids);
			map.put("regActivitiesType", regActivitiesType);
			bool = delete(map, "deleteMembersEnroll");
		}
		return bool;
	}

	@Override
	public MembersEnroll getMembersEnrollByMemberID(Long memberId,
			String regActivitiesType) {
		MembersEnroll membersEnroll = null;
		if (memberId != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", memberId);
			map.put("regActivitiesType", regActivitiesType);
			return get(map, "getMembersEnrollByMemberID");
		}
		return membersEnroll;
	}

	@Override
	public List<MembersEnroll> getMembersEnrollByMemberIDs(
			List<Long> memberIds, String regActivitiesType) {
		List<MembersEnroll> membersEnrollList = null;
		if (memberIds != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("memberIdList", memberIds);
			if (StringUtil.isStringAvaliable(regActivitiesType)) {
				map.put("regActivitiesType", regActivitiesType);
			}
			membersEnrollList = (List<MembersEnroll>) getSqlMapClientTemplate()
					.queryForList("membersEnroll.getMembersEnrollByMemberIDs",
							map);
		}
		return membersEnrollList;
	}

	@Override
	public MembersEnroll logoutMemberEnroll(Long id, Long logoutStatus,
			Long isEnroll) {
		MembersEnroll entity = get(id);
		entity.setLogoutStatus(logoutStatus);
		entity.setIsEnroll(isEnroll != null ? isEnroll : null);
		return update(entity);
	}

}
