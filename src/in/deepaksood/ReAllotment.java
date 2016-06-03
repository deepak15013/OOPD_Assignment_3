package in.deepaksood;

import in.objectmodels.RequirementDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by deepaksood619 on 3/6/16.
 */
@WebServlet(name = "ReAllotment", urlPatterns = {"/reallotment"})
public class ReAllotment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentName = (String) request.getSession().getAttribute("student_name");
        String newPreference = request.getParameter("new_preference");

        System.out.println("student: "+studentName+" - "+newPreference);

        Allotment allotment = new Allotment();
        int noOfTa = allotment.getNoOfTa(newPreference);

        int allotedNoOfTa = allotment.getAllotedNoOfTa(newPreference);

        System.out.println("Required no of TA: "+noOfTa+" ,alloted: "+allotedNoOfTa);

        if(noOfTa > allotedNoOfTa) {
            int check = allotment.addNewAllotment(studentName, newPreference);
            if(check == 0) {
                String sendMessage = "Hello "+studentName+", Congrats you have been alloted "+newPreference+".";

                request.setAttribute("error_message",sendMessage);
                String nextJSP = "/error.jsp";
                getServletContext().getRequestDispatcher(nextJSP).forward(request, response);

            }
            else {
                String sendMessage = "Hello "+studentName+", Try again, something went wrong.";

                request.setAttribute("error_message",sendMessage);
                String nextJSP = "/error.jsp";
                getServletContext().getRequestDispatcher(nextJSP).forward(request, response);
            }
        }
        else {
            String sendMessage = "Hello "+studentName+", you cannot be alloted "+newPreference+". Its Full, Try Again";

            request.setAttribute("error_message",sendMessage);
            String nextJSP = "/error.jsp";
            getServletContext().getRequestDispatcher(nextJSP).forward(request, response);
        }
    }
}
