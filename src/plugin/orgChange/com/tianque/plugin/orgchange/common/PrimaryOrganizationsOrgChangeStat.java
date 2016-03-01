package com.tianque.plugin.orgchange.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @ClassName: PrimaryOrganizationsOrgChangeStat
 * @Description: TODO组织机构模块组织结构迁移合并常量类
 * @author wanggz
 * @date 2014-10-17 下午04:38:20
 */
public class PrimaryOrganizationsOrgChangeStat {

	public static List<String> comprehensives = new ArrayList<String>();
	public static List<String> leaderTeams = new ArrayList<String>();
	public static List<Integer> threeValues = new ArrayList<Integer>();
	public static List<Integer> fourValues = new ArrayList<Integer>();

	static {

		leaderTeams.add("预防青少年犯罪");
		leaderTeams.add("特殊人群");
		leaderTeams.add("实有人口");
		leaderTeams.add("校园及周边治理");
		leaderTeams.add("铁路护路");
		leaderTeams.add("两新组织");
		leaderTeams.add("三电专项组");
		leaderTeams.add("社会治安综合治理");
		leaderTeams.add("军地共建平安");

		comprehensives.add("综治工作中心");
		comprehensives.add("综治办");
		comprehensives.add("综治工作站");

		threeValues.add(8);
		threeValues.add(10);
		threeValues.add(3);
		threeValues.add(4);
		threeValues.add(11);

		fourValues.add(9);
		fourValues.add(2);

	}

}
