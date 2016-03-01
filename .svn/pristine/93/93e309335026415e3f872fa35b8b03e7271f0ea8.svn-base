package com.tianque.plugin.account.toexcel;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianque.core.base.BaseDomain;
import com.tianque.core.util.FileUtil;
import com.tianque.plugin.account.domain.Agriculture;
import com.tianque.plugin.account.domain.Education;
import com.tianque.plugin.account.domain.Energy;
import com.tianque.plugin.account.domain.Environment;
import com.tianque.plugin.account.domain.Labor;
import com.tianque.plugin.account.domain.LedgerPeopleAspirations;
import com.tianque.plugin.account.domain.Medical;
import com.tianque.plugin.account.domain.Other;
import com.tianque.plugin.account.domain.Science;
import com.tianque.plugin.account.domain.ThreeRecordsIssueLogNew;
import com.tianque.plugin.account.domain.Town;
import com.tianque.plugin.account.domain.Traffic;
import com.tianque.plugin.account.domain.WaterResources;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class AccountExportHelper {
	private static String dir = "resource/datatemplate/";
	private static String ledgerPoorPeopleXml="ledgerPoorPeople.xml";
	private static String ledgerSteadyWorkXml="ledgerSteadyWork.xml";
	public static Map<Class,String> map=new HashMap<Class,String>();
	static{
		map.put(Medical.class, "ledgerMedical.xml");
		map.put(Town.class, "ledgerTown.xml");
		map.put(Environment.class, "ledgerEnvironment.xml");
		map.put(Traffic.class, "ledgerTraffic.xml");
		map.put(Education.class, "ledgerEducation.xml");
		map.put(Science.class, "ledgerScience.xml");
		map.put(Labor.class, "ledgerLabor.xml");
		map.put(Energy.class, "ledgerEnergy.xml");
		map.put(Agriculture.class, "ledgerAgriculture.xml");
		map.put(Other.class, "ledgerOther.xml");
		map.put(WaterResources.class, "ledgerWaterResources.xml");
	}
	public static InputStream exportDateToExcel(
			LedgerPeopleAspirations peopleAspirations,
			BaseDomain domain) throws Exception{
		Map dataMap = getDataMap(peopleAspirations,domain);
		Version version = new Version("2.3.22");
		Configuration configuration = new Configuration(version);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setDirectoryForTemplateLoading(new File(FileUtil.getWebRoot()
				+ File.separator+dir));
		Template t = null;
		t = configuration.getTemplate(map.get(domain.getClass()));
		Writer out = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		out = new OutputStreamWriter(output);
		t.process(dataMap, out);
		return new ByteArrayInputStream(output.toByteArray());
	}
	
	/**
	 * 困难台账
	 */
	public static InputStream exportLedgerPoorPeopleDateToExcel(Map dataMap) throws Exception{
		Version version = new Version("2.3.22");
		Configuration configuration = new Configuration(version);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setDirectoryForTemplateLoading(new File(FileUtil.getWebRoot()
				+ File.separator+dir));
		Template t = null;
		t = configuration.getTemplate(ledgerPoorPeopleXml);
		Writer out = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		out = new OutputStreamWriter(output);
		t.process(dataMap, out);
		return new ByteArrayInputStream(output.toByteArray());
	}
	/**
	 * 稳定台账
	 */
	public static InputStream exportLedgerSteadyDateToExcel(Map dataMap) throws Exception{
		Version version = new Version("2.3.22");
		Configuration configuration = new Configuration(version);
		configuration.setDefaultEncoding("UTF-8");
		configuration.setDirectoryForTemplateLoading(new File(FileUtil.getWebRoot()
				+ File.separator+dir));
		Template t = null;
		t = configuration.getTemplate(ledgerSteadyWorkXml);
		Writer out = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		out = new OutputStreamWriter(output);
		t.process(dataMap, out);
		return new ByteArrayInputStream(output.toByteArray());
	}
	
	private static Map getDataMap(LedgerPeopleAspirations peopleAspirations,
			BaseDomain domain) {
		List<ThreeRecordsIssueLogNew> last3Logs = new ArrayList();
		List<ThreeRecordsIssueLogNew> issueDealLogs = peopleAspirations.getIssueDealLogs();
     	int j = 0;
     	//取出最新三条处理记录
     	if(issueDealLogs!=null&&issueDealLogs.size()!=0){
	     	for (int i = issueDealLogs.size() - 1; i > 0; i--) {
	     		if (j == 3){
	     			break;
	     		}
	     		ThreeRecordsIssueLogNew log = issueDealLogs.get(i);
	     		if (log.getDealType() == null)
	     			continue;
	     		if (log.getCompleteType() != null) {
	     			last3Logs.add(log);
	     			++j;
	     		} else if (log.getTargeOrg() != null) {
	     			++j;
	     			last3Logs.add(log);
	     		}
	     	}
     	}
		Map map=new HashMap();
		map.put("p", peopleAspirations);
		map.put("last3Logs",last3Logs);
		map.put("domain", domain);
		return map;
	}
}
