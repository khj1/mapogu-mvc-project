package market;

public class BasketDTO {
	private String basket_idx;
	private String product_idx;
	private String basket_check;
	private String id;
	private int total_price;
	private int amount;
	private int total_reserves;
	
	
	public String getBasket_check() {
		return basket_check;
	}
	public void setBasket_check(String basket_check) {
		this.basket_check = basket_check;
	}
	public String getBasket_idx() {
		return basket_idx;
	}
	public void setBasket_idx(String basket_idx) {
		this.basket_idx = basket_idx;
	}
	public String getProduct_idx() {
		return product_idx;
	}
	public void setProduct_idx(String product_idx) {
		this.product_idx = product_idx;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getTotal_reserves() {
		return total_reserves;
	}
	public void setTotal_reserves(int total_reserves) {
		this.total_reserves = total_reserves;
	}
}
