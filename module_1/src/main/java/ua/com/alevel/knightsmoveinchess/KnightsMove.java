package ua.com.alevel.knightsmoveinchess;

import java.io.BufferedReader;
import java.io.IOException;

public class KnightsMove {

	static void checkMove(int x1, int y1, int x2, int y2) {
		int dx = Math.abs(x1 - x2);
		int dy = Math.abs(y1 - y2);
		if ((dx == 1 && dy == 2) || (dx == 2 && dy == 1)) {
			System.out.println("Можно передвинуть");
		} else System.out.println("Нельзя");

	}

	public void inputCoordinate(BufferedReader bufferedReader) {
		try {
			System.out.println("Введите х1:");
			int x1 = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Введите y1:");
			int y1 = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Введите х2:");
			int x2 = Integer.parseInt(bufferedReader.readLine());
			System.out.println("Введите y2:");
			int y2 = Integer.parseInt(bufferedReader.readLine());
			checkMove(x1, y1, x2, y2);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
