package com.example.demo.dao;

import java.util.List;

import com.example.demo.models.Expense;

public interface ExpensesDao {

	List<Expense> getExpenses(Double amoutGte, String member);
	List<Expense> getExpenses(Double amoutGte, String member, String sort, String order );

}
