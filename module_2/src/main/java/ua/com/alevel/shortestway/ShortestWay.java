package ua.com.alevel.shortestway;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;

import static ua.com.alevel.shortestway.Graph.saveShortestDistance;

public class ShortestWay {
	public static void readFile(String nameFile) {
		int s = 0;
		try (FileInputStream fileInputStream = new FileInputStream(nameFile);
			 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
			ArrayDeque<Integer> arrayListN = new ArrayDeque<>();
			ArrayDeque<Integer> arrayListW = new ArrayDeque<>();
			while (bufferedReader.ready()) {
				s = Integer.parseInt(bufferedReader.readLine());
				int countNegh[] = new int[s];
				String[] cities = new String[s];
				for (int i = 0; i < s; i++) {
					cities[i] = bufferedReader.readLine();
					countNegh[i] = Integer.parseInt(bufferedReader.readLine());
					int neigh[] = new int[countNegh[i]];
					int weight[] = new int[countNegh[i]];
					for (int j = 0; j < countNegh[i]; j++) {
						String nw = bufferedReader.readLine();
						String strNW[] = nw.split(" ");
						neigh[j] = Integer.parseInt(strNW[0]);
						weight[j] = Integer.parseInt(strNW[1]);
						arrayListN.add(neigh[j]);
						arrayListW.add(weight[j]);
					}
				}
				int countCalcShortestWay = Integer.parseInt(bufferedReader.readLine());
				for (int i = 0; i < countCalcShortestWay; i++) {
					String strCalcCities = bufferedReader.readLine();
					String[] cityArr = strCalcCities.split(" ");
					String nameOne = cityArr[0];
					String nameTwo = cityArr[1];
					calcInPair(cities, countNegh, arrayListN, arrayListW, nameOne, nameTwo);
				}
			}
			String saveResult = String.join("\n", saveShortestDistance);
			writeFile(saveResult);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void writeFile(String str) throws Exception {
		FileWriter nFile = new FileWriter("output3.txt");
		nFile.write(str);
		System.out.println("File output3.txt created!");
		nFile.close();
	}

	private static void calcInPair(String[] arr, int[] size, ArrayDeque<Integer> arrayN, ArrayDeque<Integer> arrayW, String nameOne, String nameTwo) throws Exception {
		int[] gdN = new int[arrayN.size()];
		int[] gdW = new int[arrayW.size()];
		ArrayDeque<Integer> arrayListForCopyN = new ArrayDeque<>();
		ArrayDeque<Integer> arrayListForCopyW = new ArrayDeque<>();
		arrayListForCopyN = arrayN.clone();
		arrayListForCopyW = arrayW.clone();
		for (int i = 0; i < gdN.length; i++) {
			gdN[i] = arrayListForCopyN.getFirst();
			gdW[i] = arrayListForCopyW.getFirst();
			arrayListForCopyN.poll();
			arrayListForCopyW.poll();
		}

		ArrayDeque<Integer> arrayListN = new ArrayDeque<>();
		ArrayDeque<Integer> arrayListW = new ArrayDeque<>();
		for (int i = 0; i < gdN.length; i++) {
			arrayListN.add(gdN[i]);
		}
		for (int i = 0; i < gdW.length; i++) {
			arrayListW.add(gdW[i]);
		}
		nameOne(arr, size, gdN, gdW, nameOne, nameTwo);
	}

	private static void nameOne(String[] arr, int[] size, int[] gdN, int[] gdW, String nameCityOne, String nameCityTwo) throws Exception {
		int firstCity = (Arrays.asList(arr).indexOf(nameCityOne));
		int starSize[] = new int[(arr.length) - firstCity];
		System.arraycopy(size, firstCity, starSize, 0, starSize.length);
		int sum = 0;
		for (int i = 0; i < starSize.length; i++) {
			sum = sum + starSize[i];
		}
		int gdN2[] = new int[sum];
		int gdW2[] = new int[sum];
		String city[] = new String[arr.length - firstCity];

		if (firstCity > 0) {
			System.arraycopy(gdN, size[firstCity - 1], gdN2, 0, gdN2.length);
			System.arraycopy(gdW, size[firstCity - 1], gdW2, 0, gdW2.length);
			System.arraycopy(arr, firstCity, city, 0, city.length);
		} else {
			System.arraycopy(gdN, 0, gdN2, 0, gdN2.length);
			System.arraycopy(gdW, 0, gdW2, 0, gdW2.length);
			System.arraycopy(arr, firstCity, city, 0, city.length);
		}
		nameCityTwo(city, starSize, nameCityTwo, gdN2, gdW2);
	}

	private static void nameCityTwo(String[] city, int[] size, String nameCityTwo, int[] arrayListN2, int[] arrayListW2) throws Exception {
		int secondCity = (Arrays.asList(city).indexOf(nameCityTwo));
		int startSize[] = new int[secondCity + 1];
		System.arraycopy(size, 0, startSize, 0, startSize.length);
		int sum = 0;
		for (int i = 0; i <= secondCity; i++) {
			sum = sum + size[i];
		}
		int[] neighbors = new int[sum];
		int[] weight = new int[sum];
		Graph graph2 = new Graph();
		String[] cityCalc = new String[(secondCity + 1)];
		if (secondCity != city.length - 1) {
			System.arraycopy(arrayListN2, 0, neighbors, 0, neighbors.length);
			System.arraycopy(arrayListW2, 0, weight, 0, weight.length);
			System.arraycopy(city, 0, cityCalc, 0, cityCalc.length);

		} else if (secondCity == city.length - 1) {
			System.arraycopy(arrayListN2, 0, neighbors, 0, arrayListN2.length);
			System.arraycopy(arrayListW2, 0, weight, 0, arrayListW2.length);
			System.arraycopy(city, 0, cityCalc, 0, city.length);

		}
		ArrayDeque<Integer> arrayDequeN = new ArrayDeque<>();
		ArrayDeque<Integer> arrayDequeW = new ArrayDeque<>();
		System.out.println();
		for (int i = 0; i < neighbors.length; i++) {
			arrayDequeN.add(neighbors[i]);
		}
		for (int i = 0; i < weight.length; i++) {
			arrayDequeW.add(weight[i]);
		}

		for (int i = 0; i < cityCalc.length; i++) {
			graph2.addVertex(city[i]);
			for (int j = 0; j < startSize[i]; j++) {
				int N = arrayDequeN.getFirst();
				int W = arrayDequeW.getFirst();
				graph2.addEdge(i, N, W);
				arrayDequeN.poll();
				arrayDequeW.poll();
			}
		}
		graph2.path();
		graph2.clean();
	}
}
