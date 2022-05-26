package com.example.demo.controllers;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Expense;
import com.example.demo.services.ExpensesService;

@RestController
public class ExpensesController {
	
	@Autowired
	private ExpensesService expensesService;

	@GetMapping("/expenses")
	public List<Expense> getExpansesData ( @PathParam(value = "amountGte") Double amountGte, 
			@PathParam(value = "member") String member,
			@PathParam(value = "sort") String sort, 
			@PathParam(value = "order") String order ) {
		return expensesService.getExpenses( amountGte, member, sort, order );
	}
	
	
}
