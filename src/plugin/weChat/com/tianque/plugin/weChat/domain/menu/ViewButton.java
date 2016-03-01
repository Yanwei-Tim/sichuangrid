/**
 * 
 */
package com.tianque.plugin.weChat.domain.menu;

/**
 * 视图按纽
 * @author 
 *  @date  2014年4月17日
 */
public class ViewButton extends Button {
	private String type;
	private String url;
	public ViewButton(){
	
	}
	public ViewButton(String name,String url,String type){
		super.setName(name);
		this.url=url;
		this.type=type;
	}
	

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
