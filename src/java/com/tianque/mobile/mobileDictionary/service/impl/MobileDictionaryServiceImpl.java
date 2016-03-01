package com.tianque.mobile.mobileDictionary.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.exolab.core.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.core.util.CalendarUtil;
import com.tianque.core.util.FileUtil;
import com.tianque.core.util.GridProperties;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.User;
import com.tianque.exception.base.ServiceValidationException;
import com.tianque.exception.base.SystemUtilException;
import com.tianque.mobile.mobileDictionary.dao.MobileDictionaryDao;
import com.tianque.mobile.mobileDictionary.dao.PropertyDictLogDao;
import com.tianque.mobile.mobileDictionary.domain.MobileDictionary;
import com.tianque.mobile.mobileDictionary.domain.PropertyDictLog;
import com.tianque.mobile.mobileDictionary.domain.vo.MobileDictionaryVo;
import com.tianque.mobile.mobileDictionary.service.MobileDictionaryService;

@Service("mobileDictionaryService")
public class MobileDictionaryServiceImpl implements MobileDictionaryService {

	@Autowired
	private MobileDictionaryDao mobileDictionaryDao;
	@Autowired
	private PropertyDictLogDao propertyDictLogDao;
	//获取全部的数据字典
	private static final Integer ALL=1;
	//获取增量的数据字典
	private static final Integer INCREMENT=2;
//	private MobileDictionary md;

	public static final String fileName = "mobileDictionary";
	
	public static final String incrementFileName = "incrementMobileDictionary";

	public MobileDictionaryServiceImpl() {
		if (ThreadVariable.getSession() == null) {
			try {
				init();
			} catch (Exception e) {
				throw new ServiceValidationException("初始化手机端字典项文件配置用户失败", e);
			}
		}
	}

	@Override
	public List<MobileDictionary> findAllMobileDictionary(MobileDictionary mobileDictionary) throws Exception {
		return mobileDictionaryDao.findMobileDictionaryLists(mobileDictionary);
	}

	@Override
	public void addOrUpdateMobileDictionary() throws Exception {
		addDictsToFile(ALL);
	}

	private String createFile(Integer type,Date renewDate) throws Exception {
		String fileStr=fileName;
		if(INCREMENT.equals(type)){
			fileStr=incrementFileName;
		}
		writeDataToFile(getDictionarys(type,renewDate), fileStr);
		//清空增量文件内容
		if(ALL.equals(type)){
			writeDataToFile("{}", incrementFileName);
		}
		String path = File.separator + GridProperties.UPLOAD_FILE_FOLDER
				+ File.separator + fileName + File.separator + fileStr
				+ ".txt";
		return path;

	}

	private void writeDataToFile(String dictionarys,String fileStr){
		File tempfileFolder = new File(FileUtil.getWebRoot() + File.separator
				+ "uploadFile" + File.separator + "upload" + File.separator
				+ fileName + File.separator);
		if (!tempfileFolder.exists() && !tempfileFolder.isDirectory()) {
			tempfileFolder.mkdirs();
		}

		File uploadFile = new File(FileUtil.getWebRoot() + File.separator
				+ "uploadFile" + File.separator + "upload" + File.separator
				+ fileName + File.separator + fileStr + ".txt");
		if (uploadFile.isFile() && uploadFile.exists()) {
			uploadFile.delete();
		}
		PrintWriter pw = null;
		try {

			pw = new PrintWriter(new FileOutputStream(uploadFile.getPath()));
			pw.write(dictionarys);

		} catch (Exception e) {

			throw new SystemUtilException("操作异常", e);

		} finally {
			if (pw != null) {
				pw.flush();
				pw.close();
			}
		}

	}
	
	private String getDictionarys(Integer type,Date renewDate) throws Exception {
		JSONObject jsonObj_ALL = new JSONObject();

		Map<String, List<Map>> dataMap = getMapData(type,renewDate);

		jsonObj_ALL.putAll(dataMap);
		return jsonObj_ALL.toString();
	}

	private Map<String, List<Map>> getMapData(Integer type,Date renewDate) throws Exception {
		List<MobileDictionaryVo> dataList =null;
		//如果是全部的
		if(ALL.equals(type)){
			dataList= mobileDictionaryDao.findAllDictsForList();
			//如果是增量的话则把最近的全部的那条作为参照时间点
		}else{
			List<MobileDictionary> lists = mobileDictionaryDao
					.findMobileDictionaryLists(createMobileDictionaryByType(ALL));
			if(lists==null ||lists.size()==0){
				throw new ServiceException("没有手机总的数据字典，不能添加手机增量数据字典！");
			}
			renewDate=lists.get(0).getRenewDate();
			dataList=mobileDictionaryDao
					.findIncrementDictsForList(renewDate);
		}
		
		Map<String, List<Map>> resultMap = new HashMap<String, List<Map>>();

		for (MobileDictionaryVo vo : dataList) {
			String domainName = vo.getDomainName();

			Map tempMap = new HashMap();
			tempMap.put("id", vo.getId());
			tempMap.put("displayName", vo.getDisplayName());
			tempMap.put("internalId", vo.getInternalId());
			tempMap.put("domainId", vo.getDomainId());
			if(INCREMENT.equals(type)){
				if(vo.getOperateType()==3){
					tempMap.put("operate", 3);
				}else if(vo.getCreateDate()!=null&&vo.getCreateDate().getTime()>renewDate.getTime()){
					tempMap.put("operate", 1);
				}else{
					tempMap.put("operate", 2);
				}
			}
			List<Map> tempList = resultMap.get(domainName);
			if (tempList == null) {// 从未添加过该大类，进行大类添加处理
				List<Map> list = new ArrayList<Map>();
				list.add(tempMap);
				resultMap.put(domainName, list);
			} else {// 小类添加
				tempList.add(tempMap);
			}
		}

		return resultMap;
	}
	
	private MobileDictionary createMobileDictionaryByType(Integer type){
		MobileDictionary dictionary=new MobileDictionary();
		dictionary.setType(type);
		return dictionary;
	}

	public void init() throws Exception {
		Session session = new Session();
		session.setUserName("admin");
		session.setUserRealName("超级管理员");
		session.setAccessIp("127.0.0.1");
		session.setAccessTime(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLoginDate(CalendarUtil.now("yyyy-MM-dd hh:mm:ss"));
		session.setLogin(true);
		session.setUserId(1L);
		session.setSessionId(UUID.randomUUID().toString());
		Organization org = new Organization();
		org.setId(1L);
		org.setOrgInternalCode("1.1.");
		session.setOrganization(org);
		session.setOrgInternalCode("1.1.");
		User user = new User();
		user.setUserName("admin");
		user.setOrganization(org);
		ThreadVariable.setUser(user);
		ThreadVariable.setSession(session);
	}

	@Override
	public void addOrUpdateMobileDictionaryIncrement() throws Exception {
		addDictsToFile(INCREMENT);
	}
	
	private void addDictsToFile(Integer type) throws Exception {
		int val = mobileDictionaryDao.countMobileDictionary(type);
		MobileDictionary md=new MobileDictionary();
		if (val > 0) {// 已有值，做修改操作
			List<MobileDictionary> lists = mobileDictionaryDao
					.findMobileDictionaryLists(createMobileDictionaryByType(type));
			md = lists.get(0);
			//如果是增量的且没有更新直接返回
			if(!hasIncrement(type,md.getRenewDate())){
				return;
			}
			md.setFileUrl(createFile(type,md.getRenewDate()));
			md.setVersion(md.getVersion() + 1l);
			md.setRenewDate(new Date());
			md.setType(type);
			mobileDictionaryDao.updateMobileDictionary(md);
		} else {
			md = new MobileDictionary();
			md.setFileUrl(createFile(type,null));
			md.setVersion(1l);
			md.setRenewDate(new Date());
			md.setType(type);
			mobileDictionaryDao.addMobileDictionary(md);
		}
	}
	//判断是否有增量数据字典
	private boolean hasIncrement(Integer type,Date renewDate){
//		if(INCREMENT.equals(type)&&mobileDictionaryDao
//				.findAllDictsForList(renewDate).size()==0){
//			return false;
//		}
		PropertyDictLog log=new PropertyDictLog();
		log.setCreateDate(renewDate);
		if(INCREMENT.equals(type)&&propertyDictLogDao
				.countPropertyDictLogsByParams(log)==0){
			return false;
		}
		return true;
	}
}
