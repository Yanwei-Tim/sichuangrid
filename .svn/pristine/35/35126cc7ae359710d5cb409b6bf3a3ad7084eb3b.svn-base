package com.tianque.plugin.orgchange.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.oproject.framework.orm.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.domain.Permission;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.plugin.orgchange.dao.ModuleTableDAO;
import com.tianque.plugin.orgchange.domain.ColumnInfo;
import com.tianque.plugin.orgchange.domain.ModuleTable;
import com.tianque.plugin.orgchange.domain.TableInfo;
import com.tianque.sysadmin.service.PermissionService;

/**
 * 功能模块表服务实现
 * 
 * @author 曾利民<br>
 *         email:zenglimin@hztianque.com <br>
 *         date:2014年9月24日
 */
@Transactional
@Service("moduleTableService")
public class ModuleTableServiceImpl implements ModuleTableService {

	@Autowired
	private ModuleTableDAO moduleTableDAO;
	@Autowired
	private PermissionService permissionService;

	@Qualifier("moduleTableValidateImpl")
	@Autowired
	private DomainValidator<ModuleTable> domainValidator;

	@Override
	public ModuleTable maintainModuleTable(ModuleTable moduleTable) {
		if (moduleTable == null) {
			throw new BusinessValidationException("新增或修改失败");
		}
		moduleTableValidator(moduleTable);
		try {
			moduleTable.setActive(true);
			moduleTable.setOperateMode(1);
			if (moduleTable.getIsMainTable() == null) {
				moduleTable.setIsMainTable(0L);
			}
			if (moduleTable.getId() == null) {
				Long id = moduleTableDAO.addModuleTable(moduleTable);
				moduleTable.setId(id);
			} else {
				moduleTableDAO.updateModuleTable(moduleTable);
			}
			return moduleTable;
		} catch (Exception e) {
			throw new ServiceValidationException("模块表信息新增修改报错", e);
		}
	}

	/**
	 * 数据验证
	 */
	private void moduleTableValidator(ModuleTable moduleTable) {
		ValidateResult baseDataValidator = domainValidator
				.validate(moduleTable);
		if (baseDataValidator.hasError()) {
			throw new BusinessValidationException(
					baseDataValidator.getErrorMessages());
		}
	}

	public ModuleTable findModuleTable(Long id) {
		return moduleTableDAO.getModueTableById(id);
	}

	public void mergeModuleTable(ModuleTable moduleTable) {

	}

	public void deleteModuleTable(Long id) {

	}

	@Override
	public PageResult<TableInfo> findTableInfos(TableInfo tableInfo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortField", sidx);
			map.put("order", sord);
			map.put("tableInfo", tableInfo);
			PageResult<TableInfo> pageInfo = moduleTableDAO
					.queryTableInfosForPageResult(map, pageNum, pageSize);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询业务表信息报错", e);
		}
	}

	@Override
	public PageResult<ColumnInfo> findColumnInfos(ColumnInfo columnInfo,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if ("columnName".equals(sidx)) {
				sidx = "COLUMN_NAME";
			}
			map.put("sortField", sidx);
			map.put("order", sord);
			map.put("columnInfo", columnInfo);
			PageResult<ColumnInfo> pageInfo = moduleTableDAO
					.queryColumnInfosForPageResult(map, pageNum, pageSize);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询表字段信息报错", e);
		}
	}

	@Override
	public PageResult<ModuleTable> findModuleTableList(ModuleTable moduleTable,
			Integer pageNum, Integer pageSize, String sidx, String sord) {
		if (moduleTable == null || moduleTable.getPermission().getId() == null) {
			throw new BusinessValidationException("查询业务表列表信息参数未获得");
		}
		Permission permission = permissionService
				.getPermissionByPermissionId(moduleTable.getPermission()
						.getId());
		moduleTable.setPermission(permission);
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("sortField", sidx);
			map.put("order", sord);
			map.put("moduleTable", moduleTable);
			PageResult<ModuleTable> pageInfo = moduleTableDAO
					.queryfindModuleTableForPageResult(map, pageNum, pageSize);
			return pageInfo;
		} catch (Exception e) {
			throw new ServiceValidationException("查询表业务信息列表报错", e);
		}
	}

	@Override
	public int deleteModuleTable(Long count, ModuleTable moduleTable) {
		try {
			int deleteCount = moduleTableDAO.deleteModuleTable(moduleTable
					.getId());
			return deleteCount;
		} catch (Exception e) {
			throw new ServiceValidationException("删除表业务关系信息报错", e);
		}
	}

	@Override
	public List<ModuleTable> queryAllForList(boolean active) {
		return moduleTableDAO.queryAllForList(active);
	}

	@Override
	public void stopMpduleTable(Long moduleId, Long permissionId) {

		Map<String, Object> map = new HashMap<String, Object>();
		if (moduleId == null) {
			if (permissionId != null) {
				List<String> permissionEnames = dealPermissionEname(permissionId);
				map.put("permissionEnames", permissionEnames);
			}
			map.put("moduleId", null);
		} else {
			map.put("moduleId", moduleId);
		}
		map.put("active", 0);
		moduleTableDAO.updateMpduleTable(map);
	}

	private List<String> dealPermissionEname(Long permissionId) {
		List<String> permissionEnames = new ArrayList<String>();
		List<Permission> list = permissionService
				.getAllChildPermissionsByParentId(permissionId);
		if (list != null && list.size() != 0) {
			for (Permission permission : list) {
				if (permission.getEname() != null) {
					permissionEnames.add(permission.getEname());
				}
			}
		}
		return permissionEnames;
	}

	@Override
	public void startMpduleTable(Long moduleId, Long permissionId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (moduleId == null) {
			if (permissionId != null) {
				List<String> permissionEnames = dealPermissionEname(permissionId);
				map.put("permissionEnames", permissionEnames);
			}
			map.put("moduleId", null);
		} else {
			map.put("moduleId", moduleId);
		}
		map.put("active", 1);
		moduleTableDAO.updateMpduleTable(map);
	}

	// /**重新排序对executeLevel赋值*/
	// private void resort(Long count, ModuleTable moduleTable) {
	// Map<String, Object> parameterMap = new HashMap();
	// parameterMap.put("sortField", "executeLevel");
	// parameterMap.put("order", "ASC");
	// parameterMap.put("moduleTable", moduleTable);
	// List<ModuleTable> list =
	// moduleTableDAO.queryfindModuleTableForPageResult(parameterMap, 1,
	// count.intValue())
	// .getResultList();
	// for (int i = 0; i < list.size(); i++) {
	// ModuleTable temp = list.get(i);
	// temp.setExecuteLevel(Integer.parseInt(String.valueOf(i)));
	// moduleTableDAO.updateModuleTable(temp);
	// }
	// }

}
