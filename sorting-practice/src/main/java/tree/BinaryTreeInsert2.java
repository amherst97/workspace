/*
 * Implement binary tree insertion using recursive function
 */
package tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInsert2 {
	private List<Node> array = new ArrayList<Node>();
	
	public void insert(String s) {
		
		System.out.println("Inserting (recursive) --> " + s);
		
		if (array.isEmpty()) {
			// first element
			Node root = new Node(s);
			array.add(root);
		}
		else {
			if (s.compareTo(array.get(0).value) <= 0) {
				insertLeft(0, s);
			}
			else {
				insertRight(0, s);
			}
		}
		
		display();
	}
	
	public void insertLeft(int index, String s) {
		Node parent = array.get(index);
		
		if (parent.leftNode == -1) {
			// have available spot
			array.add(new Node(s));
			parent.leftNode = array.size() - 1;
			return;
		}
		
		// no valid spot
		Node node = array.get(parent.leftNode);
		if (s.compareTo(node.value) <= 0) {
			insertLeft(parent.leftNode, s);
		}
		else {
			insertRight(parent.leftNode, s);
		}				
	}
	
	public void insertRight(int index, String s) {
		Node parent = array.get(index);
		
		if (parent.rightNode == -1) {
			// have available spot
			array.add(new Node(s));
			parent.rightNode = array.size() - 1;
			return;
		}
		
		// no valid spot
		Node node = array.get(parent.rightNode);
		if (s.compareTo(node.value) <= 0) {
			insertLeft(parent.rightNode, s);
		}
		else {
			insertRight(parent.rightNode, s);
		}
	}
	
	public void traverseRecursion() {
		System.out.println("Traverse recursively - inorder");
		traverse(array.get(0));
	}
	
	public void traverse(Node node) {
		if (node.leftNode != -1)
			traverse(array.get(node.leftNode));
		
		System.out.println(node.value);
		
		if (node.rightNode != -1)
			traverse(array.get(node.rightNode));
	}
	
	public void traverseWithoutRecursion() {
		System.out.println("Traverse without recursion - inorder");
		Stack<Node> stack = new Stack<Node>();
		Node node = array.get(0);
		
		while (!stack.isEmpty() || node != null) {
			if (node != null) {
				stack.push(node);
				
				if (node.leftNode != -1)
					node = array.get(node.leftNode);
				else
					node = null;
			}
			else {
				Node last = stack.pop();
				System.out.println(last.value);
				
				if (last.rightNode != -1)
					node = array.get(last.rightNode);
				else
					node = null;
			}
		}
	}
	
	public void display() {
		for (Node node : array) {
			System.out.println(node);
		}
	}

	public static void main(String[] args) {
		String[] test = {"Michael", "Andy", "Lina", "Dansong", "Dansong", "Zhansheng", "Yunzhen", "Danliu"};
		String[] test2 = {"Lewis", "Chloe", "Imogen", "Harry", "Tracy", "Xavier", "James", "Rachael"};
		
		BinaryTreeInsert2 treeInsert2 = new BinaryTreeInsert2();
		for (String s : test) {
			treeInsert2.insert(s);
		}
		
		for (String s : test2) {
			treeInsert2.insert(s);
		}
				
		treeInsert2.traverseRecursion();		
		treeInsert2.traverseWithoutRecursion();
	}	
}

