package com.example.demo.models;

import java.io.Serializable;
import java.time.LocalDate;

import com.opencsv.bean.CsvBindByName;

public class Expense implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@CsvBindByName(column = "departments")
	private String department;
	@CsvBindByName(column = "project_name")
	private String project;
	@CsvBindByName(column = "amount")
	private String amount;
	@CsvBindByName(column = "date")
	private String expenseDate;
	@CsvBindByName(column = "member_name")
	private String member;
	
	
	
	
	public String getDepartment() {
		return department;
	}




	public void setDepartment(String department) {
		this.department = department;
	}




	public String getProject() {
		return project;
	}




	public void setProject(String project) {
		this.project = project;
	}




	public String getAmount() {
		return amount;
	}




	public void setAmount(String amount) {
		this.amount = amount;
	}


	public String getExpenseDate() {
		return expenseDate;
	}




	public void setExpenseDate(String expenseDate) {
		this.expenseDate = expenseDate;
	}




	public String getMember() {
		return member;
	}




	public void setMember(String member) {
		this.member = member;
	}




	@Override
	public String toString() {
		return "Expanse [department=" + department + ", project=" + project + ", amount=" + amount + ", expanseDate="
				+ expenseDate + ", member=" + member + "]";
	}
	
	
	
	
}
