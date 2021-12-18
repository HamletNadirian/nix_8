package ua.com.alevel.firstuniquename;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class UniqueName {
	private static String readFile(String nameFile) {
		String s = null;
       try(FileInputStream fileInputStream = new FileInputStream(nameFile);
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
           while (bufferedReader.ready()) {
                s = bufferedReader.readLine();
               System.out.println("Names = " + s);
           }
       } catch (FileNotFoundException e) {
		   System.out.println("File not found");
		   System.exit(0);
	   } catch (IOException e) {
		   e.printStackTrace();
	   }
       return s;
	}

	public static void testUniqueName(String name) {
		String[] arr = readFile(name).split(" ");
		Map<String,Integer>map = new LinkedHashMap<>();
		for (int i = 0; i < arr.length; i++) {
			if (map.containsKey(arr[i])){
				map.put(arr[i],map.get(arr[i])+1);
			}
			else {
				map.put(arr[i],1);
			}
		}
		List<String>list = new ArrayList<String>();
		for (Entry<String,Integer> entry:map.entrySet()){
			if (entry.getValue()==1){
				list.add(entry.getKey());
			}
		}
		if (!list.isEmpty()){
			System.out.println("First unique element: "+list.get(0));
			try {
				writeFile(list.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Non unique element");
		}
	}

	public static void writeFile(String str) throws Exception {
		FileWriter nFile = new FileWriter("output2.txt");
		nFile.write(str);
		System.out.println("File output2.txt created!");
		nFile.close();
	}
}

