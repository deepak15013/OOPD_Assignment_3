package in.deepaksood;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by deepaksood619 on 3/6/16.
 */
@WebServlet(name = "SearchAllotment", urlPatterns = {"/search"})
public class SearchAllotment extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside do get search allotment");
        String searchRoll = request.getParameter("search_roll");
        System.out.println(searchRoll);

        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
            String rollQuery = "SELECT StudentName FROM Students WHERE RollNumber=\""+searchRoll+"\";";
            PreparedStatement preparedStatement = connection.prepareStatement(rollQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                String searchName = resultSet.getString(1);
                System.out.println("name: "+searchName);

                String nameQuery = "SELECT * FROM FinalAllotment WHERE StudentName=\""+searchName+"\";";

                preparedStatement = connection.prepareStatement(nameQuery);
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()) {
                    String subjectName = resultSet.getString("SubjectAlloted");

                    String sendMessage = "Hello "+searchName+", you have been alloted "+subjectName;

                    request.setAttribute("error_message",sendMessage);
                    String nextJSP = "/error.jsp";
                    getServletContext().getRequestDispatcher(nextJSP).forward(request, response);

                }
                else {
                    request.setAttribute("student_name", searchName);
                    String nextJsp = "/reallotment_form.jsp";
                    getServletContext().getRequestDispatcher(nextJsp).forward(request, response);
                }
            }
            else {
                request.setAttribute("error_message","No Student Record Found");
                String nextJSP = "/error.jsp";
                getServletContext().getRequestDispatcher(nextJSP).forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
