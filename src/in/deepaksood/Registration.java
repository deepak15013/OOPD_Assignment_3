package in.deepaksood;

import in.objectmodels.RequirementDetails;
import in.objectmodels.Students;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by deepaksood619 on 2/6/16.
 */

@WebServlet(name = "Registration" , urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    private static final String getPhdStudentsQuery = "SELECT * FROM Students WHERE Program=\"PHD\" ORDER BY cgpa DESC;";
    private static final String getPgStudentsQuery = "SELECT * FROM Students WHERE Program=\"PG\" ORDER BY cgpa DESC;";
    private static final String getUgStudentsQuery = "SELECT * FROM Students WHERE Program=\"UG\" ORDER BY cgpa DESC;";

    static int numOfStudentRecords = 0;

    Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
            PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE Students (StudentName varchar(20), RollNumber varchar(20), CGPA Float, Program varchar(4), FirstPreference varchar(20));");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("CREATE TABLE RequirementDetails(Subject varchar(20), NumOfTa int);");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("CREATE TABLE FinalAllotment(StudentName varchar(20), SubjectAlloted varchar(20));");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO RequirementDetails VALUES (\"graduate_algorithm\", 4);");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO RequirementDetails VALUES (\"security_engineering\", 3);");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO RequirementDetails VALUES (\"mobile_computing\", 3);");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("INSERT INTO RequirementDetails VALUES (\"oopd\", 5);");
            preparedStatement.execute();

            preparedStatement.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Inside post");

        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");

            String countQuery = "SELECT COUNT(*) FROM Students;";
            PreparedStatement preparedStatement = connection.prepareStatement(countQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            numOfStudentRecords = resultSet.getInt(1);
            System.out.println("Num of Students in database: "+numOfStudentRecords);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if(numOfStudentRecords < 15) {
            String name = request.getParameter("name");
            String rollNumber = request.getParameter("rollNumber");
            String cgpaString = request.getParameter("cgpa");
            String program = request.getParameter("program");
            String first_preference = request.getParameter("first_preference");
            Float cgpa = Float.valueOf(cgpaString);

            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            printWriter.println("Hello "+name);

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
                String insertQuery = "INSERT INTO Students VALUES (\""+name+"\", \""+rollNumber+"\", "+cgpa+", \""+program+"\", \""+first_preference+"\");";
                System.out.println("Query: "+insertQuery);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);

                int i = preparedStatement.executeUpdate();
                if(i>0) {
                    System.out.println("Successful");
                    numOfStudentRecords++;
                }
                else {
                    System.out.println("Query Failed");
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }

        if(numOfStudentRecords >= 15) {
            String countQuery = "SELECT COUNT(*) FROM FinalAllotment";
            int allotedNumber;
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(countQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                allotedNumber = resultSet.getInt(1);
                System.out.println("Num of alloted students: "+allotedNumber);

                if(allotedNumber == 0) {
                    Allotment allotment = new Allotment();
                    ArrayList<RequirementDetails> requirementDetailsArrayList = allotment.getRequirementDetails();

                    ArrayList<Students> phdStudents = allotment.getStudentsDetails(getPhdStudentsQuery);
                    ArrayList<Students> pgStudents = allotment.getStudentsDetails(getPgStudentsQuery);
                    ArrayList<Students> ugStudents = allotment.getStudentsDetails(getUgStudentsQuery);

                    allotment.startAllotment(requirementDetailsArrayList, phdStudents, pgStudents, ugStudents);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            response.sendRedirect("allotment_done.jsp");
        }
        else {
            response.sendRedirect("success.jsp");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
            PreparedStatement preparedStatement = connection.prepareStatement("DROP TABLE Students");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("DROP TABLE RequirementDetails");
            preparedStatement.execute();

            preparedStatement = connection.prepareStatement("DROP TABLE FinalAllotment");
            preparedStatement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        if(connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
