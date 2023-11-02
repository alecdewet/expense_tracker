<html>
    <head>
        <meta name="layout" content="main"/>
        <title>Home Page</title>
    </head>

    <body>
        <div id="content" role="main">
            <div class="container col-6 align-content-center justify-content-center">
                <h1 style="margin: 0 auto; width:320px; text-align: center">Welcome ${name}!</h1>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>

                <h4 style="margin: 0 auto; width:320px; text-align: center">This is a simple expense tracker.</h4>

                <g:form action="updateUser" style="margin: 0 auto; width:320px">
                    <div class="form-group">
                        <label>User Name</label>
                        <g:textField name="name" value="" placeholder="${name}" class="form-control"/>
                    </div>
                    <div class="form-group">
                        <label>Starting Balance</label>
                        <g:field type="number" name="balance", value="0" class="form-control"/>
                    </div>
                    <g:submitButton style="margin: 0 auto; width:320px" name="Login" />
                </g:form>
            </div>
        </div>
    </body>
</html>