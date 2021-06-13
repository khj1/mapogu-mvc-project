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
			String query = " SELECT COUNT(*) FROM basket WHERE id = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("user_id").toString());
			rs = psmt.executeQuery(); rs.next();
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
				    + " WHERE id = ? ";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("user_id").toString());
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
	
	// 물품 주문 시 장바구니에서 해당 제품을 제거하는 기능
	public int deleteBasket(String user_id) {
		int result = 0;
		try {
			String query = " DELETE FROM basket WHERE id = ? AND basket_check = 'Y' ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("장바구니에서 상품 삭제 중 오류 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	// 삭제하기 버튼을 통해 장바구니에서 상품을 제거하는 기능
	public int deleteBasket(String user_id, String product_idx) {
		int result = 0;
		try {
			String query = " DELETE FROM basket WHERE id = ? AND product_idx = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
			psmt.setString(2, product_idx);
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("장바구니에서 상품 삭제 중 오류 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean checkOverlap(String product_idx, String user_id) {
		boolean result = false;
		try {
			String query = " SELECT COUNT(*) FROM basket WHERE product_idx = ? AND id = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, product_idx);
			psmt.setString(2, user_id);
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
	
	public int updateBasket(BasketDTO dto) {
		int result = 0;
		try {
			String query = " UPDATE basket "
					+ "	SET amount = ?, total_price = ?, total_reserves = ? "
					+ " WHERE product_idx = ? AND id = ? ";
			psmt = con.prepareStatement(query);
			psmt.setInt(1, dto.getAmount());
			psmt.setInt(2, dto.getTotal_price());
			psmt.setInt(3, dto.getTotal_reserves());
			psmt.setString(4, dto.getProduct_idx());
			psmt.setString(5, dto.getId());
			result = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			result = 0;
			System.out.println("중복 여부 확인 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public List<ProductDTO> buyBasketList(String user_id){
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			String query = ""
					+ " SELECT B.*, P.*"
					+ " FROM basket B INNER JOIN products P "
					+ " 	ON B.product_idx = P.product_idx "
				    + " WHERE id = ? AND basket_check = 'Y' " ;
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, user_id);
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
				list.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("장바구니 리스트를 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return list;
	}
	
	public int updateCheck(BasketDTO dto) {
		int result = 0;
		try {
			String query = " UPDATE basket "
					+ "	SET basket_check = ? "
					+ " WHERE product_idx = ? AND id = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getBasket_check());
			psmt.setString(2, dto.getProduct_idx());
			psmt.setString(3, dto.getId());
			result = psmt.executeUpdate();
			
		}
		catch (Exception e) {
			result = 0;
			System.out.println("장바구니 체크 변경 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public void updateCheckAll(String user_id, String basket_check) {
		try {
			String query = " UPDATE basket SET basket_check= ? WHERE id = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, basket_check);
			psmt.setString(2, user_id);
			psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("장바구니 체크 변경 중 예외 발생");
			e.printStackTrace();
		}
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
