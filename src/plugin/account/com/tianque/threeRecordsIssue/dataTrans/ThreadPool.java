package com.tianque.threeRecordsIssue.dataTrans;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.tianque.datatransfer.ExcelDataimportThread;
import com.tianque.threeRecordsIssue.dataTrans.dataImport.ExcelDataImport;

public final class ThreadPool extends ThreadPoolExecutor {

	private static Map<Long, Thread> map = new ConcurrentHashMap<Long, Thread>();

	private static void put(Long key, Thread thread) {
		map.put(key, thread);
	}

	public static void remove(Long key) {
		Thread thread = (Thread) map.get(key);
		if (null == thread) {
			return;
		} else if (thread instanceof ExcelDataimportThread) {

			((ExcelDataimportThread) thread).setIntercept(true);
		} else if (thread instanceof ExcelDataImport) {

			((ExcelDataImport) thread).setIntercept(true);
		}
		map.remove(key);
	}

	public static void newrRemove(Long key) {
		ExcelDataImport thread = (ExcelDataImport) map.get(key);
		if (null == thread) {
			return;
		}
		thread.setIntercept(true);
		map.remove(key);
	}

	private ThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
				new CallerRunsPolicy());
	}

	private static BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

	private static ThreadPool threadPool = new ThreadPool(4, 20, 30000,
			TimeUnit.MILLISECONDS, queue);

	public static ThreadPool getInstance() {
		return threadPool;
	}

	@Override
	public void execute(Runnable command) {
		Thread thread = (Thread) command;
		put(thread.getId(), thread);
		super.execute(command);
	}

}
