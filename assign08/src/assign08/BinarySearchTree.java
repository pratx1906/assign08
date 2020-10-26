package assign08;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;
// , check over add.

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>{
	
	private BinaryNode<Type> root;
	private int size;
	
	
	public BinarySearchTree()
	{
		root = null;
		size=0;
	}
	
	public BinarySearchTree(Type item) // TA note: do we need this?
	{
	
		this.root.element = item;
		
		this.updateSize(1);
		this.root.leftChild=null;
		this.root.rightChild=null;
		
	}

	@Override
	public boolean add(Type item) 
	{	
		this.updateSize(1);
		return this.root.recAdd(root, item);
	}

	@Override
	public boolean addAll(Collection<? extends Type> items) 
	{
		boolean result = true;
		
		for(Type V : items)
		{
			result = result && add(V);
		}
		
		return result;
	}

	@Override
	public void clear() 
	{
		this.root = null; 	//Ask TA: do we need to do anything else in this method?
		this.size = 0;
	}

	@Override
	public boolean contains(Type item) //driver method
	{
		return this.root.recContains(item);
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items) {
		boolean result = false;
		
		for(Type V : items)
		{
			result = result || contains(V);
		}
		
		return result;
	}

	@Override
	public Type first() throws NoSuchElementException 
	{
		if(root == null) //TA note: check that this is the parameter we should be checking for.
			throw new NoSuchElementException();
		
		return this.root.recFirst();
	}

	@Override
	public boolean isEmpty() 
	{
		return root == null;
	}

	@Override
	public Type last() throws NoSuchElementException 
	{
		if(root == null) //TA note: check that this is the parameter we should be checking for.
			throw new NoSuchElementException();
		
		return this.root.recLast();
	}

	@Override
	public boolean remove(Type item) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<? extends Type> items) 
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() 
	{
		return size;
	}

	@Override
	public ArrayList<Type> toArrayList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void updateSize(int n)
	{
		this.size+=n;
	}
//--------------------------------------------------------------------------------------------
//									BinaryNode Class
//--------------------------------------------------------------------------------------------
	private class BinaryNode<T extends Comparable<? super T>>
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
		public <G> void generateDotFile(String filename, BinaryNode<T> root) { //TA
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
		
		
		public boolean recAdd(BinaryNode<T> root, T key)
		{
			if(root.equals(null)) //element doesn't exist so we are adding it.
			{
				root = new BinaryNode<T>(key);
				return true;
			}
			
			if(key.compareTo(root.element()) == 0) //element already exists in tree.
			{
				return false;
			}
			else if(key.compareTo(root.element) > 0) // continue right if positive val, or left if negative val.
			{
				return rightChild.recAdd(root, key);
			}
			else
			{
				return leftChild.recAdd(root,key);
			}
		}
		
		public boolean recContains(T key)
		{
			if(this.equals(null))
				return false;
			
			if(key.compareTo(this.element) == 0)
				return true;
			else if(key.compareTo(this.element) > 0)
				return rightChild.recContains(key);
			else
				return leftChild.recContains(key);
		}
		
		public T recFirst()
		{
			if(this.leftChild() == null)
				return this.element();
			else
				return leftChild().recFirst();
		}
		
		public T recLast()
		{
			if(this.rightChild() == null)
				return this.element();
			else
				return rightChild().recLast();
		}
		
	}



	}


