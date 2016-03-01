package com.tianque.state;

public class IssueDealState {
	public static final Long UN_DONE = 1L; // 待受理
	public static final Long DOING = 101L; // 处理中
	public static final Long UN_READ = 2L; // 待阅读

	public static final Long DONE = 1001L; // 已办
	public static final Long READED = 1002L; // 已阅
	public static final Long COMPLETE = 1003L; // 已办结

	public static final Long STEP_COMPLETE = 1101L;// 步骤完成

	public static final Long URGENT = 2001L;
	public static final Long PUBLICLTYCASS = 2002L;
	public static final Long HISTORICAL = 2003L;
	public static final Long NORMAL = 2004L;
	public static final Long YELLOW_CARD = 2005L;
	public static final Long RED_CARD = 2006L;
	public static final Long CANCELNORMAL = 2008L;
	public static final Long COMMAND = 2007L;
	public static final Long CANCELYELLOW_CARD = 2009L;
	public static final Long CANCELRED_CARD = 2010L;
	public static final Long CANCELSUPERVISE = 2011L;
	public static final Long CANCELHISTORICAL = 2012L;
	public static final Long CANCELURGENT = 2013L;
	public static final Long CANCELPUBLICLTYCASS = 2014L;
}
