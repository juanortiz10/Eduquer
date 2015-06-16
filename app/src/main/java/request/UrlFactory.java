package request;

import java.net.URLEncoder;

public class UrlFactory {
	private static final String url = "https://www.googleapis.com/customsearch/v1";
	private static final String cx = "005395824571062804229:x5z-qb5wuxo";
	private static final String key = "AIzaSyD_2HoDy9OPymgnM33czXgLr8yYzibhyEc";
	
	public static String buildUrl(String keywords) throws Exception{		
		return url + "?key=" + key + "&cx=" + cx + "&q=" + URLEncoder.encode(keywords);
	}
	
}

