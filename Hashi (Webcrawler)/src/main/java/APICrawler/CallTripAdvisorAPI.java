package APICrawler;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//implementation class for TripAdvisor API 
public class CallTripAdvisorAPI extends CallAPI {

	CallTripAdvisorAPI(String a, String b) {
		super(a, b);
	}
	
	public void callVenueExplorer() throws IOException, JSONException
	{
		String venueSearch= "v2/venues/explore?" ;
		String venueURL = URLroot+venueSearch+"ll="+super.lat+","+super.lng+"&client_id="+ClientID+"&client_secret="+Secret+"&v=20150624";
		System.out.println("URL - \n"+venueURL);
		JSONObject venueJson = JsonReader.readJsonFromUrl(venueURL);
		System.out.println(venueJson.toString());
		System.out.println(venueJson.getJSONObject("meta").getInt("code"));;
		
		
		JSONObject resJson = venueJson.getJSONObject("response");
		JSONArray arrGrp = resJson.getJSONArray("groups");
		JSONArray arrItem = arrGrp.getJSONObject(0).getJSONArray("items");
		for (int i = 0; i < arrItem.length(); i++)
		{
		    String ven_name = arrItem.getJSONObject(i).getJSONObject("venue").getString("name");
		    String ven_loc = arrItem.getJSONObject(i).getJSONObject("venue").getString("location");
		    System.out.println("Restaurant Name - "+ven_name);
		    System.out.println("Restaurant Address - "+ven_loc);
		    JSONArray arrCat = arrItem.getJSONObject(i).getJSONObject("venue").getJSONArray("categories");
		    for (int j = 0; j < arrCat.length(); j++)
			{
				String cat_name = arrCat.getJSONObject(j).getString("name");
				System.out.println("Restaurant Category - "+cat_name);
			}
		}	
		
		System.out.println("Total number of Results - "+arrItem.length());
		//venue.categories.name = "Sushi Restaurant"
		//venue.menu.url
	}	
	

}
