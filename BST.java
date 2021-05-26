
public class BST<T extends Comparable<T>> {
	private int nodeCount = 0; // tracks num of nodes of BST
	private Node root = null; // this BST is a rooted tree

	// internal node containing node references
	// and the actual node data
	private class Node {
		T data;
		Node left, right;

		public Node(Node left, Node right, T data) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}

	// check if binary tree is empty
	public boolean isEmpty() {
		return size() == 0;
	}

	// get size of bst
	public int size() {
		return nodeCount;
	}

	// add an element to this binary tree. Returns
	// true if we successfully perform an insertion
	public boolean add(T elem) {
		// check if the value already exists
		// in the bst, if it does ignore it
		if (contains(elem))
			return false;

		// otherwise add elem
		root = add(root, elem);
		nodeCount++;
		return true;
	}

	// private method to recursively add a val in the binary tree
	private Node add(Node node, T elem) {
		// base case: found a leaf node
		if (node == null)
			node = new Node(null, null, elem);
		else {
			// place lower elements values in left subtreee
			if (elem.compareTo(node.data) < 0)
				node.left = add(node.left, elem);
			// else right subtree
			else
				node.right = add(node.right, elem);
		}
		return node;
	}

	// remove a value from this bst, if it exists
	public boolean remove(T elem) {
		// make sure the node we want to remove
		// actually exists before we remove it
		if (!contains(elem))
			return false;

		// remove node
		root = remove(root, elem);
		nodeCount--;
		return true;
	}

	private Node remove(Node node, T elem) {
		if (node == null)
			return null;

		int cmp = elem.compareTo(node.data);

		// dig into left substree, the val we're looking
		// for is smaller than the curr val
		if (cmp < 0)
			node.left = remove(node.left, elem);

		// dig into right subtree, the val we're looking
		// for is greater than the curr val
		else if (cmp > 0)
			node.right = remove(node.right, elem);

		// found the node we wish to remove, cmp == 0
		else {
			// case 1: only a right subtree or not subtree at all
			// swap the node we wish to remove with its right child
			if (node.left == null) {
				Node rightChild = node.right;

				node.data = null;
				node = null;

				return rightChild;
			}
			// case 2: only a left subtree or not subtree at all
			// swap the node we wish to remove with its left child
			else if (node.right == null) {
				Node leftChild = node.left;

				node.data = null;
				node = null;

				return leftChild;
			}
			// case 3: has both subtrees. Find the smallest value in the right subtree
			// which can be found by traversing as far left as possible to the right subtree
			else {
				// find the leftmost node in the right subtree
				Node tmp = digLeft(node.right);

				// swap the data
				node.data = tmp.data;

				// go into right subtree and remove the leftmost node we found and swapped
				// data with. This prevents with us from having 2 nodes in our tree with the
				// same val
				node.right = remove(node.right, tmp.data);
			}
		}
		return node;
	}

	// helper method to find the leftmost node
	private Node digLeft(Node node) {
		Node curr = node;
		while (curr.left != null)
			curr = curr.left;
		return curr;
	}

	// returns true is the elem exists in the tree
	public boolean contains(T elem) {
		return contains(root, elem);
	}

	// private recursive method to find an elem in the tree
	private boolean contains(Node node, T elem) {
		// base case: reached bottom, value not found
		if (node == null)
			return false;

		int cmp = elem.compareTo(node.data);

		// dig into the left subtree because the value we're looking
		// for is smaller than the curr val
		if (cmp < 0)
			return contains(node.left, elem);

		// dig into the right subtree because the value we're looking
		// for is greater than the curr val
		else if (cmp > 0)
			return contains(node.right, elem);

		// we found it
		else
			return true;
	}

	// computes the height of the tree, O(n)
	public int height() {
		return height(root);
	}

	// recursive helper method to compute the height of the tree
	public int height(Node node) {
		if (node == null)
			return 0;
		return Math.max(height(node.left), height(node.right)) + 1;
	}

	// The ways in which you can traverse the tree are in four different ways:
	// preorder, inorder, postorder and levelorder.
	public java.util.Iterator<T> traverse(TreeTraversalOrder order) {
		switch (order) {
		case PRE_ORDER:
			return preOrderTraversal();
		case IN_ORDER:
			return inOrderTraversal();
		case POST_ORDER:
			return postOrderTraversal();
		case LEVEL_ORDER:
			return levelOrderTraversal();
		default:
			return null;
		}
	}

	// Returns as iterator to traverse the tree in pre order
	private java.util.Iterator<T> preOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final java.util.Stack<Node> stack = new java.util.Stack<>();
		stack.push(root);

		return new java.util.Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				Node node = stack.pop();
				if (node.right != null)
					stack.push(node.right);
				if (node.left != null)
					stack.push(node.left);
				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Returns as iterator to traverse the tree in order
	private java.util.Iterator<T> inOrderTraversal() {

		final int expectedNodeCount = nodeCount;
		final java.util.Stack<Node> stack = new java.util.Stack<>();
		stack.push(root);

		return new java.util.Iterator<T>() {
			Node trav = root;

			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !stack.isEmpty();
			}

			@Override
			public T next() {

				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();

				// Dig left
				while (trav != null && trav.left != null) {
					stack.push(trav.left);
					trav = trav.left;
				}

				Node node = stack.pop();

				// Try moving down right once
				if (node.right != null) {
					stack.push(node.right);
					trav = node.right;
				}

				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Returns as iterator to traverse the tree in post order
	private java.util.Iterator<T> postOrderTraversal() {
		final int expectedNodeCount = nodeCount;
		final java.util.Stack<Node> stack1 = new java.util.Stack<>();
		final java.util.Stack<Node> stack2 = new java.util.Stack<>();
		stack1.push(root);
		while (!stack1.isEmpty()) {
			Node node = stack1.pop();
			if (node != null) {
				stack2.push(node);
				if (node.left != null)
					stack1.push(node.left);
				if (node.right != null)
					stack1.push(node.right);
			}
		}
		return new java.util.Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !stack2.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return stack2.pop().data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	// Returns as iterator to traverse the tree in level order
	private java.util.Iterator<T> levelOrderTraversal() {

		final int expectedNodeCount = nodeCount;
		final java.util.Queue<Node> queue = new java.util.LinkedList<>();
		queue.offer(root);

		return new java.util.Iterator<T>() {
			@Override
			public boolean hasNext() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				return root != null && !queue.isEmpty();
			}

			@Override
			public T next() {
				if (expectedNodeCount != nodeCount)
					throw new java.util.ConcurrentModificationException();
				Node node = queue.poll();
				if (node.left != null)
					queue.offer(node.left);
				if (node.right != null)
					queue.offer(node.right);
				return node.data;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
}