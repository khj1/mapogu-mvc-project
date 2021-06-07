package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieManager {
	public static void makeCookie(HttpServletResponse response,
			String cName, String cValue, int cTime) {
		Cookie cookie = new Cookie(cName, cValue);
		cookie.setPath("/");
		cookie.setMaxAge(cTime);
		response.addCookie(cookie);
	}
	
	public static String readCookies(HttpServletRequest request, String cName) {
		Cookie[] cookies = request.getCookies();
		String getValue = "";
		if(cookies != null){
			for(Cookie c : cookies){
				String cookieName = c.getName();			
				String cookieValue = c.getValue();
				if(cookieName.equals(cName)) {
					getValue = cookieValue;
					break;
				}
			}
		}
		return getValue;
	}
	
	public static void deleteCookie(HttpServletResponse response, String cName) {
		/*
		쿠키는 삭제하기 위한 메소드가 별도로 존재하지 않는다.
		쿠키 값을 빈값으로 만들고, 유효시간을 0으로 설정하면 쿠키는 삭제된다.
		
		동일한 쿠키 이름이 이미 존재하면 새로운 데이터가 기존 데이터를 대체한다.
		 */
		makeCookie(response, cName, "", 0);
	}
}
