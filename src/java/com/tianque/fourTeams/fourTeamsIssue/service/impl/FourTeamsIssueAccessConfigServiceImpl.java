package com.tianque.fourTeams.fourTeamsIssue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.exception.base.BusinessValidationException;
import com.tianque.fourTeams.fourTeamsIssue.dao.FourTeamsIssueAccessConfigDao;
import com.tianque.fourTeams.fourTeamsIssue.domain.FourTeamsIssueAccessConfig;
import com.tianque.fourTeams.fourTeamsIssue.service.FourTeamsIssueAccessConfigService;
import com.tianque.fourTeams.fourTeamsIssue.state.FourTeamsIssueOperate;

@Transactional
@Service("fourTeamsIssueAccessConfigService")
public class FourTeamsIssueAccessConfigServiceImpl implements
		FourTeamsIssueAccessConfigService {
	@Autowired
	private FourTeamsIssueAccessConfigDao issueAccessConfigDao;

	@Override
	public FourTeamsIssueAccessConfig addIssueAccessConfig(
			FourTeamsIssueAccessConfig bean) {
		validateParam(bean);
		if (getIssueAccessConfig() != null) {
			return issueAccessConfigDao.updateIssueAccessConfig(bean);
		} else {
			return issueAccessConfigDao.addIssueAccessConfig(bean);
		}
	}

	@Override
	public FourTeamsIssueAccessConfig getIssueAccessConfig() {
		return issueAccessConfigDao.getIssueAccessConfig();
	}

	@Override
	public Double getIssueScoresConfig(FourTeamsIssueOperate issueOperate) {
		if (issueOperate == null || issueOperate.getCode() <= 0) {
			throw new BusinessValidationException("获取事件处理分数失败！");
		}
		double cent = 0;
		switch (issueOperate.getCode()) {
		case 1:
			cent = 0;
			break;// 办理中
		case 11:
			cent = getScores().getNormalScores().doubleValue();
			break;// 上报指挥中心
		case 21:
			cent = getScores().getNormalScores().doubleValue();
			break;// 交办
		case 26:
			cent = getScores().getNormalScores().doubleValue();
			break;
		case 31:
			cent = getScores().getNormalScores().doubleValue();
			break;// 结案
		case 32:
			cent = 0;
			break;// 备案
		case 41:
			cent = getScores().getNormalScores().doubleValue();
			break;// 上报
		case 51:
			cent = 0;
			break;// 领导批示
		case 61:
			cent = 0;
			break;// 受理
		case 71:
			cent = 0;
			break;// 阅读
		case 81:
			cent = 0;
			break;// 普通督办
		case 83:
			cent = getScores().getYellowCardScores().doubleValue();
			break;// 黄牌督办
		case 86:
			cent = getScores().getRedCardScores().doubleValue();
			break;// 红牌督办
		case 88:
			cent = 0;
			break;// 取消督办
		case 91:
			cent = 0;
			break;// 反馈
		case 101:
			cent = getScores().getNormalScores().doubleValue();
			break;// 完成
		case 111:
			cent = 0;
			break;// 关闭
		case 200:
			cent = 0;
			break;// 回退
		case 1001:
			cent = 0;
			break;// 加急
		case 1011:
			cent = 0;
			break;// 取消加急
		case 1101:
			cent = 0;
			break;// 设置历史遗留
		case 1111:
			cent = 0;
			break;// 取消历史遗留
		default:
			cent = 0;
		}
		return cent;
	}

	private FourTeamsIssueAccessConfig getScores() {
		FourTeamsIssueAccessConfig config = getIssueAccessConfig();
		if (config == null) {
			config = new FourTeamsIssueAccessConfig();
		}
		return config;
	}

	private void validateParam(FourTeamsIssueAccessConfig bean) {
		if (bean == null) {
			throw new BusinessValidationException("操作失败，请联系管理员！");
		}
		if (bean.getNormalScores() == null) {
			throw new BusinessValidationException("正常办理加分不能为空！");
		}
		if (bean.getRedCardAccepted() == null) {
			throw new BusinessValidationException("红牌受理期限不能为空！");
		}
		if (bean.getRedCardHandle() == null) {
			throw new BusinessValidationException("红牌处理期限不能为空！");
		}
		if (bean.getRedCardScores() == null) {
			throw new BusinessValidationException("红牌扣分不能为空！");
		}
		if (bean.getYellowCardAccepted() == null) {
			throw new BusinessValidationException("黄牌受理期限不能为空！");
		}
		if (bean.getYellowCardHandle() == null) {
			throw new BusinessValidationException("黄牌处理期限不能为空！");
		}
		if (bean.getYellowCardScores() == null) {
			throw new BusinessValidationException("黄牌扣分不能为空！");
		}
	}

}
