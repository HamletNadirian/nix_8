package ua.com.alevel.validinputbrackets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class ValidBrackets {

	static boolean isBalancedBrackets(String exp) {
		ArrayList<Character> toClose = new ArrayList<>();
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) == '(') toClose.add(')');
			else if (exp.charAt(i) == '[')
				toClose.add(']');
			else if (exp.charAt(i) == '{')
				toClose.add('}');
			else if (exp.charAt(i) == ')' || exp.charAt(i) == ']' || exp.charAt(i) == '}') {
				if (toClose.isEmpty()) {
					return false;
				}
				if ((toClose.get(toClose.size() - 1)) != exp.charAt(i)) {
					return false;
				}
				toClose.remove(toClose.size() - 1);
			}
		}
		return toClose.isEmpty();
	}

	public void inputStringWithBrackets(BufferedReader bufferedReader) {
		try {
			String string = bufferedReader.readLine();
			if (isBalancedBrackets(string)) System.out.println("Строка валидная");
			else System.out.println("Строка не валидная");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

