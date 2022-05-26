package com.example.demo.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.example.demo.models.Expense;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Repository
public class ExpensesDaoImpl implements ExpensesDao {
	
	
	private List<Expense> expenses;
	private final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
	
	@PostConstruct
	public void init () {
		
		expenses = new ArrayList<Expense>();
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:expanses.csv");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 try ( Reader reader = new BufferedReader(new FileReader(file))) {

             // create csv bean reader
             CsvToBean<Expense> csvToBean = new CsvToBeanBuilder(reader)
                     .withType(Expense.class)
                     .withIgnoreLeadingWhiteSpace(true)
                     .build();

             expenses = csvToBean.parse();

         } catch (Exception ex) {
        	 ex.printStackTrace();
         }
	}
	
	@Override
	public List<Expense> getExpenses(Double amoutGte, String member) {
		List<Expense> result = null;
		Stream<Expense> expensesSteam = expenses.stream();
		if ( StringUtils.hasText(member) ) {
			expensesSteam = expensesSteam.filter(expense -> member.equals(expense.getMember()));
		}
		if ( amoutGte != null ) {
			expensesSteam = expensesSteam.filter( expense -> Double.parseDouble(expense.getAmount().substring(0, expense.getAmount().length()-1).replaceAll(",", "")) >= amoutGte );
		}
		result = expensesSteam.collect(Collectors.toList());
		return result;
	}

	@Override
	public List<Expense> getExpenses(Double amoutGte, String member, String sort, String order) {
		List<Expense> result = null;
		Stream<Expense> expensesSteam = expenses.stream();
		if ( StringUtils.hasText(member) ) {
			expensesSteam = expensesSteam.filter(expense -> member.equals(expense.getMember()));
		}
		if ( amoutGte != null ) {
			expensesSteam = expensesSteam.filter( expense -> Double.parseDouble(expense.getAmount().substring(0, expense.getAmount().length()-1).replaceAll(",", "")) >= amoutGte );
		}
		Comparator<Expense> comparator = generateComparator(order, sort);
		
		if ( comparator != null ) {
			expensesSteam = expensesSteam.sorted(comparator);
		}
		result = expensesSteam.collect(Collectors.toList());
		return result;
	}
	
	
	private Comparator<Expense> generateComparator(String order, String sort) {
		Comparator<Expense> comparator = null;
		if ( StringUtils.hasText(sort) ) {
			
			switch (sort) {
			case "department":
				if ( "desc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> b.getDepartment().compareTo(a.getDepartment());
				} else if (  !StringUtils.hasText(order) || "asc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> a.getDepartment().compareTo(b.getDepartment());
				}
				break;
				
			case "project":
				if ( "desc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> b.getProject().compareTo(a.getProject());
				} else if (  !StringUtils.hasText(order) || "asc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> a.getProject().compareTo(b.getProject());
				}
				break;
				
			case "member":
				if ( "desc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> b.getMember().compareTo(a.getMember());
				} else if (  !StringUtils.hasText(order) || "asc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> a.getMember().compareTo(b.getMember());
				}
				break;
				
			case "amount":
				if ( "desc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> (int) (Double.parseDouble(b.getAmount().substring(0, b.getAmount().length()-1).replace(",", "")) 
							- (Double.parseDouble(a.getAmount().substring(0, a.getAmount().length()-1).replace(",", ""))));
				} else if (  !StringUtils.hasText(order) || "asc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> (int) (Double.parseDouble(a.getAmount().substring(0, a.getAmount().length()-1).replace(",", "")) 
							- (Double.parseDouble(b.getAmount().substring(0, b.getAmount().length()-1).replace(",", ""))));
				}
				break;

				
			case "expenseDate":
				if ( "desc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> getLocalDate(b.getExpenseDate()).compareTo(getLocalDate(a.getExpenseDate()));
				} else if (  !StringUtils.hasText(order) || "asc".equalsIgnoreCase(order) ) {
					comparator = (a,b) -> getLocalDate(a.getExpenseDate()).compareTo(getLocalDate(b.getExpenseDate()));
				}
				break;
				
				
			default:
				break;
			}
		}
		return comparator;
	}

	private LocalDate getLocalDate ( String DateAsString ) {
		StringBuffer sb = new StringBuffer(DateAsString);
		if ( sb.charAt(1) == '/' ) {
			sb.insert(0, '0');
		}
		if ( sb.charAt(4) == '/' ) {
			sb.insert(3, '0');
		}
		return LocalDate.parse(sb, DATE_FORMATTER);
	}

}