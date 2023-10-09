<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style2.css">
    <link rel="stylesheet" 
        href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200">
</head>
<body>
    <div class="container">
    <div class="plate">
        <div class="header">
          <div id="image"><img src="eduka.png">  </div>
          <div class="first">        
            <h1>Teacher's Dairy</h1>
        
    
          
    </div>
    <div class="lastside">
        <div class="fifth">
            <h2>Profile</h2>
           <span  class="material-symbols-outlined">
                account_circle
        </span>
        <div  class="details">
            <% String name = (String) request.getAttribute("name"); %>
    <% String email = (String) request.getAttribute("email"); %>
    <% String teacherid = (String) request.getAttribute("teacherId"); %>
    
   <h3>User Name: <%= name %></h3>
   <h3>Email Id: <%= email %></h3>
   <h3>Teacher's Id: <%= teacherid%> </h3>

   </div>
      <a href="login.jsp"><button id="logout">Log Out</button></a>
          </div>
          <div class="event">
            <h2> Events </h2>

          </div>
    </div>
    
        </div>
<div class="side-container">
  
  <div class="second">
    <h2>Progress</h2>
    
      <div class="outercircle1st"  id="outercircle">
          <div class="innercircle1st"  id="innercircle">
  0%
          </div>
      </div>
      <div class="outercircle2nd" id="outercircle" >
        <div class="innercircle2nd"  id="innercircle">
0%
        </div>
    </div>
    <div class="outercircle3rd"  id="outercircle" >
      <div class="innercircle3rd"  id="innercircle">
0%
      </div>
  </div>

    
</div>

<div class="vertical">
  <div class="third">
    <h2>Time Table</h2>
    <table>
      <thead>
          <tr>
              <th>Day/Period</th>
              <th class="period"> I </th>
              <th>II</th>
              <th>III</th>
              <th>IV</th>
              <th></th>
              <th>V</th>
              <th>VI</th>
              <th>VII</th>
              <th>VIII</th>
          </tr>
      </thead>

      <tbody>
          <tr>
              <td>Monday</td>
              <td colspan="2"  contenteditable="true" data-key="M1"></td>
              <td colspan="2"contenteditable="true" data-key="M2"></td>
              <td rowspan="6" class="lunch"> LUNCH </td>
              <td colspan="2"  contenteditable="true" data-key="M3"></td>
              <td colspan="2"  contenteditable="true" data-key="M4"></td>
          </tr>
          <tr>
              <td>Tuesday</td>
              <td colspan="2"   contenteditable="true" data-key="T1"></td>
              <td colspan="2"   contenteditable="true" data-key="T2"></td>
              <td colspan="2"   contenteditable="true" data-key="T3"></td>
              <td colspan="2"   contenteditable="true" data-key="T4"></td>
          </tr>
          <tr>
              <td>Wednesday</td>
              <td colspan="2"   contenteditable="true" data-key="W1"></td>
              <td colspan="2"   contenteditable="true" data-key="W2"></td>
              <td colspan="2"   contenteditable="true" data-key="W3"></td>
              <td colspan="2"   contenteditable="true" data-key="W4"></td>
          </tr>
          <tr>
              <td>Thrusday</td>
              <td colspan="2"   contenteditable="true" data-key="Th1"></td>
              <td colspan="2"   contenteditable="true" data-key="Th2"></td>
              <td colspan="2"   contenteditable="true" data-key="Th3"></td>
              <td colspan="2"   contenteditable="true" data-key="Th4"></td>
          </tr>
          <tr>
              <td>Friday</td>
              <td colspan="2"   contenteditable="true" data-key="F1"></td>
              <td colspan="2"   contenteditable="true" data-key="F2"></td>
              <td colspan="2"   contenteditable="true" data-key="F3"></td>
              <td colspan="2"   contenteditable="true" data-key="F4"></td>
          </tr>
          <tr>
              <td>Saturday</td>
              <td colspan="2"   contenteditable="true" data-key="S1"></td>
              <td colspan="2"   contenteditable="true" data-key="S2"></td>
              <td colspan="2"   contenteditable="true" data-key="S3"></td>
              <td colspan="2"   contenteditable="true" data-key="S4"></td>
          </tr>
      </tbody>
  </table>

  </div>
  <div class="forth">
    <h2>Attendance</h2>
     <!--calender-->
   
      <section class="attendance-calendar">
        <div class="wrapper">
            <header>
                <p class="current-date">September 2023</p>
                <div class="icons">
                    <span id="prev" class="material-symbols-outlined">chevron_left</span>
                    <span id="next" class="material-symbols-outlined">chevron_right</span>
                </div>
            </header>
            <div class="calendar">
                <ul class="weeks">
                    <li>Sun</li>
                    <li>Mon</li>
                    <li>Tue</li>
                    <li>Wed</li>
                    <li>Thu</li>
                    <li>Fri</li>
                    <li>Sat</li>
                </ul>
                <ul class="days"></ul>
            </div>
        </div>
    
        <!-- Pop-up modal -->
        <div class="modal" id="popupModal">
    <div class="modal-content">
        <span id="closeModal" style="float: right; cursor: pointer;">&times;</span>
        <p id="selectedDate"></p>
        <select id="yearSelect">
            <option value="1st">1st Year</option>
            <option value="2nd">2nd Year</option>
            <option value="3rd">3rd Year</option>
        </select>
        <select id="semesterSelect">
            <!-- Options for semester will be added dynamically using JavaScript -->
        </select>
        <select id="subjectSelect">
            <!-- Options will be populated dynamically by JavaScript -->
        </select>
           <table id="attendanceTable">
        <thead>
            <tr>
                <th>Roll No.</th>
                <th>Student Name</th>
                <th>Father Name</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <!-- Student data rows will be populated dynamically -->
        </tbody>
    </table>
    
    <!-- Submit button to save attendance -->
    <button id="saveAttendanceButton">Save</button>

    <!-- View Attendance button to view tables within a date range -->
    <button id="viewAttendanceButton">View Attendance</button>

    <!-- Modal for entering date range -->
    <div class="modal" id="dateRangeModal">
        <div class="modal-content">
            <span id="closeDateRangeModal" style="float: right; cursor: pointer;">&times;</span>
            <h2>Enter Date Range</h2>
            <label for="fromDate">From:</label>
            <input type="date" id="fromDate">
            <label for="toDate">To:</label>
            <input type="date" id="toDate">
            <button id="viewButton">View</button>
        </div>
    </div>
</div>
</div>



      </section>

</div>

  </div>
 
    </div>
    </div>
       </div>
           
       
                     <script type="text/javascript" src="script.js">
                     </script>

   </body>
   </html>
   