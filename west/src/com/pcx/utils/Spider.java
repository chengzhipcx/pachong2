package com.pcx.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pcx.entity.News;
import com.pcx.service.impl.NewsServiceImpl;

public class Spider {

	public List<News> getNewsList() throws IOException {
		List<News> newsList = new ArrayList<News>();
		Document document = Jsoup.connect("http://www.liaocheng.gov.cn/")
				.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
		Elements elements = document.select("#bqhlb0").select("tr").select("td").select("table").select("tbody")
				.select("tr").select("td").select("table").select("tbody").select("tr").select("td").select("table")
				.select("tbody").select("tr").select("td").select("a");
		for (Element element : elements) {
			System.out.println(element.text() + "--" + element.attr("href"));
			News news = new News();
			news.setTitle("【今日聊城】" + element.text());
			news.setSrc("http://www.liaocheng.gov.cn/" + element.attr("href"));
			news.setTime(new Date());
			newsList.add(news);
		}
		return newsList;
	}

	public String getNewsTime(String url) throws IOException {
		Document document = Jsoup.connect(url)
				.userAgent("Mozilla/4.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)").get();
		Elements elements = document.select("body").select("table").select("tbody").select("tr").select("td")
				.select("table").select("tr ").select(" td ").select(" table ").select(" tbody ").select(" tr")
				.select(" td ").select(" table ").select(" tbody ").select(" tr ").select(" td");
		String timestr = "";
		for (Element element : elements) {
			timestr = element.text();
		}
		return timestr;
	}

	public List<Map> getTouTiaoList() throws Exception {
		List<News> newsList = new ArrayList<News>();
		// 需要爬的网页的文章列表
		String url = "http://www.toutiao.com/news_hot/";
		// 文章详情页的前缀(由于今日头条的文章都是在group这个目录下,所以定义了前缀,而且通过请求获取到的html页面)
		String url2 = "http://www.toutiao.com/group/";
		// 链接到该网站
		Connection connection = Jsoup.connect(url);
		Document content = null;
		try {
			// 获取内容
			content = connection.get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 转换成字符串
		String htmlStr = content.html();
		// 因为今日头条的文章展示比较奇葩,都是通过js定义成变量,所以无法使用获取dom元素的方式获取值
		String jsonStr = StringUtils.substringBetween(htmlStr, "var _data = ", ";");
		System.out.println(jsonStr);
		Map parse = (Map) JSONObject.parse(jsonStr);
		JSONArray parseArray = (JSONArray) parse.get("real_time_news");
		Map map = null;
		List<Map> maps = new ArrayList<>();
		// 遍历这个jsonArray,获取到每一个json对象,然后将其转换成Map对象(在这里其实只需要一个group_id,那么没必要使用map)
		for (int i = 0; i < parseArray.size(); i++) {
			map = (Map) parseArray.get(i);
			System.out.println("=====" + parseArray.get(i));
			System.out.println(map.get("image_url"));
			String imgurl = map.get("image_url").toString();
//			String imgsrc = new NewsServiceImpl().download(imgurl, "D:/download/");
			String imgsrc = new NewsServiceImpl().download(imgurl, "/var//download//");
			map.put("image_url", imgsrc);
			System.out.println(map.get("image_url"));
			String title = "【今日头条】" + map.get("title").toString();
			map.put("title", title);
			map.put("time",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			maps.add(map);
		}
		// 遍历之前获取到的map集合,然后分别访问这些文章详情页
//		for (Map map2 : maps) {
//			connection = Jsoup.connect(url2 + map2.get("group_id"));
//			try {
//				Document document = connection.get();
//				// //获取文章标题
//				// Elements title = document.select("[class=article-title]");
//				// System.out.println(title.html());
//				// //获取文章来源和文章发布时间
//				Elements articleInfo = document.select("[class=articleInfo]");
//				// Elements src = articleInfo.select("[class=src]");
				// System.out.println(src.html());
//				Elements time = articleInfo.select("[class=time]");
//				System.out.println(time.text());
				// System.out.println(time.html());
				// //获取文章内容
				// Elements contentEle =
				// document.select("[class=article-content]");
				// System.out.println(contentEle.html());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}

		return maps;

	}

	// public static void main(String[] args) {
	// System.out.println("1111");
	// try {
	// String touTiaoList = new Spider().getTouTiaoList();
	// System.out.println(touTiaoList);
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	public static void main(String[] args) throws IOException {
		System.out.println(new Spider().getNewsTime("http://www.liaocheng.gov.cn/art/2017/11/10/art_811_276346.html"));
	}
}
