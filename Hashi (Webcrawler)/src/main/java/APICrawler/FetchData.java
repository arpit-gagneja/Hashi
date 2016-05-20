package APICrawler;

import java.io.IOException;


import org.json.JSONException;
import org.json.JSONObject;

// main class to fetch API data
public class FetchData {

	JSONObject finObj = null;
	public JSONObject getAPIData()
	{
		return finObj;
	}
	
	public void setAPIData(String key, String secret, int rad) throws IOException, JSONException
	{
		CallAPI objFourSquare = new CallFourSquareAPI(key,secret,rad);
		System.out.println("Call Loc");
		objFourSquare.getCurrLoc();
		System.out.println("Call Venue");
		finObj = objFourSquare.callVenueExplorer();
		
		
		//CallAPI objTripAdvisor = new CallTripAdvisorAPI("HB1T5BNYLTMMBDOVUWIHLRQ5AVLFFPRMKTFMB3DKATFOLR0J","EQF2VL0KNWY0YTYAWKXWSP4XV0JNDPHK24XAQGMXHKKRCPBZ");
	}
	
	
}
