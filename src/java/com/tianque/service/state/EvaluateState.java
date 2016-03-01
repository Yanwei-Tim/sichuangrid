package com.tianque.service.state;

public class EvaluateState {
	public final static int NONE = 0; // 无状态
	public final static int ACTIVE = 1; // 已启用
	public final static int REPORT = 2; // 已上报
	public final static int PIGEONHOLE = 3; // 已归档

	public final static int LOCK = 4; // 锁定
	public final static int UN_LOCK = 5; // 未锁定
}
