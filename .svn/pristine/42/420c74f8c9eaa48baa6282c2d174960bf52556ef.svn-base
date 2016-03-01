package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.PageInfo;
import com.tianque.dao.SmsSendBoxDao;
import com.tianque.domain.Contacter;
import com.tianque.domain.MyContacter;
import com.tianque.domain.MyGroup;
import com.tianque.domain.SmsSendBox;
import com.tianque.domain.WorkContacter;
import com.tianque.domain.vo.ContacterVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.SmsSendBoxService;
import com.tianque.userAuth.api.ContacterDubboService;

@Transactional
@Service("smsSendBoxService")
public class SmsSendBoxServiceImpl extends AbstractBaseService implements
		SmsSendBoxService {

	@Autowired
	private SmsSendBoxDao smsSendBoxDao;
	@Autowired
	private ContacterDubboService contacterDubboService;

	@Override
	public SmsSendBox getSmsSendBoxById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		return smsSendBoxDao.getSmsSendBoxById(id);
	}

	@Override
	public SmsSendBox addSmsSendBox(SmsSendBox smsSendBox,
			List<Contacter> contacters) {
		if (!validate(smsSendBox)) {
			throw new BusinessValidationException("数据输入错误");
		}
		smsSendBox = smsSendBoxDao.addSmsSendBox(smsSendBox);
		saveSmsSendTargets(smsSendBox.getId(), contacters);
		return smsSendBox;
	}

	@Override
	public boolean deleteSmsSendBoxById(Long id) {
		if (id == null) {
			throw new BusinessValidationException("id没有获得");
		}
		if (smsSendBoxDao.deleteSmsSendTargets(id) >= 0
				&& smsSendBoxDao.deleteSmsSendBoxById(id) >= 0) {
			return true;
		}
		return false;
	}

	@Override
	public PageInfo<SmsSendBox> findSmsSendBoxByOwnerId(Long ownerId,
			Integer year, Integer month, int pageNum, int pageSize,
			String sortField, String order) {
		if (ownerId == null) {
			return constructEmptyPageInfo();
		} else {
			// 判断是否是查询当前年月份的数据
			if (CalendarUtil.compareYearAndMonth(year, month)) {
				return smsSendBoxDao.findSmsSendBoxByOwnerId(ownerId, pageNum,
						pageSize, sortField, order);
			} else {
				String date = year.toString()
						+ (month.toString().length() == 2 ? month.toString()
								: ("0" + month.toString()));
				return smsSendBoxDao.findSmsSendBoxByOwnerId(ownerId, date,
						pageNum, pageSize, sortField, order);
			}

		}
	}

	private boolean validate(SmsSendBox smsSendBox) {
		if (!StringUtil.isStringAvaliable(smsSendBox.getReceiver())
				|| smsSendBox.getSmsContent() == null
				|| smsSendBox.getSmsContent().trim().length() < 1) {
			return false;
		}
		return true;
	}

	private void saveSmsSendTargets(Long smsId, List<Contacter> contacters) {
		Set<Contacter> distinctContacters = distinctContacter(contacters);
		if (distinctContacters != null) {
			for (Contacter contacter : distinctContacters) {
				smsSendBoxDao.addSmsSendTargets(smsId, contacter);
			}
		}
	}

	private Set<Contacter> distinctContacter(List<Contacter> contacters) {
		Set<Contacter> distinctContacters = new HashSet<Contacter>();
		if (contacters != null) {
			for (Contacter contacter : contacters) {
				if (contacter.getBelongClass().equals(
						WorkContacter.WORKCONTACTER)
						|| contacter.getBelongClass().equals(
								MyContacter.MYCONTACTER)) {
					distinctContacters.add(contacter);
				} else if (contacter.getBelongClass().equals(MyGroup.MYGROUP)) {
					List<ContacterVo> myGroupContacters = contacterDubboService
							.findMyGroupHasContactersByGroupId(contacter
									.getId());
					for (ContacterVo myGroupContacter : myGroupContacters) {
						distinctContacters.add(myGroupContacter);
					}
				}
			}
		}
		return distinctContacters;
	}

	private PageInfo<SmsSendBox> constructEmptyPageInfo() {
		PageInfo<SmsSendBox> result = new PageInfo<SmsSendBox>();
		result.setResult(new ArrayList<SmsSendBox>());
		return result;
	}

}
