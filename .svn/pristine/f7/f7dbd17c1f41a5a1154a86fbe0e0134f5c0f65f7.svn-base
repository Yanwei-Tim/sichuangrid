package com.tianque.datatransfer.newExcelExport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.core.base.SearchBaseAction;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.ThreadVariable;
import com.tianque.datatransfer.DataExportHelper;
import com.tianque.datatransfer.ExcelExportHelper;
import com.tianque.datatransfer.ExcelWriter;
import com.tianque.datatransfer.UpdateTicketInfo;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.state.TicketState;

public class NewExcelDataExportThread {

	public final static Logger logger = LoggerFactory
			.getLogger(NewExcelDataExportThread.class);

	public final static int pageRows = 2000;
	private final static ExecutorService pool = Executors.newFixedThreadPool(5);
	private String ticketId;// 缓存ID
	private int pageCount;// 总数
	private Session session;

	private UpdateTicketInfo updateTicketInfo;// 修改缓存的信息
	private String[][] excelDefines;
	private DataExportHelper helper;
	private String fileName;
	private Integer msgIndex = 0;
	private SearchBaseAction searchBaseAction;
	private CountDownLatch latch;
	private boolean isIntercept;// 是否中断
	private boolean isFirst;

	public NewExcelDataExportThread(SearchBaseAction searchBaseAction,
			String ticketId, int pageCount, String[][] excelDefines,
			DataExportHelper helper, String fileName) {
		this.isFirst = true;
		this.ticketId = ticketId;
		this.pageCount = pageCount;
		this.session = ThreadVariable.getSession();
		this.excelDefines = excelDefines;
		this.helper = helper;
		this.fileName = fileName;
		this.searchBaseAction = searchBaseAction;
		updateTicketInfo = new UpdateTicketInfo(
				searchBaseAction.getAppContext());
		updateNextStepMsgTitle("正在导出文件", 0);
		latch = new CountDownLatch(pageCount);
	}

	//启动查询数据线程
	public void allotTask(final Integer index) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (isIntercept)
						return;
					createThreadUser();
					List tempList = searchBaseAction.getNeedExportDatas(index);
					handleExport(index, tempList);
					msgIndex++;
					updateNextStepMsgTitle("数据导出，正在处理中", msgIndex);
				} catch (Exception e) {
					isIntercept = true;
					updateErrorTitleAndRowMsg("导出文件数据时出错，程序已终止，详情参见下方错误信息列表",
							index, "导出excel文件出错!  " + e.getMessage());
				} finally {
					latch.countDown();
				}
				startCreateExportZip();
			}
		});
	}

	//启动打包线程，等待数据生成后再打包
	private void startCreateExportZip() {
		if (isFirst) {
			synchronized (latch) {
				if (isFirst) {
					Thread thread = new Thread() {
						@Override
						public void run() {
							try {
								latch.await();
							} catch (InterruptedException e) {
								logger.error("", e);
							}
							if (isIntercept)
								return;
							createThreadUser();
							updateNextStepMsgTitle("数据导出，正在导出文件", pageCount);
							ExcelWriter writer = constructExcelWriter();
							writer.addWorkSheet(excelDefines[0][2]);
							try {
								File outFile = writer.createZipFile(fileName
										+ ".zip");
								String fileNameTemp = outFile
										.getCanonicalPath();
								fileNameTemp = fileNameTemp.substring(0,
										fileNameTemp.length() - 4);
								ZipOutputStream out = new ZipOutputStream(
										new FileOutputStream(outFile));
								byte[] buffer = new byte[1024];
								out.setEncoding("gbk");
								for (int j = 1; j <= pageCount; j++) {
									FileInputStream fis = new FileInputStream(
											(String) fileNameTemp + "" + j
													+ ".xls");
									out.putNextEntry(new ZipEntry(writer
											.getCurrentSheetName() + j + ".xls"));
									int len;
									while ((len = fis.read(buffer)) != -1) {
										out.write(buffer, 0, len);
									}
									out.closeEntry();
									fis.close();
								}
								out.close();
							} catch (FileNotFoundException e) {
								isIntercept = true;
								updateErrorTitleAndRowMsg(
										"导出文件数据时出错，程序已终止，详情参见下方错误信息列表", 1,
										"导出excel文件出错!");

							} catch (IOException e) {
								isIntercept = true;
								updateErrorTitleAndRowMsg(
										"导出文件数据时出错，程序已终止，详情参见下方错误信息列表", 1,
										"写入excel文件出错!");
							} catch (Exception e) {
								isIntercept = true;
								updateErrorTitleAndRowMsg(
										"导出文件数据时出错，程序已终止，详情参见下方错误信息列表", 1,
										"导出excel文件出错!");
							}
							updateNextStepMsgTitle("导出文件成功", pageCount + 1);
						}
					};
					thread.start();
				}
				isFirst = false;
			}
		}
	}

	private void handleExport(int i, List tempList) throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet(excelDefines[0][2]);
		ExcelExportHelper.writeHeaderToExcel(writer, helper, excelDefines,
				tempList);
		ExcelExportHelper.writeDateToExcel(writer, helper, excelDefines,
				tempList);
		writer.getExcelFile(fileName + "" + i + ".xls");
	}

	private void updateErrorTitleAndRowMsg(String title, int row, String msg) {
		updateTicketInfo.updateErrorTitleAndRowMsg(ticketId, title, row, msg,
				1, 1, 1, pageCount, 1);
	}

	private void updateNextStepMsgTitle(String title, Integer index) {
		updateTicketInfo.updateTicketInfo(ticketId, "{msg:'" + title + "'}",
				index, pageCount + 1, pageCount, pageCount, 0,
				TicketState.DOING);
	}

	private static ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	private void createThreadUser() {
		ThreadVariable.setSession(session);
		User user = new User();
		user.setId(session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setSourcesState(ConstantsProduct.SourcesState.IMPORT);
	}
}
