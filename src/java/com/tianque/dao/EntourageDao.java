package com.tianque.dao;

import java.util.List;

import com.tianque.domain.Entourage;

public interface EntourageDao {

	public Entourage addEntourage(Entourage entourage);

	public Entourage getEntourageById(Long id);

	public List<Entourage> findEntourageForPageByTrampResidentId(Long trampResidentId, String sidx,
			String sord);

	public Entourage updateEntourage(Entourage updateEntourage);

	public int deleteEntourageById(Long id);

	public Entourage getEntourageByIdCard(List<String> list, Long trampResidentId);

}
