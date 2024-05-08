package beans;

import java.util.List;

import jakarta.ejb.Local;

import entities.Bank;

@Local
public interface BankSessionBeanLocal {
	List<Bank> findBalanceByRange(float min, float max);
	void createBank(Bank bank);
	double checkCard(String cardNum);
}
