//attendence
const currentDate = document.querySelector(".current-date"),
            daysTag = document.querySelector(".days"),
            prevNextIcon = document.querySelectorAll(".icons span");

        // Getting new date, current year, and month
        let date = new Date(),
            currYear = date.getFullYear(),
            currMonth = date.getMonth();

        const publicHolidays = ["2023-01-01", "2023-01-26", "2023-02-5", "2023-02-18", "2023-03-08", "2023-03-30", "2023-04-04", "2023-04-07", "2023-04-14", "2023-04-22", "2023-05-05", "2023-06-29", "2023-07-29", "2023-08-15", "2023-08-31", "2023-09-07", "2023-09-28", "2023-10-02", "2023-10-23", "2023-10-24", "2023-11-12", "2023-11-13", "2023-11-15", "2023-11-27", "2023-12-25"];

        const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];

        const renderCalendar = () => {
            let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(), // Getting the first day of the month
                lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(), // Getting the last date of the month
                lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(), // Getting the last day of the month
                lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate(); // Getting the last day of the previous month
            let liTag = "";

            for (let i = firstDayofMonth; i > 0; i--) {
                liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
            }

            for (let i = 1; i <= lastDateofMonth; i++) {
                let isToday =
                    i === date.getDate() &&
                        currMonth === new Date().getMonth() &&
                        currYear === new Date().getFullYear()
                        ? "active"
                        : "";
                let isHoliday = publicHolidays.includes(
                    `${currYear}-${String(currMonth + 1).padStart(2, "0")}-${String(i).padStart(2, "0")}`
                )
                    ? "holiday"
                    : "";
                liTag += `<li class="${isToday} ${isHoliday}" onclick="showDatePopup('${months[currMonth]} ${i}, ${currYear}')">${i}</li>`;
            }

            for (let i = lastDayofMonth; i < 6; i++) {
                liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`;
            }

            currentDate.innerText = `${months[currMonth]} ${currYear}`;
            daysTag.innerHTML = liTag;
        };

        // Function to handle the day click and show the fullscreen pop-up modal
        function showDatePopup(selectedDate) {
            const modal = document.getElementById("popupModal");
            const selectedDateElement = document.getElementById("selectedDate");
            selectedDateElement.textContent = selectedDate;
            modal.style.display = "block";

            const closeModal = document.getElementById("closeModal");
            closeModal.onclick = function () {
                modal.style.display = "none";
            }

            window.onclick = function (event) {
                if (event.target === modal) {
                    modal.style.display = "none";
                }
            }
        }

        renderCalendar();

        prevNextIcon.forEach((icon) => {
            icon.addEventListener("click", () => {
                currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;

                if (currMonth < 0 || currMonth > 11) {
                    date = new Date(currYear, currMonth);
                    currYear = date.getFullYear();
                    currMonth = date.getMonth();
                } else {
                    date = new Date();
                }
                renderCalendar();
            });
        });


// Progress code 1st 
let progresscontainer1st = document.querySelector(".outercircle1st");
    let value1st = document.querySelector(".innercircle1st");
 
    let progressvalue1st = 0;
    let progressendvalue1st = 25;

    let progress1st = setInterval(() => {
        progressvalue1st++ ;
value1st.innerHTML = `${progressvalue1st}%`;
progresscontainer1st.style.background = `conic-gradient( #880bea ${progressvalue1st * 3.6}deg, #ededee 0deg)`;
        if (progressvalue1st == progressendvalue1st) {
            clearInterval(progress1st);
        }
    }, 20 );

    //Progression code 2nd
    let progresscontainer2nd = document.querySelector(".outercircle2nd");
    let value2nd = document.querySelector(".innercircle2nd");
 
    let progressendvalue2nd = 50;
    let progressvalue2nd = 0;


    let progress2nd = setInterval(() => {
        progressvalue2nd++ ;
value2nd.innerHTML = `${progressvalue2nd}%`;
progresscontainer2nd.style.background = `conic-gradient( #880bea ${progressvalue2nd * 3.6}deg, #ededee 0deg)`;
        if (progressvalue2nd == progressendvalue2nd) {
            clearInterval(progress2nd);
        }
    }, 20 );

    //Progression code 3rd
    let progresscontainer3rd = document.querySelector(".outercircle3rd");
    let value3rd = document.querySelector(".innercircle3rd");
 
    let progressendvalue3rd = 75;
    let progressvalue3rd = 0;


    let progress3rd = setInterval(() => {
        progressvalue3rd++ ;
value3rd.innerHTML = `${progressvalue3rd}%`;
progresscontainer3rd.style.background = `conic-gradient( #880bea ${progressvalue3rd * 3.6}deg, #ededee 0deg)`;
        if (progressvalue3rd == progressendvalue3rd) {
            clearInterval(progress3rd);
        }
    }, 20 );

// Get references to the select elements
const yearSelect = document.getElementById('yearSelect');
const semesterSelect = document.getElementById('semesterSelect');
const subjectSelect = document.getElementById('subjectSelect');

// Define options for semesters and subjects    
const semesterOptions = {
  '1st': ['1st Semester', '2nd Semester'],
  '2nd': ['3rd Semester', '4th Semester'],
  '3rd': ['5th Semester', '6th Semester'],
};

// Function to populate options for semester based on the selected year
function populateSemesterOptions() {
  const selectedYear = yearSelect.value;
  const semesters = semesterOptions[selectedYear];

  // Clear existing options
  semesterSelect.innerHTML = '';

  // Add new options
  semesters.forEach((semester) => {
    const option = document.createElement('option');
    option.text = semester;
    semesterSelect.add(option);
  });

  // Call the function to populate subject options based on the selected semester
  populateSubjectOptions();
}

function populateSubjectOptions() {
  const selectedYear = yearSelect.value;

  // Clear existing options
  subjectSelect.innerHTML = '';

  // Make an AJAX request to retrieve subject data based on the selected year
  fetch(`/Teachersdairy/Subject`) // Update the URL to match your servlet's URL
    .then(response => response.text()) // Parse the response as text
    .then(data => {
      // Parse the response data as JSON
      const subjectsArray = JSON.parse(data);

      // Process the retrieved data
      subjectsArray.forEach(subject => {
        const option = document.createElement('option');
        option.text = subject;
        subjectSelect.add(option);
      });
    })
    .catch(error => {
      console.error('Error:', error);
    });
}


// Event listener to update options when the year is changed
yearSelect.addEventListener('change', populateSemesterOptions);

// Initial population of subject options
populateSemesterOptions();


 // Function to save the timetable data to local storage
 function saveTimetableToLocal() {
    const timetableData = [];

    // Loop through each table cell and store its content and data-key attribute
    const cells = document.querySelectorAll('[contenteditable=true][data-key]');
    cells.forEach((cell) => {
      const key = cell.getAttribute('data-key');
      const content = cell.textContent;
      timetableData.push({ key, content });
    });

    // Store the timetable data in local storage as a JSON string
    localStorage.setItem('timetableData', JSON.stringify(timetableData));
  }

  // Function to load the timetable data from local storage
  function loadTimetableFromLocal() {
    // Retrieve the timetable data from local storage
    const storedData = localStorage.getItem('timetableData');

    if (storedData) {
      const timetableData = JSON.parse(storedData);

      // Loop through the stored data and update the corresponding table cells
      timetableData.forEach((item) => {
        const cell = document.querySelector(`[data-key="${item.key}"]`);
        if (cell) {
          cell.textContent = item.content;
        }
      });
    }
  }

  // Add an event listener to the timetable for changes
  const timetable = document.querySelector('table');
  timetable.addEventListener('input', saveTimetableToLocal);

  // Load the timetable data from local storage when the page loads
  window.addEventListener('load', loadTimetableFromLocal);
  
  
  
 
 // ...

// Event listener to load attendance data
document.addEventListener('DOMContentLoaded', function () {
	const selectedDate = document.getElementById('selectedDate').textContent;
    const selectedYear = document.getElementById('yearSelect').value;

    const selectedSemester = document.getElementById('semesterSelect').value;

    // Fetch student data from the server and populate the table
fetch(`/Teachersdairy/FetchStudentDataServlet?year=${selectedYear}`)
        .then(response => response.json())
        .then(data => {
			    console.log(data); // Add this line
			    console.log("hello"+selectedDate+"_"+selectedSemester);


            const tableBody = document.querySelector('#attendanceTable tbody');
            data.forEach(studentData => {
                const newRow = document.createElement('tr');
                newRow.innerHTML = `
                    <td>${studentData.rollNo}</td>
                    <td>${studentData.studentName}</td>
                    <td>${studentData.fatherName}</td>
                    <td>
                        <input type="radio" name="status_${studentData.rollNo}" value="A" checked> A
                        <input type="radio" name="status_${studentData.rollNo}" value="P"> P
                    </td>
                `;
                tableBody.appendChild(newRow);
	             
            });
        })
        .catch(error => {
            console.error('Error:', error);
  });
// Event listener to save attendance data
document.getElementById('saveAttendanceButton').addEventListener('click', saveAttendance);

function saveAttendance() {
    // Get selected date, year, semester, and subject
    const selectedDate = document.getElementById('selectedDate').textContent;
    const selectedYear = document.getElementById('yearSelect').value;
    const selectedSubject = document.getElementById('subjectSelect').value;
    			    console.log("hello"+selectedDate+"_"+selectedSemester);


    // Create an array to store attendance data
    const attendanceData = [];

    // Iterate through the table rows to collect data
    const tableRows = document.querySelectorAll('#attendanceTable tbody tr');
    console.log('Number of table rows:', tableRows.length);

    tableRows.forEach(row => {
		    console.log('Inside loop'); // Add this line

        const rollNo = row.querySelector('td:nth-child(1)').textContent;
const status = row.querySelector('input[type="radio"]:checked').value;
const Studentname = row.querySelector('td:nth-child(2)').textContent;
const Fathername = row.querySelector('td:nth-child(3)').textContent;

console.log("sac");

        attendanceData.push({
            rollNo,
            status,
            Studentname,Fathername
        });
    });
    // Send the attendance data to the server for saving
    const postData = {
        date: selectedDate,
        year: selectedYear,
        semester: selectedSemester,
        subject: selectedSubject,
        attendanceData
    };

    // Make an AJAX request to save attendance data
    fetch(`/Teachersdairy/SaveAttendanceServlet`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(postData),
    })
    .then(response => {
        if (response.ok) {
            alert('Attendance saved successfully.');
        } else {
            alert('Failed to save attendance.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}

 document.getElementById('viewAttendanceButton').addEventListener('click', function () {
        const dateRangeModal = document.getElementById('dateRangeModal');
        dateRangeModal.style.display = 'block';
    });

    // Close the date range modal when the close button is clicked
    document.getElementById('closeDateRangeModal').addEventListener('click', function () {
        const dateRangeModal = document.getElementById('dateRangeModal');
        dateRangeModal.style.display = 'none';
    });

    // View attendance data within the specified date range
    document.getElementById('viewButton').addEventListener('click', function () {
        const fromDate = document.getElementById('fromDate').value;

        // Make an AJAX request to retrieve attendance data within the date range
        fetch(`/Teachersdairy/FetchAttendanceByDateRangeServlet?fromDate=` + fromDate) // Update the URL to match your servlet's URL
            .then(response => response.json())
            .then(data => {
                // Process the retrieved data and display it
                console.log(data); // Log data to the console for testing
                const tableBody = document.querySelector('#attendanceTable tbody');
            data.forEach(studentData => {
                const newRow = document.createElement('tr');
                newRow.innerHTML = `
                    <td>${studentData.rollNo}</td>
                    <td>${studentData.studentName}</td>
                    <td>${studentData.fatherName}</td>
                    <td>
                        <input type="radio" name="status_${studentData.rollNo}" value="A" checked> A
                        <input type="radio" name="status_${studentData.rollNo}" value="P"> P
                    </td>
                `;
                tableBody.appendChild(newRow);
	             
            }); // Update your HTML to display the data as needed
            })
            .catch(error => {
                console.error('Error:', error);
            });
    });
});