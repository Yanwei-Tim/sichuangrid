package com.tianque.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.tianque.core.base.AbstractBaseDao;
import com.tianque.domain.SignificantIssuedeal;
import com.tianque.working.dao.SignificantIssuedealAttachFileDao;
import com.tianque.working.dao.SignificantIssuedealDao;
import com.tianque.working.domain.SignificantIssuedealAttachFiles;

@Repository("recoveryAttachFilesDaoImpl")
public class RecoveryAttachFilesDaoImpl extends AbstractBaseDao {
	@Autowired
	private SignificantIssuedealDao significantIssuedealDao;
	@Autowired
	private SignificantIssuedealAttachFileDao significantIssuedealAttachFileDao;

	public void operatingData() {
		List<Long> disputeIssuedealIds = significantIssuedealAttachFileDao
				.findAllNotDuplicateSignificantIssuedealIds();
		List<SignificantIssuedeal> significantIssuedeals = null;

		for (Long disputeIssuedealId : disputeIssuedealIds) {
			significantIssuedeals = new ArrayList<SignificantIssuedeal>();
			getSignificantIssuedealList(disputeIssuedealId, significantIssuedeals);
			addSignificantIssuedealAttachFiles(disputeIssuedealId, significantIssuedeals);
		}
	}

	private void getSignificantIssuedealList(Long disputeIssuedealId,
			List<SignificantIssuedeal> significantIssuedeals) {
		SignificantIssuedeal significantIssuedeal = significantIssuedealDao
				.getSignificantIssuedealByFromId(disputeIssuedealId);

		if (significantIssuedeal == null) {
			return;
		}

		significantIssuedeals.add(significantIssuedeal);
		getSignificantIssuedealList(significantIssuedeal.getId(), significantIssuedeals);
	}

	private void addSignificantIssuedealAttachFiles(Long disputeIssuedealId,
			List<SignificantIssuedeal> significantIssuedeals) {
		if (significantIssuedeals == null || significantIssuedeals.size() == 0) {
			return;
		}

		List<SignificantIssuedealAttachFiles> attachFiles = significantIssuedealAttachFileDao
				.getSimpleSignificantIssuedealAttachFilesByDisputeIssuedealId(disputeIssuedealId);

		for (SignificantIssuedeal significantIssuedeal : significantIssuedeals) {
			for (SignificantIssuedealAttachFiles significantIssuedealAttachFiles : attachFiles) {
				significantIssuedealAttachFiles
						.setSignificantIssuedealWorkingRecord(significantIssuedeal);
				significantIssuedealAttachFileDao
						.addSignificantIssuedealAttachFiles(significantIssuedealAttachFiles);
			}
		}
	}

}
