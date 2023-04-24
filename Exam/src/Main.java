import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	public static Test t;
	
	public static void main(String[] args) {
		//Test t;
		System.out.println(t);
		
		List<Point> list = Arrays.asList(new Point(1,2), new Point(3,4), new Point(5,6), new Point(7,8));
		
		List<Point> newList = Collections.unmodifiableList(list);
		
		System.out.println(list);
		System.out.println(newList);
		
		list.get(0).x = 0;
		list.get(0).y = 0;
				
		
		System.out.println(list);
		System.out.println(newList);
		
		newList.get(0).x = 10;
		newList.get(0).y = 10;
		
		System.out.println(list);
		System.out.println(newList);
		
		Point p = new Point(13, 13);
		
		list.set(1,  p);
		System.out.println(list);
		System.out.println(newList);
		
		p = new Point(14, 14);
		System.out.println(list);
		System.out.println(newList);
		
		Point q = newList.get(1);
		q.x = 21;
		q.y = 21;
		
		System.out.println(list);
		System.out.println(newList);
		
	}
	
	
	
}

class Point {
	int x;
	int y;
	
	Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	
	@Override
	public String toString() {
		return "" + x + "," + y;
	}
}