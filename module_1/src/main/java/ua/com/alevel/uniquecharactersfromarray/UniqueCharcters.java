package ua.com.alevel.uniquecharactersfromarray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class UniqueCharcters {

    public static int counterUnique(String []array){

        Set<String> uniqueSet = new HashSet<>();
        for (int i = 0; i < array.length; i++) {
            uniqueSet.add(array[i]);
        }
        return uniqueSet.size();
    }
    public  void inputString(BufferedReader  reader) throws IOException {
        System.out.println("Введите символы через пробел:");
          reader = new BufferedReader(new InputStreamReader(System.in));
            String str[] = reader.readLine().split(" ");
            System.out.println("Число у никальных символов:"+counterUnique(str));
    }

}
