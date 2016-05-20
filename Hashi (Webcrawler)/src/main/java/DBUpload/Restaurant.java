package DBUpload;

public class Restaurant {
	
	private int id;
	private String name;
	private double latitude;
	private double longitude;
	private String address;
	private int city_id;
	private int rating;
	
	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getName() {
		return name;
	}

	void setName(String name) {
		this.name = name;
	}

	double getLatitude() {
		return latitude;
	}

	void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	double getLongitude() {
		return longitude;
	}

	void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	String getAddress() {
		return address;
	}

	void setAddress(String address) {
		this.address = address;
	}

	int getCity_id() {
		return city_id;
	}

	void setCity_id(int city_id) {
		this.city_id = city_id;
	}

	int getRating() {
		return rating;
	}

	void setRating(int rating) {
		this.rating = rating;
	}

	Restaurant(int a, String b, double c, double d, String e, int f, int g)
	{
		this.id=a;
		this.name=b;
		this.latitude=c;
		this.longitude=d;
		this.address=e;
		this.city_id=f;
		this.rating=g;
		
	}

}
