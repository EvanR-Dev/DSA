public class Main {
  public static void main(String[] args) {
    //DynamicArray<Integer> arr = new DynamicArray<>(8);
    //Exponentiation exp = new Exponentiation();
    //System.out.println(exp.logRecPow(2, 10));
    //Merge m = new Merge();
    //int[] arr = {9, 3, 7, 5, 6, 4, 8, 2};
    //m.mergeSort(arr, 0, arr.length - 1);
	
    //for (int i : arr) System.out.println(i);
    SlidingWindow sw = new SlidingWindow();
    int x = sw.findMaxSumSubarray(new int[] {4, 2, 1, 7, 8, 1, 2, 8, 1, 0}, 3);
    System.out.println(x);
    int y = sw.smallestSubarray(new int[] {4, 2, 2, 7, 8, 1, 2, 8, 10}, 8);
    System.out.println(y);
    int z = sw.findLength("AAAHHIBC", 2);
    System.out.println(z);
  }
}
