package com.tianque.viewObject.service;

import java.util.List;

import com.tianque.viewObject.vo.ViewObjectVo;

public interface ViewObjectService {
	/**
	 * 获取各个层级的数量,默认的没有经过操作的，即系统中目前存在的所有层级的数量
	 * 
	 * @return
	 */
	public ViewObjectVo getViewObjectDefNum();

	public ViewObjectVo ajaxGetSelectedNumWhenSelectByLine(String selectedRadio,
			String selectedLevel);

	/**
	 * 保存查看对象到数据库， 如果有id则修改，没有id新增
	 * 
	 * @param viewObjectVo
	 * @return
	 */
	public ViewObjectVo saveViewObjectVo(ViewObjectVo viewObjectVo);

	/**
	 * 通过viewObjectVo查询所有选择的组织机构的id
	 * 
	 * @param viewObjectVo
	 * @return
	 */
	public List<Long> getSelectedOrgIdsByViewObjectVo(ViewObjectVo viewObjectVo);

	/**
	 * 通过viewObjectVoId查询所有选择的组织机构的id
	 * 
	 * @param viewObjectVo
	 * @return
	 */
	public List<Long> getSelectedOrgIdsByViewObjectVoId(Long id);

	public ViewObjectVo getViewObjectById(Long id);

	public ViewObjectVo updateViewObject(ViewObjectVo viewObjectVo);

	public void deleteViewObjectById(Long id);

	/**
	 * 根据viewObject和要显示的type，level获取选择的orgNameList
	 * 
	 * @param viewObjectVo
	 * @param type
	 *        组织机构的类型
	 * @param level
	 *        组织机构的层级
	 * @return
	 */
	public List<String> getselectedOrgNames(ViewObjectVo viewObjectVo, String type, String level);

	/**
	 * 提供给手机获取orgId
	 * 
	 * @param userOrgId
	 *        登陆者的OrgId
	 * @param levelType
	 *        选择的类型，包括上级，本级（包括下辖），全部
	 * @return
	 */
	public List<Long> getOrgIdsMobile(Long userOrgId, Integer levelType);

}
