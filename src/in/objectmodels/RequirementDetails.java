package in.objectmodels;

/**
 * Created by deepaksood619 on 2/6/16.
 */
public class RequirementDetails {
    private String subject;
    private int numOfTa;

    public RequirementDetails(String subject, int numOfTa) {
        this.subject = subject;
        this.numOfTa = numOfTa;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getNumOfTa() {
        return numOfTa;
    }

    public void setNumOfTa(int numOfTa) {
        this.numOfTa = numOfTa;
    }
}
