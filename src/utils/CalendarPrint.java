package utils;

import java.util.Calendar;
import java.util.Scanner;

import org.json.simple.JSONArray;

public class CalendarPrint {
	String[][] calArr = new String[6][7];

	public static String printCalendar(int year, int month){
		Calendar c = Calendar.getInstance(); //Calendar 객체 생성

		//set메소드를 통해 연도 월 일 을 설정
		c.set(Calendar.YEAR, year); 
		c.set(Calendar.MONTH, month-1); //시작 일이 1이 아닌 0부터 시작하여                         1을 빼줌니다
		c.set(Calendar.DAY_OF_MONTH, 1); // 처음 시작요일설정
		int dayofweek = c.get(Calendar.DAY_OF_WEEK);

		//일 = 1 월 =2 화 =3 ..
		c.set(Calendar.DAY_OF_MONTH, 32); //32 이상일땐 마지막 요일로 자동 설정
		int lastday = 32 - c.get(Calendar.DAY_OF_MONTH);
		int i =0;

		JSONArray jsonInnerArr = new JSONArray();
		JSONArray jsonOutterArr = new JSONArray();
		
		for(;i<dayofweek - 1; i++){
			jsonInnerArr.add("");
		}

		for(int day = 1; day<=lastday; day++,i++){
			if(i % 7 ==0){
				jsonOutterArr.add(jsonInnerArr);
				jsonInnerArr = new JSONArray();
			}
			jsonInnerArr.add(day);
		}
		return jsonOutterArr.toJSONString();
	}
	
	public static void main(String[] args) {
		System.out.println(printCalendar(2021, 6));
	}

}