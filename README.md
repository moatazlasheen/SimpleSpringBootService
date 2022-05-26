# SimpleSpringBootService


Get all Expenses API URL 
http://localhost:8080/expenses


Filter Expenses by minimum amout API URL 
http://localhost:8080/expenses?amountGte=1


Filter Expenses by member
http://localhost:8080/expenses?member=Jennifer

Filter Expenses by member anf minimum amount
http://localhost:8080/expenses?member=Jennifer&amountGte=721


Ordering and sorting
Default ordering is asc unless the user specifies descending order

filter and sort in ascending order
http://localhost:8080/expenses?member=Jennifer&amountGte=721&sort=amount
or 
http://localhost:8080/expenses?member=Jennifer&amountGte=721&sort=amount&order=asc


filter and sort in descending order
http://localhost:8080/expenses?member=Jennifer&amountGte=721&sort=amount&order=desc


acceptable values for sort are 
department
project
member
amount
expenseDate



