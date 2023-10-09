<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Details</title>
    <link rel="stylesheet" href="style.css">
<script src="https://kit.fontawesome.com/19e7b5d5c2.js" crossorigin="anonymous"></script>
</head>
<body>

    <div class="container">
        <div class="form-box">
            <h1 id="title">Log In</h1>
             <form id="login" action = "register" method="post" >
                <div class="input-group">
                   
                    <div class="input-field">
                        <i class="fa-solid fa-envelope"></i>
                        <input type="email" placeholder="E-mail" name="email" required>
                    </div>
                    <div class="input-field" id="pass5">
                        <i class="fa-solid fa-lock"></i>
                        <input type="password" placeholder="Password" name="password" required>
                    </div>
                   
                    <p id="message"></p>
                    <div id="Forget">
                        <p><a href="#" id="fbtn">Forget Password?</a></p>
                    </div>
                </div>
  <% String lstatus = (String) request.getAttribute("lstatus"); %>

  
<% if ("success".equals(lstatus)) { %>
    <div id="lsmessage" class="success-message">
        Login Successful! Redirecting To Dashboard...<!-- Modify this message as needed -->
    </div>
<% } else if ("failed".equals(lstatus)) { %>
    <div id="lsmessage" class="error-message">
        Login Failed! Please try again. <!-- Modify this message as needed -->
    </div>
<% } %>
<script>
    setTimeout(function () {
        var messageElement = document.getElementById("lsmessage");
        if (messageElement) {
            messageElement.style.display = "none";
        }
    }, 1500);
</script>
                
<% String status = (String) request.getAttribute("status"); %>
<% if ("success".equals(status)) { %>
    <div id="smessage" class="success-message">
        Registration Successful! <br> Proceed To Login <!-- Modify this message as needed -->
    </div>
<% } else if ("failed".equals(status)) { %>
    <div id="smessage" class="error-message">
        Registration Failed! Please try again. <!-- Modify this message as needed -->
    </div>
<% } %>
<script>
    setTimeout(function () {
        var messageElement = document.getElementById("smessage");
        if (messageElement) {
            messageElement.style.display = "none";
        }
    }, 1000);
</script>

                <% String rstatus = (String) request.getAttribute("statuss"); %>
            <% if ("success".equals(rstatus)) { %>
                <div id="rmessage" class="success-message">
                    Password Reset Successfully! <!-- Modify this message as needed -->
                </div>
            <% } else if ("failed".equals(rstatus)) { %>
                <div id="rmessage" class="error-message">
                    Password Reset Failed! Please try again. <!-- Modify this message as needed -->
                </div>
            <% } %>

            <script>
                setTimeout(function () {
                    var messageElement = document.getElementById("rmessage");
                    if (messageElement) {
                        messageElement.style.display = "none";
                    }
                }, 1000);
            </script>
            
            </form>
            
            
            <form style="display:none" id="main" action = "loginservlet" method="post" >
                    <div class="input-field" id="Nfield">
                        <i class="fa-solid fa-user"></i>
                        <input type="text" placeholder="Name" name="name" required>
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-envelope"></i>
                        <input type="email" placeholder="E-mail" name="email" required>
                    </div>
                   <div class="input-field" >
    <i class="fa-solid fa-lock"></i>
    <input type="password" placeholder="Password" id="pass1" name="password" required>
</div>
<div class="input-field" >
    <i class="fa-solid fa-lock"></i>
    <input type="password" id="pass2" placeholder="Confirm Password" oninput="checkPasswordMatch('passwordMatchIcon1', 'pass1', 'pass2')" required>
    <i id="passwordMatchIcon1" class="fa-regular fa-circle-check"></i>
</div>
</form>
            
            <div class="btn-field">
             <button type="submit" id="Ibtn" class="disable" >Log In</button>
              <button type="submit" id="Ubtn"  >Register</button>  
            </div>
            
           <div style="display: none;" class="form-box" id="forgetp">
            <h1 id="title">Reset Password</h1>
            <form action="reset" method="post" id="resetform">
                <div class="input-group">
                <div class="input-field">
                        <i class="fa-solid fa-envelope"></i>
                        <input type="number" placeholder="Teacher-ID" name="teacherId" required>
                    </div>
                    <div class="input-field">
                        <i class="fa-solid fa-envelope"></i>
                        <input type="email" placeholder="E-mail" name="email" required>
                    </div>
                    <div class="input-field" >
                        <i class="fa-solid fa-lock"></i>
                        <input type="password" id="pass3" placeholder="New Password" name="newPassword" required>
                    </div>
                    <div class="input-field" >
                        <i class="fa-solid fa-lock"></i>
                        <input type="password" id="pass4" placeholder="Confirm New Password" oninput="checkPasswordMatch('passwordMatchIcon2', 'pass3', 'pass4')" required>
                        <i id="passwordMatchIcon2" class="fa-regular fa-circle-check"></i>
                   </div>
                </div>
                <div class="btn-field">
                    <button type="submit" id="fbtn" >Save</button>
                </div>
            </form>
        </div>
        </div>
    </div>
    <script>
        let Ubtn = document.getElementById("Ubtn");
        let Ibtn = document.getElementById("Ibtn");
        let fbtn = document.getElementById("fbtn");
        let title = document.getElementById("title");
        let Forget = document.getElementById("Forget");
        let forgetp = document.getElementById("forgetp");
        let register = document.getElementById("main");
        let login = document.getElementById("login");
        let passwordMatchIcon1 = document.getElementById("passwordMatchIcon1");
        let passwordMatchIcon2 = document.getElementById("passwordMatchIcon2");
       
        Ibtn.onclick = function () {
            if (title.innerHTML === "Register") {
              register.style.display = "none";
              login.style.display = "block";
                Forget.style.maxHeight = "13px";
                forgetp.style.display = 'none';
                title.innerHTML = "Log In";
                Ubtn.classList.remove("disable");
                Ibtn.classList.add("disable");
         
            } else {
            	if (login.checkValidity()) {
                    login.submit(); 
                } else {
                    alert("Please fill in all required fields with valid data.");
                }
            }
        }

        Ubtn.onclick = function () {
            if (title.innerHTML === "Log In") {
            	   register.style.display = "block";
                   login.style.display = "none";
                Forget.style.maxHeight = "0";
                forgetp.style.display = 'none';
                title.innerHTML = "Register";
                Ubtn.classList.add("disable");
                Ibtn.classList.remove("disable");
                
             
            } else {
            	if (register.checkValidity()) {
                    register.submit();
                    Forget.style.maxHeight ="13px"
                } else {
                    alert("Please fill in all required fields with valid data.");
                }
            }
        }

        fbtn.onclick = function () {
            Forget.style.maxHeight = "0";
            login.style.display = "none";
            forgetp.style.display = "block";
          
        }

        function checkPasswordMatch(iconId, passId1, passId2) {

            let password1 = document.getElementById(passId1).value;
            let password2 = document.getElementById(passId2).value;
            let passwordMatchIcon = document.getElementById(iconId);
            console.log(password1);

            if (password1 == password2) {
                passwordMatchIcon.className = "fa-solid fa-circle-check";
                passwordMatchIcon.style.color = "#63e91c";
            } else {
                passwordMatchIcon.className = "fa-solid fa-circle-xmark";
                passwordMatchIcon.style.color = "red";
            }
        }
        
        
    </script>
  
</body>
</html>
