package ua.com.alevel.end_lesson;

import java.io.BufferedReader;
import java.io.IOException;

public class EndOfLesson {

    static final int lessonDuration = 45;
    static final int shortBreak = 5;
    static final int longBreak = 15;
    static final int startLesson = 9;
    static final int hourToMinute = 60;

    public void calculateEndLesson(BufferedReader reader) throws IOException {
        try {
            Integer numLesson = Integer.valueOf(reader.readLine());
            if (1 <= numLesson && numLesson <= 10) {
                int calcShortBreak = (numLesson / 2);
                int calcLongBreak = (numLesson - calcShortBreak - 1);
                int allTime = (lessonDuration * numLesson + (calcShortBreak * shortBreak) + (calcLongBreak * longBreak));
                int hour = startLesson + (allTime / hourToMinute);
                int minute = allTime % hourToMinute;
                System.out.println("Входные данные: " + numLesson + "\n" + "Выходные данные: " + hour + ":" + minute);
            } else System.out.println("Диапазон чисел должен быть от 1 до 10");
        } catch (NumberFormatException exception) {
            System.out.println("Not number");
        }
    }
}
