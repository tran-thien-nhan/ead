package beans;

import java.util.List;
import java.util.Set;

import abstracts.AbstractFacade;
import entities.Company;
import entities.Employee;
import entities.EmployeeCompany;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;


@Stateless(mappedName = "EmployeeSessionBean")
@LocalBean
public class EmployeeSessionBean extends AbstractFacade<Employee> implements EmployeeSessionBeanLocal {

    @PersistenceContext(unitName = "Test2")
    private EntityManager em;
    
	public EmployeeSessionBean() {
		super(Employee.class);
    }

	@Override
	public Employee findEmployeeByEmployeeId(String employeeId) {
		return super.findOne(employeeId);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void createEmployee(Employee employee, String selectedCompany) {
	    if (employee != null && selectedCompany != null) {
	        Company company = em.find(Company.class, selectedCompany);
	        if (company != null) {
	            EmployeeCompany employeeCompany = new EmployeeCompany();
	            employeeCompany.setEmployeeId(employee.getEmployeeId());
	            employeeCompany.setCompanyId(company.getCompanyId());
	            em.persist(employeeCompany);
	            super.create(employee);
	        }
	    }
	}

    @Override
    public boolean isEmployeeIdExists(String employeeId) {
        Query query = em.createQuery("SELECT COUNT(e) FROM Employee e WHERE e.employeeId = :employeeId");
        query.setParameter("employeeId", employeeId);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

	@Override
	public void addEmployeeCompany(EmployeeCompany employeeCompany) {
		em.persist(employeeCompany);		
	}
	
	@Override
	public List<Company> getEmployeeCompanies(String employeeId) {
	    Query query = em.createQuery("SELECT c FROM Company c JOIN EmployeeCompany ec ON c.companyId = ec.companyId WHERE ec.employeeId = :employeeId");
	    query.setParameter("employeeId", employeeId);
	    return query.getResultList();
	}
}
