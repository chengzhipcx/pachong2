package west;

import com.pcx.service.impl.NewsServiceImpl;

public class Test {
	public static void main(String[] args) throws Exception {
		String imgsrc=new NewsServiceImpl().download("https://p3.pstatp.com/list/240x240/438e0003be547858fac9", "WebContent/resources/upload");
		System.out.println(imgsrc);
	}
}
