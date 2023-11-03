# expense_tracker

How to start the Project

-   If grails is in your PATH vars
    -   In the root dir of project, run command (grails run-app) in cmd
-   Else run ./grailsw run-app in cmd

Current Functionality

-   Simple home page that can be logged in with a name and starting balance and will be saved to the db. If the user already exists, will simply log in without saving. Currently no functionality to change starting balance except by going to /user/show/{id} directly
-   Logging in takes you the main expense tracking table, with all relevant columns. Creating new expenses and selecting existing expenses also allowed. Also the table can be exported to a csv file to download immediately.
-   The currency conversion doesn't work properly, since I couldn't get the rest endpoint to work properly, currently using a hardcoded exchangeRate.
-   Fixer.io's free plan has very limited functionality and doesn't allow you to do any conversions with ZAR. api.freecurrencyapi.com worked without any payment.

How to test the Project

-   Tests have not been implemented, so all tests will fail since grails generate them to fail
-   Reason is because the rest endpoint wasn't working properly so testing would not be testing much.
-   But if you want to, run either (grails test-app) or (./grails test-app)

This was my first project with grails, it has been a great learning experience but not without it's issues.

-   Main issues were most documentation is heavily out of date, non-existant or not easily accessible.
-   A lot of my time was wasted by looking for the simple syntax of some grails functionality that either isn't clear or most answers on the web is out-of-date and doesn't work anymore without a clear updated solution
-   Plugins were hard to understand, since it's implementation has heavily changed over time. I couldn't get any CSV exporter to work, so I wrote my own, and the REST plugins were hard to get to build without errors, and then it didn't even work since the GET method returned an empty json even though directly calling the endpoint works as intended in the browser.
-   On the positive side, the MVC side of grails was very satisfying coming from Spring, as grails favours convention over configuration. I liked the fact that just putting classes in certain folders generated the configuration for the controllers, services and domain objects. Scaffolding is also a very powerful tool to quickly have a codebase to build from.

Optimisations that can be done when 10k users use the system concurrently?

-   The obvious way that stands out immediately is to improve the codebase, having a chaotic codebase without clear guidelines and rules will quickly become a mess and will run inefficiently since anything added will be patched on.
-   Database calls can be resource intensive, reducing the amount of queries and optimizing complicated queries can improve perfomance greatly under heavy load.
-   Caching some data for the user that is used repeatedly can help reduce unnecessary calls to rest endpoints or db queries
-   When the database grows large enough, an easy way to improve access speed is to implement indexing on common columns.
