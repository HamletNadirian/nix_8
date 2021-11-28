package ua.com.alevel;

import MathSet.MathSet;
import org.junit.jupiter.api.*;

import static ua.com.alevel.MathSetTesHelper.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MathSetServiceTest {

	@Test
	@Order(1)
	public void shouldDoAddToSet() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		Assertions.assertArrayEquals(SET_ONE, mathSetOne.getElements());
	}


	@Test
	@Order(2)
	public void shouldDoGetToAddArraysAdd() {
		MathSet mathSetOne = new MathSet();
		MathSet mathSetTwo = new MathSet();
		MathSet mathSetThree = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		mathSetTwo.add(5);
		mathSetTwo.add(13);
		mathSetTwo.add(22);

		mathSetThree.sortAsc();
		Number arrayForAdd[] = new Number[mathSetOne.size()];
		for (int i = 0; i < arrayForAdd.length; i++) {
			arrayForAdd[i] = mathSetTwo.getElements()[i];
		}

		mathSetOne.add(arrayForAdd);
		int c = 0;
		for (int i = 0; i < mathSetOne.size(); i++) {
			if (mathSetOne.getElements()[i] != null) {
				c++;
			}
		}
		Number number[] = new Number[c];
		for (int i = 0; i < c; i++) {
			number[i] = mathSetOne.getElements()[i];
		}
		Assertions.assertArrayEquals(SET_ONE_ARRAY_FOR_ADD, number);

	}


	@Test
	@Order(3)
	public void shouldDoJoin() {
		MathSet mathSetOne = new MathSet();
		MathSet mathSetTwo = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetTwo.add(4);
		mathSetTwo.add(5);
		mathSetTwo.add(6);
		mathSetTwo.add(7);
		mathSetTwo.join(mathSetOne);
		Assertions.assertArrayEquals(SET_JOIN, mathSetTwo.getElements());
	}

	@Test
	@Order(4)
	public void shouldDoGetToAddArraysMathSetJoin() {
		MathSet mathSetOne = new MathSet();
		MathSet mathSetTwo = new MathSet();
		MathSet mathSetThree = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		mathSetTwo.add(4);

		mathSetTwo.add(5);
		mathSetTwo.add(6);
		mathSetTwo.add(7);

		mathSetThree.add(10);
		mathSetThree.add(22);
		mathSetThree.add(32);

		mathSetThree.join(mathSetOne, mathSetTwo);
		mathSetThree.sortAsc();
		Number number[] = new Number[mathSetThree.size()];
		for (int i = 0; i < number.length; i++) {
			number[i] = mathSetThree.getElements()[i];
		}
		Assertions.assertArrayEquals(SET_ARRAY_JOIN, number);
	}

	@Test
	@Order(5)
	public void shouldDoIntersection() {
		MathSet mathSetOne = new MathSet();
		MathSet mathSetTwo = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		mathSetTwo.add(4);
		mathSetTwo.add(5);
		mathSetTwo.add(6);
		mathSetTwo.add(7);
		mathSetTwo.intersection(mathSetOne);
		Assertions.assertArrayEquals(SET_INTERSECTION, mathSetTwo.getElements());
	}

	@Test
	@Order(6)
	public void shouldDoGetToAddArraysMathSetIntersection() {
		MathSet mathSetOne = new MathSet();
		MathSet mathSetTwo = new MathSet();
		MathSet mathSetThree = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		mathSetTwo.add(5);

		mathSetTwo.add(3);
		mathSetTwo.add(2);

		mathSetThree.add(3);
		mathSetThree.add(2);
		mathSetThree.add(32);

		mathSetThree.intersection(mathSetOne, mathSetTwo);
		mathSetThree.sortAsc();
		int c = 0;
		for (int i = 0; i < mathSetThree.size(); i++) {
			if (mathSetThree.getElements()[i] != null) {
				c++;
			}
		}
		Number number[] = new Number[c];
		for (int i = 0; i < c; i++) {
			number[i] = mathSetThree.getElements()[i];
		}
		Assertions.assertArrayEquals(SET_ONE_INTERSECTION, number);
	}

	@Test
	@Order(7)
	public void shouldDoSortAsc() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		mathSetOne.sortAsc();
		Assertions.assertArrayEquals(SET_FOR_SORTED_ASC, mathSetOne.getElements());
	}

	@Test
	@Order(8)
	public void shouldDoSortAscNumberValue() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		mathSetOne.sortAsc(6);
		Assertions.assertArrayEquals(SET_FOR_SORT_ASC_NUMBER_VALUE, mathSetOne.getElements());
	}

	@Test
	@Order(9)
	public void shouldDoSortAscByIndex() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		mathSetOne.sortAsc(3, 6);
		Assertions.assertArrayEquals(SET_FOR_SORT_ASC_BY_INDEX, mathSetOne.getElements());
	}

	@Test
	@Order(10)
	public void shouldDoSortDesc() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		mathSetOne.sortDesc();
		Assertions.assertArrayEquals(SET_FOR_SORTED_DESC, mathSetOne.getElements());
	}

	@Test
	@Order(11)
	public void shouldDoSortDescNumberValue() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		mathSetOne.sortDesc(5);
		Assertions.assertArrayEquals(SET_FOR_SORT_DESC_NUMBER_VALUE, mathSetOne.getElements());
	}

	@Test
	@Order(12)
	public void shouldDoSortDescByIndex() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		mathSetOne.sortDesc(3, 5);
		Assertions.assertArrayEquals(SET_FOR_SORT_DESC_BY_INDEX, mathSetOne.getElements());
	}

	@Test
	@Order(13)
	public void shouldDoGetIndex() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		Number getValue = mathSetOne.get(5);
		Assertions.assertEquals(1, getValue);
	}

	@Test
	@Order(14)
	public void shouldDoGetMax() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		Number getMax = mathSetOne.getMax();
		Assertions.assertEquals(7, getMax);
	}

	@Test
	@Order(15)
	public void shouldDoGetMin() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		Number getMin = mathSetOne.getMin();
		Assertions.assertEquals(1, getMin);
	}

	@Test
	@Order(16)
	public void shouldDoGetAverage() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(4);
		mathSetOne.add(3);
		mathSetOne.add(6);
		mathSetOne.add(5);
		mathSetOne.add(2);
		mathSetOne.add(1);
		mathSetOne.add(7);
		Number getAverage = mathSetOne.getAverage();
		Assertions.assertEquals(4.0, getAverage);
	}

	@Test
	@Order(17)
	public void shouldDoGetMedian() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(10);
		mathSetOne.add(20);
		mathSetOne.add(30);
		mathSetOne.add(40);
		mathSetOne.add(50);
		Number getMedian = mathSetOne.getMedian();
		Assertions.assertEquals(30, getMedian);
	}

	@Test
	@Order(18)
	public void shouldDoGetToArray() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		Number getToArray[] = mathSetOne.toArray();
		Assertions.assertArrayEquals(SET_ONE, getToArray);
	}

	@Test
	@Order(19)
	public void shouldDoGetToArrayByIndex() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		Number getToArray[] = mathSetOne.toArray(0, 2);
		Assertions.assertArrayEquals(SET_ONE_BY_INDEX, getToArray);
	}

	@Test
	@Order(20)
	public void shouldDoGetСut() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		int first = 0;
		int last = 2;
		MathSet getToArrayCut = mathSetOne.cut(0, 2);
		Number number[] = new Number[last - first];
		for (int i = 0; i < number.length; i++) {
			number[i] = getToArrayCut.getElements()[i];
		}
		Assertions.assertArrayEquals(SET_ONE_BY_INDEX, number);
	}

	@Test
	@Order(21)
	public void shouldDoGetToClear() {
		MathSet mathSetOne = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		mathSetOne.clear();
		Number number[] = new Number[mathSetOne.size()];
		for (int i = 0; i < number.length; i++) {
			number[i] = mathSetOne.getElements()[i];
		}
		Assertions.assertArrayEquals(MATH_SETS_CLEAR, number);
	}

	@Test
	@Order(22)
	public void shouldDoGetToClearArray() {
		MathSet mathSet = new MathSet(SET_ONE);
		mathSet.clear(SET_ONE);
		Number number[] = new Number[mathSet.size()];
		for (int i = 0; i < number.length; i++) {
			number[i] = mathSet.getElements()[i];
		}
		Assertions.assertArrayEquals(MATH_SETS_CLEAR, number);
	}

	@Test
	@Order(23)
	public void shouldDoGetToAddArrays() {
		MathSet mathSet = new MathSet(SET_ONE_AND_TWO);//{{1,2,3,4},{4,5,6,7}} ;
		Assertions.assertArrayEquals(SET_FOR_SORTED_ASC, mathSet.toArray());//{1,2,3,4,5,6,7};
	}

	@Test
	@Order(24)
	public void shouldDoGetToAddArraysMathSet() {
		MathSet mathSetOne = new MathSet();
		MathSet mathSetTwo = new MathSet();
		mathSetOne.add(1);
		mathSetOne.add(2);
		mathSetOne.add(3);
		mathSetOne.add(4);
		mathSetTwo.add(4);
		mathSetTwo.add(5);
		mathSetTwo.add(6);
		mathSetTwo.add(7);
		MathSet mathSetArray = new MathSet(mathSetOne, mathSetTwo);
		mathSetArray.sortAsc();
		MathSet mathSet = new MathSet(SET_FOR_SORTED_ASC);
		Assertions.assertArrayEquals(mathSet.getElements(), mathSetArray.getElements());//{1,2,3,4,5,6,7};
	}

}

