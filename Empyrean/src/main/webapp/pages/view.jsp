<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- adding core tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %> <!-- adding formatting tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %> <!-- adding sql tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %> <!-- adding core tag library -->
<sql:setDataSource var="dbConnection" driver ="com.mysql.jdbc.Driver" url ="jdbc:mysql://localhost:3306/savor" user="root" password = ""/>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<sql:query var="login" dataSource="${dbConnection}">
		SELECT * FROM login_info; 
	</sql:query>
	
	<c:forEach var="login" items="${login.rows}">
	
		<c:out value ="${login.user_email}"/>
		<c:out value ="${login.user_pass}"/>
		
	</c:forEach>
	
</body>
</html>