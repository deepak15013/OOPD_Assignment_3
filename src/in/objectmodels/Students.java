package in.objectmodels;

/**
 * Created by deepaksood619 on 2/6/16.
 */
public class Students {
    private String studentName;
    private String rollName;
    private Float cgpa;
    private String program;
    private String first_preference;

    public Students(String studentName, String rollName, Float cgpa, String program, String first_preference) {
        this.studentName = studentName;
        this.rollName = rollName;
        this.cgpa = cgpa;
        this.program = program;
        this.first_preference = first_preference;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollName() {
        return rollName;
    }

    public void setRollName(String rollName) {
        this.rollName = rollName;
    }

    public Float getCgpa() {
        return cgpa;
    }

    public void setCgpa(Float cgpa) {
        this.cgpa = cgpa;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getFirst_preference() {
        return first_preference;
    }

    public void setFirst_preference(String first_preference) {
        this.first_preference = first_preference;
    }
}
