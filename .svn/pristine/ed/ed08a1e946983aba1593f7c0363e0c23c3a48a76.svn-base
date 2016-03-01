package com.tianque.core.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.controller.annotation.SolrServerIndex;
import com.tianque.core.base.BaseDomain;
import com.tianque.core.datatransfer.ExcelImportHelper;
import com.tianque.jms.OperateMode;
import com.tianque.jms.msg.PopulationSolrIndexMsg;
import com.tianque.jms.sender.ActiveMQMessageSender;
import com.tianque.plugin.tqSearch.service.TqQueryService;

@Component
@Aspect
public class SolrIndexAspect {
	private Logger logger = LoggerFactory.getLogger(SolrIndexAspect.class);

	@Autowired
	private ActiveMQMessageSender activeMQMessageSender;
	@Autowired
	private TqQueryService tqQueryService;
	/**
	 * @param @param call
	 * @param @throws Throwable
	 * @return Object
	 * @throws
	 */
	@Around("@annotation(com.tianque.controller.annotation.SolrServerIndex)")
	public Object get(ProceedingJoinPoint call) throws Throwable{
		Object retVal = call.proceed();
		try {
			SolrServerIndex  anno = getAnnotation(call, SolrServerIndex.class);
			Object[] args = call.getArgs();
			editSolrIndex(anno,retVal,args);
		} catch (Exception e) {
			logger.error("SolrIndexAspect更新Solr索引出错：",e);
		}
		return retVal;
	}
	
	public void editSolrIndex(SolrServerIndex anno,Object returnVal,Object parameter) throws Exception {
		String indexType = anno.value();
		OperateMode mode = anno.mode();
		if(OperateMode.EDIT.equals(mode)){
			tqQueryService.updateIndex(indexType,parameter);
		}else if(OperateMode.DELETE.equals(mode)){
			tqQueryService.deleteIndex(indexType,parameter);
		}
	}
	
	/**
	 * @Description: 获得Annotation对象
	 * @throws
	 */
	private <T extends Annotation> T getAnnotation(ProceedingJoinPoint jPoint,
			Class<T> clazz){
		Annotation annotation =null;
		try {
			MethodSignature joinPointObject = (MethodSignature) jPoint.getSignature();
			annotation = joinPointObject.getMethod().getAnnotation(clazz);
			if(annotation==null){
				Object[] args = jPoint.getArgs();
				Class[] parameterClass = new Class[args.length];
				for (int i = 0; i < parameterClass.length; i++) {
					Class pclass = args[i].getClass();
					if(pclass.equals(ArrayList.class)){
						parameterClass[i] = List.class;
					}else if(pclass.equals(HashMap.class)){
						parameterClass[i] = Map.class;
					}else{
						parameterClass[i] = pclass;
					}
				}
				String methodName =joinPointObject.getName();
				String classType = jPoint.getTarget().getClass().getName();
				Class<?> className = Class.forName(classType);
				Method method = className.getMethod(methodName, parameterClass);
				if(method.isAnnotationPresent(clazz)){
					return (T) method.getAnnotation(clazz);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    return (T) annotation;
	}
	
	private void editPopulationSolrIndex(SolrServerIndex anno,Object retVal,Object temp){
		if(ExcelImportHelper.isImport.get()){
			ExcelImportHelper.isUpdateSolrIndex.set(true);
			if(ExcelImportHelper.importIdRange.get() == null){
				ExcelImportHelper.importIdRange.set(new HashMap<String, HashMap<String,Long>>());
			}
			updateImportIdRange(ExcelImportHelper.importIdRange.get(), ((BaseDomain) retVal).getId(), anno.value());
			return;
		}
		try {
			PopulationSolrIndexMsg populationSolrIndexMsg = new PopulationSolrIndexMsg();
			populationSolrIndexMsg.setMode(anno.mode());
			populationSolrIndexMsg.setObjectType(anno.value());
			if(OperateMode.ADD.equals(anno.mode())){
				fillPopulationSolrIndexMsg(populationSolrIndexMsg,((Countrymen)retVal).getId(),
						((Countrymen)retVal).getBaseInfoId());
			}
			if(OperateMode.EDIT.equals(anno.mode())){
				fillPopulationSolrIndexMsg(populationSolrIndexMsg,((Countrymen)temp).getId(),
						((Countrymen)temp).getBaseInfoId());
			}
			if(OperateMode.CANCEL_DEATH.equals(anno.mode())){
				fillPopulationSolrIndexMsg(populationSolrIndexMsg,null,(Long)temp);
			}
			if(OperateMode.DELETE.equals(anno.mode())){
				List<Long> list = new ArrayList<Long>();
				if(temp instanceof Long[]){
					Long[] longs = (Long[]) temp;
					for (int i = 0; null != longs && i < longs.length; i++) {
						   list.add(longs[i]);
					}
				}else if(temp instanceof List){
					list = (List<Long>) temp;
				}else if(temp instanceof Countrymen){
					list.add(((Countrymen) temp).getId());
				}else if(temp instanceof Long){
					list.add((Long)temp);
				}
				populationSolrIndexMsg.setDeleteIds(list);
			}
			activeMQMessageSender.send(populationSolrIndexMsg);
		} catch (Exception e) {
			logger.error("更新统一搜索索引失败："+e);
		}
	}
	
	private PopulationSolrIndexMsg fillPopulationSolrIndexMsg(PopulationSolrIndexMsg 
			populationSolrIndexMsg ,Long objectId,Long baseinfoId){
		populationSolrIndexMsg.setObjectId(objectId);
		populationSolrIndexMsg.setBaseinfoId(baseinfoId);
		return populationSolrIndexMsg;
	}
	
	private void updateImportIdRange(Map<String,HashMap<String,Long>> mm,Long dd,String type){
		if(mm.get(type) == null){
			HashMap<String,Long> hm1 =  new HashMap<String, Long>();
			hm1.put("startId", dd);
			hm1.put("endId", dd);
			mm.put(type, hm1);
		}else{
			HashMap<String,Long> hm1 = mm.get(type);
			if(hm1.get("startId") > dd){
				hm1.put("startId", dd);
			}
			if(hm1.get("endId") < dd){
				hm1.put("endId", dd);
			}
		}
		
	}
}
