package com.rewards.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends Reward {

	@Id
	@GeneratedValue
	private Long transactionId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn 
	private Customer customer;
	
	private Double total;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date saveDate;

	@Override
	public Long getPoints() {
		this.points = 0l;

		if (this.total > 50 && this.total <= 100) {
			this.points += (this.total.intValue() - 50) * 1;
		}

		if (this.total > 100) {
			this.points += 50; // 1 point for every dollar spent over $50
			this.points += (this.total.intValue() - 100) * 2; // 2 points for every dollar spent over $100
		}

		return this.points;
	}

	@Override
	public String toString() {
		return String.format("Transaction [transactionId=%s, customer=%s, total=%s, description=%s, saveDate=%s]", transactionId, customer,
				total, description, saveDate);
	}

}
