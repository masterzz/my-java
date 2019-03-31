package gof.factory.builder;

/**
 * Builder模式
 * Phone
 */
public class Phone {
	private String name;
	private String brand;
	private String productArea;
	private float price;

	public String getName() {
		return name;
	}

	public Phone setName(String name) {
		this.name = name;
		return this ;
	}

	public String getBrand() {
		return brand;
	}

	public Phone setBrand(String brand) {
		this.brand = brand;
		return this ;
	}

	public String getProductArea() {
		return productArea;
	}

	public Phone setProductArea(String productArea) {
		this.productArea = productArea;
		return this ;
	}

	public float getPrice() {
		return price;
	}

	public Phone setPrice(float price) {
		this.price = price;
		return this ;
	}
}
