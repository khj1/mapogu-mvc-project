package market;

public class OrderDTO {
	private String order_idx;
	private String order_name;
	private String order_addr;
	private String order_mobile;
	private String order_email;
	private String receipt_name;
	private String receipt_addr;
	private String receipt_mobile;
	private String receipt_email;
	private String receipt_msg;
	private int total_price;
	private String payment_type;
	private String id;
	
	public String getPayment_type() {
		return payment_type;
	}
	public void setPayment_type(String payment_type) {
		this.payment_type = payment_type;
	}
	public String getOrder_idx() {
		return order_idx;
	}
	public void setOrder_idx(String order_idx) {
		this.order_idx = order_idx;
	}
	public String getOrder_name() {
		return order_name;
	}
	public void setOrder_name(String order_name) {
		this.order_name = order_name;
	}
	public String getOrder_addr() {
		return order_addr;
	}
	public void setOrder_addr(String order_addr) {
		this.order_addr = order_addr;
	}
	public String getOrder_mobile() {
		return order_mobile;
	}
	public void setOrder_mobile(String order_mobile) {
		this.order_mobile = order_mobile;
	}
	public String getOrder_email() {
		return order_email;
	}
	public void setOrder_email(String order_email) {
		this.order_email = order_email;
	}
	public String getReceipt_name() {
		return receipt_name;
	}
	public void setReceipt_name(String receipt_name) {
		this.receipt_name = receipt_name;
	}
	public String getReceipt_addr() {
		return receipt_addr;
	}
	public void setReceipt_addr(String receipt_addr) {
		this.receipt_addr = receipt_addr;
	}
	public String getReceipt_mobile() {
		return receipt_mobile;
	}
	public void setReceipt_mobile(String receipt_mobile) {
		this.receipt_mobile = receipt_mobile;
	}
	public String getReceipt_email() {
		return receipt_email;
	}
	public void setReceipt_email(String receipt_email) {
		this.receipt_email = receipt_email;
	}
	public String getReceipt_msg() {
		return receipt_msg;
	}
	public void setReceipt_msg(String receipt_msg) {
		this.receipt_msg = receipt_msg;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int payment) {
		this.total_price = payment;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
