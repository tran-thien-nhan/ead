package beans;

import java.util.List;

import entities.Bank;
import jakarta.ejb.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless(mappedName = "BankSessionBean")
@LocalBean
public class BankSessionBean implements BankSessionBeanLocal {

    @PersistenceContext(unitName = "BankEjb")
    private EntityManager em;

    public BankSessionBean() {
    }

    @Override
    public List<Bank> findBalanceByRange(float min, float max) {
        return em.createQuery("SELECT b FROM Bank b WHERE b.balance BETWEEN :min AND :max", Bank.class)
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
}
