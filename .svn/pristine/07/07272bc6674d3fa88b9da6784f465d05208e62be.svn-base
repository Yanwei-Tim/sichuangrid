package com.tianque.xichang.working.poorPeople.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.core.vo.PageInfo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.xichang.working.poorPeople.dao.PoorPeopleMembersDao;
import com.tianque.xichang.working.poorPeople.domain.PoorPeopleMembers;
import com.tianque.xichang.working.poorPeople.service.PoorPeopleMembersService;

/**
 * @ClassName: PoorPeopleMembersServiceImpl
 * @Description: 三本台账-困难群众台账-维护家庭成员服务接口实现类
 * @author wangxiaohu wsmalltiger@163.com
 * @date Dec 26, 2013 4:05:28 PM
 */
@Transactional
@Service("poorPeopleMembersService")
public class PoorPeopleMembersServiceImpl implements PoorPeopleMembersService {

	private static Logger logger = LoggerFactory
			.getLogger(PoorPeopleMembersServiceImpl.class);

	@Autowired
	private PoorPeopleMembersDao poorPeopleMembersDao;
	@Autowired
	@Qualifier("poorPeopleMembersValidator")
	private DomainValidator<PoorPeopleMembers> validator;

	private void validate(PoorPeopleMembers poorPeopleMembers) {
		ValidateResult dataValidator = validator.validate(poorPeopleMembers);
		if (dataValidator.hasError()) {
			throw new BusinessValidationException(dataValidator.getErrorMessages());
		}
	}

	@Override
	public PoorPeopleMembers addPoorPeopleMembers(
			PoorPeopleMembers poorPeopleMembers) {
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validate(poorPeopleMembers);
			}
			return poorPeopleMembersDao.addPoorPeopleMembers(poorPeopleMembers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("困难群众台账-家庭成员新增信息出现错误", e.getMessage());
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("困难群众台账-家庭成员新增信息出现错误");
			} else {
				return null;
			}
		}
	}

	@Override
	public void deletePoorPeopleMembers(String ids) {
		try {
			if (ids != null && !"".equals(ids)) {
				String idArry[] = ids.split(",");
				poorPeopleMembersDao.deletePoorPeopleMembersByIds(idArry);
				// for (String id : idArry) {
				// if (id != null && !"".equals(id)) {
				// poorPeopleMembersDao.deletePoorPeopleMembers(id);
				// }
				// }
			}
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众台账-家庭成员删除信息出现错误", "", e);
		}
	}

	@Override
	public PageInfo findPoorPeopleMembersForList(
			PoorPeopleMembers poorPeopleMembers) {
		try {
			return poorPeopleMembersDao
					.findPoorPeopleMembersForList(poorPeopleMembers);
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众台账-家庭成员获取列表失败", "", e);
		}
	}

	@Override
	public PoorPeopleMembers getPoorPeopleMembersById(
			PoorPeopleMembers poorPeopleMembers) {
		validateId(poorPeopleMembers);
		try {
			return poorPeopleMembersDao
					.getPoorPeopleMembersById(poorPeopleMembers.getId());
		} catch (Exception e) {
			throw new ServiceValidationException("困难群众台账-家庭成员获取详情出现错误", "", e);
		}
	}

	@Override
	public PoorPeopleMembers updatePoorPeopleMembers(
			PoorPeopleMembers poorPeopleMembers) {
		validateId(poorPeopleMembers);
		try {
			if (!ExcelImportHelper.isImport.get()) {
				validate(poorPeopleMembers);
			}
			return poorPeopleMembersDao
					.updatePoorPeopleMembers(poorPeopleMembers);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("困难群众台账-家庭成员修改信息出现错误", e.getMessage());
			if (!ExcelImportHelper.isImport.get()) {
				throw new BusinessValidationException("困难群众台账-家庭成员修改信息出现错误");
			} else {
				return null;
			}
		}
	}

	private void validateId(PoorPeopleMembers poorPeopleMembers) {
		if (poorPeopleMembers == null || poorPeopleMembers.getId() == null
				|| poorPeopleMembers.getId().longValue() == 0L) {
			throw new BusinessValidationException("参数错误");
		}
	}

}
