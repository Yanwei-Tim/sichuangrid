package com.tianque.datatransfer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("excelTemplateHelper")
public class ExcelTemplateHelper {

	public final static Logger logger = LoggerFactory
			.getLogger(ExcelTemplateHelper.class);

	private static PropertyDictService propertyDictService;

	protected static OrganizationDubboService organizationDubboService;

	@Autowired
	public void setPropertyDictService(PropertyDictService propertyDictService) {
		ExcelTemplateHelper.propertyDictService = propertyDictService;
	}

	@Autowired
	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		ExcelTemplateHelper.organizationDubboService = organizationDubboService;
	}

	public static HSSFWorkbook exportTemplate(String[][] excelDefines)
			throws Exception {
		HSSFWorkbook workbook = new HSSFWorkbook();
		DataExportHelper helper = constructDataExportHelper(
				propertyDictService, organizationDubboService);
		procHSSFWorkbook(workbook, excelDefines);
		// writeHeaderToExcel(writer, helper, excelDefines, objectList);
		return workbook;
	}

	private static void procHSSFWorkbook(HSSFWorkbook workbook,
			String[][] excelDefines) {
		HSSFSheet sheet = workbook.createSheet();
		HSSFRow row = sheet.createRow((short) 1);
		HSSFCell cell = row.createCell((short) 1);

		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setWrapText(true);

		HSSFFont font = defineFont(workbook, (short) 10,
				HSSFFont.BOLDWEIGHT_BOLD, HSSFColor.RED.index, "Arial", false,
				false);
		HSSFFont font1 = defineFont(workbook, (short) 10,
				HSSFFont.BOLDWEIGHT_BOLD, HSSFColor.BLUE.index, "Arial", false,
				false);

		HSSFRichTextString ts = new HSSFRichTextString("hello\r\nworld!");
		ts.applyFont(0, 9, font);
		ts.applyFont(10, ts.length() - 1, font1);
		cell.setCellStyle(cellStyle);
		cell.setCellValue(ts);
	}

	private static DataExportHelper constructDataExportHelper(
			PropertyDictService propertyDictService,
			OrganizationDubboService organizationDubboService) {
		DataExportHelper helper = new DataExportHelper();
		helper.setOrganizationService(organizationDubboService);
		helper.setPropertyDictService(propertyDictService);
		return helper;
	}

	/**
	 * 字体设置
	 * 
	 * @param workbook
	 * @param heigth
	 *            字体高度
	 * @param weight
	 *            宽度
	 * @param color
	 *            字体颜色
	 * @param fontName
	 *            字体
	 * @param italic
	 *            是否使用斜体
	 * @param strikeout
	 *            是否使用划线
	 */
	private static HSSFFont defineFont(HSSFWorkbook workbook, short heigth,
			short weight, short color, String fontName, Boolean italic,
			Boolean strikeout) {
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(heigth);
		font.setColor(color);
		font.setFontName(fontName);
		font.setBoldweight(weight);
		font.setItalic(italic);
		font.setStrikeout(strikeout);
		return font;
	}
}
