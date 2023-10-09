package project;

public class Student {
    private int rollNo;
    private String studentName;
    private String fatherName;
    private String status;

    public Student(int rollNo, String studentName, String fatherName, String status) {
        this.rollNo = rollNo;
        this.studentName = studentName;
        this.fatherName = fatherName;
        this.status = status;
    }

    public Student() {
		// TODO Auto-generated constructor stub
	}

	// Getter and setter methods for the fields
    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
