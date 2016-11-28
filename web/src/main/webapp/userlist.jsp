
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>

        <title>User List</title>
        <link rel="stylesheet" type="text/css" href="css/itemlist.css"/>
    </head>
    <body>

<%--<sql:setDataSource var="datasource"
        driver="com.mysql.jdbc.Driver"
        url="jdbc:mysql://seadev.tk:3306/db1"
        user="user1" password="111"
/>
<sql:query var="users" dataSource="${datasource}">
    SELECT user_id, first_name, last_name, email FROM Users;
</sql:query>--%>

        <div class="wrapper">
            <main class="content">

                <table>
                    <h3>Users</h3>


                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Phone</th>
                    </tr>

                    <c:forEach var="user" items="${ users }">

                        <tr>
                            <td><c:out value="${user.userId}" /></td>
                            <td><c:out value="${user.firstName}" /></td>
                            <td><c:out value="${user.lastName}" /></td>
                            <td><c:out value="${user.email}" /></td>
                            <td><c:out value="${user.phone}" /></td>
                        </tr>

                    </c:forEach>

                </table>


            </main>
        </div>

    </body>
</html>