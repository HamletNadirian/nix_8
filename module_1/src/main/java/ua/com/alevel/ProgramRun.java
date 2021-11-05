package ua.com.alevel;

import ua.com.alevel.calculatetheareaofatriangleonthreepoints.CalculateTheAreaOfaTriangle;
import ua.com.alevel.celluarautomatongameoflife.GameOfLife;
import ua.com.alevel.knightsmoveinchess.KnightsMove;
import ua.com.alevel.maxdepthtreenode.TreeNode;
import ua.com.alevel.uniquecharactersfromarray.UniqueCharcters;
import ua.com.alevel.validinputbrackets.ValidBrackets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {
	private static void preview() {
		System.out.println("Для того,чтобы запустить задачу №1 уровня #1#. Вернуть число уникальных символов. Нажмите - 1");
		System.out.println("Для того,чтобы запустить задачу №2 уровня #1#. Ход коня по бесконечной шахматной доске. Нажмите - 2");
		System.out.println("Для того,чтобы запустить задачу №3 уровня #1#. Вычислить площадь треугольника ABC. Нажмите - 3");

		System.out.println("Для того,чтобы запустить задачу №1 уровня #2#. Определить является ли входная строка допустимой' (', ' )', ' {', ' }', ' [' и ' ]'. Нажмите - 4");
		System.out.println("Для того,чтобы запустить задачу №2 уровня #2#. Нахождение максимальной глубины бинарного дерева. Нажмите - 5");

		System.out.println("Для того,чтобы запустить задачу №1 уровня #3#. Игра Жизни . Нажмите - 6");
		System.out.println("Чтобы завершить работу програми нажмите - 0");
		System.out.println("Сделайте ваш выбор:");
		System.out.println();
	}

	public static void menu() {
		String choice;
		preview();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			while ((choice = reader.readLine()) != null) {
				switch (choice) {
					case "1": {
						new UniqueCharcters().inputString(reader);
					}
					break;
					case "2": {
						new KnightsMove().inputCoordinate(reader);
					}
					break;
					case "3": {
						new CalculateTheAreaOfaTriangle().inputSide(reader);
					}
					break;
					case "4": {
						new ValidBrackets().inputStringWithBrackets(reader);
					}
					break;
					case "5": {
						new TreeNode().initTreeNode();
					}
					break;
					case "6": {
						new GameOfLife().initLife();
					}
					break;
					case "0": {
						System.exit(0);
					}
					break;
					default:
						System.out.println("Сделайте правильный выбор!");
						break;
				}
				preview();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
