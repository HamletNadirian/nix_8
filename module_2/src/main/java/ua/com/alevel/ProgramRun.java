package ua.com.alevel;

import ua.com.alevel.correctdateformat.FormatDate;
import ua.com.alevel.firstuniquename.UniqueName;
import ua.com.alevel.shortestway.ShortestWay;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProgramRun {

	private static void preview() {
		System.out.println("Для того,чтобы запустить задачу №1 уровня #1#. Вернуть список дат (строковая запись) в формате 20200405. Нажмите - 1");
		System.out.println("Для того,чтобы запустить задачу №2 уровня #1#. Найти первое уникальное имя. Нажмите - 2");
		System.out.println("Для того,чтобы запустить задачу №3 уровня #1#. Задача - найти самый выгодный путь между двумя городами. Нажмите - 3");
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
						FormatDate.testDate("input1.txt");
					}
					break;
					case "2": {
						UniqueName.testUniqueName("input2.txt");
					}
					break;
					case "3": {
						ShortestWay.readFile("input3.txt");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
