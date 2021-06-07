package member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class MemberDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public MemberDAO() {
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
	
	public boolean isMember(String id) {
		boolean result = false;
		try {
			String query = " SELECT COUNT(*) FROM membership WHERE id = ? "; 
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery(); rs.next();
			if(rs.getInt(1) > 0)
				result = true;
		}
		catch(Exception e) {
			System.out.println("아이디 중복 확인 시 예외 발생");
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean isMember(String id, String pass) {
		boolean result = false;
		try {
			String query = " SELECT COUNT(*) FROM membership WHERE id = ? AND pass = ? "; 
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			psmt.setString(2, pass);
			rs = psmt.executeQuery(); rs.next();
			if(rs.getInt(1) > 0)
				result = true;
		}
		catch(Exception e) {
			System.out.println("회원 여부 확인 시 예외 발생");
			result = false;
			e.printStackTrace();
		}
		return result;
	}
	
	public MemberDTO getMemberInfo(String id) {
		MemberDTO dto = new MemberDTO();
		try {
			String query = " SELECT * FROM membership WHERE id = ? "; 
			psmt = con.prepareStatement(query);
			psmt.setString(1, id);
			rs = psmt.executeQuery(); rs.next();
			dto.setName(rs.getString("name"));
			dto.setId(rs.getString("id"));
			dto.setPass(rs.getString("pass"));
			dto.setTel(rs.getString("tel"));
			dto.setMobile(rs.getString("mobile"));
			dto.setEmail(rs.getString("email"));
			dto.setOpen_email(rs.getString("open_email"));
			dto.setAddress(rs.getString("address"));
		}
		catch(Exception e) {
			System.out.println("멤버 정보 조회 시 예외 발생");
			e.printStackTrace();
		}
		return dto;
	}
	
	public int insertMember(MemberDTO dto) {
		int result = 0;
		try {
			String query = " INSERT INTO membership "
					+ " (name, id, pass, tel, mobile, email, open_email, address) "
					+ "	VALUES(?,?,?,?,?,?,NVL(?, 0),?) "; 
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getId());
			psmt.setString(3, dto.getPass());
			psmt.setString(4, dto.getTel());
			psmt.setString(5, dto.getMobile());
			psmt.setString(6, dto.getEmail());
			psmt.setString(7, dto.getOpen_email());
			psmt.setString(8, dto.getAddress());
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("아이디 중복 확인 시 예외 발생");
			e.printStackTrace();
		}
		return result;
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