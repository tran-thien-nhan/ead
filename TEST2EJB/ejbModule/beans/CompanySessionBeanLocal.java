package beans;

import java.util.List;

import entities.Company;
import jakarta.ejb.Local;

@Local
public interface CompanySessionBeanLocal {
	void createCompany(Company company);
	List<Company> getAllCompanies();
	Company findCompanyByCompanyId(String companyId);
}
