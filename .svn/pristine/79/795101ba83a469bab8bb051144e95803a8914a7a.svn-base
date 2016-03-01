package com.tianque.service.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.util.FileUtil;
import com.tianque.service.FileTransfer;

/**
 * 平台内消息文件转存处理策略
 */
@Service("personnelMessageFileTransFer")
@Transactional
public class PersonnelMessageFileTransFer implements FileTransfer {

	private static Logger logger = LoggerFactory.getLogger(PersonnelMessageFileTransFer.class);

	@Override
	public boolean removeTargetFile(String sourceDir) {
		File file = new File(FileUtil.getWebRoot() + File.separator + sourceDir);
		if (file.exists())
			return file.delete();
		else {
			throw new RuntimeException("目标文件不存在");
		}
	}

}
