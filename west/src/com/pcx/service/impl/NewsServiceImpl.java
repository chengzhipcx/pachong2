package com.pcx.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pcx.entity.News;
import com.pcx.service.INewsService;
import com.pcx.utils.Spider;

@Service
public class NewsServiceImpl implements INewsService {

	@Override
	public List<News> getLiaoChengNews() {
		List<News> newsList = null;
		try {
			newsList = new Spider().getNewsList();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newsList;
	}

	@Override
	public List<Map> getToutiao() throws Exception {

		return new Spider().getTouTiaoList();
	}

	public static String download(String urlPath, String savePath) throws Exception {
		// 构造URL
		URL url = new URL(urlPath);
		// 打开连接
		URLConnection con = url.openConnection();
		// 设置请求超时为5s
		con.setConnectTimeout(5 * 10000);
		// 输入流
		InputStream is = con.getInputStream();
		// 1K的数据缓冲
		byte[] bs = new byte[1024];
		// 读取到的数据长度
		int len;
		// 输出的文件流
		File sf = new File(savePath);
		String imgsavepath=urlPath.replace("/", "").replace(":", "");
		File img = new File(savePath+imgsavepath+".jpg");
		if (!sf.exists()) {
			sf.mkdirs();
		}
		String virtualPath ="";
		if(!img.exists()){
			//图片如果不存在
			OutputStream os = new FileOutputStream(sf.getPath() + "/" + imgsavepath+".jpg");
			// String virtualPath = "/upload/SDSPage/" + filename+".jpg";//
			// 存入数据库的虚拟路径
			virtualPath = "/file/" + imgsavepath + ".jpg";// 存入数据库的虚拟路径
			// 开始读取
			while ((len = is.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
			// 完毕，关闭所有链接
			os.flush();
			os.close();
			is.close();
		}else{
			virtualPath="/file/" + imgsavepath + ".jpg";
		}
//		String filename = urlPath.substring(urlPath.lastIndexOf("/") + 1, urlPath.length());// 获取服务器上图片的名称
//		filename = new java.text.SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + randomNo + filename;// 时间+随机数防止重复
		
		return virtualPath;
	}

}
