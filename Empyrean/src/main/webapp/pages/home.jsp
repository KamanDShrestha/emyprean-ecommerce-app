<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="controller.dbconnection.DBConnection" %>
<%@ page import="resources.myConstants" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- adding core tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %> <!-- adding formatting tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %> <!-- adding sql tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %> <!-- adding core tag library -->
<sql:setDataSource var="dbConnection" driver ="com.mysql.jdbc.Driver" url ="jdbc:mysql://localhost:3306/empyrean" user="root" password = ""/>
    
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link href="${pageContext.request.contextPath}/css/home.css" rel = "stylesheet">
    
    <!-- import css for social media icons -->  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    
</head>

	<c:set var = "isAdmin" value = "false"/>
	<c:if test="${sessionScope.user != null}">
			<c:set var = "userEmail" scope = "session" value ="${sessionScope.user}@gmail.com"/>
	   			<sql:query var="userRole" dataSource="${dbConnection}">
					SELECT user_role FROM users WHERE user_email = ?
				<sql:param value="${userEmail}"/>
				</sql:query>
		<c:forEach var="userRole" items="${userRole.rows}">
			<c:if test="${userRole.user_role == 0 }">
				<c:set var = "isAdmin" value = "true"/>
			</c:if>
		</c:forEach>
		
	</c:if>
	

<body>
    
   
    <nav class = navbar>
        <div>
            <img src = "${pageContext.request.contextPath}/images/LOGO.png" alt = "logo.png" class = "logo" onclick = "refresh()">
        </div>

        <div class="menu">
            <a href = "${pageContext.request.contextPath}/pages/home.jsp" style="color: red;" >HOME</a>
            
            <a href = "${pageContext.request.contextPath}/pages/product.jsp">PRODUCTS</a>
            
            <a href = "${pageContext.request.contextPath}/pages/about.jsp">ABOUT</a>
            
            <a href = "${pageContext.request.contextPath}/pages/contact.jsp">CONTACT</a>
            
			
			<div style="display:${isAdmin ? 'none' : 'block'}">
	            <a href = "${pageContext.request.contextPath}/pages/profile.jsp">PROFILE</a>
			</div>
			            
            <div style="display:${isAdmin ? 'block' : 'none'}">
	            <a href ="${pageContext.request.contextPath}/pages/admin.jsp">ADMIN</a>
            </div>
            
            <a href = "${pageContext.request.contextPath}/pages/cart.jsp" class="cart"><i class="fa fa-shopping-cart"></i></a>
			



        </div>
        
    </nav>
   
    
    <div class = "upperSection" style=margin-bottom:50px;>
        <div class= "tagLine">
            <h1>Rejoice in this heavenly bliss</h1>
            <h3>You seek, We deliver.</h3>
            <button id="explore" onclick = "redirectToProducts()">Explore</button>
        </div>
    </div>
    
	  </div>
  
    <div class="gridContainer">
        <div class="gridTop" >
            <div class="item1">
                <img src="${pageContext.request.contextPath}/images/lady-crossedHands.jpg" alt="handCrossed.jpg" id = "ladyCross" height="100%" width="auto" >       
            </div>
    
            <div class="item2">
                <img src = "${pageContext.request.contextPath}/images/youGotFriendInMe.jpg" alt = "friend.jpg" id = "friend" height="284px" width="auto" style="padding-bottom: 33px;">
                <img src = "${pageContext.request.contextPath}/images/youThere.jpg" alt = "lookingThere.jpg" id = "looking" height="284px" width="auto">
            </div>
    
            <div class="item3">            
                <img src = "${pageContext.request.contextPath}/images/sunshineHat.jpg" alt = "sunshineHat.jpg" id = "hat" height="363px" width="319px" style="padding-bottom: 30px;">  
                <img src = "${pageContext.request.contextPath}/images/pinkIsNewPink.jpg" alt = "pinkHair.jpg" id = "pinkHair" height="209px" width="319px"> 
                
            </div>
        </div>
        

        <div class="gridMid">
            <div class="item4">
                <img src="${pageContext.request.contextPath}/images/curlyHair.jpg" alt="curlyHair" id = "curlyHair" height="354px" style="padding-bottom: 38px;" >
                <img src="${pageContext.request.contextPath}/images/blueShadow.jpg" alt="blueShadow" id="blueShadow" height="324px" style="padding-bottom: 41px;" >
                <img src="${pageContext.request.contextPath}/images/peaceYellow.jpg" alt="blueShadow" id="blueShadow" height="363px" >

            </div>
            <div class="item5">
                <img src="${pageContext.request.contextPath}/images/skyPants.jpg" alt="sunKiss" id = "skyPants" height="608px" width="auto" style="padding-bottom: 38px;">
                <img src="${pageContext.request.contextPath}/images/pinkLegs.jpg" alt="pinkLegs" id = "pinkLegs" height="478px" width="auto" >
            </div>
            <div class="item6">
                <img src="${pageContext.request.contextPath}/images/sunKiss.jpg" alt="sunKiss" id = "sunKiss" width="auto" height="398px" style="padding-bottom: 41px;">
                <img src="${pageContext.request.contextPath}/images/velma.jpg" alt="velma" id = "velma" width="auto" height="685px" >
            </div>
        </div>   

        <div class="gridBottom">
            <div class="item7">
                <img src="${pageContext.request.contextPath}/images/pinkBag.jpg" alt="pinkBag" id = "pinkBag" style="padding-bottom: 37px;" height="360px">
                <img src="${pageContext.request.contextPath}/images/asianCouple.jpg" alt="asianCouple" id="asianCouple" height="244px">
            </div>
            <div class="item8">
                <img src="${pageContext.request.contextPath}/images/skateBoard.jpg" alt="skateBoard" id = "skateBoard"  width="auto" height="645px">
            </div>
            
            <div class="item9">
                <img src="${pageContext.request.contextPath}/images/asianWhite.jpg" alt="asianWhite" id = "asianWhite" width="auto"  style="padding-bottom: 42px; height: 243px;">
                <img src="${pageContext.request.contextPath}/images/leafPaint.jpg" alt="leafPaint" id = "leafPaint" width="auto" height="356px" >
            </div>

        </div>

        <hr width="80%" class="seperator" align="center">
        
    </div>

    <footer class = "footer" >

        

        <div class = "firstSection"  >
            <div style="display: flex; row-gap: 10px; flex-direction: column; ">
                <img src = "${pageContext.request.contextPath}/images/LOGO.png" alt = "logo.png" class = "logo" onclick = "refresh()">
                <span>EMPYREAN@GMAIL.COM</span>
                <span>+9779801010100</span>
                <p> STAY IN TOUCH </p>
                <div class="social-icons">
                    <!--the social media icons-->
                    <a class="facebook" href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a>
                    <a class="twitter" href="https://twitter.com/"><i class="fa fa-twitter"></i></a>
                    <a class="instagram" href="https://www.instagram.com/"><i class="fa fa-instagram"></i> </a> 
                </div>
            </div>

            
        </div>

        <div class = "thirdSection">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3531.440308600562!2d85.13042874634266!3d27.734562095941612!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x39eb25e27e961e4b%3A0x49c5572c9cc12a60!2sEmpyrean%20Trade%20Pvt.%20Ltd.!5e0!3m2!1sen!2snp!4v1682258101634!5m2!1sen!2snp" 
             width="450px" height="300px" style="border:0; padding-right: 20px; padding-top: 20px;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>    
        </div>
    </footer>
        
</body>

<script>
    const redirectToProducts = () => {
        window.location.href = "product.jsp";
    }
    
    const refresh = () => {
        window.location.reload();
    }
</script>

</html>