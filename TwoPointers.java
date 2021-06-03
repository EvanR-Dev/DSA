
public class TwoPointers {
	// finds the indices of two numbers that sum to a target
	// of a sorted array
	// Example input:
	// [-1, 1, 2, 3, 5], target = 5
	public int[] twoSumSorted(int[] arr, int target) {
		int low = 0, high = arr.length - 1;
		while (low < high) {
			int sum = arr[low] + arr[high];
			// sum is lower than target, increase it by moving low right 1
			if (sum < target) low++;
			// sum is higher than target, decrease it by moving high left 1
			else if (sum > target) high--;
			// found target sum
			else return new int[] {low, high};
		}
		// no target sum
		return new int[] {0};
	}
	
	// finds three numbers in a sorted array that sum
	// to a target
	// Example input:
	// [1, 2, 4, 5, 12], target = 19
	public int[] threeSumSorted(int[] arr, int target) {
		for (int i = 0; i < arr.length - 2; i++) {
			int newTarget = target - arr[i];
			int low = i + 1, high = arr.length - 1;
			while (low < high) {
				if (arr[low] + arr[high] < newTarget) low++;
				else if (arr[low] + arr[high] > newTarget) high--;
				else return new int[] {i, low, high};
			}
		}
		return new int[] {0};
	}
	
	// square each val in a sorted array and return
	// the array in sorted order
	// Example input:
	// [-4, -3, 1, 2 ,3]
	public int[] sqEach(int[] nums) {
		int[] arr = new int[nums.length];
        int i = 0, j = nums.length - 1;
        int k = arr.length - 1;
        while (i <= j) {
            int iSquared = nums[i] * nums[i];
            int jSquared = nums[j] * nums[j];
            
            if (iSquared > jSquared) {
                arr[k--] = iSquared;
                i++;
            }
            else {
                arr[k--] = jSquared;
                j--;
            }
        }
        return arr;
	}
}
