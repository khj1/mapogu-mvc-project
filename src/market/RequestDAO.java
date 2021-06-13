package market;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

public class RequestDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public RequestDAO() {
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
	
	public int insertCleaningRequest(Map<String, String> map) {
		int result = 0;
		try {
			String query = "INSERT INTO request_cleaning ("
					+ "	name, addr, tel, mobile, email, cleanType, acreage, reqDate, receiptType, etc ) "
					+ " VALUES ( "
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("name")); 
			psmt.setString(2, map.get("addr"));
			psmt.setString(3, map.get("tel"));
			psmt.setString(4, map.get("mobile"));
			psmt.setString(5, map.get("email"));
			psmt.setString(6, map.get("cleanType"));
			psmt.setString(7, map.get("acreage"));
			psmt.setString(8, map.get("reqDate"));
			psmt.setString(9, map.get("receiptType"));
			psmt.setString(10, map.get("etc"));
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			result = 0;
			System.out.println("블루 클리닝 신청 내역 추가 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertFieldRequest(Map<String, String> map) {
		int result = 0;
		try {
			String query = "INSERT INTO request_fieldstudy ("
					+ "	name, isdis, disType, useDev, devType, tel, mobile, email, "
					+ " cake, cookie, reqDate, receiptType, etc ) "
					+ " VALUES ( "
					+ " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("name")); 
			psmt.setString(2, map.get("isdis"));
			psmt.setString(3, map.get("disType"));
			psmt.setString(4, map.get("useDev"));
			psmt.setString(5, map.get("devType"));
			psmt.setString(6, map.get("tel"));
			psmt.setString(7, map.get("mobile"));
			psmt.setString(8, map.get("email"));
			psmt.setString(9, map.get("cake"));
			psmt.setString(10, map.get("cookie"));
			psmt.setString(11, map.get("reqDate"));
			psmt.setString(12, map.get("receiptType"));
			psmt.setString(13, map.get("etc"));
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			result = 0;
			System.out.println("체험 학습 신청 내역 추가 중 예외 발생");
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
