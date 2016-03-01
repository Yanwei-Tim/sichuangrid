package com.tianque.domain;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.tianque.core.base.BaseDomain;

public class Floorperson extends BaseDomain {

	private Date floorpersonsTime;
	private String address;
	private String events;
	private Long placeId;
	private String placeType;
	private String personInChargeName;
	private String placeName;
	private String placeTypeName;

	@JSON(format = "yyyy-MM-dd")
	public Date getFloorpersonsTime() {
		return floorpersonsTime;
	}

	public void setFloorpersonsTime(Date floorpersonsTime) {
		this.floorpersonsTime = floorpersonsTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEvents() {
		return events;
	}

	public void setEvents(String events) {
		this.events = events;
	}

	public Long getPlaceId() {
		return placeId;
	}

	public void setPlaceId(Long placeId) {
		this.placeId = placeId;
	}

	public String getPlaceType() {
		return placeType;
	}

	public void setPlaceType(String placeType) {
		this.placeType = placeType;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceTypeName() {
		return placeTypeName;
	}

	public void setPlaceTypeName(String placeTypeName) {
		this.placeTypeName = placeTypeName;
	}

	public String getPersonInChargeName() {
		return personInChargeName;
	}

	public void setPersonInChargeName(String personInChargeName) {
		this.personInChargeName = personInChargeName.replaceAll(" ", "");
		;
	}

}
