
@SuppressWarnings("unchecked")
public class DynamicArray<T> implements Iterable<T> {
	private T[] arr;
	private int len = 0; // length user thinks array is
	private int capacity = 0; // Actual array size

	public DynamicArray() { this(16); }		// default capacity is 16

	public DynamicArray(int capacity) {
		if (capacity < 0) throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];
	}
	
	public int size() { return len; }
	public boolean isEmpty() { return size() == 0; }
	
	public T get(int index) {	// retrieve element using idx
		if (index >= len || index < 0) throw new IndexOutOfBoundsException();
	    return arr[index];
	}

	public void set(int index, T elem) {	// set element at idx
		if (index >= len || index < 0) throw new IndexOutOfBoundsException();
	    arr[index] = elem;
	}
	
	public void clear() {	// fills array with all null, len to 0
		for (int i = 0; i < len; i++) arr[i] = null;
	    len = 0;
	}
	
	public void addAt(int index, T elem) {
		if (index >= len || index < 0) throw new IndexOutOfBoundsException();
		if (len + 1 >= capacity) {	// adding 1 more elem fills arr, resize
			if (capacity == 0) capacity = 1;
			else capacity *= 2; 	// double the size
			
			// make new arr and copy elems
			T[] newArr = (T[]) new Object[capacity];
			for (int i = 0; i < len; i++)
				newArr[i] = arr[i];
			arr = newArr; // old arr is updated to new arr
		}
		
		// shift element at idx to len - 1 to the right
		len++;	// inc len
		for (int i = len - 1; i >= index; i--)
			arr[i + 1] = arr[i];
		arr[index] = elem;	// add elem at idx
	}
	
	public void add(T elem) {	// add elem to end of arr
		if (len + 1 >= capacity) {	// adding 1 more elem fills arr, resize
			if (capacity == 0) capacity = 1;
			else capacity *= 2; 	// double the size
			
			// make new arr and copy elems
			T[] new_arr = (T[]) new Object[capacity];
			for (int i = 0; i < len; i++)
				new_arr[i] = arr[i];
			arr = new_arr; // old arr is updated to new arr
		}
		arr[len++] = elem;	// add elem to end
	}
	
	public T removeAt(int index) {	// remove elem at index
		if (index >= len || index < 0) throw new IndexOutOfBoundsException();
		T data = arr[index];
		// shift elements at idx to len - 1 to the left
		for (int i = index; i < len - 1; i++)
			arr[i] = arr[i + 1];
		arr[len - 1] = null;	// last elem is repeated, delete
		capacity = --len;		// reduce len by 1
		
		return data;	// return removed data
	}
	
	public boolean remove(T elem) {
		for (int i = 0; i < len; i++) {		// scan through arr
			if (arr[i].equals(elem)) {	// find obj in arr
				removeAt(i);	// remove elem at idx i
				return true;	// true when removed
			}
		}
		return false;	// not found -> not removed
	}
	
	public int indexOf(T elem) {
		for (int i = 0; i < len; i++)	// linear search for elem
			if (arr[i].equals(elem))
				return i;
		return -1;
	}
	
	public boolean contains(T elem) {
		return indexOf(elem) != -1;
	}
	
	// abstraction for arr to iterate over it
	@Override
	public java.util.Iterator <T> iterator() {
		return new java.util.Iterator <T> () {
			int index = 0;
			// there exists a next elem in the arr
			@Override
			public boolean hasNext() { return index < len; }
			// next elem
			@Override
			public T next() { return arr[index++]; }
		};
	}
	
	@Override
	public String toString() {
		if (len == 0) return "[]";
		StringBuilder sb = new StringBuilder(len).append("[");
		for (int i = 0; i < len - 1; i++)
			sb.append(arr[i] + ", ");
		return sb.append(arr[len - 1] + "]").toString();
	}
}
