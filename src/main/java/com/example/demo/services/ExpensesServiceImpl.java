package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ExpensesDao;
import com.example.demo.models.Expense;

@Service
public class ExpensesServiceImpl implements ExpensesService {
	
	@Autowired
	private ExpensesDao expensesDao;

	@Override
	public List<Expense> getExpenses(Double amoutGte, String member) {
		return expensesDao.getExpenses(amoutGte, member);
	}

	@Override
	public List<Expense> getExpenses(Double amountGte, String member, String sort, String order) {
		return expensesDao.getExpenses(amountGte, member, sort, order );
	}

}
