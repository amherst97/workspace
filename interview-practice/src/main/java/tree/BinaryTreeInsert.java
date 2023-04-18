/*
 * Implement a string array to support binary insertion of string based on the natural order 
 */
package tree;

public class BinaryTreeInsert {
	private Node[] array = new Node[8];
	int lastNode = -1; 		// track the index of last inserted node
		
	public void insert(String s) {		
		int index = 0;
		int parentNode = -1;	// trace the parent node of last inserted node
		
		System.out.println("Inserting --> " + s);
		
		if (array[0] == null) {
			// insert first element
			Node node = new Node(s);
			array[0] = node;
			lastNode = 0;
 		}
		else {
			
			// loop until find the correct place to put
			while(index != -1) {
				// keep the current index before it changes
				parentNode = index;
				if (s.compareTo(array[index].value) <= 0) {
					// go left				
					index = array[index].leftNode;
				}
				else {
					// go right				
					index = array[index].rightNode;
				}
			}
			
			// found a place to insert
			array[++lastNode] = new Node(s);
			if (s.compareTo(array[parentNode].value) <= 0) {
				array[parentNode].leftNode = lastNode;
			}
			else {
				array[parentNode].rightNode = lastNode;
			}
		}
		
		display();
	}
	
	public void display() {
		for (Node node : array) {
			System.out.println(node);
		}
	}
	
	public static void main(String[] args) {
		//String[] test = {"Michael", "Andy", "Lina", "Dansong", "Zhansheng", "Yunzhen", "Danliu"};
		String[] test = {"Lewis", "Chloe", "Imogen", "Harry", "Tracy", "Xavier", "James", "Rachael"};
		
		BinaryTreeInsert treeInsert = new BinaryTreeInsert();
		for (String s : test) {
			treeInsert.insert(s);
		}
	}	
}