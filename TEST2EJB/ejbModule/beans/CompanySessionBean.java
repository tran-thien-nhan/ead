package beans;

import java.util.List;

import abstracts.AbstractFacade;
import entities.Company;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless(mappedName = "CompanySessionBean")
@LocalBean
public class CompanySessionBean extends AbstractFacade<Company> implements CompanySessionBeanLocal {

    @PersistenceContext(unitName = "Test2")
    private EntityManager em;
    
    public CompanySessionBean() {
    	super(Company.class);
    }

	@Override
	public void createCompany(Company company) {
		super.create(company);
	}
	
	@Override
	public List<Company> getAllCompanies() {
		// TODO Auto-generated method stub
		return super.findAll();
	}
	
	@Override
	public Company findCompanyByCompanyId(String companyId) {
		// TODO Auto-generated method stub
		return super.findOne(companyId);
	}

	@Override
	protected EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return em;
	}

}
