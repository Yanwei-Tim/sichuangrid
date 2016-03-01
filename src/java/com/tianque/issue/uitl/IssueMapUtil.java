package com.tianque.issue.uitl;

import java.util.ArrayList;
import java.util.List;

import com.tianque.issue.domain.IssueMap;

public class IssueMapUtil {

	public static IssueMap setRelationAndStates(IssueMap im) {
		List<String> states = new ArrayList<String>();
		if (new Integer(21).equals(im.getDealType())) {
			im.setRelation("交办");
		}
		if (new Integer(41).equals(im.getDealType())) {
			im.setRelation("上报");
		}
		if (new Integer(200).equals(im.getDealType())) {
			im.setRelation("回退");
		}
		if (new Integer(31).equals(im.getDealType())) {
			im.setRelation("结案");
		}
		if (new Integer(1).equals(im.getUrgent())) {
			states.add("加急");
		}
		if (new Integer(1).equals(im.getSuperviselevel())) {
			states.add("普通督办");
		}
		if (new Integer(100).equals(im.getSuperviselevel())) {
			states.add("黄牌督办");
		}
		if (new Integer(200).equals(im.getSuperviselevel())) {
			states.add("红牌督办");
		}
		im.setStates(states);
		return im;
	}

}
