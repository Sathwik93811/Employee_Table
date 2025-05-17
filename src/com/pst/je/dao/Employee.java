package com.pst.je.dao;
import java.sql.*;
import java.util.*;
public class Employee {
Scanner sc=new Scanner(System.in);
 public void operations() {
	String enter="yes";
	while(enter.equals("yes")) {
		System.out.println("** Welcome to EMS System **");
		System.out.println("1. Add Employee");
		System.out.println("2. Update Employee By Id");
		System.out.println("3. Remove Employee");
		System.out.println("4. View All Employees");
		System.out.println("5. View Employee By Name"); 
		System.out.println("Choose your Option?");
		System.out.println("Enter Number: ");
		
		int num=sc.nextInt();
		System.out.println("Selected Operation: "+num);

	     switch(num) {
	     case 1: AddEmployee();break;
	     case 2:updateEmployee();break;
	     case 3:removeEmployee();break;
	     case 4:viewEmployees();break;
	     case 5:empByName();break;
	     default:System.out.println("choose correct Option!");
	     }
	     System.out.println("Do you want to continue?Enter yes or no");
	      enter=sc.next();
	}
	
}
public void AddEmployee() {
	System.out.println("Enter Employee id");
	int idNum=sc.nextInt();
	System.out.println("Enter Employee Salary");
	int salary=sc.nextInt();
	System.out.println("Enter Employee Name");
	sc.nextLine();
	String name=sc.nextLine();
	System.out.println("Enter Employee Village");
	String village=sc.next();
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","batch6","batch6");
		PreparedStatement ps=conn.prepareStatement("insert into employee values(?,?,?,?)");
		ps.setInt(1,idNum );
		ps.setInt(2, salary);
		ps.setString(3,name);
		ps.setString(4, village);
		int i=ps.executeUpdate();
		System.out.println(i>0?"successfully Inserted":"try Again!");
		conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public void updateEmployee() {
	sc.nextLine();
	System.out.println("Enter name");
	String name=sc.nextLine();
	
	System.out.println("Enter id");
	int idNum=sc.nextInt();
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","batch6","batch6");
		PreparedStatement ps=conn.prepareStatement("update employee set name=? where id=?");
		ps.setString(1,name );
		ps.setInt(2,idNum);
		
		int i=ps.executeUpdate();
		System.out.println(i>0?"successfully updated":"try Again!");
		conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public void removeEmployee() {
	System.out.println("Enter id");
	int idNum=sc.nextInt();
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","batch6","batch6");
		PreparedStatement ps=conn.prepareStatement("delete from employee where id=?");
		
		ps.setInt(1,idNum);
		
		int i=ps.executeUpdate();
		System.out.println(i>0?"successfully deleted":"try Again!");
		
		conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public  void viewEmployees() {
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","batch6","batch6");
		PreparedStatement ps=conn.prepareStatement("select*from employee");
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			int salary=rs.getInt(2);
			String name=rs.getString(3);
			String village=rs.getString(4);
			System.out.println("Employee ID: "+id);
			System.out.println("Employee Salary: "+salary);
			System.out.println("Employee Name: "+name);
			System.out.println("Employee Village: "+village);
		}
		
		conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
public void empByName() {
	sc.nextLine();
	System.out.println("Enter name: ");
	String Name=sc.nextLine();
	
	try {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","batch6","batch6");
		PreparedStatement ps=conn.prepareStatement("select*from employee where name=?");
		ps.setString(1, Name);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			int id=rs.getInt(1);
			int salary=rs.getInt(2);
			String name=rs.getString(3);
			String village=rs.getString(4);
			System.out.println("Employee ID: "+id);
			System.out.println("Employee Salary: "+salary);
			System.out.println("Employee Name: "+name);
			System.out.println("Employee Village: "+village);
		}
		
		conn.close();
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
