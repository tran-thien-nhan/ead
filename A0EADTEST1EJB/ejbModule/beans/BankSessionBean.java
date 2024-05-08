package beans;

import java.util.List;

import entities.Bank;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(mappedName = "BankSessionBean")
@LocalBean
public class BankSessionBean implements BankSessionBeanLocal {

    @PersistenceContext(unitName = "BankEjb")
    private EntityManager em;

    public BankSessionBean() {
    }

    @Override
    public List<Bank> findBalanceByRange(float min, float max) {
        return em.createQuery("SELECT b FROM Bank b WHERE b.balancer BETWEEN :min AND :max", Bank.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    @Override
    public void createBank(Bank bank) {
        em.persist(bank);
    }

    @Override
    public double checkCard(String cardNum) {
        Bank bank = em.find(Bank.class, cardNum);
        if (bank != null) {
            return (double)bank.getBalancer();
        }
        return -1;
    }

    @Override
    public double withdrawMoney(String cardNum, float amountWithdraw) {
        Bank bank = em.find(Bank.class, cardNum);
        if (bank != null) {
            float currentBalance = bank.getBalancer();
            if (currentBalance >= amountWithdraw) {
            	float newBalance = currentBalance - amountWithdraw;
                bank.setBalancer(newBalance);
                em.merge(bank);
                return newBalance;
            } else {
                // Insufficient balance
                return -1;
            }
        }
        // Account not found
        return -1;
    }

    @Override
    public double depositMoney(String cardNum, float depositAmount) {
        Bank bank = em.find(Bank.class, cardNum);
        if (bank != null) {
            float currentBalance = bank.getBalancer();
            float newBalance = currentBalance + depositAmount;
            bank.setBalancer(newBalance);
            em.merge(bank);
            return newBalance;
        }
        // Account not found
        return -1;
    }

    @Override
    public Bank findAccount(String cardNum) {
        return em.find(Bank.class, cardNum);
    }

    @Override
    public List<Bank> getAllAccounts() {
        return em.createQuery("SELECT b FROM Bank b", Bank.class).getResultList();
    }
}
