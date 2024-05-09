package beans;

import java.util.List;

import entities.Account;
import jakarta.ejb.Local;

@Local
public interface AccountSessionBeanLocal {
	boolean Login(String username, String password);
	List<Account> ViewAll();
	Account findOne(String username);
	void Update(Account account);
}
