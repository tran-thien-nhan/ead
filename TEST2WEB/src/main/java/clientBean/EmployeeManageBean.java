package clientBean;

import java.io.Serializable;
import java.util.*;

import beans.CompanySessionBeanLocal;
import beans.EmployeeSessionBeanLocal;
import entities.Company;
import entities.Employee;
import entities.EmployeeCompany;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "EmployeeManageBean")
@SessionScoped
public class EmployeeManageBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private Employee employee;
    private String employeeId;
    private String employeeName;
    private String errorMessage;
    private String successMessage;
    private String newCompanyId;
    private String newCompanyName;
    private String selectedCompany;
    private List<Company> companyList;

    @Inject
    private EmployeeSessionBeanLocal employeeLocal;
    @Inject
    private CompanySessionBeanLocal companyLocal;

    @PostConstruct
    public void initialize() {
        employee = new Employee();
        companyList = companyLocal.getAllCompanies();
    }
    
    public String getBack() {
        employeeId = null;
        employeeName = null;
        errorMessage = null;
        successMessage = null;
        return "check_employee?faces-redirect=true";
    }

    public String checkEmpId() {
        employee = employeeLocal.findEmployeeByEmployeeId(employeeId);
        if (employee != null) {
        	employeeName = employee.getEmployeeName();
        	companyList = employeeLocal.getEmployeeCompanies(employeeId);
            return "info?faces-redirect=true";
        }
        errorMessage = "Wrong employee id! Try again";
        return null;
    }

    public String moveToRegister() {
        return "register?faces-redirect=true";
    }
    
    public void register() {
        if (selectedCompany == null || selectedCompany.isEmpty()) {
            Company newCompany = new Company();
            newCompany.setCompanyName(newCompanyName);
            newCompany.setCompanyId(newCompanyId); // Set the new company ID
            companyLocal.createCompany(newCompany);
            selectedCompany = newCompany.getCompanyId();
        }
        
        if (employeeLocal.isEmployeeIdExists(employeeId)) {
            // If employeeId already exists, add to employee_company table
            EmployeeCompany employeeCompany = new EmployeeCompany();
            employeeCompany.setEmployeeId(employeeId);
            employeeCompany.setCompanyId(selectedCompany);
            employeeLocal.addEmployeeCompany(employeeCompany);
        } else {
            // If employeeId does not exist, create new Employee
            Employee newEmployee = new Employee();
            newEmployee.setEmployeeId(employeeId);
            newEmployee.setEmployeeName(employeeName);
            employeeLocal.createEmployee(newEmployee, selectedCompany);
        }
        errorMessage = null;
        successMessage = "created successfully !";
    }

    
    public void resetRegister() {
        // Logic to reset the registration form
        employeeId = "";
        employeeName = "";
        newCompanyName = "";
        selectedCompany = "";
    }
    


    // Getters and setters
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public String getNewCompanyName() {
        return newCompanyName;
    }

    public void setNewCompanyName(String newCompanyName) {
        this.newCompanyName = newCompanyName;
    }

    public String getSelectedCompany() {
        return selectedCompany;
    }

    public void setSelectedCompany(String selectedCompany) {
        this.selectedCompany = selectedCompany;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public List<Company> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<Company> companyList) {
        this.companyList = companyList;
    }

	public String getNewCompanyId() {
		return newCompanyId;
	}

	public void setNewCompanyId(String newCompanyId) {
		this.newCompanyId = newCompanyId;
	}
}
