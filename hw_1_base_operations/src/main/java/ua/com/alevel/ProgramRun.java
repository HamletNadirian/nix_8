package ua.com.alevel;

import ua.com.alevel.count_characters.NumberOfEachCharacter;
import ua.com.alevel.end_lesson.EndOfLesson;
import ua.com.alevel.sum_number.CalculationOfSumNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProgramRun {

    private static void preview() {
        System.out.println("if you need first task, please select 1");
        System.out.println("if you need second task, please select 2");
        System.out.println("if you need second task, please select 3");
        System.out.println("if you need exit task, please select 0");
        System.out.println("Select you event:");
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
                        new NumberOfEachCharacter().calculateEachCharacter(reader);
                    }
                    break;
                    case "2": {
                        new CalculationOfSumNumbers().calculateSumNum(reader);
                    }
                    break;
                    case "3": {
                        new EndOfLesson().calculateEndLesson(reader);
                    }
                    break;
                    case "0": {
                        System.exit(0);
                    }
                    break;
                    default:
                        System.out.println("Select correct choice");
                        break;
                }
                preview();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
