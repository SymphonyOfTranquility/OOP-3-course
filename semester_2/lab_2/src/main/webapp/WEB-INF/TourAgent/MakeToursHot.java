import DataBase.DBConnection;
import TableElements.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/makeToursHot")
public class MakeToursHot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("tour_agent")) {
            List<Tour> tours = DBConnection.getAllTours();
            System.out.println(tours.size());
            request.setAttribute("Tours", tours.toArray(new Tour[0]));
            getServletContext().getRequestDispatcher("/tour_agent/makeToursHot.jsp").
                    forward(request, response);
        }
        else
        {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("tour_agent")) {
            String tourId = request.getParameter("id");
            String isHot = request.getParameter("is_hot");
            DBConnection.changeTourHotness(Integer.parseInt(tourId), Boolean.parseBoolean(isHot));
            System.out.println("Done!");
            response.sendRedirect("/lab_2_war_exploded/donePageTourAgent");
        }
        else
        {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }
}
