package com.tianque.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.ExamineItem;
import com.tianque.domain.ExaminePlan;
import com.tianque.domain.ExamineScoreRecord;
import com.tianque.domain.ExamineScores;
import com.tianque.domain.Organization;
import com.tianque.domain.vo.ExamineItemVo;
import com.tianque.service.ExamineItemService;
import com.tianque.service.ExaminePlanService;
import com.tianque.service.ExamineScoresService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Controller("examineScoreRecordController")
@Scope("prototype")
@Transactional
public class ExamineScoreRecordController extends BaseAction {

	@Autowired
	private ExaminePlanService examinePlanService;
	@Autowired
	private ExamineItemService examineItemService;
	@Autowired
	private ExamineScoresService examineScoresService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private Map<String, ExamineScoreRecord> checkedExamineScoreRecord;
	private ExamineScores examineScores;
	private ExaminePlan examinePlan;
	private ExamineItem examineItem;
	private List<ExaminePlan> examinePlans;
	private List<ExamineItemVo> examineItemVos;

	public String dispatch() throws Exception {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			return prepareAddExamineScoreRecord();
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			return viewExamineScoreRecord();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			return prepareUpdateExamineScoreRecord();
		}
		return SUCCESS;
	}

	public String findExamineScoresList() throws Exception {
		if (examineScores == null || examineScores.getOrg() == null) {
			gridPage = new GridPage(new PageInfo<ExamineScores>());
			return SUCCESS;
		}
		Organization organization = organizationDubboService
				.getSimpleOrgById(examineScores.getOrg().getId());
		PageInfo pageInfo = ControllerHelper.processAllOrgRelativeName(
				examineScoresService.findExamineScoresForPageByOrgInternalCode(
						organization.getOrgInternalCode(), page, rows, sidx,
						sord), organizationDubboService, new String[] { "org" },
				examineScores.getOrg().getId());
		fillPlan(pageInfo);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	private void fillPlan(PageInfo<ExamineScores> pageInfo) {
		if (pageInfo.getResult() == null || pageInfo.getResult().size() == 0)
			return;
		for (ExamineScores examineScores : pageInfo.getResult()) {
			examineScores.setPlan(examinePlanService
					.getSimpleExaminePlanById(examineScores.getPlan().getId()));
		}

	}

	private String prepareAddExamineScoreRecord() {
		examinePlans = examinePlanService.findExaminePlans();

		return SUCCESS;
	}

	private String prepareUpdateExamineScoreRecord() {

		return SUCCESS;
	}

	private String viewExamineScoreRecord() {
		examinePlans = examinePlanService.findExaminePlans();
		if (examineScores != null) {
			examineScores = examineScoresService
					.getFullExamineScoresById(examineScores.getId());
			examineItemVos = examineItemService
					.findSelectedExamineItemVosByExaminePlanId(examineScores
							.getPlan().getId());
		}
		return "view";
	}

	public String addExamineScoreRecord() throws Exception {
		initParameters();
		if (!validateExamineScoreRecord())
			return ERROR;
		Organization org = organizationDubboService.getSimpleOrgById(examineScores
				.getOrg().getId());
		examineScores.setOrgInternalCode(org.getOrgInternalCode());
		examineScores = examineScoresService.addExamineScores(examineScores);
		examineScores.setOrg(org);
		return SUCCESS;
	}

	public String updateExamineScoreRecord() throws Exception {
		initParameters();
		if (!validateExamineScoreRecord())
			return ERROR;

		return SUCCESS;
	}

	public String deleteExamineScoreRecord() throws Exception {

		return SUCCESS;
	}

	public String findSelectedExamineVoForPage() throws Exception {
		if (examinePlan != null) {
			examineItemVos = examineItemService
					.findSelectedExamineItemVosByExaminePlanId(examinePlan
							.getId());
		}
		return SUCCESS;
	}

	public String checkUnique() throws Exception {
		Integer count = examineScoresService
				.countExsistedExamineScoresByOrgIdAndYear(examineScores
						.getOrg().getId(), examineScores.getYear());
		if (count > 0)
			return ERROR;
		return SUCCESS;
	}

	private void initParameters() {
		for (Map.Entry<String, ExamineScoreRecord> entry : checkedExamineScoreRecord
				.entrySet()) {
			examineScores.getScoreRecords().add(entry.getValue());
		}
	}

	private boolean validateExamineScoreRecord() {
		List<ExamineScoreRecord> examineScoreRecords = examineScores
				.getScoreRecords();
		for (ExamineScoreRecord examineScoreRecord : examineScoreRecords) {
			ExamineItem examineItem = examineItemService
					.getSimpleExamineItemById(examineScoreRecord
							.getExamineItem().getId());
			if (examineScoreRecord.getScore() > examineItem.getPlanScore()
					|| examineScoreRecord.getScore() < 0) {
				return false;
			}
		}
		return true;
	}

	public List<ExaminePlan> getExaminePlans() {
		return examinePlans;
	}

	public void setExaminePlans(List<ExaminePlan> examinePlans) {
		this.examinePlans = examinePlans;
	}

	public ExamineItem getExamineItem() {
		return examineItem;
	}

	public void setExamineItem(ExamineItem examineItem) {
		this.examineItem = examineItem;
	}

	public List<ExamineItemVo> getExamineItemVos() {
		return examineItemVos;
	}

	public void setExamineItemVos(List<ExamineItemVo> examineItemVos) {
		this.examineItemVos = examineItemVos;
	}

	public ExaminePlan getExaminePlan() {
		return examinePlan;
	}

	public void setExaminePlan(ExaminePlan examinePlan) {
		this.examinePlan = examinePlan;
	}

	public Map<String, ExamineScoreRecord> getCheckedExamineScoreRecord() {
		return checkedExamineScoreRecord;
	}

	public void setCheckedExamineScoreRecord(
			Map<String, ExamineScoreRecord> checkedExamineScoreRecord) {
		this.checkedExamineScoreRecord = checkedExamineScoreRecord;
	}

	public ExamineScores getExamineScores() {
		return examineScores;
	}

	public void setExamineScores(ExamineScores examineScores) {
		this.examineScores = examineScores;
	}
}
