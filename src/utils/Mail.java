package utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

public class Mail {
	
	
	public static void sendPassword(Map<String, String> map)  throws AddressException {
		Map<String, Object> mailInfo = new HashMap<String, Object>();
		
		String name = map.get("name");
		String pass = map.get("pass");
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h4>안녕하세요. ");
		sb.append("<h4>마포구립 장애인 직업 재활 센터입니다.</h4>");    
		sb.append("<h4>" + name + "님이 요청하신 비밀번호는 다음과 같습니다.</h4>\n");    
		sb.append("<ul><li>" + pass + "</li></ul>");    
		
		String html = sb.toString();
		String subject = name + "님이 요청하신 비밀번호입니다.";
		String toEmail = map.get("email");
	   
		InternetAddress[] toAddr = new InternetAddress[1];
	    toAddr[0] = new InternetAddress(toEmail);
		
		mailInfo.put("subject", subject);
		mailInfo.put("toAddr", toAddr);
		mailInfo.put("html", html);
		
		sendEmail(mailInfo);
	}
	
	public static void sendRuest_clean(Map<String, String> map) throws AddressException {
		Map<String, Object> mailInfo = new HashMap<String, Object>();

		StringBuffer sb = new StringBuffer();
	    sb.append("<h4>" + map.get("name") + "님의 블루 클리닝 견적 의뢰 신청 내용은 다음과 같습니다.</h4>\n");    
	    sb.append("<ul>");    
	    sb.append("<li>이름: " + map.get("name") + "</li>");    
	    sb.append("<li>주소: " + map.get("addr") + "</li>");    
	    sb.append("<li>연락처: " + map.get("tel") + "</li>");    
	    sb.append("<li>휴대전화: " + map.get("mobile") + "</li>");    
	    sb.append("<li>이메일: " + map.get("email") + "</li>");    
	    sb.append("<li>청소 분류: " + map.get("cleanType") + "</li>");    
	    sb.append("<li>등기 평수: " + map.get("acreage") + "</li>");    
	    sb.append("<li>청소 희망 날짜: " + map.get("reqDate") + "</li>");    
	    sb.append("<li>접수 분류: " + map.get("receiptType") + "</li>");    
	    sb.append("<li>기타 특이사항: " + map.get("etc") + "</li>");    
	    sb.append("</ul>");    
	    
	    String html = sb.toString();
	    String subject = map.get("name") + "님의 블루 클리닝 견적 의뢰 신청 내용입니다.";
	    String toEmail = map.get("email");
	    
	    InternetAddress[] toAddr = new InternetAddress[2];
	    toAddr[0] = new InternetAddress(toEmail);
	    toAddr[1] = new InternetAddress("dhepd8849@gmail.com");
		
	   
		mailInfo.put("subject", subject);
		mailInfo.put("toAddr", toAddr);
		mailInfo.put("html", html);
		
		sendEmail(mailInfo);
	}
	public static void sendRuest_field(Map<String, String> map) throws AddressException  {
		Map<String, Object> mailInfo = new HashMap<String, Object>();
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h4>" + map.get("name") + "님의 체험학습 신청 내용은 다음과 같습니다.</h4>\n");    
		sb.append("<ul>");    
		sb.append("<li>이름: " + map.get("name") + "</li>");    
		sb.append("<li>장애여부: " + map.get("isdis") + "</li>");    
		sb.append("<li>주요장애유형: " + map.get("disType") + "</li>");    
		sb.append("<li>보장구 사용유무: " + map.get("useDev") + "</li>");    
		sb.append("<li>보장구 명: " + map.get("devType") + "</li>");    
		sb.append("<li>연락처: " + map.get("tel") + "</li>");    
		sb.append("<li>담당자 휴대전화: " + map.get("mobile") + "</li>");    
		sb.append("<li>이메일: " + map.get("mobile") + "</li>");    
		sb.append("<li>케익체험: " + map.get("cake") + "</li>");    
		sb.append("<li>쿠키체험: " + map.get("cookie") + "</li>");    
		sb.append("<li>체험희망날짜: " + map.get("reqDate") + "</li>");    
		sb.append("<li>접수종류 구분: " + map.get("receiptType") + "</li>");    
		sb.append("<li>기타 특이사항: " + map.get("etc") + "</li>");    
		sb.append("</ul>");    

		String html = sb.toString();
		String subject = map.get("name") + "님의 체험학습 신청 내용입니다.";
		String toEmail = map.get("email");
	   
		InternetAddress[] toAddr = new InternetAddress[2];
	    toAddr[0] = new InternetAddress(toEmail);
	    toAddr[1] = new InternetAddress("dhepd8849@gmail.com");
		
		
		mailInfo.put("subject", subject);
		mailInfo.put("toAddr", toAddr);
		mailInfo.put("html", html);
		
		sendEmail(mailInfo);
	}
	
	private static void sendEmail(Map<String, Object> map) {
		final String bodyEncoding = "UTF-8"; //콘텐츠 인코딩
		final String username = "dhepd8849@gmail.com";         
		final String password = "fkdnswmf48!";
	    
		String fromEmail = "dhepd8849@naver.com";
		String fromUsername = "마포구립 장애인 직업 재활 센터";
	    String subject = map.get("subject").toString();
	    String html = map.get("html").toString();
	    
	    InternetAddress[] toAddr = (InternetAddress[])map.get("toAddr");
	    
	    // 메일 옵션 설정
	    Properties props = new Properties();    
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "465");
	    props.put("mail.smtp.auth", "true");
	 
	    props.put("mail.smtp.quitwait", "false");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.socketFactory.fallback", "false");
	    
	    try {
	      // 메일 서버  인증 계정 설정
	      Authenticator auth = new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication(username, password);
	        }
	      };
	      
	      // 메일 세션 생성
	      Session session = Session.getInstance(props, auth);
	      
	      // 메일 송/수신 옵션 설정
	      Message message = new MimeMessage(session);
	      message.setFrom(new InternetAddress(fromEmail, fromUsername));
	      message.setRecipients(RecipientType.TO, toAddr);
	      message.setSubject(subject);
	      message.setSentDate(new Date());
	      
	      // 메일 콘텐츠 설정
	      Multipart mParts = new MimeMultipart();
	      MimeBodyPart mTextPart = new MimeBodyPart();
	      MimeBodyPart mFilePart = null;
	 
	      // 메일 콘텐츠 - 내용
	      mTextPart.setText(html, bodyEncoding, "html");
	      mParts.addBodyPart(mTextPart);
	            
	      // 메일 콘텐츠 설정
	      message.setContent(mParts);
	      
	      // MIME 타입 설정
	      MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	      MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	      MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	      MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	      MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	      MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	      CommandMap.setDefaultCommandMap(MailcapCmdMap);
	 
	      // 메일 발송
	      Transport.send( message );
	      
	    } catch ( Exception e ) {
	      e.printStackTrace();
	    }
	}
}

