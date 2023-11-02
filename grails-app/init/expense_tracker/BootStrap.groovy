package expense_tracker

class BootStrap {

    UserService userService
    ExpenseService expenseService

    def init = { servletContext ->
        User user = userService.save('Alec', 10000)

        Expense e1 = expenseService.save(1234, 'Expense 1', 'Description 1', 'Alec')
        Expense e2 = expenseService.save(3400, 'Expense 2', 'Description 2', 'Alec')
        Expense e3 = expenseService.save(222, 'Expense 3', 'Description 3', 'Alec')
        Expense e4 = expenseService.save(667, 'Expense 4', 'Description 4', 'Alec')
        Expense e5 = expenseService.save(289, 'Expense 5', 'Description 5', 'Alec')
        Expense e6 = expenseService.save(1100, 'Expense 6', 'Description 6', 'Alec')
    }

    def destroy = {
    }
}
