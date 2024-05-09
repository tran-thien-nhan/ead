package entities;

import java.util.*;

import jakarta.persistence.*;
import entities.Company;

@Entity(name = "Employee")
@Table(name = "employees")
public class Employee {
	@Id
    @Column(name = "EmployeeId")
	private String employeeId;
	
    @Column(name = "EmployeeName")
	private String employeeName;
    
	
	public Employee() {
	}

	public Employee(String employeeId, String employeeName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	
}
