package calendar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import community.CommunityDTO;

public class CalendarDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public CalendarDAO() {
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
	
//	public int insertWrite(CommunityDTO dto) {
//		
//	}
	
	public List<CommunityDTO> selectList(int year, int month){
		List<CommunityDTO> list = new ArrayList<CommunityDTO>();
		try {
			String query = ""
					+ " SELECT board_idx, title, calDate FROM multi_board "
					+ " WHERE flag='calendar' AND YEAR(caldate) = ? AND MONTH(caldate) = ? ";
			
			psmt = con.prepareStatement(query);
			psmt.setInt(1, year);
			psmt.setInt(2, month);
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				CommunityDTO dto = new CommunityDTO();
				dto.setBoard_idx(rs.getString("board_idx"));
				dto.setTitle(rs.getString("title"));
				dto.setCaldate(rs.getString("caldate"));
				
				list.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("캘린더 게시물 정보를 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return list;
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
