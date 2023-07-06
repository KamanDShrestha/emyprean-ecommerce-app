<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %> <!-- adding core tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %> <!-- adding formatting tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql" %> <!-- adding sql tag library -->
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %> <!-- adding core tag library -->
<sql:setDataSource var="dbConnection" driver ="com.mysql.jdbc.Driver" url ="jdbc:mysql://localhost:3306/empyrean" user="root" password = ""/>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/profile.css" rel = "stylesheet">
    <title>Document</title>

    <!-- import css for social media icons -->  

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
  
</head>
<body>

   
    <nav class = navbar>
        <div>
            <img src = "${pageContext.request.contextPath}/images/LOGO.png" alt = "logo.png" class = "logo" onclick = "refresh()">
        </div>
        <div class="menu">
            <a href = "${pageContext.request.contextPath}/pages/home.jsp"  >HOME</a>
            
            <a href = "${pageContext.request.contextPath}/pages/product.jsp">PRODUCTS</a>
            
            <a href = "${pageContext.request.contextPath}/pages/about.jsp">ABOUT</a>
            
            <a href = "${pageContext.request.contextPath}/pages/contact.jsp">CONTACT</a>
            
            <a href = "${pageContext.request.contextPath}/pages/profile.jsp" style="color: red;">PROFILE</a>

            <a href = "${pageContext.request.contextPath}/pages/cart.jsp" class="cart" ><i class="fa fa-shopping-cart"></i></a>
        </div>
        
    </nav>
   <div class="profileInfo">
   <c:choose>
   		<c:when test="${sessionScope.user != null}">
			<c:set var = "userEmail" scope = "session" value = "${sessionScope.user}@gmail.com"/>
	   			<sql:query var="currentUser" dataSource="${dbConnection}">
					SELECT * FROM users WHERE user_email = ?
					<sql:param value="${userEmail}"/>
				</sql:query>
				<form class = "currentUser" action = "${pageContext.request.contextPath}/updateCustomerServlet" method = "post" enctype="multipart/form-data">
					<c:forEach var="user" items="${currentUser.rows}">
					<div class="profileContainer">

					<input onchange="loadFile(event)"  name="profileURL" id="selectedFile" style="display: none;" />      
              		
            
					<img name="profilePic" id="profilePicture" src="http://localhost:8082/images/${user.image_link}" alt="${user.user_name}" onclick="document.getElementById('selectedFile').click();"/>
					
					</div>	
                    <div class = "userInfo">
                    	<div class = "userDetails">
						<p>
							 <input type="text" class="userName" value="${user.user_Fname}" name = "user_Fname" readonly> 
							<input type="text" class="userName" value="${user.user_Lname}" name = "user_Lname" readonly> 
						</p>	
						
						<p>
							<label for = "customer_address">Email:&nbsp&nbsp</label> 
							<input type="text" class="userField" value="${user.user_email}" name = "user_email" readonly> 
						</p>
						
						<p>
							<label for = "customer_password">Password:</label> 
							<input  type="password" class="userField" value="${user.user_password}" name = "user_password" readonly> 
						</p>
						
						<p>
							<label for = "customer_address">Address:</label>
							<input type="text" class="userField" value="${user.user_address}" name = "user_address" readonly> 
						</p>
						<p>
							<label for = "customer_Birthdate">BirthDate:</label> 
							<input type="date" class="userField" value="${user.user_birthDate}" name = "user_birthDate" readonly> 
						</p>
					
						<p>
							<label for = "customer_address">Mobile Number:</label> 
							<input type="text" class="userField" value="${user.user_contact}" name = "user_contact" readonly> 
						</p>
						</div>
						
						
						<div class = "buttons">
							<button  id = "editButton" type = "button" onclick = "initiateUpdate()">Edit Details</button>
						  	
							<button id = "updateButton" type = "submit">Update details</button>
														
							
							<button id = "resetButton" type = "button" onclick = "refresh()">Reset</button>
							
						</div>
						
					</div>
					</c:forEach>
					</form>

				
				<div>
				
				</div>
				<br class = "seperator"/>
				<h1 style="text-align:center;">Ordered Products</h1>
				<div class = "orderedProducts">
					<sql:query var="orderedProducts" dataSource="${dbConnection}">
						SELECT * FROM users JOIN orders ON users.user_id = orders.user_id WHERE users.user_email = ?;
					<sql:param value="${userEmail}"/>
				</sql:query>
					
					<c:forEach var = "orders" items = "${orderedProducts.rows}">
					<div class = "eachProduct">
						<div class = "productDetails">
							<span>Order ID: ${orders.order_id}</span>
							<span>Order Date: ${orders.order_date}</span>
							<span>Total cost: ${orders.total_cost}</span>
						</div>
					</div>
					</c:forEach>
				</div>
					<form action ="${pageContext.request.contextPath}/logoutServlet" method="post" class="buttons">
						<button type="submit" style="color:white;margin-top:30px; margin-bottom:-30px;width:100px; height:40px;" >Logout</button>
					</form>
				
   		</c:when>
   		<c:otherwise>
   			<div class = "noProfile" >
   			<h1 style="text-align:center">Sign in to view your profile</h1>
   			<button onclick = "redirectToLogin()" style="position:relative;  left: 50%;">Sign in</button>
   			</div>
   		</c:otherwise>
   </c:choose>
	</div>
	
	<!-- getting the session and user id, if not null then, displaying the values of the current user -->
	<hr width="90%" >
   
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
const redirectToLogin = () => {
	
	  window.location.href = "login.jsp";

	  }


	  const initiateUpdate = () => {

	  document.getElementById('editButton').style.display = 'none';

	  document.getElementById('resetButton').style.display = 'block';

	  document.getElementById('updateButton').style.display = 'block';


	  const userFields = document.querySelectorAll('.userField');

	  const userNames = document.querySelectorAll('.userName');


	  const userImg = document.getElementById('selectedFile');



	  userFields.forEach((userField) => {

	  userField.removeAttribute('readonly');

	  });

	  userNames.forEach((userName) => {

	  userName.removeAttribute('readonly');

	  });


	  selectedFile.setAttribute('type', 'file');


	  }


	const refresh = () => {

	window.location.reload();

	}


	var loadFile = function(event) {

	  var output = document.getElementById('profilePicture');

	  output.src = URL.createObjectURL(event.target.files[0]);

	  output.onload = function() {

	  URL.revokeObjectURL(profilePicture.src) // free memory

	  }

	};
</script>
    

    
</html>