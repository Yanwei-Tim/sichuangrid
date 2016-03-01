package com.tianque.state;

public interface TicketState {

	public static final Integer DOING = 1;// 进行中
	public static final Integer EXCEPTIONSTOP = 2;// 异常中止
	public static final Integer DONE = 3;// 完成
	public static final Integer TIMEOUT = 4;// 超时
}
