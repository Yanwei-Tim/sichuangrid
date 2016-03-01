package com.tianque.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.tianque.core.base.BaseAction;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.AmchartBean;
import com.tianque.domain.ExamineScroseStanalObject;
import com.tianque.service.CommonService;
import com.tianque.service.ExamineScroseStanalService;
import com.tianque.state.Grades;
import com.tianque.util.AmchartUtil;

@Controller("examineScroseStanalController")
@Scope("prototype")
@Transactional
public class ExamineScroseStanalController extends BaseAction {
	@Autowired
	private ExamineScroseStanalService examineScroseStanalService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private AmchartUtil amchartUtil;
	private Long orgId;
	private String year;
	private int minYear;
	private int maxYear;

	public String dispatch() {
		minYear = commonService.getSysBeginUseYear();
		Calendar endCalendar = Calendar.getInstance();
		maxYear = endCalendar.get(Calendar.YEAR);
		if (year == null || "".equals(year)) {
			year = maxYear + "";
		}
		return SUCCESS;
	}

	public String examineScroseStanal() {
		int countAll = examineScroseStanalService.countAll(orgId, year);
		int excellentCount = examineScroseStanalService.examineScroseStanal(orgId, year,
				Grades.EXCELLENT, null);
		int wellCount = examineScroseStanalService.examineScroseStanal(orgId, year, Grades.WELL,
				Grades.EXCELLENT);
		int passCount = examineScroseStanalService.examineScroseStanal(orgId, year, Grades.FAIL,
				Grades.WELL);
		int failCount = examineScroseStanalService.examineScroseStanal(orgId, year, null,
				Grades.FAIL);
		List<ExamineScroseStanalObject> resultList = new ArrayList<ExamineScroseStanalObject>();
		if (countAll != 0) {
			resultList.add(new ExamineScroseStanalObject("优秀", excellentCount, excellentCount * 100
					/ countAll + "%"));
			resultList.add(new ExamineScroseStanalObject("良好", wellCount, wellCount * 100
					/ countAll + "%"));
			resultList.add(new ExamineScroseStanalObject("合格", passCount, passCount * 100
					/ countAll + "%"));
			resultList.add(new ExamineScroseStanalObject("不合格", failCount, failCount * 100
					/ countAll + "%"));
		} else {
			resultList.add(new ExamineScroseStanalObject("优秀", 0, "0%"));
			resultList.add(new ExamineScroseStanalObject("良好", 0, "0%"));
			resultList.add(new ExamineScroseStanalObject("合格", 0, "0%"));
			resultList.add(new ExamineScroseStanalObject("不合格", 0, "0%"));
		}
		PageInfo<ExamineScroseStanalObject> pageInfo = new PageInfo<ExamineScroseStanalObject>();
		pageInfo.setResult(resultList);
		pageInfo.setTotalRowSize(resultList.size());
		pageInfo.setCurrentPage(page);
		pageInfo.setPerPageSize(rows);
		gridPage = new GridPage(pageInfo);
		return SUCCESS;
	}

	public String examineScrosePie() {
		List<AmchartBean> list = new ArrayList<AmchartBean>();
		list.add(createBean(1L, "年度考核评分", "#FF0000"));
		ActionContext.getContext().put("piedatakey",
				amchartUtil.setChartData(list, this.getClass().getName()));
		ActionContext.getContext().put("pietitlekey",
				amchartUtil.setTtile("年度考核评分统计图", this.getClass().getName()));
		return SUCCESS;
	}

	private AmchartBean createBean(Long id, String name, String color) {
		AmchartBean bean = new AmchartBean();
		bean.setColor(color);
		bean.setId(id);
		bean.setName(name);
		Map<String, Integer> dataMap = new HashMap<String, Integer>();
		dataMap.put("优秀",
				examineScroseStanalService.examineScroseStanal(orgId, year, Grades.EXCELLENT, null));
		dataMap.put("良好", examineScroseStanalService.examineScroseStanal(orgId, year, Grades.WELL,
				Grades.EXCELLENT));
		dataMap.put("合格", examineScroseStanalService.examineScroseStanal(orgId, year, Grades.FAIL,
				Grades.WELL));
		dataMap.put("不合格",
				examineScroseStanalService.examineScroseStanal(orgId, year, null, Grades.FAIL));
		bean.setDataMap(dataMap);
		return bean;
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getMinYear() {
		return minYear;
	}

	public void setMinYear(int minYear) {
		this.minYear = minYear;
	}

	public int getMaxYear() {
		return maxYear;
	}

	public void setMaxYear(int maxYear) {
		this.maxYear = maxYear;
	}

}
