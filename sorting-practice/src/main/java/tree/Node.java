package tree;

class Node {
	public String value;
	public int leftNode;
	public int rightNode;
	
	public Node(String value) {
		this.value = value;
		leftNode = -1;
		rightNode = -1;
	}
	
	@Override
	public String toString() {
		return "(" + leftNode + ", " + value + ", " + rightNode + ")";
	}
}