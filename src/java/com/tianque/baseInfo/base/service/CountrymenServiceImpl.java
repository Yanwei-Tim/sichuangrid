package com.tianque.baseInfo.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianque.baseInfo.base.dao.CountrymenDao;
import com.tianque.baseInfo.base.domain.Countrymen;
import com.tianque.core.base.AbstractBaseService;
import com.tianque.core.util.ThreadVariable;
import com.tianque.domain.Organization;
import com.tianque.domain.Session;
import com.tianque.domain.property.OrganizationType;
import com.tianque.service.util.PopulationCatalog;
import com.tianque.shard.util.ShardConversion;
import com.tianque.sysadmin.service.OrganizationDubboService;

@Service("countrymenService")
public class CountrymenServiceImpl extends AbstractBaseService implements
		CountrymenService {
	@Autowired
	private CountrymenDao countrymenDao;
	@Autowired
	private ShardConversion shardConversion;
	@Autowired
	private OrganizationDubboService organizationDubboService;

	public Countrymen getCountrymenByPopulationIdAndAttentionPopulationType(
			Long populationId, String attentionPopulationType) {
		return countrymenDao.getCountrymenByPopulationIdAndPopulationCatalog(
				populationId,
				PopulationCatalog.populationCatalog(attentionPopulationType));
	}

	@Override
	public Countrymen getCountrymenByPopulationIdAndPopulationType(
			Long populationId, String populationType, String populationOrgCode) {
		Session session = (Session) this.getCurrentUser();
		String orgInternalCode = session.getOrgInternalCode();
		if (ThreadVariable.getOrganization() != null) {
			orgInternalCode = ThreadVariable.getOrganization()
					.getOrgInternalCode();
			if (ThreadVariable.getOrganization().getOrgType() != null
					&& ThreadVariable.getOrganization().getOrgType()
							.getInternalId() == OrganizationType.FUNCTIONAL_ORG
					&& ThreadVariable.getOrganization().getParentOrg() != null) {
				orgInternalCode = ThreadVariable.getOrganization()
						.getParentOrg().getOrgInternalCode();
			}
		}
		Organization organization = organizationDubboService
				.getSimplePinyinBySimpleCode(populationOrgCode);
		String shardCode = shardConversion.getShardCode(organization.getId());
		List<Countrymen> countrymens = countrymenDao
				.findCountrymensByPopulationIdAndPopulationType(populationId,
						populationType, orgInternalCode, shardCode);
		if (null != countrymens && countrymens.size() > 0)
			return countrymens.get(0);
		else
			return new Countrymen();
	}

	@Override
	public Countrymen getCountrymenByIdCardNoAndOrgInternalCode(
			String idCardNo, String orgInternalCode) {
		List<Countrymen> countrymens = countrymenDao
				.findCountrymensByIdCardNoAndOrgInternalCode(idCardNo,
						orgInternalCode,
						shardConversion.getShardCodeByOrgCode(orgInternalCode));
		if (null != countrymens && countrymens.size() > 0)
			return countrymens.get(0);
		else
			return new Countrymen();
	}
}
