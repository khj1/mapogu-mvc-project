package request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class CleanDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public CleanDAO() {
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
	
	public int insertWrite(CleanDTO dto) {
		int result = 0;
		try {
			String query = ""
					+ " INSERT INTO request_cleaning "
					+ " (name, addr, tel, mobile, email, cleanType, acreage, date, receiptType, etc) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getAddr());
			psmt.setString(3, dto.getTel());
			psmt.setString(4, dto.getMobile());
			psmt.setString(5, dto.getEmail());
			psmt.setString(6, dto.getCleanType());
			psmt.setString(7, dto.getAcreage());
			psmt.setString(8, dto.getDate());
			psmt.setString(9, dto.getReceiptType());
			psmt.setString(10, dto.getEtc());
			
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			result = 0;
			System.out.println("신청 정보 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
}
