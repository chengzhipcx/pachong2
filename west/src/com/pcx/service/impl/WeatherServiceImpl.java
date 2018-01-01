package com.pcx.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.pcx.service.IWeatherService;


@Service
public class WeatherServiceImpl implements IWeatherService {
	
	@Override
	public String getWeather() {
		String result = "";
		String param = "key=06f1371695af43e1a875781701ad9ff0&location=聊城";
//		String param = "key=06f1371695af43e1a8757ff0&location=聊城";
		StringBuilder sb = new StringBuilder();
		InputStream is = null;
		BufferedReader br = null;
		try {
			// 接口地址
			String url = "https://free-api.heweather.com/s6/weather/now?parameters";
			URL uri = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
			connection.setRequestMethod("POST");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(10000);
			connection.setRequestProperty("accept", "*/*");
			// 发送参数
			connection.setDoOutput(true);
			PrintWriter out = new PrintWriter(connection.getOutputStream());
			out.print(param);
			out.flush();
			// 接收结果
			is = connection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is));
			String line;
			// 缓冲逐行读取
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb.toString());
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {
					is.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	

}
