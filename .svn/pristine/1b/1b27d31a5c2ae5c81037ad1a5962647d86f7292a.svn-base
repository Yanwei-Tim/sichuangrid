/**
 * 
 */
package com.tianque.plugin.weChat.util;

import net.sf.json.JSONObject;

import com.tianque.plugin.weChat.domain.menu.Button;
import com.tianque.plugin.weChat.domain.menu.ClickButton;
import com.tianque.plugin.weChat.domain.menu.ComplexButton;
import com.tianque.plugin.weChat.domain.menu.Menu;
import com.tianque.plugin.weChat.domain.menu.ViewButton;

/**
 * 组装菜单
 *  @date  2014年4月18日
 */
public class MenuUtils {
	public static void main(String[] args) {
	System.out.println(	JSONObject.fromObject(getMenu()).toString());
	System.out.println(	JSONObject.fromObject(getTestMenu()).toString());
	
}
	/** 
	 * 组装菜单数据 
	 *  
	 * @return 
	 */
	public static Menu getMenu() {
		ClickButton btn11 = new ClickButton();
		btn11.setName("辖区概况");
		btn11.setType("click");
		btn11.setKey("11");

		ClickButton btn12 = new ClickButton();
		btn12.setName("辖区新闻");
		btn12.setType("click");
		btn12.setKey("12");

		ClickButton btn13 = new ClickButton();
		btn13.setName("通知公告");
		btn13.setType("click");
		btn13.setKey("13");

		ClickButton btn14 = new ClickButton();
		btn14.setName("平安简讯");
		btn14.setType("click");
		btn14.setKey("14");

		ClickButton btn15 = new ClickButton();
		btn15.setName("往期回顾");
		btn15.setType("click");
		btn15.setKey("15");

		ClickButton btn21 = new ClickButton();
		btn21.setName("办事指南");
		btn21.setType("click");
		btn21.setKey("21");

		ClickButton btn22 = new ClickButton();
		btn22.setName("联系我们");
		btn22.setType("click");
		btn22.setKey("22");

		ClickButton btn23 = new ClickButton();
		btn23.setName("常用号码");
		btn23.setType("click");
		btn23.setKey("23");

	
		ViewButton btn31 = new ViewButton();
		btn31.setName("留言板");
		btn31.setType("view");
		btn31.setUrl("http://anhaooray.oicp.net:28096/index.html");

		ViewButton btn32 = new ViewButton();
		btn32.setName("互帮互助");
		btn32.setType("view");
		btn32.setUrl("http://anhaooray.oicp.net:28096/index.html");

		ViewButton btn33 = new ViewButton();
		btn33.setName("远程申报");
		btn33.setType("view");
		btn33.setUrl("http://anhaooray.oicp.net:28096/index.html");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("辖区动态");
		mainBtn1.setSub_button(new ClickButton[] { btn11, btn12, btn13, btn14, btn15 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("便民指南");
		mainBtn2.setSub_button(new ClickButton[] { btn21, btn22, btn23 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("互动专栏");
		mainBtn3.setSub_button(new ViewButton[] { btn31, btn32, btn33 });

		/** 
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
		 *  
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
		 */

		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}

	/** 
	 * 组装菜单数据 (测试)
	 *  
	 * @return 
	 */
	public static Menu getTestMenu() {
		ViewButton btn11 = new ViewButton();
		btn11.setName("百度");
		btn11.setType("view");
		btn11.setUrl("http://www.baidu.com");

		ViewButton btn12 = new ViewButton();
		btn12.setName("谷歌");
		btn12.setType("view");
		btn12.setUrl("https://www.google.com");

		ViewButton btn13 = new ViewButton();
		btn13.setName("360");
		btn13.setType("view");
		btn13.setUrl("http://www.3600.com");

		ClickButton btn21 = new ClickButton();
		btn21.setName("歌曲点播");
		btn21.setType("click");
		btn21.setKey("21");

		ClickButton btn22 = new ClickButton();
		btn22.setName("经典游戏");
		btn22.setType("click");
		btn22.setKey("22");

		ClickButton btn31 = new ClickButton();
		btn31.setName("给我留言");
		btn31.setType("click");
		btn31.setKey("31");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("网站");
		mainBtn1.setSub_button(new ViewButton[] { btn11, btn12, btn13 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("体闲驿站");
		mainBtn2.setSub_button(new ClickButton[] { btn21, btn22 });

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("小小互动");
		mainBtn3.setSub_button(new ClickButton[] { btn31 });

		/** 
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br> 
		 *  
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br> 
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br> 
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 }); 
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, btn31 });
		return menu;
	}
}
