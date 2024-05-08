package entities;

import jakarta.persistence.*;

@Entity(name = "Bank")
@Table(name = "tbl_HICB")
public class Bank {
	@Id
	private String cardNumber;
	private String customerName;
	private float balancer;
	
	public Bank() {
	}

	public Bank(String cardNumber, String customerName, float balancer) {
		super();
		this.cardNumber = cardNumber;
		this.customerName = customerName;
		this.balancer = balancer;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public float getBalancer() {
		return balancer;
	}

	public void setBalancer(float balancer) {
		this.balancer = balancer;
	}
}
