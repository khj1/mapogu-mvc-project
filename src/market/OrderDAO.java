package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public OrderDAO() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mariadb://127.0.0.1:3306/suamil_db";
			String id = "suamil_user";
			String pass = "1234";
			con = DriverManager.getConnection(url, id, pass);
			System.out.println("MariaDB success");
		}
		catch(Exception e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
	
	public int insertOrderInfo(OrderDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO order_info ("
					+ "	order_name, order_addr, order_mobile, order_email, "
					+ " receipt_name, receipt_addr, receipt_mobile, receipt_email, "
					+ " receipt_msg, total_price, payment_type, id ) "
					+ " VALUES ( "
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getOrder_name()); 
			psmt.setString(2, dto.getOrder_addr());
			psmt.setString(3, dto.getOrder_mobile());
			psmt.setString(4, dto.getOrder_email());
			psmt.setString(5, dto.getReceipt_name());
			psmt.setString(6, dto.getReceipt_addr());
			psmt.setString(7, dto.getReceipt_mobile());
			psmt.setString(8, dto.getReceipt_email());
			psmt.setString(9, dto.getReceipt_msg());
			psmt.setInt(10, dto.getTotal_price());
			psmt.setString(11, dto.getPayment_type());
			psmt.setString(12, dto.getId());
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			result = 0;
			System.out.println("주문내역 항목 추가 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public OrderDTO getLatestOrderInfo(String user_id) {
		OrderDTO oDto = new OrderDTO();
		try {
			String query = " SELECT * FROM order_info "
					+ " WHERE id = ? "
					+ "	ORDER BY order_idx DESC "
					+ " LIMIT 0, 1 ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
			rs = psmt.executeQuery(); rs.next();
			
			oDto.setOrder_idx(rs.getString(1));
			oDto.setOrder_name(rs.getString(2));
			oDto.setOrder_addr(rs.getString(3));
			oDto.setOrder_mobile(rs.getString(4));
			oDto.setOrder_email(rs.getString(5));
			oDto.setReceipt_name(rs.getString(6));
			oDto.setReceipt_addr(rs.getString(7));
			oDto.setReceipt_mobile(rs.getString(8));
			oDto.setReceipt_email(rs.getString(9));
			oDto.setReceipt_msg(rs.getString(10));
			oDto.setTotal_price(rs.getInt(11));
			oDto.setPayment_type(rs.getString(12));
			oDto.setId(rs.getString(13));
		}
		catch(Exception e) {
			System.out.println("주문 정보 조회 중 예외 발생");
			e.printStackTrace();
		}
		return oDto;
	}
	
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
		}
		catch(Exception e) {
			System.out.println("MariaDB 자원 반납 시 예외 발생");
		}
	}
}
