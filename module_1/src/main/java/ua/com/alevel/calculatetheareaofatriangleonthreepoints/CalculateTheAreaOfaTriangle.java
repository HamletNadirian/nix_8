package ua.com.alevel.calculatetheareaofatriangleonthreepoints;

import java.io.BufferedReader;
import java.io.IOException;

public class CalculateTheAreaOfaTriangle {

	static double calculateTheArea(double a, double b, double c) {
		if (a < 0 || b < 0 || c < 0 || (a + b <= c) || a + c <= b || b + c <= a) {
			System.out.println("Такого треугольника не сущесвует.");
			System.exit(0);
		}
		double p = (a + b + c) / 2;
		return Math.sqrt(p * (p - a) * (p - b) * (p - c));
	}



	public void inputSide(BufferedReader bufferedReader) {
		try {
			System.out.println("Введите сторону A: ");
			int A = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Введите сторону B: ");
			int B = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Введите сторону C: ");
			int C = Integer.parseInt(bufferedReader.readLine());
			double result = calculateTheArea(A,B,C);
			System.out.println("Площадь треугольника ABC: "+result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
