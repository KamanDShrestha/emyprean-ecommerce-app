<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
	

    <div class="leftText">
        <h4 style="color: #8B8E98;padding-top: 50px;">Elegant</h4>
        <h4>Sophisticated</h4>
        <h4>Technical</h4>
    </div>
    
    <div class="leftHeader">
        <h1 style="font-size: 50px;">Empyrean<sup><i>(n)</i></sup> </h1>
    </div>

    <div class="leftHeaderSubtext">
        <h2><i>Let yourself rejoice in this heavenly bliss</i> </h2>
    </div>

    <div>
         <img src="${pageContext.request.contextPath}/images/7xm.xyz117255.jpg" alt="car_red_dress" width="35%" class="red_dress" height="auto">
        <img src="${pageContext.request.contextPath}/images/7xm.xyz873894.jpg" alt="asian_woman" width="40%" class="asian_woman"height="auto">
    </div>

    <div class="formDiv">

        <form action="${pageContext.request.contextPath}/loginServlet" method="post">

            <div class="formInput">

                <span class="formTitle"> <h2> Login to your Account </h2> </span>
                <span class="formSubtitle">Get started with our site, create an account and enjoy the experience.</span>
                <br><br>
                <label for="userEmail">Email</label>
                
                <input type="email" name="userEmail" id="userEmail">
               
                <label for="userPass">Password</label>
                
                <input type="password" name="userPass" id="userPass">
                <%String errorMessage= (String) request.getAttribute("errorMessage"); %>
		<%if(errorMessage != null){%>
			<p class="error-message"><%= errorMessage %></p>
			<%} %>
                <input type="submit" value="Login" style="margin-left: 110px;margin-bottom: 15px;margin-top:10px">
                <hr width="80%">
                <span style="margin-left: 50px">Don't have an account? <a href="${pageContext.request.contextPath}/pages/signIn.jsp" class="signUp">Sign Up!</a></span>

            </div>
            
        </form>
    </div>

    
    

</body>
</html>