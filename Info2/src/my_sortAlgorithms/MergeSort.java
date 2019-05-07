package my_sortAlgorithms;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import sortAlgorithms.HeapSort;

public class MergeSort<T extends Comparable<T>> {

	public void merge(T[] source1, int l1, int r1, T[] source2, int l2, int r2, T[] target, int l3) {
		while (l1 < r1 && l2 < r2) {
			if (source1[l1].compareTo(source2[l2]) < 0) {
				target[l3++] = source2[l2++];
			} else {
				target[l3++] = source1[l1++];
			}
		}
		while (l1 < r1) {
			target[l3++] = source1[l1++];
		}
		while (l2 < r2) {
			target[l3++] = source2[l2++];

		}
	}

	public void distribute(T[] source, T[] targetA, T[] targetB, int size) {
		int ind_source = 0;
		for (int i = 0; i < source.length / size / 2; i++) {
			for (int j = 0; j < size; j++) {
				targetA[i * size + j] = source[ind_source++];
			}
			for (int j = 0; j < size; j++) {
				targetB[i * size + j] = source[ind_source++];
			}
		}
	}

	public void sort(T[] a) {
		
		// Vor.: a.length ist 2er-Potenz
		T[] helpB = a.clone();
		T[] helpC = a.clone();
		for (int size = 1; size < a.length; size *= 2) {
			// a auf b und c verteilen indem immer size viele Elemente kopiert werden, wobei
			// size eine 2er Potenz ist
			distribute(a, helpB, helpC, size);
			for (int i = 0; i < a.length / 2; i += size) {
				merge(helpB, i, i + size, helpC, i, i + size, a, 2 * i);
			}
		}

	}

	private boolean istPotenzVon2(int x) {
		double temp = Math.log((double) x) / Math.log(2);
		if (temp == (int) temp) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		Integer[] a = { 17, 42, 3, 22, 5, 29, 54, 6 };
		MergeSort<Integer> ms = new MergeSort<Integer>();
		System.out.println("A: " + Arrays.toString(a));
		ms.sort(a);
		System.out.println("A: " + Arrays.toString(a));

	}

}
