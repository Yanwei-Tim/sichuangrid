/**
 * 
 */
package com.tianque.job.optmize;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.taobao.pamirs.schedule.TaskItemDefine;

/**
 * 
 * @author 曾利民
 * @version 1.0.0
 * @since 2015年2月6日 下午1:57:24
 */
public class OptimizeJobUtils {
	/**
	 * @param taskParameter
	 * @param queryCondition
	 * @param all
	 * @return
	 */
	public static List<String> getExcuteShard(String taskParameter,
			List<TaskItemDefine> queryCondition, List<String> all) {
		Integer shardNum = all.size();
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(taskParameter);
		if (isNum.matches()) {
			shardNum = Integer.parseInt(taskParameter);
		}

		int time = 1;
		//如果拆分的任务个数比分表数小则需要，多次分配
		if (shardNum < all.size()) {
			time = (all.size() + 1) / shardNum;
		}
		List<String> excute = new ArrayList<String>();
		for (int i = 0; i < time; i++) {
			for (TaskItemDefine taskItemDefine : queryCondition) {
				String taskItemId = taskItemDefine.getTaskItemId();
				if (pattern.matcher(taskItemId).matches()) {
					int index = i * shardNum + Integer.parseInt(taskItemId);
					if (index < all.size()) {
						excute.add(all.get(index));
					}
				}
			}
		}
		return excute;
	}
}
