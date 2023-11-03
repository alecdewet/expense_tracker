package expense_tracker

import grails.gorm.transactions.Transactional

@Transactional
class ExpenseTrackerService {

    List<ExpenseRow> getExpensesWithBalances(User user, List<Expense> expenses, double exchangeRate) {
        List<ExpenseRow> expenseRows = []
        int runningBalance = user.balance
        for (expense in expenses) {
            runningBalance -= expense.amount
            def runningBalanceUSD = Math.round(runningBalance * exchangeRate * 100) / 100
            ExpenseRow row = new ExpenseRow(expenseId: expense.id, amount: expense.amount, name: expense.name, description: expense.description, runningBalance: runningBalance, runningBalanceUSD: runningBalanceUSD)
            expenseRows.add(row)
        }
        return expenseRows
    }

}
