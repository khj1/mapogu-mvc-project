package request;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import member.MemberDTO;

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
					+ " (name, isdis, disType, useDev, devType, tel, mobile, email, cake, cookie, reqDate, receiptType, etc) "
					+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
			
			psmt=con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getIsdis());
			psmt.setString(3, dto.getDisType());
			psmt.setString(4, dto.getUseDev());
			psmt.setString(5, dto.getDevType());
			psmt.setString(6, dto.getTel());
			psmt.setString(7, dto.getMobile());
			psmt.setString(8, dto.getEmail());
			psmt.setString(9, dto.getCake());
			psmt.setString(10, dto.getCookie());
			psmt.setString(11, dto.getReqDate());
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
	
	public List<FieldDTO> getAllRequestInfo() {
		List<FieldDTO> list = new ArrayList<FieldDTO>();
		try {
			String query = " SELECT * FROM request_fieldstudy "; 
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				FieldDTO dto = new FieldDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setMobile(rs.getString("mobile"));
				dto.setReqDate(rs.getString("reqDate"));
				dto.setReceiptType(rs.getString("receiptType"));
				
				list.add(dto);
			}
		}
		catch(Exception e) {
			System.out.println("신청 정보 조회 시 예외 발생");
			e.printStackTrace();
		}
		return list;
	}
	
	public FieldDTO selectView(String idx) {
		FieldDTO dto = new FieldDTO();
		try {
			String query = " SELECT * FROM request_fieldstudy WHERE idx = ? "; 
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			
			rs = psmt.executeQuery(); rs.next();
			
			dto.setIdx(rs.getString("idx"));
			dto.setName(rs.getString("name"));
			dto.setIsdis(rs.getString("isdis"));
			dto.setDisType(rs.getString("disType"));
			dto.setUseDev(rs.getString("useDev"));
			dto.setDevType(rs.getString("devType"));
			dto.setTel(rs.getString("tel"));
			dto.setEmail(rs.getString("email"));
			dto.setMobile(rs.getString("mobile"));
			dto.setCake(rs.getString("cake"));
			dto.setCookie(rs.getString("cookie"));
			dto.setReqDate(rs.getString("reqDate"));
			dto.setReceiptType(rs.getString("receiptType"));
			dto.setEtc(rs.getString("etc"));
		}
		catch(Exception e) {
			System.out.println("신청 정보 조회 시 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	// 자원 해제
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
