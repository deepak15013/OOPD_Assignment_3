package in.deepaksood;

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
@WebServlet(name = "ShowResults", urlPatterns = {"/ShowResults"})
public class ShowResults extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("inside doPost show Results");
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/OOPD_Assignment_3","root","root");
            String countQuery = "SELECT COUNT(*) FROM FinalAllotment;";
            PreparedStatement preparedStatement = connection.prepareStatement(countQuery);

            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int alloted = resultSet.getInt(1);
            System.out.println("alloted number: "+alloted);
            if(alloted > 0) {
                response.sendRedirect("check_allotment.jsp");
            }
            else {
                request.setAttribute("error_message","OOPS! Allotment Not Yet Done");
                String nextJSP = "/error.jsp";
                getServletContext().getRequestDispatcher(nextJSP).forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
