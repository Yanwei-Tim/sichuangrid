package com.tianque.domain.workingRecord;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.beanutils.PropertyUtils;

import com.tianque.core.util.StringUtil;
import com.tianque.working.domain.ReportWorkingRecord;

@SuppressWarnings("serial")
public class EmphasisSafetyDetail extends ReportWorkingRecord {
	private long inspectSum;// 排查次数-总次数
	private long inspectworkingTeam;// 排查次数-组织工作组数
	private long inspectOther;// 排查次数-其他
	private long inspectCadre;// 排查次数-发动干部数
	private long inspectPeople;// 排查次数-发动群众数

	@SuppressWarnings("unused")
	private long publicitySum;// 宣传发动数-总人数
	private long publicityCadre;// 宣传发动数-发动干部数
	private long publicityPeople;// 宣传发动数-发动群众数
	private long publicityPublicNotice;// 宣传发动数-发布通告数
	private long publicitySymposium;// 宣传发动数-召开座谈会数
	private long publicityPeopleInform;// 宣传发动数-群众举报数
	private long publicityCrackedCase;// 宣传发动数-从中破获刑事案件数
	private long publicityCatchingCriminal;// 宣传发动数-从中抓获犯罪嫌疑人数
	private long findAreaSum;// 排查发现治安重点地区数-总数
	private long findAreaCounty;// 排查发现治安重点地区数-县市区数
	private long findAreaStreet;// 排查发现治安重点地区数-乡镇（街道）数
	private long findAreaVillage;// 排查发现治安重点地区数-村
	private long findAreaOther;// 排查发现治安重点地区数-其他
	private long findAreaVicious;// 排查发现治安重点地区数-恶势力地区
	private long findAreaViolence;// 排查发现治安重点地区数-杀人爆炸等严重暴力犯罪地区
	private long findAreaRob;// 排查发现治安重点地区数-两抢一盗犯罪地区
	private long findAreaPoison;// 排查发现治安重点地区数-黄赌毒违法犯罪地区
	private long findAreaHeresy;// 排查发现治安重点地区数-邪教组织违法犯罪地区数
	private long findAreaOtherType;// 排查发现治安重点地区数-其他

	private long alreadyRenovateSum;// 已整治重点地区数-总数
	private long alreadyRenovateCounty;// 已正在整治重点地区数-县市区数
	private long alreadyRenovateStreet;// 已正在整治重点地区数-乡镇（街道）数
	private long alreadyRenovateVillage;// 已正在整治重点地区数-村
	private long alreadyRenovateOther;// 已正在整治重点地区数-其他
	private long nowRenovateSum;// 正在整治重点地区数-总数
	private long nowRenovateCounty;// 正在整治重点地区数-县市区数
	private long nowRenovateStreet;// 正在整治重点地区数-乡镇（街道）数
	private long nowRenovateVillage;// 正在整治重点地区数-村
	private long nowRenovateOther;// 正在整治重点地区数-其他
	private long strikeCrackedSum;// 打击整治数—破获刑事案件数—总数
	private long strikeViolence;// 打击整治数—杀人爆炸等严重暴力案件数
	private long strikeRob;// 打击整治数—两抢一盗案件数
	private long strikePoison;// 打击整治数—黄赌毒违法案件数
	private long strikeArrestSum;// 打击整治数—抓获犯罪嫌疑人数-总数
	private long strikeFlow;// 打击整治数—流串犯罪嫌疑人
	private long strikeOutside;// 打击整治数—外来人员
	private long strikeUnemployed;// 打击整治数—无业人员
	private long strikePenal;// 打击整治数—刑释解教人员
	private long strikeJuvenile;// 打击整治数—14-15周岁青少年
	private long strikeAttackSum;// 打击整治数—打掉黑恶势力-总数
	private long strikeGangdom;// 打击整治数—黑社会性质组织数
	private long strikeDadness;// 打击整治数—恶势力
	private long cautionSum;// 警示数-总数
	private long cautionCity;// 警示数-省级警示数
	private long cautionLand;// 警示数-地市警示数
	private long cautionCounty;// 警示数-县市区警示数
	private long brandSum;// 挂牌整治数-总数
	private long brandCity;// 挂牌整治数-省级警示数
	private long brandLand;// 挂牌整治数-地市警示数
	private long brandCounty;// 挂牌整治数-县市区警示数

	public EmphasisSafetyDetail() {

	}

	public EmphasisSafetyDetail(ReportWorkingRecord record) throws Exception {
		// content转化成属性
		JSONObject jsonObject = new JSONObject();
		if (StringUtil.isStringAvaliable(record.getContent())) {
			jsonObject = (JSONObject) JSONSerializer.toJSON(record.getContent());
		}
		EmphasisSafetyDetail bean = (EmphasisSafetyDetail) JSONObject.toBean(jsonObject,
				EmphasisSafetyDetail.class);
		PropertyUtils.copyProperties(bean, record);
		PropertyUtils.copyProperties(this, bean);
	}

	public long getInspectSum() {
		return inspectSum;
	}

	public void setInspectSum(long inspectSum) {
		this.inspectSum = inspectSum;
	}

	public long getInspectworkingTeam() {
		return inspectworkingTeam;
	}

	public void setInspectworkingTeam(long inspectworkingTeam) {
		this.inspectworkingTeam = inspectworkingTeam;
	}

	public long getInspectOther() {
		return inspectOther;
	}

	public void setInspectOther(long inspectOther) {
		this.inspectOther = inspectOther;
	}

	public long getPublicitySum() {
		return getPublicityCadre() + getPublicityPeople();
	}

	public void setPublicitySum(long publicitySum) {
		this.publicitySum = publicitySum;
	}

	public long getPublicityPublicNotice() {
		return publicityPublicNotice;
	}

	public void setPublicityPublicNotice(long publicityPublicNotice) {
		this.publicityPublicNotice = publicityPublicNotice;
	}

	public long getPublicitySymposium() {
		return publicitySymposium;
	}

	public void setPublicitySymposium(long publicitySymposium) {
		this.publicitySymposium = publicitySymposium;
	}

	public long getPublicityPeopleInform() {
		return publicityPeopleInform;
	}

	public void setPublicityPeopleInform(long publicityPeopleInform) {
		this.publicityPeopleInform = publicityPeopleInform;
	}

	public long getPublicityCrackedCase() {
		return publicityCrackedCase;
	}

	public void setPublicityCrackedCase(long publicityCrackedCase) {
		this.publicityCrackedCase = publicityCrackedCase;
	}

	public long getPublicityCatchingCriminal() {
		return publicityCatchingCriminal;
	}

	public void setPublicityCatchingCriminal(long publicityCatchingCriminal) {
		this.publicityCatchingCriminal = publicityCatchingCriminal;
	}

	public long getFindAreaSum() {
		return findAreaSum;
	}

	public void setFindAreaSum(long findAreaSum) {
		this.findAreaSum = findAreaSum;
	}

	public long getFindAreaCounty() {
		return findAreaCounty;
	}

	public void setFindAreaCounty(long findAreaCounty) {
		this.findAreaCounty = findAreaCounty;
	}

	public long getFindAreaStreet() {
		return findAreaStreet;
	}

	public void setFindAreaStreet(long findAreaStreet) {
		this.findAreaStreet = findAreaStreet;
	}

	public long getFindAreaVillage() {
		return findAreaVillage;
	}

	public void setFindAreaVillage(long findAreaVillage) {
		this.findAreaVillage = findAreaVillage;
	}

	public long getFindAreaOther() {
		return findAreaOther;
	}

	public void setFindAreaOther(long findAreaOther) {
		this.findAreaOther = findAreaOther;
	}

	public long getFindAreaVicious() {
		return findAreaVicious;
	}

	public void setFindAreaVicious(long findAreaVicious) {
		this.findAreaVicious = findAreaVicious;
	}

	public long getFindAreaViolence() {
		return findAreaViolence;
	}

	public void setFindAreaViolence(long findAreaViolence) {
		this.findAreaViolence = findAreaViolence;
	}

	public long getFindAreaRob() {
		return findAreaRob;
	}

	public void setFindAreaRob(long findAreaRob) {
		this.findAreaRob = findAreaRob;
	}

	public long getFindAreaPoison() {
		return findAreaPoison;
	}

	public void setFindAreaPoison(long findAreaPoison) {
		this.findAreaPoison = findAreaPoison;
	}

	public long getFindAreaHeresy() {
		return findAreaHeresy;
	}

	public void setFindAreaHeresy(long findAreaHeresy) {
		this.findAreaHeresy = findAreaHeresy;
	}

	public long getAlreadyRenovateSum() {
		return alreadyRenovateSum;
	}

	public void setAlreadyRenovateSum(long alreadyRenovateSum) {
		this.alreadyRenovateSum = alreadyRenovateSum;
	}

	public long getAlreadyRenovateCounty() {
		return alreadyRenovateCounty;
	}

	public void setAlreadyRenovateCounty(long alreadyRenovateCounty) {
		this.alreadyRenovateCounty = alreadyRenovateCounty;
	}

	public long getAlreadyRenovateStreet() {
		return alreadyRenovateStreet;
	}

	public void setAlreadyRenovateStreet(long alreadyRenovateStreet) {
		this.alreadyRenovateStreet = alreadyRenovateStreet;
	}

	public long getAlreadyRenovateVillage() {
		return alreadyRenovateVillage;
	}

	public void setAlreadyRenovateVillage(long alreadyRenovateVillage) {
		this.alreadyRenovateVillage = alreadyRenovateVillage;
	}

	public long getAlreadyRenovateOther() {
		return alreadyRenovateOther;
	}

	public void setAlreadyRenovateOther(long alreadyRenovateOther) {
		this.alreadyRenovateOther = alreadyRenovateOther;
	}

	public long getNowRenovateSum() {
		return nowRenovateSum;
	}

	public void setNowRenovateSum(long nowRenovateSum) {
		this.nowRenovateSum = nowRenovateSum;
	}

	public long getNowRenovateCounty() {
		return nowRenovateCounty;
	}

	public void setNowRenovateCounty(long nowRenovateCounty) {
		this.nowRenovateCounty = nowRenovateCounty;
	}

	public long getNowRenovateStreet() {
		return nowRenovateStreet;
	}

	public void setNowRenovateStreet(long nowRenovateStreet) {
		this.nowRenovateStreet = nowRenovateStreet;
	}

	public long getNowRenovateVillage() {
		return nowRenovateVillage;
	}

	public void setNowRenovateVillage(long nowRenovateVillage) {
		this.nowRenovateVillage = nowRenovateVillage;
	}

	public long getNowRenovateOther() {
		return nowRenovateOther;
	}

	public void setNowRenovateOther(long nowRenovateOther) {
		this.nowRenovateOther = nowRenovateOther;
	}

	public long getStrikeCrackedSum() {
		return strikeCrackedSum;
	}

	public void setStrikeCrackedSum(long strikeCrackedSum) {
		this.strikeCrackedSum = strikeCrackedSum;
	}

	public long getStrikeViolence() {
		return strikeViolence;
	}

	public void setStrikeViolence(long strikeViolence) {
		this.strikeViolence = strikeViolence;
	}

	public long getStrikeRob() {
		return strikeRob;
	}

	public void setStrikeRob(long strikeRob) {
		this.strikeRob = strikeRob;
	}

	public long getStrikePoison() {
		return strikePoison;
	}

	public void setStrikePoison(long strikePoison) {
		this.strikePoison = strikePoison;
	}

	public long getStrikeArrestSum() {
		return strikeArrestSum;
	}

	public void setStrikeArrestSum(long strikeArrestSum) {
		this.strikeArrestSum = strikeArrestSum;
	}

	public long getStrikeFlow() {
		return strikeFlow;
	}

	public void setStrikeFlow(long strikeFlow) {
		this.strikeFlow = strikeFlow;
	}

	public long getStrikeOutside() {
		return strikeOutside;
	}

	public void setStrikeOutside(long strikeOutside) {
		this.strikeOutside = strikeOutside;
	}

	public long getStrikeUnemployed() {
		return strikeUnemployed;
	}

	public void setStrikeUnemployed(long strikeUnemployed) {
		this.strikeUnemployed = strikeUnemployed;
	}

	public long getStrikePenal() {
		return strikePenal;
	}

	public void setStrikePenal(long strikePenal) {
		this.strikePenal = strikePenal;
	}

	public long getStrikeJuvenile() {
		return strikeJuvenile;
	}

	public void setStrikeJuvenile(long strikeJuvenile) {
		this.strikeJuvenile = strikeJuvenile;
	}

	public long getStrikeAttackSum() {
		return strikeAttackSum;
	}

	public void setStrikeAttackSum(long strikeAttackSum) {
		this.strikeAttackSum = strikeAttackSum;
	}

	public long getStrikeGangdom() {
		return strikeGangdom;
	}

	public void setStrikeGangdom(long strikeGangdom) {
		this.strikeGangdom = strikeGangdom;
	}

	public long getStrikeDadness() {
		return strikeDadness;
	}

	public void setStrikeDadness(long strikeDadness) {
		this.strikeDadness = strikeDadness;
	}

	public long getCautionSum() {
		return cautionSum;
	}

	public void setCautionSum(long cautionSum) {
		this.cautionSum = cautionSum;
	}

	public long getCautionCity() {
		return cautionCity;
	}

	public void setCautionCity(long cautionCity) {
		this.cautionCity = cautionCity;
	}

	public long getCautionLand() {
		return cautionLand;
	}

	public void setCautionLand(long cautionLand) {
		this.cautionLand = cautionLand;
	}

	public long getCautionCounty() {
		return cautionCounty;
	}

	public void setCautionCounty(long cautionCounty) {
		this.cautionCounty = cautionCounty;
	}

	public long getBrandSum() {
		return brandSum;
	}

	public void setBrandSum(long brandSum) {
		this.brandSum = brandSum;
	}

	public long getBrandCity() {
		return brandCity;
	}

	public void setBrandCity(long brandCity) {
		this.brandCity = brandCity;
	}

	public long getBrandLand() {
		return brandLand;
	}

	public void setBrandLand(long brandLand) {
		this.brandLand = brandLand;
	}

	public long getBrandCounty() {
		return brandCounty;
	}

	public void setBrandCounty(long brandCounty) {
		this.brandCounty = brandCounty;
	}

	public long getFindAreaOtherType() {
		return findAreaOtherType;
	}

	public void setFindAreaOtherType(long findAreaOtherType) {
		this.findAreaOtherType = findAreaOtherType;
	}

	public long getPublicityCadre() {
		return publicityCadre;
	}

	public void setPublicityCadre(long publicityCadre) {
		this.publicityCadre = publicityCadre;
	}

	public long getPublicityPeople() {
		return publicityPeople;
	}

	public void setPublicityPeople(long publicityPeople) {
		this.publicityPeople = publicityPeople;
	}

	public long getInspectCadre() {
		return inspectCadre;
	}

	public void setInspectCadre(long inspectCadre) {
		this.inspectCadre = inspectCadre;
	}

	public long getInspectPeople() {
		return inspectPeople;
	}

	public void setInspectPeople(long inspectPeople) {
		this.inspectPeople = inspectPeople;
	}

}
