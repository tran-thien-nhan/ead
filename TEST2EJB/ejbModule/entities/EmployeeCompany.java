package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_company")
public class EmployeeCompany {
    @Id
    @Column(name = "EmployeeId")
    private String employeeId;

    @Id
    @Column(name = "CompanyId")
    private String companyId;

	public EmployeeCompany() {
	}

	public EmployeeCompany(String employeeId, String companyId) {
		super();
		this.employeeId = employeeId;
		this.companyId = companyId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
