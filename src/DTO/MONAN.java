package DTO;

public class MONAN {
	
	private String id;
	private String name;
	private String type;
	private String image;
	private double unitPrice;
	private String decription;
	
	
	public MONAN(String id, String name, String type, String image, double unitPrice, String decription) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.image = image;
		this.unitPrice = unitPrice;
		this.decription = decription;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	
}
