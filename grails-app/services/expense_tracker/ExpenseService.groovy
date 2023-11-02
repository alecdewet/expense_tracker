package expense_tracker

import grails.gorm.services.Service

@Service(Expense)
interface ExpenseService {

    Expense get(Serializable id)

    List<Expense> list(Map args)

    Long count()

    void delete(Serializable id)

    Expense save(Expense expense)

    Expense save(double amount, String name, String description, String userName)

}