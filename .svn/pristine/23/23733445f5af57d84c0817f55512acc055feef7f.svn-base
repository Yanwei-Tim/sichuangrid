package com.tianque.controller;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.controller.annotation.PermissionFilter;
import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.PartyConstructionFiles;
import com.tianque.service.PartyConstructionFilesService;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Namespace("/partyBuilding/partyConstructionFilesManage")
@Transactional
@Scope("request")
@Controller("partyConstructionFilesController")
public class PartyConstructionFilesController extends BaseAction {
	@Autowired
	private PartyConstructionFilesService partyConstructionFilesService;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	private PartyConstructionFiles partyConstructionFiles;
	private String ids;
	private Long organizationId;
	private List<PartyConstructionFiles> partyConstructionFilesList;

	private String type;
	private String currentLine;
	private Integer pageNum;
	private Boolean hasDuplicatePartyConstructionFiles;
	private String errorMessage;

	@Action(value = "dispatchOperate", results = {
			@Result(name = "success", location = "/partyBuilding/partyFiles/partyConstructionFilesDlg.jsp"),
			@Result(name = "view", location = "/baseinfo/partyConstruction/PartyConstructionFiles/partyConstructionFilesViewDlg.jsp"),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String dispatchOperate() throws Exception {
		if (null != partyConstructionFiles
				&& null != partyConstructionFiles.getId()) {
			partyConstructionFiles = partyConstructionFilesService
					.getPartyConstructionFilesById(partyConstructionFiles
							.getId());
			partyConstructionFiles.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							partyConstructionFiles.getOrganization().getId(),
							organizationDubboService));
			organizationId = partyConstructionFiles.getOrganization().getId();
		}
		if (DialogMode.VIEW_MODE.equals(mode)) {
			return "view";
		}
		return SUCCESS;
	}

	@PermissionFilter(ename = "findpartyConstructionFiles")
	@Action(value = "findpartyConstructionFiles", results = { @Result(type = "json", name = "success", params = {
			"root", "partyConstructionFilesList", "ignoreHierarchy", "false" }) })
	public String findpartyConstructionFiles() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<PartyConstructionFiles>());
			partyConstructionFilesList = new ArrayList<PartyConstructionFiles>();
			return SUCCESS;
		} else {
			PageInfo<PartyConstructionFiles> pageInfo;
			pageInfo = ControllerHelper.processAllOrgRelativeName(
					partyConstructionFilesService
							.findPartyConstructionFilesByOrgId(organizationId,
									null, null, "createDate", "desc", null),
					organizationDubboService, new String[] { "organization" },
					organizationId);
			gridPage = new GridPage(pageInfo);
			partyConstructionFilesList = pageInfo.getResult();
		}
		return SUCCESS;
	}

	@Action(value = "list", results = { @Result(name = "success", location = "/partyBuilding/partyFiles/partyConstructionFilesDetail.jsp") })
	public String partyConstructionFilesList() throws Exception {
		if (null == organizationId) {
			gridPage = new GridPage(new PageInfo<PartyConstructionFiles>());
			partyConstructionFilesList = new ArrayList<PartyConstructionFiles>();
			return SUCCESS;
		} else {
			PageInfo<PartyConstructionFiles> pageInfo;
			if ("".equals(type) || "null".equals(type)) {
				type = null;
			}
			pageInfo = ControllerHelper.processAllOrgRelativeName(
					partyConstructionFilesService
							.findPartyConstructionFilesByOrgId(organizationId,
									page, rows, "createDate", "desc", type),
					organizationDubboService, new String[] { "organization" },
					organizationId);
			gridPage = new GridPage(pageInfo);
			partyConstructionFilesList = pageInfo.getResult();
		}

		return SUCCESS;
	}

	// private String convent(Integer i){
	// if(i==null){
	// return null;
	// }
	// switch (i) {
	// case 1:
	// return "党建制度";
	// case 2:
	// return "党建计划";
	// case 3:
	// return "考核评审";
	// case 4:
	// return "会议文件";
	// default:
	// return null;
	// }
	// }

	@Action(value = "addPartyConstructionFiles", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyConstructionFiles", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String addPartyConstructionFiles() throws Exception {
		partyConstructionFiles = partyConstructionFilesService
				.addPartyConstructionFiles(partyConstructionFiles);
		partyConstructionFiles.getOrganization().setOrgName(
				ControllerHelper.getOrganizationRelativeName(
						partyConstructionFiles.getOrganization().getId(),
						organizationDubboService));
		return SUCCESS;

	}

	@Action(value = "editPartyConstructionFiles", results = {
			@Result(name = "success", type = "json", params = { "root",
					"partyConstructionFiles", "ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String updatePartyConstructionFiles() throws Exception {
		if (null != partyConstructionFiles
				&& null != partyConstructionFiles.getId()) {
			partyConstructionFiles = partyConstructionFilesService
					.updatePartyConstructionFiles(partyConstructionFiles);
			partyConstructionFiles.getOrganization().setOrgName(
					ControllerHelper.getOrganizationRelativeName(
							partyConstructionFiles.getOrganization().getId(),
							organizationDubboService));

		}
		return SUCCESS;
	}

	@Action(value = "deletePartyConstructionFiles", results = {
			@Result(name = "success", type = "json", params = { "root", "ids",
					"ignoreHierarchy", "false" }),
			@Result(name = "error", type = "json", params = { "root",
					"errorMessage" }) })
	public String deletePartyConstructionFiles() throws Exception {
		String[] deleteId = ids.split(",");
		List<Long> idList;
		if (deleteId[0].equals("")) {
			idList = initTargetId(deleteId, 1);
		} else {
			idList = initTargetId(deleteId, 0);
		}
		partyConstructionFilesService
				.deletePartyConstructionFilessByIdList(idList);
		return SUCCESS;
	}

	private List<Long> initTargetId(String[] targetIds, int size) {
		List<Long> idLongs = new ArrayList<Long>();
		for (int i = size; i < targetIds.length; i++) {
			String tempId = targetIds[i];
			if (size == 0) {
				idLongs.add(Long.parseLong(targetIds[i]));
			} else {
				idLongs.add(Long.parseLong(tempId));
			}
		}
		return idLongs;
	}

	@Action(value = "downloadFile", results = { @Result(name = "success", type = "stream", params = {
			"contentType", "application/msword;charset=ISO8859-1", "inputName",
			"inputStream", "contentDisposition",
			"attachment;filename=\"${downloadFileName}\"", "bufferSize", "4096" }) })
	public String downloadFile() throws Exception {
		if (null == partyConstructionFiles
				|| null == partyConstructionFiles.getId()) {
			this.errorMessage = "参数错误";
			return ERROR;
		}
		partyConstructionFiles = partyConstructionFilesService
				.getPartyConstructionFilesById(partyConstructionFiles.getId());
		String html = "<html><body>" + partyConstructionFiles.getContent()
				+ "</body></html>";
		inputStream = new ByteArrayInputStream(html.getBytes());
		downloadFileName = new String(partyConstructionFiles.getTitle()
				.getBytes("gbk"), "ISO8859-1");
		return SUCCESS;
	}

	@Action(value = "searchPartyConstructionFiles", results = { @Result(type = "json", name = "success", params = {
			"root", "partyConstructionFilesList", "ignoreHierarchy", "false" }) })
	public String searchPartyConstructionFiles() throws Exception {
		if (organizationId == null) {
			partyConstructionFilesList = new ArrayList<PartyConstructionFiles>();
		} else {
			if ("请输入标题信息".equals(partyConstructionFiles.getTitle().trim())) {
				partyConstructionFiles.setTitle(null);
			}
			partyConstructionFilesList = ControllerHelper
					.processAllOrgRelativeName(partyConstructionFilesService
							.findPartyConstructionFilesByorgIdAndTitle(
									organizationId,
									partyConstructionFiles.getTitle(), null,
									"createDate", "desc"), organizationDubboService,
							new String[] { "organization" }, organizationId);
		}
		return SUCCESS;
	}

	@Action(value = "hasDuplicatePartyConstructionFiles", results = { @Result(type = "json", name = "success", params = {
			"root", "hasDuplicatePartyConstructionFiles", "ignoreHierarchy",
			"false" }) })
	public String hasDuplicatePartyConstructionFiles() throws Exception {
		if (organizationId == null) {
			errorMessage = "组织机构ID不能为空";
			return ERROR;
		} else {
			if (partyConstructionFiles.getTitle() != null) {
				hasDuplicatePartyConstructionFiles = partyConstructionFilesService
						.hasDuplicatePartyConstructionFilesByTitle(
								partyConstructionFiles.getTitle(),
								organizationId, partyConstructionFiles.getId());
			}
			return SUCCESS;
		}
	}

	@Action(value = "viewFile", results = { @Result(name = "success", location = "/partyBuilding/partyFiles/partyConstructionFilesArticle.jsp") })
	public String viewFile() throws Exception {

		if (null == partyConstructionFiles
				|| null == partyConstructionFiles.getId()) {
			this.errorMessage = "";
			return ERROR;
		}
		partyConstructionFiles = partyConstructionFilesService
				.getPartyConstructionFilesById(partyConstructionFiles.getId());

		// if
		// (!"txt".equals(partyConstructionFiles.getFileName().split("\\.")[1]))
		// {
		// currentLine = "不支持此类文件显示,请直接下载";
		// return SUCCESS;
		// }
		// try {
		// String url = FileUtil.getWebRoot() + File.separator
		// + partyConstructionFiles.getUrl();
		// String type = partyConstructionFiles.getFileName().split("\\.")[1];
		// inputStream = new java.io.FileInputStream(url);
		// if ("doc".equals(type)) {
		//
		// currentLine = WordToHtml.getWordAndStyle(url);
		//
		// } else if ("docx".equals(type)) {
		// OPCPackage opcPackage = POIXMLDocument.openPackage(url);
		// POIXMLTextExtractor ex = new XWPFWordExtractor(opcPackage);
		// currentLine = ex.getText();
		// } else if ("xls".equals(type)) {
		// currentLine = extractTextFromXLS(inputStream);
		// } else if ("xlsx".equals(type)) {
		// currentLine = extractTextFromXLS2007(url);
		// } else if("pdf".equals(type)){
		// currentLine =getTextFromPdf(url);
		// }
		// else {
		// String line = "";
		// currentLine = "&nbsp;&nbsp;&nbsp;&nbsp;";
		// BufferedReader breader = new BufferedReader(
		// new InputStreamReader(inputStream, "gbk"));
		// while ((line = breader.readLine()) != null) {
		// currentLine = currentLine + line + "\n";
		// }
		//
		// if (null != breader)
		// breader.close();
		// }
		// if (null != inputStream)
		// inputStream.close();
		// } catch (FileNotFoundException e) {
		// logger.error("downloadDailyLogAttachFile 错误", e);
		// throw new RuntimeException("文件打开失败!");
		// } catch (UnsupportedEncodingException uee) {
		// logger.error("downloadDailyLogAttachFile 错误", uee);
		// throw new RuntimeException("文件打开失败!");
		// } catch (IOException ioe) {
		// logger.error("downloadDailyLogAttachFile 错误", ioe);
		// throw new RuntimeException("文件读取失败!");
		// } catch (Exception ee) {
		// logger.error("downloadDailyLogAttachFile 错误", ee);
		// throw new RuntimeException("word文件读取失败!");
		// }

		return SUCCESS;
	}

	/**
	 * @Method: extractTextFromXLS
	 * @Description: 从excel 2003文档中提取纯文本
	 * @param
	 * @return String
	 * @throws
	 */
	@SuppressWarnings("deprecation")
	private static String extractTextFromXLS(InputStream is) throws IOException {
		StringBuffer content = new StringBuffer();
		HSSFWorkbook workbook = new HSSFWorkbook(is); // 创建对Excel工作簿文件的引用
		for (int numSheets = 0; numSheets < workbook.getNumberOfSheets(); numSheets++) {
			if (null != workbook.getSheetAt(numSheets)) {
				HSSFSheet aSheet = workbook.getSheetAt(numSheets); // 获得一个sheet
				for (int rowNumOfSheet = 0; rowNumOfSheet <= aSheet
						.getLastRowNum(); rowNumOfSheet++) {
					if (null != aSheet.getRow(rowNumOfSheet)) {
						HSSFRow aRow = aSheet.getRow(rowNumOfSheet); // 获得一行
						for (short cellNumOfRow = 0; cellNumOfRow <= aRow
								.getLastCellNum(); cellNumOfRow++) {
							if (null != aRow.getCell(cellNumOfRow)) {
								HSSFCell aCell = aRow.getCell(cellNumOfRow); // 获得列值
								if (aCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
									content.append(aCell.getNumericCellValue());
								} else if (aCell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
									content.append(aCell.getBooleanCellValue());
								} else {
									content.append(aCell.getStringCellValue());
								}
							}
						}
					}
				}
			}
		}
		return content.toString();
	}

	/**
	 * @Method: extractTextFromXLS2007
	 * @Description: 从excel 2007文档中提取纯文本
	 * @param
	 * @return String * @throws
	 */
	private static String extractTextFromXLS2007(String fileName)
			throws Exception {
		StringBuffer content = new StringBuffer(); // 构造 XSSFWorkbook 对象，strPath
		// 传入文件路径
		XSSFWorkbook xwb = new XSSFWorkbook(fileName); // 循环工作表Sheet
		for (int numSheet = 0; numSheet < xwb.getNumberOfSheets(); numSheet++) {
			XSSFSheet xSheet = xwb.getSheetAt(numSheet);
			if (xSheet == null) {
				continue;
			} // 循环行Row
			for (int rowNum = 0; rowNum <= xSheet.getLastRowNum(); rowNum++) {
				XSSFRow xRow = xSheet.getRow(rowNum);
				if (xRow == null) {
					continue;
				}
				// 循环列Cell
				for (int cellNum = 0; cellNum <= xRow.getLastCellNum(); cellNum++) {
					XSSFCell xCell = xRow.getCell(cellNum);
					if (xCell == null) {
						continue;
					}
					if (xCell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
						content.append(xCell.getBooleanCellValue());
					} else if (xCell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
						content.append(xCell.getNumericCellValue());
					} else {
						content.append(xCell.getStringCellValue());
					}
				}
			}
		}
		return content.toString();

	}

	/**
	 * @param filePath
	 *            文件路径
	 * @return 读出的pdf的内容
	 */
	public String getTextFromPdf(String filePath) throws Exception {
		String result = null;
		FileInputStream is = null;
		PDDocument document = null;
		try {
			is = new FileInputStream(filePath);
			PDFParser parser = new PDFParser(is);
			parser.parse();
			document = parser.getPDDocument();
			PDFTextStripper stripper = new PDFTextStripper();
			result = stripper.getText(document);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (document != null) {
				try {
					document.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	public PartyConstructionFiles getPartyConstructionFiles() {
		return partyConstructionFiles;
	}

	public void setPartyConstructionFiles(
			PartyConstructionFiles partyConstructionFiles) {
		this.partyConstructionFiles = partyConstructionFiles;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public List<PartyConstructionFiles> getPartyConstructionFilesList() {
		return partyConstructionFilesList;
	}

	public void setPartyConstructionFilesList(
			List<PartyConstructionFiles> partyConstructionFilesList) {
		this.partyConstructionFilesList = partyConstructionFilesList;
	}

	public Boolean getHasDuplicatePartyConstructionFiles() {
		return hasDuplicatePartyConstructionFiles;
	}

	public void setHasDuplicatePartyConstructionFiles(
			Boolean hasDuplicatePartyConstructionFiles) {
		this.hasDuplicatePartyConstructionFiles = hasDuplicatePartyConstructionFiles;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCurrentLine() {
		return currentLine;
	}

	public void setCurrentLine(String currentLine) {
		this.currentLine = currentLine;
	}

}
