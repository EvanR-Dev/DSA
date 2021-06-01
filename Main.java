public class Main {
  public static void main(String[] args) {
    //DynamicArray<Integer> arr = new DynamicArray<>(8);
    //Exponentiation exp = new Exponentiation();
    //System.out.println(exp.logRecPow(2, 10));
    Merge m = new Merge();
    int[] arr = {9, 3, 7, 5, 6, 4, 8, 2};
    m.mergeSort(arr, 0, arr.length - 1);
	
    for (int i : arr) System.out.println(i);
  }
}
