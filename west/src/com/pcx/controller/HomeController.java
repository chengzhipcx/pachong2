package com.pcx.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcx.entity.News;
import com.pcx.service.INewsService;
import com.pcx.service.IWeatherService;

/**
 * @author chengzhi
 * 控制层
 */
@Controller
@RequestMapping(value = "/homeController")
public class HomeController {
	@Autowired
	private IWeatherService weatherService;
	
	@Autowired
	private INewsService newsService;
	
	@RequestMapping(value = "/getWeather",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getWeather() {
		String weather = weatherService.getWeather();
		weather=weather.split("\"now\":")[1];
		weather = weather.substring(0, weather.length()-3);
		return weather;
	}
	
	@RequestMapping(value = "/getLiaoChengNews")
	@ResponseBody
	public List<News> getLiaoChengNews() {
		List<News> liaoChengNews = newsService.getLiaoChengNews();
		return liaoChengNews;
	}
	@RequestMapping(value = "/getTouTiaoNews")
	@ResponseBody
	public Map<String,List<Map>> getTouTiaoNews() throws Exception {
		 Map<String,List<Map>> map =new  HashMap<String,List<Map>> ();
		List<Map> liaoChengNews = newsService.getToutiao();
		map.put("real_time_news",liaoChengNews );
		return map;
	}
	
}
