package com.tianque.resourcePool.help;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.resourcePool.domain.DirectorySetting;
import com.tianque.resourcePool.domain.MyProfile;
import com.tianque.resourcePool.domain.MyProfileAttachFile;
import com.tianque.working.domain.DailyLogAttachFile;
import com.tianque.working.domain.Document;
import com.tianque.working.domain.WorkingRecord;

@Component("parseToMyProfile")
@Transactional
public class ParseToMyProfile {

	public static MyProfile parse(WorkingRecord workingRecord,
			Long resourcePoolTypeId, Long sendMessage) {
		MyProfile result = new MyProfile();
		DirectorySetting directorySetting = new DirectorySetting();
		directorySetting.setId(Long.valueOf(resourcePoolTypeId));
		result.setName(workingRecord.getName());
		result.setContent(workingRecord.getContent());
		result.setSource(MyProfile.SOURCE_FROM_WORKING);
		result.setSendMessage(sendMessage);
		result.setResourcePoolType(directorySetting);

		List<DailyLogAttachFile> dailyLogAttachFile = workingRecord
				.getDailyLogAttachFile();
		List<MyProfileAttachFile> myProfileAttachFile = new ArrayList<MyProfileAttachFile>();
		if (dailyLogAttachFile != null) {
			for (DailyLogAttachFile daily : dailyLogAttachFile) {
				MyProfileAttachFile file = new MyProfileAttachFile();
				file.setFileName(daily.getFileName());
				file.setFileActualUrl(daily.getFileActualUrl());
				file.setFileSize(daily.getFileSize());
				file.setMyPrifile(result);
				myProfileAttachFile.add(file);
			}
			result.setMyProfileAttachFile(myProfileAttachFile);
		}

		return result;
	}

	public static MyProfile parse(Document document, Long resourcePoolTypeId,
			Long sendMessage) {
		MyProfile result = new MyProfile();
		DirectorySetting directorySetting = new DirectorySetting();
		directorySetting.setId(Long.valueOf(resourcePoolTypeId));
		result.setName(document.getTitle());
		result.setReleaseUnit(document.getDispatchUnit());
		result.setReleaseTime(document.getDispatchDate());
		result.setFileNo(document.getDocumentNo());
		result.setDocumentSubject(document.getTheme());
		result.setContent(document.getContents());
		result.setSource(MyProfile.SOURCE_FROM_DOCUMENT);
		result.setSendMessage(sendMessage);
		result.setResourcePoolType(directorySetting);

		return result;
	}
}
