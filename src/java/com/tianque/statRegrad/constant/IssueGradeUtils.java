package com.tianque.statRegrad.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.statRegrad.util.RegradedPointUtil;

/**
 * 
 * 
 * @author 曾利民
 * @date 2014年11月17日
 */
public class IssueGradeUtils {

	private static final Logger LOG = LoggerFactory
			.getLogger(IssueGradeUtils.class);

	/** 根据所有乡镇办事处和职能部门单位，根据本级和该部门结构下的下辖单位，事件接件总数量的多少排名分为三个档次 */
	public static void allocationDegree(Set<Long> set, List<Long>[] sumKind) {
		try {
			// 读取配置文件regradedPoint.properties
			// 同一个层级的组织机构根据总接件数分成的档次，默认为1:2:2，即分成3个档次，1/5为第一档次2/5为第二档次2/5为第三档次
			List<Integer> scales = RegradedPointUtil.getRankScale();
			// 获取同一层级的所有组织机构的总接件数(去重)，并按降序排序，例如a镇接件数为1000，b镇接件数为988，c镇接件数为985，d镇接件数为988
			// 那么sumKinds = {1000,988,985}
			List<Long> sumKinds = new ArrayList<Long>(set);
			int count = sumKinds.size();// 下辖职能部门的数量（即是接件总数的个数【因为需要分为三个档次，所以当总数为1、2、3时可以直接根据总数的分档次，当分数总数超过3个时，需要进行特殊处理把所有的分数分为三个类】）

			// 根据接件数的种类分档次，规则如下
			// 1、如果接件数种类为1则都为第一档次
			// 2、如果接件数种类为2则一个为第一档次一个为第二档次
			// 3、如果接件数种类为3则一个为第一档次一个为第二档次一个为第三档次
			// 4、如果接件种类为N（N>=4），默认分档次的比例为1:2:2，则总数为5
			// a N/5==0则第一档次为sumKinds.subList(0,
			// N/5*1)，第二档次为sumKinds.subList(N/5*1,
			// (N/5)*2)，第三档次为sumKinds.subList((N/5)*2, sumKinds.size())
			// b N/5>0则第一档次为sumKinds.subList(0,((N mod
			// 5)+1)*1)，第二档次为sumKinds.subList(((N mod 5)+1)*1),((N mod
			// 5)+1)*2))，第三档次为sumKinds.subList(((N mod 5)+1)*2),sumKinds.size())
			switch (count) {
			case 0:
				break;
			case 1:
				sumKind[0] = sumKinds;

				break;
			case 2:
				sumKind[0] = sumKinds.subList(0, 1);
				sumKind[1] = sumKinds.subList(1, count);
				break;
			case 3:
				sumKind[0] = sumKinds.subList(0, 1);
				sumKind[1] = sumKinds.subList(1, 2);
				sumKind[2] = sumKinds.subList(2, count);
				break;
			default:
				Integer scaleCount = scaleCount(scales);// 5
				int first = rounding(count * scales.get(0), scaleCount);
				int second = rounding(count * scales.get(1), scaleCount);
				sumKind[0] = sumKinds.subList(0, first);
				sumKind[1] = sumKinds.subList(first, first + second);
				sumKind[2] = sumKinds.subList(first + second, count);
				break;
			}
		} catch (Exception e) {
			LOG.error("", e);
		}
	}

	private static int rounding(int divisor, int dividend) {
		return (divisor % dividend == 0) ? (divisor / dividend) : (divisor
				/ dividend + 1);
	}

	private static Integer scaleCount(List<Integer> scales) {
		Integer count = 0;
		for (Integer scale : scales) {
			count += scale;
		}
		return count;
	}

}
