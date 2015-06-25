package request;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import models.Items;
import models.Result;

public class ResultDeserializer{

	public static Result deserialize(String object) throws JSONException 
	{
		Result result = new Result();
		
		final List<Items> items = new ArrayList<Items>();
		
		JSONObject jSonObject = new JSONObject(object);
		JSONArray jSonObjectArray = jSonObject.getJSONArray("items");
		for(int count = 0; count < jSonObjectArray.length();count++){
			JSONObject jsonItem = (JSONObject) jSonObjectArray.get(count);

			Items item = new Items();
			item.setTitle(jsonItem.getString("title"));
			item.setHtmlTitle(jsonItem.getString("htmlTitle"));
			item.setLink(jsonItem.getString("link"));
			item.setDisplayLink(jsonItem.getString("displayLink"));
			item.setSnippet(jsonItem.getString("snippet"));
			item.setHtmlSnippet(jsonItem.getString("htmlSnippet"));
			items.add(item);

		}

		result.setItems(items);
		
		return result;
	}
}
