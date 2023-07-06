<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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

    <meta name="keywords" content="HTML, CSS, JavaScript">

    <meta name="author" content="Ashutosh Pradhan">

    <meta name="description" content="My Blog Page with a few articles">

    <title>Document</title>
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
<!--
    internal css for social media buttons in the footer
-->
<style>    
/* styling for the body tag*/
body{
    margin: 0px;
}

.navbar{
	display: grid;

    grid-template-columns: repeat(2,50%);
    
    justify-content: center;
	align-items: center;
}


img{
	width: 100%;
	object-fit: cover
}

.logo{
	height: 10em;
	width: 30em;
}

.logo:hover{
	cursor: pointer;
}

.menu{
	list-style: none;
	display: grid;
    grid-template-columns:repeat(6,auto);
	grid-template-rows: auto;
}

a{
	text-decoration: none;
	color: black;
}

a:hover{
	text-decoration: none;

	text-decoration: underline;
}
.blog_Content{
    background-color: rgb(248, 247, 247);
    margin: 20px;
    width: 75%;
    float: left;
}
/* styling for the about me section*/
.about_me{
    background-color: rgb(248, 247, 247);
    width: 20%;
    margin-top: 20px;
    float: left;
}   
/* styling concerned for JS*/
.more {
    display: none;
}
/* styling for the button*/
.myBtn{
    background-color: white;
    outline-style: none;
    border: none;
    padding: 10px;
}
/* styling for the like Button*/
#like_button{
    background-color: black;
    color: white;
    padding: 10px;
}
/* styling for the image holder*/
.image_container{
    width: 100%;
    height: 100%;
    text-align: right;
    
/* styling for the button on hover*/
}

.image_container img{
    width: 100%;
    height: 600px
    
/* styling for the button on hover*/
}

.container_text{

}

.seperator {
	margin-top: 37px;
	margin-bottom: 0px;
}

.footer {
	display: flex; 
	flex-wrap: wrap; 
	flex-direction: row; 
	align-items: center; 
	justify-content: center; 
	padding-bottom: 20px;
}

.firstSection{
	display: flex; width: 40%; 
	height: auto;
	color: grey; 
	text-align: center;
}


.firstSection img{
	margin-bottom: 0px;
}

.social-icons a{
	background-color:#eceeef;
	color:#818a91;
	font-size:16px;
	display:inline-block;
	line-height:44px;
	width:44px;
	height:44px;
	text-align:center;
	margin-right:8px;
	border-radius:100%;
	-webkit-transition:all .2s linear;
	-o-transition:all .2s linear;
	transition:all .2s linear;
}
.facebook:hover{
	background-color:#007bb6
}
.twitter:hover{
	background-color:#00aced
}
.instagram:hover{
	background-color:#f7005f
}
</style>

<!--
   import css for social media icons 
-->  
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!--
    import external css 
--> 
<link rel="stylesheet" type="text/css" href="../CSS/blog.css">

<body>

    <nav class = navbar>
        <div>
            <img src = "${pageContext.request.contextPath}/images/LOGO.png" alt = "logo.png" class = "logo" onclick = "refresh()">
        </div>

        <div class="menu">
            <a href = "${pageContext.request.contextPath}/pages/home.jsp">HOME</a>
            
            <a href = "${pageContext.request.contextPath}/pages/product.jsp">PRODUCTS</a>
            
            <a href = "${pageContext.request.contextPath}/pages/about.jsp" style="color: red;">ABOUT</a>
            
            <a href = "${pageContext.request.contextPath}/pages/contact.jsp">CONTACT</a>
            
            <div style="display:${isAdmin ? 'none' : 'block'}">
	            <a href = "${pageContext.request.contextPath}/pages/profile.jsp">PROFILE</a>
			</div>
			            
            <div style="display:${isAdmin ? 'block' : 'none'}">
	            <a href ="${pageContext.request.contextPath}/pages/admin.jsp" >ADMIN</a>
            </div>
            
            <a href = "${pageContext.request.contextPath}/pages/cart.jsp" class="cart"><i class="fa fa-shopping-cart"></i></a>



        </div>
        
    </nav>

    <!--
       Article on Impact Of Technology In Your Everyday Life. 
    -->
        <div class="image_container" id="image_container" >
            <img id="firstImage" src="${pageContext.request.contextPath}/images/clothes.png" width="90%" height="400px"> 
        </div> 
        <aside>
            <div class="container_text" id="container_text">
                <div id="invisible_text">
                    <br>
                    <h2 style="font-weight:bolder;" class="margin_all"> Learn more about us </h2>
                    <span style="font-size:xx-small" class="margin_all">
                        May 4, 2023.Ashutosh.
                    </span> 
                </div>
        
                <p style="color: rgb(56, 56, 56);" >
                <br>
                Welcome to Empyrean, Nepal's premier destination for fashion enthusiasts seeking the latest trends and timeless style. Located in the heart of Nepal, our clothing store is dedicated to providing a truly exceptional shopping experience that combines high-quality garments, personalized service, and a passion for fashion 

                        At Empyrean, we believe that clothing is not just about covering oneself, but a form of self-expression. Our carefully curated collection showcases a diverse range of apparel for men and women, sourced from renowned local and international designers. From casual wear to formal attire, we strive to cater to every individual's unique taste and fashion sensibilities.
                        <br><br>
                        What sets Empyrean apart is our commitment to quality. We meticulously handpick each item in our store, ensuring that only the finest fabrics, craftsmanship, and attention to detail make their way to our shelves. We prioritize comfort, durability, and style, offering garments that are designed to make you look and feel your best.
                        <br><br>
                        Our knowledgeable and friendly staff are fashion enthusiasts themselves, always ready to provide personalized assistance and styling advice. Whether you're looking for a complete wardrobe makeover or simply seeking a single statement piece, we're here to guide you in discovering the perfect ensemble for any occasion.
                        <br><br>
                        Beyond our commitment to fashion, Empyrean is deeply rooted in our local community. We collaborate with local artisans and designers, supporting their talent and promoting the rich heritage of Nepalese craftsmanship. By showcasing their creations, we strive to foster a sense of pride and appreciation for the creative talents that thrive within our nation.
                        <br><br>
                        Furthermore, Empyrean embraces sustainability. We actively seek out brands that prioritize ethical practices and eco-friendly materials, ensuring that our customers can make conscious fashion choices without compromising on style.
                        <br><br>
                        We invite you to visit Empyrean and immerse yourself in a world of fashion excellence. Step inside our elegantly designed store, where you will find an inviting atmosphere, thoughtfully arranged displays, and a team dedicated to providing an unforgettable shopping experience.
                        <br><br>
                        Thank you for choosing Empyrean as your trusted destination for fashion in Nepal. Join us on this exciting journey of style, self-expression, and sartorial discovery.
        
                        Empyrean - Elevate Your Style
                </p>
                <!--
                    Like and Read More Buttons
                -->

                
            </div>
        </aside>  
   
        <hr width="100%" class="seperator" align="center">

    <!--
        Footer Section with Social media Icons
    -->
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


</html>