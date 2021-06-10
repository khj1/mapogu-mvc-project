package utils;

public class FormatFunc {
	public static String moneyFormat(int number) {
		String result = String.format("%,d", number);
		return result;
	}
}
