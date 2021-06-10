package request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class FieldDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public FieldDAO() {
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
	
	public int insertWrite(FieldDTO dto) {
		int result = 0;
		try {
			String query = ""
					+ " INSERT INTO request_fieldstudy "
					+ " (name, isdis, disType, useDev, typeDev, tel, mobile, email, cake, cookie, date, receiptType, etc) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getIsdis());
			psmt.setString(3, dto.getDisType());
			psmt.setString(4, dto.getUseDev());
			psmt.setString(5, dto.getTypeDev());
			psmt.setString(6, dto.getTel());
			psmt.setString(7, dto.getMobile());
			psmt.setString(8, dto.getEmail());
			psmt.setString(9, dto.getCake());
			psmt.setString(10, dto.getCookie());
			psmt.setString(11, dto.getDate());
			psmt.setString(12, dto.getReceiptType());
			psmt.setString(13, dto.getEtc());
			
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
