package com.tianque.init.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tianque.domain.ExamineCatalog;
import com.tianque.domain.ExamineItem;
import com.tianque.domain.ExaminePlan;
import com.tianque.domain.property.ExamineCatalogs;
import com.tianque.init.Initialization;
import com.tianque.service.ExamineCatalogService;
import com.tianque.service.ExamineItemService;

public class ExamineScoreInitialization implements Initialization {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	private ExamineCatalogService examineCatalogService;
	private ExamineItemService examineItemService;

	private ExamineCatalog examineCatalog = new ExamineCatalog();
	private ExamineItem examineItem = new ExamineItem();

	public ExamineScoreInitialization(ExamineCatalogService examineCatalogService,
			ExamineItemService examineItemService) {
		this.examineCatalogService = examineCatalogService;
		this.examineItemService = examineItemService;
	}

	@Override
	public void init() {
		initLeaderShip();
		logger.info(ExamineCatalogs.LEADER_SHIP + "字典初始化结束!");
		initResolveConflicts();
		logger.info(ExamineCatalogs.RESOLVE_CONFLICTS + "字典初始化结束!");
		initGrassrootsConstruction();
		logger.info(ExamineCatalogs.GRASSROOTS_CONSTRUCTION + "字典初始化结束!");
		initGrassrootsWork();
		logger.info(ExamineCatalogs.GRASSROOTS_WORK + "字典初始化结束!");
		initSystemConstruction();
		logger.info(ExamineCatalogs.SYSTEM_CONSTRUCTION + "字典初始化结束!");
		initPublicOrderSatisfaction();
		logger.info(ExamineCatalogs.PUBLIC_ORDER_SATISFACTION + "字典初始化结束!");
		initWorkInnovation();
		logger.info(ExamineCatalogs.WORK_INNOVATION + "字典初始化结束!");
	}

	private void initLeaderShip() {
		ExamineCatalog examineCatalog = addExamineCatalog(1, "落实综治领导责任制情况考核(100分)", null, null);
		// ======================== 党委、政府对综治工作领导有力 ========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 1, -1, null,
				"落实综治领导责任制情况考核(100分)", "党委、政府对综治工作领导有力", false, null, 60D, 60D, false, null, null);
		addExamineItem(null, examineCatalog, 1, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"党委、政府研究综治工作少于2次的每少一次扣5分", true, "(N-2)*5", 60D, 60D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 1, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"对中央和省委、省综治委有关综治工作重大部署，党委、政府在一个月内未落实传达、贯彻措施的，扣10分", false, "-10", 60D, 60D, true,
				"省综治办", null);
		addExamineItem(null, examineCatalog, 2, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"党政主要领导实地调研、指导综治工作少于2次的，每少一次扣5分", false, "(N-2)*5", 60D, 60D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 3, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"党委、政府分管领导不明确的，扣10分；因人事变动，分管领导3个月内未明确的，扣5分", false, "-5", 60D, 60D, true, "省综治办",
				null);
		addExamineItem(
				null,
				examineCatalog,
				4,
				-1,
				addExamineItem,
				"落实综治领导责任制情况考核(100分)",
				"党委、政府未按照省综治委、省纪委、省委组织部、省人力社保厅、声监察厅等五部门以及省综治委与省委组织部下发的有关文件要求，将社会治安综合治理工作纳入党政领导干部的实绩考核内容的，扣5分",
				false, "-5", 60D, 60D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 4, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"未建立综治实绩档案的，扣5分", true, "-5", 60D, 60D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 4, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"未落实组织部门在提拔考核干部时，或者人事部门在办理干部评先、受奖、晋级时，书面征求综治部门意见规定的，分别扣5分", false, "-5", 60D, 60D,
				true, "省综治办", null);
		addExamineItem(null, examineCatalog, 5, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"未表彰奖励综治工作成绩突出的地方、单位和个人的，扣5分", true, "-5", 60D, 60D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 5, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"对发生造成重大社会影响的案（事）件，按照有关政策法律规定应予责任查究和“一票否决”而未执行的，扣5分", false, "-5", 60D, 60D, true,
				"省综治办", null);
		// ======================== 定期签订综治目标管理责任书 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 6, -1, null, "落实综治领导责任制情况考核(100分)",
				"定期签订综治目标管理责任书", false, null, 20D, 20D, false, null, null);
		addExamineItem(null, examineCatalog, 6, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"党委、政府与下级地方党委、政府未签订年度综治目标管理责任书的，扣10分；已签订责任书但不规范的，扣5分", false, "-10/-5", 20D, 20D,
				true, "省综治办", null);
		addExamineItem(null, examineCatalog, 7, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"党委、政府与所辖有关部门、单位未签订年度综治目标管理责任书的，扣10分；已签订责任书但不规范的，扣5分", false, "-10/-5", 20D, 20D,
				true, "省综治办", null);
		// ======================== 综治工作保障措施落实到位 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 8, -1, null, "落实综治领导责任制情况考核(100分)",
				"定期签订综治目标管理责任书", false, null, 20D, 20D, false, null, null);
		addExamineItem(null, examineCatalog, 8, -1, addExamineItem, "落实综治领导责任制情况考核(100分)",
				"未将综治经费列入同级财政预算的，扣20分；已列入预算但未得到较好落实的，扣10分", false, "-10", 20D, 20D, true, "省综治办",
				null);
	}

	private void initResolveConflicts() {
		ExamineCatalog examineCatalog = addExamineCatalog(2, "预防化解矛盾纠纷工作情况(170分)", null, null);
		// ======================== 矛盾纠纷排查化解工作深入开展 ========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 9, -1, null,
				"预防化解矛盾纠纷工作情况(170分)", "矛盾纠纷排查化解工作深入开展", false, null, 30D, 30D, false, null, null);
		addExamineItem(null, examineCatalog, 9, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未按照《浙江省矛盾纠纷排查调处工作规程（试行）》要求，落实矛盾纠纷集中排查、调处化解、“三定一包”、情况报告、督查督办、报结归档等制度的，每项（次)扣2分",
				false, "N*(-2)", 30D, 30D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 10, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未按《浙江省医疗纠纷预防与处理办法》规定建立医疗纠纷人民调解委员会的，扣5分", false, "-5", 30D, 30D, true,
				"省司法厅、省卫生厅、省财政厅", null);
		addExamineItem(null, examineCatalog, 10, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"医疗纠纷人民调解员配备、医疗责任保险（医疗责任风险金）制度及相关经费未按要求落实到位的，每项扣2分", false, "N*(-2)", 30D, 30D,
				true, "省司法厅、省卫生厅、省财政厅", null);
		addExamineItem(null, examineCatalog, 11, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"单独建立工会的企业开展工资集体协商覆盖面低于80%的，每降低1个百分点扣1分", false, "N*(-1)", 30D, 30D, true, "省总工会",
				null);
		addExamineItem(null, examineCatalog, 11, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未开展行业性和区域性工资集体协商工作的，每缺1项扣3分", false, "N*(-3)", 30D, 30D, true, "省总工会", null);
		// ======================== 基层民主法制建设不断推进 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 12, -1, null, "预防化解矛盾纠纷工作情况(170分)",
				"基层民主法制建设不断推进", false, null, 10D, 10D, false, null, null);
		addExamineItem(null, examineCatalog, 12, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未按规定进行村务公开的，每村每发现一次扣1分", false, "N*(-1)", 10D, 10D, true, "省民政厅、省总工会、省司法厅", null);
		addExamineItem(null, examineCatalog, 12, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"公有制企业职工代表大会建制率、厂务公开达标率低于85%的，每降低1个百分点扣2分", false, "N*(-2)", 10D, 10D, true,
				"省民政厅、省总工会、省司法厅", null);
		addExamineItem(null, examineCatalog, 12, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"聘请律师、基层法律服务工作者等担任行政村法律顾问覆盖面低于60%的，每降低1个百分点扣2分", false, "N*(-2)", 10D, 10D, true,
				"省民政厅、省总工会、省司法厅", null);
		addExamineItem(null, examineCatalog, 12, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未按省有关规定要求开展工作的，扣2分", false, "-2", 10D, 10D, true, "省民政厅、省总工会、省司法厅", null);
		// ======================== 去京越级上访、来省集体上访得到有效遏制 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 13, -1, null, "预防化解矛盾纠纷工作情况(170分)",
				"去京越级上访、来省集体上访得到有效遏制", false, null, 30D, 30D, false, null, null);
		addExamineItem(null, examineCatalog, 13, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"去京越级上访、来省集体上访情况，按省信访工作目标管理考核评分办法扣分", false, null, 30D, 30D, true, "省信访局",
				"去京越级上访、来省集体上访情况，按省信访工作目标管理考核评分办法扣分");
		addExamineItem(null, examineCatalog, 13, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未达到省规定合格分的，每降低1个百分点加扣3分", false, "N*(-3)", 30D, 30D, true, "省信访局", null);
		addExamineItem(null, examineCatalog, 14, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"中央、省级交办的涉法涉诉的信访案件当事人去京非正常上访、赴沪扰序滋事的，每次分别扣10分、5分", false, "-10/-5", 30D, 30D, true,
				"省委政法委、省信访局；配合部门：省法院、省检察院、省公安厅、省司法厅", null);
		addExamineItem(null, examineCatalog, 14, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"在清理涉法涉诉信访积案活动中，应清理而未清理的信访人员再次来省去京上访或赴沪扰序滋事的，每次扣5分", false, "N*(-5)", 30D, 30D,
				true, "省委政法委、省信访局；配合部门：省法院、省检察院、省公安厅、省司法厅", null);
		addExamineItem(null, examineCatalog, 14, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"对各类信访人员去京非正常访或赴沪扰序滋事，未按照依法进行处置或处置不到位，致使信访人员再次去京赴沪信访滋事的，每次加扣5分", false, "N*(-5)",
				30D, 30D, true, "省委政法委、省信访局；配合部门：省法院、省检察院、省公安厅、省司法厅", null);
		// ======================== 严重影响社会稳定事件得到有效防范和控制 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 15, -1, null, "预防化解矛盾纠纷工作情况(170分)",
				"严重影响社会稳定事件得到有效防范和控制", false, null, 50D, 50D, false, null, null);
		addExamineItem(null, examineCatalog, 15, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生聚众阻塞铁路、拦截列车，致使铁路运输中断；或聚众阻塞高速公路。致使高速公路运输中断2小时以上事件的，每起扣30分", true, "N*(-30)", 50D,
				50D, true, "省公安厅；配合部门：省委维稳办、省武警总队、省交通运输厅、杭州铁路办事处", null);
		addExamineItem(null, examineCatalog, 16, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生聚众冲击国家机关，严重影响正常工作秩序，造成恶劣影响事件的，每起扣30分", true, "N*(-30)", 50D, 50D, true,
				"省公安厅；配合部门：省委维稳办、省信访局、省武警总队", null);
		addExamineItem(null, examineCatalog, 17, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生打砸抢烧等行为，造成人员伤亡或公私财产重大损失，并造成重大影响的群体性事件，或其他重大群体性事件的，每起扣30分", true, "N*(-30)", 50D,
				50D, true, "省公安厅；配合部门：省委维稳办、省武警总队", null);
		addExamineItem(null, examineCatalog, 18, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生10人以上重复集体去京非正常上访，或100人以上重复来省集体上访；在上海世博会期间，信访人或其他人员到上海扰序滋事，造成恶劣影响的，每起扣30分", true,
				"N*(-30)", 50D, 50D, true, "省信访局", null);
		// ===================== 群体性事件及其他影响社会稳定事件得到有效控制 =====================
		addExamineItem = addExamineItem(null, examineCatalog, 19, -1, null, "预防化解矛盾纠纷工作情况(170分)",
				"群体性事件及其他影响社会稳定事件得到有效控制", false, null, 50D, 50D, false, null, null);
		addExamineItem(null, examineCatalog, 19, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生聚众阻塞铁路或高速公路、冲击国家机关、实施打砸抢烧等大规模群体性事件而未达到本细则第15、16、17条程度的，每起扣15分", true, "N*(-15)",
				50D, 50D, true, "省公安厅；配合部门：省委维稳办、省武警总队、省交通运输厅、杭州铁路办事处", null);
		addExamineItem(null, examineCatalog, 20, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生较大规模的，以及其他造成人员受伤或较大公私财产损失的群体性事件；阻塞国道、省道、海上（内河）航道、城市主干道造成交通中断2小时以上的群体性事件，每起扣10分",
				true, "N*(-10)", 50D, 50D, true, "省公安厅；配合部门：省委维稳办、省武警总队、省交通运输厅、省法院、浙江海事局", null);
		addExamineItem(null, examineCatalog, 20, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"发生其他聚众暴力抗法事件，造成恶劣影响的，每起扣10分", true, "N*(-10)", 50D, 50D, true,
				"省公安厅；配合部门：省委维稳办、省武警总队、省交通运输厅、省法院、浙江海事局", null);
		addExamineItem(null, examineCatalog, 21, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"因海事、渔市或边界等各类纠纷引发群体械斗事件，造成人员受伤或较大公私财产损失的；因学校内部管理及周边社会管理不当，引发学生群体性冲突事件的，每起扣10分",
				true, "N*(-10)", 50D, 50D, true, "省公安厅、省边防总队、省民政厅、省教育厅；配合部门：省海洋与渔业局、浙江省海事局", null);

		addExamineItem(null, examineCatalog, 22, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"县级当年组织开展重大事项社会稳定风险评估数量少于3件的，每少1件扣2分", false, "(N-3)*(-2)", 50D, 50D, true,
				"省委维稳办；配合部门：省信访局", null);
		addExamineItem(null, examineCatalog, 22, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"应开展重大事项社会稳定风险评估而未开展，引发50人以上集体越级上访的，每起扣10分", false, "N*(-10)", 50D, 50D, true,
				"省委维稳办；配合部门：省信访局", null);
		addExamineItem(null, examineCatalog, 22, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"引发群体性事件，造成严重社会影响的，每起扣15分", true, "N*(-15)", 50D, 50D, true, "省委维稳办；配合部门：省信访局",
				null);
		addExamineItem(null, examineCatalog, 22, -1, addExamineItem, "预防化解矛盾纠纷工作情况(170分)",
				"未按《浙江省预防处置群体性事件领导责任制及责任追究制暂行规定》要求追究相关人员责任的，每起加扣5分", false, "N*(-5)", 50D, 50D,
				true, "省委维稳办；配合部门：省信访局", "未按《浙江省预防处置群体性事件领导责任制及责任追究制暂行规定》要求追究相关人员责任的，每起加扣5分");
	}

	private void initGrassrootsConstruction() {
		ExamineCatalog examineCatalog = addExamineCatalog(3, "基层平安创建和社会治安防控体系建设情况考核(300分)", null,
				null);
		// ======================== 严重刑事犯罪活动得到有效打击和控制 ========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 23, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "严重刑事犯罪活动得到有效打击和控制", false, null, 40D, 40D, false,
				null, null);
		addExamineItem(null, examineCatalog, 23, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"七类案件总量占全部刑事案件的比例与全省三年平均比例数相比，每超过0.1个百分点扣2分", false, "N*(-2)", 40D, 40D, true,
				"省公安厅", null);
		addExamineItem(null, examineCatalog, 24, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生杀人、伤害致死案件的，每发生1起扣2分，当年破案的减半扣分（本项扣分以市级所辖县市区数的平均值计算）", false, "N*(-2)", 40D, 40D,
				true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 24, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"案件总数超过本地前三年平均数的，每增加一起加扣6分", false, "N*(-6)", 40D, 40D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 24, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生一次致死3人以上（不包括自杀人数），或在公共场所、校园内发生杀人、伤害致死案件，每起加扣5分", true, "N*(-5)", 40D, 40D, true,
				"省公安厅", null);
		addExamineItem(null, examineCatalog, 24, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"杀人及伤害致死案件破案率达100%的，加15分", false, "+15", 40D, 40D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 25, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生持枪、利用爆炸物品作案的刑事案件，每起加扣5分，当年破案的减半扣分", false, "N*(-5)/N*(-2.5)", 40D, 40D, true,
				"省公安厅", null);
		addExamineItem(null, examineCatalog, 25, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"造成严重后果的每起加扣5分", false, "N*(-5)", 40D, 40D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 26, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"黑社会性质组织犯罪案件，被省级以上部门查处的，每起扣20分", false, "N*(-20)", 40D, 40D, true, "省公安厅、省法院", null);
		addExamineItem(null, examineCatalog, 26, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"恶势力犯罪团伙在本地活动未及时发现，被省级以上部门通报问责的，每起扣10分", false, "N*(-10)", 40D, 40D, true,
				"省公安厅、省法院", null);
		addExamineItem(null, examineCatalog, 26, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"对依法判决的黑社会性质组织、重大恶势力犯罪案件，每起分别加5分、2分，加分上限为15分", false, "N*5/N*2", 40D, 40D, true,
				"省公安厅、省法院", "对依法判决的黑社会性质组织、重大恶势力犯罪案件，每起分别加5分、2分，加分上限为15分");
		// ======================== 重特大刑事犯罪案件得到有效遏制 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 27, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "严重刑事犯罪活动得到有效打击和控制", false, null, 30D, 30D, false,
				null, null);
		addExamineItem(
				null,
				examineCatalog,
				27,
				-1,
				addExamineItem,
				"基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生造成恶劣影响的针对不特定中小学生、幼儿的侵害案件，一次致死5人以上或在公共场所、校园内一次致死3人以上的严重刑事案件，以及在本地区杀死5人以上的系列杀人案等重大刑事案件的，每起扣30分",
				true, "N*(-30)", 30D, 30D, true, "省公安厅", null);
		addExamineItem(
				null,
				examineCatalog,
				28,
				-1,
				addExamineItem,
				"基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生在全国造成重大影响的盗抢金融机构案件，被中央有关部门立案查处的其他严重刑事犯罪案件或突出治安问题，以及被中央重点地区整治办挂牌督办的社会治安重点地区或突出治安问题，每起扣30分",
				true, "N*(-30)", 30D, 30D, true, "省公安厅、省综治办", null);
		// ===================== 社会治安重点地区和突出治安问题得到有效整治 =====================
		addExamineItem = addExamineItem(null, examineCatalog, 29, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "社会治安重点地区和突出治安问题得到有效整治", false, null, 30D, 30D,
				false, null, null);
		addExamineItem(null, examineCatalog, 29, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"社会治安重点地区或突出治安问题，被省重点地区整治办挂牌督办的，每起扣10分", true, "N*(-10)", 30D, 30D, true,
				"省公安厅、省综治办、省委宣传部", null);
		addExamineItem(null, examineCatalog, 29, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"被中央、省级有关部门通报批评的，每起分别扣10分、5分", false, "-10/-5", 30D, 30D, true, "省公安厅、省综治办、省委宣传部",
				null);
		addExamineItem(null, examineCatalog, 29, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"被中央、我省主要媒体报道，造成较大负面影响并查证属实的，每项每起分别扣5分、3分", false, "-5/-3", 30D, 30D, true,
				"省公安厅、省综治办、省委宣传部", null);
		addExamineItem(null, examineCatalog, 30, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"抢劫、抢夺案件总量与本地上年相比，每上升一个百分点扣2分", false, "N*(-2)", 30D, 30D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 30, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"入室盗窃案件与本地前三年平均数相比，每上升1个百分点，扣2分", false, "N*(-2)", 30D, 30D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 31, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"所辖县（市、区）被列为全国毒品问题重点整治地区的，每个扣20分", false, "N*(-20)", 30D, 30D, true, "省公安厅、省卫生厅",
				null);
		addExamineItem(null, examineCatalog, 31, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"被省禁毒委列为整改单位的，每个扣10分", false, "N*(-10)", 30D, 30D, true, "省公安厅、省卫生厅", null);
		addExamineItem(null, examineCatalog, 31, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"被通报批评的，每个扣5分（本项扣分以市级所辖县市区数平均值计算）", false, "N*(-5)", 30D, 30D, true, "省公安厅、省卫生厅",
				null);
		addExamineItem(null, examineCatalog, 31, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"本省籍社区戒毒人员有效执行率低于80%、70%、60%的，分别扣1分、2分、3分", false, "-1/-2/-3", 30D, 30D, true,
				"省公安厅、省卫生厅", null);
		addExamineItem(null, examineCatalog, 31, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"吸毒成瘾者社区药物维持治疗工作按省禁毒办下达给各地的美沙酮维持治疗任务数进行考核，未完成任务的，每降低1个百分点扣1分", false, "N*(-1)",
				30D, 30D, true, "省公安厅、省卫生厅", null);
		// ===================== 各类专项斗争和打击整治工作成效明显 =====================
		addExamineItem = addExamineItem(null, examineCatalog, 32, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "各类专项斗争和打击整治工作成效明显", false, null, 60D, 60D, false,
				null, null);
		addExamineItem(null, examineCatalog, 32, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"打击网络赌博、违法犯罪、打击拐卖妇女儿童犯罪、打击盗窃破坏“三电”、输油气管道安全保护等工作不力的，酌情扣分，每项最高扣10分", false,
				"N*(-10)", 60D, 60D, true, "省公安厅", "酌情扣分，每项最高扣10分");
		addExamineItem(null, examineCatalog, 33, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"反邪教（省委610办）、反偷渡（省边防总队）、打击传销活动（省工商局）、查处规范无照经营工作（省工商局）、打击走私犯罪（省打私办）、"
						+ "“扫黄打非”（省新闻出版局）、非法卫星电视接收设施整治（省广电局）、打击假发票专项行动（省国税局）、国家安全人民防线工作"
						+ "（省国家安全厅）、看守所安全管理（省综合治理看守所安全管理联席会议办公室）、核查纠正监外执行罪犯脱管漏管（省检察院）等工"
						+ "作落实不到位的，酌情扣分，每项最高扣10分", true, "N*(-10)", 60D, 60D, true, null,
				"酌情扣分，每项最高扣10分");
		// ===================== 基层平安创建系列活动扎实推进 =====================
		addExamineItem = addExamineItem(null, examineCatalog, 34, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "基层平安创建系列活动扎实推进", false, null, 30D, 30D, false,
				null, null);
		addExamineItem(null, examineCatalog, 34, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"“网格化管理，组团式服务”、“和谐促进工程”等基层平安创建载体未能实现村（居）、社区、规模企业全覆盖的，扣3分", false, "-3", 30D, 30D,
				true, "省委政法委", null);
		addExamineItem(null, examineCatalog, 35, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"开展“平安交通”、“平安林区”、“平安医院”、“平安（和谐）企业”、“平安（和谐）寺观教堂”、“平安工地”、"
						+ "“平安边界”、“平安家庭”、“青少年平安行动”等系列平安创建工作，不符合省级有关部门工作标准和要求的，每项最高扣15分", false,
				"-15", 30D, 30D, true, "省交通运输厅、省林业厅、省卫生厅、省总工会、省民宗委、省建设厅、省民政厅、省妇联、团省委", "每项最高扣15分");
		// ========================== 社会面安全防范有效 ==========================
		addExamineItem = addExamineItem(null, examineCatalog, 36, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "社会面安全防范有效", false, null, 20D, 20D, false, null,
				null);
		addExamineItem(null, examineCatalog, 36, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"县级社会治安动态视频监控系统建设资金未纳入同级政府城市改造、基本建设投资计划，" + "系统建设和运行维护所需经费不到位的，扣5分", false, "-5",
				20D, 20D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 36, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"年底前未完成视频信息共享平台建设，未完成市级平台与县（市、区）联网的，扣3分", false, "-3", 20D, 20D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 36, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"未落实运行维护机制，建立相应制度的，扣3分", false, "-3", 20D, 20D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 36, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"辅助光源建设未达标，图像质量不符合相关标准要求，正常有效运行的监控点少于90%的，扣2分", false, "-2", 20D, 20D, true,
				"省公安厅", null);
		addExamineItem(null, examineCatalog, 36, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"对视频监控系统发现的治安问题未及时处理的，扣3分", false, "-3", 20D, 20D, true, "省公安厅", null);
		addExamineItem(null, examineCatalog, 37, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"治安信息员、治安巡防员等群防群治队伍不健全，工作不经常的，扣3分", false, "-3", 20D, 20D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 37, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"未有效整合“平台志愿者”队伍充分发挥作用的，扣3分", false, "-3", 20D, 20D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 38, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生从业单位或个人不按规定生产、经营、运输、存储、携带、使用枪支弹药、爆炸物品、特定种类危险化"
						+ "学品、剧毒化学品、放射性物品、管制刀具等物品，导致非法流失或发生重大案件的，每起扣5分；" + "造成严重后果的，每起扣10分；",
				false, "N*(-10)", 20D, 20D, true, "省公安厅；配合部门：省安全生产监管局、省工商局、省环保厅、省交通运输厅、省经信委、浙江海事局",
				null);
		addExamineItem(null, examineCatalog, 38, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"上述物品被境内外恐怖组织或恐怖分子利用的，或非法流入上海影响世博会安全的，每起加扣10分", false, "N*(-10)", 20D, 20D, true,
				"省公安厅；配合部门：省安全生产监管局、省工商局、省环保厅、省交通运输厅、省经信委、浙江海事局", null);
		// ========================== 安全生产状况稳定好转 ==========================
		addExamineItem = addExamineItem(null, examineCatalog, 39, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "安全生产状况稳定好转", false, null, 50D, 50D, false, null,
				null);
		addExamineItem(null, examineCatalog, 39, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"各类事故死亡人数超过上级政府下达的控制指标，每超过1人扣5分", false, "N*(-5)", 50D, 50D, true, "省安全生产监管局", null);
		addExamineItem(null, examineCatalog, 39, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"生产安全事故死亡人数与上年相比，绝对数下降幅度超过15%以上的，每少死亡1人加1分；超过50%以上的，每少死亡1人加2分；", false, "N*1/N*2",
				50D, 50D, true, "省安全生产监管局", null);
		addExamineItem(null, examineCatalog, 40, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"各类事故起数与上年相比，每增加1起扣2分", false, "N*(-2)", 50D, 50D, true, "省安全生产监管局", null);
		addExamineItem(null, examineCatalog, 40, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"较大事故起数超过上级下达的控制指标，每超过1起扣3分", false, "N*(-3)", 50D, 50D, true, "省安全生产监管局", null);
		addExamineItem(null, examineCatalog, 40, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"各类事故经济损失与上年按同口径相比，每增加20万元扣1分", false, "N*(-1)", 50D, 50D, true, "省安全生产监管局", null);
		addExamineItem(null, examineCatalog, 41, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生相关的道路交通、火灾（森林火灾、宗教活动场所因火灾损毁市级以上文物）等较大责任事故，每起扣10分", false, "N*(-10)", 50D, 50D,
				true, "省安全生产监管局；配合部门：省公安厅、省交通运输厅、省林业厅、省民宗委、省消防总队", null);
		addExamineItem(null, examineCatalog, 41, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"再次发生同类事故的，每起加扣10分", false, "N*(-10)", 50D, 50D, true,
				"省安全生产监管局；配合部门：省公安厅、省交通运输厅、省林业厅、省民宗委、省消防总队", null);
		addExamineItem(null, examineCatalog, 41, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"在省定临水、临崖危险路段政治点发生较大交通事故的，每起加扣5分（本条扣分以市级所辖县市区数的平均值计算）", false, "N*(-5)", 50D, 50D,
				true, "省安全生产监管局；配合部门：省公安厅、省交通运输厅、省林业厅、省民宗委、省消防总队", null);
		addExamineItem(null, examineCatalog, 42, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生一次死亡10人以上的各类生产安全事故每起扣30分", false, "N*(-30)", 50D, 50D, true,
				"省安全生产监管局；配合部门：省公安厅、省交通运输厅、省消防总队", null);
		addExamineItem(null, examineCatalog, 42, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生一次死亡10人以上的道路交通事故，另扣负有责任的车籍地10分", false, "N*(-10)", 50D, 50D, true,
				"省安全生产监管局；配合部门：省公安厅、省交通运输厅、省消防总队", null);
		addExamineItem(null, examineCatalog, 43, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"农村公路超限率超过4.5%、卸载率低于82%，县道、乡道、村道交通标志标线完好率分别低于75%、55%、35%的，每项扣5分", false, "N*(-5)",
				50D, 50D, true, "省交通运输厅；配合部门：省林业厅", null);
		addExamineItem(null, examineCatalog, 44, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"“三合一”场所、居住出租房屋消防安全未得到有效整治，消防控制室值班人员未持证上岗或不能熟练操作的，"
						+ "属于人员密集场所的消防重点单位消除火灾隐患、扑救初起火灾、组织人员疏散和消防宣传教育“" + "四个能力”建设不达标的，每项每发现一家扣3分",
				false, "N*(-3)", 50D, 50D, true, "省消防总队", null);
		// ======================= 严重危害国家安全事件得到有效防范和控制 =======================
		addExamineItem = addExamineItem(null, examineCatalog, 45, -1, null,
				"基层平安创建和社会治安防控体系建设情况考核(300分)", "严重危害国家安全事件得到有效防范和控制", false, null, 40D, 40D, false,
				null, null);
		addExamineItem(null, examineCatalog, 45, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生国家工作人员从事间谍、情报或其他各种敌对活动，造成严重危害的；或发生严重泄、" + "窃密事件，造成国家利益重大损失的，每起扣30分", false,
				"N*(-30)", 40D, 40D, true, "省安全厅、省公安厅", null);
		addExamineItem(null, examineCatalog, 46, -1, addExamineItem, "基层平安创建和社会治安防控体系建设情况考核(300分)",
				"发生敌对组织、恐怖组织、邪教组织及宗教极端势力策划、实施敌对活动或捣乱破坏，造成严重政治影响事件的，每起扣30分", false, "N*(-30)", 40D,
				40D, true, "省安全厅、省公安厅；配合部门：省委610办公室、省民宗委", null);
	}

	private void initGrassrootsWork() {
		ExamineCatalog examineCatalog = addExamineCatalog(4, "综治基层基础工作情况(300分)", null, null);
		// ========================== 综治基层基础工作情况 ==========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 47, -1, null,
				"综治基层基础工作情况(300分)", "综治基层基础工作情况", false, null, 100D, 100D, false, null, null);
		addExamineItem(null, examineCatalog, 47, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"未按规定配备市级综治办主任、副主任的，扣5分", true, "-5", 100D, 100D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 47, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"社会管理综合试点县（市、区）未配齐配强综治办主任、副主任的，扣5分", true, "-5", 100D, 100D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 48, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"未落实乡镇（街道）综治委主任由党（工）委、政府（办事处）主要领导担任，综治办主任由党（工）委分管"
						+ "副书记或专职委员担任，按照乡镇（街道）中层正职以上职级配备综治办专职副主任，配备一定数" + "量的专职工作人员规定的，每项扣5分",
				false, "N*(-5)", 100D, 100D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 49, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"乡镇（街道）综治工作中心及城市社区、较大村（常住人口在1000人以上，登记在册暂住人口在100人以上，"
						+ "乡镇人民政府所在地，或者位于县市区城乡结合部的行政村）、规模企业（职工人数在200人以上的企业）"
						+ "综治工作室（站）规范化建成率低于100%的，每项每降低1个百分点扣2分", false, "N*(-2)", 100D, 100D, true,
				"省综治办", null);
		addExamineItem(null, examineCatalog, 50, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"乡镇（街道）综治工作中心首问责任制、工作例会、情况报告、督查督办、值班制度不落实的，每项扣1分", false, "N*(-1)", 100D, 100D,
				true, "省综治办", null);
		addExamineItem(null, examineCatalog, 50, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"排查出来的矛盾纠纷和问题隐患未登记建档的，未落实责任单位和责任人的，每项扣2分", false, "N*(-2)", 100D, 100D, true,
				"省综治办", null);
		addExamineItem(null, examineCatalog, 50, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"安全防范、交通防范、消防安全、禁毒等法制宣传教育工作措施不落实的，每项扣2分", false, "N*(-2)", 100D, 100D, true,
				"省综治办", null);
		addExamineItem(null, examineCatalog, 51, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"未组织开展基层综治干部轮训的，扣10分", false, "-10", 100D, 100D, true, "省综治办", null);
		// ======================== 综治委专门工作措施落实、效果明显 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 52, -1, null, "综治基层基础工作情况(300分)",
				"综治委专门工作措施落实、效果明显", false, null, 200D, 200D, false, null, null);
		addExamineItem(null, examineCatalog, 52, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"流动人口服务管理工作（40分），由省综治委流动人口治安管理工作领导小组办公室制定", false, null, 40D, 40D, true, "省公安厅",
				"由省综治委流动人口治安管理工作领导小组办公室制定");
		addExamineItem(null, examineCatalog, 53, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"归正人员安置帮教工作（30分），由省综治委归正人员安置帮教工作领导小组办公室制定评分标准", false, null, 30D, 30D, true,
				"省司法厅", "由省综治委归正人员安置帮教工作领导小组办公室制定评分标准");
		addExamineItem(null, examineCatalog, 54, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"预防青少年违法犯罪工作（30分），由省综治委预防青少年违法犯罪工作领导小组办公室制定评分标准", false, null, 30D, 30D, true,
				"团省委", "由省综治委预防青少年违法犯罪工作领导小组办公室制定评分标准");
		addExamineItem(null, examineCatalog, 55, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"学校及周边治安综合治理工作（50分），由省综治委学校及周边治安综合治理工作领导小组办公室制定评分标准", false, null, 50D, 50D, true,
				"省教育厅", "由省综治委学校及周边治安综合治理工作领导小组办公室制定评分标准");
		addExamineItem(null, examineCatalog, 56, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"铁路护路联防、海事渔市纠纷调处工作（30分），分别由省综治委铁路护路联防工作领导小组办公室、海事渔事纠纷调处指导小组办公室制定评分标准", false, null,
				30D, 30D, true, "省护路办，配合部门：杭州铁路办事处；省边防总队，配合部门：省海洋与渔业局、浙江海事局",
				"由省综治委铁路护路联防工作领导小组办公室、海事渔事纠纷调处指导小组办公室制定评分标准");
		addExamineItem(null, examineCatalog, 57, -1, addExamineItem, "综治基层基础工作情况(300分)",
				"解决法院执行难工作（20分），由省综治委解决执行难协调工作领导小组办公室制定评分标准", false, null, 20D, 20D, true, "省法院",
				"省综治委解决执行难协调工作领导小组办公室制定评分标准");
	}

	private void initSystemConstruction() {
		ExamineCatalog examineCatalog = addExamineCatalog(5, "综治工作制度建设情况(90分)", null, null);
		// ========================== 社会治安综合治理工作制度落实 ==========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 58, -1, null,
				"综治工作制度建设情况(90分)", "社会治安综合治理工作制度落实", false, null, 30D, 30D, false, null, null);
		addExamineItem(null, examineCatalog, 58, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"召开综治委全体会议少于2次的，每少1次扣2.5分", true, "(N-2)*2.5", 30D, 30D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 58, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"未召开五部门联席会议的，扣2.5分", true, "-2.5", 30D, 30D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 59, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"未按规定落实综治检查督导和成员单位述职、联系点、联席会议等制度并及时上报情况的，每项扣5分", false, "N*(-5)", 30D, 30D, true,
				"省综治办", null);
		addExamineItem(null, examineCatalog, 60, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"未按规定落实定期分析社会治安形势制度并及时上报情况的，扣5分", false, "-5", 30D, 30D, true, "省综治办", null);
		// ======================== 中央和省综治委（办）部署的工作任务落实 ========================
		addExamineItem = addExamineItem(null, examineCatalog, 61, -1, null, "综治工作制度建设情况(90分)",
				"中央和省综治委（办）部署的工作任务落实", false, null, 60D, 60D, false, null, null);
		addExamineItem(null, examineCatalog, 61, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"组织开展“基层基础建设年”活动、社会管理创新综合试点工作、社会治安重点地区排查整治工作措施不力、效果不明显的，酌情扣分，每项最高扣10分", false,
				"-10", 60D, 60D, true, "省综治办", "酌情扣分，每项最高扣10分");
		addExamineItem(null, examineCatalog, 62, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"组织开展“综治宣传月”活动、参加政法综治优秀新闻作品评选活动不积极、效果不明显的，酌情扣分，最高扣5分", false, "-5", 60D, 60D, true,
				"省综治办", "酌情扣分，最高扣5分");
		addExamineItem(null, examineCatalog, 62, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"未及时总结报送宣传工作信息的，扣2分", false, "-2", 60D, 60D, true, "省综治办", null);
		addExamineItem(null, examineCatalog, 63, -1, addExamineItem, "综治工作制度建设情况(90分)",
				"未完成中央和省综治委（办）年内部署的专项斗争工作和其他工作任务的，每项扣2至5分", false, "-2~-5)", 60D, 60D, true,
				"省综治办", "每项扣2至5分");
	}

	private void initPublicOrderSatisfaction() {
		ExamineCatalog examineCatalog = addExamineCatalog(6, "人民群众对社会治安满意程度(40分)", null, null);
		// ======================== 人民群众对社会治安满意程度 ========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 64, -1, null,
				"人民群众对社会治安满意程度(40分)", "人民群众对社会治安满意程度", false, null, 40D, 40D, false, null, null);
		addExamineItem(null, examineCatalog, 64, -1, addExamineItem, "人民群众对社会治安满意程度(40分)",
				"省、市统计部门的群众安全感数据与随机问卷调查的安全感测评数据按一定比例测算后，" + "与省统计局调查的全省群众安全感相比，每降低1个百分点扣2分", false,
				"N*(-2)", 40D, 40D, true, "省统计局、省综治办", null);
		addExamineItem(null, examineCatalog, 64, -1, addExamineItem, "人民群众对社会治安满意程度(40分)",
				"发生影响干扰统计部门正常开展群众安全感调查工作，经查证属实的，每起扣5分", false, "N*(-5)", 40D, 40D, true,
				"省统计局、省综治办", null);
		addExamineItem(null, examineCatalog, 64, -1, addExamineItem, "人民群众对社会治安满意程度(40分)",
				"情节严重的每起加扣5分", false, "N*(-5)", 40D, 40D, true, "省统计局、省综治办", null);
	}

	private void initWorkInnovation() {
		ExamineCatalog examineCatalog = addExamineCatalog(7, "综治工作创新", null, null);
		// ======================== 综治工作创新 ========================
		ExamineItem addExamineItem = addExamineItem(null, examineCatalog, 65, 1, null, "综治工作创新",
				"综治工作创新", false, null, 10D, 10D, false, null, null);
		addExamineItem(null, examineCatalog, 65, 1, addExamineItem, "综治工作创新",
				"经验做法得到中央领导批示肯定的，每次加1分；得到省委省政府主要领导、分管领导或省综治委领导批示"
						+ "肯定的，每次加0.5分；或在全国、全省性政法综治会上介绍推广的，每次分别加1分、0.5分", false, null, 10D, 10D,
				true, "省综治办", "加分项目。同一项目按照分值高的加分，不重复加分，加分累计不超过10分");
		addExamineItem(null, examineCatalog, 65, 1, addExamineItem, "综治工作创新",
				"在《中央综治动态》上刊发的，每次加0.5分", false, null, 10D, 10D, true, "省综治办",
				"加分项目。同一项目按照分值高的加分，不重复加分，加分累计不超过10分");
		addExamineItem(null, examineCatalog, 66, 1, addExamineItem, "综治工作创新",
				"省委、省综治委召开现场会推广典型经验的，加5分", false, null, 10D, 10D, true, "省综治办",
				"加分项目。同一项目按照分值高的加分，不重复加分，加分累计不超过10分");
	}

	private ExamineCatalog addExamineCatalog(Integer index, String shortName, String fullName,
			String remark) {
		examineCatalog.setIndex(index);
		examineCatalog.setShortName(shortName);
		examineCatalog.setFullName(fullName);
		examineCatalog.setRemark(remark);
		return examineCatalogService.addExamineCatalog(examineCatalog);
	}

	private ExamineItem addExamineItem(ExaminePlan examinePlan, ExamineCatalog examineCatalog,
			Integer index, Integer examineType, ExamineItem ownerItem, String catalogDisplayName,
			String examineContent, Boolean autoExamine, String scoreWayDetail, Double planScore,
			Double actualCaculateScore, Boolean editabled, String examineOrganizationNames,
			String remark) {
		examineItem.setPlan(examinePlan);
		examineItem.setCatalog(examineCatalog);
		examineItem.setIndex(index);
		examineItem.setExamineType(examineType);
		examineItem.setOwnerItem(ownerItem);
		examineItem.setCatalogDisplayName(catalogDisplayName);
		examineItem.setExamineContent(examineContent);
		examineItem.setAutoExamine(autoExamine);
		examineItem.setScoreWayDetail(scoreWayDetail);
		examineItem.setPlanScore(planScore);
		examineItem.setActualCaculateScore(actualCaculateScore);
		examineItem.setEditabled(editabled);
		examineItem.setExamineOrganizationNames(examineOrganizationNames);
		examineItem.setRemark(remark);
		return examineItemService.addExamineItem(examineItem);
	}
}
