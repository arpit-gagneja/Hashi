package DBUpload;

public class Review {
	private int id;
	private String author;
	private String text;
	private int restaurant_id;
	
	Review(int a, String b, String c, int d)
	{
		this.id=a;
		this.author=b;
		this.text=c;
		this.restaurant_id=d;
	}

	int getId() {
		return id;
	}

	void setId(int id) {
		this.id = id;
	}

	String getAuthor() {
		return author;
	}

	void setAuthor(String author) {
		this.author = author;
	}

	String getText() {
		return text;
	}

	void setText(String text) {
		this.text = text;
	}

	int getRestaurant_id() {
		return restaurant_id;
	}

	void setRestaurant_id(int restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	
}
