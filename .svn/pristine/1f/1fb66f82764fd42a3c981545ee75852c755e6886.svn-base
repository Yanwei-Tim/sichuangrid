package com.tianque.datatransfer;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import ognl.Ognl;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.core.datatransfer.DataType;
import com.tianque.core.util.CalendarUtil;
import com.tianque.domain.Organization;
import com.tianque.domain.PropertyDict;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.sysadmin.service.OrganizationDubboService;
import com.tianque.sysadmin.service.PropertyDictService;

@Component("excelExportHelper")
public class ExcelExportHelper {

	public final static Logger logger = LoggerFactory
			.getLogger(ExcelExportHelper.class);

	private static PropertyDictService propertyDictService;

	protected static OrganizationDubboService organizationDubboService;

	protected static Map<Long, String> orgMap = new HashMap<Long, String>();

	protected static Map<Long, String> dictMap = new HashMap<Long, String>();

	@Autowired
	public void setPropertyDictService(PropertyDictService propertyDictService) {
		ExcelExportHelper.propertyDictService = propertyDictService;
	}

	@Autowired
	public void setOrganizationDubboService(
			OrganizationDubboService organizationDubboService) {
		ExcelExportHelper.organizationDubboService = organizationDubboService;
	}

	public static InputStream exportDateToExcel(String[][] excelDefines,
			List objectList) throws Exception {
		ExcelWriter writer = constructExcelWriter();
		writer.addWorkSheet(excelDefines[0][2]);
		DataExportHelper helper = constructDataExportHelper(
				propertyDictService, organizationDubboService);
		writeHeaderToExcel(writer, helper, excelDefines, objectList);
		writeDateToExcel(writer, helper, excelDefines, objectList);
		return new java.io.FileInputStream(writer.getExcelFile(UUID
				.randomUUID() + ".xls"));
	}

	/**
	 * 写头信息
	 * 
	 * @param writer
	 * @param helper
	 * @param excelColumArray
	 * @param objList
	 *            @
	 */
	public static void writeHeaderToExcel(ExcelWriter writer,
			DataExportHelper helper, String[][] excelColumArray, List objList) {
		int startRow = 0;
		int endRow = 0;
		int k = 1;
		try {
			for (int j = 0; j < excelColumArray.length; j++) {
				if (j == 0) {// 写标题
					writeTitle(writer, excelColumArray, j);
				} else {// 写头
					if (StringUtils.isEmpty(excelColumArray[j][1])) { // 写跨列头标题
						k++;
						startRow = Integer.parseInt(excelColumArray[j][0]);
						endRow = Integer.parseInt(excelColumArray[j][7]);
						writeIsMergerHeaderTitle(writer, excelColumArray, j);
					} else { // 普通头信息
						if (startRow > 0
								&& (j - k >= startRow && j - k <= endRow)) {// 写跨列头信息
							writeIsMergerHeader(writer, excelColumArray, j);
						} else {
							writeHeader(writer, excelColumArray, j);
						}
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelExportHelper的writeHeaderToExcel方法出现异常，原因：",
					"向excel模板写头信息出现错误", e);
		}
	}

	/**
	 * 写标题
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeTitle(ExcelWriter writer,
			String[][] excelColumArray, int j) {
		if ("true".equals(excelColumArray[j][5])) {// 采用默认格式
			appendTableHead(
					writer,
					0,
					0,
					excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					ExcelStyleConstant.defalTitleStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalTitleStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalTitleStyle[5]),
					true);
		} else {
			appendTableHead(writer, 0, 0, excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					excelColumArray[j][8],
					Integer.parseInt(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]),
					Boolean.parseBoolean(excelColumArray[j][12]),
					Boolean.parseBoolean(excelColumArray[j][13]), true);
		}
	}

	/**
	 * 写普通头信息
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeHeader(ExcelWriter writer,
			String[][] excelColumArray, int j) {
		if ("true".equals(excelColumArray[j][5])) {
			appendTableHead(writer, 1, Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2], 2,
					Integer.parseInt(excelColumArray[j][0]),
					ExcelStyleConstant.defalHeadStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalHeadStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[5]),
					false);
		} else {
			appendTableHead(writer, 1, Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2], 2,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][6],
					Integer.parseInt(excelColumArray[j][7]),
					Boolean.parseBoolean(excelColumArray[j][8]),
					Boolean.parseBoolean(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]), false);
		}
	}

	/**
	 * 写普跨列头信息
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeIsMergerHeader(ExcelWriter writer,
			String[][] excelColumArray, int j) {
		if ("true".equals(excelColumArray[j][5])) {
			appendNoMergerTableHead(writer, 2,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					ExcelStyleConstant.defalHeadStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalHeadStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[5]));
		} else {
			appendNoMergerTableHead(writer, 2,
					Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2], excelColumArray[j][6],
					Integer.parseInt(excelColumArray[j][7]),
					Boolean.parseBoolean(excelColumArray[j][8]),
					Boolean.parseBoolean(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]));
		}
	}

	/**
	 * 写跨列头标题
	 * 
	 * @param writer
	 * @param excelColumArray
	 * @param j
	 */
	private static void writeIsMergerHeaderTitle(ExcelWriter writer,
			String[][] excelColumArray, int j) {
		if ("true".equals(excelColumArray[j][5])) {
			appendTableHead(writer, 1, Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					ExcelStyleConstant.defalHeadStyle[0],
					Integer.parseInt(ExcelStyleConstant.defalHeadStyle[1]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[2]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[3]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[4]),
					Boolean.parseBoolean(ExcelStyleConstant.defalHeadStyle[5]),
					true);
		} else {
			appendTableHead(writer, 1, Integer.parseInt(excelColumArray[j][0]),
					excelColumArray[j][2],
					Integer.parseInt(excelColumArray[j][6]),
					Integer.parseInt(excelColumArray[j][7]),
					excelColumArray[j][8],
					Integer.parseInt(excelColumArray[j][9]),
					Boolean.parseBoolean(excelColumArray[j][10]),
					Boolean.parseBoolean(excelColumArray[j][11]),
					Boolean.parseBoolean(excelColumArray[j][12]),
					Boolean.parseBoolean(excelColumArray[j][13]), true);
		}
	}

	private static void appendNoMergerTableHead(ExcelWriter writer, int row,
			int column, String propertys, String fontName, int size,
			boolean bold, boolean italic, boolean underLine, boolean deleteLine) {
		writer.addCell(row, column, propertys).setFont(fontName, size, bold,
				italic, underLine, deleteLine);
	}

	private static void appendTableHead(ExcelWriter writer, int row,
			int column, String propertys, int endRow, int endColumn,
			String fontName, int size, boolean bold, boolean italic,
			boolean underLine, boolean deleteLine, boolean isMerger) {
		if (isMerger) {
			writer.addCell(row, column, propertys)
					.mergeTo(endRow, endColumn)
					.setFont(fontName, size, bold, italic, underLine,
							deleteLine)
					.setHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_CENTER);
		} else {
			writer.addCell(row, column, propertys)
					.mergeTo(endRow, endColumn)
					.setFont(fontName, size, bold, italic, underLine,
							deleteLine);
		}
	}

	/**
	 * 写数据
	 * 
	 * @param writer
	 * @param helper
	 * @param excelColumArray
	 * @param objList
	 *            @
	 * @throws NumberFormatException
	 */
	public static void writeDateToExcel(ExcelWriter writer,
			DataExportHelper helper, String[][] excelColumArray, List objList)
			throws NumberFormatException {
		for (int i = 0; i < objList.size(); i++) {
			for (int j = 1; j < excelColumArray.length; j++) {

				appendRow(writer, helper, objList, i,
						Integer.parseInt(excelColumArray[j][0]),
						excelColumArray[j][1], excelColumArray[j][3],
						objList.get(i));
			}
		}
	}

	/**
	 * 向excel模板写内容
	 * 
	 * @param writer
	 * @param helper
	 * @param excelColumArray
	 * @param objList
	 *            @
	 */
	private static void appendRow(ExcelWriter writer, DataExportHelper helper,
			List objList, int row, int seq, String propertyName,
			String propertyType, Object object) {
		try {

			if (!StringUtils.isEmpty(propertyType)
					&& DataType.DATA_TYPE_DICT.equals(propertyType)) {

				PropertyDict propertyDict = ((PropertyDict) Ognl.getValue(
						propertyName, object));
				if (null != propertyDict && propertyDict.getId() != null
						&& propertyDict.getId() > 0) {
					if (dictMap.get(propertyDict.getId()) == null) {
						dictMap.put(propertyDict.getId(), propertyDictService
								.getPropertyDictById(propertyDict.getId())
								.getDisplayName());
					}
					writer.addCell((row + 3), seq,
							dictMap.get(propertyDict.getId()));
				}
			} else if (!StringUtils.isEmpty(propertyType)
					&& DataType.DATA_TYPE_ORG.equals(propertyType)) {
				Organization organization = (Organization) Ognl.getValue(
						propertyName, object);
				if (null != organization) {

					if (orgMap.get(organization.getId()) == null) {
						orgMap.put(organization.getId(), helper
								.getOrganizationRelativeName(organization
										.getId()));
					}

					writer.addCell((row + 3), seq,
							orgMap.get(organization.getId()));
				}
			} else if (!StringUtils.isEmpty(propertyType)
					&& DataType.DATA_TYPE_DICT_LIST.equals(propertyType)) {
				List<PropertyDict> list = (List<PropertyDict>) Ognl.getValue(
						propertyName, object);
				if (null != list && list.size() > 0) {
					StringBuffer sb = new StringBuffer();
					for (PropertyDict pd : list) {
						sb.append(propertyDictService.getPropertyDictById(
								pd.getId()).getDisplayName());
						if (pd.getId().longValue() != list.get(list.size() - 1)
								.getId().longValue()) {
							sb.append("、");
						}
					}
					writer.addCell((row + 3), seq, sb.toString());
				}
			} else if (propertyName.split("\\.").length == 2) {
				String[] propertiesArray = propertyName.split("\\.");
				String data = "";
				if (null != Ognl.getValue(propertiesArray[0], object)) {
					data = String.valueOf(BeanUtils.getProperty(object,
							propertyName) == null ? "" : BeanUtils.getProperty(
							object, propertyName));
					String specData = procSpecialTypeData(propertyName,
							propertyType, object);
					if (!StringUtils.isEmpty(specData)) {
						data = specData;
					}
				}
				writer.addCell((row + 3), seq, data);
			} else if (!StringUtils.isEmpty(propertyType)
					&& DataType.DATA_TYPE_MATCHINGTOORG.equals(propertyType)) {
				String data = "";
				if (!".".equals(String.valueOf(Ognl.getValue(propertyName,
						object)))) {
					data = "是";
				} else {
					data = "否";
				}
				writer.addCell((row + 3), seq, data);
			} else {
				if (!StringUtils.isEmpty(propertyName)) {
					// BeanUtils.getProperty(object, propertyName);
					// String data = String.valueOf(Ognl.getValue(propertyName,
					// object));
					String data = String.valueOf(BeanUtils.getProperty(object,
							propertyName));
					String specData = procSpecialTypeData(propertyName,
							propertyType, object);
					if (!StringUtils.isEmpty(specData)) {
						data = specData;
					}
					// if (null != Ognl.getValue(propertyName, object)) {
					if (null != BeanUtils.getProperty(object, propertyName)) {
						writer.addCell((row + 3), seq, data);
					}
				}
			}
		} catch (Exception e) {
			throw new ServiceValidationException(
					"类ExcelExportHelper的appendRow方法出现异常，原因：",
					"向excel模板写数据出现错误", e);
		}
	}

	private static String procSpecialTypeData(String propertyName,
			String propertyType, Object object) throws Exception {
		String data = "";
		if (DataType.DATA_TYPE_DATE.equals(propertyType)) {
			data = CalendarUtil.format((Date) Ognl.getValue(propertyName,
					object));
		}
		if (DataType.DATA_TYPE_BOOLEAN.equals(propertyType)) {
			if ("1".equals(String.valueOf(Ognl.getValue(propertyName, object)))
					|| "true".equals(String.valueOf(Ognl.getValue(propertyName,
							object)))) {
				data = "是";
			} else {
				data = "否";
			}
		}
		if (DataType.DATA_TYPE_BOOLEANS.equals(propertyType)) {
			if ("1".equals(String.valueOf(Ognl.getValue(propertyName, object)))
					|| "true".equals(String.valueOf(Ognl.getValue(propertyName,
							object)))) {
				data = "有";
			} else {
				data = "无";
			}
		}
		if (DataType.DATA_TYPE_COUNTS.equals(propertyType)) {
			if ((Long) Ognl.getValue(propertyName, object) >= 1) {
				data = "有";
			} else {
				data = "无";
			}
		}
		return data;
	}

	private static DataExportHelper constructDataExportHelper(
			PropertyDictService propertyDictService,
			OrganizationDubboService organizationDubboService) {
		DataExportHelper helper = new DataExportHelper();
		helper.setOrganizationService(organizationDubboService);
		helper.setPropertyDictService(propertyDictService);
		return helper;
	}

	private static ExcelWriter constructExcelWriter() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}

	public static ExcelWriter constructExcelWriterTemp() {
		ExcelWriter writer = new ExcelWriter();
		writer.setDefaultFont("宋体", (short) 12, false, false, false, false);
		writer.setDefaultCellBorder(true);
		writer.setDefaultHorizontalAlignment(ExcelWriter.HORIZONTAL_ALIGN_LEFT);
		writer.setDefaultVerticalAlignment(ExcelWriter.VERTICAL_ALIGN_CENTER);
		return writer;
	}
}
