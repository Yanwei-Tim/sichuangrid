package com.tianque.fourTeams.fourTeamsIssue.validator;

import java.util.List;

import com.tianque.core.validate.DomainValidator;
import com.tianque.core.validate.ValidateResult;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAttachFile;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueNew;

public interface FourTeamsIssueValidator extends DomainValidator<FourTeamsIssueNew> {

	ValidateResult validateAttachFiles(List<FourTeamsIssueAttachFile> files);
}
