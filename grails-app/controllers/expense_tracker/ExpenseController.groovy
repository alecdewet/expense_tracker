package expense_tracker

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExpenseController {

    UserService userService
    ExpenseService expenseService
    ExpenseTrackerService expenseTrackerService
    CurrencyConverterService currencyConverterService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        User user = User.findByName(session.name)
        params.max = Math.min(max ?: 10, 100)
        List<ExpenseRow> expenseRows = expenseTrackerService.getExpensesWithBalances(user, expenseService.list(params).findAll {it.userName == user.name}, currencyConverterService.getExchangeRate())
        [expenseRowList: expenseRows, expenseCount: expenseRows.size(), user: user]
    }

    def exportCSV() {
        User user = User.findByName(session.name)
        List<ExpenseRow> expenseRows = expenseTrackerService.getExpensesWithBalances(user, expenseService.list(params).findAll {it.userName == user.name}, currencyConverterService.getExchangeRate())
        String output = "userName,expenseId,name,description,amount,runningBalance,runningBalanceUSD\n"
        for (row in expenseRows) {
            output += user.name + "," + row.toString() + "\n"
        }
        File file = File.createTempFile("expenses",".csv")
        file.write(output)
        response.setHeader "Content-disposition", "attachment; filename=${file.name}"
        response.contentType = 'text-plain'
        response.outputStream << file.text
        response.outputStream.flush()
    }

    def show(Long id) {
        respond expenseService.get(id)
    }

    def create() {
        respond new Expense(params)
    }

    def save(Expense expense) {
        if (expense == null) {
            notFound()
            return
        }
        expense.userName = session.name
        try {
            expenseService.save(expense)
        } catch (ValidationException e) {
            respond expense.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*' { respond expense, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond expenseService.get(id)
    }

    def update(Expense expense) {
        if (expense == null) {
            notFound()
            return
        }

        try {
            expenseService.save(expense)
        } catch (ValidationException e) {
            respond expense.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'expense.label', default: 'Expense'), expense.id])
                redirect expense
            }
            '*'{ respond expense, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        expenseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'expense.label', default: 'Expense'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'expense.label', default: 'Expense'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
