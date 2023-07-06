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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/signin.css">
</head>

<script>
    var loadFile = function(event) {
      var output = document.getElementById('profilePicture');
      output.src = URL.createObjectURL(event.target.files[0]);
      output.onload = function() {
        URL.revokeObjectURL(profilePicture.src) // free memory
      }
    };
  </script>   
  
<body>
	<div class="container">
        <div class="child">
            <form action="${pageContext.request.contextPath}/signInServlet" method="post" enctype="multipart/form-data">  
                <div style="display: flex; flex-direction: column;align-items: center;margin-bottom: 35px; margin-top: -15px;">
                    <input type='file' onchange="loadFile(event)" name="userImg" id="selectedFile" style="display: none;" required>      
                    <img id="profilePicture" name="profilePic"src="${pageContext.request.contextPath}/images/img.jpg" alt="your image" onclick="document.getElementById('selectedFile').click();"/>
               	</div>
                <div class="formChild">
                        
                    <div>
                        <label for="userFName">First Name: </label>
                        <input type="text" name="userFName" required>
                    </div>
    
                    <div>
                        <label for="userLName">Last Name: </label>
                        <input type="text" name="userLName" required>
                    </div>
                    
                    <div>
                        <label for="userEmail">Email: </label>
                        <input type="email" name="userEmail" required>  
                    </div>
                    
                    <div>
                        <label for="userPassword">Password: </label>
                        <input type="password" name="userPassword" required>
                    </div>
                   
                    <div>
                        <label for="userPhone">Contact No: </label>
                        <input type="tel" name="userPhone" pattern="[0-9]{10}" maxlength="10" required>
                    </div>
                    
                    <div>
                        <label for="userAddress">Address: </label>
                        <input type="text" name="userAddress" required>
                    </div>
                    <div>
                        <label for="ccn">Credit Card Number:</label>
            			<input type="tel" name="userCCN" minlength="19" autocomplete="cc-number" maxlength="19" placeholder="xxxx-xxxx-xxxx-xxxx" required>
                    </div>
                    
                    <div>
                        <label for="userBirth">BirthDate:</label>

                        <input type="date"  value="Birtday" name="userBirth" required>
                    </div>

                    <div style="margin-top: 9px;">
                        <input type="submit" value="Sign Up" class="Sign">
                    </div>
                    <span>Already have an account? <a href="${pageContext.request.contextPath}/pages/login.jsp" class="Login">Login!</a></span>

                </div>
                
            </form>
        </div>
	</div>
</body>
</html>