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
        User user = User.findByName(name)
        if (user) {
            if (balance != 0) {
                user.balance = balance
                userService.save(user)
            }
        } else {
            userService.save(name, balance)
        }
        redirect(controller: "expense", action: 'index')
    }
}
