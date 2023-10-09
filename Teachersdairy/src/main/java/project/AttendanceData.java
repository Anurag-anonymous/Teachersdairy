package project;

import java.util.List;

public class AttendanceData {
    private String date;
    public String year;
    private String semester;
    private String subject;
    private List<Student> attendanceData;

    // Getter and setter methods for all fields

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Student> getAttendanceData() {
        return attendanceData;
    }

    public void setAttendanceData(List<Student> attendanceData) {
        this.attendanceData = attendanceData;
    }
}
