package expense_tracker

import grails.gorm.services.Service

@Service(Expense)
interface ExpenseService {
    Expense save(BigDecimal amount, String name, String description)
}
