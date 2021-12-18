package ua.com.alevel.correctdateformat;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FormatDate {
	static String[] reverseArr(String[] array) {
		try {
			for (int i = 0; i < array.length / 2; i++) {
				String temp = array[i];
				array[i] = array[array.length - 1 - i];
				array[array.length - 1 - i] = temp;
			}
			return array;
		} catch (Exception e) {
			System.out.println("Wrong format. Method reverseArr");
		}
		return null;
	}

	static String[] swapIndexZeroOne(String[] array) {
		try {
			String temp = array[0];
			array[0] = array[1];
			array[1] = temp;
			return array;
		} catch (Exception e) {
			System.out.println("Wrong format. Method swapIndexZeroOne.");
		}
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	public static String splitDate(String str) throws ParseException {
		String arr[] = str.split(" ");
		String string = "";
		int counter = 0;
		if (arr.length == 3) {
			String[] yyyMMdd = testyyyMMdd(arr[0]);
			String[] ddMMyyy = testddMMyyy(arr[1]);
			String[] mm_dd_yyy = test_MM_dd_yyy(arr[2]);
			ddMMyyy = reverseArr(ddMMyyy);
			mm_dd_yyy = swapIndexZeroOne(mm_dd_yyy);
			mm_dd_yyy = reverseArr(mm_dd_yyy);

			try {
				for (int i = 0; i < yyyMMdd.length; i++) {
					if ((yyyMMdd[i].equals(ddMMyyy[i]) && yyyMMdd[i].equals(mm_dd_yyy[i]) && ddMMyyy[i].equals(mm_dd_yyy[i]))) {
						string = string.concat(yyyMMdd[i]);
						counter++;
					}
				}
			} catch (Exception e) {
				System.out.println("Wrong format: " + str);
			}
		} else {
			System.out.println("Wrong format");
			for (int i = 0; i < arr.length; i++) {
				if (arr[i].equals("")) {
					System.out.println("Found empty string ");
				}
				System.out.println(arr[i]);
			}
		}
		if (counter == 3) {
			return string;
		} else {
			return "";
		}
	}

	public static void writeFile(String arrDates[]) throws Exception {
		FileWriter nFile = new FileWriter("output1.txt");
		for (int i = 0; i < arrDates.length; i++) {
			if (!arrDates[i].equals(""))
				nFile.write(arrDates[i] + '\n');
		}
		System.out.println("File output1.txt created!");
		nFile.close();
	}

	public static void testDate(String nameFile) throws Exception {
		String[] arrDates = readFile( nameFile).toArray(new String[0]);
		for (int i = 0; i < arrDates.length; i++) {
			arrDates[i] = (splitDate(arrDates[i]));
		}
		writeFile(arrDates);

	}

	private static String[] testyyyMMdd(String dateInString1) {
		String dateInString = dateInString1;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date = null;
		try {
			date = sdf.parse(dateInString);
		} catch (ParseException e) {
			System.out.println("Wrong format. Expected: yyyy/MM/dd. Received: " + dateInString);
		}
		try {
			String formatDate_yyyyMMdd = sdf.format(date);
			String[] formatDate_yyyyMMddArr = formatDate_yyyyMMdd.split("/", 3);
			return formatDate_yyyyMMddArr;
		} catch (NullPointerException e) {
			System.out.println("Wrong format split ");
		}
		return null;
	}

	private static String[] testddMMyyy(String dateInString1) throws ParseException {
		String dateInString = dateInString1;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
		try {
			Date date = sdf.parse(dateInString);
			String formatDate_yyyyMMdd = sdf.format(date);
			String[] formatDate_yyyyMMddArr = formatDate_yyyyMMdd.split("/", 3);
			return formatDate_yyyyMMddArr;
		} catch (ParseException e) {
			System.out.println("Wrong format. Expected: dd/MM/yyy. Received: " + dateInString1);
		}
		return null;
	}

	private static String[] test_MM_dd_yyy(String dateInString1) throws ParseException {
		String dateInString = dateInString1;
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyy");
		try {
			Date date = sdf.parse(dateInString);
			String formatDate_yyyyMMdd = sdf.format(date);
			String[] formatDate_yyyyMMddArr = formatDate_yyyyMMdd.split("-", 3);
			return formatDate_yyyyMMddArr;
		} catch (ParseException e) {
			System.out.println("Wrong format. Expected: MM-dd-yyy. Received: " + dateInString1);
		}
		return null;
	}

	private static ArrayList<String> readFile(String nameFile) {
		ArrayList<String> arrayList = new ArrayList<>();
		try {
			File file = new File(nameFile);
			FileReader fr = new FileReader(file);
			BufferedReader reader = new BufferedReader(fr);
			String line = reader.readLine();
			while (line != null) {
				arrayList.add(line);
				line = reader.readLine();
			}
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayList;
	}
}
