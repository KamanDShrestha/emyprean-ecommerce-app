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
    <link href="${pageContext.request.contextPath}/css/product.css" rel = "stylesheet">
    <title>Document</title>

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
    
    <!-- more styling is required. -->
    <div class="sidepanel" id="mySidepanel">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
         <form action="${pageContext.request.contextPath}/filterByCategoryServlet" class = "filterByCategory" method="get">
	   		 <select name="category">
		        <option value="all">All categories</option>
		        <option value="Tshirt">Tshirt</option>
		        <option value="Jacket">Jacket</option>
		        <option value="Summer Wear">Summer Wear</option>
	        <!-- Add more options as needed -->
	    	</select>
	    	<br>
	    	
	    	<div id ="brands">
	    		<h3>Our brands</h3>
		    	<input type="radio" id="brandRadio" name="brands" value="Fila">
				<label for="Fila">Fila</label><br>
				<input type="radio" id="brandRadio" name="brands" value="Nike">
				<label for="Nike">Nike</label><br>
				<input type="radio" id="brandRadio" name="brands" value="Adidas">
				<label for="Adidas">Adidas</label>
   			 </div>
   			 
   			 <div class = "prices">
   			 	<h3>Find the ideal price</h3>
		    	<input type="range" min="0" max="5000" value="0" step="500" class="slider" name = "priceSlider" id="priceRange" list="markers" /> <br>
		    	<label for="priceRange">Price Range:</label>
				<span id="sliderValue"></span>
				<datalist id="markers">
				  <option value="0">0</option>
				  <option value="1000">1000</option>
				  <option value="2000">2000</option>
				  <option value="3000">3000</option>
				  <option value="4000">4000</option>
				  <option value="5000">5000</option>
				 <!--  <option value="3000"></option>
				  <option value="3500"></option>
				  <option value="4000"></option>
				  <option value="4500"></option>
				  <option value="5000"></option>  -->
				</datalist>
	    	</div>
	    	
	    	
	    	<button type="submit">Filter</button>
	    	
	    	
		</form>
    </div>
    <div class="filter-search-container">
        <form action="${pageContext.request.contextPath}/searchServlet" class="searchServlet" method="get">
            <input type="text" placeholder="search" class="search" name="searchText">
            <button type="submit"  class="search_button"><i class="fa fa-search"></i></button>
        </form>

        <div class="sort_by_filter">
                <button class="sort_by" onclick="myFunction()">Sort By</button>
                <div id="sort_by" class="sort_by-content">
                    <form action="${pageContext.request.contextPath}/sortByServlet" class="" method = "post">
                        <button type="submit" value="Desc" style="margin-top: 10px;" name="DescPrice">Price (Desc)</button>
                        <button type="submit" value="Asc" style="margin-top: 10px;" name="AscPrice">Price (Asc)</button>
                        <button type="submit" value="Asc" style="margin-top: 10px;" name="Ratings">Ratings</button>
                    </form>
                </div>
            |
            <button class="filter" onclick="openNav()">Filter</button>
        </div>
    </div>
    
   
	<h1>${searchedProducts}</h1>
	<c:if test="${empty searchedProducts}">
  		<p>Cannot find the item of your choice</p>
	</c:if>
	<c:if test="${not empty searchedProducts}">
    	<div class="product">
			<c:forEach var="searchedP" items="${searchedProducts}">
			 	<figure>
	            	<img src="${searchedP.image_link}" alt="${searchedP.productName}">
	           		<figcaption>
	            		${searchedP.productName}	 <br>
		            	Brand:${searchedP.productBrand} <br>
		            	In Stock: ${searchedP.productRatings}
		            	In Stock: ${searchedP.productPrice}
		            	In Stock: ${searchedP.productQuantity}
	            		<div class = "cartDiv">
		            		<button type="submit" class="addToCart" value="${products.product_id}" name="toCart">
		            		<i class='fa fa-cart-plus'></i>
		            		</button>
	            		</div>
	            	</figcaption>
	        	</figure>
			</c:forEach> 
	 	</div>   
     </c:if>
     
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
    /* When the user clicks on the button, 
    toggle between hiding and showing the dropdown content */
    function myFunction() {
        document.getElementById("sort_by").classList.toggle("show");
    }
    
    // Close the dropdown if the user clicks outside of it
    window.onclick = function(event) {
        if (!event.target.matches('.sort_by')) {
        var dropdowns = document.getElementsByClassName("sort_by-content");
        var i;
        for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
            openDropdown.classList.remove('show');
            }
        }
        }
    }
    function openNav() {
        document.getElementById("mySidepanel").style.display = "block";
    }

    function closeNav() {
        document.getElementById("mySidepanel").style.display = "none";
    }
	
    window.location.reload() = "product.jsp";
    </script>
    
</html>