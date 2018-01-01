package com.pcx.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.pcx.entity.News;

/**
 * @author chengzhi
 * 爬取新闻的逻辑
 */
public interface INewsService {
	
	/**
	 * 爬取今日聊城的新闻
	 */
	public List<News> getLiaoChengNews();
	
	/**
	 * 爬取今日头条的新闻
	 * @return
	 * @throws IOException 
	 * @throws Exception 
	 */
	public List<Map> getToutiao() throws Exception;
}
