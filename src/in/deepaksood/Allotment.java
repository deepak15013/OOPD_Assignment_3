package in.deepaksood;

import in.objectmodels.FinalAllotment;
import in.objectmodels.RequirementDetails;
import in.objectmodels.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by deepaksood619 on 2/6/16.
 */

public class Allotment{

    private Connection connection;


    public Allotment() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<RequirementDetails> getRequirementDetails() {

        ArrayList<RequirementDetails> requirementDetailsArrayList= new ArrayList<>();

        String getQuery = "SELECT * FROM RequirementDetails;";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(getQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            RequirementDetails requirementDetails;
            while(resultSet.next()) {
                requirementDetails = new RequirementDetails(resultSet.getString("Subject"), resultSet.getInt("NumOfTa"));
                requirementDetailsArrayList.add(requirementDetails);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return requirementDetailsArrayList;
    }

    public int getNoOfTa(String coursePreference) {
        String getQuery = "SELECT NumOfTa FROM RequirementDetails WHERE Subject=\""+coursePreference+"\";";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(getQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int noOfTa = resultSet.getInt(1);
                System.out.println("noOfTA req: "+coursePreference+" - "+noOfTa);
                return noOfTa;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getAllotedNoOfTa(String coursePreference) {
        String getQuery = "SELECT COUNT(*) FROM FinalAllotment WHERE SubjectAlloted=\""+coursePreference+"\";";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(getQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                int noOfTa = resultSet.getInt(1);
                System.out.println("noOfTA req: "+coursePreference+" - "+noOfTa);
                return noOfTa;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<Students> getStudentsDetails(String getQuery) {

        ArrayList<Students> studentsArrayList = new ArrayList<>();

        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(getQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            Students students;
            while(resultSet.next()) {
                students = new Students(resultSet.getString("StudentName"), resultSet.getString("RollNumber"), resultSet.getFloat("CGPA"), resultSet.getString("Program"), resultSet.getString("FirstPreference"));
                studentsArrayList.add(students);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentsArrayList;
    }

    public void startAllotment(ArrayList<RequirementDetails> requirementDetailsArrayList, ArrayList<Students> phdStudents, ArrayList<Students> pgStudents, ArrayList<Students> ugStudents) {

        ArrayList<FinalAllotment> finalAllotmentArrayList = new ArrayList<>();
        FinalAllotment finalAllotment;

        for(RequirementDetails details: requirementDetailsArrayList) {
            System.out.println("details: "+details.getSubject()+" - "+details.getNumOfTa());
        }

        for(Students students: phdStudents) {
            System.out.println("phd: "+students.getStudentName()+" - "+students.getProgram()+" - first: "+students.getFirst_preference()+" - "+students.getCgpa());
        }

        for(Students students: pgStudents) {
            System.out.println("pg: "+students.getStudentName()+" - "+students.getProgram()+" - first: "+students.getFirst_preference()+" - "+students.getCgpa());
        }

        for(Students students: ugStudents) {
            System.out.println("ug: "+students.getStudentName()+" - "+students.getProgram()+" - first: "+students.getFirst_preference()+" - "+students.getCgpa());
        }

        System.out.println("Start allotment");

        for(RequirementDetails requirementDetails: requirementDetailsArrayList) {
            int numOfMaxPhd = 0;
            int numOfMaxPg = 0;
            int numOfMaxUg = 0;

            if(requirementDetails.getNumOfTa() == 1) {
                numOfMaxPhd = 1;
                numOfMaxPg = 1;
                numOfMaxUg = 1;
            }
            else if(requirementDetails.getNumOfTa() > 1) {
                numOfMaxPhd = requirementDetails.getNumOfTa()/2;
                numOfMaxPg = requirementDetails.getNumOfTa()/2;
                numOfMaxUg = (int) (requirementDetails.getNumOfTa() *.3);
            }

            String selectSubject = requirementDetails.getSubject();
            int numOfTa = requirementDetails.getNumOfTa();

            for(Students students: phdStudents) {
                if(students.getFirst_preference().equalsIgnoreCase(selectSubject) && numOfMaxPhd >0 && numOfTa >0) {
                    finalAllotment = new FinalAllotment(students.getStudentName(), students.getFirst_preference());
                    numOfMaxPhd--;
                    numOfTa--;
                    finalAllotmentArrayList.add(finalAllotment);
                }
            }
            for(Students students: pgStudents) {
                if(students.getFirst_preference().equalsIgnoreCase(selectSubject) && numOfMaxPg >0 && numOfTa>0) {
                    finalAllotment = new FinalAllotment(students.getStudentName(), students.getFirst_preference());
                    numOfMaxPg--;
                    numOfTa--;
                    finalAllotmentArrayList.add(finalAllotment);
                }
            }
            for(Students students: ugStudents) {
                if(students.getFirst_preference().equalsIgnoreCase(selectSubject) && numOfMaxUg>0 && numOfTa>0) {
                    finalAllotment = new FinalAllotment(students.getStudentName(), students.getFirst_preference());
                    numOfMaxUg--;
                    numOfTa--;
                    finalAllotmentArrayList.add(finalAllotment);
                }
            }
        }

        System.out.println("final allotment");
        for(FinalAllotment finalAllotment1: finalAllotmentArrayList) {
            System.out.println("allot: "+finalAllotment1.getStudentName()+" - "+finalAllotment1.getSubjectAlloted());
        }

        saveAllotmentResultToDatabase(finalAllotmentArrayList);

    }

    public void saveAllotmentResultToDatabase(ArrayList<FinalAllotment> finalAllotmentArrayList) {
        String getQuery;
        for(FinalAllotment finalAllotment: finalAllotmentArrayList) {
            getQuery = "INSERT INTO FinalAllotment VALUES(\""+finalAllotment.getStudentName()+"\", \""+finalAllotment.getSubjectAlloted()+"\")";
            PreparedStatement preparedStatement;
            try {
                preparedStatement = connection.prepareStatement(getQuery);
                int check = preparedStatement.executeUpdate();
                if(check == 0) {
                    System.out.println("Unsuccessfull insert");
                }
                else
                    System.out.println("Successfull insert");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int addNewAllotment(String studentName, String subjectAlloted) {
        String getQuery = "INSERT INTO FinalAllotment VALUES(\""+studentName+"\", \""+subjectAlloted+"\");";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(getQuery);
            int check = preparedStatement.executeUpdate();
            if(check == 0) {
                System.out.println("Unsuccessfull insert");
            }
            else {
                System.out.println("Successfull insert");
                return 0;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

}
