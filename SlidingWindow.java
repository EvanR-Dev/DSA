import java.util.HashMap;

public class SlidingWindow {
	// finds the max sum subarray of a fixed size K
	// Example input:
	// [4, 2, 1, 7, 8, 1, 2, 8, 1, 0]
	// runs in O(N) as opposed to O(N^2)
	// fixed window
	public int findMaxSumSubarray(int[] arr, int k) {
		int maxVal = Integer.MIN_VALUE;		// assume max defaults to negative infinity
		int currWindowSum = 0;	// sum of the current subarray
		
		for (int i = 0; i < arr.length; i++) {	// check every number
			currWindowSum += arr[i];	// accumulate sum (add num to window)
			
			if (i >= k - 1) {	// at window size
				maxVal = Math.max(maxVal, currWindowSum);		// find if sum of window is new max
				currWindowSum -= arr[i - (k - 1)];		// subtract left elem of window from the window sum
			}
		}
		return maxVal;
	}
	
	// finds the min sum of a subarray that must be greater than or equal to a sum
	// Example input:
	// [4, 2, 2, 7, 8, 1, 2, 8, 10]
	// dynamic window
	public int smallestSubarray(int[] nums, int target) {
		int windowStart = 0, currWindowSum = 0;
		int minWindowSize = Integer.MAX_VALUE;	// smallest subarr size
		for (int windowEnd = 0; windowEnd < nums.length; windowEnd++) {
			currWindowSum += nums[windowEnd];	// add values
			
			// while the criteria is satisfied, try to minimize the size of the window
			while (currWindowSum >= target) {
				// find size of window when target is hit, see if it is new min
				minWindowSize = Math.min(minWindowSize, windowEnd - windowStart + 1);
				currWindowSum -= nums[windowStart];		// subtract left elem from window sum
				windowStart++;		// decrease size of window by 1, move left window to the right by 1
			}
		}
		return minWindowSize == Integer.MAX_VALUE ? 0 : minWindowSize;
	}
	
	// finds longest substring length with K distinct chars
	// uses auxillary DS
	// Example input:
	// AAAHHIBC
	public int findLength(String str, int k) {
		// map for chars and their frequencies
		HashMap<Character, Integer> map = new HashMap<>();
		
		int windowStart = 0, currWindowLen = 0;
		int maxWindowLen = Integer.MIN_VALUE;
		
		for (int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
			char endCh = str.charAt(windowEnd);
			map.put(endCh, map.getOrDefault(endCh, 0) + 1);		// place char and freq into map
			
			while (map.size() > k) {	// more than k distinct chars, move start window right
				char startCh = str.charAt(windowStart);
				map.put(startCh, map.get(startCh) - 1);		// subtract 1 from freq
				
				// freq is 0, remove char from map
				if (map.get(startCh) == 0) map.remove(startCh);
				
				// move left of window right one
				windowStart++;
			}
			// find max window len
			currWindowLen = windowEnd - windowStart + 1;
			maxWindowLen = Math.max(maxWindowLen, currWindowLen);
		}
		return maxWindowLen;
	}
}
