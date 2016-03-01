package com.tianque.resourcePool.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseServiceImpl;
import com.tianque.core.util.Chinese2pinyin;
import com.tianque.core.util.ThreadVariable;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.resourcePool.dao.DirectorySettingDao;
import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.vo.SearchDirectorySetting;

@Service("directorySettingService")
@Transactional
public class DirectorySettingServiceImpl extends
		BaseServiceImpl<DirectorySetting, SearchDirectorySetting> implements
		DirectorySettingService {
	DirectorySetting previousDirect = null;
	DirectorySetting directory = null;

	@Resource(name = "directorySettingDao")
	public void setBaseDao(DirectorySettingDao baseDao) {
		super.setBaseDao(baseDao);
	}

	@Autowired
	private DirectorySettingDao directorySettingDao;

	private void autoFillChinesePinyin(DirectorySetting directorySetting) {
		Map<String, String> pinyin = Chinese2pinyin
				.changeChinese2Pinyin(directorySetting.getName());
		directorySetting.setSimplePinyin(pinyin.get("simplePinyin"));
		directorySetting.setFullPinyin(pinyin.get("fullPinyin"));
	}

	@Override
	public List<DirectorySetting> findchildByparentId(Long parentId) {
		return directorySettingDao.findchildByparentId(parentId, ThreadVariable
				.getUser().getId());
	}

	@Override
	public List<DirectorySetting> findDirectorySettingByParentId(Long parentId) {
		return directorySettingDao.findDirectorySettingByParentId(parentId);
	}

	@Override
	public List<DirectorySetting> findDirectorySettingByDirectoryType(
			int directoryType) {
		List<DirectorySetting> list = directorySettingDao
				.findDirectorySettingByDirectoryType(directoryType);
		for (DirectorySetting directorySetting : list) {
			List<DirectorySetting> dire = findchildByparentId(directorySetting
					.getId());
			directorySetting.setCount(dire.size());
		}
		return list;
	}

	@Override
	public DirectorySetting findDirectorySettingById(Long id) {
		return directorySettingDao.findDirectorySettingById(id);
	}

	@Override
	public Integer getMaxIndexByParnetId(Long parentId) {
		return directorySettingDao.getMaxIndexId(parentId);
	}

	@Override
	public Integer getCountByResourcePoolType(Long resourcePoolTypeId) {
		return directorySettingDao
				.getCountByResourcePoolType(resourcePoolTypeId);
	}

	@Override
	public Long getDirectoryIdByparentIdAndindexId(Integer indexId,
			Long parentId) {

		return directorySettingDao.getDirectoryIdByparentIdAndindexId(indexId,
				parentId, ThreadVariable.getUser().getId());
	}

	@Override
	public Long getMinIdByparentIdAndindexId(Integer indexId, Long parentId) {
		return directorySettingDao.getMinIdByParentIdAndLargerThanIndexId(
				indexId, parentId, ThreadVariable.getUser().getId());
	}

	@Override
	public List<DirectorySetting> SearchDirectorySetting(String value) {
		return directorySettingDao.searchDirectorySetting(value);
	}

	@Override
	public List<Long> searchParentNodeIds(Long id) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(id);
		DirectorySetting directorySetting = directorySettingDao
				.findDirectorySettingById(id);
		if (directorySetting.getParentPersonalDirectory() != null)
			addIds(ids, directorySetting.getParentPersonalDirectory().getId());
		return ids;
	}

	public void addIds(List<Long> ids, Long parentId) {

		if (parentId != 0 && parentId != null) {
			ids.add(parentId);
			DirectorySetting directorySetting = directorySettingDao
					.findDirectorySettingById(parentId);
			if (directorySetting != null
					&& directorySetting.getParentPersonalDirectory() != null
					&& directorySetting.getParentPersonalDirectory().getId() != 0
					&& directorySetting.getParentPersonalDirectory().getId() != null)
				addIds(ids, directorySetting.getParentPersonalDirectory()
						.getId());
		}
	}

	@Override
	public List<Long> searchListParentNodeIds(Long id) {
		List<Long> ids = new ArrayList<Long>();
		DirectorySetting directorySetting = directorySettingDao
				.findDirectorySettingById(id);
		if (directorySetting.getParentPersonalDirectory() != null)
			addListIds(ids, directorySetting.getParentPersonalDirectory()
					.getId());
		return ids;
	}

	public void addListIds(List<Long> ids, Long parentId) {
		ids.add(parentId);
		if (parentId != 0) {
			DirectorySetting directorySetting = directorySettingDao
					.findDirectorySettingById(parentId);
			if (directorySetting != null
					&& directorySetting.getParentPersonalDirectory() != null)
				addIds(ids, directorySetting.getParentPersonalDirectory()
						.getId());
		}
	}

	@Override
	@Transactional
	public DirectorySetting add(DirectorySetting entity) {
		autoFilled(entity);

		entity.setUserId(ThreadVariable.getUser().getId());

		setLevelAndIndex(entity);

		return directorySettingDao.add(entity);
	}

	private void setLevelAndIndex(DirectorySetting directorySetting) {
		if (directorySetting.getParentPersonalDirectory() != null) {
			directorySetting.setLevel(directorySetting
					.getParentPersonalDirectory().getLevel() + 1);
			directorySetting.setIndexId(getMaxIndexByParnetId(directorySetting
					.getParentPersonalDirectory().getId()) + 1);
		}
	}

	private void autoFilled(DirectorySetting directorySetting) {
		if (directorySetting.getName() == null
				|| "".equals(directorySetting.getName())) {
			throw new BusinessValidationException("目录名称没有获得");
		}
		autoFillChinesePinyin(directorySetting);

	}

	@Override
	public DirectorySetting update(DirectorySetting entity) {
		autoFilled(entity);
		return directorySettingDao.update(entity);
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			throw new BusinessValidationException("参数错误，id不能为空");
		}
		if (validateDelete(id)) {
			directorySettingDao.delete(id);
		}
		;

	}

	private boolean validateDelete(Long id) {
		int childDirectCount = findDirectorySettingByParentId(id).size();
		if (childDirectCount > 0) {
			throw new BusinessValidationException(
					"该目录下含有子目录，无法删除<br>请先将子目录删除后，再重新删除该目录。");
		}
		int fileCount = getCountByResourcePoolType(id);
		if (fileCount > 0) {
			throw new BusinessValidationException("该目录下已经存在文件，无法删除！");
		}
		return true;

	}

	@Override
	public void updateMove(DirectorySetting directorySetting, String moveMode) {
		if (validateMove(directorySetting, moveMode)) {
			directorySettingDao.updateMove(directory.getIndexId(),
					directory.getId());
			directorySettingDao.updateMove(previousDirect.getIndexId(),
					previousDirect.getId());
		}
	}

	public boolean validateMove(DirectorySetting directorySetting,
			String moveMode) {
		Long previous = null;

		if (directorySetting != null
				&& directorySetting.getId() != null
				&& directorySetting.getParentPersonalDirectory().getId() != null) {
			directory = directorySettingDao.get(directorySetting.getId());
			if ("up".equals(moveMode)) {

				previous = getDirectoryIdByparentIdAndindexId(
						directory.getIndexId(), directorySetting
								.getParentPersonalDirectory().getId());
				if (previous == null) {
					throw new BusinessValidationException("到顶了，不能再上移！");
				}
			} else {
				previous = getMinIdByparentIdAndindexId(directory.getIndexId(),
						directorySetting.getParentPersonalDirectory().getId());
				if (previous == null) {
					throw new BusinessValidationException("到底了，不能再下移！");
				}
			}

		}

		if (previous == null) {
			return false;
		} else {
			int indexid = directory.getIndexId();
			previousDirect = get(previous);
			directory.setIndexId(previousDirect.getIndexId());
			previousDirect.setIndexId(indexid);
		}
		return true;

	}

	@Override
	public List<DirectorySetting> findDirectorySettingByIdArray(
			List<String> array) {
		// TODO Auto-generated method stub
		return directorySettingDao.findDirectorySettingByIdArray(array);
	}

}
