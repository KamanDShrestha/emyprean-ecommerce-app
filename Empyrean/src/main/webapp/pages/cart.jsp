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
 <!-- import css for social media icons -->  
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cart.css">

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
	            <a href ="${pageContext.request.contextPath}/pages/admin.jsp">ADMIN</a>
            </div>
            
            <a href = "${pageContext.request.contextPath}/pages/cart.jsp" class="cart" style="color:red;"><i class="fa fa-shopping-cart"></i></a>
		
        </div>
        
    </nav>
	
	<c:if test="${sessionScope.user != null}">
			<c:set var = "userEmail" scope = "session" value ="${sessionScope.user}@gmail.com"/>
	   			<sql:query var="currentProduct" dataSource="${dbConnection}">
					SELECT carts_products.cart_product_id,products.product_id,products.product_name, products.image_link, products.product_price FROM users INNER JOIN carts ON users.user_id = carts.user_id INNER JOIN carts_products ON carts.cart_id = carts_products.cart_id INNER JOIN products ON products.product_id = carts_products.product_id  WHERE user_email = ?
				<sql:param value="${userEmail}"/>
				</sql:query>
	</c:if>
    
        
			<c:if test="${currentProduct.rowCount != 0}">
                <c:forEach var = "product" items = "${currentProduct.rows}">
	                   <div class="product" id="${product.cart_product_id}">
				        <img src="http://localhost:8082/images/products/${product.image_link}" alt="">
				        <span>${product.product_name}</span>
				        <span>${product.product_price}</span>
				        <button style="text-align: right;" class="increment fa fa-plus-circle" onclick = "inc()"></button>
				            <p class="counter">0</p>
				        <button class="decrement fa fa-minus-circle" onclick = "dec()"></button>
				            
				        <span>
				            ${product.product_price}
				        </span>
				
				        <span>
	                        <form action="${pageContext.request.contextPath}/RemoveFromCartServlet" method="post">
	                            <button name="remove" class="remove" type="submit" value="${product.product_id })">x</button>
	                        </form>				        	
	                    </span>

				    </div>

                </c:forEach>
                 
			    <hr width="20%" align="right" style="margin-right: 40px;">
			    <div class="total" style="text-align: end; margin-right: 40px;">
			        <form action="${pageContext.request.contextPath}/confirmOrderServlet" method="post">
			            <span style="font-size: 20px;">
			                Total Price <br>
			                <input type = "text" value = "0" style="text-align:end;border:none;" name= "total_cost" id = "Total" readOnly>
			            </span> 
			            <br>
			            <button type="submit" class="checkOut" >CheckOut</button>  
			        </form>
       
   				</div>
               </c:if>
               
               <c:if test="${currentProduct.rowCount == 0}">
				    <div  style="  display: flex; justify-content: center; align-items: center; height: 400px; ">
				        <h2>No Products in Cart</h2> 
				    </div>               
			    </c:if>

    <script>
    
    const dec = () => {

    	console.log(event.target.parentNode.getAttribute('id'));

    	var parentId = document.getElementById(event.target.parentNode.getAttribute('id'));
    	const totalPriceId = document.getElementById('Total');
    	
		console.log(totalPriceId);
    	console.log(parentId);

    	const counterEl = parentId.children[4];

    	console.log(counterEl);

    	console.log(counterEl.textContent)

    	let currentValue = parseInt(parentId.children[4].innerHTML);

    	let fixedVal = parseInt(parentId.children[2].innerHTML);

    	console.log(fixedVal);

    	let total = parentId.children[6];

    	console.log(currentValue)

    	console.log(currentValue);

    	if (currentValue > 0) {

    	currentValue--;

    	counterEl.textContent = currentValue;

    	}
    	totalPrice = parseInt(totalPriceId.value);
    	total.textContent = changingValue(currentValue, fixedVal);
		totalPriceId.value = subtractingValue(totalPrice, fixedVal);

    	}


    	const inc = () => {

    	console.log(event.target.parentNode.getAttribute('id'));

    	var parentId = document.getElementById(event.target.parentNode.getAttribute('id'));
    	const totalPriceId = document.getElementById('Total');
		
    	
		
    	console.log(parentId);
		console.log(totalPriceId);
    	const counterEl = parentId.children[4];

    	console.log(counterEl);

    	console.log(counterEl.textContent)

    	let currentValue = parseInt(parentId.children[4].innerHTML);

    	let fixedVal = parseInt(parentId.children[2].innerHTML);

    	console.log(fixedVal);

    	let total = parentId.children[6];

    	console.log(currentValue)

    	console.log(currentValue);

    	if(currentValue < 9){

    	currentValue++;

    	counterEl.textContent = currentValue;
		
    	}
		
    	totalPrice = parseInt(totalPriceId.value);
    	total.textContent = changingValue(currentValue, fixedVal); 
    	totalPriceId.value = addingValue(totalPrice, fixedVal);

    	}
    	
    	const changingValue = (currentValue, fixedValue) => {

    		return currentValue * fixedValue;

    		}
    	
    	
    	const addingValue = (totalPrice, fixedVal) => {
    		return totalPrice + fixedVal;
    	}
    	
    	const subtractingValue = (totalPrice, fixedVal) => {
    		return totalPrice - fixedVal;
    	}
    </script>
</body>
</html>	