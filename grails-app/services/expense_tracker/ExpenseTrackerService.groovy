package expense_tracker

import grails.gorm.transactions.Transactional

@Transactional
class ExpenseTrackerService {

    List<ExpenseRow> getExpensesWithBalances(User user, List<Expense> expenses) {
        List<ExpenseRow> expenseRows = []
        int runningBalance = user.balance
        for (expense in expenses) {
            runningBalance -= expense.amount
            ExpenseRow row = new ExpenseRow(expenseId: expense.id, amount: expense.amount, name: expense.name, description: expense.description, runningBalance: runningBalance)
            expenseRows.add(row)
        }
        return expenseRows
    }

}
