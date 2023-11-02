<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title>Expense Tracker</title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#list-expense" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                        <li><g:link class="create" action="create">New Expense</g:link></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="list-expense" class="col-12 content scaffold-list" role="main">
                    <h1>${user.name}'s Expenses - Starting Balance: <g:formatNumber number="${user.balance}" type="currency" currencySymbol="R"/></h1>
                    <g:if test="${flash.message}">
                        <div class="message" role="status">${flash.message}</div>
                    </g:if>
            

                    <table>
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Amount</th>
                            <th>Balance</th>
                        </tr>
                        <g:each status="i" in="${expenseRowList}" var="row">
                            <tr>
                                <td>${i}</td>
                                <td><g:link action="show" id="${row.expenseId}">${row.name}</g:link></td>
                                <td>${row.description}</td>
                                <td style="text-align: right"><g:formatNumber number="${row.amount}" type="currency" currencySymbol="R"/></td>
                                <td style="text-align: right"><g:formatNumber number="${row.runningBalance}" type="currency" currencySymbol="R"/></td>
                            </tr>
                        </g:each>
                    </table>

                    <g:if test="${expenseCount > params.int('max')}">
                    <div class="pagination">
                        <g:paginate total="${expenseCount ?: 0}" />
                    </div>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>