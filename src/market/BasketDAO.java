package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import community.CommunityDTO;

public class BasketDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public BasketDAO() {
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
	
	public int countList(Map<String, Object> map) {
		int result = 0;
		try {
			String query = " SELECT COUNT(*) FROM basket ";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query); rs.next();
			result = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 갯수 조회중 예외 발생");
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	public List<ProductDTO> showBasket(Map<String, Object> map){
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			String query = ""
					+ " SELECT B.id, B.amount, P.* "
					+ " FROM basket B INNER JOIN products P "
					+ " 	ON B.product_idx = P.product_idx "
				    + " WHERE id = ? "
				    + " ORDER BY basket_idx DESC "
				    + " LIMIT ?, ? ";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("user_id").toString());
			psmt.setInt(2, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(3, Integer.parseInt(map.get("pageSize").toString()));
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProduct_idx(rs.getString("product_idx"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setId(rs.getString("id"));
				dto.setAmount(rs.getInt("amount"));
				dto.setReserves(rs.getInt("reserves"));
				dto.setPrice(rs.getInt("price"));
				dto.setSfile(rs.getString("sfile"));
				dto.setStock(rs.getInt("stock"));
				list.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("장바구니 리스트를 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return list;
	}
	
	public int insertBasket(BasketDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO basket ( "
					+ " product_idx, id, total_price, amount, total_reserves) "
					+ " VALUES ( "
					+ " ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getProduct_idx());
			psmt.setString(2, dto.getId());
			psmt.setInt(3, dto.getTotal_price());
			psmt.setInt(4, dto.getAmount());
			psmt.setInt(5, dto.getTotal_reserves());
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("장바구니 항목 추가 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean checkOverlap(String product_idx) {
		boolean result = false;
		try {
			String query = " SELECT COUNT(*) FROM basket WHERE product_idx = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, product_idx);
			rs = psmt.executeQuery(); rs.next();
			if(rs.getInt(1) != 0)
				result = true;
		}
		catch (Exception e) {
			result = false;
			System.out.println("중복 여부 확인 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public int updateBasket(String product_idx, int amount) {
		int result = 0;
		try {
			String query = " UPDATE basket SET amount = ? WHERE product_idx = ? ";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, amount);
			psmt.setString(2, product_idx);
			result = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			result = 0;
			System.out.println("중복 여부 확인 중 예외 발생");
			e.printStackTrace();
		}
		return result;
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
