package beans;

import java.util.List;

import abstracts.AbstractFacade;
import entities.Account;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless(mappedName = "AccountSessionBean")
@LocalBean
public class AccountSessionBean extends AbstractFacade<Account> implements AccountSessionBeanLocal {

    @PersistenceContext(unitName = "Test3")
    private EntityManager em;
    
    public AccountSessionBean() {
    	super(Account.class);
    }

    @Override
    public boolean Login(String username, String password) {
        Account account = super.findOne(username);
        if (account != null && account.getPassword().equalsIgnoreCase(password)) {
            return true;
        }
        return false;
    }

	@Override
	public List<Account> ViewAll() {
		return super.findAll();
	}

	@Override
	public void Update(Account account) {
		super.update(account);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public Account findOne(String username) {
		return super.findOne(username);
	}

}
