package expense_tracker

class ExpenseRow {

    static mapWith = "none"

    int expenseId
    double amount
    String name
    String description
    double runningBalance
    double runningBalanceUSD

    static constraints = {
    }

    String toString() {
        expenseId + "," +
        name + "," +
        description + "," +
        amount + "," +
        runningBalance + "," +
        runningBalanceUSD
    }
}
