# Teachersdairy

A web-based application designed for teachers to manage attendance, assignments, timetables, student progress, and login/registration with secure authentication.
Built using Java Servlets, JSP, HTML, CSS, JavaScript, and MySQL, this project demonstrates a full-stack Java web application deployed on Apache Tomcat.

🚀 Features:

 • User Authentication
 
   - Registration, Login, Logout with validations
  
   - Password reset with validation checks (uppercase, lowercase, number, special character, minimum length)

 • Attendance Management
 
   - Save and download attendance records

 • Assignment & Progress Tracking
 
   - Upload assignments
   
   - Track subject progress
   
   - View assignment details

 • Timetable Management
 
   - Upload timetable
   
   - Fetch timetable dynamically

 • File Uploads
 
   - Multipart file handling with size restrictions

• Responsive UI

   - Built with HTML, CSS (Flexbox, Media Queries)
   
   - Password strength checker with live feedback
   
   - Loading spinner overlay until the page fully loads

 • CORS Support
 
   - Configured via CorsFilter in web.xml


🛠️ Tech Stack

- Frontend: HTML, CSS, JavaScript

- Backend: Java, Servlets, JSP

- Database: MySQL

- Server: Apache Tomcat

- Build Tool/IDE: Eclipse IDE


🔒 Security Features

- Password strength enforcement:

  - Minimum 8 characters
  
  - At least one uppercase, one lowercase, one digit, and one special character
  
• Session management (clearing local/session storage on load)

• Input validations for all forms
