package in.objectmodels;

/**
 * Created by deepaksood619 on 2/6/16.
 */
public class FinalAllotment {
    private String studentName;
    private String subjectAlloted;

    public FinalAllotment(String studentName, String subjectAlloted) {
        this.studentName = studentName;
        this.subjectAlloted = subjectAlloted;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubjectAlloted() {
        return subjectAlloted;
    }

    public void setSubjectAlloted(String subjectAlloted) {
        this.subjectAlloted = subjectAlloted;
    }
}
