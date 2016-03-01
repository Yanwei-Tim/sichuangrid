package com.tianque.service;

import java.util.List;

import com.tianque.domain.Entourage;

public interface EntourageService {

	public Entourage addEntourage(Entourage entourage);

	public Entourage getEntourageById(Long id);

	public boolean hasDuplicateEntourage(Long trampResidentId, String idCardNo, Long exceptedId);

	public int deleteEntourageById(Long id);

	public Entourage updateEntourage(Entourage entourage);

	public List<Entourage> findEntourageForPageByTrampResidentId(Long trampResidentId, String sidx,
			String sord);

}
