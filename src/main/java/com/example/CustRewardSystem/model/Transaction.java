package com.example.CustRewardSystem.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Transaction {
	private String trans_id;
	private String cust_id;
	private double trans_amt;
	private LocalDate trans_date;
	
	public Transaction(String trans_id, String cust_id, double amt, LocalDate trans_date, double trans_amt) {
		super();
		this.trans_id = trans_id;
		this.cust_id = cust_id;
		this.trans_amt = trans_amt;
		this.trans_date = trans_date;
	}
	
	public String getTrans_id() {
		return trans_id;
	}
	public void setTrans_id(String trans_id) {
		this.trans_id = trans_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public double getTrans_amt() {
		return trans_amt;
	}
	public void setTrans_amt(double trans_amt) {
		this.trans_amt = trans_amt;
	}
	public LocalDate getTrans_date() {
		return trans_date;
	}
	public void setTrans_date(LocalDate trans_date) {
		this.trans_date = trans_date;
	}
	
	@Override
	public String toString() {
		return "Transaction [trans_id=" + trans_id + ", cust_id=" + cust_id + ", trans_amt=" + trans_amt + ", trans_date="
				+ trans_date + "]";
	}
}
