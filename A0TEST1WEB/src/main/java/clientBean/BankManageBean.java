package clientBean;

import java.io.Serializable;
import java.util.Comparator;
import java.util.*;

import entities.Bank;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import beans.*;

@Named(value = "BankManageBean")
@SessionScoped
public class BankManageBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Bank> accounts = new ArrayList<>();
	private Bank bank;
	private String cardNumber;
	private Double balanceInfo;
	private float minBalance;
	private float maxBalance;
	private String accountCardName;
	private String accountName;
	private Float accountFirstDeposit;
	private String errorMessage;
	private String successMessage;
	private float withdrawAmount;
	private float depositAmount;
    private String searchCustomerName;
    private String searchCardNumber;
    private List<Bank> initialAccounts = new ArrayList<>();
	@Inject
	private BankSessionBeanLocal bankLocal;
	
	@PostConstruct
	public void initialize() {
		bank = new Bank();
		initialAccounts = bankLocal.getAllAccounts();
        accounts.addAll(initialAccounts); 
	}
	
	public String backToHome() {
	    // Đặt lại tất cả các giá trị nhập và tin nhắn
	    cardNumber = null;
	    balanceInfo = null;
	    minBalance = 0;
	    maxBalance = 0;
	    accountCardName = null;
	    accountName = null;
	    accountFirstDeposit = null;
	    errorMessage = null;
	    successMessage = null;
	    withdrawAmount = 0;
	    depositAmount = 0;
	    searchCardNumber = null;
	    searchCustomerName = null;

	    // Cập nhật danh sách tài khoản với tất cả các tài khoản ban đầu
	    accounts.clear();
	    accounts.addAll(initialAccounts);

	    return "check_card?faces-redirect=true";
	}
	
	public String checkCardNumber() {
		balanceInfo = bankLocal.checkCard(cardNumber);
		if (balanceInfo == -1) {
			return "error?faces-redirect=true";
		}
		return "welcome?faces-redirect=true";
	}

	public String checkRangeBalance() {
	    if (maxBalance < minBalance) {
	    	errorMessage = "min should not lower than max";
	        return null;
	    }

	    accounts = bankLocal.findBalanceByRange(minBalance, maxBalance);
	    return "list?faces-redirect=true";
	}
	
	public String withdrawMoney() {
        if (withdrawAmount <= 0) {
        	successMessage = null;
            errorMessage = "Withdraw amount must be greater than zero";
            return null;
        }
        
        if (withdrawAmount > balanceInfo) {
        	successMessage = null;
            errorMessage = "Insufficient balance";
            return null;
        }
        
        // Call method to withdraw money
        int success = (int) bankLocal.withdrawMoney(cardNumber, withdrawAmount);
        if (success > 0) {
            // Update balanceInfo
            balanceInfo -= withdrawAmount;
            errorMessage = null;
            successMessage = "withdraw successfully !";
        } else {
        	successMessage = null;
            errorMessage = "Failed to withdraw money. Please try again later.";
        }
        return null;
    }
    
    public String moveToRegister() {
    	return "register?faces-redirect=true";
    }
    
    public String register() {
        // Check if the provided card number already exists
        Bank existingAccount = bankLocal.findAccount(accountCardName);
        if (existingAccount != null) {
            // Display error message if the account already exists
        	successMessage = null;
            errorMessage = "Account with this card number already exists.";
            return null;
        }
        
        // Create a new bank account with provided details
        Bank newAccount = new Bank();
        newAccount.setCardNumber(accountCardName);
        newAccount.setCustomerName(accountName);
        newAccount.setBalancer(accountFirstDeposit);

        // Call the session bean to persist the new account
        bankLocal.createBank(newAccount);
        successMessage = "created back account successfully !";
        
        // Redirect to a success page
        return "check_card?faces-redirect=true";
    }
    
    public String depositMoney() {
        if (depositAmount <= 0) {
        	successMessage = null;
            errorMessage = "Deposit amount must be greater than zero";
            return null;
        }

        // Call method to deposit money
        int success = (int) bankLocal.depositMoney(cardNumber, depositAmount);
        if (success > 0) {
            // Update balanceInfo
            balanceInfo += depositAmount;
            errorMessage = null;
            successMessage = "deposit successfully !";
        } else {
        	successMessage = null;
            errorMessage = "Failed to deposit money. Please try again later.";
        }
        return null;
    }
    
    public String resetRegister() {
        // Reset all input fields and error messages to their initial state
        cardNumber = null;
        balanceInfo = null;
        minBalance = 0;
        maxBalance = 0;
        accountCardName = null;
        accountName = null;
        accountFirstDeposit = null;
        errorMessage = null;
        successMessage = null;
        withdrawAmount = 0;
        depositAmount = 0;
        
        // Redirect back to the same page
        return "register?faces-redirect=true";
    }
    
    public String resetWelcome() {
        // Reset all input fields and error messages to their initial state
        minBalance = 0;
        maxBalance = 0;
        accountCardName = null;
        accountName = null;
        accountFirstDeposit = null;
        errorMessage = null;
        successMessage = null;
        withdrawAmount = 0;
        depositAmount = 0;
        
        // Redirect back to the same page
        return "welcome?faces-redirect=true";
    }
    
    public String resetList() {
        searchCardNumber = null;
        searchCustomerName = null;
        errorMessage = null;
        successMessage = null;

        accounts = bankLocal.getAllAccounts();

        return "list?faces-redirect=true";
    }
    
    public String searchByCustomerName() {
        if (searchCustomerName == null || searchCustomerName.isEmpty()) {
            // Nếu không có tên khách hàng được nhập, hiển thị tất cả các tài khoản
            accounts.clear();
            accounts.addAll(initialAccounts);
            return null;
        }
        
        // Tạo danh sách mới để lưu kết quả tìm kiếm
        List<Bank> searchResults = new ArrayList<>();
        
        // Lặp qua danh sách tài khoản ban đầu và thêm các tài khoản phù hợp vào danh sách kết quả
        for (Bank account : initialAccounts) {
            if (account.getCustomerName().toLowerCase().contains(searchCustomerName.toLowerCase())) {
                searchResults.add(account);
            }
        }
        
        // Cập nhật danh sách hiện tại để hiển thị kết quả tìm kiếm
        accounts.clear();
        accounts.addAll(searchResults);
        return null;
    }

    public String searchByCardNumber() {
        if (searchCardNumber == null || searchCardNumber.isEmpty()) {
            // Nếu không có số thẻ được nhập, hiển thị tất cả các tài khoản
            accounts.clear();
            accounts.addAll(initialAccounts);
            return null;
        }
        
        // Tạo danh sách mới để lưu kết quả tìm kiếm
        List<Bank> searchResults = new ArrayList<>();
        
        // Lặp qua danh sách tài khoản ban đầu và thêm các tài khoản phù hợp vào danh sách kết quả
        for (Bank account : initialAccounts) {
            if (account.getCardNumber().equals(searchCardNumber)) {
                searchResults.add(account);
            }
        }
        
        // Cập nhật danh sách hiện tại để hiển thị kết quả tìm kiếm
        accounts.clear();
        accounts.addAll(searchResults);
        return null;
    }


	public String getAccountCardName() {
		return accountCardName;
	}

	public void setAccountCardName(String accountCardName) {
		this.accountCardName = accountCardName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public Float getAccountFirstDeposit() {
		return accountFirstDeposit;
	}

	public void setAccountFirstDeposit(Float accountFirstDeposit) {
		this.accountFirstDeposit = accountFirstDeposit;
	}
	
    public String sortByBalanceAscending() {
        accounts.sort(Comparator.comparingDouble(Bank::getBalancer));
        return null;
    }

    public String sortByBalanceDescending() {
        accounts.sort(Comparator.comparingDouble(Bank::getBalancer).reversed());
        return null;
    }

	public List<Bank> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Bank> accounts) {
		this.accounts = accounts;
	}

	public BankSessionBeanLocal getBankLocal() {
		return bankLocal;
	}

	public void setBankLocal(BankSessionBeanLocal bankLocal) {
		this.bankLocal = bankLocal;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Double getBalanceInfo() {
		return balanceInfo;
	}

	public void setBalanceInfo(Double balanceInfo) {
		this.balanceInfo = balanceInfo;
	}

	public float getMinBalance() {
		return minBalance;
	}

	public void setMinBalance(float minBalance) {
		this.minBalance = minBalance;
	}

	public float getMaxBalance() {
		return maxBalance;
	}

	public void setMaxBalance(float maxBalance) {
		this.maxBalance = maxBalance;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public float getWithdrawAmount() {
		return withdrawAmount;
	}

	public void setWithdrawAmount(float withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}

	public float getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(float depositAmount) {
		this.depositAmount = depositAmount;
	}

	public String getSuccessMessage() {
		return successMessage;
	}

	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}

	public String getSearchCustomerName() {
		return searchCustomerName;
	}

	public void setSearchCustomerName(String searchCustomerName) {
		this.searchCustomerName = searchCustomerName;
	}

	public String getSearchCardNumber() {
		return searchCardNumber;
	}

	public void setSearchCardNumber(String searchCardNumber) {
		this.searchCardNumber = searchCardNumber;
	}

	public List<Bank> getInitialAccounts() {
		return initialAccounts;
	}

	public void setInitialAccounts(List<Bank> initialAccounts) {
		this.initialAccounts = initialAccounts;
	}
	
}
