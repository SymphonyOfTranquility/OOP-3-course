import DataBase.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/donePageClient")
public class DonePage extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (DBConnection.exists("client")) {
            getServletContext().getRequestDispatcher("/client/donePage.jsp").forward(request, response);
        } else {
            response.sendRedirect("/lab_2_war_exploded/logOut");
        }
    }
}
