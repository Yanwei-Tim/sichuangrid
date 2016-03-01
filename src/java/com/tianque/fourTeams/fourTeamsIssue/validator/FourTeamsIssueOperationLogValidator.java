package com.tianque.fourTeams.fourTeamsIssue.validator;

import java.util.List;

import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueLogNew;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;

public interface FourTeamsIssueOperationLogValidator {
	ValidateResult validate(FourTeamsIssueOperate operate, FourTeamsIssueLogNew log,
			List<FourTeamsIssueAttachFile> attachFiles);
}
