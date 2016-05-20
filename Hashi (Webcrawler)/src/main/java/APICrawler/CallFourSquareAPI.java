package APICrawler;

import java.io.IOException;
import org.json.*;

// implementation class for FourSQuare API 
class CallFourSquareAPI extends CallAPI{
	
	CallFourSquareAPI(String a, String b, int r)
	{
		super(a,b);
		URLroot = "https://api.foursquare.com/";
		radius=r;
	}
	
	
	public JSONObject callVenueExplorer() throws IOException, JSONException
	{
		System.out.println("calling URI");
		
		String venueSearch= "v2/venues/explore?" ;
		String venueURL = URLroot+venueSearch+"&client_id="+ClientID+"&client_secret="+Secret+"&v=20150624&ll="+lat+","+lng+"&query=sushi";
		System.out.println("URL - \n"+venueURL);
		JSONObject venueJson = JsonReader.readJsonFromUrl(venueURL);
		System.out.println(venueJson.toString());
		int code = venueJson.getJSONObject("meta").getInt("code");
		System.out.println(code);
		
		//handling JSON error codes and warnings
		if(code!=200)
		{
			System.out.println("Error");
			return null;
		}
		else if(venueJson.getJSONObject("response").has("warning"))
		{
			System.out.println(venueJson.getJSONObject("response").getJSONObject("warning").getString("text"));
			return null;
		}
			
		return venueJson;
	}

	
}
