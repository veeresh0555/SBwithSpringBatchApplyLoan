package com.sbatch.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="loanaccountdetails")
public class LoanAccountDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long loanid;
	
	
	private long accountno;
	
	private BigDecimal amount;
	
	
	@ManyToOne
	@JoinColumn(name = "userid")
	@JsonIgnore
	private User user;


	public long getLoanid() {
		return loanid;
	}


	public void setLoanid(long loanid) {
		this.loanid = loanid;
	}


	public long getAccountno() {
		return accountno;
	}


	public void setAccountno(long accountno) {
		this.accountno = accountno;
	}


	public BigDecimal getAmount() {
		
		return amount;
	}


	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
	
	
	
	
	
	
}
