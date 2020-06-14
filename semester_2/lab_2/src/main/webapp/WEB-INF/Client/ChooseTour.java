import DataBase.DBConnection;
import TableElements.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/chooseTour")
public class ChooseTour extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("client")) {
            List<Tour> tours = DBConnection.getAllTours();
            System.out.println(tours.size());
            request.setAttribute("Tours", tours.toArray(new Tour[0]));
            getServletContext().getRequestDispatcher("/client/chooseTour.jsp").
                    forward(request, response);
        }
        else
        {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("client")) {
            String tourId = request.getParameter("id");
            DBConnection.addTourToUser(Integer.parseInt(tourId));
            System.out.println("Done!");
            response.sendRedirect("/lab_2_war_exploded/donePageClient");
        }
        else
        {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }
}
