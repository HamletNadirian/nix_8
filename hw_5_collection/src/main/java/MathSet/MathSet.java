package MathSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;


public class MathSet<T extends Number> {
	private Number[] elements;
	int size;
	private static final Number[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
	private int index;
	private static final int DEFAULT_CAPACITY = 15;
	private int arraySize = DEFAULT_CAPACITY;

	@Override
	public int hashCode() {
		return Arrays.hashCode(elements);
	}

	public Number[] getElements() {
		return elements;
	}

	private static Number[] getOneDimArrFromTwoDimArray(Number[]... matrix) {
		int countElem = 0;
		for (Number[] tmpArr : matrix)
			countElem += tmpArr.length;
		Number[] ret = new Number[countElem];
		int indRet = 0;
		for (Number[] tmpArr : matrix)
			for (Number elemTmpArr : tmpArr)
				ret[indRet++] = elemTmpArr;
		return ret;
	}

	private int getSizeWithoutNull(Number[] elements) {
		int c = 0;
		for (int i = 0; i < elements.length; i++) {
			if (this.elements[i] != null) c++;
		}
		return c;
	}

	public MathSet() {
		elements = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
	}

	public MathSet(int capacity) {
		elements = new Number[capacity];
	}

	public MathSet(Number[] elements) {
		this.elements = getUniqueNumber(elements);
	}

	public MathSet(Number[]... elements) {
		this.elements = getOneDimArrFromTwoDimArray(elements);
		this.elements = getUniqueNumber((Number[]) this.elements);
	}

	public MathSet(MathSet elements) {
		Number[] number = elements.getElements();
		this.elements = number;
	}

	public MathSet(MathSet... elements) {
		Number arr[] = new Number[elements.length * elements[0].getElements().length];

		for (int i = 0; i < elements.length; i++) {
			for (int j = 0; j < elements[0].getElements().length; j++) {
				if (elements[i].getElements()[j] != null)
					arr[i + (elements.length * j)] = elements[i].getElements()[j];
			}
		}
		this.elements = getOneDimArrFromTwoDimArray(arr);
		this.elements = getUniqueNumber((Number[]) this.elements);
	}

	public void join(MathSet ms) {
		Number[] numberFirst = ms.getElements();
		Number[] numberSecond = (Number[]) this.elements;
		int length = numberFirst.length + numberSecond.length;
		size = numberFirst.length + numberSecond.length;
		Number[] mergedArray = new Number[length];
		int pos = 0;

		for (Number element : numberFirst) {
			mergedArray[pos] = element;
			pos++;
		}
		for (Number element : numberSecond) {
			mergedArray[pos] = element;
			pos++;
		}
		this.elements = getUniqueNumber(mergedArray);
	}

	public void join(MathSet... ms) {
		Number arr[] = new Number[ms.length * ms[0].getElements().length];
		for (int i = 0; i < ms.length; i++) {
			for (int j = 0; j < ms[0].getElements().length; j++) {
				arr[i + (ms.length * j)] = ms[i].getElements()[j];
			}
		}
		Number[] numberSecond = (Number[]) this.elements;
		int length = (ms.length * ms[0].getElements().length) + this.elements.length;
		Number[] mergedArray = new Number[length];
		int pos = 0;

		for (Number element : arr) {
			mergedArray[pos] = element;
			pos++;
		}
		for (Number element : numberSecond) {
			mergedArray[pos] = element;
			pos++;
		}
		this.elements = getUniqueNumber(mergedArray);
		size = this.elements.length;
	}

	private void grow() {
		int newSize = (this.elements.length) + (this.elements.length / 2);
		System.arraycopy(elements, 0, elements, 0, Math.min(elements.length, newSize));
	}

	private void resize() {
		if (size >= arraySize) {
			Number[] newValues = new Number[size * 3 / 2 + 1];
			System.arraycopy(elements, 0, newValues, 0, size);
			elements = newValues;
		}
		if (size >= DEFAULT_CAPACITY && size < arraySize / 4) {
			Number[] newValues = new Number[size * 3 / 2 + 1];
			System.arraycopy(elements, 0, newValues, 0, size);
			elements = newValues;
		}
		if (size == elements.length) {
			Number[] newValues = new Number[size * 3 / 2 + 1];
			System.arraycopy(elements, 0, newValues, 0, size);
			elements = newValues;
		}
	}

	public static <T> boolean contains(final T[] array, final T v) {
		for (final T e : array)
			if (e == v || v != null && v.equals(e))
				return true;

		return false;
	}

	public void add(Number object) {
		if (!(contains(elements, object))) {
			resize();
			elements[size] = object;
			size++;
		}
	}

	public void add(Number... numbers) {
		for (int i = 0; i < numbers.length; i++) {
			add(numbers[i]);
		}
	}

	T elements(int index) {
		return (T) elements[index];
	}

	public Number[] set(int index, Number[] element) {
		Number[] oldValue = elements;
		elements = element;
		return oldValue;
	}

	public void setElements(Number[] elements) {
		this.elements = elements;
	}

	public void intersection(MathSet ms) {
		int c = 0;
		Number[] numberFirst = ms.getElements();
		Number[] numberSecond = (Number[]) this.elements;
		int length = numberFirst.length * numberSecond.length;
		Number[] mergedArray = new Number[length];

		for (int i = 0; i < (numberFirst.length); i++) {
			for (int j = 0; j < numberSecond.length; j++) {
				if (numberFirst[i] == numberSecond[j]) {
					mergedArray[c] = numberFirst[i];
					c++;
				}

			}
		}
		Number intersection[] = new Number[c];
		for (int i = 0; i < intersection.length; i++) {
			intersection[i] = mergedArray[i];
		}
		this.elements = intersection;
	}

	public void intersection(MathSet... ms) {

		Number arr[] = new Number[ms.length * ms[0].getElements().length];
		for (int i = 0; i < ms.length; i++) {
			for (int j = 0; j < ms[0].getElements().length; j++) {
				arr[i + (ms.length * j)] = ms[i].getElements()[j];
			}
		}
		Number[] numberSecond = new Number[ms.length * ms[0].getElements().length];
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] == arr[j]) {
					numberSecond[i] = arr[j];
				}
			}
		}
		int c = 0;
		Number[] numberFirst = (Number[]) this.elements;
		int length = numberFirst.length * numberSecond.length;
		Number[] mergedArray = new Number[length];

		for (int i = 0; i < (numberFirst.length); i++) {
			for (int j = 0; j < numberSecond.length; j++) {
				if (numberFirst[i] == numberSecond[j]) {
					mergedArray[c] = numberFirst[i];
					c++;
				}

			}
		}
		this.elements = mergedArray;
	}

	private static Number[] getUniqueNumber(Number[] arrayWithDuplicates) {
		int len = arrayWithDuplicates.length;
		Number[] distinctArray = new Number[arrayWithDuplicates.length];
		int index = 0;
		for (int i = 0; i < len; i++) {
			int flag = 0;
			for (int j = 0; j < i; j++) {
				if (arrayWithDuplicates[i] != null)
					if (arrayWithDuplicates[i].equals(arrayWithDuplicates[j])) {
						flag = 1;
						break;
					}
			}
			if (flag == 0) {
				distinctArray[index] = arrayWithDuplicates[i];
				if (distinctArray[index] != null)
					index++;
			}
		}
		Number[] uniqueNumber = new Number[index];
		for (int i = 0; i < index; i++)
			uniqueNumber[i] = distinctArray[i];
		return uniqueNumber;
	}

	public int size() {
		return size;
	}

	public void sortDesc() {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		for (int i = 0; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() < mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		for (int i = 0; i < c; i++) {
			this.elements[i] = mas[i];
		}
	}

	public void sortAsc() {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		for (int i = 0; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() > mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		for (int i = 0; i < c; i++) {
			this.elements[i] = mas[i];
		}
	}

	public void sortAsc(int value) {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = (Number) this.elements[i];
				c++;
			}
		}
		int idx = getArrayIndex(mas, value);
		for (int i = idx; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() > mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		for (int i = 0; i < c; i++) {
			this.elements[i] = mas[i];
		}
	}

	public void sortDesc(int value) {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = (Number) this.elements[i];
				c++;
			}
		}

		int idx = getArrayIndex(mas, value);
		for (int i = idx; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() < mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		for (int i = 0; i < c; i++) {
			this.elements[i] = mas[i];
		}
	}

	public void sortDesc(int first, int last) {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		for (int i = first; i < last; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() < mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		for (int i = 0; i < c; i++) {
			this.elements[i] = mas[i];
		}
	}

	public void sortAsc(int first, int last) {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		for (int i = first; i < last; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() > mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		for (int i = 0; i < c; i++) {
			this.elements[i] = mas[i];
		}
	}

	public static int getArrayIndex(Number[] arr, int value) {

		int k = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i].doubleValue() == value) {
				k = i;
				break;
			}
		}
		return k;
	}

	public Number get(int value) {
		if (elements[value] == (null))
			throw new NullPointerException("not found index " + value);
		else {
			return elements[value];
		}
	}

	public Number getMax() {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = (Number) this.elements[i];
				c++;
			}
		}

		for (int i = 0; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() > mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		return mas[c - 1];
	}

	public Number getMin() {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = (Number) this.elements[i];
				c++;
			}
		}
		for (int i = 0; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].doubleValue() > mas[j].doubleValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		return mas[0];
	}

	public Number getAverage() {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		Number avg = 0;
		for (int i = 0; i < c; i++) {
			avg = mas[i].doubleValue() + avg.doubleValue();
		}
		return avg.doubleValue() / c;
	}

	public Number getMedian() {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (this.elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		for (int i = 0; i < c - 1; i++) {
			for (int j = c - 1; j > i; j--) {
				if (mas[j - 1].intValue() > mas[j].intValue()) {
					int tmp = (int) mas[j - 1];
					mas[j - 1] = mas[j];
					mas[j] = tmp;
				}
			}
		}
		int length = c;
		Number median = 0;
		if (length % 2 == 0)
			median = (mas[length / 2].intValue() + mas[length / 2 - 1].intValue()) / 2;
		else
			median = mas[c / 2];
		return median;

	}

	public Number[] toArray() {
		Number mas[] = new Number[elements.length];
		int c = 0;
		for (int i = 0; i < mas.length; i++) {
			if (elements[i] != null) {
				mas[i] = this.elements[i];
				c++;
			}
		}
		return elements;
	}

	public Number[] toArray(int first, int last) {
		Number mas[] = new Number[this.elements.length];
		int c = 0;
		for (int i = first; i < last; i++) {
			if (!elements[i].equals(null)) {
				mas[i] = elements[i];
				c++;
			}
		}

		Number[] newElem = new Number[c];
		for (int i = 0; i < c; i++) {
			newElem[i] = elements[i];
		}
		return newElem;
	}

	public MathSet cut(int first, int last) {
		Number[] mas = elements;
		int c = 0;
		for (int i = first; i < last; i++) {
			if (elements[i] != null) {
				mas[i] = elements[i];
				c++;
			}
		}
		int sizeCut = last;
		MathSet mathSet = new MathSet(sizeCut);
		for (int i = first; i < last; i++) {
			mathSet.getElements()[i] = mas[i];
		}
		size = c;
		return mathSet;
	}

	public void clear() {
		for (int to = size, i = size = 0; i < to; i++)
			elements[i] = null;

	}

	public void clear(Number numbers[]) {
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = null;
		}
		elements = numbers;
	}
}

