package com.rewards.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private Integer customerId;
	
	private String customerName;
	
	@OneToMany(mappedBy="customer", fetch = FetchType.LAZY)
	private Set<Transaction> transactions;
	
	@JsonInclude
	@Transient
	private Long rewardPoints;
	
	@JsonInclude
	@Transient
	private Double totalPurchases;
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Set<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}
	public void setRewardPoints(Long rewardPoints) {
		this.rewardPoints = rewardPoints;
	}
	public void setTotalPurchases(Double totalPurchases) {
		this.totalPurchases = totalPurchases;
	}
	public Long getRewardPoints() {
		if (transactions == null || transactions.isEmpty()) return 0l;
		
		return transactions.stream().map(x -> x.getPoints().intValue()).reduce(0, (a,b) -> a + b).longValue();
	}
	public Double getTotalPurchases() {
		if (transactions == null || transactions.isEmpty()) return 0d;
		
		return transactions.stream().map(x -> x.getTotal().doubleValue()).reduce(0d, (a,b) -> a + b).doubleValue();
	}
	public Customer(Integer customerId, String customerName, Set<Transaction> transactions, Long rewardPoints,
			Double totalPurchases) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.transactions = transactions;
		this.rewardPoints = rewardPoints;
		this.totalPurchases = totalPurchases;
	}
	public Customer() {
		super();
	}
	
}
