package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import utils.JSFunction;

public class FileUtil {

	public static MultipartRequest uploadFile(HttpServletRequest req,
			String saveDirectory, int maxPostSize) {
		
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, saveDirectory, maxPostSize, "UTF-8");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return mr;
	}
	
	public static String renameFile(String fileName, String saveDirectory) {
		String nowTime = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
		int idx = fileName.lastIndexOf(".");
		String newFileName = nowTime + fileName.substring(idx, fileName.length());
		
		File oldFile = new File(saveDirectory + File.separator + fileName);
		File newFile = new File(saveDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}
	
	public static void deleteFile(HttpServletRequest req, String saveDirectory, String saveFileName) {
		String sDirectory = req.getServletContext().getRealPath(saveDirectory);
		File file = new File(sDirectory+ File.separator + saveFileName);
		if(file.exists())
			file.delete();
	}
	
	public static void downloadFile(HttpServletRequest req, HttpServletResponse resp,
			String directory, String sfileName, String ofileName) {
		
		String sDirectory = req.getServletContext().getRealPath(directory);
		try{	
			File file = new File(sDirectory, sfileName);
			InputStream iStream = new FileInputStream(file);
			
			String client = req.getHeader("User-Agent");
			if(client.indexOf("WOW64")==-1){
				ofileName = new String(ofileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			else{
				ofileName = new String(ofileName.getBytes("KSC5601"), "ISO-8859-1");
			}
			
			resp.reset();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + ofileName + "\"");
			resp.setHeader("Content-Length", ""+file.length() );
			
			//out.clear();
			
			OutputStream oStream = resp.getOutputStream();
			byte[] b = new byte[(int)file.length()];
			int readBuffer = 0;	
			while( (readBuffer = iStream.read(b)) > 0 ){
				System.out.println(readBuffer);
				oStream.write(b, 0, readBuffer);
			}	
			iStream.close();
			oStream.close();
		}
		catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
			JSFunction.alertBack(resp, "파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace(); 
		}
	}	
}
