package DBUpload;

import java.io.IOException;
import java.text.DecimalFormat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import APICrawler.FetchData;

public class DatabasePush {

	public static void main(String arg[])
	{
		FetchData fetchData = new FetchData();
		Matcher matchObj = new Matcher();
		DecimalFormat df = new DecimalFormat("#.00000");
	
		try
		{
			fetchData.setAPIData("HB1T5BNYLTMMBDOVUWIHLRQ5AVLFFPRMKTFMB3DKATFOLR0J","EQF2VL0KNWY0YTYAWKXWSP4XV0JNDPHK24XAQGMXHKKRCPBZ",5000);
			JSONObject venueJson = fetchData.getAPIData();
		
			JSONObject resJson = venueJson.getJSONObject("response");
			JSONArray arrGrp = resJson.getJSONArray("groups");
			JSONArray arrItem = arrGrp.getJSONObject(0).getJSONArray("items");
			int revId = matchObj.maxReviewID();
			matchObj.pullData();
			
			for (int i = 0; i < arrItem.length(); i++)
			{	
				JSONObject VenueDetails = arrItem.getJSONObject(i).getJSONObject("venue");
				String RestCategory = VenueDetails.getJSONArray("categories").getJSONObject(0).getString("shortName");
				int restId = 0;
				revId++;
				double latVal= Double.valueOf(df.format(VenueDetails.getJSONObject("location").getDouble("lat")));
				double lngVal= Double.valueOf(df.format(VenueDetails.getJSONObject("location").getDouble("lng")));
				    
				restId = matchObj.matchData(latVal , lngVal);
				if(restId != 0)
				{
					JSONArray arrTip = arrItem.getJSONObject(i).getJSONArray("tips");
					for (int k = 0; k < arrTip.length(); k++)
					{ 
						String tip = arrTip.getJSONObject(k).getString("text");
						String tip_fname = arrTip.getJSONObject(k).getJSONObject("user").getString("firstName");
						String tip_lname = (String) ((arrTip.getJSONObject(k).getJSONObject("user").has("lastName")) == true?arrTip.getJSONObject(k).getJSONObject("user").getString("lastName"):" ");
							
						matchObj.pushData(new Review(revId, (tip_fname+" "+tip_lname).trim(), tip, restId));
					}
				}
				else
				{
				   	if((VenueDetails.getJSONObject("location").has("city")) 
				   		&& (VenueDetails.getJSONObject("location").has("address")) )
				   	{
				    	int cityID= ((VenueDetails.getJSONObject("location").getString("city")).equals("Kitchener"))?2:1;
				    	int restaurantidd = (matchObj.maxRestaurantID())+1;
				    	int rating = (VenueDetails.has("rating")==true)?(VenueDetails.getInt("rating")):1;
				    	matchObj.pushData(new Restaurant(
				    			restaurantidd,
				    			VenueDetails.getString("name"),
				    			latVal,
				    			lngVal,
				    			VenueDetails.getJSONObject("location").getString("address"),
				    			cityID,
				    			rating
				    			)
				    	);
				    	
				    	JSONArray arrTip = arrItem.getJSONObject(i).getJSONArray("tips");
					    for (int k = 0; k < arrTip.length(); k++)
						{ 
							String tip = arrTip.getJSONObject(k).getString("text");
							String tip_fname = arrTip.getJSONObject(k).getJSONObject("user").getString("firstName");
							String tip_lname = (String) ((arrTip.getJSONObject(k).getJSONObject("user").has("lastName")) == true?arrTip.getJSONObject(k).getJSONObject("user").getString("lastName"):" ");
							
							matchObj.pushData(new Review(revId, (tip_fname+" "+tip_lname).trim(), tip, restaurantidd));
						}
					    	
					   	matchObj.addLocalData(latVal,lngVal,restaurantidd);
				    }	
				}
			}
			
			matchObj.closeConn();
			System.out.println("Total number of Results - "+arrItem.length());
			
		}
		catch(JSONException je)
		{
			System.out.println("Error JSON");
			System.out.println(je.toString());
		}
		catch(IOException ie)
		{
			System.out.println("Error IO");
			ie.printStackTrace();
	        System.err.println(ie.getClass().getName()+": "+ie.getMessage());
			System.out.println(ie.toString());
		}
		catch(NullPointerException ne)
		{
			System.out.println("Null JSON returned");
			System.out.println(ne.toString());
		}
		
	}
	
	
}
