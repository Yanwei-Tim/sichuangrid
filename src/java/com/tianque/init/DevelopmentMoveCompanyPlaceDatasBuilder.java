package com.tianque.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.init.impl.DatabaseToCompanyPlaveMoveInitialization;

public class DevelopmentMoveCompanyPlaceDatasBuilder extends
		InitializationsRunner {

	private static Logger moveDataLog = LoggerFactory
			.getLogger(MoveCompanyPlaceDatasInitialization.class);

	public DevelopmentMoveCompanyPlaceDatasBuilder() throws Exception {
		DatabaseToCompanyPlaveMoveInitialization dataMove = new DatabaseToCompanyPlaveMoveInitialization(
				ContextType.development);
		// 0.读取SQL文件中的SQL
		// dataMove.init();
		// // 单位场所迁移
		// moveDataForCompanyPlace(dataMove);
		// // 数据管理单位场所迁移
		// moveDataManageForCompanyPlace(dataMove);
	}

	/**
	 * 单位场所迁移
	 * 
	 * @throws Exception
	 */
	private void moveDataForCompanyPlace(
			DatabaseToCompanyPlaveMoveInitialization dataMove) throws Exception {
		// logger.error("单位场所迁移开始!");
		// moveDataLog.error("单位场所迁移开始!");
		//
		// // 创建一个假登录用户
		// new CreateSessionForTestInitialization().init();
		// // 1.获得单位场所值及巡场情况中关联关系中的服务记录ID最大值
		// dataMove.maxValueRecord();
		// // 2.为了迁移数据必要的表中添加的冗余字段
		// dataMove.alterTableToAdd();
		// // 3.统计迁移前所有数据关联的表数据量
		// dataMove.countMoveBefore();
		// // 4.执行重点场所对应的数据迁移
		// dataMove.moveDate();
		// // 5.建立治安负责人关系
		// dataMove.insertServiceMemberHasObjectData();
		// // 6.建立巡场情况关系
		// dataMove.insertServiceRecordRelyObjectData();
		// // 7.建立新单位场所地图相关数据
		// dataMove.insertKeyplacesForGis();
		// // 8.删除迁移数据必要的表中添加的冗余字段
		// dataMove.alterTableToDrop();
		// // 9.统计迁移后所有数据关联的表数据量
		// dataMove.countMoveAfter();
		//
		// logger.error("单位场所迁移结束!");
		// moveDataLog.error("单位场所迁移结束!");
	}

	/**
	 * 数据管理迁移
	 * 
	 * @throws Exception
	 */
	private void moveDataManageForCompanyPlace(
			DatabaseToCompanyPlaveMoveInitialization dataMove) throws Exception {
//		logger.error("数据管理单位场所迁移开始!");
//		moveDataLog.error("数据管理单位场所迁移开始!");
//
//		// 数据管理单位场所迁移前数据统计
//		dataMove.countDataManageMoveBefore();
//		// 执行数据管理重点场所对应的数据迁移
//		dataMove.moveDataManageForCompanyPlace();
//		// 数据管理单位场所迁移后数据统计
//		dataMove.countDataManageMoveAfter();
//
//		logger.error("数据管理单位场所迁移结束!");
//		moveDataLog.error("数据管理单位场所迁移结束!");
	}

	public void builderTestEnv() throws Exception {
		executeInitialization();
		logger.error("开发环境初始化结束!");
		moveDataLog.error("开发环境初始化结束!");
		System.exit(0);
	}

}
