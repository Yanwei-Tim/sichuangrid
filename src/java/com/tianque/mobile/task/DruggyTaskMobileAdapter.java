package com.tianque.mobile.task;

public interface DruggyTaskMobileAdapter {
	public String getDruggyTaskList() throws Exception;

	public String addDruggyTask() throws Exception;

	public String updateDruggyTask() throws Exception;

	public String deleteDruggyTask() throws Exception;

	public String viewDruggyTask() throws Exception;

	public String getDruggyTaskIsSign() throws Exception;
	
	public String getInterViewDruggyList() throws Exception;

	public String viewInterViewDruggy() throws Exception;
}
