package com.tianque.sms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.User;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sms.dao.SmsUplinkDao;
import com.tianque.sms.domain.SmsSendObject;
import com.tianque.sms.domain.SmsUplink;
import com.tianque.sms.domain.Smsquerycondition;
import com.tianque.sms.domain.vo.SearchSmsUplinkVo;
import com.tianque.sms.domain.vo.SmsJobSqlVo;
import com.tianque.sms.domain.vo.SmsSendObjectVo;
import com.tianque.sms.service.SmsSendObjectService;
import com.tianque.sms.service.SmsUplinkService;
import com.tianque.sms.service.SmsqueryconditionService;
import com.tianque.sms.util.SmsGlobalValue;
import com.tianque.sms.util.SmsSendStatus;

/**
 * 短信发件箱:业务逻辑层
 * 
 * @author
 * @date 2013-07-02 09:53:32
 */
@Repository("smsUplinkService")
@Transactional
public class SmsUplinkServiceImpl extends
		BaseServiceImpl<SmsUplink, SearchSmsUplinkVo> implements
		SmsUplinkService {

	private static Logger logger = LoggerFactory
			.getLogger(SmsUplinkServiceImpl.class);

	@Autowired
	@Qualifier("smsUplinkValidator")
	private DomainValidator<SmsUplink> domainValidator;
	@Autowired
	private SmsSendObjectService smsSendObjectService;
	@Autowired
	private SmsqueryconditionService smsqueryconditionService;
	@Autowired
	private SmsUplinkDao smsUplinkDao;

	@Resource(name = "smsUplinkDao")
	public void setBaseDao(SmsUplinkDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	public SmsUplink add(SmsUplink smsUplink) {
		smsUplinkValidator(smsUplink);
		try {
			smsUplink
					.setMobileType(getMobileType(smsUplink.getReceiverMobile()));
			smsUplink = getBaseDao().add(smsUplink);
			return smsUplink;
		} catch (Exception e) {
			throw new ServiceValidationException("新增短信发件箱信息出现错误", e);

		}
	}

	@Override
	public SmsUplink update(SmsUplink smsUplink) {
		smsUplinkValidator(smsUplink);
		try {
			smsUplink = getBaseDao().update(smsUplink);
			return smsUplink;
		} catch (Exception e) {
			throw new ServiceValidationException("更新短信发件箱信息出现错误", e);
		}
	}

	@Override
	public boolean addSendSMSInfo(Map<String, String> map) {
		try {
			validateMap(map);
			if (map.get("sendType").equals(
					SmsGlobalValue.MOBILE_NUMBER.toString())
					|| map.get("sendType").equals(
							SmsGlobalValue.CONTACTS.toString())) {
				return addSmsUplinkByMobileNumber(map);
			} else {
				return addSmsUplinkBySendObject(map);
			}
		} catch (Exception e) {
			throw new ServiceValidationException("新增发送短信信息时出现错误", e);
		}
	}

	@Override
	public int countSmsSendObjectNumber(Map<String, String> map) {
		if (null == map || null == map.get("sendType")) {
			throw new BusinessValidationException("参数无效！");
		}
		if (map.get("sendType").equals(SmsGlobalValue.MOBILE_NUMBER.toString())
				|| map.get("sendType").equals(
						SmsGlobalValue.CONTACTS.toString())) {
			return map.get("smsSendId").split(",").length;
		}
		List<Smsquerycondition> queryList = smsqueryconditionService
				.findSmsqueryconditionsBySmsSendObjectId(Long.valueOf(map
						.get("smsSendId")));
		if (null == queryList || queryList.size() <= 0) {
			throw new BusinessValidationException("参数无效！");
		}

		return smsUplinkDao.countSmsSendObjectNumber(getSql(queryList, map));
	}

	@Override
	public PageInfo<SmsSendObjectVo> findSmsSendPeopleInfoPager(
			Map<String, String> map, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		if (null == map || null == map.get("smsSendId")) {
			throw new BusinessValidationException("参数无效！");
		}
		List<Smsquerycondition> queryList = smsqueryconditionService
				.findSmsqueryconditionsBySmsSendObjectId(Long.valueOf(map
						.get("smsSendId")));
		return smsUplinkDao.findSmsSendPeopleInfoPager(getSql(queryList, map),
				pageNum, pageSize, sortField, order);
	}

	@Override
	public SmsJobSqlVo addSmsJobSqlVo(SmsJobSqlVo smsJobSqlVo) {
		if (null == smsJobSqlVo) {
			throw new BusinessValidationException("参数无效！");
		}
		return smsUplinkDao.addSmsJobSqlVo(smsJobSqlVo);
	}

	@Override
	public SmsJobSqlVo getSmsJobSqlVoById(Long id) {
		if (null == id) {
			throw new BusinessValidationException("参数无效！");
		}
		return smsUplinkDao.getSmsJobSqlVoById(id);
	}

	@Override
	public List<SmsJobSqlVo> findSmsJobSqlVoBySmsLevel() {
		return smsUplinkDao.findSmsJobSqlVoBySmsLevel();
	}

	@Override
	public SmsJobSqlVo updateSmsJobSqlVo(SmsJobSqlVo smsJobSqlVo) {
		if (null == smsJobSqlVo || null == smsJobSqlVo.getId()) {
			throw new BusinessValidationException("参数无效！");
		}
		return smsUplinkDao.updateSmsJobSqlVo(smsJobSqlVo);
	}

	@Override
	public PageInfo<SmsUplink> findSubSmsUplinksBySearchVo(
			SearchSmsUplinkVo searchVo, Integer pageNum, Integer pageSize,
			String sortField, String order) {
		return smsUplinkDao.findSubSmsUplinksBySearchVo(searchVo, pageNum,
				pageSize, sortField, order);
	}

	@Override
	public void addBatchSmsUplink(List<SmsUplink> smsUplinkList) {
		if (null == smsUplinkList || smsUplinkList.size() <= 0) {
			throw new BusinessValidationException("参数无效！");
		}
		smsUplinkDao.addSmsUplinkByBatch(smsUplinkList);
	}

	@Override
	public SmsUplink getSmsUplinkByMinIdAndProcessed() {
		return smsUplinkDao.getSmsUplinkByMinIdAndProcessed();
	}

	@Override
	public void updateDeleteStatusSmsUplinkByReceiverMobile(
			String receiverMobile) {
		if (receiverMobile == null || receiverMobile.trim().equals("")) {
			logger.error("号码不能为空！");
			throw new BusinessValidationException("号码不能为空！");
		}
		smsUplinkDao
				.updateDeleteStatusSmsUplinkByReceiverMobile(receiverMobile);
	}

	@Override
	public boolean updateDeleteStatusByIds(Long[] ids) {
		if (null == ids || ids.length <= 0) {
			throw new BusinessValidationException("参数无效！");
		}
		StringBuffer sb = new StringBuffer();
		boolean bol = false;
		for (int i = 0; i < ids.length; i++) {
			if (!validateDelateStatus(ids[i])) {
				continue;
			}
			sb.append(",").append(ids[i]);
			if (i != 0 && i % 500 == 0) {
				bol = smsUplinkDao.updateDeleteStatusByIds(sb.toString()
						.substring(1));
				sb = new StringBuffer();
			}
		}
		if (sb.length() > 0) {
			bol = smsUplinkDao.updateDeleteStatusByIds(sb.toString().substring(
					1));
		}
		return bol;
	}

	private boolean validateDelateStatus(Long id) {
		if (null == id) {
			return false;
		}
		SmsUplink sms = smsUplinkDao.get(id);
		if (null == sms) {
			return false;
		}
		if (sms.getStatus().equals(SmsSendStatus.TO_SEND)
				|| sms.getStatus().equals(SmsSendStatus.PROCESSING)) {
			throw new BusinessValidationException("待发送或处理中的短信不能被删除!");
		}
		return true;
	}

	private boolean addSmsUplinkByMobileNumber(Map<String, String> map) {
		String mobiles = map.get("smsSendId");
		SmsUplink smsUp = add(createSmsUplink(mobiles.split(",").length,
				map.get("smsSendContent")));
		addBatchSmsUplink(createSmsUplinkList(map, smsUp.getId()));
		return true;
	}

	private List<SmsUplink> createSmsUplinkList(Map<String, String> map,
			Long parentId) {
		List<SmsUplink> smsUplinkList = new ArrayList<SmsUplink>();
		User user = ThreadVariable.getUser();
		for (String mobile : getMobiles(map)) {
			SmsUplink smsUplink = new SmsUplink();
			smsUplink.setParentId(parentId);
			smsUplink.setReceiverMobile(mobile);
			smsUplink.setMobileType(getMobileType(mobile));
			smsUplink.setSender(user);
			smsUplink.setSenderName(user.getName());
			smsUplink.setSenderOrgId(user.getOrganization().getId());
			smsUplink.setSenderOrgInternalCode(user.getOrgInternalCode());
			smsUplink.setSmsLevel(Long.valueOf(map.get("smsLevel")));
			smsUplink.setContent(map.get("smsSendContent"));
			smsUplink.setSendTime(new Date());
			smsUplinkList.add(smsUplink);
		}
		return smsUplinkList;
	}

	private String[] getMobiles(Map<String, String> map) {
		if (map.get("sendType").equals(SmsGlobalValue.MOBILE_NUMBER.toString())) {
			return map.get("smsSendId").split(",");
		} else if (map.get("sendType").equals(
				SmsGlobalValue.CONTACTS.toString())) {
			Pattern p = Pattern.compile("\\((.*?)\\)");
			Matcher m = p.matcher(map.get("smsSendId"));
			int i = 0;
			String[] arr = new String[map.get("smsSendId").split(",").length];
			while (m.find()) {
				arr[i++] = m.group(1);
			}
			return arr;
		}
		return new String[0];
	}

	private boolean addSmsUplinkBySendObject(Map<String, String> map) {
		SmsSendObject sso = smsSendObjectService.get(Long.valueOf(map
				.get("smsSendId")));
		List<Smsquerycondition> queryList = smsqueryconditionService
				.findSmsqueryconditionsBySmsSendObjectId(Long.valueOf(map
						.get("smsSendId")));
		String sql = getSql(queryList, map);
		int counts = smsUplinkDao.countSmsSendObjectNumber(sql);
		if (counts <= 0) {
			return true;
		}
		SmsUplink smsUp = add(createSmsUplink(counts, map.get("smsSendContent")));
		return null != addSmsJobSqlVo(createSmsJobSqlVo(sql, sso.getName(),
				map, smsUp));
	}

	private SmsUplink createSmsUplink(Integer counts, String smsSendContent) {
		User user = ThreadVariable.getUser();
		SmsUplink smsUplink = new SmsUplink();
		smsUplink.setSender(user);
		smsUplink.setSenderName(user.getName());
		smsUplink.setSenderOrgId(user.getOrganization().getId());
		smsUplink.setSenderOrgInternalCode(user.getOrgInternalCode());
		smsUplink.setContent(smsSendContent);
		smsUplink.setSendTime(new Date());
		smsUplink.setCounts(counts);
		return smsUplink;
	}

	private SmsJobSqlVo createSmsJobSqlVo(String sql, String description,
			Map<String, String> map, SmsUplink smsUp) {
		SmsJobSqlVo smsJobSqlVo = new SmsJobSqlVo();
		smsJobSqlVo.setSmsuplinkId(smsUp.getId());
		smsJobSqlVo.setDescription(description);
		smsJobSqlVo.setSql(sql);
		smsJobSqlVo.setSmsLevel(Long.valueOf(map.get("smsLevel")));
		smsJobSqlVo.setContent(map.get("smsSendContent"));
		smsJobSqlVo.setSender(ThreadVariable.getUser());
		smsJobSqlVo.setSendTime(new Date());
		return smsJobSqlVo;
	}

	private String getSql(List<Smsquerycondition> queryList,
			Map<String, String> map) {
		Map<String, Smsquerycondition> queryMap = new HashMap<String, Smsquerycondition>();
		for (Smsquerycondition query : queryList) {
			queryMap.put(query.getKey(), query);
		}

		StringBuffer sql = replaceString(queryMap, map);

		for (Entry<String, Smsquerycondition> entry : queryMap.entrySet()) {
			if (null != entry.getValue().getIsNull()
					&& entry.getValue().getIsNull().booleanValue()) {
				sql.append(" ").append(entry.getValue().getSqlTemplate());
			}
		}
		return sql.append(" and orgInternalCode like '")
				.append(ThreadVariable.getUser().getOrgInternalCode())
				.append("%' ").toString();
	}

	private StringBuffer replaceString(Map<String, Smsquerycondition> queryMap,
			Map<String, String> map) {
		Smsquerycondition table = queryMap.get("table");
		queryMap.remove("table");
		map.remove("smsSendId");
		StringBuffer sql = new StringBuffer(table.getSqlTemplate());
		for (Entry<String, String> entry : map.entrySet()) {
			Smsquerycondition query = queryMap.get(entry.getKey());
			if (null == query) {
				continue;
			}
			if (StringUtil.isStringAvaliable(entry.getValue())) {
				sql.append(" ").append(
						query.getSqlTemplate().replace(
								"${" + entry.getKey() + "}", entry.getValue()));
				queryMap.remove(entry.getKey());
			} else if (null != query && null != query.getIsNull()
					&& query.getIsNull().booleanValue()) {
				sql.append(" ").append(query.getSqlTemplate());
				queryMap.remove(entry.getKey());
			}
		}
		return sql;
	}

	private int getMobileType(String mobile) {
		int b = 0;
		int type = 0;
		try {
			b = Integer.parseInt(mobile.substring(0, 3));
		} catch (Exception e) {
			b = 0;
		}
		switch (b) {
		case 134:
		case 135:
		case 136:
		case 137:
		case 138:
		case 139:
		case 147:
		case 150:
		case 151:
		case 152:
		case 157:
		case 158:
		case 159:
		case 182:
		case 187:
		case 188:
			type = 1;
			break;
		case 133:
		case 153:
		case 180:
		case 189:
			type = 4;
			break;
		case 130:
		case 131:
		case 132:
		case 155:
		case 156:
		case 185:
		case 186:
			type = 2;
			break;
		default:
			type = 0;
			break;

		}
		return type;
	}

	private void validateMap(Map<String, String> map) {
		if (null == map || !StringUtil.isStringAvaliable(map.get("sendType"))) {
			throw new BusinessValidationException("参数无效！");
		}
		if (!StringUtil.isStringAvaliable(map.get("smsSendId"))) {
			throw new BusinessValidationException("收信人不能为空！");
		}
		if (!StringUtil.isStringAvaliable(map.get("smsLevel"))) {
			throw new BusinessValidationException("优先级不能为空！");
		}
		if (!StringUtil.isStringAvaliable(map.get("smsSendContent"))) {
			throw new BusinessValidationException("发送内容不能为空！");
		}
	}

	private void smsUplinkValidator(SmsUplink smsUplink) {
		ValidateResult baseDataValidator = domainValidator.validate(smsUplink);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	@Override
	public List<Map<String, Object>> findDifferPhoneTypeMessagesNumberByOrgIds(
			List<Long> orgIds) {
		if (null == orgIds || orgIds.size() <= 0) {
			throw new BusinessValidationException("参数不能为空！");
		}
		String str = "";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < orgIds.size(); i++) {
			if (null == orgIds.get(i)) {
				continue;
			}
			str += "," + orgIds.get(i).toString();
			if (0 == i || i % 500 != 0) {
				continue;
			}
			List<Map<String, Object>> tempList1 = smsUplinkDao
					.findDifferPhoneTypeMessagesNumberByOrgIds(str.substring(1));
			if (null != tempList1 && tempList1.size() > 0) {
				list.addAll(tempList1);
			}
			str = "";
		}
		if ("".equals(str)) {
			return list;
		}
		List<Map<String, Object>> tempList2 = smsUplinkDao
				.findDifferPhoneTypeMessagesNumberByOrgIds(str.substring(1));
		if (null != tempList2 && tempList2.size() > 0) {
			list.addAll(tempList2);
		}
		return list;
	}

	@Override
	public boolean updateRestoreDeleteStatusById(Long id) {
		try {
			return smsUplinkDao.updateRestoreDeleteStatusById(id);
		} catch (Exception e) {
			throw new ServiceValidationException("更新短信删除状态为未删除失败!", e);

		}
	}
}
