import DataBase.DBConnection;
import TableElements.Tour;
import TableElements.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/approveAdmin")
public class ApproveAdmin extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("tour_agent")) {
            List<User> users = DBConnection.getAllUnapproved();
            System.out.println(users.size());
            request.setAttribute("Users", users.toArray(new User[0]));
            getServletContext().getRequestDispatcher("/tour_agent/approveAdmin.jsp").forward(request, response);
        } else {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("tour_agent")) {
            String tourId = request.getParameter("id");
            DBConnection.approveUser(Integer.parseInt(tourId));
            System.out.println("Done!");
            response.sendRedirect("/lab_2_war_exploded/donePageTourAgent");
        }
        else
        {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }
}
