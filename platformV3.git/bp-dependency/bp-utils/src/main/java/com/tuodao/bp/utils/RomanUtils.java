package com.tuodao.bp.utils;
//引入Scanner工具 

public class RomanUtils {
	/**
	 * @param s
	 *            - String Roman
	 * @return int number
	 */
	public static int romanToInt(String s) {
		if (s.length() < 1)
			return 0;
		int result = 0;
		int current = 0;
		int pre = singleRomanToInt(s.charAt(0));
		int temp = pre;
		for (int i = 1; i < s.length(); i++) {
			current = singleRomanToInt(s.charAt(i));
			if (current == pre)
				temp += current;
			else if (current > pre) {
				temp = current - temp;
			} else if (current < pre) {
				result += temp;
				temp = current;
			}
			pre = current;
		}
		result += temp;
		return result;

	}

	/**
	 * @param c
	 *            single Roman
	 * @return single number
	 */
	public static int singleRomanToInt(char c) {
		switch (c) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}

	/**
	 * @param n
	 *            - input single int
	 * @param nth
	 *            must start from 1; 1 <= nth <= 4
	 * @return String single Roman
	 */
	public static String singleDigitToRoman(int n, int nth) {
		if (n == 0) {
			return "";
		}
		nth = 2 * nth - 1; // nth must start from 1
		char singleRoman[] = { 'I', 'V', 'X', 'L', 'C', 'D', 'M', 'Z', 'E' }; // never
																				// use
																				// 'Z'
																				// &
																				// 'E'
		StringBuilder rsb = new StringBuilder("");
		if (n <= 3) {
			for (int i = 0; i < n; i++) {
				rsb.append(singleRoman[nth - 1]);
			}
			return rsb.toString();
		}
		if (n == 4) {
			rsb.append(singleRoman[nth - 1]);
			rsb.append(singleRoman[nth]);
			return rsb.toString();
		}
		if (n == 5) {
			return singleRoman[nth] + "";
		}
		if (n >= 6 && n <= 8) {
			rsb.append(singleRoman[nth]);
			for (int i = 0; i < (n - 5); i++) {
				rsb.append(singleRoman[nth - 1]);
			}
			return rsb.toString();
		}
		if (n == 9) {
			rsb.append(singleRoman[nth - 1]);
			rsb.append(singleRoman[nth + 1]);
			return rsb.toString();
		}
		return "ERROR!!!";
	}

	/**
	 * @param num
	 *            - input number within range 1 ~ 3999
	 * @return String Roman number
	 */
	public static String intToRoman(int num) {
		if (num < 1 || num > 3999) {
			return "ERROR input number is 1 ~ 3999";
		}
		int temp = num;
		String singleRoman[] = { "", "", "", "" };
		StringBuilder result = new StringBuilder("");
		int digits = 0; // 1 ~ 4
		while (temp != 0) {
			temp = temp / 10;
			digits++;
		}
		temp = num;
		int[] singleInt = new int[digits];
		for (int i = 0; i < digits; i++) {
			singleInt[i] = temp % 10;
			singleRoman[i] = singleDigitToRoman(temp % 10, i + 1);
			temp /= 10;
		}
		for (int i = digits - 1; i >= 0; i--) {
			result.append(singleRoman[i]);
		}
		return result.toString();
	}

	/**
	 * test main
	 */
	public static void main(String args[]) {
		for (int i = 1; i < 50; i++) {

			System.out.println("B" + intToRoman(i));
		}
	}
}
