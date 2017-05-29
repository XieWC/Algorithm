package mySort;

public class MySort {
	public static void main(String[] args) {
		int[] arr = new int[] { 2, 2, 1, 2, 2, 3, 5, 2 };
		display(arr);
		// bubbleSort(arr);
		// insertSort(arr);
		// selectSort(arr);
		// shellSort(arr);
		// myMaxHeap(arr);
		// mergeSort(arr, 0, arr.length - 1);
		// quickSort(arr, 0, arr.length - 1);
		bucketSort(arr);
		display(arr);

	}

	public static void display(int[] arr) {
		for (int i : arr) {
			System.out.print(i + " ");
		}
		System.out.println();
	}

	// Õ∞≈≈–Ú
	public static void bucketSort(int[] arr) {
		int[] array = new int[100];
		for (int i = 0; i < arr.length; i++) {
			array[arr[i]] += 1;
		}
		for (int i = 0, j = 0; i < array.length; i++) {
			while (array[i] > 0) {
				arr[j++] = i;
				array[i]--;
			}
		}
	}

	// —°‘Ò≈≈–Ú ≤ªŒ»∂®
	public static void selectSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			int flag = i;
			int temp = arr[i];
			for (int j = i + 1; j < len; j++) {
				if (temp > arr[j]) {
					temp = arr[j];
					flag = j;
				}
			}
			if (flag != i) {
				arr[flag] = arr[i];
				arr[i] = temp;
			}
		}
	}

	// øÏÀŸ≈≈–Ú ≤ªŒ»∂®

	public static int parttion(int[] arr, int begin, int end) {
		int index = arr[end];
		int i = begin, j = end - 1;
		while (i < j) {
			while (i < j && arr[i] < index) {
				i++;
			}
			while (i < j && arr[j] >= index) {
				j--;
			}
			if (i < j) {
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				j--;
				i++;
			}
		}
		int temp = arr[end];
		arr[end] = arr[i];
		arr[i] = temp;
		return i;
	}

	public static void quickSort(int[] arr, int begin, int end) {
		if (begin < end) {
			int part = parttion(arr, begin, end);
			quickSort(arr, begin, part - 1);
			quickSort(arr, part + 1, end);
		}
	}

	// πÈ≤¢≈≈–Ú Œ»∂®
	public static void mergeSort(int[] arr, int begin, int end) {
		if (begin < end) {
			int middle = (begin + end) / 2;
			mergeSort(arr, begin, middle);
			mergeSort(arr, middle + 1, end);
			merge(arr, begin, middle, end);
		}
	}

	private static void merge(int[] arr, int begin, int middle, int end) {
		// TODO Auto-generated method stub
		int ln = middle - begin + 1;
		int rn = end - middle;
		int[] larr = new int[ln];
		int[] rarr = new int[rn];

		for (int i = begin, j = 0; i <= middle; i++, j++) {
			larr[j] = arr[i];
		}
		for (int i = middle + 1, j = 0; i <= end; i++, j++) {
			rarr[j] = arr[i];
		}
		int i, j, k;
		for (i = 0, j = 0, k = begin; i < ln && j < rn; k++) {
			if (larr[i] <= rarr[j]) {
				arr[k] = larr[i];
				i++;
			} else {
				arr[k] = rarr[j];
				j++;
			}
		}
		if (i != ln) {
			for (; i < ln; i++, k++) {
				arr[k] = larr[i];
			}
		}
		if (j != ln) {
			for (; j < rn; j++, k++) {
				arr[k] = rarr[j];
			}
		}

	}

	// ∂—≈≈–Ú ≤ªŒ»∂®
	public static void adjustMaxHeap(int[] arr, int pos, int len) {

		int temp = arr[pos];
		int child = 2 * pos + 1;
		while (child <= len) {
			if (child < len) {
				if (arr[child] < arr[child + 1]) {
					child++;
				}
			}
			if (temp < arr[child]) {
				arr[pos] = arr[child];
				pos = child;
				child = 2 * pos + 1;
			} else {
				break;
			}
		}
		arr[pos] = temp;
	}

	public static void myMaxHeap(int[] arr) {
		int len = arr.length;
		for (int i = len / 2 - 1; i >= 0; i--) {
			adjustMaxHeap(arr, i, len - 1);
		}
		for (int i = len - 1; i >= 1; i--) {
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			adjustMaxHeap(arr, 0, i - 1);
		}
	}

	// œ£∂˚≈≈–Ú£®ºı…Ÿ‘ˆ¡ø≈≈–Ú£©
	public static void shellSort(int[] arr) {
		int len = arr.length;
		for (int h = len / 2; h > 0; h /= 2) {
			for (int i = h; i < len; i++) {
				int j = i, temp = arr[i];
				while (j >= h && temp < arr[j - h]) {
					arr[j] = arr[j - h];
					j = j - h;
				}
				arr[j] = temp;
			}
		}
	}

	// ÷±Ω”≤Â»Î≈≈–Ú Œ»∂®
	public static void insertSort(int[] arr) {
		int len = arr.length;
		for (int i = 1; i < len; i++) {
			int j = i, temp = arr[i];
			while (j >= 1 && temp < arr[j - 1]) {
				arr[j] = arr[--j];
			}
			arr[j] = temp;
		}
	}

	// √∞≈›≈≈–Ú Œ»∂®
	public static void bubbleSort(int[] arr) {
		int length = arr.length;
		int temp;
		for (int i = 0; i < length - 1; i++) {
			for (int j = length - 1; j > i; j--) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = temp;
				}
			}
		}
	}
}
