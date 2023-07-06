
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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

    <!-- import css for social media icons -->  
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/admin.css">
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
            <a href = "${pageContext.request.contextPath}/pages/home.jsp" >HOME</a>
            
            <a href = "${pageContext.request.contextPath}/pages/product.jsp">PRODUCTS</a>
            
            <a href = "${pageContext.request.contextPath}/pages/about.jsp">ABOUT</a>
            
            <a href = "${pageContext.request.contextPath}/pages/contact.jsp">CONTACT</a>
            
			
			<div style="display:${isAdmin ? 'none' : 'block'}">
	            <a href = "${pageContext.request.contextPath}/pages/profile.jsp">PROFILE</a>
			</div>
			            
            <div style="display:${isAdmin ? 'block' : 'none'}">
	            <a href ="${pageContext.request.contextPath}/pages/admin.jsp"  style="color: red;">ADMIN</a>
            </div>
            
            <a href = "${pageContext.request.contextPath}/pages/cart.jsp" class="cart"><i class="fa fa-shopping-cart"></i></a>
		
        </div>
        
    </nav>
    <div class="customerTable">
        <div class="scrollableCustomer">
            <table>
                <thead>
                    <tr style="background-color: black;">
                        <th style=" font-size: x-large;">Customer</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                        <th>Phone Number</th>
                        <th>Address</th>
                        <th>CCN</th>
                        <th>Birth Date</th>
                      </tr>
                </thead>
                <tbody>
                <sql:query var="userInfo" dataSource="${dbConnection}">
					SELECT * FROM users; 
				</sql:query>
				
				<c:forEach var="userInfo" items="${userInfo.rows}">
                    <tr>
                        <td><img src="http://localhost:8082/images/${userInfo.image_link}"></td>
                        <td>${userInfo.user_FName}</td>
                        <td>${userInfo.user_LName}</td>
                        <td>${userInfo.user_email}</td>
                        <td>${userInfo.user_contact}</td>
                        <td>${userInfo.user_address}</td>
                        <td>${userInfo.cc_number}</td>
                        <td>${userInfo.user_birthDate}</td>
                    </tr>
				</c:forEach>

                    <tr>
                        <td><img src="${pageContext.request.contextPath}/images/curlyHair.jpg"></td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                    </tr>
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/images/img.jpg"></td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                    </tr>
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/images/img.jpg"></td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                        <td>Content 1</td>
                    </tr>
                    <tr>
                        <td><img src="${pageContext.request.contextPath}/images/img.jpg"></td>
                            <td>Content 1</td>
                            <td>Content 1</td>
                            <td>Content 1</td>
                            <td>Content 1</td>
                            <td>Content 1</td>
                            <td>Content 1</td>
                            <td>Content 1</td>
                    </tr>
                    
                <tbody>
            </table>
        </div>
    </div>

    <div class="productTable">
        <div class="scrollableProduct">
            <table class="tableP">
                <thead>
                <sql:query var ="productInfo" dataSource="${dbConnection}">
                	Select * FROM products;
                </sql:query>
                    <tr style="background-color: black;">
                        <th style="width: 15%; font-size: x-large;">Product</th>
                        <th>ProductID</th>
                        <th>Product Name</th>
                        <th>Brand</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Rating</th>
                    </tr>
                </thead>
                <tbody>              
                <c:forEach var="productInfo" items="${productInfo.rows}">
                 <tr>
                        <td><img src="http://localhost:8082/images/products/${productInfo.image_link}"></td>
                        <td>${productInfo.product_id}</td>
                        <td>${productInfo.product_name}</td>
                        <td>${productInfo.product_brand }</td>
                        <td>${productInfo.product_category }</td>
                        <td>${productInfo.product_price}</td>
                        <td>${productInfo.product_quantity }</td>
                        <td>${productInfo.product_ratings }</td>
                    </tr>
                </c:forEach>
                   
                   
                <tbody>
            </table>
          </div>
    </div>


        
    <div class="CreateUpdateDeleteTable">
        <form action="${pageContext.request.contextPath}/ModifyProductServlet" method="post" enctype="multipart/form-data">
            <table>
                <tr>
                    <td><input type='file' onchange="loadFile(event)" id="selectedFile" style="display: none;" name="productImage">
                        <img id="profilePicture" src="${pageContext.request.contextPath}/images/img.jpg" alt="your image" onclick="document.getElementById('selectedFile').click();"/>
                    </td>
                    <td>                           
                       	<input type="text" placeholder="Product ID" name="productID" required id="pID">
                    </td>
                    <td>                            
                        <input type="text" placeholder="Product Name" name="productName" id="pName">
                    </td>
                    <td>
                        <input type="text" placeholder="Product Brand" name="productBrand" id="pBrand">   

                    </td>
                    <td> 
                        <input type="text" placeholder="Product Category" name="productCategory" id="pCat">
                    </td>
                    <td>
                        <input type="text" placeholder="Product Price" name="productPrice" id="pPrice">
                    </td>
                    <td>                            
                        <input type="text" placeholder="Product Quantity" name="productQuantity" id="pQuantity">
                    </td>
                </tr>

            </table>

            <div class="containerButtons">
                <button type="submit" value="Insert" name="submit">Insert</button>
                <button type="submit" value="Update" name="submit">Update</button>
                <button type="submit" value="Delete" name="submit">Delete</button>
            </div>
        </form>

    </div>
        

    <form action ="${pageContext.request.contextPath}/logoutServlet" method="post" class="buttons">
		<button type="submit" style="margin-bottom:30px; width:100px; height:40px; position:relative; left:48%" >Logout</button>
	</form>

   

</body>

<script>

    var loadFile = function(event) {
      var output = document.getElementById('profilePicture');
      output.src = URL.createObjectURL(event.target.files[0]);
      output.onload = function() {
        URL.revokeObjectURL(profilePicture.src) // free memory
      }
    };
</script>  

</html>