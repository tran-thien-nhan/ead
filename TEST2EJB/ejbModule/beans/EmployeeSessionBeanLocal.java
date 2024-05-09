package beans;

import entities.*;
import jakarta.ejb.*;
import java.util.*;

@Local
public interface EmployeeSessionBeanLocal {
	Employee findEmployeeByEmployeeId(String employeeId);
	void createEmployee(Employee employee, String selectedCompany);
	boolean isEmployeeIdExists(String employeeId);
	void addEmployeeCompany(EmployeeCompany employeeCompany);
	List<Company> getEmployeeCompanies(String employeeId);
}
