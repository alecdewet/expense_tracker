package expense_tracker

import grails.gorm.services.Service

@Service(User)
interface UserService {
    User save(String name, double balance)

    User save(User user)
}

