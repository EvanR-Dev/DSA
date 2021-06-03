public class Main {
  public static void main(String[] args) {
    //DynamicArray<Integer> arr = new DynamicArray<>(8);
    //Exponentiation exp = new Exponentiation();
    //System.out.println(exp.logRecPow(2, 10));
    //Merge m = new Merge();
    //SlidingWindow sw = new SlidingWindow();
    TwoPointers tp = new TwoPointers();
    int[] x = tp.threeSumSorted(new int[] {1, 2, 4, 5, 12}, 19);
    for (int i : x) System.out.println(i);
    int[] y = tp.sqEach(new int[] {-4, -3, 1, 2, 3});
    for (int i : y) System.out.println(i);
  }
}
