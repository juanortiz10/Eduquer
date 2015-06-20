package models;

import request.HttpRequest;
import request.ResultDeserializer;
import request.UrlFactory;
import models.Result;

public class Search {

	public Result doSearch(String keywords) throws Exception{
		final String urlRequest = UrlFactory.buildUrl(keywords);
		final String jsonObjet = HttpRequest.doRequest(urlRequest);
		return ResultDeserializer.deserialize(jsonObjet);
	}
}
