package com.shashank.C9problem1;

public class Merge {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int a[] = new int[10];
		int b[] = new int[5];
		for (int i = 0; i < 9; i++) {
			a[i] = i;
		}
		for (int j = 0; j < 5; j++) {
			b[j] = j + 10;
		}
		merge(a, b, 5, 5);
	}

	private static void merge(int[] a, int[] b, int m, int n) {
		int k = m + n - 1;
		int j = m - 1;
		int i = n - 1;
		/*
		 * while (m > 0 && n > 0) { if (a[m] > b[n]) { a[k--] = a[m--]; } else
		 * if (a[m] < b[n]) { a[k--] = b[n--]; } else { a[k--] = a[m--]; a[k--]
		 * = b[n--]; } } while(m>=0){ a[k--] = b[m--]; }
		 */
		while (i >= 0 && j >= 0) {
			if (a[i] > b[j]) {
				a[k--] = a[i--];
			} else {
				a[k--] = b[j--];
			}
		}
		while (j >= 0) {
			a[k--] = b[j--];
		}
		for (int l = 0; l < a.length; l++) {
			System.out.println(a[l]);
		}
	}

}
