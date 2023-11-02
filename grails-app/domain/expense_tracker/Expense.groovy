package expense_tracker

class Expense {

    double amount
    String name
    String description
    String userName
    
    static constraints = {
        userName display: false, nullable: true
    }
}
