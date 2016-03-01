package com.tianque.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tianque.core.base.BaseAction;
import com.tianque.core.util.DialogMode;
import com.tianque.core.util.StringUtil;
import com.tianque.core.vo.GridPage;
import com.tianque.core.vo.PageInfo;
import com.tianque.domain.Floorperson;
import com.tianque.service.FloorpersonService;

@Controller("floorpersonsController")
public class FloorpersonsController extends BaseAction {
	@Autowired
	private FloorpersonService floorpersonService;
	private Floorperson floorperson = new Floorperson();
	private Long placeId;
	private String placeType;

	public String addFloorperson() {
		// if (floorperson.getFloorpersonsTime()==null) {
		// floorperson=new Floorperson();
		// return SUCCESS;
		// }
		floorperson = floorpersonService.addFloorperson(floorperson);
		return SUCCESS;
	}

	public String updateFloorperson() {
		// if (floorperson.getFloorpersonsTime()==null) {
		// floorperson=new Floorperson();
		// return SUCCESS;
		// }
		floorperson = floorpersonService.updateFloorperson(floorperson);
		return SUCCESS;
	}

	public String dispatch() {
		if (DialogMode.ADD_MODE.equalsIgnoreCase(mode)) {
			floorperson = new Floorperson();
		} else if (DialogMode.EDIT_MODE.equalsIgnoreCase(mode)) {
			floorperson = floorpersonService.getFloorperson(floorperson.getId());
		} else if (DialogMode.VIEW_MODE.equalsIgnoreCase(mode)) {
			floorperson = floorpersonService.getFloorperson(floorperson.getId());
		}
		if (placeType.equals("lettingHouses")) {
			return "lettingHouseKeyType";
		}
		return SUCCESS;
	}

	public String findFloorperson() {
		if (placeId == null || placeId.longValue() == 0L
				|| !StringUtil.isStringAvaliable("placeType")) {
			gridPage = new GridPage(new PageInfo<Floorperson>());
			return SUCCESS;
		}
		gridPage = new GridPage(floorpersonService.findFloorperson(placeId, page.intValue(),
				rows.intValue(), sidx, sord, placeType));
		return SUCCESS;
	}

	public String deleteFloorpersonById() {
		if (floorperson.getId() == null) {
			errorMessage = "对象ID不能为空";
			return ERROR;
		}
		floorpersonService.deleteFloorpersonById(floorperson.getId());
		return SUCCESS;
	}

	public String getFloorpersonById() {
		floorperson = floorpersonService.getFloorperson(floorperson.getId());
		return SUCCESS;
	}

	public Floorperson getFloorperson() {
		return floorperson;
	}

	public void setFloorperson(Floorperson floorperson) {
		this.floorperson = floorperson;
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

}
