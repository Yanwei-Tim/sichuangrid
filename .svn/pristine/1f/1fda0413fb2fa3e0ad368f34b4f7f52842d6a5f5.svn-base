/**
 * 
 */
package com.tianque.plugin.weChat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianque.core.base.AbstractBaseService;
import com.tianque.plugin.weChat.domain.sendMessage.Article;
import com.tianque.plugin.weChat.domain.sendMessage.NewsMessage;
import com.tianque.plugin.weChat.service.NewsService;
import com.tianque.plugin.weChat.util.Constants;

/**
 * @author 
 *  @date  2014年4月18日
 */
@Service("newsService")
@Transactional
public class NewsServiceImpl extends AbstractBaseService implements NewsService {

	@Override
	public NewsMessage getArticleByType(int type, Map<String, String> messageMap) {

		// 发送方帐号（open_id）  
		String fromUserName = messageMap.get("FromUserName");
		// 公众帐号  
		String toUserName = messageMap.get("ToUserName");
		// 创建图文消息  
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(Constants.RESP_MESSAGE_TYPE_NEWS);
		List<Article> articleList = new ArrayList<Article>();

		if (type == 0) {
			Article article = new Article();
			article.setTitle("天阙科技欢迎你！！！");
			article.setDescription("  杭州天阙科技有限公司是为政府和公共事业提供专业解决方案的现代科技公司。\n   自2003年成立以来，在中科院与浙大科研平台的基础上，整合浙江行政管理学会理 论资源，经过多年的创新研究，沉淀了雄厚的技术实力与应用经验。公司现有技术 人员500余名，设有独立的技术研究中心，为公司下辖社会管理、公安警务、社区 信息化四个事业部提供技术支撑。来源于社会、服务于社会。背负着强烈的使命感， 天阙通过现代科技手段助力变化中的社会需求，全面提升信息技术带来的创新生产力。\n  天阙科技一直专注于提升用户价值。除了整合一流的管理理念与技术手段，为客户 提供先进的信息化支撑外，天阙科技更注重提供客户与社会的高体验服务。从对复 杂业务的顾问咨询、整体设计，到系统实施、运营保障，围绕用户需求的整体价值 链，天阙科技本着执着，专业、创新、进取的职业精神，并以诚恳的态度，努力实 现用户价值与天阙社会价值的共同体现。");
			article.setPicUrl("http://www.hztianque.com/skin/images/about_18.gif");
			article.setUrl("http://www.hztianque.com/");
			articleList.add(article);
			// 设置图文消息个数  
			newsMessage.setArticleCount(articleList.size());
			// 设置图文消息包含的图文集合  
			newsMessage.setArticles(articleList);
		} else if (type == 1) {
			Article article1 = new Article();
			article1.setTitle("天气查询");
			article1.setDescription("");
			// 将图片置为空  
			article1.setPicUrl("http://www.weather.com.cn/m/i/city/101210101l.jpg");
			article1.setUrl("http://www.weather.com.cn/weather/101210101.shtml#7d");

			Article article2 = new Article();
			article2.setTitle("天气查询2");
			article2.setDescription("");
			article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article2.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

			Article article3 = new Article();
			article3.setTitle("天气查询3");
			article3.setDescription("");
			article3.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article3.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

			Article article4 = new Article();
			article4.setTitle("天气查询4");
			article4.setDescription("天气查询测试阶段");
			article4.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article4.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			articleList.add(article4);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);

		} else if (type == 2) {
			Article article1 = new Article();
			article1.setTitle("公交查询");
			article1.setDescription("");
			article1.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
			article1.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

			Article article2 = new Article();
			article2.setTitle("公交查询1");
			article2.setDescription("公交查询测试阶段");
			article2.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
			article2.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

			Article article3 = new Article();
			article3.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
			article3.setDescription("");
			// 将图片置为空  
			article3.setPicUrl("");
			article3.setUrl("http://blog.csdn.net/lyq8479");

			articleList.add(article1);
			articleList.add(article2);
			articleList.add(article3);
			newsMessage.setArticleCount(articleList.size());
			newsMessage.setArticles(articleList);

		}

		return newsMessage;
	}

}
