package com.tianque.dao.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tianque.dao.FileManagerDao;

@Repository("fileManagerDao")
public class FileManagerDaoImpl implements FileManagerDao {

	private static Logger logger = LoggerFactory.getLogger(FileManagerDaoImpl.class);

	private String baseDir = "./";

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	private String getDirName(String dir) {
		String dirname = this.baseDir + File.separator + dir;
		return dirname;
	}

	@Override
	public boolean deleteDir(String dir) {
		String dirname = getDirName(dir);
		this.nestingEmptyDir(dirname);
		return new File(dirname).delete();
	}

	public void emptyDir(String dir) {
		String dirname = getDirName(dir);
		this.nestingEmptyDir(dirname);

	}

	private void nestingEmptyDir(String abosulatePath) {
		File[] files = new File(abosulatePath).listFiles();
		if (files == null)
			return;
		for (int i = 0; i < files.length; ++i) {
			if (files[i].isDirectory()) {
				this.nestingEmptyDir(files[i].getPath());
			}
			files[i].delete();
		}
	}

	@Override
	public boolean updateDir(String sourceDir, String targetDir) {
		String sdir = this.getDirName(sourceDir);
		String tdir = this.getDirName(targetDir);
		return new File(sdir).renameTo(new File(tdir));
	}

	public boolean hasAccessIniFile(String parentPath) {
		File file = new File(parentPath + File.separator + "access.ini");
		if (file.exists())
			return true;
		return false;
	}

}
