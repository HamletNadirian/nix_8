package ua.com.alevel.sumnumber;

import java.io.BufferedReader;
import java.io.IOException;

/*
* На входе принимается строку с консоли и вычленяет все цифры (от 0 - 9) и находит
их сумму
* */
public class CalculationOfSumNumbers {

    public void calculateSumNum(BufferedReader reader) throws IOException {
        String text = reader.readLine();
        char symb[] = text.toCharArray();
        System.out.println("Входные данные: " + text);
        int res = 0;
        for (int i = 0; i < symb.length; i++) {
            if (Character.isDigit(symb[i]))
                res = res + Character.getNumericValue(symb[i]);
        }
        System.out.println("Выходные данные: " + res);
    }
}
