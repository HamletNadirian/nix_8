package ua.com.alevel.count_characters;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class NumberOfEachCharacter {

    public static HashMap<Character, Integer> listLetter;
    public void calculateEachCharacter(BufferedReader reader) throws IOException {
    String inp = reader.readLine();
    char[] letters = inp.toCharArray();
    listLetter = new HashMap<>();
        for (int i = 0; i < inp.length(); i++) {
        if (Character.isLetter(letters[i])) {
            if (listLetter.containsKey(letters[i]))
                listLetter.put(letters[i], listLetter.get(letters[i]) + 1);
            else
                listLetter.put(letters[i], 1);
        }
    }
    TreeMap<Character,Integer> sortedTreeMap = new TreeMap<>(listLetter);
       // System.out.println(sorted);
        Set<Map.Entry<Character, Integer>> entries = sortedTreeMap.entrySet();
        int index = 1;
        //using for loop
        for(Map.Entry<Character, Integer> entry : entries){
            System.out.println((index++)+". "+entry.getKey() + "-" + entry.getValue() );
        }    }
}
