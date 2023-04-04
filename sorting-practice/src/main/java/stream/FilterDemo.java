package stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterDemo {
	
	public void demoFilter() {
		List<Integer> numberList = Arrays.asList(10, 15, 20, 25, 30);	
		List<Integer> evenNumberList = new ArrayList<>();
	
		evenNumberList = numberList.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());		
		System.out.println(evenNumberList);
	}
	
	public void demoMap() {
		List<String> vehicles = Arrays.asList("bus", "car", "bicycle", "plane", "train");
		System.out.println(vehicles.stream().map(name -> name.toUpperCase()).collect(Collectors.toList()));
	}
	
	public void demoBoth() {
		List<Employee> empList = Arrays.asList(
				new Employee(101, "Alex", 10000),
				new Employee(102, "Brian", 20000),
				new Employee(103, "Charles", 30000),
				new Employee(104, "David", 40000),
				new Employee(105, "Edward", 50000)
				);

		// apply both filter and map
		System.out.println(empList.stream().filter(e -> e.salary > 20000).map(e -> 2).collect(Collectors.toList()));
		
	}
	
	public static void main(String[] args) {
		FilterDemo demo = new FilterDemo();
		demo.demoFilter();		
		demo.demoMap();
		demo.demoBoth();
	}
}

class Employee {
	int empId;
	String empName;
	int salary;
	
	public Employee(int empId, String empName, int salary) {
		this.empId = empId;
		this.empName = empName;
		this.salary = salary;
	}
}