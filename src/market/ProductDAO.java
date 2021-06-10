package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public ProductDAO() {
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
			String query = " SELECT COUNT(*) FROM products ";
			
			if(map.get("searchWord") != null) {
				query += " AND " + map.get("searchField") + " "
						+ "LIKE '%" + map.get("searchWord") + "%'"; 
			}
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
	
	public List<ProductDTO> selectList(Map<String, Object> map){
		List<ProductDTO> list = new ArrayList<ProductDTO>();
		try {
			String query = ""
					+ " SELECT * FROM products ";
			
			if(map.get("searchWord") != null) {
				query += " WHERE " + map.get("searchField") + " "
						+ "LIKE '%" + map.get("searchWord") + "%'"; 
			}
			
			query += " ORDER BY product_idx DESC "
				   + " LIMIT ?, ? ";
			
			psmt = con.prepareStatement(query);
			psmt.setInt(1, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(2, Integer.parseInt(map.get("pageSize").toString()));
			rs = psmt.executeQuery();
			while(rs.next()) {
				ProductDTO dto = new ProductDTO();
				dto.setProduct_idx(rs.getString("product_idx"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setDescription(rs.getString("description"));
				dto.setReserves(rs.getInt("reserves"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				list.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물을 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return list;
	}
	
	public ProductDTO getProductInfo(String product_idx) {
		ProductDTO dto = new ProductDTO();
		String query = " SELECT * FROM products WHERE product_idx = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, product_idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setProduct_idx(rs.getString("product_idx"));
				dto.setProduct_name(rs.getString("product_name"));
				dto.setPrice(rs.getInt("price"));
				dto.setStock(rs.getInt("stock"));
				dto.setDescription(rs.getString("description"));
				dto.setReserves(rs.getInt("reserves"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
			}
		}
		catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생"); 
			e.printStackTrace();
		}
		return dto;
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
