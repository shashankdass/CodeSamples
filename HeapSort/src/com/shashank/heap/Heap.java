package com.shashank.heap;

public class Heap {
	int heap[] ;
	public void build_Min_heap(int[] arr) {
		heap = new int[arr.length];
		for (int i=0 ; i<arr.length>>1 ; i++) {
			Min_Heapify(arr,i,arr.length);
		}
		
	}
	private void Min_Heapify(int[] arr, int i,int length) {
		int left = (i<<1) + 1 ; // 2*i+1
		int  right = (i<<1) + 2;// 2*i+2
		int min = i;
		//int length = arr.length;
		// if root is greater than left child swap the root and the left child.
		if (left<length && arr[left] < arr[min] ) {
		min = left;
		}
		// if root is greater than right child swap the root and the right child.
		if (right<length && arr[right] < arr[min] ) {
			min = right;
		}
		// if min is still the same then root is less than right and left and no need to recurse.
		if (min != i)
		{
			swap(arr,min,i);
			Min_Heapify(arr, min,length);
		}
	}
	public void swap (int[] arr, int i , int j) {
		//Swap without temp variable..freaking cool!!!
		arr[i] =arr[i] ^ arr[j]; // x = x xor y
		arr[j] = arr[i] ^ arr[j];// y = x xor y
		arr[i] = arr[i] ^ arr[j];// x = x xor y
	}
	
	private void heapSort(int[] arr) {
		int length = arr.length;
		for(int i=0 ; i<arr.length ;i++) {
		System.out.println(arr[0]);
		swap(arr,0,length-1);
		length = length - 1;
		Min_Heapify(arr,0,length);
		}
	}
	public static void main(String args[]) {
		int[] arr = {1,2,3,12,4,13,15,5};
		Heap h = new Heap();
		h.build_Min_heap(arr);//Build a heap of unsorted array.
		for(int i=0 ; i<arr.length ;i++) {
			System.out.println(arr[i]);
		}
		h.heapSort(arr);//Do a heapsort on the array
		
	}
	
}
