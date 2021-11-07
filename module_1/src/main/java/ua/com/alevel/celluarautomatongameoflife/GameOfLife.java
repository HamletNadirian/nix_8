package ua.com.alevel.celluarautomatongameoflife;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameOfLife {

	static int[][] board;

	public void initLife() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int m = 5;
		int n = 5;
		board = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] = (int) ((1 + Math.random() * 10) % 2);
			}
		}
		GameOfLife gameOfLife = new GameOfLife();
		System.out.println("Сгенерировано:");
		gameOfLife.printBoard(m, n);
		System.out.println();
		System.out.println("Введите количество поколения:");
		int countNext = Integer.parseInt(reader.readLine());
		for (int i = 0; i < countNext; i++) {
			gameOfLife.next(m, n);
			gameOfLife.printBoard(m, n);
			System.out.println();
		}
	}

	public void printBoard(int m, int n) {
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 1)
					System.out.print('*');
				else
					System.out.print('.');
			}
			System.out.println();
		}
	}

	public int getState(int x, int y, int m, int n) {
		if (x < 0 || x >= m) {
			return 0;
		}
		if (y < 0 || y >= n) {
			return 0;
		}
		return board[x][y];
	}

	int alive(int x, int y, int m, int n) {
		int neighbors = 0;
		if (getState(x - 1, y - 1, m, n) == 1) neighbors++;
		if (getState(x, y - 1, m, n) == 1) neighbors++;
		if (getState(x + 1, y - 1, m, n) == 1) neighbors++;
		if (getState(x - 1, y, m, n) == 1) neighbors++;
		if (getState(x + 1, y, m, n) == 1) neighbors++;
		if (getState(x - 1, y + 1, m, n) == 1) neighbors++;
		if (getState(x, y + 1, m, n) == 1) neighbors++;
		if (getState(x + 1, y + 1, m, n) == 1) neighbors++;
		return neighbors;
	}

	public void next(int m, int n) {
		int[][] newBoard = new int[m][n];
		for (int y = 0; y < m; y++) {
			for (int x = 0; x < n; x++) {
				int aliveNeighbours = alive(x, y, m, n);

				if (getState(x, y, m, n) == 1) {
					if (aliveNeighbours < 2) {
						newBoard[x][y] = 0;
					} else if (aliveNeighbours == 2 || aliveNeighbours == 3) {
						newBoard[x][y] = 1;
					} else if (aliveNeighbours > 3) {
						newBoard[x][y] = 0;
					}
				} else {
					if (aliveNeighbours == 3) {
						newBoard[x][y] = 1;
					}
				}

			}
		}
		board = newBoard;
	}
}
