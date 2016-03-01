package com.tianque.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.dao.ExamineCatalogDao;
import com.tianque.dao.ExamineItemDao;
import com.tianque.domain.ExamineCatalog;
import com.tianque.domain.ExamineItem;
import com.tianque.domain.vo.ExamineItemVo;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.service.ExamineCatalogService;
import com.tianque.service.ExamineItemService;

@Service("examineCatalogService")
public class ExamineCatalogServiceImple extends AbstractBaseService implements
		ExamineCatalogService {

	@Autowired
	private ExamineCatalogDao examineCatalogDao;
	@Autowired
	private ExamineItemService examineItemService;
	@Autowired
	private ExamineItemDao examineItemDao;

	@Override
	public ExamineCatalog addExamineCatalog(ExamineCatalog examineCatalog) {
		return examineCatalogDao.addExamineCatalog(examineCatalog);
	}

	@Override
	public List<ExamineItemVo> findExamineItemVosByPlanId(Long planId) {

		List<ExamineCatalog> examineCatalogs = examineCatalogDao
				.findExamineCatalogs();

		return conversionExamineItemVos(examineCatalogs, planId, null);
	}

	private String formatterCatalogIndex(int i) {
		String[] indexs = { "", "一", "二", "三", "四", "五", "六", "七", "八", "九",
				"十" };
		return indexs[i];
	}

	@Override
	public List<ExamineCatalog> findSelectedExamineCatalogsByPlanId(Long planId) {
		return examineCatalogDao.findSelectedExamineCatalogsByPlanId(planId);
	}

	@Override
	public List<ExamineItemVo> conversionExamineItemVos(
			List<ExamineCatalog> examineCatalogs, Long planId, Boolean selected) {
		if (examineCatalogs == null) {
			throw new BusinessValidationException("参数不正确");
		}
		List<ExamineItemVo> examineItemVos = new ArrayList<ExamineItemVo>();
		for (int i = 0; i < examineCatalogs.size(); i++) {
			List<ExamineItem> examineItems = examineItemDao
					.findExamineItemsByExamineCatalogIdAndPlanId(
							examineCatalogs.get(i).getId(), planId, selected);

			ExamineItemVo topExamineItemVo = new ExamineItemVo();
			int middelIndex = 0;
			for (int j = 0; j < examineItems.size(); j++) {
				ExamineItemVo examineItemVo = new ExamineItemVo();

				examineItemVo.setId(examineItems.get(j).getId());
				examineItemVo
						.setCatalogIndex(formatterCatalogIndex(examineCatalogs
								.get(i).getIndex())); // 大则序号
				examineItemVo.setCatalogShortName(examineCatalogs.get(i)
						.getShortName()); // 大则内容
				examineItemVo.setPlanScore(String.valueOf(examineItems.get(j)
						.getPlanScore())); // 分值
				examineItemVo.setExamineIndex(String.valueOf(examineItems
						.get(j).getIndex())); // 小则序号
				examineItemVo.setExamineContent(examineItems.get(j)
						.getExamineContent()); // 小则内容
				examineItemVo.setExamineOrganizationNames(examineItems.get(j)
						.getExamineOrganizationNames());// 参与单位
				examineItemVo.setRemark(examineItems.get(j).getRemark()); // 备注

				examineItemVo.setRowspanItemCount(1); // 合并小则数量
				examineItemVo.setRowspanItem(false); // 是否合并小则
				examineItemVo.setSelected(examineItems.get(j).getSelected());
				examineItemVo.setEditabled(examineItems.get(j).isEditabled());

				if (j == 0) {
					examineItemVo.setRowspanCatalog(true); // 是否合并大则
					examineItemVo.setRowspanCatalogCount(examineItems.size()); // 合并单元格数
					examineItemVo.setExamineIndex("（"
							+ formatterCatalogIndex(++middelIndex) + "）"); // 小则序号
					examineItemVo.setMiddleItem(true);
				} else {
					if (examineItems.get(j).getOwnerItem() == null) {// 如果是中则
						examineItemVo.setExamineIndex("（"
								+ formatterCatalogIndex(++middelIndex) + "）"); // 小则序号
						examineItemVo.setMiddleItem(true);
					} else {
						if (!examineItemVo.getExamineIndex().equals(
								topExamineItemVo.getExamineIndex())) {// 小则 第一条
							topExamineItemVo = examineItemVo;
							examineItemVo.setRowspanItem(true);
						} else {
							int rowspanItem = topExamineItemVo
									.getRowspanItemCount();
							topExamineItemVo.setRowspanItemCount(++rowspanItem);
							topExamineItemVo.setRowspanItem(true);
						}
						// examineItemVo.setPlanScore(""); 分值设为空
					}
				}
				examineItemVos.add(examineItemVo);
			}
		}
		return examineItemVos;
	}
}
