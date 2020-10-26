package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>{
	
	private BinaryNode<Type> root;
	private int size;
	private Type item;
	
	
	public BinarySearchTree()
	{
		root = null;
		size=0;
	}
	
	public BinarySearchTree(Type item)
	{
	
		this.item= item;
		
		this.updateSize(1);
		root.leftChild=null;
		root.rightChild=null;
		
	}

	@Override
	public boolean add(Type item) {
		if(root.size()==0)
		{
			root = new BinarySearchTree(item);
			return true;
		}
		
		
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type first() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Type last() throws NoSuchElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean remove(Type item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateSize(int n)
	{
		this.size+=n;
	}
	public int getSize()
	{
		return this.size;
	}
	
	private class BinaryNode<T>
	{
		private T element;

		private BinaryNode<T> leftChild;

		private BinaryNode<T> rightChild;

		/**
		 * Creates a new BinaryNode object.
		 * 
		 * @param element - data element to store at this node
		 * @param leftChild - this node's left child
		 * @param rightChild - this node's right child
		 */
		public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
			this.element = element;
			this.leftChild = left;
			this.rightChild = right;
		}

		/**
		 * Creates a new BinaryNode object.
		 * 
		 * @param element - data element to store at this node
		 */
		public BinaryNode(T element) {
			this(element, null, null);
		}

		/**
		 * Getter method for the data element contained in this node.
		 * 
		 * @return the data element
		 */
		public T element() {
			return element;
		}

		/**
		 * Setter method for the element contained in this node.
		 * 
		 * @param element - the data element
		 */
		public void resetElement(T element) {
			this.element = element;
		}

		/**
		 * Getter method for the left child of this node.
		 * 
		 * @return the left child
		 */
		public BinaryNode<T> leftChild() {
			return leftChild;
		}

		/**
		 * Getter method for the right child of this node.
		 * 
		 * @return the right child
		 */
		public BinaryNode<T> rightChild() {
			return rightChild;
		}

		/**
		 * Determines the number of nodes in the tree rooted at this node.
		 * 
		 * @return the number of nodes in the tree
		 */
		public int size() {
			// count this node
			int size = 1; 
			
			// count all of the nodes in the left subtree
			if(leftChild != null)
				size += leftChild.size(); 

			// count all of the nodes in the right subtree
			if(rightChild != null)
				size += rightChild.size(); 

			return size;
		}

		/**
		 * Generate a copy of the tree rooted at this node.
		 * 
		 * @return the tree copy
		 */
		public BinaryNode<T> duplicate() {
			BinaryNode<T> copyLeft = null;
			
			// get copy of left subtree
			if(leftChild != null)
				copyLeft = leftChild.duplicate(); 

			// get copy of right subtree
			BinaryNode<T> copyRight = null;
			if(rightChild != null)
				copyRight = rightChild.duplicate(); 

			// combine left and right in a new node w/ element
			return new BinaryNode<T>(this.element, copyLeft, copyRight);
		}

		/**
		 * Print the elements of the tree rooted at this node, using preorder traversal
		 * (element followed by left subtree followed by right subtree).
		 */
		public void printPreorder() {
			// "visit" this node
			System.out.print(element + " ");
			// do a recursive traversal of the subtree on the left
			if(leftChild != null)
				leftChild.printPreorder();
			// do a recursive traversal of the subtree on the right
			if(rightChild != null)
				rightChild.printPreorder();
		}

		/**
		 * Print the elements of the tree rooted at this node, using postorder traversal 
		 * (left subtree followed by right subtree followed by element).
		 */
		public void printPostorder() {
			// do a recursive traversal of the subtree on the left
			if(leftChild != null)
				leftChild.printPostorder();
			// do a recursive traversal of the subtree on the right
			if(rightChild != null)
				rightChild.printPostorder();
			// "visit" this node
			System.out.print(element + " ");
		}

		/**
		 * Print the elements of the tree rooted at this node, using inorder traversal 
		 * (left subtree followed by element followed by right subtree).
		 */
		public void printInorder() {
			// do a recursive traversal of the subtree on the left
			if(leftChild != null)
				leftChild.printInorder();
			// "visit" this node
			System.out.print(element + " ");
			// do a recursive traversal of the subtree on the right
			if(rightChild != null)
				rightChild.printInorder();
		}

		/**
		 * Print the elements of the tree rooted at this node, using level-order
		 * traversal (i.e., breadth-first search).
		 */
		public void printLevelorder() {
			Queue<BinaryNode<T>> q = new LinkedList<BinaryNode<T>>();
			q.offer(this);

			while(!q.isEmpty()) {
				BinaryNode<T> x = q.poll();
				System.out.print(x.element + " ");
				if(x.leftChild != null)
					q.offer(x.leftChild);
				if(x.rightChild != null)
					q.offer(x.rightChild);
			}
		}

		/**
		 * Generate the DOT representation of the tree rooted at this node, 
		 * useful for visualizing the tree at http://www.webgraphviz.com.
		 * 
		 * @return a string containing all of the edges in the tree, DOT format
		 */
		private String generateDot() {
			String ret = "\tnode" + element + " [label = \"<f0> |<f1> " + element + "|<f2> \"]\n";
			if(leftChild != null)
				ret += "\tnode" + element + ":f0 -> node" + leftChild.element + ":f1\n" + leftChild.generateDot();
			if(rightChild != null)
				ret += "\tnode" + element + ":f2 -> node" + rightChild.element + ":f1\n" + rightChild.generateDot();

			return ret;
		}

		/**
		 * Write a DOT representation of the tree rooted at "root" to file.
		 * 
		 * @param filename - output file name
		 * @param root - the root of the binary tree
		 */
		public void <T> void generateDotFile(String filename, BinaryNode<T> root) { //TA
			try {
				PrintWriter out = new PrintWriter(filename);
				out.println("digraph Tree {\n\tnode [shape=record]\n");

				if(root == null)
					out.println("");
				else
					out.print(root.generateDot());

				out.println("}");
				out.close();
			}
			catch(IOException e) {
				System.out.println(e);
			}
		}
		
		
		public boolean recAdd(BinaryNode<T> root , T key)
		{
			if(root.equals(null))
			{
				
			}
			
			if(key)
		}
		
		
		
	}



	}


