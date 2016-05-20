package APICrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//interface for all API classes
abstract class CallAPI
{
	String ClientID = "HB1T5BNYLTMMBDOVUWIHLRQ5AVLFFPRMKTFMB3DKATFOLR0J";
	String Secret = "EQF2VL0KNWY0YTYAWKXWSP4XV0JNDPHK24XAQGMXHKKRCPBZ";
	String lng;
	String lat;
	String URLroot;
	int radius;
	
	CallAPI(String a, String b)
	{
		System.out.println("Constructor");
		this.ClientID = a;
		this.Secret = b;
	}
	
	//method to obtain user's current location
	void getCurrLoc() throws IOException, JSONException
	{
		  //throws IOException, JSONException 
		//JsonReader jsonReader = new JsonReader();
		//JSONObject json = JsonReader.readJsonFromUrl("http://maps.googleapis.com/maps/api/geocode/json?address=Waterloo");
		/*
		JSONObject json = JsonReader.readJsonFromUrl("http://freegeoip.net/json/");
		System.out.println(json.toString());
		System.out.println(json.get("latitude"));
		System.out.println(json.get("longitude"));
		this.lng = json.get("longitude").toString() ;
		this.lat = json.get("latitude").toString();*/
		
		this.lat = "43.5318";
		this.lng = "-80.4673" ;
		
	}
	
	//abstract method for different implementation of each API 
	public abstract JSONObject callVenueExplorer() throws IOException, JSONException;
	 
}