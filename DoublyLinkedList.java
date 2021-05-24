
public class DoublyLinkedList <T> implements Iterable <T> {
	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;
	
	// internal node class to represent data
	@SuppressWarnings("hiding")
	private class Node<T> {
		T data;
		Node<T> prev, next;
		
		public Node(T data, Node<T> prev, Node<T> next) {
			this.data = data;
			this.prev = prev;
			this.next = next;
		}
		@Override
		public String toString() {
			return data.toString();
		}
	}
	
	// empty this linked list, O(n)
	public void clear() {
		Node<T> trav = head;
		while (trav != null) {
			Node<T> next = trav.next;
			trav.prev = trav.next = null;
			trav.data = null;
			trav = next;
		}
		head = tail = trav = null;
		size = 0;
	}
	
	public int size() { return size; }		// return size of LL
	public boolean isEmpty() { return size() == 0; }	// is LL empty?
	
	// add an elem to tail of LL, O(1)
	public void add(T elem) { addLast(elem); }
	
	// Add a node to the tail of the linked list, O(1)
	public void addLast(T elem) {
		if (isEmpty()) 
	      head = tail = new Node<T>(elem, null, null);
		else {
			tail.next = new Node<T>(elem, tail, null);
			tail = tail.next;
		}
		size++;
	}
	
	// add elem to beginning of LL O(1)
	public void addFirst(T elem) {
		if (isEmpty())	// LL is empty
			head = tail = new Node<T> (elem, null, null);	// add first node
		else {
			head.prev = new Node<T> (elem, null, head);
			head = head.prev;
		}
		size++;
	}
	
	// check val of last node if it exists, O(1)
	public T peekFirst() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		return head.data;
	}
	
	// Check the value of the last node if it exists, O(1)
	public T peekLast() {
		if (isEmpty()) throw new RuntimeException("Empty list");
	    return tail.data;
	}
	
	// remove 1st value at head of LL, O(1)
	public T removeFirst() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		
		T data = head.data;		// extract data at head
		head = head.next;		// head ptr moves forward by 1 node
		size--;
		
		if (isEmpty()) tail = null;		// if list is empty set tail to null as well
		else head.prev = null;			// remove prev node ptr
		
		return data;
	}
	
	// remove last value at tail of LL, O(1)
	public T removeLast() {
		if (isEmpty()) throw new RuntimeException("Empty list");
		
		T data = tail.data;		// extract data at tail
		tail = tail.prev;		// tail ptr moves backward by 1 node
		size--;
		
		if (isEmpty()) head = null;		// if list is empty set head to null as well
		else tail.next = null;			// remove prev node ptr
		
		return data;
	}
	
	// remove an arbitrary node from LL, O(1)
	private T remove(Node<T> node) {
		// handle head and tail independently
		if (node.prev == null) return removeFirst();
		if (node.next == null) return removeLast();
		
		// make ptrs of adjacent nodes skip over 'node'
		node.next.prev = node.prev;
		node.prev.next = node.next;
		
		// temp store the data to return
		T data = node.data;
		
		// memory cleanup
		node.data = null;
		node = node.prev = node.next = null;
		size--;
		
		return data;
	}
	
	// remove a node at an index, O(n)
	public T removeAt(int index) {
		if (index < 0 || index >= size) throw new IllegalArgumentException();
		
		int i;
		Node<T> trav;
		
		if (index < size / 2) {	// search from front of list
			for (i = 0, trav = head; i != index; i++)
				trav = trav.next;
		}
		else {	// search from end of list
			for (i = size - 1, trav = tail; i != index; i--)
				trav = trav.prev;
		}
		return remove(trav);
	}
	
	// remove a particular value in the linked list, O(n)
	public boolean remove(T elem) {
		Node<T> trav = head;
		
		if (elem == null) {  // support search for null elems
			while (trav != null) {
				if (trav.data == null) {
					remove(trav);
					return true;
				}
				trav = trav.next;
			}
		}
		else {	// search for non null obj
			while (trav != null) {
				if (elem.equals(trav.data)) {
					remove(trav);
					return true;
				}
				trav = trav.next;
			}
		}
		return false;
	}
	
	// Find the index of a particular value in the linked list, O(n)
	public int indexOf(T elem) {
		int index = 0;
		Node<T> trav = head;

	    if (elem == null) {	// Support searching for null
	    	for (; trav != null; trav = trav.next, index++)
	    		if (trav.data == null) return index;
	    }
	    else {	// Search for non null object
	    	for (; trav != null; trav = trav.next, index++)
	    		if (elem.equals(trav.data)) return index;
	    }
	    return -1;
	}
	
	public boolean contains(T elem) {
		return indexOf(elem) != -1;
	}
	
	@Override
	public java.util.Iterator<T> iterator() {
		return new java.util.Iterator<T> () {
			private Node<T> trav = head;
			@Override
			public boolean hasNext() { return trav != null; }
			@Override
			public T next() {
				T data = trav.data;
				trav = trav.next;
				return data;
			}
		};
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
	    Node<T> trav = head;
	    while (trav != null) {
	    	sb.append(trav.data);
	    	if (trav.next != null)
	    		sb.append(", ");
	    	trav = trav.next;
	    }
	    sb.append(" ]");
	    
	    return sb.toString();
	}
}
