package com.tianque.controller.vo;

import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import com.tianque.core.vo.Tree;
import com.tianque.domain.property.StatementsReportedType;

/**
 * 对于前五种存在workingRecord里面的日常工作； 都标记为1了，没有给它具体的代码
 */
public class WorkingRecordTreeData extends ExtTreeStringIdData implements Tree {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WorkingRecordTreeData(Object obj) {
		Class objClassType = obj.getClass();
		String className = objClassType.getName();
		String methodName = null;
		String headName = null;
		try {
			if (className
					.equals("com.tianque.domain.workingRecord.WorkingRecord")) {
				methodName = "getName";
				headName = "1";
			} else if (className
					.equals("com.tianque.domain.KeyAreasOfInvestigationInfo")) {
				methodName = "getAreaName";
				headName = String.valueOf(StatementsReportedType.INVESTIGATION);
			} else if (className
					.equals("com.tianque.domain.SignificantIssuedeal")) {
				methodName = "getAddress";
				headName = String
						.valueOf(StatementsReportedType.SIGNIFICANT_ISSUEDEAL);
			}

			Method getNameMethod = objClassType.getMethod(methodName,
					new Class[] {});
			Method getIdMethod = objClassType
					.getMethod("getId", new Class[] {});
			text = (String) getNameMethod.invoke(obj, new Object[] {});
			id = headName + "_"
					+ (Long) getIdMethod.invoke(obj, new Object[] {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		cls = "file";
		leaf = true;
		expanded = false;
		icon = ServletActionContext.getRequest().getContextPath() + LEAF;
		checked = false;
	}
}
