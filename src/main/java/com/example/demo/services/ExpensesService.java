package com.example.demo.services;

import java.util.List;

import com.example.demo.models.Expense;

public interface ExpensesService {

	List<Expense> getExpenses(Double amountGte, String member);

	List<Expense> getExpenses(Double amountGte, String member, String sort, String order);

}
