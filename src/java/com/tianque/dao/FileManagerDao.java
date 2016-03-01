package com.tianque.dao;

public interface FileManagerDao {

	public boolean updateDir(String sourceDir, String targetDir);

	public boolean deleteDir(String dir);

}
