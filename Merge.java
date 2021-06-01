
public class Merge {
	// merge two sorted lists, O(m + n)
	public int[] twoWayMerge(int[] A, int[] B) {
		// m = A.length, n = B.length
		int[] C = new int[A.length + B.length];

		// i is index of A, j is index of B, k is index of C
		int i, j, k;
		i = j = k = 0;

		// start merge
		while (i < A.length && j < B.length) {
			// elem in A is less than elem in B
			if (A[i] < B[j])
				C[k++] = A[i++];

			// elem in B is less than or eq to elem in A
			else
				C[k++] = B[j++];
		}
		// merge remaining elements of the remaining list
		while (i < A.length)
			C[k++] = A[i++];
		while (j < B.length)
			C[k++] = B[j++];

		return C;
	}

	void merge(int arr[], int l, int m, int r) {
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		int L[] = new int[n1];
		int R[] = new int[n2];

		/* Copy data to temp arrays */
		for (int i = 0; i < n1; ++i)
			L[i] = arr[l + i];
		for (int j = 0; j < n2; ++j)
			R[j] = arr[m + 1 + j];

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarry array
		int k = l;
		while (i < n1 && j < n2) {
			if (L[i] < R[j]) arr[k++] = L[i++];
			
			else arr[k++] = R[j++];
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1) arr[k++] = L[i++];

		/* Copy remaining elements of R[] if any */
		while (j < n2) arr[k++] = R[j++];
	}

	// Main function that sorts arr[l..r] using
	// merge()
	void mergeSort(int arr[], int l, int r) {
		if (l < r) {
			// Find the middle point
			int m = l + (r - l) / 2;

			// Sort first and second halves
			mergeSort(arr, l, m);
			mergeSort(arr, m + 1, r);

			// Merge the sorted halves
			merge(arr, l, m, r);
		}
	}
}
