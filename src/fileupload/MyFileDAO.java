package fileupload;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

public class MyFileDAO extends JConnect{
	public MyFileDAO(ServletContext application) {
		super(application);
	}
	
	public int myFileInsert(MyFileDTO dto) {
		int applyResult = 0;
		try {
			String query = " INSERT INTO myfile ( "
					+ " idx, name, title, cate, ofile, sfile ) "
					+ " VALUES (myfile_seq.nextval, ?, ?, ?, ?, ?)";
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getCate());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			
			applyResult = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("insert failed");
			e.printStackTrace();
		}
		return applyResult;
	}
	
	public List<MyFileDTO> myFileList(){
		List<MyFileDTO> fileList = new ArrayList<MyFileDTO>();
		String query = "SELECT * FROM myfile ORDER BY idx DESC";
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			
			while(rs.next()) {
				MyFileDTO dto = new MyFileDTO();
				dto.setIdx(rs.getString("idx"));
				dto.setName(rs.getString("name"));
				dto.setTitle(rs.getString("title"));
				dto.setCate(rs.getString("cate"));
				dto.setOfile(rs.getString("ofile"));
				dto.setSfile(rs.getString("sfile"));
				dto.setPostdate(rs.getString("postdate"));
				
				fileList.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("Select failed");
			e.printStackTrace();
		}
		return fileList;
	}
	
}
