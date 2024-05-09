package clientBean;

import java.io.Serializable;
import java.util.List;

import beans.AccountSessionBeanLocal;
import entities.Account;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value = "AccountManageBean")
@SessionScoped
public class AccountManageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Account account;
	private List<Account> accounts;
	private String successMessage;
	private String errorMessage;
	private String username;
	private String password;
	
	@Inject
	private AccountSessionBeanLocal accountLocal;
	
    @PostConstruct
    public void initialize() {
    	account = new Account();
        accounts = accountLocal.ViewAll();
    }
    
    public String home() {
    	errorMessage = null;
    	successMessage = null;
    	username = null;
    	password = null;
    	
    	return "login?faces-redirect=true";
    }
    
    public String checkLogin() {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            errorMessage = "Please fill in both username and password fields.";
            return null;
        }
        
        if(username.length() < 2 || password.length() < 2) {
        	errorMessage = "invalid input, please try again !";
        	return null;
        }
        boolean result = accountLocal.Login(username, password);
        
        if (result) {
            errorMessage = null;
            return "list?faces-redirect=true";
        } else {
            errorMessage = "Login failed! Please check your username and password.";
            return null;
        }
    }

    
    public String showFormEdit(String username) {
    	account = accountLocal.findOne(username);
    	return "edit";
    }
    
    public String updateAccount() {
        String stringAge = String.valueOf(account.getAge());
        
        if (account.getUsername().isBlank() || account.getPassword().isBlank() || 
            account.getFullname().isBlank() || stringAge.isBlank() || 
            account.getUsername() == null || account.getPassword() == null || 
            account.getFullname() == null || account.getAge() == 0) {
            errorMessage = "Please fill in all required fields.";
            return null;
        }        
        else if(account.getUsername().length() < 2 || account.getPassword().length() < 2 || account.getFullname().length() < 2 || stringAge.length() < 2) {
        	errorMessage = "invalid input, please try again !";
        	return null;
        }        
        else {
            try {
                int age = Integer.parseInt(stringAge);
                if (age < 18 || age < 0) {
                    errorMessage = "Age must not be lower than 18 years old or be negative";
                    return null;
                }
                else if(age > 100) {
                	errorMessage = "age must not bigger than 100 years old";
                	return null;
                }
            } catch (NumberFormatException e) {
                errorMessage = "Invalid age format.";
                return null;
            }
        }
        errorMessage = null;
        accountLocal.Update(account);
        accounts = accountLocal.ViewAll();
        return "list?faces-redirect=true";
    }
    
    public String backToList() {
    	errorMessage = null;
    	successMessage = null;
    	
    	return "list?faces-redirect=true";
    }
    
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}



	public String getSuccessMessage() {
		return successMessage;
	}



	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}



	public String geterrorMessage() {
		return errorMessage;
	}



	public void seterrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
