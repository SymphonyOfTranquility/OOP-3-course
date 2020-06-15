import DataBase.DBConnection;
import TableElements.Tour;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/yourTours")
public class YourTours extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("client")) {
            List<Tour> tours = DBConnection.getOwnTours();
            System.out.println(tours.size());
            request.setAttribute("Tours", tours.toArray(new Tour[0]));
            getServletContext().getRequestDispatcher("/client/yourTours.jsp").forward(request, response);
        } else {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }
}
