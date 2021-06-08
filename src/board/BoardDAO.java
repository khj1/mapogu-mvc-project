package board;

import java.sql.Connection;   
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardDAO {
	public Connection con;
	public Statement stmt;
	public PreparedStatement psmt;
	public ResultSet rs;
	
	public BoardDAO() {
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
	
	// 게시물의 갯수 조회
	public int countList(String flag) {
		int result = 0;
		try {
			String query = " SELECT COUNT(*) FROM multi_board WHERE flag = ? ";
			psmt = con.prepareStatement(query);
			psmt.setString(1, flag);
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
	
	// 미리보기를 위해 게시물의 일부분만 조회
	public List<BoardDTO> previewList(String flag){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			String query = ""
					+ " SELECT board_idx, title, DATE_FORMAT(postdate, '%Y-%m-%d'), ofile, sfile "
					+ " FROM multi_board "
					+ " WHERE flag=? "
					+ " ORDER BY board_idx DESC "
					+ " LIMIT 0, 6 ";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, flag);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_idx(rs.getString(1));
				
				//길이가 긴 제목을 문자열 처리
				String title = rs.getString(2);
				if(title.length() > 20)
					title = title.substring(0, 20) + "...";
				dto.setTitle(title);
				
				dto.setPostdate(rs.getString(3));
				dto.setOfile(rs.getString(4));
				dto.setSfile(rs.getString(5));
				list.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물을 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return list;
	}
	
	// 전체 게시물 조회
	public List<BoardDTO> selectList(Map<String, Object> map){
		List<BoardDTO> list = new ArrayList<BoardDTO>();
		try {
			String query = ""
					+ " SELECT B.*, M.name "
					+ " FROM multi_board B INNER JOIN membership M "
					+ " ON B.id = M.id "
					+ " WHERE 1=1 ";
			
			if(map.get("searchWord") != null) {
				query += " AND " + map.get("searchField") + " "
						+ "LIKE '%" + map.get("searchWord") + "%'"; 
						
			}
			
			query += " AND flag = ? "
				  + " ORDER BY board_idx DESC "
				  + " LIMIT ?, ? ";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, map.get("flag").toString());
			psmt.setInt(2, Integer.parseInt(map.get("start").toString()));
			psmt.setInt(3, Integer.parseInt(map.get("pageSize").toString()));
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_idx(rs.getString("board_idx"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				
				//길이가 긴 제목을 문자열 처리
				String title = rs.getString("title");
				if(title.length() > 20)
					title = title.substring(0, 20) + "...";
				dto.setTitle(title);
				
				dto.setContent(rs.getString("content").replaceAll("\r\n", "<br/>"));
				dto.setPass(rs.getString("pass"));
				dto.setPostdate(rs.getString("postdate"));
				dto.setOfile(rs.getString("sfile"));
				dto.setSfile(rs.getString("ofile"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setLikecount(rs.getInt("likecount"));
				dto.setHatecount(rs.getInt("hatecount"));
				list.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물을 불러오는 중 예외 발생");
			e.printStackTrace();
		}
		return list;
	}
	
	public BoardDTO selectView(String board_idx) {
		BoardDTO dto = new BoardDTO();
		String query = ""
				+ " SELECT B.*, M.name, M.email "
				+ " FROM multi_board B INNER JOIN membership M "
				+ " ON B.id = M.id "
				+ " WHERE board_idx = ? ";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setBoard_idx(rs.getString("board_idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content").replaceAll("\r\n", "<br/>"));
				dto.setPostdate(rs.getString("postdate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setEmail(rs.getString("email"));
				dto.setPass(rs.getString("pass"));
				dto.setVisitcount(rs.getInt("visitcount"));
				dto.setDowncount(rs.getInt("downcount"));
				dto.setLikecount(rs.getInt("likecount"));
				dto.setHatecount(rs.getInt("hatecount"));
			}
		}
		catch(Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생"); 
			e.printStackTrace();
		}
		return dto;
	}
	
	public void updateVisitCount(String board_idx) {
		String query = "UPDATE multi_board SET "
				+ " visitcount = visitcount + 1 "
				+ " WHERE board_idx = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_idx);
			psmt.executeQuery();
			
		}
		catch(Exception e) {
			System.out.println("게시물 조회수 증가 처리 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public void updateDownCount(String board_idx) {
		String query = "UPDATE multi_board SET "
				+ " downcount = downcount + 1 "
				+ " WHERE board_idx = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_idx);
			psmt.executeQuery();
			
		}
		catch(Exception e) {
			System.out.println("다운로드 횟수 증가 처리 중 예외 발생");
			e.printStackTrace();
		}
	}
	public void updateLikeCount(String board_idx) {
		String query = "UPDATE multi_board SET "
				+ " likecount = likecount + 1 "
				+ " WHERE board_idx = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_idx);
			psmt.executeQuery();
			
		}
		catch(Exception e) {
			System.out.println("다운로드 횟수 증가 처리 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public void updateHateCount(String board_idx) {
		String query = "UPDATE multi_board SET "
				+ " hatecount = hatecount + 1 "
				+ " WHERE board_idx = ?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, board_idx);
			psmt.executeQuery();
			
		}
		catch(Exception e) {
			System.out.println("다운로드 횟수 증가 처리 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO multi_board ( "
					+ " id, title, content, ofile, sfile, pass, flag) "
					+ " VALUES ( "
					+ " ?, ?, ?, ?, ?, ?, ?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass()); 
			psmt.setString(7, dto.getFlag());
			result = psmt.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
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