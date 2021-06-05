import java.util.Arrays;
import java.util.LinkedList;

public class MergeIntervals {
	// Given an array of meetings represented by arrays of length 2
	// with a start time and an end time, return true if any of the 
	// meetings overlap, and false if none of the meetings overlap.
	//
	// Input: meetings=[[1,3], [4,6], [8,10], [9,18]]
	// Output: true
	public boolean overlappingMeetings(int[][] meetings) {
		// iterate thru each interval/meeting
		for (int i = 1; i < meetings.length; i++)
			// right val in 1st interval > left val in 2nd interval, overlap
			if (meetings[i - 1][1] > meetings[i][0])
				return true;
		return false;
	}
	
	// Merge any overlapping intervals in the list and return the
	// vals in the new list
	// 
	// Input: [[1,3], [2,6], [8,10], [9, 18]]
	// Output: [[1, 6][8, 18]]
	public int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        LinkedList<int[]> merged = new LinkedList<>();
        for (int[] interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
	}
}
