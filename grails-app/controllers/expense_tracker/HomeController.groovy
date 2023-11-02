package expense_tracker

class HomeController {

    UserService userService
    ExpenseService expenseService
    ExpenseTrackerService expenseTrackerService

    def index() {
        respond([name: session.name ?: 'User'])
    }

    def updateUser(String name, double balance) {
        if (name) {
            session.name = name
        }
        if (!(User.where { name == name }.count() > 0)) {
            userService.save(name, balance)
        }
        redirect(controller: "expense", action: 'index')
    }
}
