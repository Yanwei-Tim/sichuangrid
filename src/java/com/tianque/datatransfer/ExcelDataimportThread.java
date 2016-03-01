package com.tianque.datatransfer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.tianque.core.datatransfer.DataType;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.core.datatransfer.ExcelReader;
import com.tianque.core.datatransfer.UserImportHelper;
import com.tianque.core.datatransfer.data.ExcelData;
import com.tianque.core.util.ConstantsProduct;
import com.tianque.core.util.StringUtil;
import com.tianque.core.util.ThreadVariable;
import com.tianque.core.validate.ValidateResult;
import com.tianque.datatransfer.dataconvert.OrganizationDataConverter;
import com.tianque.datatransfer.dataconvert.UserDataConverter;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.domain.property.OrganizationLevel;
import com.tianque.exception.base.BusinessValidationException;
import com.tianque.state.TicketState;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;
import com.tianque.ticket.DataImportTicket;
import com.tianque.ticket.service.TicketService;

public class ExcelDataimportThread extends Thread {

	public final static Logger logger = LoggerFactory
			.getLogger(ExcelDataimportThread.class);

	private PropertyDictService propertyDictService;
	private OrganizationDubboService organizationDubboService;
	private TicketService ticketService;
	private String ticketId;
	private String fileUrl;
	private int firstDataRow;
	private String templates;
	private String dataType;
	private Session session;
	private PlatformTransactionManager jtaTransactionManager;
	private boolean validateErrorOccur = false;
	private int totleStep;
	private DataConvert converter;
	private ApplicationContext appContext;
	private FileInputStream file;
	private ExcelData excelDatas;
	private int currentStep = 0;
	private int totalRow;
	
	private UserDataConverter userDataConverter;
	
	private OrganizationDataConverter organizationDataConverter;

	private boolean isIntercept;

	public ExcelDataimportThread(Session session,
			ApplicationContext appContext, String ticketId, String fileUrl,
			String dataType, int firstDataRow, String templates)
			throws Exception {
		this.appContext = appContext;
		this.ticketId = ticketId;
		this.fileUrl = fileUrl;
		this.dataType = dataType;
		this.firstDataRow = firstDataRow;
		this.session = session;
		this.templates = templates;
		init();
	}

	private void init() throws Exception {
		this.organizationDubboService = (OrganizationDubboService) appContext
				.getBean("organizationDubboService");
		this.propertyDictService = (PropertyDictService) appContext
				.getBean("propertyDictService");
		this.ticketService = (TicketService) appContext
				.getBean("ticketService");
		this.jtaTransactionManager = (PlatformTransactionManager) appContext
				.getBean("txManager");
		file = openFile(fileUrl);
		excelDatas = ExcelReader.readFile(file);
	}

	@Override
	public void run() {

		createThreadUser();

		if (!validateParames(dataType, fileUrl, excelDatas, firstDataRow)) {
			return;
		}

		converter = getDataConvert(dataType, appContext);

		if (isConverterNull(converter)) {
			return;
		}

		totleStep = initializeTotalStep(totalRow);

		proccessExcelDatas(excelDatas);
	}

	private void updateNextStepMsgTitle(String title) {
		updateTicketInfo(ticketId, "{msg:'" + title + "'}", getNextStep(),
				totleStep, TicketState.DOING);
	}

	private void proccessExcelDatas(ExcelData excelDatas) {

		updateNextStepMsgTitle("正在解析导入的文件");

		String[][] datas = excelDatas.getSheetDatas(ExcelData.FIRST);

		if (validateExcelHeader(datas)) {
			return;
		}

		updateNextStepMsgTitle("文件已解析，准备校验数据格式");

		if (UserImportHelper.isUserImport(dataType)) {
			proccessUserDatas(datas);
		}else if(DataType.ORG_DATA_TYPE.equals(dataType)){
			proccessOrganizationDatas(datas);
		} else {
			proccessDatas(datas);
		}
		updateCompleteMsgTitle("数据已保存，处理完成");
	}

	private void proccessDatas(String[][] datas) {
		List<Object> objects = convertToDomains(datas);

		if (validateErrorOccur) {
			updateTicketInfo(ticketId, "{msg:'校验数据格式已完成'}", 1, 1,
					TicketState.EXCEPTIONSTOP);
			throw new BusinessValidationException("导入失败");
		}

		updateNextStepMsgTitle("数据格式效验通过，准备校验数据有效性");
		initializeCurrentStep();
		if (validateRowDatas(objects)) {
			updateTicketInfo(ticketId, "{msg:'校验数据有效性已完成'}", 1, 1,
					TicketState.EXCEPTIONSTOP);
			throw new BusinessValidationException("导入失败");
		}

		updateNextStepMsgTitle("校验已经通过,准备保存数据");

		initializeCurrentStep();
		importToDataBase(objects);
	}
	
	private void proccessOrganizationDatas(String[][] datas){
		initializeCurrentStep();
		validateRowDatasForOrganization(datas);
		if (validateErrorOccur) {
			updateTicketInfo(ticketId, "{msg:'校验数据结束，出现如下错误'}", 1, 1,
					TicketState.EXCEPTIONSTOP);
			throw new BusinessValidationException("导入失败");
		}
		initializeCurrentStep();
		importOrganizationToDataBase(datas);
	}

	private void proccessUserDatas(String[][] datas) {
		initializeCurrentStep();

		validateRowDatasForUser(datas);
		if (validateErrorOccur) {
			updateTicketInfo(ticketId, "{msg:'校验数据结束，出现如下错误'}", 1, 1,
					TicketState.EXCEPTIONSTOP);
			throw new BusinessValidationException("导入失败");
		}

		initializeCurrentStep();
		importUsersToDataBase(datas);
	}

	private boolean validateExcelHeader(String[][] datas) {
		if (!UserImportHelper.isUserImport(dataType)
				&& validateTemplateVersion(templates, datas[0][0])) {
			return true;
		}
		Organization uploadOrg = getOrgFromData(datas);
		if (isOrgNull(uploadOrg)) {
			return true;
		}
		converter.setUploadOrganization(uploadOrg);

		return !UserImportHelper.isUserImport(dataType)
				&& validateOrganization(datas);
	}
	
	private void validateRowDatasForOrganization(String[][] datas){
		if(datas==null || datas.length<5){
			
		}
		organizationDataConverter=(OrganizationDataConverter) getDataConvert(dataType, appContext);
		firstDataRow=4;
		for (int rowIndex = firstDataRow; rowIndex < datas.length; rowIndex++) {
			updateNextStepMsgTitle("正在效验数据。。。");
			String[] rowData = datas[rowIndex];
			ExcelImportHelper.realRow.set(rowIndex + 1);
			try {
				ExcelImportHelper.realRow.set(rowIndex + 1);
				if (!isBlankRow(rowData)) {
					ValidateResult vresult = organizationDataConverter.validateForOrganization(rowData,
							rowIndex + 1);
					if (!validateErrorOccur) {
						validateErrorOccur = vresult.hasError();
					}
					updateTicketErrorMsg(ticketId, vresult, TicketState.DOING);
				}
			} catch (Exception e) {
				updateErrorTitleAndRowMsg("数据格式出错，程序已终止，详情参见下方错误信息列表",
						rowIndex + 1, e.getMessage());
				validateErrorOccur = true;
			}
		}
	}

	private void validateRowDatasForUser(String[][] datas) {
		// excel表格中最后两行是不需要解析的
		if(UserImportHelper.DISCRIPT_NAME.equals(datas[2][0])){//区县以下账号导入模板更改
			userDataConverter = (UserDataConverter) getDataConvert(dataType, appContext);
			firstDataRow = 6;
			for (int rowIndex = firstDataRow; rowIndex < datas.length; rowIndex++) {
				updateNextStepMsgTitle("正在效验数据。。。");
				String[] rowData = datas[rowIndex];
				try {
					ExcelImportHelper.realRow.set(rowIndex + 1);
					if (!isBlankRow(rowData)) {
						ValidateResult vresult = userDataConverter.validateForDisprit(rowData,
								rowIndex + 1);
						if (!validateErrorOccur) {
							validateErrorOccur = vresult.hasError();
						}
						updateTicketErrorMsg(ticketId, vresult, TicketState.DOING);
					}
				} catch (Exception e) {
					updateErrorTitleAndRowMsg("数据格式出错，程序已终止，详情参见下方错误信息列表",
							rowIndex + 1, e.getMessage());
					validateErrorOccur = true;
				}
			}
		}else{
			for (int rowIndex = firstDataRow; rowIndex < datas.length - 2; rowIndex++) {
				updateNextStepMsgTitle("正在效验数据。。。");
				String[] rowData = datas[rowIndex];
				if (rowData[0].endsWith(UserImportHelper.OTHER_DATA)) {
					converter.setCheckOrgOrNot(UserImportHelper.OTHER);
					rowIndex = +3;
					firstDataRow = +3;
					rowData = datas[rowIndex];
				}
				try {
					ExcelImportHelper.realRow.set(rowIndex + 1);
					if (!isBlankRow(rowData)) {
						ValidateResult vresult = converter.validate(rowData,
								rowIndex + 1);
						if (!validateErrorOccur) {
							validateErrorOccur = vresult.hasError();
						}
						updateTicketErrorMsg(ticketId, vresult, TicketState.DOING);
					}
					if (rowIndex + 1 < datas.length - 2) {
						String[] nextData = datas[rowIndex + 1];
						if (nextData[0].endsWith(UserImportHelper.OTHER_DATA)) {
							converter.setCheckOrgOrNot(UserImportHelper.OTHER);
							rowIndex += 3;
						}
					}
				} catch (Exception e) {
					updateErrorTitleAndRowMsg("数据格式出错，程序已终止，详情参见下方错误信息列表",
							rowIndex + 1, e.getMessage());
					validateErrorOccur = true;
				}
			}
		} 
	}

	private List<Object> convertToDomains(String[][] datas) {
		List<Object> temps = new ArrayList<Object>();
		for (int rowIndex = firstDataRow; rowIndex < datas.length; rowIndex++) {
			if (isIntercept) {
				return temps;
			}
			updateNextStepMsgTitle("正在效验数据格式。。。");
			ValidateResult result = new ValidateResult();
			String[] rowData = datas[rowIndex];

			try {
				ExcelImportHelper.realRow.set(rowIndex + 1);
				if (!isBlankRow(rowData)) {
					Object domain = converter.convertToDomain(rowData, result);
					temps.add(domain);
					if (!validateErrorOccur) {
						validateErrorOccur = result.hasError();
					}
					updateTicketErrorMsg(ticketId, result, TicketState.DOING);
				}
			} catch (Exception e) {
				updateErrorTitleAndRowMsg("数据格式出错，程序已终止，详情参见下方错误信息列表",
						rowIndex + 1, e.getMessage());
				throw new BusinessValidationException("导入失败");
			}
		}
		return temps;
	}
	
	private void importOrganizationToDataBase(String[][] datas){
		converter.setCheckOrgOrNot(UserImportHelper.OTHER);//组织机构导入不需要验证
		organizationDataConverter=(OrganizationDataConverter) getDataConvert(dataType, appContext);
		firstDataRow=4;
		for (int rowIndex = firstDataRow; rowIndex < datas.length; rowIndex++) {
			updateNextStepMsgTitle("正在保存数据。。。");
			String[] rowData = datas[rowIndex];
			try {
				ExcelImportHelper.realRow.set(rowIndex + 1);
				if (!isBlankRow(rowData)) {
					Object domain = organizationDataConverter.convertToOrganization(rowData);
					converter.persistenceDomain(domain);
				}

			} catch (Exception e) {
				updateErrorTitleAndRowMsg("保存数据出错，程序已终止，详情参见下方错误信息列表",
						rowIndex + 1, e.getMessage());
				throw new BusinessValidationException("导入失败");
			}
		}
	}

	private void importUsersToDataBase(String[][] datas) {
		converter.setCheckOrgOrNot(UserImportHelper.SELF);
		if(UserImportHelper.DISCRIPT_NAME.equals(datas[2][0])){//区县以下账号导入模板更改
			userDataConverter = (UserDataConverter) getDataConvert(dataType, appContext);
			firstDataRow = 6;
			for (int rowIndex = firstDataRow; rowIndex < datas.length; rowIndex++) {
				updateNextStepMsgTitle("正在保存数据。。。");
				String[] rowData = datas[rowIndex];
				try {
					ExcelImportHelper.realRow.set(rowIndex + 1);
					if (!isBlankRow(rowData)) {
						Object domain = userDataConverter.convertToDomainForNewUser(rowData);
						converter.persistenceDomain(domain);
					}

				} catch (Exception e) {
					updateErrorTitleAndRowMsg("保存数据出错，程序已终止，详情参见下方错误信息列表",
							rowIndex + 1, e.getMessage());
					throw new BusinessValidationException("导入失败");
				}
			}
		}else{
			for (int rowIndex = firstDataRow; rowIndex < datas.length - 2; rowIndex++) {
				updateNextStepMsgTitle("正在保存数据。。。");
				String[] rowData = datas[rowIndex];
				try {
					ExcelImportHelper.realRow.set(rowIndex + 1);
					if (!isBlankRow(rowData)) {
						Object domain = converter.convertToDomain(rowData);
						converter.persistenceDomain(domain);
					}

					if (rowIndex + 1 < datas.length - 2) {
						String[] nextData = datas[rowIndex + 1];
						if (nextData[0].endsWith(UserImportHelper.OTHER_DATA)) {
							converter.setCheckOrgOrNot(UserImportHelper.OTHER);
							rowIndex += 3;
						}
					}
				} catch (Exception e) {
					updateErrorTitleAndRowMsg("保存数据出错，程序已终止，详情参见下方错误信息列表",
							rowIndex + 1, e.getMessage());
					throw new BusinessValidationException("导入失败");
				}
			}
		}
		
	}

	private void importToDataBase(List<Object> datas) {
		int total = 1;
		TransactionStatus status = beginTransaction();
		for (int rowIndex = firstDataRow; rowIndex < datas.size()
				+ firstDataRow; rowIndex++) {
			updateNextStepMsgTitle("正在保存数据。。。");
			try {
				Object domain = datas.get(rowIndex - firstDataRow);
				if (!converter.isRepeatData(domain)) {
					converter.persistenceDomain(domain);
				} else {
					converter.updateDomain(domain);
				}
				if (total % 1000 == 0) {
					commitTransaction(status);
					status = beginTransaction();
				}
				total++;
			} catch (Exception e) {
				updateErrorTitleAndRowMsg("保存数据出错，程序已终止，详情参见下方错误信息列表",
						rowIndex + 1, e.getMessage());
				rollbackTransaction(status);
				throw new BusinessValidationException("导入失败");
			}
		}
		if (!status.isCompleted()) {
			finishTransaction(status);
		}
	}

	private void updateCompleteMsgTitle(String title) {
		updateTicketInfo(ticketId, "{successMsg:'" + title + "'}", 1, 1,
				TicketState.DONE);
	}

	private void initializeCurrentStep() {
		currentStep = 0;
	}

	private int initializeTotalStep(int totalRow) {
		return 3 + totalRow;
	}

	private boolean validateRowDatas(List<Object> datas) {
		for (int rowIndex = firstDataRow; rowIndex < datas.size()
				+ firstDataRow; rowIndex++) {
			if (isIntercept) {
				return true;
			}
			updateNextStepMsgTitle("正在校验数据有效性。。。");
			try {
				ValidateResult vresult = converter.validate(
						datas.get(rowIndex - firstDataRow), rowIndex + 1);
				if (!validateErrorOccur) {
					validateErrorOccur = vresult.hasError();
				}
				updateTicketErrorMsg(ticketId, vresult, TicketState.DOING);

			} catch (Exception e) {
				updateErrorTitleAndRowMsg("校验数据时出错，程序已终止，详情参见下方错误信息列表",
						rowIndex + 1, e.getMessage());
				throw new BusinessValidationException("导入失败");
			}
		}

		return validateErrorOccur;
	}

	private static boolean isBlankRow(String[] rowData) {
		if (rowData == null)
			return true;
		for (String data : rowData) {
			if (StringUtil.isStringAvaliable(data)) {
				return false;
			}
		}
		return true;
	}

	private boolean validateOrganization(String[][] datas) {
		if(DataType.ORG_DATA_TYPE.equals(dataType)){//组织机构导入不需要验证表头
			return false;
		}
		int index = 1;
		while (index < datas[2].length && !isEmptyString(datas[2][index])) {
			index = index + 2;
		}

		boolean isEmptyOrg = true;
		while (index < datas[2].length - 3) {
			index = index + 2;
			if (!isEmptyString(datas[2][index])) {
				isEmptyOrg = false;
			}
		}
		ValidateResult vResult = new ValidateResult();
		if (!isEmptyOrg) {
			vResult.addErrorMessage(3, "填报单位填写不完整，请确保填报单位为连续的！");
			updateTicketErrorMsg(ticketId, vResult, TicketState.EXCEPTIONSTOP);
			return true;
		}

		PropertyDict orgLevelDict = propertyDictService
				.getPropertyDictById(converter.getUploadOrganization()
						.getOrgLevel().getId());
		String userOrgInternalCode = organizationDubboService.getSimpleOrgById(
				ThreadVariable.getUser().getOrganization().getId())
				.getOrgInternalCode();
		String orgInternalCode = converter.getUploadOrganization()
				.getOrgInternalCode();
		Long userOrgLeval = organizationDubboService
				.getSimpleOrgById(
						ThreadVariable.getUser().getOrganization().getId())
				.getOrgLevel().getId();
		int userOrgLevalId = propertyDictService.getPropertyDictById(
				userOrgLeval).getInternalId();
		int orgOrgLevalId = orgLevelDict.getInternalId();
		/**
		 * 如果当前用户的层级和“导入的数据表头的层级”是同一层级，但不相同是不允许导入的
		 */
		if (userOrgLevalId == orgOrgLevalId
				&& !userOrgInternalCode.equals(orgInternalCode)) {
			return addError(vResult);
		}
		/**
		 * 当当前用户是网格层级时，而导入的数据表头不是是到 村 层级 不允许导入的
		 */
		if (userOrgLevalId < orgOrgLevalId
				&& orgOrgLevalId != OrganizationLevel.VILLAGE) {
			return addError(vResult);
		}

		/**
		 * 当当前用户的层级高于导入数据的层级时，如果导入数据的层级不在当前用户的层级的管辖内是不允许导入的
		 */
		if (userOrgLevalId > orgOrgLevalId) {
			orgInternalCode = orgInternalCode.substring(0,
					userOrgInternalCode.length());
			if (!userOrgInternalCode.equals(orgInternalCode)) {
				return addError(vResult);
			}
		}

		if (dataType.equals("leaderGroupData") || dataType.equals("massesData")
				|| dataType.equals("postulantData")) {
			if (!(orgLevelDict.getInternalId() <= OrganizationLevel.DISTRICT)) {
				vResult.addErrorMessage(3, "填报单位必须填至县/区一级或以下层级");
				updateTicketErrorMsg(ticketId, vResult,
						TicketState.EXCEPTIONSTOP);
				return true;
			}
		} else if (dataType.equals("grassRootsPartyData")
				|| dataType.equals("autonomyOrgData")) {
			if (!(orgLevelDict.getInternalId() <= OrganizationLevel.VILLAGE)) {
				vResult.addErrorMessage(3, "填报单位必须填至社区/村一级或以下层级");
				updateTicketErrorMsg(ticketId, vResult,
						TicketState.EXCEPTIONSTOP);
				return true;
			}
		} else {
			if ("newSocietyOrganizations".equals(dataType)) {
				return false;
			} else if (dataType.endsWith("Temp")) {
				if (!(orgLevelDict.getInternalId() <= OrganizationLevel.DISTRICT)) {
					vResult.addErrorMessage(3, "填报单位必须填至县/区一级或以下层级");
					updateTicketErrorMsg(ticketId, vResult,
							TicketState.EXCEPTIONSTOP);
					return true;
				}
			} else if (!(orgLevelDict.getInternalId() <= OrganizationLevel.VILLAGE)) {
				vResult.addErrorMessage(3, "填报单位必须填至社区/村一级或以下层级");
				updateTicketErrorMsg(ticketId, vResult,
						TicketState.EXCEPTIONSTOP);
				return true;
			}

		}

		return false;
	}

	private boolean addError(ValidateResult vResult) {
		vResult.addErrorMessage(3, "权限越界，不能将数据导入到该网格下！");
		updateTicketErrorMsg(ticketId, vResult, TicketState.EXCEPTIONSTOP);
		return true;
	}

	private boolean isOrgNull(Organization uploadOrg) {
		if (uploadOrg.getId() == null) {
			updateErrorTitleAndRowMsg("处理文件时出错，程序已终止，详情参见下方错误信息列表", 3, "["
					+ uploadOrg.getOrgName() + "]填报单位填写错误");
			return true;
		}
		return false;
	}

	private Organization getOrgFromData(String[][] data) {
		// 用户导入模板没有版本号
		int dataLength = UserImportHelper.isUserImport(dataType) ? ExcelImportHelper.USER_IMPORT_DATA
				: ExcelImportHelper.IMPORT_DATA;

		if (data.length < dataLength) {
			return null;
		}

		Organization result = organizationDubboService.getRootOrganization();
		int index = 1;
		String orgName;
		while (index < data[dataLength - 1].length
				&& !isEmptyString(data[dataLength - 1][index])) {
			orgName = data[dataLength - 1][index].trim();
			index = index + 2;
			// if (orgName.equals(result.getOrgName())) {
			// continue;
			// } else {
			result = getOrganizationsByOrgNameAndParentId(result.getId(),
					orgName);
			if (result == null) {
				result = new Organization();
				result.setOrgName(orgName);
				result.setId(null);
				break;
			}
			// }
		}

		return (!UserImportHelper.isUserImport(dataType) && result != null && result
				.getId() != null) ? organizationDubboService.getFullOrgById(result
				.getId()) : result;
	}

	private Organization getOrganizationsByOrgNameAndParentId(Long parentId,
			String orgName) {
		List<Organization> results = organizationDubboService
				.findOrganizationsByOrgNameAndParentId(null, orgName, parentId);
		return (results != null && results.size() == 1) ? results.get(0) : null;
	}

	private boolean isEmptyString(String value) {
		return value == null || value.trim().equals("");
	}

	private boolean validateTemplateVersion(String templates,
			String uploadTemplateVersion) {
		if (!ImportTemplatesSetting.getImportTemplatesVo(templates)
				.getVersion().equals(uploadTemplateVersion)) {
			updateErrorTitleAndRowMsg("导入模板版本不正确，导入已终止", 0,
					"您目前使用的数据导入模板不正确,请按上面提示下载最新数据模板");
			return true;
		}
		return false;
	}

	private boolean validateParames(String dataType, String fileUrl,
			ExcelData excelDatas, int firstDataRow) {
		return isDataTypeLegitimate(dataType) && isExcel(fileUrl)
				&& isTotalRowLesserThan1000(excelDatas, firstDataRow);
	}

	private boolean isConverterNull(DataConvert converter) {
		if (converter == null) {
			updateErrorTitleAndRowMsg("打开文件出错，程序已终止，详情参见下方错误信息列表", 0,
					"不能导入此格式数据,沒有找到对应的dataConverter");
			return true;
		}
		return false;
	}

	private boolean isTotalRowLesserThan1000(ExcelData excelDatas,
			int firstDataRow) {
		totalRow = excelDatas.getSheetDatas(ExcelData.FIRST).length
				- firstDataRow + 1;
		if (totalRow > 40001) {
			updateErrorTitleAndRowMsg("导入数据时出错，程序已终止，详情参见下方错误信息列表", 0,
					"导入数据记录行数不能大于40000行!");
			return false;
		}
		return true;
	}

	private void updateErrorTitleAndRowMsg(String title, int row, String msg) {
		updateTicketMessage(ticketId, "{msg:'" + title + "'}");
		ValidateResult vResult = new ValidateResult();
		vResult.addErrorMessage(row, msg);
		updateTicketErrorMsg(ticketId, vResult, TicketState.EXCEPTIONSTOP);
		validateErrorOccur = true;
	}

	private boolean isDataTypeLegitimate(String dataType) {
		if (!StringUtil.isStringAvaliable(dataType)
				|| DataConvertDefine.getConvertBeanDefine(dataType) == null) {
			updateErrorTitleAndRowMsg("打开文件出错，程序已终止，详情参见下方错误信息列表", 0,
					"不能导入此格式数据,沒有找到对应的dataConverter");
			return false;
		}
		return true;
	}

	private DataConvert getDataConvert(String dataType,
			ApplicationContext appContext) {
		return (DataConvert) appContext.getBean(DataConvertDefine
				.getConvertBeanDefine(dataType));
	}

	private void finishTransaction(TransactionStatus status) {
		if (validateErrorOccur) {
			rollbackTransaction(status);
		} else {
			commitTransaction(status);
		}
	}

	private void commitTransaction(TransactionStatus status) {
		jtaTransactionManager.commit(status);
	}

	private void rollbackTransaction(TransactionStatus status) {
		jtaTransactionManager.rollback(status);
	}

	private boolean isExcel(String fileUrl) {
		if (!"xls".equals(fileUrl.substring(fileUrl.lastIndexOf(".") + 1))) {
			updateErrorTitleAndRowMsg("上传文件出错，程序已终止，详情参见下方错误信息列表", 0,
					"只能选择后缀为.xls格式的文件");
			return false;
		}
		return true;
	}

	private void updateTicketInfo(String ticketId, String description,
			int currentRow, int totalRow, Integer state) {
		DataImportTicket ticket = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		ticket.setState(state);
		ticket.setDescription(description);
		if (ticket.getFailureTime() != null
				&& ticket.getFailureTime() < System.currentTimeMillis()) {
			ticket.setDescription("{msg:'请求超时'}");
			ticket.setState(TicketState.TIMEOUT);
		}
		ticket.setCurrentRowCount(currentRow);
		ticket.setTotalRowCount(totalRow);
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	private void updateTicketErrorMsg(String ticketId, ValidateResult vresult,
			Integer state) {
		if (vresult.hasError()) {
			DataImportTicket ticket = (DataImportTicket) ticketService
					.getTicketById(ticketId);
			ticket.setState(state);
			if (ticket.getFailureTime() != null
					&& ticket.getFailureTime() < System.currentTimeMillis()) {
				ticket.setDescription("{msg:'请求超时'}");
				ticket.setState(TicketState.TIMEOUT);
			}
			ticket.appendErrorMsgs(vresult.getMessages());
			ticketService.updateTicket(ticket,
					TicketService.DEFAULT_EXPRIED_SECOND);
		}
	}

	private void updateTicketMessage(String ticketId, String description) {
		DataImportTicket ticket = (DataImportTicket) ticketService
				.getTicketById(ticketId);
		ticket.setDescription(description);
		if (ticket.getFailureTime() != null
				&& ticket.getFailureTime() < System.currentTimeMillis()) {
			ticket.setDescription("{msg:'请求超时'}");
			ticket.setState(TicketState.TIMEOUT);
		}
		ticketService
				.updateTicket(ticket, TicketService.DEFAULT_EXPRIED_SECOND);
	}

	private FileInputStream openFile(String url) {
		logger.info("正在打开文件:{} .....", url);
		File file = new File(url);
		if (file == null || !file.exists()) {
			logger.error("文件{}不存在!", url);
			return null;
		} else {
			try {
				return new FileInputStream(file);
			} catch (FileNotFoundException e) {
				return null;
			}
		}
	}

	private void createThreadUser() {
		ThreadVariable.setSession(session);
		User user = new User();
		user.setId(session.getUserId());
		user.setOrganization(session.getOrganization());
		ThreadVariable.setUser(user);
		ThreadVariable.setSourcesState(ConstantsProduct.SourcesState.IMPORT);
	}

	private int getNextStep() {
		return currentStep++;
	}

	private TransactionStatus beginTransaction() {
		return jtaTransactionManager.getTransaction(createTransacationDefine());
	}

	private DefaultTransactionDefinition createTransacationDefine() {
		DefaultTransactionDefinition result = new DefaultTransactionDefinition();
		result.setTimeout(12000);
		result.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		return result;
	}

	public boolean isIntercept() {
		return isIntercept;
	}

	public void setIntercept(boolean isIntercept) {
		this.isIntercept = isIntercept;
	}

}
