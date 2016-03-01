package com.tianque.threeRecordsIssue.dataTrans.dataConvert;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.tianque.account.api.PeopleAspirationDubboService;
import com.tianque.core.util.StringUtil;
import com.tianque.core.validate.ValidateResult;
import com.tianque.plugin.account.domain.Education;

@Component("educationDataConverter")
@Scope("prototype")
public class EducationDataConverter extends
		ThreeAbstractDataConverter<Education> {

	@Autowired
	private PeopleAspirationDubboService peopleAspirationDubboService;

	@Override
	public ValidateResult validate(Education domain, int realRow) {
		return null;
	}

	@Override
	public Education persistenceDomain(Education domain) {
		if (StringUtil.isStringAvaliable(domain.getFromAddress())) {
			JSONArray jsonArray = (JSONArray) JSONSerializer.toJSON(domain
					.getFromAddress());
			String fromAddress = "";
			String toAddress = "";
			if (jsonArray != null) {
				List list = (List) JSONSerializer.toJava(jsonArray);
				JSONObject jsonObject = null;
				for (int i = 0; i < list.size(); i++) {
					jsonObject = JSONObject.fromObject(list.get(i));
					if (jsonObject != null) {
						String name = (String) jsonObject.get("Name");
						if (i == 0) {
							fromAddress = name;
						}
						if (i != 0) {
							toAddress += name;
						}
						if (i != list.size() - 1 && i != 0) {
							toAddress += ">";
						}
					}
				}
			}
			domain.setFromAddress(fromAddress);
			domain.setToAddress(toAddress);
		}
		return peopleAspirationDubboService.addEducation(domain);
	}

	@Override
	public Education updateDomain(Education domain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Education convertToDomain(String[] cellValues, ValidateResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
