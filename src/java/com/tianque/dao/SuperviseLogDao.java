package com.tianque.dao;

import java.util.List;

import com.tianque.domain.SuperviseLog;

public interface SuperviseLogDao {

	SuperviseLog addSuperviseLog(SuperviseLog superviseLog);

	List<SuperviseLog> findErrorSuperviseLogs(Long dealType, Long overType, String superviseType);

	SuperviseLog getSuperviseLogById(Long id);

	SuperviseLog updateSuperviseLogSuccess(Long id);

}
